package com.smart_traffic.services;

import com.smart_traffic.adapters.Adapter;
import com.smart_traffic.controllers.AccidentController;
import com.smart_traffic.dtos.accident.request.AccidentRequest;
import com.smart_traffic.dtos.accident.response.AccidentResponse;
import com.smart_traffic.dtos.route.response.RouteResponse;
import com.smart_traffic.entities.Accident;
import com.smart_traffic.entities.Route;
import com.smart_traffic.enums.AccidentSeverity;
import com.smart_traffic.models.AccidentModel;
import com.smart_traffic.repositories.accident.AccidentRepository;
import com.smart_traffic.repositories.accident.AccidentRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@Service
public class AccidentService implements GenericService<AccidentResponse, AccidentRequest>{

    private final Adapter adapter;
    private final RouteService routeService;
    private final TrafficPatternService trafficPatternService;
    private final AccidentRepository accidentRepository;
    private final AccidentRepositoryImpl accidentRepositoryImpl;

    public AccidentResponse findById(Long accidentId) {
        AccidentModel accidentModel = accidentRepositoryImpl.findById(accidentId);
        AccidentResponse accidentResponse = adapter.map(accidentModel, AccidentResponse.class);
        accidentResponse.add(linkTo(methodOn(AccidentController.class).findById(accidentResponse.getAccidentId())).withSelfRel());
        accidentResponse.add(linkTo(methodOn(AccidentController.class).findAll()).withRel("accidents"));
        return accidentResponse;
    }


    public List<AccidentResponse> findAll() {
        return accidentRepositoryImpl.listAll().stream()
                .map(accidentModel -> adapter.map(accidentModel, AccidentResponse.class))
                .toList();
    }


    public void deleteById(Long id) {
        AccidentModel accidentModel = accidentRepositoryImpl.findById(id);
        accidentRepository.delete(accidentModel);
    }

    public AccidentResponse update(Long accidentId,AccidentRequest entity){
        AccidentModel accidentModel = accidentRepositoryImpl.findById(accidentId);
        Accident accident = adapter.map(entity, Accident.class);
        adapter.updateTargetFromSource(accident, accidentModel);
        accident = adapter.map(accidentRepository.save(accidentModel) , Accident.class);
        trafficPatternService.predictTrafficPattern(accident.getRoute());
        List<RouteResponse> suggestedRoutes =  routeService.suggestFastestRoute();
        AccidentResponse response = adapter.map(accident, AccidentResponse.class);
        response.setSuggestedRoutes(suggestedRoutes);
        response.add(linkTo(methodOn(AccidentController.class).findById(response.getAccidentId())).withSelfRel());
        return response;
    }

    public AccidentResponse save(AccidentRequest entity) {
        Accident accident = adapter.map(entity, Accident.class);
        Route route = adapter.map(routeService.findById(entity.getRouteId()), Route.class);
        accident.setRoute(route);
        severityLevel(accident);
        AccidentModel accidentModel = adapter.map(accident, AccidentModel.class);
        accidentRepository.save(accidentModel);
        accident = adapter.map(accidentModel, Accident.class);
        List<RouteResponse> suggestedRoutes = routeService.suggestFastestRoute();
        trafficPatternService.predictTrafficPattern(accident.getRoute());
        AccidentResponse response = adapter.map(accident, AccidentResponse.class);
        response.setSuggestedRoutes(suggestedRoutes);
        response.add(linkTo(methodOn(AccidentController.class).findById(response.getAccidentId())).withSelfRel());
        return response;
    }

    private void severityLevel(Accident accident) {
        if (accident.getAccidentInvolvedVehicles() < 2) {
            accident.setAccidentSeverity(AccidentSeverity.LOW);
        } else if (accident.getAccidentInvolvedVehicles() < 5) {
            accident.setAccidentSeverity(AccidentSeverity.MEDIUM);
        } else {
            accident.setAccidentSeverity(AccidentSeverity.HIGH);
        }
    }
}
