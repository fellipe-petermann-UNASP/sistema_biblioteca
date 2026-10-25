package br.com.biblioteca.service;

import br.com.biblioteca.event.EmprestimoListener;
import br.com.biblioteca.exception.ItemIndisponivelException;
import br.com.biblioteca.exception.LimiteEmprestimoExcedidoException;
import br.com.biblioteca.model.ItemAcervo;
import br.com.biblioteca.model.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorEmprestimo {
    // MAP: Mapeia cada Usuário para a sua Lista de Itens Emprestados
    private Map<Usuario, List<ItemAcervo>> emprestimosAtivos;
    // LIST: Mantém a lista de ouvintes (Listeners) cadastrados para eventos
    private List<EmprestimoListener> listeners;

    public GestorEmprestimo() {
        this.emprestimosAtivos = new HashMap<>();
        this.listeners = new ArrayList<>();
    }

    public void registrarListener(EmprestimoListener listener) {
        this.listeners.add(listener);
    }

    public void realizarEmprestimo(Usuario usuario, ItemAcervo item)
            throws ItemIndisponivelException, LimiteEmprestimoExcedidoException {

        // 1. Validação de Exceção: Item indisponível
        if (!item.isDisponivel()) {
            throw new ItemIndisponivelException("O item '" + item.getTitulo() + "' já está emprestado.");
        }

        // Obtém ou inicializa a lista de empréstimos do usuário
        List<ItemAcervo> itensDoUsuario = emprestimosAtivos.getOrDefault(usuario, new ArrayList<>());

        // 2. Validação de Exceção: Limite do Usuário (Polimorfismo dinâmico de Aluno/Professor)
        if (itensDoUsuario.size() >= usuario.getLimiteLivros()) {
            throw new LimiteEmprestimoExcedidoException(
                    "Usuário " + usuario.getNome() + " atingiu o limite máximo de " + usuario.getLimiteLivros() + " itens."
            );
        }

        // 3. Sucesso: Executa o empréstimo
        item.setDisponivel(false);
        itensDoUsuario.add(item);
        emprestimosAtivos.put(usuario, itensDoUsuario);

        System.out.println("Empréstimo realizado com sucesso: '" + item.getTitulo() + "' para " + usuario.getNome());

        // 4. DISPARO DE EVENTO (Notifica todos os listeners registrados)
        dispararEventoEmprestimo(usuario, item);
    }

    private void dispararEventoEmprestimo(Usuario usuario, ItemAcervo item) {
        for (EmprestimoListener listener : listeners) {
            listener.onEmprestimoRealizado(usuario, item);
        }
    }
}