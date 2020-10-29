package com.meteo.station.repositories;

import com.meteo.station.domain.models.BMP085Measurement;
import com.meteo.station.mappers.InfluxDBCustomResultMapper;
import lombok.RequiredArgsConstructor;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class BMP085Repository extends DBSpecificValues {

    private final InfluxDBTemplate<Point> influxDBTemplate;
    private final InfluxDBCustomResultMapper resultMapper = new InfluxDBCustomResultMapper();

    public void writeData(final BMP085Measurement measurement) {
        final Point point = Point.measurementByPOJO(BMP085Measurement.class)
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addFieldsFromPOJO(measurement)
                .build();

        influxDBTemplate.write(point);
    }

    public BMP085Measurement getLastMeasurementByDeviceId(String deviceId) {
        String queryString = String.format(GET_LAST_BMP085_MEASUREMENT_QUERY, deviceId);

        QueryResult queryResult = influxDBTemplate.query(new Query(queryString, DB_NAME));

        return resultMapper.toPOJO(queryResult, BMP085Measurement.class).get(0);
    }

//    public void readAllDataByDeviceId(final BMP085Measurement measurement) {
//        final Point point = Point.measurementByPOJO(BMP085Measurement.class)
//                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
//                .addFieldsFromPOJO(measurement)
//                .build();
//
//        influxDBTemplate.query(point);
//    }
//
//    public void readAllDataByDeviceId(final BMP085Measurement measurement) {
//        final Point point = Point.measurementByPOJO(BMP085Measurement.class)
//                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
//                .addFieldsFromPOJO(measurement)
//                .build();
//
//        influxDBTemplate.query(point);
//    }
//
//    public void readAllDataByDeviceId(final BMP085Measurement measurement) {
//        final Point point = Point.measurementByPOJO(BMP085Measurement.class)
//                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
//                .addFieldsFromPOJO(measurement)
//                .build();
//
//        influxDBTemplate.query(point);
//    }
}
