package com.jeppu.dto.error;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetail {
    private String title;
    private int status;
    private String detail;
    private long timeStamp;
    private String developerMessage;

    private Map<String, List<ValidateError>> errors = new LinkedHashMap<>();

    public ErrorDetail() {
    }

    public ErrorDetail(String title, int status, String detail, long timeStamp, String developerMessage, Map<String, List<ValidateError>> fieldErrors) {
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.timeStamp = timeStamp;
        this.developerMessage = developerMessage;
        this.errors = fieldErrors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public Map<String, List<ValidateError>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<ValidateError>> errors) {
        this.errors = errors;
    }
}
