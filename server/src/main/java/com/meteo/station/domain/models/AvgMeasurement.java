package com.meteo.station.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Measurement(name = "AvgMeasurement", database = "meteo")
public class AvgMeasurement {

    @Column(name = "deviceId", tag = true)
    private String deviceId;

    @Column(name = "avgHumidity")
    private BigDecimal avgHumidity;

    @Column(name = "avgTemperature")
    private BigDecimal avgTemperature;

    @Column(name = "avgPressure")
    private BigDecimal avgPressure;

    @TimeColumn
    @Column(name = "time")
    private Instant time;
}
