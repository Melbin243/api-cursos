package com.api_cursos.common.exception;

import com.api_cursos.common.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException ex,
                                                           HttpServletRequest request) {

        int httpStatus = HttpStatus.NOT_FOUND.value();

        ApiError apiError = new ApiError(
                httpStatus,
                request.getRequestURI(),
                request.getMethod(),
                "Lo siento, no se pudo encontrar la información solicitada." +
                "Por favor, revise la URL o intente otra búsqueda.",
                ex.getMessage(),
                LocalDateTime.now(),
                null
        );

        return ResponseEntity.status(httpStatus).body(apiError);
    }
}
