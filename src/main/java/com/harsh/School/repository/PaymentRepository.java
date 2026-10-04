package com.harsh.School.repository;

import com.harsh.School.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment , Long> {

}
