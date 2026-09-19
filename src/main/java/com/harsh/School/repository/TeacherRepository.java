package com.harsh.School.repository;

import com.harsh.School.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
