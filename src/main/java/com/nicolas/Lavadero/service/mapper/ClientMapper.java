package com.nicolas.Lavadero.service.mapper;

import com.nicolas.Lavadero.dto.request.ClientDTOIn;
import com.nicolas.Lavadero.dto.response.ClientDTO;
import com.nicolas.Lavadero.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper extends EntityMapper<ClientDTO, ClientDTOIn, Client>{
    ClientMapper MAPPER = Mappers.getMapper(ClientMapper.class);
}
