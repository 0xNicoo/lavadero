package com.nicolas.Lavadero.dto.response;

import com.nicolas.Lavadero.model.Appointment;
import com.nicolas.Lavadero.model.VehicleType;
import lombok.Data;

import java.util.List;

@Data
public class VehicleDTO {
    private long id;
    private String model;
    private String registration;
    private VehicleType vehicleType;
    private ClientSummaryDTO client;
    private List<Appointment> appointments;
}
