package com.nicolas.Lavadero.service.impl;

import com.nicolas.Lavadero.dto.request.AppointmentDTOIn;
import com.nicolas.Lavadero.dto.response.AppointmentDTO;
import com.nicolas.Lavadero.exception.custom.BadRequestException;
import com.nicolas.Lavadero.exception.error.Error;
import com.nicolas.Lavadero.model.Appointment;
import com.nicolas.Lavadero.model.ServiceType;
import com.nicolas.Lavadero.repository.AppointmentRepository;
import com.nicolas.Lavadero.repository.ServiceTypeRepository;
import com.nicolas.Lavadero.service.AppointmentService;
import com.nicolas.Lavadero.service.mapper.AppointmentMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ServiceTypeRepository serviceTypeRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  ServiceTypeRepository serviceTypeRepository) {
        this.appointmentRepository = appointmentRepository;
        this.serviceTypeRepository = serviceTypeRepository;
    }

    @Override
    public AppointmentDTO create(AppointmentDTOIn appointmentDTOIn) {
        Optional<ServiceType> serviceTypeOptional = serviceTypeRepository.findById(appointmentDTOIn.getServiceTypeId());
        if(serviceTypeOptional.isEmpty()){
            throw new BadRequestException(Error.SERVICE_TYPE_NOT_FOUND);
        }
        if(appointmentDTOIn.getDate().isBefore(LocalDateTime.now())){
            throw new BadRequestException(Error.APPOINTMENT_INVALID_DATE);
        }
        ServiceType serviceType = serviceTypeOptional.get();
        Appointment appointment = AppointmentMapper.MAPPER.toEntity(appointmentDTOIn);
        appointment.setServiceType(serviceType);
        appointment = appointmentRepository.save(appointment);
        return AppointmentMapper.MAPPER.toDto(appointment);
    }

    @Override
    public AppointmentDTO get(Long id) {
        Appointment appointment = getAppointmentById(id);
        return AppointmentMapper.MAPPER.toDto(appointment);
    }

    @Override
    public List<AppointmentDTO> getAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return AppointmentMapper.MAPPER.toDto(appointments);
    }

    private Appointment getAppointmentById(Long id){
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if(appointmentOptional.isEmpty()){
            throw new BadRequestException(Error.APPOINTMENT_NOT_FOUND);
        }
        return appointmentOptional.get();
    }
}
