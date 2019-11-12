package com.ironijunior.energyconsumption.controller;

import com.ironijunior.energyconsumption.dto.ErrorResponseDTO;
import com.ironijunior.energyconsumption.exception.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Locale;

public class ControllerExceptionHandlerTest {

    private ControllerExceptionHandler handler = new ControllerExceptionHandler();

    @Test
    public void entityNotFoundExceptionTest() {
        ErrorResponseDTO error = handler.handleEntityNotFoundException(
                new EntityNotFoundException("abc"), Locale.getDefault());

        ErrorResponseDTO expected = ErrorResponseDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("There is no data with the identifier abc.")
                .build();

        Assertions.assertEquals(expected, error);
    }

    @Test
    public void unexpectedExceptionTest() {
        ErrorResponseDTO error = handler.handleUnexpectedException(
                new Exception("any message"), Locale.getDefault());

        ErrorResponseDTO expected = ErrorResponseDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("any message")
                .build();

        Assertions.assertEquals(expected, error);
    }

}
