package com.training.epam.controller.exception;

import com.training.epam.controller.exception.dto.ErrorFormInfo;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorFormInfo handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        ErrorFormInfo errorDto = processFieldErrors(fieldErrors);
        return errorDto;
    }

    private ErrorFormInfo processFieldErrors(List<FieldError> fieldErrors) {
        ErrorFormInfo dto = new ErrorFormInfo();

        for (FieldError fieldError : fieldErrors) {
            String message = fieldError.getDefaultMessage();
            String field = fieldError.getField();
            dto.addFieldError(field, message);
        }

        return dto;
    }
}
