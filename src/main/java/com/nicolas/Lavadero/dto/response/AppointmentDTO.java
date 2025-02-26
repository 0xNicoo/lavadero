package com.nicolas.Lavadero.dto.response;

import com.nicolas.Lavadero.model.AppointmentStatus;
import com.nicolas.Lavadero.model.Payment;
import com.nicolas.Lavadero.model.ServiceType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO {
    private long id;
    private LocalDateTime date;
    private AppointmentStatus status;
    private ServiceType serviceType;
    private VehicleSummaryDTO vehicle;
    private Payment payment;
}
