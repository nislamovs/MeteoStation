package com.meteo.station.services;

import com.meteo.station.domain.dto.AvgMeasurementDTO;
import com.meteo.station.domain.dto.BMP085DTO;
import com.meteo.station.domain.dto.DHT11DTO;
import com.meteo.station.domain.dto.FullMeasurementDTO;
import com.meteo.station.domain.models.BMP085Measurement;
import com.meteo.station.domain.models.DHT11Measurement;
import com.meteo.station.mappers.AvgMeasurementMapper;
import com.meteo.station.mappers.BMP085Mapper;
import com.meteo.station.mappers.DHT11Mapper;
import com.meteo.station.repositories.BMP085Repository;
import com.meteo.station.repositories.DHT11Repository;
import com.meteo.station.repositories.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final StatisticsRepository statisticsRepository;
    private final BMP085Repository bmp085Repository;
    private final DHT11Repository dht11Repository;
    private final BMP085Mapper bmp085Mapper;
    private final DHT11Mapper dht11Mapper;
    private final AvgMeasurementMapper avgMapper;

////////////

    public void addMeasurement(String deviceId, DHT11DTO dht11dto, BMP085DTO bmp085dto) {

        bmp085Repository.writeData(bmp085Mapper.toMeasurement(bmp085dto).toBuilder().deviceId(deviceId).build());
        dht11Repository.writeData(dht11Mapper.toMeasurement(dht11dto).toBuilder().deviceId(deviceId).build());
    }

    public FullMeasurementDTO getLastFullMeasurementByDeviceId(String deviceId) {

        BMP085Measurement bmp085Measurement = bmp085Repository.getLastMeasurementByDeviceId(deviceId);
        DHT11Measurement dht11Measurement = dht11Repository.getLastMeasurementByDeviceId(deviceId);

        return FullMeasurementDTO.builder()
                .bmp085dto(bmp085Mapper.toDTO(bmp085Measurement))
                .dht11dto(dht11Mapper.toDTO(dht11Measurement))
                .deviceId(bmp085Measurement.getDeviceId())
                .build();
    }

    public DHT11DTO getLastDHT11MeasurementByDeviceId(String deviceId) {
        return dht11Mapper.toDTO(dht11Repository.getLastMeasurementByDeviceId(deviceId));
    }

    public BMP085DTO getLastBMP085MeasurementByDeviceId(String deviceId) {
        return bmp085Mapper.toDTO(bmp085Repository.getLastMeasurementByDeviceId(deviceId));
    }

    public AvgMeasurementDTO getAvgHumidityByDeviceIdAndDate(String deviceId, String date) {
        return avgMapper.toDTO(statisticsRepository.getAvgHumidityByDateAndDeviceId(deviceId, date));
    }

    public AvgMeasurementDTO getAvgTemperatureByDeviceIdAndDate(String deviceId, String date) {
        return avgMapper.toDTO(statisticsRepository.getAvgTemperatureByDateAndDeviceId(deviceId, date));
    }

    public AvgMeasurementDTO getAvgPressureByDeviceIdAndDate(String deviceId, String date) {
        return avgMapper.toDTO(statisticsRepository.getAvgPressureByDateAndDeviceId(deviceId, date));
    }

    public AvgMeasurementDTO getAvgFullMeasurementByDeviceIdAndDate(String deviceId, String date) {
        return avgMapper.toDTO(statisticsRepository.getAvgFullMeasurementByDateAndDeviceId(deviceId, date));
    }
}
