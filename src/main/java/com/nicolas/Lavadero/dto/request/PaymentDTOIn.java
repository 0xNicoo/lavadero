package com.nicolas.Lavadero.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDTOIn {
    private long id;
    private BigDecimal amount;

    @NotNull
    private Long appointmentId;
}
