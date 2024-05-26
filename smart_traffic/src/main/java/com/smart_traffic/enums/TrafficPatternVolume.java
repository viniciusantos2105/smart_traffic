package com.smart_traffic.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TrafficPatternVolume {

    HIGH("Alto"),
    MEDIUM("Medio"),
    LOW("Baixo");

    private String description;
}
