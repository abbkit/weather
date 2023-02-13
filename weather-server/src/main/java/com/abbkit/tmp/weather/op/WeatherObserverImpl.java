package com.abbkit.tmp.weather.op;

import com.abbkit.tmp.weather.po.WeatherEntity;
import com.abbkit.tmp.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Observable;

@Component
public class WeatherObserverImpl implements WeatherObserver{

    @Autowired
    private WeatherService weatherService;

    @Override
    public void update(Observable o, Object arg) {

        if(o instanceof WeatherObservable){
            if (arg instanceof WeatherEntity) {
                weatherService.insert(((WeatherEntity) arg));
            }
        }

    }


}
