package com.meteo.station.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class TimeUtils {

    private static long getTimestamp() {
        ZoneId zone = ZoneId.systemDefault();
        Instant beginofday = LocalDate.now(zone).atStartOfDay(zone).toInstant();

        return beginofday.toEpochMilli();
    }
}
