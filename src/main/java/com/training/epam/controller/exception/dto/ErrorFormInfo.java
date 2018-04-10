package com.training.epam.controller.exception.dto;

import java.util.ArrayList;
import java.util.List;

public class ErrorFormInfo {
    private List<FieldErrorDTO> fieldErrors = new ArrayList<>();

    public ErrorFormInfo() {
    }

    public void addFieldError(String path, String message) {
        FieldErrorDTO error = new FieldErrorDTO(path, message);
        fieldErrors.add(error);
    }

    public List<FieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldErrorDTO> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
