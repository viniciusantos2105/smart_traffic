package com.smart_traffic.dtos.accident.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccidentRequest {

    @NotNull(message = "Local do acidente é obrigatório")
    private String accidentLocation;
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Hora do acidente deve estar no formato HH:mm")
    private String accidentTime;
    @NotNull(message = "Número de veículos envolvidos no acidente é obrigatório")
    private int accidentInvolvedVehicles;
    @NotNull(message = "Id da rota é obrigatório")
    private Long routeId;
}
