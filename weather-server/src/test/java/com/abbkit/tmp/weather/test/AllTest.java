package com.abbkit.tmp.weather.test;


import org.junit.jupiter.api.DisplayName;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@DisplayName("All test")
@Suite
@SelectClasses({
        WeatherServiceTest.class
        , WeatherControllerTest.class
})
public class AllTest {
}
