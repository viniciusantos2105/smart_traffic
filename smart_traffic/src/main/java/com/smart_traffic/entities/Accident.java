package com.smart_traffic.entities;

import com.smart_traffic.enums.AccidentSeverity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accident {


    private Long accidentId;
    private String accidentLocation;
    private LocalTime accidentTime;
    private AccidentSeverity accidentSeverity;
    private int accidentInvolvedVehicles;
    private Route route;
}
