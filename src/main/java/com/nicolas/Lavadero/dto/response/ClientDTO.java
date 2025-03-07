package com.nicolas.Lavadero.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ClientDTO {
    private long id;
    private String name;
    private String email;
    private String phoneNumber;
    private List<VehicleDTO> vehicles;
}
