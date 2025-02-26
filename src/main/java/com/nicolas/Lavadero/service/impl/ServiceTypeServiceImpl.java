package com.nicolas.Lavadero.service.impl;

import com.nicolas.Lavadero.dto.request.ServiceTypeDTOIn;
import com.nicolas.Lavadero.dto.response.ServiceTypeDTO;
import com.nicolas.Lavadero.model.ServiceType;
import com.nicolas.Lavadero.repository.ServiceTypeRepository;
import com.nicolas.Lavadero.service.ServiceTypeService;
import com.nicolas.Lavadero.service.mapper.ServiceTypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {
    private final ServiceTypeRepository serviceTypeRepository;

    public ServiceTypeServiceImpl(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    @Override
    public ServiceTypeDTO create(ServiceTypeDTOIn serviceTypeDTOIn) {
        ServiceType serviceType = ServiceTypeMapper.MAPPER.toEntity(serviceTypeDTOIn);
        serviceType = serviceTypeRepository.save(serviceType);
        return ServiceTypeMapper.MAPPER.toDto(serviceType);
    }

    @Override
    public List<ServiceTypeDTO> getAll() {
        List<ServiceType> serviceTypes = serviceTypeRepository.findAll();
        return ServiceTypeMapper.MAPPER.toDto(serviceTypes);
    }
}
