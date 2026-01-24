package com.api_cursos.common.exception;

import com.api_cursos.common.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

   private final ZoneId ZONE_ID = ZoneId.of("America/La_Paz");

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
                LocalDateTime.now(ZONE_ID),
                null
        );

        return ResponseEntity.status(httpStatus).body(apiError);
    }

    @ExceptionHandler(AlreadyEnrolledException.class)
    public ResponseEntity<ApiError> handleAlreadyEnrolledException(AlreadyEnrolledException ex,
                                                                   HttpServletRequest request) {
        int httpStatus = HttpStatus.CONFLICT.value();

        ApiError error = new ApiError(
                httpStatus,
                request.getRequestURI(),
                request.getMethod(),
                "El estudiante ya está inscrito en este curso.",
                ex.getMessage(),
                LocalDateTime.now(ZONE_ID),
                null
        );

        return ResponseEntity.status(httpStatus).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException ex,
                                                                          HttpServletRequest request) {

        int httpStatus = HttpStatus.BAD_REQUEST.value();

        List<FieldError> erros = ex.getBindingResult().getFieldErrors();
        List<String> details = erros.stream()
                .map(error -> error.getField() + " : " + error.getDefaultMessage()
                ).toList();

        ApiError apiError  = new ApiError(
                httpStatus,
                request.getRequestURL().toString(),
                request.getMethod(),
                "La solicitud contiene parámetros inválidos o incompletos. " +
                        "Por favor, verifique y proporcione la información requerida antes de volver a intentarlo.",
                "Validation Errors",
                LocalDateTime.now(ZONE_ID),
                details
        );

        return ResponseEntity.status(httpStatus).body(apiError);
    }

}
