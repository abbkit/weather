package com.abbkit.tmp.weather.service;

import com.abbkit.tmp.weather.po.WeatherEntity;

import java.util.List;

public interface WeatherService {

    /**
     * insert the weather information
     * @param weatherEntity
     */
    void insert(WeatherEntity weatherEntity);

    /**
     * get the latest weather information in each minute.
     * @return
     */
    List<WeatherEntity> recListPerMin();

}
