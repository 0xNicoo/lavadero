package com.nicolas.Lavadero.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicolas.Lavadero.dto.request.AssociateVehicleDTO;
import com.nicolas.Lavadero.dto.request.ClientDTOIn;
import com.nicolas.Lavadero.dto.response.ClientDTO;
import com.nicolas.Lavadero.exception.GlobalExceptionHandler;
import com.nicolas.Lavadero.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private ClientServiceImpl clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    public void setUp(){
        clientController = new ClientController(clientService);
        mockMvc = MockMvcBuilders
                .standaloneSetup(clientController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void create_success_test() throws Exception {
        //Given
        ClientDTOIn clientDTOIn = new ClientDTOIn();
        clientDTOIn.setEmail("test@example.com");
        clientDTOIn.setName("test");
        clientDTOIn.setPhoneNumber("123456");

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setEmail("test@example.com");
        clientDTO.setName("test");
        clientDTO.setPhoneNumber("123456");

        //When
        when(clientService.create(any(ClientDTOIn.class))).thenReturn(clientDTO);
        final ResultActions result = mockMvc.perform(post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDTOIn)));

        //Then
        result.andExpect(status().isOk()).andExpect(jsonPath("$.email").value("test@example.com"));
        verify(clientService, times(1)).create(any(ClientDTOIn.class));
    }

    @Test
    public void get_success_test() throws Exception {
        //Given
        Long clientId = 1L;
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(clientId);
        clientDTO.setEmail("test@example.com");

        //When
        when(clientService.get(clientId)).thenReturn(clientDTO);
        final ResultActions result = mockMvc.perform(get("/client/" + clientId));

        //Then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientId))
                .andExpect(jsonPath("$.email").value("test@example.com"));
        verify(clientService, times(1)).get(clientId);
    }

    @Test
    public void get_all_success_test() throws Exception {
        //Given
        ClientDTO clientDTO1 = new ClientDTO();
        clientDTO1.setId(1L);
        clientDTO1.setEmail("a@example.com");

        ClientDTO clientDTO2 = new ClientDTO();
        clientDTO2.setId(2L);
        clientDTO2.setEmail("b@example.com");

        List<ClientDTO> clientDTOList = Arrays.asList(clientDTO1, clientDTO2);

        //When
        when(clientService.getAll()).thenReturn(clientDTOList);
        final ResultActions result = mockMvc.perform(get("/client/all"));

        //Then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].email").value("a@example.com"))
                .andExpect(jsonPath("$[1].email").value("b@example.com"));

        verify(clientService, times(1)).getAll();
    }


    @Test
    public void associate_success_test() throws Exception {
        //Given
        Long clientId = 1L;
        AssociateVehicleDTO associateVehicleDTO = new AssociateVehicleDTO();
        associateVehicleDTO.setVehicleId(10L);

        //When
        doNothing().when(clientService).assignVehicle(eq(clientId), any(AssociateVehicleDTO.class));
        final ResultActions result = mockMvc.perform(post("/client/" + clientId + "/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(associateVehicleDTO)));

        //Then
        result.andExpect(status().isOk());
        verify(clientService, times(1)).assignVehicle(eq(clientId), any(AssociateVehicleDTO.class));
    }
}
