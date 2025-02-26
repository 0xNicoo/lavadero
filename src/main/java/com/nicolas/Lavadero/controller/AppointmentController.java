package com.nicolas.Lavadero.controller;

import com.nicolas.Lavadero.dto.request.AppointmentDTOIn;
import com.nicolas.Lavadero.dto.response.AppointmentDTO;
import com.nicolas.Lavadero.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@Tag(name = "Appointment", description = "Appointment endpoints")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Operation(summary = "Create appointment")
    @PostMapping
    public ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentDTOIn appointmentDTOIn){
        AppointmentDTO response = appointmentService.create(appointmentDTOIn);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get appointment by id")
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> get(@PathVariable Long id){
        AppointmentDTO response = appointmentService.get(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all appointments")
    @GetMapping("/all")
    public ResponseEntity<List<AppointmentDTO>> getAll(){
        List<AppointmentDTO> response = appointmentService.getAll();
        return ResponseEntity.ok(response);
    }

}
