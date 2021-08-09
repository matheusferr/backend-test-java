package br.com.fcamara.teste.dev.controller.advice;

import br.com.fcamara.teste.dev.dto.ErroDto;
import br.com.fcamara.teste.dev.exception.OperacaoInvalidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OperacaoInvalidaHandler {
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(OperacaoInvalidaException.class)
	public ResponseEntity<ErroDto> handle(OperacaoInvalidaException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDto(e.getMessage()));
	}
}
