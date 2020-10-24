package com.meteo.station.repositories;

import com.meteo.station.domain.models.BMP085Measurement;
import lombok.RequiredArgsConstructor;
import org.influxdb.dto.Point;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class BMP085Repository {

    private final InfluxDBTemplate<Point> influxDBTemplate;

    public void writeData(final BMP085Measurement measurement) {
        final Point point = Point.measurementByPOJO(BMP085Measurement.class)
               .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
               .addFieldsFromPOJO(measurement)
               .build();

        influxDBTemplate.write(point);
    }
}
