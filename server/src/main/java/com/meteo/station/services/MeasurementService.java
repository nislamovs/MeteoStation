package com.meteo.station.services;

import com.meteo.station.domain.dto.BMP085DTO;
import com.meteo.station.domain.dto.DHT11DTO;
import com.meteo.station.domain.dto.FullMeasurementDTO;
import com.meteo.station.domain.models.BMP085Measurement;
import com.meteo.station.domain.models.DHT11Measurement;
import com.meteo.station.mappers.BMP085Mapper;
import com.meteo.station.mappers.DHT11Mapper;
import com.meteo.station.mappers.InfluxDBCustomResultMapper;
import com.meteo.station.repositories.BMP085Repository;
import com.meteo.station.repositories.DHT11Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final BMP085Repository bmp085Repository;
    private final DHT11Repository dht11Repository;
    private final BMP085Mapper bmp085Mapper;
    private final DHT11Mapper dht11Mapper;

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
        return new DHT11DTO();
//        bmp085Repository.writeData(bmp085Mapper.toMeasurement(bmp085dto).toBuilder().deviceId(deviceId).build());
//        dht11Repository.writeData(dht11Mapper.toMeasurement(dht11dto).toBuilder().deviceId(deviceId).build());
    }

    public BMP085DTO getLastBMP085MeasurementByDeviceId(String deviceId) {
        return new BMP085DTO();
        //bmp085Repository.readDataByDeviceId(deviceId);
//        bmp085Repository.writeData(bmp085Mapper.toMeasurement(bmp085dto).toBuilder().deviceId(deviceId).build());
//        dht11Repository.writeData(dht11Mapper.toMeasurement(dht11dto).toBuilder().deviceId(deviceId).build());
    }

//    public FullMeasurementDTO getLastFullMeasurementByDeviceId(String deviceId) {
////        QueryResult queryResult = influxDBTemplate.query(new Query("Select * from BMP085 order by time desc limit 5", "meteo"));
////        QueryResult queryResult2 = influxDBTemplate.query(new Query("Select * from DHT11 order by time desc limit 5", "meteo"));
////
////        List<BMP085Measurement> bmp085Measurements = resultMapper.toPOJO(queryResult, BMP085Measurement.class);
////        List<DHT11Measurement> dht11Measurements = resultMapper.toPOJO(queryResult2, DHT11Measurement.class);
//
//        BMP085Measurement bmp085Measurements = bmp085Repository.getLastMeasurementByDeviceId(deviceId);
//        DHT11Measurement dht11Measurements = dht11Repository.getLastMeasurementByDeviceId(deviceId);
//
//
//        ListIterator<BMP085DTO> bmp085DTOs = bmp085Measurements.stream()
//                .map(bmp085Mapper::toDTO)
//                .sorted(Comparator.comparing(BMP085DTO::getTime))
//                .collect(Collectors.toList()).listIterator();
//        ListIterator<DHT11DTO> dht11DTOs = dht11Measurements.stream()
//                .map(dht11Mapper::toDTO)
//                .sorted(Comparator.comparing(DHT11DTO::getTime))
//                .collect(Collectors.toList()).listIterator();
//
//        List<FullMeasurementDTO> ff = new ArrayList<>();
//
//        while(bmp085DTOs.hasNext() && dht11DTOs.hasNext()) {
//            DHT11DTO dht11DTO = dht11DTOs.next();
//            BMP085DTO bmp085DTO = bmp085DTOs.next();
//
//            ff.add(FullMeasurementDTO.builder()
//                    .bmp085dto(bmp085DTO)
//                    .dht11dto(dht11DTO)
//                    .deviceId(dht11DTO.getDeviceId())
//                    .build()
//            );
//        }
////        getBmp085dto()
////ff.get(0).setDht11dto();
//        return ff;
//
////        ff.stream().map(fm -> fm.setBmp085dto(results2.ge))
////        return results2.stream().map(bmp085Mapper::toDTO).collect(toList());
////        bmp085Repository.writeData(bmp085Mapper.toMeasurement(bmp085dto).toBuilder().deviceId(deviceId).build());
////        dht11Repository.writeData(dht11Mapper.toMeasurement(dht11dto).toBuilder().deviceId(deviceId).build());
//    }
//
//    public DHT11DTO getLastDHT11MeasurementByDeviceId(String deviceId) {
//        return new DHT11DTO();
////        bmp085Repository.writeData(bmp085Mapper.toMeasurement(bmp085dto).toBuilder().deviceId(deviceId).build());
////        dht11Repository.writeData(dht11Mapper.toMeasurement(dht11dto).toBuilder().deviceId(deviceId).build());
//    }
//
//    public BMP085DTO getLastBMP085MeasurementByDeviceId(String deviceId) {
//        return new BMP085DTO();
//    //bmp085Repository.readDataByDeviceId(deviceId);
////        bmp085Repository.writeData(bmp085Mapper.toMeasurement(bmp085dto).toBuilder().deviceId(deviceId).build());
////        dht11Repository.writeData(dht11Mapper.toMeasurement(dht11dto).toBuilder().deviceId(deviceId).build());
//    }

    public String getAvgHumidityByDeviceIdAndDate(String deviceId, String date) {
        return "";
    }

    public String getAvgTemperatureByDeviceIdAndDate(String deviceId, String date) {
        return "";
    }

    public String getAvgPressureByDeviceIdAndDate(String deviceId, String date) {
        return "";
    }

    public String getAvgFullMeasurementByDeviceIdAndDate(String deviceId, String date) {
        return "";
    }
}
