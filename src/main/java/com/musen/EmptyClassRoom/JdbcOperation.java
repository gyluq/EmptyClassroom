package com.musen.EmptyClassRoom;

import com.musen.EmptyClassRoom.pojo.Classroom;
import com.musen.EmptyClassRoom.utils.JdbcUtils;
import com.musen.EmptyClassRoom.utils.WeekUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JdbcOperation {
    String[] classTime = {"12", "34", "56", "78", "90"};
    String[] classRoom = {"雨母楼101", "雨母楼102", "雨母楼103", "雨母楼104", "雨母楼105", "雨母楼106", "雨母楼107", "雨母楼108",
            "雨母楼111", "雨母楼113", "雨母楼114", "雨母楼115", "雨母楼116", "雨母楼117", "雨母楼118", "雨母楼119", "雨母楼120", "雨母楼201",
            "雨母楼202", "雨母楼203", "雨母楼204", "雨母楼205", "雨母楼206", "雨母楼207", "雨母楼208", "雨母楼211", "雨母楼212", "雨母楼213",
            "雨母楼214", "雨母楼215", "雨母楼216", "雨母楼217", "雨母楼218", "雨母楼219", "雨母楼220", "雨母楼221", "雨母楼301", "雨母楼302",
            "雨母楼303", "雨母楼304", "雨母楼305", "雨母楼306", "雨母楼307", "雨母楼308", "雨母楼311", "雨母楼312", "雨母楼313", "雨母楼314",
            "雨母楼315", "雨母楼316", "雨母楼317", "雨母楼318", "雨母楼319", "雨母楼320", "雨母楼321", "雨母楼401", "雨母楼402", "雨母楼403",
            "雨母楼404", "雨母楼406", "雨母楼407", "雨母楼409", "雨母楼410", "雨母楼411", "雨母楼412", "雨母楼413", "雨母楼414", "雨母楼415",
            "雨母楼416", "雨母楼417", "雨母楼418", "雨母楼419", "雨母楼420", "雨母楼501", "雨母楼502", "雨母楼503", "雨母楼504", "雨母楼510",
            "雨母楼511", "雨母楼512", "雨母楼513", "雨母楼514", "雨母楼515", "雨母楼516", "雨母楼517", "雨母楼518", "雨母楼519", "雨母楼520",
            "雨母楼521", "雨母楼522", "雨母楼523", "雨母楼524"};

    /**
     * 插入到数据库
     *
     * @param classroom 教室
     * @throws SQLException
     */
    public void insertClassroom(Classroom classroom) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtils.getConnection();
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
            ps = conn.prepareStatement(sql);
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
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(conn, ps);
        }
    }

    /**
     * 获取今日空教室列表
     *
     * @return 所有今日数据
     */
    public HashMap<String, String> selectEmptyClassroom() {
        int currentWeek = WeekUtils.getCurrentWeek();
        int dayOfWeek = WeekUtils.getDayOfWeek();

        //System.out.println(currentWeek + ":" + dayOfWeek);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        HashMap<String, String> map = new HashMap<>();
        //遍历5次，找到今天对应节次无课教室
        for (String time : classTime) {
            try {
                List<String> noLessonsRoom = new ArrayList<>(Arrays.asList(classRoom));

                conn = JdbcUtils.getConnection();
                String sql = "select classroom from class_schedule where w" + dayOfWeek + "_" + time + " REGEXP '^" + currentWeek + ",|[^1]+" + currentWeek + ",'";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String classroom = rs.getString(1);
                    noLessonsRoom.remove(classroom);
                }
                map.put("when_" + time, noLessonsRoom.toString());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                JdbcUtils.closeResource(conn, ps, rs);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        JdbcOperation jdbcOperation = new JdbcOperation();
        jdbcOperation.selectEmptyClassroom();
    }
}
