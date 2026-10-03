package com.harsh.School.controller;

import com.harsh.School.entity.Teacher;
import com.harsh.School.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/teacher"})
public class TeacherController {

    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @PostMapping({"/name"})
    ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher){

        Teacher t1 = new TeacherService.createTeacher(teacher);

        return null;
    }
}
