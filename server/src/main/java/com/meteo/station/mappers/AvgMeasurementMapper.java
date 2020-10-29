package com.meteo.station.mappers;

import com.meteo.station.domain.dto.AvgMeasurementDTO;
import com.meteo.station.domain.models.AvgMeasurement;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface AvgMeasurementMapper extends MappingUtils {

    @Mapping(source = "avgTemperature", target = "avgTemperature", qualifiedByName = "BigDecimalToString")
    @Mapping(source = "avgPressure", target = "avgPressure", qualifiedByName = "BigDecimalToString")
    @Mapping(source = "avgHumidity", target = "avgHumidity", qualifiedByName = "BigDecimalToString")
    @Mapping(source = "time", target = "date", qualifiedByName = "InstantToStringShort")
    AvgMeasurementDTO toDTO(AvgMeasurement avgMeasurement);

}
