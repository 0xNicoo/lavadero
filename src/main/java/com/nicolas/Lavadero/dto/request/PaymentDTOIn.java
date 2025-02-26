package com.nicolas.Lavadero.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDTOIn {
    private long id;
    private BigDecimal amount;
    private Long appointmentId;
}
