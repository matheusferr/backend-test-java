package br.com.fcamara.teste.dev.controller.advice;

import br.com.fcamara.teste.dev.dto.ErroDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DataIntegrityViolationHandler {
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErroDto> handle() {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				new ErroDto("Erro ao criar recurso: campos providenciados não são únicos")
		);
	}
}
