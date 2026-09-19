package com.harsh.School.controller;

import com.harsh.School.dto.CreateStduentResponseDto;
import com.harsh.School.dto.CreateStudentRequestDto;
import com.harsh.School.entity.Student;
import com.harsh.School.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;
    //doing AutoWiring here
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    //create Student
    @PostMapping
    public ResponseEntity<CreateStduentResponseDto> createStudent(@RequestBody CreateStudentRequestDto studentRequestDto){

        CreateStduentResponseDto createdStudent = studentService.createStudent(studentRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    //read Student
    @GetMapping("/{id}")
    public  ResponseEntity<CreateStduentResponseDto> getStudent(@PathVariable Long id){
        CreateStduentResponseDto  studentResp = studentService.getStudent(id);

        if(studentResp == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(studentResp);
    }

    //GetAlldetails
    @GetMapping
    public  ResponseEntity<List<CreateStduentResponseDto>> getAllStudent(){
        List<CreateStduentResponseDto> studentList = studentService.getAllStudent();

//        if(studentList == null){
//            return ResponseEntity.notFound().build();
//        }

        return ResponseEntity.ok(studentList);
    }

    //update Student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id , @RequestBody Student studentReq){
        Student studreq = studentService.updateStudent(id , studentReq);

//        if(studreq == null){
//            return ResponseEntity.notFound().build();
//        }
        return  ResponseEntity.ok(studreq);
    }
    //delete Student
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteStudent(@PathVariable Long id){

         studentService.deleteStudent(id);

//        if(!isDeleted){
//            return ResponseEntity.notFound().build();
//        }
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
// soft delete
    @PatchMapping("/soft-delete/{id}")
    public ResponseEntity<String> softDeleteStudent(@PathVariable Long id){
         studentService.softDeleteStudent(id);

//        if(!isDeleted){
//            return  ResponseEntity.notFound().build();
//        }
        return  ResponseEntity.noContent().build();
    }
}
