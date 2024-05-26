package com.smart_traffic.controllers;


import com.smart_traffic.dtos.trafficPattern.request.TrafficPatternRequest;
import com.smart_traffic.dtos.trafficPattern.response.TrafficPatternResponse;
import com.smart_traffic.services.TrafficPatternService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/route/traffic-pattern")
public class TrafficPatternController {

    private final TrafficPatternService trafficPatternService;

    @Operation(summary = "Busca de padrão de trafego", description = "Este endpoint get, consiste na busca de uma padrão de trafego especifico", tags = {"Traffic-Pattern"})
    @GetMapping(value = "/{trafficPatternId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TrafficPatternResponse> findById(@PathVariable Long trafficPatternId) {
        TrafficPatternResponse trafficPatternResponse = trafficPatternService.findById(trafficPatternId);
        return ResponseEntity.status(HttpStatus.OK).body(trafficPatternResponse);
    }

    @Operation(summary = "Listagem de padrões de trafego", description = "Este endpoint get, consiste na listagem de todos os padrões de trafego", tags = {"Traffic-Pattern"})
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<TrafficPatternResponse>> findAll() {
        List<TrafficPatternResponse> trafficPatternResponseList = trafficPatternService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(trafficPatternResponseList);
    }

    @Operation(summary = "Cadastro de padrão de trafego", description = "Este endpoint post, consiste no cadastro de um padrão de trafego", tags = {"Traffic-Pattern"})
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TrafficPatternResponse> createTrafficPattern(@Valid @RequestBody TrafficPatternRequest trafficPatternRequest) {
        TrafficPatternResponse trafficPatternResponse = trafficPatternService.save(trafficPatternRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(trafficPatternResponse);
    }

    @Operation(summary = "Delete de padrão de trafego", description = "Este endpoint delete, consiste na exclusão de uma padrão de trafego especifico", tags = {"Traffic-Pattern"})
    @DeleteMapping(value = "/{trafficPatternId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long trafficPatternId) {
        trafficPatternService.deleteById(trafficPatternId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
