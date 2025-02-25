package com.nicolas.Lavadero.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "serviceType", cascade = CascadeType.REFRESH)
    private List<Appointment> appointment;
}
