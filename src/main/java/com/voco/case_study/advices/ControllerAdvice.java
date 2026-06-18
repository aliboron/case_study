package com.voco.case_study.advices;

import com.voco.case_study.dtos.ErrorResponse;
import com.voco.case_study.exceptions.BadRequestException;
import com.voco.case_study.exceptions.ConflictException;
import com.voco.case_study.exceptions.DuplicateResourceException;
import com.voco.case_study.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errorMessage,
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(
            BadRequestException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request);
    }

    // 409 CONFLICT same seat on same flight
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(
            ConflictException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.CONFLICT, request);
    }

    //403 FORBIDDEN - RBAC violations
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(
            AccessDeniedException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.FORBIDDEN, request);
    }

    //401 UNAUTHORIZED - invalid JWT
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(
            AuthenticationException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.UNAUTHORIZED, request);
    }

    // 7. 500 INTERNAL SERVER ERROR
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            Exception ex, HttpStatus status, HttpServletRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateResourceException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(), 409, "Conflict", ex.getMessage(),
                request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}