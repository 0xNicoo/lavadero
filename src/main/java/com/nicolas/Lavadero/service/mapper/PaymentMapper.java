package com.nicolas.Lavadero.service.mapper;

import com.nicolas.Lavadero.dto.request.PaymentDTOIn;
import com.nicolas.Lavadero.dto.response.PaymentDTO;
import com.nicolas.Lavadero.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper extends EntityMapper<PaymentDTO, PaymentDTOIn, Payment>{
    PaymentMapper MAPPER = Mappers.getMapper(PaymentMapper.class);
}
