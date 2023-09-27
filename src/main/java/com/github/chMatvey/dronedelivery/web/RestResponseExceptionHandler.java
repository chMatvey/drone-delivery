package com.github.chMatvey.dronedelivery.web;

import com.github.chMatvey.dronedelivery.exception.MedicationImageNotFoundException;
import com.github.chMatvey.dronedelivery.web.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Slf4j
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({MedicationImageNotFoundException.class})
    @ResponseStatus(NOT_FOUND)
    public void handleNotFoundException(RuntimeException e) {
        log.warn(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handleDefault(Exception e) {
        log.error(e.getMessage(), e);
        return new ErrorResponse(INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        log.warn(e.getMessage());
        return super.handleExceptionInternal(e, body, headers, statusCode, request);
    }
}
