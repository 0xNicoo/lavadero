package com.nicolas.Lavadero.exception.error;

public enum Error implements ErrorCode {

    CLIENT_NOT_FOUND("0001", "No se encontro el cliente"),
    CLIENT_EMAIL_ALREADY_EXIST("0002", "Ya existe un cliente con ese mail");
    private final String code;

    private final String message;

    Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public String getCode() {
        return null;
    }
}
