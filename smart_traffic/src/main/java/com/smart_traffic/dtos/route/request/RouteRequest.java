package com.smart_traffic.dtos.route.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteRequest {

    @NotBlank(message = "Localização de início da rota é obrigatório")
    private String routeStartLocation;
    @NotBlank(message = "Localização de fim da rota é obrigatório")
    private String routeEndLocation;
    @NotNull(message = "Distância da rota é obrigatório")
    private double routeDistance;
}
