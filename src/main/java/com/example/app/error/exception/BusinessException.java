package com.example.app.error.exception;

import com.mysql.cj.exceptions.ExceptionFactory;

public class BusinessException extends AppException {
    public BusinessException(String message, String code) {
        super(message);
    }
}
