package br.com.fcamara.teste.dev.exception;

public class TelefoneInvalidoException extends RuntimeException{
    public TelefoneInvalidoException() {
        super("Erro ao validar telefone: formato inválido");
    }
}
