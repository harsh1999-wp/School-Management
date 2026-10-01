package com.harsh.School.entity;
import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id; //Primary key
    @Column(
        nullable = false,
        length = 123


    )

    private String name;

    @Column(precision = 3 ,scale = 0)
    private int rollno;

    @Lob
    private String address;

    private String email;

    private String subject;

    @Convert(converter = BooleanToStringConverter.class)
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    public Student(Department department) {
        this.department = department;
    }

    public Student() {

    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }







    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
































//    private LocalDateTime currentTime;
//    private LocalDateTime updatedAt;

//    public LocalDateTime getCurrentTime() {
//        return currentTime;
//    }
//
//    public void setCurrentTime(LocalDateTime currentTime) {
//        this.currentTime = currentTime;
//    }
//
//    public LocalDateTime getUpdatedAt(LocalDateTime now) {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }