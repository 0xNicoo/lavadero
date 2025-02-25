package com.nicolas.Lavadero.exception.custom;

import com.nicolas.Lavadero.exception.error.ErrorCode;

public class BadRequestException extends ServiceException {
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BadRequestException(String code, String message) {
        super(code, message);
    }
}
