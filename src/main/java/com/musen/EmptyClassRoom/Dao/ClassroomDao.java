package com.musen.EmptyClassRoom.Dao;

import com.musen.EmptyClassRoom.pojo.Classroom;

import java.util.HashMap;
import java.util.List;

public interface ClassroomDao {
    boolean insertClassroom(Classroom classroom);
    HashMap<String, String> selectAllEmptyClassrooms();
    List<String> selectAllClassrooms();
}
