package com.nicolas.Lavadero.service.mapper;

import com.nicolas.Lavadero.dto.request.ServiceTypeDTOIn;
import com.nicolas.Lavadero.dto.response.ServiceTypeDTO;
import com.nicolas.Lavadero.model.ServiceType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceTypeMapper extends EntityMapper<ServiceTypeDTO, ServiceTypeDTOIn, ServiceType>{
    ServiceTypeMapper MAPPER = Mappers.getMapper(ServiceTypeMapper.class);
}
