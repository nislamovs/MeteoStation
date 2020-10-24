package com.meteo.station.mappers;

import com.meteo.station.domain.dto.DHT11DTO;
import com.meteo.station.domain.models.DHT11Measurement;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface DHT11Mapper {

//    @Mapping(source = "deviceId", target = "deviceId")
    DHT11DTO toDTO(DHT11Measurement dht11Measurement);

//    @Mapping(source = "deviceId", target = "deviceId")
    DHT11Measurement toMeasurement(DHT11DTO dht11DTO);
}