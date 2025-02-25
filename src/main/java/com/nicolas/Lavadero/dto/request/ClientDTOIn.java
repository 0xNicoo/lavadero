package com.nicolas.Lavadero.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientDTOIn {

    @NotNull(message = "Debe ingresar el nombre del cliente")
    private String name;

    @NotEmpty(message = "Debe ingresar un mail")
    private String email;
    private String phoneNumber;
}
