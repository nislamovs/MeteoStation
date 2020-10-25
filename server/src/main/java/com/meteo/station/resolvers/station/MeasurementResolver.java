package com.meteo.station.resolvers.station;

import com.meteo.station.domain.models.BMP085Measurement;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class MeasurementResolver implements DataFetcher<List<BMP085Measurement>> {

    @Override
    public List<BMP085Measurement> get(DataFetchingEnvironment environment) {
//        val gvozdj = BMP085Measurement.builder().altitude(1000.0).pressure(1000.0).build();
//        val chugun = BMP085Measurement.builder().altitude(1000.0).pressure(1000.0).build();
//        val kirpich = BMP085Measurement.builder().altitude(1000.0).pressure(1000.0).build();

//        return asList(gvozdj, chugun, kirpich);
        return null;
    }
}
