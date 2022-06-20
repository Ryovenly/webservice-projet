package com.akane.j2eetd.dto;

import java.beans.JavaBean;
import java.io.Serializable;
import java.util.Map;

@JavaBean
public class ValidationError {
    String errorMessage;
    Map<String, String> fieldErrors;

    public ValidationError(){}

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
