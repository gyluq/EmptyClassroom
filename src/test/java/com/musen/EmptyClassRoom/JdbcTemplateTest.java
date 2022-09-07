package com.musen.EmptyClassRoom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class JdbcTemplateTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void show() {
        System.out.println(jdbcTemplate);
        //System.out.println(jdbcTemplate.queryForObject("select count(*) from class_schedule", Long.class));
    }

    public static void main(String[] args) {
        JdbcTemplateTest jdbcTemplateTest = new JdbcTemplateTest();
        jdbcTemplateTest.show();
    }
}
