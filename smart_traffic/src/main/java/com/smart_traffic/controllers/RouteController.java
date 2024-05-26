package com.smart_traffic.controllers;

import com.smart_traffic.dtos.route.request.RouteRequest;
import com.smart_traffic.dtos.route.response.RouteResponse;
import com.smart_traffic.services.RouteService;
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
@RequestMapping("/api/v1/route")
public class RouteController {

    private final RouteService routeService;


    @Operation(summary = "Busca de rota especifica", description = "Este endpoint get, consiste no busca de uma rota especifica", tags = {"Route"})
    @GetMapping(value = "/{routeId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<RouteResponse> findById(@PathVariable Long routeId) {
        RouteResponse routeResponse = routeService.findById(routeId);
        return ResponseEntity.status(HttpStatus.OK).body(routeResponse);
    }

    @Operation(summary = "Listagem de rotas", description = "Este endpoint get, consiste na listagem de todas as rotas", tags = {"Route"})
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<RouteResponse>> findAll() {
        List<RouteResponse> routeResponseList = routeService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(routeResponseList);
    }

    @Operation(summary = "Cadastro de rota", description = "Este endpoint post, consiste no cadastro de uma rota", tags = {"Route"})
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<RouteResponse> createRoute(@Valid @RequestBody RouteRequest routeRequest) {
        RouteResponse routeResponse =  routeService.save(routeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(routeResponse);
    }

    @Operation(summary = "Atualização de rota", description = "Este endpoint put, consiste na atualização de uma rota existente", tags = {"Route"})
    @PutMapping(value = "/{routeId}")
    public ResponseEntity<RouteResponse> updateRoute(@PathVariable Long routeId, @Valid @RequestBody RouteRequest routeRequest) {
        RouteResponse routeResponse = routeService.update(routeId, routeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(routeResponse);
    }

    @Operation(summary = "Delete de rota", description = "Este endpoint delete, consiste na exclusão de uma rota existente", tags = {"Route"})
    @DeleteMapping(value = "/{routeId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long routeId) {
        routeService.deleteById(routeId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
