package com.abbkit.tmp;

import lombok.Data;

import java.util.Date;

/**
 * 天气信息记录表
 */
@Data
public class WeatherEntity {

    private long id;

    /**
     * 温度
     */
    private int temperature;

    /**
     * 湿度
     */
    private int humidity;

    /**
     * 气压
     */
    private float pressure;


    /**
     * 记录时间
     */
    private Date recTime;


    private String station;


}
