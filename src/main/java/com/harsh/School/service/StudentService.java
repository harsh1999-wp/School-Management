package com.harsh.School.service;

import com.harsh.School.entity.Student;
import com.harsh.School.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;

    }

    public Student createStudent(Student studentreq){
        System.out.println("Inside Student Service");
        Student studentResp = studentRepository.save(studentreq);
        System.out.println("Exiting Student Respository");
        return studentreq;
    }

    public  Student getStudent(Long id){

        Optional<Student> studentResp = studentRepository.findById(id);

        if(studentResp.isPresent()){
            return studentResp.get();
        }
        else{
            return null;
        }
    }

    public List<Student> getallStudent(){

        List<Student> studentList = studentRepository.findAll();

       return studentList;
    }

    public Student updateStudent(Long id, Student studentReq){

        Optional<Student> existingReq = studentRepository.findById(id);

        if(existingReq.isEmpty()){
            return null;
        }

        Student studenttosave = existingReq.get();

        studenttosave.setSubject(studentReq.getSubject());
        studenttosave.setName(studentReq.getName());
        studenttosave.setAddress(studentReq.getAddress());
        studenttosave.setRollno(studentReq.getRollno());

        return studentRepository.save(studenttosave);
    }

    public  Boolean deleteStudent(Long id){
        Boolean studreq = studentRepository.existsById(id);

        if(!studreq) return false;

        studentRepository.deleteById(id);

        return true;
    }
}
