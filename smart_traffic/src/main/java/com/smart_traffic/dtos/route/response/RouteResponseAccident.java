package com.smart_traffic.dtos.route.response;

import com.smart_traffic.entities.Accident;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteResponseAccident {

    private Long routeId;
    private String routeStartLocation;
    private String routeEndLocation;
    private double routeDistance;
    private List<Accident> accidents;
}
