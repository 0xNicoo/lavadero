package com.nicolas.Lavadero.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssociateAppointmentDTO {
    @NotNull
    private Long appointmentId;
}
