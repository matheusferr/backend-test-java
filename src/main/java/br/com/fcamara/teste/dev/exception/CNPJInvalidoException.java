package br.com.fcamara.teste.dev.exception;

public class CNPJInvalidoException extends RuntimeException {
	public CNPJInvalidoException(String message) {
		super(String.format("Erro ao validar cnpj: %s", message));
	}
}
