package com.harsh.School.repository;

import com.harsh.School.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentRepository {

    public Student saveStudent(Student studentReq){

        //save to DB
        System.out.println("Inside Student Respository");

        Student s1 = new Student();
        s1.setName("Sonu");
        s1.setRollno(12);
        s1.setAddress("adhadhkj");
        s1.setSubject("english");

        return  s1;

    }
}
