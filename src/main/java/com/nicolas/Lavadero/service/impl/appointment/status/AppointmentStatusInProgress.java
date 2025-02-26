package com.nicolas.Lavadero.service.impl.appointment.status;

import com.nicolas.Lavadero.dto.request.AppointmentStatusDTO;
import com.nicolas.Lavadero.exception.custom.BadRequestException;
import com.nicolas.Lavadero.exception.error.Error;
import com.nicolas.Lavadero.model.Appointment;
import com.nicolas.Lavadero.model.AppointmentStatus;
import com.nicolas.Lavadero.service.impl.appointment.AppointmentStatusStrategy;
import org.springframework.stereotype.Component;

@Component
public class AppointmentStatusInProgress implements AppointmentStatusStrategy {
    @Override
    public Appointment change(AppointmentStatusDTO statusDTO, Appointment appointment) {
        if(statusDTO.getNextStatus().equals(AppointmentStatus.FINISHED)){
            appointment.setStatus(AppointmentStatus.FINISHED);
            return appointment;
        }
        throw new BadRequestException(Error.APPOINTMENT_INVALID_STATUS);
    }

    @Override
    public AppointmentStatus getStatus() {
        return AppointmentStatus.IN_PROGRESS;
    }
}
