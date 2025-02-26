package com.nicolas.Lavadero.service.impl;

import com.nicolas.Lavadero.dto.request.PaymentDTOIn;
import com.nicolas.Lavadero.dto.response.PaymentDTO;
import com.nicolas.Lavadero.exception.custom.BadRequestException;
import com.nicolas.Lavadero.exception.error.Error;
import com.nicolas.Lavadero.model.Appointment;
import com.nicolas.Lavadero.model.AppointmentStatus;
import com.nicolas.Lavadero.model.Payment;
import com.nicolas.Lavadero.repository.AppointmentRepository;
import com.nicolas.Lavadero.repository.PaymentRepository;
import com.nicolas.Lavadero.service.AppointmentService;
import com.nicolas.Lavadero.service.PaymentService;
import com.nicolas.Lavadero.service.mapper.PaymentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final AppointmentRepository appointmentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, AppointmentRepository appointmentRepository) {
        this.paymentRepository = paymentRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public PaymentDTO create(PaymentDTOIn paymentDTOIn) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(paymentDTOIn.getAppointmentId());
        if(appointmentOptional.isEmpty()){
            throw new BadRequestException(Error.APPOINTMENT_NOT_FOUND);
        }
        Appointment appointment = appointmentOptional.get();
        if(appointment.getPayment() != null){
            throw new BadRequestException(Error.APPOINTMENT_ALREADY_PAID);
        }
        if(!appointment.getStatus().equals(AppointmentStatus.FINISHED)){
            throw new BadRequestException(Error.APPOINTMENT_NOT_FINISH);
        }
        Payment payment = Payment.builder()
                .amount(paymentDTOIn.getAmount())
                .appointment(appointment)
                .build();
        payment = paymentRepository.save(payment);
        return PaymentMapper.MAPPER.toDto(payment);
    }

    @Override
    public List<PaymentDTO> getAll() {
        List<Payment> payments = paymentRepository.findAll();
        return PaymentMapper.MAPPER.toDto(payments);
    }
}
