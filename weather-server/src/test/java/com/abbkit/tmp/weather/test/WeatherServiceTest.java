package com.abbkit.tmp.weather.test;

import com.abbkit.tmp.weather.po.WeatherEntity;
import com.abbkit.tmp.weather.service.WeatherService;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DisplayName("Weather Service")
@SpringBootTest
public class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;


    @DisplayName("Insert Service")
    @Test
    public void testInsert()throws Exception{
        WeatherEntity weather = rec();
        weatherService.insert(weather);
        Assertions.assertTrue(true);
    }

    @DisplayName("Report Service")
    @Test
    public void testReport()throws Exception{
        List<WeatherEntity> entities = weatherService.reportMin();
        Assertions.assertTrue(entities!=null && entities.size()>0);
    }

    WeatherEntity rec(){

        List<String> stationList=new ArrayList<>();
        stationList.add("S1");
        stationList.add("S2");
        stationList.add("S3");
        stationList.add("S4");
        stationList.add("S5");

        WeatherEntity weather=new WeatherEntity();
        weather.setTemperature(RandomUtils.nextInt(0,50));
        weather.setHumidity(RandomUtils.nextInt(20,90));
        weather.setPressure(RandomUtils.nextFloat(50.3f,99.9f));
        weather.setStation(stationList.get(RandomUtils.nextInt(0,stationList.size())));
        weather.setRecTime(new Date());
        return weather;

    }


}
