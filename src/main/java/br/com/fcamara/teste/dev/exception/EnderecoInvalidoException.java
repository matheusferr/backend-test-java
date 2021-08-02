package br.com.fcamara.teste.dev.exception;

public class EnderecoInvalidoException extends RuntimeException{
    public EnderecoInvalidoException() {
        super("Erro ao validar endereço: número inválido");
    }
}
