package com.nicolas.Lavadero.dto.response;

import lombok.Data;

@Data
public class VehicleAttributeDTO {
    private Long id;
    private String attributeName;
    private String attributeValue;
}
