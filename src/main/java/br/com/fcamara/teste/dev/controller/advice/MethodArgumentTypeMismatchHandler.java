package br.com.fcamara.teste.dev.controller.advice;

import br.com.fcamara.teste.dev.dto.ErroDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class MethodArgumentTypeMismatchHandler {
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErroDto> handle(MethodArgumentTypeMismatchException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDto(
				String.format("Tipo inválido para parâmetro '%s'", e.getName())
		));
	}
}
