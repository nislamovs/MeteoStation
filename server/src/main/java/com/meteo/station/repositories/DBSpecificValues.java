package com.meteo.station.repositories;


public abstract class DBSpecificValues {

    public static final String DB_NAME = "meteo";
    public static final String GET_LAST_BMP085_MEASUREMENT_QUERY = "Select * from BMP085 where deviceId = '%s' order by time desc limit 1";
    public static final String GET_LAST_DHT11_MEASUREMENT_QUERY = "Select * from DHT11 where deviceId = '%s' order by time desc limit 1";
}
