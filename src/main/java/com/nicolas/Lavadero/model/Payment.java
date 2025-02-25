package com.nicolas.Lavadero.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private BigDecimal amount;

    @Column
    private LocalDateTime date;

    @OneToOne(mappedBy = "payment")
    private Appointment appointment;
}
