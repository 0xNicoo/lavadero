package com.nicolas.Lavadero.service.impl.appointment;

import com.nicolas.Lavadero.dto.request.AppointmentStatusDTO;
import com.nicolas.Lavadero.model.Appointment;
import com.nicolas.Lavadero.model.AppointmentStatus;

public interface AppointmentStatusStrategy {
    Appointment change(AppointmentStatusDTO statusDTO, Appointment appointment);

    AppointmentStatus getStatus();
}
