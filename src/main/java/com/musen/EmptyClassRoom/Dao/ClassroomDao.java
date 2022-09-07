package com.musen.EmptyClassRoom.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ClassroomDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void getAllEmptyClassrooms(String time, String dayOfWeek, String currentWeek) {
        String sql = "select classroom from class_schedule where w" + dayOfWeek + "_" + time + " REGEXP '^" + currentWeek + ",|[^1]+" + currentWeek + ",'";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        System.out.println(maps);
    }
}
