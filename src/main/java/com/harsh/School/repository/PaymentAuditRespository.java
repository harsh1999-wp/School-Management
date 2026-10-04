package com.harsh.School.repository;

import com.harsh.School.entity.PaymentAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentAuditRespository  extends JpaRepository<PaymentAudit , Long> {
}
