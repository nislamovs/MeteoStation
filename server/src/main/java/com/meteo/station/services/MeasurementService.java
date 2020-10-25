package com.meteo.station.services;

import com.meteo.station.domain.dto.BMP085DTO;
import com.meteo.station.domain.dto.DHT11DTO;
import com.meteo.station.domain.models.BMP085Measurement;
import com.meteo.station.mappers.BMP085Mapper;
import com.meteo.station.mappers.DHT11Mapper;
import com.meteo.station.repositories.BMP085Repository;
import com.meteo.station.repositories.DHT11Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final BMP085Repository bmp085Repository;
    private final DHT11Repository dht11Repository;
    private final BMP085Mapper bmp085Mapper;
    private final DHT11Mapper dht11Mapper;


    public void addMeasurement(String deviceId, DHT11DTO dht11dto, BMP085DTO bmp085dto) {

        bmp085Repository.writeData(bmp085Mapper.toMeasurement(bmp085dto).toBuilder().deviceId(deviceId).build());
        dht11Repository.writeData(dht11Mapper.toMeasurement(dht11dto).toBuilder().deviceId(deviceId).build());
    }

}
