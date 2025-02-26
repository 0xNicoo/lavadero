package com.nicolas.Lavadero.service.mapper;

import com.nicolas.Lavadero.dto.request.AppointmentDTOIn;
import com.nicolas.Lavadero.dto.response.AppointmentDTO;
import com.nicolas.Lavadero.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppointmentMapper extends EntityMapper<AppointmentDTO, AppointmentDTOIn, Appointment>{
    AppointmentMapper MAPPER = Mappers.getMapper(AppointmentMapper.class);
}
