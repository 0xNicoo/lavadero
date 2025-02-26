package com.nicolas.Lavadero.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private long id;
    private BigDecimal amount;
    private LocalDateTime date;
    private AppointmentSummaryDTO appointment;
}
