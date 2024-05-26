package com.smart_traffic.dtos.route.response;

import com.smart_traffic.dtos.accident.response.AccidentResponseRoute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteResponseTraffic {

    private Long routeId;
    private String routeStartLocation;
    private String routeEndLocation;
    private double routeDistance;
    private List<AccidentResponseRoute> accidents;
}
