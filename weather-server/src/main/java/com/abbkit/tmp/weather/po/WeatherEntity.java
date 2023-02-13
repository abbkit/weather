package com.abbkit.tmp.weather.po;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private int pressure;


    /**
     * 记录时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:00")
    private Date recTime;


    private String station;


}
