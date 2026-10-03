package com.harsh.School.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Data


@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )// auto unqiue id
    @Column(name = "id")
    private  Long dept_id;

    private String name;

    @OneToMany(mappedBy = "department",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();

    @OneToOne(mappedBy = "department" ,
                cascade = CascadeType.REMOVE,
                fetch = FetchType.LAZY)
    private List<Teacher> teachers = new ArrayList<>();
}
