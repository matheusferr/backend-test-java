package br.com.fcamara.teste.dev.exception;

public class PlacaInvalidaException extends RuntimeException{
    public PlacaInvalidaException() {
        super("Erro ao validar placa: formato inválido");
    }
}
