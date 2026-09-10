package com.harsh.School.service;

import com.harsh.School.controller.StudentController;
import com.harsh.School.entity.Student;
import com.harsh.School.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;

    }

    public Student createStudent(Student studentreq){
        System.out.println("Inside Student Service");
        Student studentResp = studentRepository.saveStudent(studentreq);
        System.out.println("Exiting Student Respository");
        return studentreq;
    }
}
