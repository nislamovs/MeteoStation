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
public interface BMP085Mapper {

//    @Mapping(source = "deviceId", target = "deviceId")
    BMP085DTO toDTO(BMP085Measurement bmp085Measurement);

//    @Mapping(source = "deviceId", target = "deviceId")
    BMP085Measurement toMeasurement(BMP085DTO bmp085DTO);
}
