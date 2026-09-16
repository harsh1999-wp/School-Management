package com.harsh.School.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class CreateStduentResponseDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;



    private String name;
    private int rollno;
    private String address;
    private String email;
    private String message;
    private String subject;
  //  private LocalDateTime CreatedAt;
   // private LocalDateTime updatedAt;

//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }

//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public LocalDateTime getCreatedAt() {
//        return CreatedAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        CreatedAt = createdAt;
//    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    private LocalDateTime updatedTime;

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
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

