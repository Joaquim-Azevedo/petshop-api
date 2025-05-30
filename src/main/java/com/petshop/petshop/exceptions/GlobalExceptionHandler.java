package com.petshop.petshop.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ElementNotFound.class)
    public ResponseEntity<?> handleElementNotFound(ElementNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.NOT_FOUND,
                "error", "Recurso não encontrado",
                "message", ex.getMessage()
            )
        );
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<?> handleInvalidArgument(InvalidArgumentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.BAD_REQUEST,
                "error", "Argumento inválido",
                "message", ex.getMessage()
            )
        );
    }

    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<?> globalExceptionHandler(Exception ex) {
    //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
    //         Map.of(
    //             "timestamp", LocalDateTime.now(),
    //             "status", HttpStatus.INTERNAL_SERVER_ERROR,
    //             "error", "Erro interno",
    //             "message", ex.getMessage()
    //         )
    //     );
    // }
}
