package com.meteo.station.configuration;

import lombok.Setter;
import okhttp3.OkHttpClient;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.influxdb")
@Setter
public class InfluxDBConfiguration
{
    private String url;
    private String database;
    private String username;
    private String password;
    private String retentionPolicy;
    private long connectionTimeout;
    private long readTimeout;
    private long writeTimeout;

//    @Bean(name = "influxDB")
//    public InfluxDB influxDbDatasource() {
//        return InfluxDBFactory.connect(url, username, password, okHttpClientBuilder())
//                    .setDatabase(database)
//                    .setLogLevel(InfluxDB.LogLevel.BASIC)
//                    .setRetentionPolicy(retentionPolicy);
//    }
//
//    @Bean
//    public InfluxDBResultMapper resultMapper() {
//        return new InfluxDBResultMapper();
//    }
//
//    private OkHttpClient.Builder okHttpClientBuilder() {
//        return new OkHttpClient().newBuilder()
//                .connectTimeout(connectionTimeout, TimeUnit.SECONDS)
//                .readTimeout(readTimeout, TimeUnit.SECONDS)
//                .writeTimeout(writeTimeout, TimeUnit.SECONDS);
//    }


//    @Bean
//    public InfluxDBConnectionFactory connectionFactory(final InfluxDBProperties properties)
//    {
//
//
//        return new InfluxDBConnectionFactory(properties);
//    }
//
//    @Bean
//    public InfluxDBTemplate<Point> influxDBTemplate(final InfluxDBConnectionFactory connectionFactory)
//    {
//        /*
//         * You can use your own 'PointCollectionConverter' implementation, e.g. in case
//         * you want to use your own custom measurement object.
//         */
//        return new InfluxDBTemplate<>(connectionFactory, new PointConverter());
//    }
//
//    @Bean
//    public DefaultInfluxDBTemplate defaultTemplate(final InfluxDBConnectionFactory connectionFactory)
//    {
//        /*
//         * If you are just dealing with Point objects from 'influxdb-java' you could
//         * also use an instance of class DefaultInfluxDBTemplate.
//         */
//        return new DefaultInfluxDBTemplate(connectionFactory);
//    }
}