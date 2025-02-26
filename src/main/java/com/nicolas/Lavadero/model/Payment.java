package com.nicolas.Lavadero.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @PrePersist
    public void prePersist(){
        this.date = LocalDateTime.now();
    }
}
