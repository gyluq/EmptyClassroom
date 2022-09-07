package com.musen.EmptyClassRoom.parse;

import com.musen.EmptyClassRoom.JdbcOperation;
import com.musen.EmptyClassRoom.pojo.Classroom;
import com.musen.EmptyClassRoom.utils.MapUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * 导入html
 * @author GuYeLuo
 * @create 2022/9/3 18:05
 */
public class Main {
    public static void main(String[] args) throws Exception {
        JdbcOperation jdbcOperation = new JdbcOperation();
        ParseHtml parseHtml = new ParseHtml();
        ArrayList<Map> arrayList = parseHtml.getClassroomInf();
        for (Map<String, String> hashMapMap : arrayList) {
            Classroom classroom = MapUtils.mapToObj(hashMapMap, Classroom.class);
            jdbcOperation.insertClassroom(classroom);
        }
    }
}
