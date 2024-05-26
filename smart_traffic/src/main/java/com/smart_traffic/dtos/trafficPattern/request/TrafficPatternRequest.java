package com.smart_traffic.dtos.trafficPattern.request;

import jakarta.validation.constraints.NotBlank;
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
public class TrafficPatternRequest {

    @NotBlank(message = "Localização do padrão de tráfego é obrigatório")
    private String trafficPatternLocation;
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Hora do padrão de tráfego deve estar no formato HH:mm")
    private String trafficPatternTime;
    @NotBlank(message = "Volume do padrão de tráfego é obrigatório")
    private String trafficPatternVolume;
    @NotNull(message = "Id da rota é obrigatório")
    private Long routeId;
}
