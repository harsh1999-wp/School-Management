package com.harsh.School.service;

import com.harsh.School.dto.CreateStduentResponseDto;
import com.harsh.School.dto.CreateStudentRequestDto;
import com.harsh.School.entity.Student;
import com.harsh.School.exception.DuplicateResourceException;
import com.harsh.School.exception.ResourceNotFoundException;
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

        if(emailExist(student)){
            throw new DuplicateResourceException("Student with this " +student.getEmail() +" already Exist");
        }

        Student studentResp = studentRepository.save(student);

        return mapToDto(studentResp);
    }

    public  CreateStduentResponseDto getStudent(Long id){

        Student studentResp = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student with id " + id +" not found"));

        return mapToDto(studentResp);
    }

    //Get all the student details

    public List<CreateStduentResponseDto> getAllStudent(){

        List<Student> studentList = studentRepository.findByDeletedIsFalse();

       return studentList.stream().map(this::mapToDto).toList();
    }

    // Updating student details
    public Student updateStudent(Long id, Student studentReq){

        Student existingReq = studentRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Student not found with id" + id ));
// no need we added exception here:
//        if(existingReq.isEmpty()){
//            return null;
//        }

        //Student studenttosave = existingReq;

        existingReq.setSubject(studentReq.getSubject());
        existingReq.setName(studentReq.getName());
        existingReq.setAddress(studentReq.getAddress());
        existingReq.setRollno(studentReq.getRollno());
        existingReq.setDeleted(false);

        // need to add update dto class here.

        return studentRepository.save(existingReq);
    }
// deleting student details : will work normally
    public  void deleteStudent(Long id){
        Student  studentToBeDeleted = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student with id " + id +"Not found"));

       // if(!studreq) return false;

        studentRepository.delete(studentToBeDeleted);
    }

    public  void softDeleteStudent(Long id) {

        Student StudentToBeDeleted = studentRepository.findByIdAndDeletedIsFalse(id).orElseThrow(()->new ResourceNotFoundException("Student with id "  + id   + " not found"));

//        if (existingStudent.isEmpty()){
//            return false;
//        }
        //TODO:
        //Student saveToSave = existingStudent.get(); why we deleted after using exception need to check
        StudentToBeDeleted.setDeleted(true);
        studentRepository.save(StudentToBeDeleted);


    }

    public Student maptoEntity(CreateStudentRequestDto studentReqDto){

        Student student = new Student();

        student.setName(studentReqDto.getName());
        student.setAddress(studentReqDto.getAddress());
        student.setSubject(studentReqDto.getSubject());
        student.setEmail(studentReqDto.getEmail());
       // student.setCurrentTime(LocalDateTime.now());
        //student.getUpdatedAt(LocalDateTime.now());

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
      //  studentResDto.setUpdatedAt(student.getUpdatedAt(LocalDateTime.now()));
       // studentResDto.setCreatedAt(student.getCurrentTime());

        return studentResDto;

    }

    private boolean emailExist(Student student){
        return studentRepository.existsByEmail(student.getEmail());
    }
}
