package com.harsh.School.controller;

import com.harsh.School.entity.Student;
import com.harsh.School.service.StudentService;
import jakarta.persistence.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    //create Student
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getName());
        System.out.println(student.getRollno());
        System.out.println("Inside Student Controller");
        Student createdStudent = studentService.createStudent(student);
        System.out.println("Exiting Student Service");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);


    }

    //read Student
    @GetMapping("/get/{id}")
    public  ResponseEntity<Student> getStudent(@PathVariable Long id){
        Student studentResp = studentService.getStudent(id);

        if(studentResp == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(studentResp);
    }

    //GetAlldetails
    @GetMapping("/get/all")
    public  ResponseEntity<List<Student>> getallStudnet(){
        List<Student> studentResp = studentService.getallStudent();

        if(studentResp == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentResp);
    }

    //update Student
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id , @RequestBody Student studentReq){
        Student studreq = studentService.updateStudent(id , studentReq);

        if(studreq == null){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(studreq);
    }
    //delete Student
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> deleteStudent(@PathVariable Long id){

        Boolean isDeleted = studentService.deleteStudent(id);

        if(!isDeleted){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok("Record deleted");
    }

}
