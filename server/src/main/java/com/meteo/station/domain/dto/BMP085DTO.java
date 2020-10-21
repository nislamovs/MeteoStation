package com.meteo.station.domain.dto;

import lombok.*;


@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BMP085DTO {

    private String temperature;
    private String pressure;
    private String altitude;
    private String seaLevelPressure;

}

