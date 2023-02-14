package com.abbkit.tmp.weather.test;

import cn.hutool.json.JSONUtil;
import com.abbkit.tmp.weather.controller.WeatherController;
import com.abbkit.tmp.weather.op.WeatherObservable;
import com.abbkit.tmp.weather.po.WeatherEntity;
import com.abbkit.tmp.weather.service.WeatherService;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@DisplayName("Controller Service")
public class WeatherControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    //被测类本身使用@InjectMocks注解
    private WeatherController controller;

    @Mock
    //要mock被测类中依赖的对象使用@Mock注解
    private WeatherService weatherService;

    @Mock
    private WeatherObservable weatherObservable;

    @BeforeEach()
    public void setup() {
        MockitoAnnotations.openMocks(this);
        //初始化MockMvc对象,将KeywordController加载进Spring容器
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @DisplayName("Insert Service")
    @Test
    public void testInsert()throws Exception{
        WeatherEntity weather=rec();
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/weather/insert")
                                .contentType(MediaType.APPLICATION_JSON)//发送的文本格式
                                .content(JSONUtil.toJsonStr(weather))
                                .accept(MediaType.APPLICATION_JSON)//接受的文本格式
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertTrue(status==200);
    }


    @DisplayName("Report Service")
    @Test
    public void testReport()throws Exception{

        Mockito.when(weatherService.reportMin()).thenReturn(Arrays.asList(rec()));
        List<WeatherEntity> weatherEntityList=new ArrayList<>();
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/weather/reportMin")
                                .contentType(MediaType.TEXT_PLAIN)//发送的文本格式
                                .accept(MediaType.APPLICATION_JSON)//接受的文本格式
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(new ResultHandler() {
                    @Override
                    public void handle(MvcResult result) throws Exception {
                        String content = result.getResponse().getContentAsString();
                        List<WeatherEntity> entities = JSONUtil.toList(content, WeatherEntity.class);
                        weatherEntityList.addAll(entities);
                    }
                }).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertTrue(status==200);
        Assertions.assertTrue(weatherEntityList.size()==1);
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
