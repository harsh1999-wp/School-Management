package com.harsh.School.repository;

import com.harsh.School.entity.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class DepartmentRepository  {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Department department){
        entityManager.persist(department);
    }

    public  Department getDepartmentById(Long Id){
        return entityManager.find(Department.class , Id);
    }
}
