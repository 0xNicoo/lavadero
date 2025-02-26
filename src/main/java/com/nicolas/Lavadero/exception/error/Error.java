package com.nicolas.Lavadero.exception.error;

public enum Error implements ErrorCode {

    CLIENT_NOT_FOUND("0001", "No se encontro el cliente"),
    CLIENT_EMAIL_ALREADY_EXIST("0002", "Ya existe un cliente con ese mail"),
    VEHICLE_NOT_FOUND("1001", "No se encontro el vehiculo"),
    VEHICLE_ALREADY_ASSIGN_TO_CLIENT("1002", "El vehiculo ya se asigno al cliente"),
    APPOINTMENT_INVALID_DATE("2001", "Fecha invalida para el turno"),
    APPOINTMENT_NOT_FOUND("2002", "No se encontro el turno"),
    APPOINTMENT_ALREADY_TAKEN("2003", "El turno ya esta asignado a otro vehiculo"),
    APPOINTMENT_ALREADY_PAID("2004", "El turno ya esta pago"),
    APPOINTMENT_NOT_FINISH("2005", "El turno aun no se completo"),
    SERVICE_TYPE_NOT_FOUND("3001", "No se encontro el tipo de servicio");
    private final String code;

    private final String message;

    Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return code;
    }

    @Override
    public String getCode() {
        return message;
    }
}
