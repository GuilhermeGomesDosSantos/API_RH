package com.RH.API_RH.exception.exceptionApiError.DtoExeption;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public record ApiErrorResponseExceptionCadastrar(LocalDateTime timesTamp, HttpStatus httpStatus, String error, Map<String, String> messages) {
}
