
## db schema

```
CREATE TABLE `t_weather` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `temperature` int DEFAULT NULL COMMENT '温度',
  `humidity` int DEFAULT NULL COMMENT '湿度',
  `pressure` float DEFAULT NULL COMMENT '气压',
  `rec_time` datetime DEFAULT NULL COMMENT '记录时间',
  `station` varchar(255) DEFAULT NULL COMMENT '站点',
  PRIMARY KEY (`id`),
  KEY `I_TIME` (`rec_time`) COMMENT '记录时间的索引'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='天气信息记录表';

```
1.  a no-unique index on rec_time

## weather-server
1. GET  :  **/weather/insert** , receive weather information from a station
2. POST : **/weather/reportMin** , report weather information in each minute

## weather-push-data
1. generate some weather information randomly, then use http channel to push to the server
    - schedule a task using a java internal scheduled service 'java.util.concurrent.ScheduledExecutorService'

## design pattern
1. observe pattern 
   - WeatherObserverImpl as a observer that observes any change on the weather observable
     - WeatherObservable as a observable , registers all observers that implements 'com.abbkit.tmp.weather.op.WeatherObserver' interface
   
        ```
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
          ```

## env
1. mysql
2. redis