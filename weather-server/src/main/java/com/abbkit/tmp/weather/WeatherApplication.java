package com.abbkit.tmp.weather;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootConfiguration(proxyBeanMethods = false)
@EnableAutoConfiguration
//@Import({ SomeConfiguration.class, AnotherConfiguration.class })
@EnableScheduling
@ComponentScan("com.abbkit.tmp")
@MapperScan("com.abbkit.tmp.**.mapper")
public class WeatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }

}
