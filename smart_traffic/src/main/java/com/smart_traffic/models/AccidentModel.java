package com.smart_traffic.models;

import com.smart_traffic.enums.AccidentSeverity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "accident")
@NoArgsConstructor
@AllArgsConstructor
public class AccidentModel {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long accidentId;
        private String accidentLocation;
        private LocalTime accidentTime;
        private AccidentSeverity accidentSeverity;
        private int accidentInvolvedVehicles;

        @ManyToOne
        @JoinColumn(name="route_id", nullable=false)
        private RouteModel route;
}
