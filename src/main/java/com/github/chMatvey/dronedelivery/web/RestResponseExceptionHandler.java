package com.github.chMatvey.dronedelivery.web;

import com.github.chMatvey.dronedelivery.service.exception.*;
import com.github.chMatvey.dronedelivery.web.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MedicationImageNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public void handleMedicationImageNotFoundException(MedicationImageNotFoundException e) {
        log.warn(e.getMessage());
    }

    @ExceptionHandler({
            DeliveryNotFoundException.class,
            DroneNotFoundException.class,
            MedicationNotFoundException.class
    })
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFoundException(RuntimeException e) {
        log.warn(e.getMessage());
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(MedicationsWeightOverTheLimitException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleMedicationsWeightOverTheLimitException(MedicationsWeightOverTheLimitException e) {
        log.warn(e.getMessage());
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(UnexpectedDroneStatusException.class)
    @ResponseStatus(CONFLICT)
    @ResponseBody
    public ErrorResponse handleUnexpectedDroneStatusException(UnexpectedDroneStatusException e) {
        log.warn(e.getMessage());
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
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
