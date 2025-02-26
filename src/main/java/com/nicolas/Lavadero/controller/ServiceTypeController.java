package com.nicolas.Lavadero.controller;

import com.nicolas.Lavadero.dto.request.ClientDTOIn;
import com.nicolas.Lavadero.dto.request.ServiceTypeDTOIn;
import com.nicolas.Lavadero.dto.response.ClientDTO;
import com.nicolas.Lavadero.dto.response.ServiceTypeDTO;
import com.nicolas.Lavadero.service.ServiceTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-type")
@Tag(name = "Service Type", description = "Service Type endpoints")
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    public ServiceTypeController(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @Operation(summary = "Create service type")
    @PostMapping
    public ResponseEntity<ServiceTypeDTO> create(@RequestBody ServiceTypeDTOIn serviceTypeDTOIn){
        ServiceTypeDTO response = serviceTypeService.create(serviceTypeDTOIn);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Get all service type")
    @GetMapping("/all")
    public ResponseEntity<List<ServiceTypeDTO>> getAll(){
        List<ServiceTypeDTO> response = serviceTypeService.getAll();
        return ResponseEntity.ok(response);
    }
}
