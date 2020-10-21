package com.meteo.station.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Measurement(name = "BMP085")
public class BMP085Measurement {

    @Column(name = "deviceId")
    private String deviceId;

    @Column(name = "temperature")
    private String temperature;

    @Column(name = "pressure")
    private String pressure;

    @Column(name = "altitude")
    private String altitude;

    @Column(name = "seaLevelPressure")
    private String seaLevelPressure;

}
