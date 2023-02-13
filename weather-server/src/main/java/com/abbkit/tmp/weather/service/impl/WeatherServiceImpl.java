package com.abbkit.tmp.weather.service.impl;

import cn.hutool.json.JSONUtil;
import com.abbkit.tmp.weather.mapper.WeatherMapper;
import com.abbkit.tmp.weather.po.WeatherEntity;
import com.abbkit.tmp.weather.service.WeatherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class WeatherServiceImpl implements WeatherService {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private WeatherMapper weatherMapper;

    @Override
    public void insert(WeatherEntity weatherEntity) {
        weatherMapper.insert(weatherEntity);
    }

    @Override
    public List<WeatherEntity> recListPerMin() {

        String key = "report";
        String report = stringRedisTemplate.opsForValue().get(key);
        if(StringUtils.isEmpty(report)){
            synchronized (this){
                //monitor SINGLETON OBJECT
                report = stringRedisTemplate.opsForValue().get(key);
                if(StringUtils.isNotEmpty(report)) {
                    return JSONUtil.toList(report, WeatherEntity.class);
                }
                List<WeatherEntity> entityList = weatherMapper.recPerMin();
                String jsonStr = JSONUtil.toJsonStr(entityList);
                stringRedisTemplate.opsForValue().set(key,jsonStr,60, TimeUnit.MINUTES);
                return entityList;
            }

        }
        else {
            return JSONUtil.toList(report, WeatherEntity.class);
        }
    }


}
