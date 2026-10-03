package com.harsh.School.repository;

import com.harsh.School.entity.Teacher;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface TeacherRepository extends JpaRepositoryImplementation<Teacher, Long> {
}
