package com.meteo.station.repositories;

import com.meteo.station.domain.models.AvgMeasurement;
import com.meteo.station.mappers.InfluxDBCustomResultMapper;
import lombok.RequiredArgsConstructor;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Repository;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static java.time.LocalDate.parse;

@Repository
@RequiredArgsConstructor
public class StatisticsRepository extends DBSpecificValues {

    private final InfluxDBTemplate<Point> influxDBTemplate;
    private final InfluxDBCustomResultMapper resultMapper = new InfluxDBCustomResultMapper();

    public AvgMeasurement getAvgHumidityByDateAndDeviceId(String deviceId, String date) {
        String queryString = String.format(GET_AVG_HUMIDITY_BY_DATE_AND_DEVICEID_QUERY, date, nextDay(date), deviceId);

        QueryResult queryResult = influxDBTemplate.query(new Query(queryString, DB_NAME));
        Optional<AvgMeasurement> measurement = resultMapper.toPOJO(queryResult, AvgMeasurement.class, "AvgMeasurement", TimeUnit.MILLISECONDS, true)
                .stream().findFirst();

        return (measurement.isPresent())
                ?  measurement.get().toBuilder().deviceId(deviceId).build()
                :  AvgMeasurement.builder().deviceId(deviceId).time(toInstant(date)).build();
    }

    public AvgMeasurement getAvgTemperatureByDateAndDeviceId(String deviceId, String date) {
        String queryString = String.format(GET_AVG_TEMPERATURE_BY_DATE_AND_DEVICEID_QUERY, date, nextDay(date), deviceId);

        QueryResult queryResult = influxDBTemplate.query(new Query(queryString, DB_NAME));
        Optional<AvgMeasurement> measurement = resultMapper.toPOJO(queryResult, AvgMeasurement.class, "AvgMeasurement", TimeUnit.MILLISECONDS, true)
                .stream().findFirst();

        return (measurement.isPresent())
                ?  measurement.get().toBuilder().deviceId(deviceId).build()
                :  AvgMeasurement.builder().deviceId(deviceId).time(toInstant(date)).build();
    }

    public AvgMeasurement getAvgPressureByDateAndDeviceId(String deviceId, String date) {
        String queryString = String.format(GET_AVG_PRESSURE_BY_DATE_AND_DEVICEID_QUERY, date, nextDay(date), deviceId);

        QueryResult queryResult = influxDBTemplate.query(new Query(queryString, DB_NAME));
        Optional<AvgMeasurement> measurement = resultMapper.toPOJO(queryResult, AvgMeasurement.class, "AvgMeasurement", TimeUnit.MILLISECONDS, true)
                .stream().findFirst();

        return (measurement.isPresent())
                ?  measurement.get().toBuilder().deviceId(deviceId).build()
                : AvgMeasurement.builder().deviceId(deviceId).time(toInstant(date)).build();
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

    private String nextDay(String date) {
        return parse(date).plusDays(1).toString();
    }

    private Instant toInstant(String date) {
        LocalDate localDate = LocalDate.parse(date);
        LocalDateTime localDateTime = localDate.atStartOfDay();

        return localDateTime.toInstant(ZoneOffset.UTC);
    }
}
