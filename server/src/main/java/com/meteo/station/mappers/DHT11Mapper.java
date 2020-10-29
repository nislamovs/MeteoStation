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
public interface DHT11Mapper extends MappingUtils {

    @Mapping(source = "temperature", target = "temperature", qualifiedByName = "BigDecimalToString")
    @Mapping(source = "humidity", target = "humidity", qualifiedByName = "BigDecimalToString")
    @Mapping(source = "time", target = "time", qualifiedByName = "InstantToString")
    DHT11DTO toDTO(DHT11Measurement dht11Measurement);

    @Mapping(source = "temperature", target = "temperature", qualifiedByName = "StringToBigDecimal")
    @Mapping(source = "humidity", target = "humidity", qualifiedByName = "StringToBigDecimal")
    DHT11Measurement toMeasurement(DHT11DTO dht11DTO);

}
