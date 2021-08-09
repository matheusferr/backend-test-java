package br.com.fcamara.teste.dev.controller.advice;

import br.com.fcamara.teste.dev.dto.ErroFormDto;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationHandler {
	private final MessageSource messageSource;

	public ValidationHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErroFormDto>> handle(MethodArgumentNotValidException e) {
		List<ErroFormDto> errosFormulario = new ArrayList<>();

		List<FieldError> errosCampos = e.getBindingResult().getFieldErrors();

		errosCampos.forEach(fieldError -> {
			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			errosFormulario.add(new ErroFormDto(fieldError.getField(), message));
		});

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errosFormulario);
	}
}
