package com.nicolas.Lavadero.dto.response;

import com.nicolas.Lavadero.model.AppointmentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentSummaryDTO {
    private long id;
    private LocalDateTime date;
    private AppointmentStatus status;
    private ServiceTypeDTO serviceType;
    private VehicleSummaryDTO vehicle;
}
