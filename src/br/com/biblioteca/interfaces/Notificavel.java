package br.com.biblioteca.interfaces;

public interface Notificavel {
    // Método abstrato: toda classe que implementar 'Notificavel' deve fornecer o código de envio
    void enviarNotificacao(String mensagem);
}
