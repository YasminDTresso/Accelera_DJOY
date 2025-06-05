package com.djoy.accelera.Infra.exceptions;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * A classe GlobalExceptionHandler customiza exceções HTTP 
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    // Formata os erros de recurso não encontrado 404 (Not found)
    // Essas exceções geralmente são lançadas pelo usuário quando ele acessa uma rota que não existe na aplicação.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("erro", ex.getMessage()));
    }

    // Formata os erros de requisição mal formatada 400 (Bad request). 
    // Essas exceções geralmente são lançadas pelo usuário quando ele manda uma requisição com formato errado.
    // Por exemplo, quando uma rota pede o campo "nome" no body e essa informação não vem.
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap("erro", ex.getMessage()));
    }

    // Formata os erros de servidor 500 (Internal server error)
    // Essas exceções são lançadas pelo servidor, possivelmente por conta de erros de programação ou de configuração
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("erro", ex.getMessage()));
    }
}