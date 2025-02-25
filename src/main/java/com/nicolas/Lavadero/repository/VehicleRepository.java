package com.nicolas.Lavadero.repository;

import com.nicolas.Lavadero.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByRegistrationAndClientId(String registration, Long clientId);
}
