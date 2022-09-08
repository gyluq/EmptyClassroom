package com.musen.EmptyClassRoom.Dao.Impl;

import com.musen.EmptyClassRoom.Dao.ClassroomDao;
import com.musen.EmptyClassRoom.pojo.Classroom;
import com.musen.EmptyClassRoom.utils.WeekUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ClassroomDaoImpl implements ClassroomDao {

    private final JdbcTemplate jdbcTemplate;

    List<String> allClassrooms;
    String[] classTime = {"12", "34", "56", "78", "90"};

    public ClassroomDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 插入课表数据
     *
     * @param classroom 教室，包含课表信息
     * @return boolean
     */
    public boolean insertClassroom(Classroom classroom) {
        String sql = "insert into class_schedule (classroom," +
                "w1_12,w1_34,w1_56,w1_78,w1_90," +
                "w2_12,w2_34,w2_56,w2_78,w2_90," +
                "w3_12,w3_34,w3_56,w3_78,w3_90," +
                "w4_12,w4_34,w4_56,w4_78,w4_90," +
                "w5_12,w5_34,w5_56,w5_78,w5_90," +
                "w6_12,w6_34,w6_56,w6_78,w6_90," +
                "w7_12,w7_34,w7_56,w7_78,w7_90) VALUES (" +
                "?,?,?,?,?,?,?,?,?," +
                "?,?,?,?,?,?,?,?,?," +
                "?,?,?,?,?,?,?,?,?," +
                "?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, classroom.getClassroom());
            ps.setObject(2, classroom.getW1_12());
            ps.setObject(3, classroom.getW1_34());
            ps.setObject(4, classroom.getW1_56());
            ps.setObject(5, classroom.getW1_78());
            ps.setObject(6, classroom.getW1_90());
            ps.setObject(7, classroom.getW2_12());
            ps.setObject(8, classroom.getW2_34());
            ps.setObject(9, classroom.getW2_56());
            ps.setObject(10, classroom.getW2_78());
            ps.setObject(11, classroom.getW2_90());
            ps.setObject(12, classroom.getW3_12());
            ps.setObject(13, classroom.getW3_34());
            ps.setObject(14, classroom.getW3_56());
            ps.setObject(15, classroom.getW3_78());
            ps.setObject(16, classroom.getW3_90());
            ps.setObject(17, classroom.getW4_12());
            ps.setObject(18, classroom.getW4_34());
            ps.setObject(19, classroom.getW4_56());
            ps.setObject(20, classroom.getW4_78());
            ps.setObject(21, classroom.getW4_90());
            ps.setObject(22, classroom.getW5_12());
            ps.setObject(23, classroom.getW5_34());
            ps.setObject(24, classroom.getW5_56());
            ps.setObject(25, classroom.getW5_78());
            ps.setObject(26, classroom.getW5_90());
            ps.setObject(27, classroom.getW6_12());
            ps.setObject(28, classroom.getW6_34());
            ps.setObject(29, classroom.getW6_56());
            ps.setObject(30, classroom.getW6_78());
            ps.setObject(31, classroom.getW6_90());
            ps.setObject(32, classroom.getW7_12());
            ps.setObject(33, classroom.getW7_34());
            ps.setObject(34, classroom.getW7_56());
            ps.setObject(35, classroom.getW7_78());
            ps.setObject(36, classroom.getW7_90());
            return ps;
        }) > 0;
    }

    /**
     * 获取今日空教室列表
     *
     * @return 返回HashMap
     */
    public HashMap<String, String> selectAllEmptyClassrooms() {
        int currentWeek = WeekUtils.getCurrentWeek();
        int dayOfWeek = WeekUtils.getDayOfWeek();
        HashMap<String, String> map = new HashMap<>();

        for (String time : classTime) {
            //查询今日有课教室列表
            String sql = "select classroom from class_schedule where w" + dayOfWeek + "_" + time + " REGEXP '^" + currentWeek + ",|[^1]+" + currentWeek + ",'";
            List<String> classroomsWithLesson = jdbcTemplate.queryForList(sql, String.class);

            if (allClassrooms == null) {
                selectAllClassrooms();
            }
            //初始化空教室列表
            List<String> emptyClassrooms = new ArrayList<>();
            for (String room : allClassrooms) {
                if (!classroomsWithLesson.contains(room)) {
                    emptyClassrooms.add(room);
                }
            }
            emptyClassrooms.sort(String::compareTo);
            map.put("when_" + time, emptyClassrooms.toString());
        }
        return map;
    }

    /**
     * 获取所有教室
     */
    public List<String> selectAllClassrooms() {
        String sql = "select classroom from class_schedule";
        allClassrooms = jdbcTemplate.queryForList(sql, String.class);
        return allClassrooms;
    }
}
