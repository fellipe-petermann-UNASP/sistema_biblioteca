package br.com.biblioteca.event;

import br.com.biblioteca.model.ItemAcervo;
import br.com.biblioteca.model.Usuario;

public interface EmprestimoListener {
    void onEmprestimoRealizado(Usuario usuario, ItemAcervo item);
}
