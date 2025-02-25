package com.nicolas.Lavadero.dto.response;

import com.nicolas.Lavadero.model.Vehicle;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class ClientDTO {
    private long id;
    private String name;
    private String email;
    private String phoneNumber;
    private List<Vehicle> vehicles;
}
