package com.musen.EmptyClassRoom.service;

import com.musen.EmptyClassRoom.Dao.ClassroomDao;
import com.musen.EmptyClassRoom.pojo.Classroom;
import com.musen.EmptyClassRoom.utils.MapUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassRoomService {
    private final ClassroomDao classroomDao;

    public ClassRoomService(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }

    public void saveClassroomInf() throws Exception {
        ParseHtml parseHtml = new ParseHtml();
        ArrayList<Map> arrayList = parseHtml.getClassroomInf();
        for (Map<String, String> hashMapMap : arrayList) {
            Classroom classroom = MapUtils.mapToObj(hashMapMap, Classroom.class);
            classroomDao.insertClassroom(classroom);
        }
    }

    public HashMap<String, String> getAllEmptyClassrooms() {
        return classroomDao.selectAllEmptyClassrooms();
    }

    public List<String> getAllClassrooms() {
        return classroomDao.selectAllClassrooms();
    }
}
