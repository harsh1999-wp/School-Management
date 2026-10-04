package com.harsh.School.service;

import com.harsh.School.entity.Payment;
import com.harsh.School.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    PaymentRepository paymentRepository;

    public  PaymentService(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    public void paymentSuccessful(Payment payment){
        paymentRepository.save(payment);
    }
}
