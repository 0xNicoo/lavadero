package com.nicolas.Lavadero.service;

import com.nicolas.Lavadero.dto.request.PaymentDTOIn;
import com.nicolas.Lavadero.dto.response.PaymentDTO;

import java.util.List;

public interface PaymentService {
    PaymentDTO create(PaymentDTOIn paymentDTOIn);
    List<PaymentDTO> getAll();
}
