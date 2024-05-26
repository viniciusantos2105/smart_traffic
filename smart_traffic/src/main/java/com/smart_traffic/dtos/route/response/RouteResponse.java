package com.smart_traffic.dtos.route.response;

import com.smart_traffic.dtos.accident.response.AccidentResponse;
import com.smart_traffic.dtos.trafficPattern.response.TrafficPatternResponseRoute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteResponse  extends RepresentationModel<RouteResponse>  {

    private Long routeId;
    private String routeStartLocation;
    private String routeEndLocation;
    private double routeDistance;
    private List<TrafficPatternResponseRoute> trafficPatterns;
    private List<AccidentResponse> accidents;

}
