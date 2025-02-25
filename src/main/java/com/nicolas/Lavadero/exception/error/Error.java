package com.nicolas.Lavadero.exception.error;

public enum Error implements ErrorCode {

    ;
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
