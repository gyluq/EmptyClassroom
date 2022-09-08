package com.musen.EmptyClassRoom.controller;

import com.musen.EmptyClassRoom.service.ClassRoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JdbcControllerTest {

    private final ClassRoomService classRoomService;

    public JdbcControllerTest(ClassRoomService classRoomService) {
        this.classRoomService = classRoomService;
    }

    @GetMapping("/user")
    public void userList2() {
        System.out.println(classRoomService.getAllEmptyClassrooms());
    }
}
