package com.meteo.station.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Measurement(name = "DHT11", database = "meteo")
public class DHT11Measurement {

    @Column(name = "deviceId", tag = true)
    private String deviceId;

    @Column(name = "humidity")
    private BigDecimal humidity;

    @Column(name = "temperature")
    private BigDecimal temperature;

    @TimeColumn
    private Instant time;
}
