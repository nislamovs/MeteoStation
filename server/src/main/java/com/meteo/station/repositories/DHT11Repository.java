package com.meteo.station.repositories;

import com.meteo.station.domain.models.BMP085Measurement;
import com.meteo.station.domain.models.DHT11Measurement;
import com.meteo.station.mappers.InfluxDBCustomResultMapper;
import lombok.RequiredArgsConstructor;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class DHT11Repository extends DBSpecificValues {

    private final InfluxDBTemplate<Point> influxDBTemplate;
    private final InfluxDBCustomResultMapper resultMapper = new InfluxDBCustomResultMapper();

    public void writeData(final DHT11Measurement measurement) {
        final Point point = Point.measurementByPOJO(DHT11Measurement.class)
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addFieldsFromPOJO(measurement)
                .build();

        influxDBTemplate.write(point);
    }

    public DHT11Measurement getLastMeasurementByDeviceId(String deviceId) {
        String queryString = String.format(GET_LAST_DHT11_MEASUREMENT_QUERY, deviceId);

        QueryResult queryResult = influxDBTemplate.query(new Query(queryString, DB_NAME));

        return resultMapper.toPOJO(queryResult, DHT11Measurement.class).get(0);
    }

}
