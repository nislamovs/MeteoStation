package com.meteo.station.resolvers.station;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.meteo.station.domain.dto.BMP085DTO;
import com.meteo.station.domain.dto.DHT11DTO;
import com.meteo.station.domain.models.BMP085Measurement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class MeasurementQuery implements GraphQLQueryResolver {

//    private final MeasurementService measurementService;

    public String getBMP085MeasurementsByDeviceId(String deviceId) {
//        return Arrays.asList(BMP085DTO.builder()
//                .altitude("asd")
//                .build());
        return "asda23423sd23";
    }

    public String getDHT11MeasurementsByDeviceId(String deviceId) {
//        return Arrays.asList(DHT11DTO.builder()
//                .temperature("asd")
//                .build());
            return "asdasd23";
    }
}
