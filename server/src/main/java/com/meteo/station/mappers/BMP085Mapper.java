package com.meteo.station.mappers;

import com.meteo.station.domain.dto.BMP085DTO;
import com.meteo.station.domain.models.BMP085Measurement;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface BMP085Mapper extends MappingUtils {

    @Mapping(source = "temperature", target = "temperature", qualifiedByName = "BigDecimalToString")
    @Mapping(source = "pressure", target = "pressure", qualifiedByName = "BigDecimalToString")
    @Mapping(source = "altitude", target = "altitude", qualifiedByName = "BigDecimalToString")
    @Mapping(source = "seaLevelPressure", target = "seaLevelPressure", qualifiedByName = "BigDecimalToString")
    @Mapping(source = "time", target = "time", qualifiedByName = "InstantToString")
    BMP085DTO toDTO(BMP085Measurement bmp085Measurement);

    @Mapping(source = "temperature", target = "temperature", qualifiedByName = "StringToBigDecimal")
    @Mapping(source = "pressure", target = "pressure", qualifiedByName = "StringToBigDecimal")
    @Mapping(source = "altitude", target = "altitude", qualifiedByName = "StringToBigDecimal")
    @Mapping(source = "seaLevelPressure", target = "seaLevelPressure", qualifiedByName = "StringToBigDecimal")
    BMP085Measurement toMeasurement(BMP085DTO bmp085DTO);

}
