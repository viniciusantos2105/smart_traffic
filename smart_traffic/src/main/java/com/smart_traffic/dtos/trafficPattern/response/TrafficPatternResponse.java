package com.smart_traffic.dtos.trafficPattern.response;

import com.smart_traffic.dtos.route.response.RouteResponseTraffic;
import com.smart_traffic.enums.TrafficPatternVolume;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrafficPatternResponse  extends RepresentationModel<TrafficPatternResponse> {

    private Long trafficPatternId;
    private String trafficPatternLocation;
    private String trafficPatternTime;
    private TrafficPatternVolume trafficPatternVolume;
    private RouteResponseTraffic route;
}
