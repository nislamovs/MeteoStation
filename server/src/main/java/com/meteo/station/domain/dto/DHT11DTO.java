package com.meteo.station.domain.dto;

import lombok.*;


@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DHT11DTO {

    private String humidity;
    private String temperature;
}

