package com.harsh.School.service;

import com.harsh.School.entity.Department;
import com.harsh.School.entity.Student;
import com.harsh.School.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public void createDepartment(Department department){

        Student student1 = new Student();
        student1.setName("Harsh");
        student1.setDepartment(department);

        Student student2 = new Student();
        student2.setName("Aditya");
        student2.setDepartment(department);

        Student student3 = new Student();
        student3.setName("Shashank");
        student3.setDepartment(department);

        Student student4 = new Student();
        student4.setName("Joe");
        student4.setDepartment(department);

        department.getStudents().addAll(List.of(student1,student2,student3,student4));

        departmentRepository.save(department);
    }
}
