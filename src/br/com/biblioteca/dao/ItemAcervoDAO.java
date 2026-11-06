package br.com.biblioteca.dao;

import br.com.biblioteca.model.ItemAcervo;
import java.util.List;

public interface ItemAcervoDAO {

    // Método do Exercício 1 (Listar todos os itens)
    List<ItemAcervo> listarTodos();

    // NOVO: Método do Exercício 2 (Buscar item específico no banco)
    List<ItemAcervo> buscarPorTitulo(String tituloBusca);

}