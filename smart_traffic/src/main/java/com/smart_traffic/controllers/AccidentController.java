package com.smart_traffic.controllers;

import com.smart_traffic.dtos.accident.request.AccidentRequest;
import com.smart_traffic.dtos.accident.response.AccidentResponse;
import com.smart_traffic.services.AccidentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import com.smart_traffic.util.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/route/accident")
public class AccidentController {


    private final AccidentService accidentService;

    @Operation(summary = "Busca de acidente especifico", description = "Este endpoint get, consiste na busca de um acidente especifico", tags = {"Accident"})
    @GetMapping(value = "/{accidentId}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ResponseEntity<AccidentResponse> findById(@PathVariable Long accidentId) {
        AccidentResponse accidentResponse = accidentService.findById(accidentId);
        return ResponseEntity.status(HttpStatus.OK).body(accidentResponse);
    }

    @Operation(summary = "Lista de acidentes", description = "Este endpoint get, consiste na listagem de todos os acidentes", tags = {"Accident"})
    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ResponseEntity<List<AccidentResponse>> findAll() {
        List<AccidentResponse> accidentResponseList = accidentService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(accidentResponseList);
    }

    @Operation(summary = "Cadastro de acidente", description = "Este endpoint post, consiste no registro de um acidente", tags = {"Accident"})
    @PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ResponseEntity<AccidentResponse> createAccident(@Valid @RequestBody AccidentRequest accidentRequest){
        AccidentResponse accidentResponse = accidentService.save(accidentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(accidentResponse);
    }

    @Operation(summary = "Atualização de acidente", description = "Este endpoint put, consiste na atualização de um acidente existente", tags = {"Accident"})
    @PutMapping(value = "/{accidentId}")
    public ResponseEntity<AccidentResponse> updateAccident(@PathVariable Long accidentId, @Valid @RequestBody AccidentRequest accidentRequest) {
        AccidentResponse accidentResponse = accidentService.update(accidentId, accidentRequest);
        return ResponseEntity.status(HttpStatus.OK).body(accidentResponse);
    }

    @Operation(summary = "Delete de acidente", description = "Este endpoint delete, consiste na exclusão de um acidente existente", tags = {"Accident"})
    @DeleteMapping(value = "/{accidentId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long accidentId) {
        accidentService.deleteById(accidentId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
