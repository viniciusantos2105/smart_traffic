package com.smart_traffic.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "route")
@NoArgsConstructor
@AllArgsConstructor
public class RouteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;
    private String routeStartLocation;
    private String routeEndLocation;
    private double routeDistance;

    @OneToMany(mappedBy="route")
    private List<TrafficPatternModel> trafficPatterns = new ArrayList<>();

    @OneToMany(mappedBy="route")
    private List<AccidentModel> accidents = new ArrayList<>();

}
