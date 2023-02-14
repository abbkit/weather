package com.abbkit.tmp.weather.controller;

import com.abbkit.tmp.weather.op.WeatherObservable;
import com.abbkit.tmp.weather.po.WeatherEntity;
import com.abbkit.tmp.weather.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/weather")
public class WeatherController {


    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherObservable weatherObservable;

    /**
     * 接受气象信息并存储
     * @param weatherVO
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(path = "insert")
    public boolean insert(@RequestBody WeatherEntity weatherVO) throws Exception {
        weatherObservable.stream(weatherVO);
        return true;
    }

    /**
     * 返回每分钟的气象预报
     * @return
     * @throws Exception
     */
    @ResponseBody
    @GetMapping(path = "reportMin")
    public List<WeatherEntity> reportMin() throws Exception {
        return weatherService.reportMin();
    }

}
