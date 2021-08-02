package br.com.fcamara.teste.dev.exception;

public class DadosInvalidosException extends RuntimeException{
    public DadosInvalidosException(String message) {
        super(String.format("Dados inválidos: %s", message));
    }
}
