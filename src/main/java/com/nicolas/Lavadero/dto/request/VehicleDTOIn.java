package com.nicolas.Lavadero.dto.request;

import com.nicolas.Lavadero.model.VehicleType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class VehicleDTOIn {
    private String model;
    @NotEmpty(message = "Debe ingresar una matricula")
    private String registration;
    private VehicleType vehicleType;
}
