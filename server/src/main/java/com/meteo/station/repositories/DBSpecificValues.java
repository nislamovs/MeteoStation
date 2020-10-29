package com.meteo.station.repositories;


public abstract class DBSpecificValues {

    public static final String DB_NAME = "meteo";
    public static final String GET_LAST_BMP085_MEASUREMENT_QUERY = "Select * from BMP085 where deviceId = '%s' order by time desc limit 1";
    public static final String GET_LAST_DHT11_MEASUREMENT_QUERY = "Select * from DHT11 where deviceId = '%s' order by time desc limit 1";

    public static final String GET_AVG_HUMIDITY_BY_DATE_AND_DEVICEID_QUERY = "select mean(humidity) as avgHumidity from DHT11 where time >= '%s 00:00:00' and time < '%s 00:00:00' and deviceId = '%s'";
    public static final String GET_AVG_TEMPERATURE_BY_DATE_AND_DEVICEID_QUERY = "select mean(temperature) as avgTemperature from BMP085 where time >= '%s 00:00:00' and time < '%s 00:00:00' and deviceId = '%s'";
    public static final String GET_AVG_PRESSURE_BY_DATE_AND_DEVICEID_QUERY = "select mean(pressure) as avgPressure from BMP085 where time >= '%s 00:00:00' and time < '%s 00:00:00' and deviceId = '%s'";

}
