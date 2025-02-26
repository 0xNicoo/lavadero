package com.nicolas.Lavadero.service.impl;

import com.nicolas.Lavadero.dto.request.AssociateVehicleDTO;
import com.nicolas.Lavadero.dto.request.ClientDTOIn;
import com.nicolas.Lavadero.dto.response.ClientDTO;
import com.nicolas.Lavadero.exception.custom.BadRequestException;
import com.nicolas.Lavadero.exception.error.Error;
import com.nicolas.Lavadero.model.Client;
import com.nicolas.Lavadero.model.Vehicle;
import com.nicolas.Lavadero.repository.ClientRepository;
import com.nicolas.Lavadero.repository.VehicleRepository;
import com.nicolas.Lavadero.service.ClientService;
import com.nicolas.Lavadero.service.mapper.ClientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final VehicleRepository vehicleRepository;

    public ClientServiceImpl(ClientRepository clientRepository, VehicleRepository vehicleRepository) {
        this.clientRepository = clientRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public ClientDTO create(ClientDTOIn clientDTOIn) {
        Optional<Client> clientOptional = clientRepository.findByEmail(clientDTOIn.getEmail());
        if(clientOptional.isPresent()){
            throw new BadRequestException(Error.CLIENT_EMAIL_ALREADY_EXIST);
        }
        Client client = ClientMapper.MAPPER.toEntity(clientDTOIn);
        client = clientRepository.save(client);
        return ClientMapper.MAPPER.toDto(client);
    }

    @Override
    public ClientDTO get(Long id) {
        Client client = getClientById(id);
        return ClientMapper.MAPPER.toDto(client);
    }

    @Override
    public List<ClientDTO> getAll() {
        List<Client> clients = clientRepository.findAll();
        return ClientMapper.MAPPER.toDto(clients);
    }

    @Transactional
    @Override
    public void assignVehicle(Long clientId, AssociateVehicleDTO associateVehicleDTO) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(associateVehicleDTO.getVehicleId());
        if(vehicleOptional.isEmpty()){
            throw new BadRequestException(Error.VEHICLE_NOT_FOUND);
        }
        Vehicle vehicle = vehicleOptional.get();
        Client client = getClientById(clientId);
        if(vehicle.getClient() != null && vehicle.getClient().getId() == client.getId()){
            throw new BadRequestException(Error.VEHICLE_ALREADY_ASSIGN_TO_CLIENT);
        }
        client.getVehicles().add(vehicle);
        vehicle.setClient(client);
        clientRepository.save(client);
        vehicleRepository.save(vehicle);
    }

    private Client getClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()){
            throw new BadRequestException(Error.CLIENT_NOT_FOUND);
        }
        return clientOptional.get();
    }
}
