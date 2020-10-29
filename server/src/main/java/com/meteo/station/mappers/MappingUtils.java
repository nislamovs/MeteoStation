package com.meteo.station.mappers;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.lang.Double.parseDouble;
import static java.time.Instant.now;

public interface MappingUtils {

    @Named("StringToBigDecimal")
    default BigDecimal extractBigDecimalFromString(String value) {

        if (value.equalsIgnoreCase("nan"))
             return BigDecimal.ZERO;

        if (StringUtils.isNotEmpty(value) && value.contains("test"))
            return BigDecimal.ZERO;

        String numericVal = String.format("%.2f", parseDouble(value.split(" ")[0]));
        return new BigDecimal(numericVal);
    }

    @Named("BigDecimalToString")
    default String extractStringFromBigDecimal(BigDecimal value) {

        return (value == null) ? "N/A" : String.format("%.2f", value);
    }

    @Named("InstantToString")
    default String extractStringFromInstant(Instant value) {
        return (value == null)
                ?   "N/A"
                :   DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(ZoneId.systemDefault()).format(value);
    }

    @Named("InstantToStringShort")
    default String extractStringFromInstantShort(Instant value) {
        return (value == null)
                ?   "N/A"
                :   DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault()).format(value);
    }

    @Named("StringToInstant")
    default Instant extractInstantFromString(String value) {
        return now();
    }
}
