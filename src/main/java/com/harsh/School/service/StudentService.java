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
        studentreq.setDeleted(false);
        //System.out.println("Inside Student Service");
        Student studentResp = studentRepository.save(studentreq);
        //System.out.println("Exiting Student Respository");
        return studentreq;
    }

    public  Student getStudent(Long id){

        Optional<Student> studentResp = studentRepository.findByIdAnddeletedISFalse(id);

        if(studentResp.isPresent()){
            return studentResp.get();
        }
        else{
            return null;
        }
    }

    //Get all the student details

    public List<Student> getallStudent(){

        List<Student> studentList = studentRepository.findAllDeletedIsFalse();

       return studentList;
    }

    // Updating student details
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
        studenttosave.setDeleted(false);

        return studentRepository.save(studenttosave);
    }
// deleting student details : will work normally
    public  Boolean deleteStudent(Long id){
        Boolean studreq = studentRepository.existsById(id);

        if(!studreq) return false;

        studentRepository.deleteById(id);

        return true;
    }

    public  Boolean softDeleteStudent(Long id) {

        Optional<Student> existingStudent = studentRepository.findByIdAnddeletedIsFalse(id);

        if (existingStudent.isEmpty()){
            return false;
        }

        Student saveToSave = existingStudent.get();
        saveToSave.setDeleted(true);
        studentRepository.save(saveToSave);

        return true;

    }
}
