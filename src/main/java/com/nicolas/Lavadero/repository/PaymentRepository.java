package com.nicolas.Lavadero.repository;

import com.nicolas.Lavadero.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
