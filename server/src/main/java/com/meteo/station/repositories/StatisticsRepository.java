package com.meteo.station.repositories;

import com.meteo.station.domain.models.AvgMeasurement;
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
public class StatisticsRepository extends DBSpecificValues {

    private final InfluxDBTemplate<Point> influxDBTemplate;
    private final InfluxDBCustomResultMapper resultMapper = new InfluxDBCustomResultMapper();

    public AvgMeasurement getAvgHumidityByDateAndDeviceId(String deviceId, String date) {
        String queryString = String.format(GET_AVG_HUMIDITY_BY_DATE_AND_DEVICEID_QUERY, "2020-10-29", "2020-10-30", deviceId);

        QueryResult queryResult = influxDBTemplate.query(new Query(queryString, DB_NAME));
        AvgMeasurement measurement = resultMapper.toPOJO(queryResult, AvgMeasurement.class, "AvgMeasurement", TimeUnit.MILLISECONDS, true).get(0);
        measurement.setDeviceId(deviceId);

        return measurement;
    }

    public AvgMeasurement getAvgTemperatureByDateAndDeviceId(String deviceId, String date) {
        String queryString = String.format(GET_AVG_TEMPERATURE_BY_DATE_AND_DEVICEID_QUERY, "2020-10-29", "2020-10-30", deviceId);

        QueryResult queryResult = influxDBTemplate.query(new Query(queryString, DB_NAME));
        AvgMeasurement measurement = resultMapper.toPOJO(queryResult, AvgMeasurement.class, "AvgMeasurement", TimeUnit.MILLISECONDS, true).get(0);
        measurement.setDeviceId(deviceId);

        return measurement;
    }


    public AvgMeasurement getAvgPressureByDateAndDeviceId(String deviceId, String date) {
        String queryString = String.format(GET_AVG_PRESSURE_BY_DATE_AND_DEVICEID_QUERY, "2020-10-29", "2020-10-30", deviceId);

        QueryResult queryResult = influxDBTemplate.query(new Query(queryString, DB_NAME));
        AvgMeasurement measurement = resultMapper.toPOJO(queryResult, AvgMeasurement.class, "AvgMeasurement", TimeUnit.MILLISECONDS, true).get(0);
        measurement.setDeviceId(deviceId);

        return measurement;
    }


    public AvgMeasurement getAvgFullMeasurementByDateAndDeviceId(String deviceId, String date) {

        return AvgMeasurement.builder()
                    .avgHumidity(getAvgHumidityByDateAndDeviceId(deviceId, date).getAvgHumidity())
                    .avgPressure(getAvgPressureByDateAndDeviceId(deviceId, date).getAvgPressure())
                    .avgTemperature(getAvgTemperatureByDateAndDeviceId(deviceId, date).getAvgTemperature())
                    .time(getAvgTemperatureByDateAndDeviceId(deviceId, date).getTime())
                    .deviceId(deviceId)
                .build();
    }
}
