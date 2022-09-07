package com.musen.EmptyClassRoom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JdbcController {

    //自动装配
    @Autowired
    JdbcTemplate jdbcTemplate;

    //因为我们没有写实体类，所以获取数据有些麻烦
    @GetMapping("/userList")
    public List<Map<String, Object>> userList() {
        String sql = "select * from class_schedule";
        List<Map<String, Object>> list_map = jdbcTemplate.queryForList(sql);
        return list_map;
    }

    @GetMapping("/addUser")
    public String addUser() {
        String sql = "insert into user(id,name,pwd) values('4','小明','127890')";
        jdbcTemplate.update(sql);
        return "ok";
    }

    //修改用户指定的id所对应的数据信息
    //因为要修改姓名和密码，所以首先将数据封装
    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id) {
        //String sql = "update user set pwd = ‘516171’ where name = '小明'";
        String sql = "update user set name = ?,pwd = ? where id = " + id;
        //封装
        Object[] objects = new Object[2];
        objects[0] = "小红";
        objects[1] = "000000";
        jdbcTemplate.update(sql, objects);
        return "update-ok";
    }

    @GetMapping("/deleteUser")
    public String deleteUser() {
        String sql = "delete from user where id = '4'";
        jdbcTemplate.update(sql);
        return "delete-ok !";
    }
}