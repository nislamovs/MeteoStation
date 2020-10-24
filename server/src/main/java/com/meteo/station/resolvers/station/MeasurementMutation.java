package com.meteo.station.resolvers.station;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.meteo.station.domain.dto.BMP085DTO;
import com.meteo.station.domain.dto.DHT11DTO;
import com.meteo.station.services.MeasurementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MeasurementMutation implements GraphQLMutationResolver {

    private final MeasurementService measurementService;

    public String addMeasurement(String deviceId, DHT11DTO dht11dto, BMP085DTO bmp085dto) {

        measurementService.addMeasurement(deviceId, dht11dto, bmp085dto);
        System.out.println(">>>>    " +  deviceId + " " +  dht11dto.toString() + " " + bmp085dto.toString());

        return "Mut_OK";
    }
}
