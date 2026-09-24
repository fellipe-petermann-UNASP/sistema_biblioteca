package br.com.biblioteca.service;

import br.com.biblioteca.interfaces.Notificavel;
import java.util.List;

public class ServicoNotificacao {
    public void notificarAtrasados(List<Notificavel>listaParaNotificar, String aviso){
     System.out.println("\n--- DISPARANDO NOTIFICAÇÕES DE ATRASO ---");
     for (Notificavel item : listaParaNotificar){
         item.enviarNotificacao(aviso);
     }
     System.out.println("------------------------------------------");
    }
}
