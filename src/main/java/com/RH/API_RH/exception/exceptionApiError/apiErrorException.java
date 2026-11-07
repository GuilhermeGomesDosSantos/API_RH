package com.RH.API_RH.exception.exceptionApiError;

import com.RH.API_RH.exception.exceptionApiError.DtoExeption.ApiErrorResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class apiErrorException {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiErrorResponseException> error_500_noSuchElement(NoSuchElementException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorResponseException(LocalDateTime.now(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiErrorResponseException> error_404_notFound(NoResourceFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorResponseException(LocalDateTime.now(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage() + " / Endpoint não encontrado!"));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiErrorResponseException> error_405_methodNotSupported(HttpRequestMethodNotSupportedException exception){
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ApiErrorResponseException(LocalDateTime.now(), HttpStatus.METHOD_NOT_ALLOWED, HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), exception.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponseException> error_400_badRequest_messsageNotReadable(HttpMessageNotReadableException exception){
        String message;
        if (exception.getMessage().contains("Required request body is missing")) {
            message = "Required request body is missing / O Corpo da requisição está vázio!";
        } else {
            message = "O corpo da requisição está mal formatado!";
        }
        System.out.println(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiErrorResponseException(LocalDateTime.now(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), message));
    }
}
