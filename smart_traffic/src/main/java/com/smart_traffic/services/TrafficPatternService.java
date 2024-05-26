package com.smart_traffic.services;

import com.smart_traffic.adapters.Adapter;
import com.smart_traffic.controllers.RouteController;
import com.smart_traffic.controllers.TrafficPatternController;
import com.smart_traffic.dtos.trafficPattern.request.TrafficPatternRequest;
import com.smart_traffic.dtos.trafficPattern.response.TrafficPatternResponse;
import com.smart_traffic.entities.Accident;
import com.smart_traffic.entities.Route;
import com.smart_traffic.entities.TrafficPattern;
import com.smart_traffic.enums.TrafficPatternVolume;
import com.smart_traffic.exceptions.commons.NotFoundException;
import com.smart_traffic.models.TrafficPatternModel;
import com.smart_traffic.repositories.trafficPattern.TrafficPatternRepository;
import com.smart_traffic.repositories.trafficPattern.TrafficPatternRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@Service
public class TrafficPatternService implements GenericService<TrafficPatternResponse, TrafficPatternRequest>{

    private final Adapter adapter;
    private final RouteService routeService;
    private final TrafficPatternRepositoryImpl trafficPatternRepositoryImpl;
    private final TrafficPatternRepository trafficPatternRepository;

    public TrafficPatternResponse save(TrafficPatternRequest entity) {
        TrafficPattern trafficPattern = adapter.map(entity, TrafficPattern.class);
        selectTrafficPatternVolume(trafficPattern, entity);
        Route route = routeService.findByIdEntity(entity.getRouteId());
        trafficPattern.setRoute(route);
        TrafficPatternModel trafficPatternModel = adapter.map(trafficPattern, TrafficPatternModel.class);
        trafficPatternRepository.save(trafficPatternModel);

        TrafficPatternResponse trafficPatternResponse = adapter.map(trafficPatternModel, TrafficPatternResponse.class);
        trafficPatternResponse.add(linkTo(methodOn(TrafficPatternController.class).findById(trafficPatternResponse.getTrafficPatternId())).withSelfRel());
        trafficPatternResponse.add(linkTo(methodOn(TrafficPatternController.class).findAll()).withSelfRel());
        trafficPatternResponse.add(linkTo(RouteController.class).slash(trafficPatternResponse.getRoute().getRouteId()).withRel("route"));
        return trafficPatternResponse;
    }


    public TrafficPatternResponse findById(Long id) {
        TrafficPatternModel trafficPatternModel = trafficPatternRepositoryImpl.findById(id);
        TrafficPatternResponse trafficPatternResponse = adapter.map(trafficPatternModel, TrafficPatternResponse.class);

        trafficPatternResponse.add(linkTo(methodOn(TrafficPatternController.class).findById(trafficPatternResponse.getTrafficPatternId())).withSelfRel());
        trafficPatternResponse.add(linkTo(methodOn(TrafficPatternController.class).findAll()).withSelfRel());
        return trafficPatternResponse;
    }


    public List<TrafficPatternResponse> findAll() {
        List<TrafficPatternResponse> trafficPatternResponseList = trafficPatternRepositoryImpl.listAll().stream()
                .map(trafficPatternModel -> adapter.map(trafficPatternModel, TrafficPatternResponse.class))
                .toList();
        trafficPatternResponseList.forEach(trafficPatternResponse -> {
            trafficPatternResponse.add(linkTo(RouteController.class).slash(trafficPatternResponse.getTrafficPatternId()).withSelfRel());
        });
        return trafficPatternResponseList;
    }


    public void deleteById(Long id) {
        TrafficPatternModel trafficPatternModel = trafficPatternRepositoryImpl.findById(id);
        trafficPatternRepository.delete(trafficPatternModel);
    }

    public void predictTrafficPattern(Route route) {
        for (Accident accident : route.getAccidents()) {
            Duration duration = Duration.between(accident.getAccidentTime(), LocalDateTime.now());
            long hoursDiference = Math.abs(duration.toHours());
            for(TrafficPattern trafficPattern : route.getTrafficPatterns()){
                if (hoursDiference < 1) {
                    trafficPattern.setTrafficPatternVolume(TrafficPatternVolume.HIGH);
                    trafficPatternRepository.save(adapter.map(trafficPattern, TrafficPatternModel.class));
                } else if (hoursDiference < 2) {
                    trafficPattern.setTrafficPatternVolume(TrafficPatternVolume.MEDIUM);
                    trafficPatternRepository.save(adapter.map(trafficPattern, TrafficPatternModel.class));
                } else {
                    trafficPattern.setTrafficPatternVolume(TrafficPatternVolume.LOW);
                    trafficPatternRepository.save(adapter.map(trafficPattern, TrafficPatternModel.class));
                }
            }
        }
    }

    private void selectTrafficPatternVolume(TrafficPattern trafficPattern, TrafficPatternRequest entity) {
       for(TrafficPatternVolume volume : TrafficPatternVolume.values()) {
           if(volume.getDescription().equalsIgnoreCase(entity.getTrafficPatternVolume())) {
               trafficPattern.setTrafficPatternVolume(volume);
               return;
           }
       }
       throw NotFoundException.createNotFoundException("Tipo de volume de tráfego não encontrado, Use Baixo, Medio ou Alto");
    }
}
