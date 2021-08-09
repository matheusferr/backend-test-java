package br.com.fcamara.teste.dev.exception;

public class OperacaoInvalidaException extends RuntimeException {
	public OperacaoInvalidaException(String message) {
		super(String.format("Operação inválida: %s", message));
	}
}
