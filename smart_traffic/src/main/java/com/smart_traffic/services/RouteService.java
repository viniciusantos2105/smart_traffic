package com.smart_traffic.services;

import com.smart_traffic.adapters.Adapter;
import com.smart_traffic.controllers.RouteController;
import com.smart_traffic.dtos.route.request.RouteRequest;
import com.smart_traffic.dtos.route.response.RouteResponse;
import com.smart_traffic.entities.Route;
import com.smart_traffic.entities.TrafficPattern;
import com.smart_traffic.enums.TrafficPatternVolume;
import com.smart_traffic.models.RouteModel;
import com.smart_traffic.repositories.route.RouteRepository;
import com.smart_traffic.repositories.route.RouteRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@Service
public class RouteService implements GenericService<RouteResponse, RouteRequest>{

    private final Adapter adapter;
    private final RouteRepository routeRepository;
    private final RouteRepositoryImpl routeRepositoryImpl;

    public RouteResponse save(RouteRequest entity) {
        Route route = adapter.map(entity, Route.class);
        routeRepositoryImpl.existRoute(route.getRouteStartLocation(), route.getRouteEndLocation());
        RouteModel routeModel = adapter.map(route, RouteModel.class);
        routeRepository.save(routeModel);
        RouteResponse response = adapter.map(routeModel, RouteResponse.class);
        response.add(linkTo(RouteController.class).slash(response.getRouteId()).withSelfRel());
        response.add(linkTo(methodOn(RouteController.class).createRoute(entity)).withSelfRel());
        return response;
    }


    public RouteResponse findById(Long routeId) {
        RouteModel routeModel = routeRepositoryImpl.findById(routeId);
        RouteResponse response = adapter.map(routeModel, RouteResponse.class);
        response.add(linkTo(methodOn(RouteController.class).deleteById(response.getRouteId())).withSelfRel());
        response.add(linkTo(methodOn(RouteController.class).findAll()).withSelfRel());
        return response;
    }

    public Route findByIdEntity(Long routeId) {
        RouteModel routeModel = routeRepositoryImpl.findById(routeId);
        return adapter.map(routeModel, Route.class);
    }


    public List<RouteResponse> findAll() {
        List<RouteResponse> routeResponseList = routeRepositoryImpl.listAll().stream()
                .map(routeModel -> adapter.map(routeModel, RouteResponse.class))
                .toList();
        routeResponseList.forEach(routeResponse -> {
            routeResponse.add(linkTo(RouteController.class).slash(routeResponse.getRouteId()).withSelfRel());
        });
        return routeResponseList;
    }

    public RouteResponse update(Long id, RouteRequest entity) {
        RouteModel routeModel = routeRepositoryImpl.findById(id);
        Route route = adapter.map(entity, Route.class);
        adapter.updateTargetFromSource(route, routeModel);
        routeRepository.save(routeModel);
        RouteResponse response = adapter.map(routeModel, RouteResponse.class);
        response.add(linkTo(RouteController.class).slash(response.getRouteId()).withSelfRel());
        response.add(linkTo(methodOn(RouteController.class).createRoute(entity)).withSelfRel());
        return response;
    }

    public void deleteById(Long id) {
        RouteModel routeModel = routeRepositoryImpl.findById(id);
        routeRepository.delete(routeModel);
    }

   // Roteamento Inteligente: O sistema deve ser capaz de sugerir a rota mais rápida com base no padrão de tráfego e na distância.
    public List<RouteResponse> suggestFastestRoute() {
        List<RouteResponse> suggestRoutes = new ArrayList<>();
        List<Route> routeList = routeRepositoryImpl.listAll().stream().map(routeModel -> adapter.map(routeModel, Route.class)).toList();
        List<TrafficPattern> trafficPatternLowerVolumeList =  routeList.stream()
                .flatMap(route -> route.getTrafficPatterns().stream())
                .filter(trafficPattern -> trafficPattern.getTrafficPatternVolume() != TrafficPatternVolume.HIGH)
                .sorted(Comparator.comparing(TrafficPattern::getTrafficPatternVolume))
                .limit(3)
                .toList();
        for(TrafficPattern trafficPattern : trafficPatternLowerVolumeList){
            suggestRoutes.add(adapter.map(trafficPattern.getRoute(), RouteResponse.class));
        }
        return suggestRoutes;
    }



}
