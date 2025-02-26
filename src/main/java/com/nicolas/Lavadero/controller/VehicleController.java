package com.nicolas.Lavadero.controller;

import com.nicolas.Lavadero.dto.request.AssociateAppointmentDTO;
import com.nicolas.Lavadero.dto.request.VehicleDTOIn;
import com.nicolas.Lavadero.dto.response.VehicleDTO;
import com.nicolas.Lavadero.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
@Tag(name = "Vehicle", description = "Vehicle endpoints")
public class VehicleController {
    private final VehicleService vehicleService;


    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Operation(summary = "Create vehicle")
    @PostMapping
    public ResponseEntity<VehicleDTO> create(@RequestBody VehicleDTOIn vehicleDTOIn){
        VehicleDTO response = vehicleService.create(vehicleDTOIn);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Associate appointment to a vehicle")
    @PostMapping("/{vehicleId}/appointments")
    public ResponseEntity<String> associate(@PathVariable Long vehicleId, @RequestBody AssociateAppointmentDTO associateAppointmentDTO){
        vehicleService.assignAppointment(vehicleId, associateAppointmentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
