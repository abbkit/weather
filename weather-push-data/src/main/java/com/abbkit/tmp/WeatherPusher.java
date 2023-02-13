package com.abbkit.tmp;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WeatherPusher {


    public void run() throws Exception{
        ScheduledExecutorService service= Executors.newScheduledThreadPool(1);

        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    WeatherEntity weather=rec();
                    String post = HttpUtil.post("http://127.0.0.1:8080/weather/insert", JSONUtil.toJsonStr(weather));
                    log.info("call result: "+post);
                }catch (Exception e){
                    log.error(e.getMessage(),e);
                }

            }
        },1,10, TimeUnit.SECONDS);

    }


    public static void main(String[] args)throws Exception {
        new WeatherPusher().run();
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