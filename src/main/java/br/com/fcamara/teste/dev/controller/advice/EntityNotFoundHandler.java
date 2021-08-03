package br.com.fcamara.teste.dev.controller.advice;

import br.com.fcamara.teste.dev.dto.ErroDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class EntityNotFoundHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroDto> handle() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroDto("Recuso não encontrado"));
    }
}
