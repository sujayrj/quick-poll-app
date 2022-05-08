package com.jeppu.dto.error;

public class ValidateError {
    private String code;
    private String message;

    public ValidateError() {
    }

    public ValidateError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
