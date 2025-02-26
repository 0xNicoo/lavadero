package com.nicolas.Lavadero.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDTOIn {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    @Schema(
            type = "string",
            pattern = "dd/MM/yyyy HH:mm"
    )
    private LocalDateTime date;

    @NotNull
    private Long serviceTypeId;
}
