package com.smart_traffic.dtos.trafficPattern.response;

import com.smart_traffic.enums.TrafficPatternVolume;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrafficPatternResponseRoute {

    private Long trafficPatternId;
    private String trafficPatternLocation;
    private String trafficPatternTime;
    private TrafficPatternVolume trafficPatternVolume;
}
