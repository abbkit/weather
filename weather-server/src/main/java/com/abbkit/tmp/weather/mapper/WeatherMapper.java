package com.abbkit.tmp.weather.mapper;


import com.abbkit.tmp.weather.po.WeatherEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


@Mapper
public interface WeatherMapper {

    @Delete("delete from t_weather ")
    void deleteAll();

    @Insert("INSERT INTO `t_weather` (`id`, `temperature`, `humidity`, `pressure`, `rec_time`,`station`) VALUES (null, #{temperature}, #{humidity}, #{pressure}, #{recTime},#{station})")
    void insert(WeatherEntity weather);

    @Select("select * from `t_weather` \n" +
            "where id in (\n" +
            "SELECT min(id) FROM `t_weather`\n" +
            "group by DATE_FORMAT(rec_time,'%Y-%m-%d %H:%i:00')  \n" +
            ")")
    @Results(
            @Result(column = "rec_time", property = "recTime",jdbcType= JdbcType.TIMESTAMP )

    )
    List<WeatherEntity> recPerMin();

}
