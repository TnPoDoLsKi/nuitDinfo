package com.example.nuitdinfo.exception;

import com.example.nuitdinfo.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomRestControllerAdvice {
    @ExceptionHandler(BadIdException.class)
    public ResponseEntity<?> badIdExceptionHandler(BadIdException badIdException){
        return ResponseEntity
                .badRequest()
                .body(new ResponseDTO(badIdException.getMessage(),
                        "BadIdException",
                        400));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequestException(BadRequestException badRequestException){
        return ResponseEntity
                .badRequest()
                .body(new ResponseDTO(badRequestException.getMessage(),
                        "BadRequestException",
                        400));
    }

}
