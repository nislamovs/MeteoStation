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
@Measurement(name = "BMP085", database = "meteo")
public class BMP085Measurement {

    @Column(name = "deviceId", tag = true)
    private String deviceId;

    @Column(name = "temperature")
    private BigDecimal temperature;

    @Column(name = "pressure")
    private BigDecimal pressure;

    @Column(name = "altitude")
    private BigDecimal altitude;

    @Column(name = "seaLevelPressure")
    private BigDecimal seaLevelPressure;

    @TimeColumn
    private Instant time;
}
