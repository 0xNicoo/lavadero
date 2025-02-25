package com.nicolas.Lavadero.service.impl;

import com.nicolas.Lavadero.dto.request.ClientDTOIn;
import com.nicolas.Lavadero.dto.response.ClientDTO;
import com.nicolas.Lavadero.model.Client;
import com.nicolas.Lavadero.repository.ClientRepository;
import com.nicolas.Lavadero.service.ClientService;
import com.nicolas.Lavadero.service.mapper.ClientMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDTO create(ClientDTOIn clientDTOIn) {
        //TODO: Validar mail unico
        Client client = ClientMapper.MAPPER.toEntity(clientDTOIn);
        client = clientRepository.save(client);
        return ClientMapper.MAPPER.toDto(client);
    }

    @Override
    public ClientDTO get(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()){
            //TODO: Agregar manejo excepciones
        }
        return ClientMapper.MAPPER.toDto(clientOptional.get());
    }

    @Override
    public List<ClientDTO> getAll() {
        List<Client> clients = clientRepository.findAll();
        return ClientMapper.MAPPER.toDto(clients);
    }
}
