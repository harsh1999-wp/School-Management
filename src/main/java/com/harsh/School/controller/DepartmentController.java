package com.harsh.School.controller;


import com.harsh.School.entity.Department;
import com.harsh.School.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deparment")
public class DepartmentController {

    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<String> createDepartment(@RequestBody Department department){

        departmentService.createDepartment(department);

        return ResponseEntity.ok("OK");
    }

}
