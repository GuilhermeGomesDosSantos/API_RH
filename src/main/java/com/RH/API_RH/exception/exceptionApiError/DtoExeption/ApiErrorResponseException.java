package com.RH.API_RH.exception.exceptionApiError.DtoExeption;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiErrorResponseException(LocalDateTime timesTamp, HttpStatus status, String error, String message) {

}
