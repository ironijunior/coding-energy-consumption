package com.ironijunior.energyconsumption.controller;

import com.ironijunior.energyconsumption.dto.ErrorResponseDTO;
import com.ironijunior.energyconsumption.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

/**
 * Class responsible for generating the error response in case of exceptions.
 * It transforms the exception in a {@link ErrorResponseDTO} object.
 *
 * @author Ironi Junior Medina
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * Handle the {@link EntityNotFoundException} exception and transform to
     * {@link ErrorResponseDTO}.
     *
     * @param ex {@link EntityNotFoundException} class
     * @param locale {@link Locale} object. Could be used to internationalization
     * @return {@link ErrorResponseDTO}
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDTO handleEntityNotFoundException(EntityNotFoundException ex, Locale locale) {
        return createAndLogErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle the {@link Exception} exception and transform to
     * {@link ErrorResponseDTO}.
     *
     * @param ex {@link Exception} class
     * @param locale {@link Locale} object. Could be used to internationalization
     * @return {@link ErrorResponseDTO}
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseDTO handleUnexpectedException(Exception ex, Locale locale) {
        return createAndLogErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponseDTO createAndLogErrorResponse(Exception ex, HttpStatus status) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .status(status.value())
                .message(ex.getMessage())
                .build();
        logger.error(error.getMessage(), error);
        return error;
    }

}
