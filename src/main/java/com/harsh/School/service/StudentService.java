package com.harsh.School.service;

import com.harsh.School.annotation.TimeTrack;
import com.harsh.School.dto.CreateStduentResponseDto;
import com.harsh.School.dto.CreateStudentRequestDto;
import com.harsh.School.entity.Department;
import com.harsh.School.entity.Student;
import com.harsh.School.exception.DuplicateResourceException;
import com.harsh.School.exception.ResourceNotFoundException;
import com.harsh.School.repository.DepartmentRepository;
import com.harsh.School.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class StudentService {

    private StudentRepository studentRepository;
    private DepartmentRepository departmentRepository;

    public StudentService(StudentRepository studentRepository,
                          DepartmentRepository departmentRepository){
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;

    }

    //Creating Student with id
    @Transactional
    public CreateStduentResponseDto createStudent(CreateStudentRequestDto studentReqDto ,
                                                  Long Id){

        //getting id for department
        Department department = departmentRepository.getDepartmentById(Id);

        Student student = maptoEntity(studentReqDto, department);

        //how ?
        student.setDepartment(department);

        //Exception handling
        if(emailExist(student)){
            throw new DuplicateResourceException("Student with this " +student.getEmail() +" already Exist");
        }

        Student studentResp = studentRepository.save(student);

        return mapToDto(studentResp);
    }

    //Creating Student with deptname
    @Transactional
    public CreateStduentResponseDto createStudent(CreateStudentRequestDto studentReqDto ,
                                                   String name){

        Department department = new Department();

        //getting id for department
        department.setName(name);

        departmentRepository.save(department);

        Student student = maptoEntity(studentReqDto, department);

        //how ?
        student.setDepartment(department);

        //Exception handling
        if(emailExist(student)){
            throw new DuplicateResourceException("Student with this " +student.getEmail() +" already Exist");
        }

        Student studentResp = studentRepository.save(student);

        return mapToDto(studentResp);
    }

    //getting single student
    public  CreateStduentResponseDto getStudent(Long id){

        Student studentResp = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student with id " + id +" not found"));

        return mapToDto(studentResp);
    }

    //Get all the student details
    @TimeTrack(warnAfter = 10,
               operation = "Getting all Student")
    public List<CreateStduentResponseDto> getAllStudent(){

        try{
            Thread.sleep(2000);
        }catch (Exception e){

        }

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

    public Student maptoEntity(CreateStudentRequestDto studentReqDto, Department department){

        Student student = new Student();
        Department dep = new Department();

        student.setName(studentReqDto.getName());
        student.setAddress(studentReqDto.getAddress());
        student.setSubject(studentReqDto.getSubject());
        student.setEmail(studentReqDto.getEmail());
        dep.setId(dep.getId());

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
