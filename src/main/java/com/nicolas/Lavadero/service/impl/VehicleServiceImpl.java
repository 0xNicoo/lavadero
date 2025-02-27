package com.nicolas.Lavadero.service.impl;

import com.nicolas.Lavadero.dto.request.AssociateAppointmentDTO;
import com.nicolas.Lavadero.dto.request.VehicleAttributeDTOIn;
import com.nicolas.Lavadero.dto.request.VehicleDTOIn;
import com.nicolas.Lavadero.dto.response.VehicleDTO;
import com.nicolas.Lavadero.exception.custom.BadRequestException;
import com.nicolas.Lavadero.exception.error.Error;
import com.nicolas.Lavadero.model.Appointment;
import com.nicolas.Lavadero.model.Vehicle;
import com.nicolas.Lavadero.model.VehicleAttribute;
import com.nicolas.Lavadero.repository.AppointmentRepository;
import com.nicolas.Lavadero.repository.VehicleAttributeRepository;
import com.nicolas.Lavadero.repository.VehicleRepository;
import com.nicolas.Lavadero.service.VehicleService;
import com.nicolas.Lavadero.service.mapper.VehicleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    private final AppointmentRepository appointmentRepository;

    private final VehicleAttributeRepository vehicleAttributeRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository,
                              AppointmentRepository appointmentRepository,
                              VehicleAttributeRepository vehicleAttributeRepository) {
        this.vehicleRepository = vehicleRepository;
        this.appointmentRepository = appointmentRepository;
        this.vehicleAttributeRepository = vehicleAttributeRepository;
    }

    @Override
    public VehicleDTO create(VehicleDTOIn vehicleDTOIn) {
        Vehicle vehicle = VehicleMapper.MAPPER.toEntity(vehicleDTOIn);
        vehicle = vehicleRepository.save(vehicle);
        return VehicleMapper.MAPPER.toDto(vehicle);
    }

    @Override
    public VehicleDTO get(Long id) {
        Vehicle vehicle = getVehicleById(id);
        return VehicleMapper.MAPPER.toDto(vehicle);
    }

    @Override
    public List<VehicleDTO> getAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return VehicleMapper.MAPPER.toDto(vehicles);
    }

    @Transactional
    @Override
    public void assignAppointment(Long vehicleId, AssociateAppointmentDTO associateAppointmentDTO) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(associateAppointmentDTO.getAppointmentId());
        if(appointmentOptional.isEmpty()){
            throw new BadRequestException(Error.APPOINTMENT_NOT_FOUND);
        }
        Appointment appointment = appointmentOptional.get();
        if(appointment.getVehicle() != null){
            throw new BadRequestException(Error.APPOINTMENT_ALREADY_TAKEN);
        }
        Vehicle vehicle = getVehicleById(vehicleId);
        vehicle.getAppointments().add(appointment);
        appointment.setVehicle(vehicle);
        vehicleRepository.save(vehicle);
        appointmentRepository.save(appointment);
    }

    @Override
    public VehicleDTO addAttribute(Long vehicleId, VehicleAttributeDTOIn attributesDTO) {
        Vehicle vehicle = getVehicleById(vehicleId);
        VehicleAttribute vehicleAttribute = VehicleAttribute.builder()
                .attributeName(attributesDTO.getAttributeName())
                .attributeValue(attributesDTO.getAttributeValue())
                .build();
        vehicleAttribute.setVehicle(vehicle);
        vehicleAttribute = vehicleAttributeRepository.save(vehicleAttribute);
        vehicle.getAttributes().add(vehicleAttribute);
        vehicleRepository.save(vehicle);
        return VehicleMapper.MAPPER.toDto(vehicle);
    }

    private Vehicle getVehicleById(Long id){
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        if(vehicleOptional.isEmpty()){
            throw new BadRequestException(Error.VEHICLE_NOT_FOUND);
        }
        return vehicleOptional.get();
    }
}
