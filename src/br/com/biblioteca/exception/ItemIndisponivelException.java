package br.com.biblioteca.exception;

public class ItemIndisponivelException extends Exception {
    public ItemIndisponivelException(String mensagem) {
        super(mensagem);
    }
}