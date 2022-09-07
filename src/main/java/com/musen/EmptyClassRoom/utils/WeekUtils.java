package com.musen.EmptyClassRoom.utils;

import java.util.Calendar;

/**
 * 获取当前周和星期
 */
public class WeekUtils {

    public static int getCurrentWeek() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.WEEK_OF_YEAR) - 35;
    }

    public static int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == 1 ? 7 : dayOfWeek - 1;
    }
}
