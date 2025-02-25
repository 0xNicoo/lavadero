package com.nicolas.Lavadero.controller;

import com.nicolas.Lavadero.dto.request.AssociateVehicleDTO;
import com.nicolas.Lavadero.dto.request.ClientDTOIn;
import com.nicolas.Lavadero.dto.response.ClientDTO;
import com.nicolas.Lavadero.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@Tag(name = "Client", description = "Client endpoints")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Create client")
    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTOIn clientDTOIn){
        ClientDTO response = clientService.create(clientDTOIn);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get client by id")
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> get(@PathVariable Long id){
        ClientDTO response = clientService.get(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all clients")
    @GetMapping("/all")
    public ResponseEntity<List<ClientDTO>> getAll(){
        List<ClientDTO> response = clientService.getAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Associate vehicle to a client")
    @PostMapping("/{clientId}/vehicles")
    public ResponseEntity<String> associate(@PathVariable Long clientId, @RequestBody AssociateVehicleDTO associateVehicleDTO){
        clientService.assignVehicle(clientId, associateVehicleDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
