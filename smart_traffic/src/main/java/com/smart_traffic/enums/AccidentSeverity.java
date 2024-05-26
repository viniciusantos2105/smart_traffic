package com.smart_traffic.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum AccidentSeverity {

    HIGH ("Alta"),
    MEDIUM ("Medio"),
    LOW ("Baixa");


    private String description;
}
