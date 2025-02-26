package com.nicolas.Lavadero.service;

import com.nicolas.Lavadero.dto.request.ServiceTypeDTOIn;
import com.nicolas.Lavadero.dto.response.ServiceTypeDTO;

import java.util.List;

public interface ServiceTypeService {
    ServiceTypeDTO create(ServiceTypeDTOIn serviceTypeDTOIn);

    List<ServiceTypeDTO> getAll();
}
