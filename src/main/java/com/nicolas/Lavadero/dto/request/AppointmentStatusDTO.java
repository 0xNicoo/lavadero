package com.nicolas.Lavadero.dto.request;

import com.nicolas.Lavadero.model.AppointmentStatus;
import lombok.Data;

@Data
public class AppointmentStatusDTO {
    private AppointmentStatus nextStatus;
}
