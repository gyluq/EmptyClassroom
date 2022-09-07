package com.musen.EmptyClassRoom.utils;

import com.musen.EmptyClassRoom.pojo.FormattedRoom;
import lombok.Data;

import java.util.ArrayList;

/**
 * 返回结果
 */
@Data
public class ResultUtils {
    ArrayList<FormattedRoom> formattedRooms;
    Integer currentWeek;
    Integer dayOfWeek;
}
