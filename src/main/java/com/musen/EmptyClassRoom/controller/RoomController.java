package com.musen.EmptyClassRoom.controller;

import com.musen.EmptyClassRoom.pojo.FormattedRoom;
import com.musen.EmptyClassRoom.service.ClassRoomService;
import com.musen.EmptyClassRoom.utils.ResultUtils;
import com.musen.EmptyClassRoom.utils.WeekUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class RoomController {

    private final ClassRoomService classRoomService;

    public RoomController(ClassRoomService classRoomService) {
        this.classRoomService = classRoomService;
    }

    @ResponseBody
    @GetMapping("/room")
    public ResultUtils room() {
        ArrayList<FormattedRoom> arrayList = new ArrayList<>();
        arrayList.add(new FormattedRoom());
        arrayList.add(new FormattedRoom());
        arrayList.add(new FormattedRoom());
        arrayList.add(new FormattedRoom());
        arrayList.add(new FormattedRoom());

        //获取总数据
        HashMap<String, String> stringListHashMap = classRoomService.getAllEmptyClassrooms();

        //调整数据结构,便于elementUI解析
        for (Map.Entry<String, String> entry : stringListHashMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            //value中的数据表示第n节课的所有空教室,遍历5次即按楼层分类
            for (int j = 1; j <= 5; j++) {
                FormattedRoom formattedRoom = arrayList.get(j - 1);
                formattedRoom.setWhichFloor("floor" + j);
                //匹配楼层
                String pattern = "雨母楼" + j + ".*雨母楼" + j + "\\d{2}";
                Pattern pt = Pattern.compile(pattern);
                Matcher matcher = pt.matcher(value);
                if (matcher.find()) {
                    //去掉"雨母楼"
                    String superX = matcher.group().replace("雨母楼", "");
                    //分楼层
                    if ("when_12".equals(key)) {
                        if (formattedRoom.getClass_12() != null) {
                            formattedRoom.setClass_12(formattedRoom.getClass_12() + "," + superX);
                        } else {
                            formattedRoom.setClass_12(superX);
                        }
                    } else if ("when_34".equals(key)) {
                        if (formattedRoom.getClass_34() != null) {
                            formattedRoom.setClass_34(formattedRoom.getClass_34() + "," + superX);
                        } else {
                            formattedRoom.setClass_34(superX);
                        }
                    } else if ("when_56".equals(key)) {
                        if (formattedRoom.getClass_56() != null) {
                            formattedRoom.setClass_56(formattedRoom.getClass_56() + "," + superX);
                        } else {
                            formattedRoom.setClass_56(superX);
                        }
                    } else if ("when_78".equals(key)) {
                        if (formattedRoom.getClass_78() != null) {
                            formattedRoom.setClass_78(formattedRoom.getClass_78() + "," + superX);
                        } else {
                            formattedRoom.setClass_78(superX);
                        }
                    } else {
                        if (formattedRoom.getClass_90() != null) {
                            formattedRoom.setClass_90(formattedRoom.getClass_90() + "," + superX);
                        } else {
                            formattedRoom.setClass_90(superX);
                        }
                    }
                }
            }
        }
        ResultUtils resultUtils = new ResultUtils();
        resultUtils.setFormattedRooms(arrayList);
        resultUtils.setCurrentWeek(WeekUtils.getCurrentWeek());
        resultUtils.setDayOfWeek(WeekUtils.getDayOfWeek());
        return resultUtils;
    }

    @GetMapping("/")
    public String index() {
        return "/pages/room.html";
    }
}
