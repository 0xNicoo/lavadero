package com.nicolas.Lavadero.dto.response;

import lombok.Data;

@Data
public class ClientSummaryDTO {
    private long id;
    private String name;
    private String email;
    private String phoneNumber;
}
