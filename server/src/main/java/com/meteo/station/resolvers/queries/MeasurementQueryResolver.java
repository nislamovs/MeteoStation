package com.meteo.station.resolvers.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.meteo.station.domain.dto.BMP085DTO;
import com.meteo.station.domain.dto.DHT11DTO;
import com.meteo.station.domain.dto.FullMeasurementDTO;
import com.meteo.station.services.MeasurementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class MeasurementQueryResolver implements GraphQLQueryResolver {

    private final MeasurementService measurementService;

    public BMP085DTO getLastBMP085MeasurementByDeviceId(String deviceId) {
        return measurementService.getLastBMP085MeasurementByDeviceId(deviceId);
    }

    public DHT11DTO getLastDHT11MeasurementByDeviceId(String deviceId) {
        return measurementService.getLastDHT11MeasurementByDeviceId(deviceId);
    }

    public FullMeasurementDTO getLastFullMeasurementByDeviceId(String deviceId) {
        return measurementService.getLastFullMeasurementByDeviceId(deviceId);
    }



    public String getAvgHumidityByDeviceIdAndDate(String deviceId, String date) {
        return measurementService.getAvgHumidityByDeviceIdAndDate(deviceId, date);
    }

    public String getAvgTemperatureByDeviceIdAndDate(String deviceId, String date) {
        return measurementService.getAvgTemperatureByDeviceIdAndDate(deviceId, date);
    }

    public String getAvgPressureByDeviceIdAndDate(String deviceId, String date) {
        return measurementService.getAvgPressureByDeviceIdAndDate(deviceId, date);
    }

    public String getAvgFullMeasurementByDeviceIdAndDate(String deviceId, String date) {
        return measurementService.getAvgFullMeasurementByDeviceIdAndDate(deviceId, date);
    }

}
