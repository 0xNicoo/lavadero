package com.nicolas.Lavadero.service;

import com.nicolas.Lavadero.dto.request.AssociateAppointmentDTO;
import com.nicolas.Lavadero.dto.request.VehicleAttributeDTOIn;
import com.nicolas.Lavadero.dto.request.VehicleDTOIn;
import com.nicolas.Lavadero.dto.response.VehicleDTO;

import java.util.List;

public interface VehicleService {

    VehicleDTO create(VehicleDTOIn vehicleDTOIn);

    VehicleDTO get(Long id);

    List<VehicleDTO> getAll();

    void assignAppointment(Long vehicleId, AssociateAppointmentDTO associateAppointmentDTO);

    VehicleDTO addAttribute(Long vehicleId, VehicleAttributeDTOIn attributesDTO);

}
