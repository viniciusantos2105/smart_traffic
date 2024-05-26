package com.smart_traffic.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    private Long routeId;
    private String routeStartLocation;
    private String routeEndLocation;
    private double routeDistance;
    private List<TrafficPattern> trafficPatterns = new ArrayList<>();
    private List<Accident> accidents = new ArrayList<>();
}
