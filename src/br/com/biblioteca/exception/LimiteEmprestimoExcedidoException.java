package br.com.biblioteca.exception;

public class LimiteEmprestimoExcedidoException extends Exception {
    public LimiteEmprestimoExcedidoException(String mensagem) {
        super(mensagem);
    }
}