package com.tiffinservice.tiffin_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationException(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        Map<String, Object> response = new HashMap<>();

        response.put("message", "Validation failed");
        response.put("errors", errors);

        return response;
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleCustomerNotFound(
            CustomerNotFoundException exception) {

        return createErrorResponse(
                exception.getMessage()
        );
    }

    @ExceptionHandler(SubscriptionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleSubscriptionNotFound(
            SubscriptionNotFoundException exception) {

        return createErrorResponse(
                exception.getMessage()
        );
    }

    @ExceptionHandler(LeaveException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleLeaveException(
            LeaveException exception) {

        return createErrorResponse(
                exception.getMessage()
        );
    }

    @ExceptionHandler(PaymentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handlePaymentException(
            PaymentException exception) {

        return createErrorResponse(
                exception.getMessage()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleRuntimeException(
            RuntimeException exception) {

        return createErrorResponse(
                exception.getMessage()
        );
    }

    private Map<String, String> createErrorResponse(
            String message) {

        Map<String, String> response = new HashMap<>();

        response.put("message", message);

        return response;
    }
}