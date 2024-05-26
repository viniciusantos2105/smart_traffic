package com.smart_traffic.models;

import com.smart_traffic.enums.TrafficPatternVolume;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "traffic_pattern")
@NoArgsConstructor
@AllArgsConstructor
public class TrafficPatternModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trafficPatternId;
    private String trafficPatternLocation;
    private LocalTime trafficPatternTime;
    private TrafficPatternVolume trafficPatternVolume;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name="route_id", nullable=false)
    private RouteModel route;
}
