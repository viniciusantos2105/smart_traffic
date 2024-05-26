package com.smart_traffic.config;


import com.smart_traffic.dtos.accident.request.AccidentRequest;
import com.smart_traffic.dtos.route.response.RouteResponse;
import com.smart_traffic.dtos.trafficPattern.request.TrafficPatternRequest;
import com.smart_traffic.entities.Accident;
import com.smart_traffic.entities.TrafficPattern;
import com.smart_traffic.models.RouteModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        mapper.createTypeMap(AccidentRequest.class, Accident.class)
                .addMappings(mapping -> mapping.using(toLocalTime).map(AccidentRequest::getAccidentTime, Accident::setAccidentTime))
                .addMappings(mapping -> mapping.skip(Accident::setAccidentId)) ;
        mapper.createTypeMap(TrafficPatternRequest.class, TrafficPattern.class)
                .addMappings(mapping -> mapping.using(toLocalTime).map(TrafficPatternRequest::getTrafficPatternTime, TrafficPattern::setTrafficPatternTime));
       mapper.map(RouteModel.class, RouteResponse.class);

        return mapper;
    }

    Converter<String, LocalTime> toLocalTime = context -> {
        try {
            return LocalTime.parse(context.getSource(), DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Hora inválida");
        }
    };

    private final Converter<Date, String> fromDate = context -> new SimpleDateFormat("dd/MM/yyyy").format(context.getSource());
}
