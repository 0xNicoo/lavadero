package com.nicolas.Lavadero.service;

import com.nicolas.Lavadero.dto.request.ClientDTOIn;
import com.nicolas.Lavadero.dto.response.ClientDTO;

import java.util.List;

public interface ClientService {
    ClientDTO create(ClientDTOIn clientDTOIn);

    ClientDTO get(Long id);

    List<ClientDTO> getAll();
}
