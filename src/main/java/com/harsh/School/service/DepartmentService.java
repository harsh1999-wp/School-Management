package com.harsh.School.service;

import com.harsh.School.entity.Department;
import com.harsh.School.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public void createDepartment(Department department){
            departmentRepository.save(department);
    }
}
