package com.meteo.station.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Measurement(name = "DHT11", database = "meteo")
public class DHT11Measurement {

    @Column(name = "deviceId")
    private String deviceId;

    @Column(name = "humidity")
    private String humidity;

    @Column(name = "temperature")
    private String temperature;
}
