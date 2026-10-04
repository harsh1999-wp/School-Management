package com.harsh.School.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Payment {
    
    @Id
    private long transatationId;


    private String name;
    private int salary;
    private boolean status;
    
    
}
