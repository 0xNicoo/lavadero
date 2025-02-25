package com.nicolas.Lavadero.service;

import com.nicolas.Lavadero.dto.request.VehicleDTOIn;
import com.nicolas.Lavadero.dto.response.VehicleDTO;

import java.util.List;

public interface VehicleService {

    VehicleDTO create(VehicleDTOIn vehicleDTOIn);

    VehicleDTO get(Long id);

    List<VehicleDTO> getAll();

}
