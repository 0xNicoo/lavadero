package com.nicolas.Lavadero.repository;

import com.nicolas.Lavadero.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
