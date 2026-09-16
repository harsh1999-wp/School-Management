package com.harsh.School.service;

import com.harsh.School.dto.CreateStduentResponseDto;
import com.harsh.School.dto.CreateStudentRequestDto;
import com.harsh.School.entity.Student;
import com.harsh.School.repository.StudentRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;

    }

    public CreateStduentResponseDto createStudent(CreateStudentRequestDto studentReqDto){
        Student student = maptoEntity(studentReqDto);
        //System.out.println("Inside Student Service");
        Student studentResp = studentRepository.save(student);
        //System.out.println("Exiting Student Respository");
        return mapToDto(student);
    }

    public  CreateStduentResponseDto getStudent(Long id){

        Student studentResp = studentRepository.findById(id).orElseThrow();

        return mapToDto(studentResp);
    }

    //Get all the student details

    public List<CreateStduentResponseDto> getAllStudent(){

        List<Student> studentList = studentRepository.findByDeletedIsFalse();

       return studentList.stream().map(this::mapToDto).toList();
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

        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if (existingStudent.isEmpty()){
            return false;
        }

        Student saveToSave = existingStudent.get();
        saveToSave.setDeleted(true);
        studentRepository.save(saveToSave);

        return true;

    }

    public Student maptoEntity(CreateStudentRequestDto studentReqDto){

        Student student = new Student();

        student.setName(studentReqDto.getName());
        student.setAddress(studentReqDto.getAddress());
        student.setSubject(studentReqDto.getSubject());
        student.setEmail(studentReqDto.getEmail());
        student.setCurrentTime(LocalDateTime.now());
        student.getUpdatedAt(LocalDateTime.now());

        student.setDeleted(false);

        return student;
    }

    public CreateStduentResponseDto mapToDto(Student student){

        CreateStduentResponseDto studentResDto = new CreateStduentResponseDto();

        studentResDto.setName(student.getName());
        studentResDto.setId(student.getId());
        studentResDto.setRollno(student.getRollno());
        studentResDto.setEmail(student.getEmail());
        studentResDto.setAddress(student.getAddress());
        studentResDto.setMessage("Student Save Successfully");
        studentResDto.setUpdatedAt(student.getUpdatedAt(LocalDateTime.now()));
        studentResDto.setCreatedAt(student.getCurrentTime());

        return studentResDto;

    }
}
