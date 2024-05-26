package com.smart_traffic.dtos.accident.response;

import com.smart_traffic.dtos.route.response.RouteResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccidentResponseRoute {

    private Long accidentId;
    private String accidentLocation;
    private LocalTime accidentTime;
    private String accidentSeverity;
    private int accidentInvolvedVehicles;
    private List<RouteResponse> suggestedRoutes;
}
