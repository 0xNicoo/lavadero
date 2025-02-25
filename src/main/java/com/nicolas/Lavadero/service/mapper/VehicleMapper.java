package com.nicolas.Lavadero.service.mapper;

import com.nicolas.Lavadero.dto.request.VehicleDTOIn;
import com.nicolas.Lavadero.dto.response.VehicleDTO;
import com.nicolas.Lavadero.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleMapper extends EntityMapper<VehicleDTO, VehicleDTOIn, Vehicle>{
    VehicleMapper MAPPER = Mappers.getMapper(VehicleMapper.class);
}
