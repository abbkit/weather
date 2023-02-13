package com.abbkit.tmp.weather.op;

import com.abbkit.tmp.weather.po.WeatherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Observable;


@Component
public class WeatherObservable extends Observable {


    @Autowired
    public void observer(List<WeatherObserver> weatherObserverList){
        for (WeatherObserver weatherObserver : weatherObserverList) {
            addObserver(weatherObserver);
        }
    }

    public void stream(WeatherEntity weatherEntity){
        setChanged();
        notifyObservers(weatherEntity);
    }

}
