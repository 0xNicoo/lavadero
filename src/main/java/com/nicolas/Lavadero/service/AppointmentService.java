package com.nicolas.Lavadero.service;

import com.nicolas.Lavadero.dto.request.AppointmentDTOIn;
import com.nicolas.Lavadero.dto.request.AppointmentStatusDTO;
import com.nicolas.Lavadero.dto.response.AppointmentDTO;

import java.util.List;

public interface AppointmentService {
    AppointmentDTO create(AppointmentDTOIn appointmentDTOIn);

    AppointmentDTO get(Long id);

    List<AppointmentDTO> getAll();

    AppointmentDTO changeStatus(AppointmentStatusDTO appointmentStatusDTO, Long appointmentId);

}
