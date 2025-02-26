package com.nicolas.Lavadero.service;

import com.nicolas.Lavadero.dto.request.AssociateVehicleDTO;
import com.nicolas.Lavadero.dto.request.ClientDTOIn;
import com.nicolas.Lavadero.dto.response.ClientDTO;
import com.nicolas.Lavadero.exception.custom.BadRequestException;
import com.nicolas.Lavadero.exception.error.Error;
import com.nicolas.Lavadero.model.Client;
import com.nicolas.Lavadero.model.Vehicle;
import com.nicolas.Lavadero.repository.ClientRepository;
import com.nicolas.Lavadero.repository.VehicleRepository;
import com.nicolas.Lavadero.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    public void setUp(){
        clientService = new ClientServiceImpl(clientRepository, vehicleRepository);
    }

    @Test
    public void create_success_test() {
        //Given
        ClientDTOIn clientDTOIn = new ClientDTOIn();
        clientDTOIn.setEmail("test@example.com");
        Client clientEntity = new Client();
        clientEntity.setEmail("test@example.com");
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setEmail("test@example.com");

        //When
        when(clientRepository.findByEmail(clientDTOIn.getEmail())).thenReturn(Optional.empty());
        when(clientRepository.save(clientEntity)).thenReturn(clientEntity);
        ClientDTO result = clientService.create(clientDTOIn);

        //Then
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    public void create_duplicate_email_test() {
        //Given
        ClientDTOIn clientDTOIn = new ClientDTOIn();
        clientDTOIn.setEmail("test@example.com");

        Client existingClient = new Client();
        existingClient.setEmail("test@example.com");

        //When
        when(clientRepository.findByEmail(clientDTOIn.getEmail())).thenReturn(Optional.of(existingClient));

        //Then
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            clientService.create(clientDTOIn);
        });
        assertEquals(Error.CLIENT_EMAIL_ALREADY_EXIST.getMessage(), exception.getMessage());
    }

    @Test
    public void get_client_by_id_test() {
        //Given
        Long clientId = 1L;
        Client client = new Client();
        client.setId(clientId);
        client.setEmail("test@example.com");

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(clientId);
        clientDTO.setEmail("get@example.com");

        //When
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        ClientDTO result = clientService.get(clientId);

        //Then
        assertNotNull(result);
        assertEquals(clientId, result.getId());
    }

    @Test
    public void get_all_client_test() {
        //Given
        Client client1 = new Client();
        client1.setId(1L);
        client1.setEmail("a@example.com");
        Client client2 = new Client();
        client2.setId(2L);
        client2.setEmail("b@example.com");
        List<Client> clients = Arrays.asList(client1, client2);

        ClientDTO clientDTO1 = new ClientDTO();
        clientDTO1.setId(1L);
        clientDTO1.setEmail("a@example.com");
        ClientDTO clientDTO2 = new ClientDTO();
        clientDTO2.setId(2L);
        clientDTO2.setEmail("b@example.com");
        List<ClientDTO> clientDTOList = Arrays.asList(clientDTO1, clientDTO2);

        //When
        when(clientRepository.findAll()).thenReturn(clients);
        List<ClientDTO> result = clientService.getAll();
        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void assign_vehicle_success_test() {
        //Given
        Long clientId = 1L;
        Long vehicleId = 10L;
        Client client = new Client();
        client.setId(clientId);
        client.setVehicles(new ArrayList<>());
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        vehicle.setClient(null);
        AssociateVehicleDTO associateVehicleDTO = new AssociateVehicleDTO();
        associateVehicleDTO.setVehicleId(vehicleId);

        //When
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        clientService.assignVehicle(clientId, associateVehicleDTO);

        // Then
        assertTrue(client.getVehicles().contains(vehicle));
        assertEquals(client, vehicle.getClient());
        assertEquals(client.getVehicles().size(), 1L);
        verify(clientRepository, times(1)).save(client);
        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    public void assign_vehicle_throw_error_vehicle_not_found_test() {
        //Given
        Long clientId = 1L;
        Long vehicleId = 10L;

        AssociateVehicleDTO associateVehicleDTO = new AssociateVehicleDTO();
        associateVehicleDTO.setVehicleId(vehicleId);

        //When
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

        //Then
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            clientService.assignVehicle(clientId, associateVehicleDTO);
        });
        assertEquals(Error.VEHICLE_NOT_FOUND.getMessage(), exception.getMessage());
    }

    @Test
    public void assign_vehicle_throw_error_vehicle_already_assigned_test(){
        //Given
        Long clientId = 1L;
        Long vehicleId = 10L;

        Client client = new Client();
        client.setId(clientId);
        client.setVehicles(new ArrayList<>());

        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        vehicle.setClient(client);

        AssociateVehicleDTO associateVehicleDTO = new AssociateVehicleDTO();
        associateVehicleDTO.setVehicleId(vehicleId);

        //When
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        //Then
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            clientService.assignVehicle(clientId, associateVehicleDTO);
        });
        assertEquals(Error.VEHICLE_ALREADY_ASSIGN_TO_CLIENT.getMessage(), exception.getMessage());
    }
}
