package com.meteo.station.mappers;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;

import java.math.BigDecimal;
import static java.lang.Double.parseDouble;

public interface MappingUtils {

    @Named("StringToDouble")
    default BigDecimal extractDoubleFromString(String value) {

        if (StringUtils.isNotEmpty(value) && value.contains("test"))
            return BigDecimal.ZERO;

        String numericVal = String.format("%.2f", parseDouble(value.split(" ")[0]));
        return new BigDecimal(numericVal);
    }
}
