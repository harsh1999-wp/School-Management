package com.harsh.School.service;

import com.harsh.School.entity.Teacher;
import com.harsh.School.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    TeacherRepository teacherRepository;

    TeacherService(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    public Teacher createTeacher(Teacher teacher){
        Teacher teacher1 = teacherRepository.save(teacher);
        return teacher1;
    }

    // why this
    public static class createTeacher extends Teacher {
        public createTeacher(Teacher teacher) {
            super();
        }
    }
}
