package com.harsh.School.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data


@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private  Long id;

    private String name;
}
