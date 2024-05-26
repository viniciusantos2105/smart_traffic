package com.smart_traffic.entities;

import com.smart_traffic.enums.TrafficPatternVolume;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrafficPattern {


    private Long trafficPatternId;
    private String trafficPatternLocation;
    private LocalTime trafficPatternTime;
    private TrafficPatternVolume trafficPatternVolume;
    private Route route;
}
