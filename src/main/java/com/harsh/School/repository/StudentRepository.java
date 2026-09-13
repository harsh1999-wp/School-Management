package com.harsh.School.repository;

import com.harsh.School.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {

    Optional<Student> findByIdAnddeletedIsFalse(Long id);

    List<Student> findAllDeletedIsFalse();

    Optional<Student> findByIdAnddeletedISFalse(Long id);


    // find by + "fieldname" + condition
}
