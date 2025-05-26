package com.petshop.petshop.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OwnerNotFound.class)
    public ResponseEntity<?> handleOwnerNotFound(OwnerNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.NOT_FOUND,
                "error", "Recurso não encontrado",
                "message", ex.getMessage()
            )
        );
    }

    @ExceptionHandler(AnimalNotFound.class)
    public ResponseEntity<?> handleAnimalNotFound(AnimalNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.NOT_FOUND,
                "error", "Recurso não encontrado",
                "message", ex.getMessage()
            )
        );
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.INTERNAL_SERVER_ERROR,
                "error", "Erro interno",
                "message", ex.getMessage()
            )
        );
    }
}
