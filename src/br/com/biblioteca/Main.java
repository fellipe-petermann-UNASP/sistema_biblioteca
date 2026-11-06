package br.com.biblioteca;

import br.com.biblioteca.event.EmprestimoListener;
import br.com.biblioteca.exception.ItemIndisponivelException;
import br.com.biblioteca.exception.LimiteEmprestimoExcedidoException;
import br.com.biblioteca.exception.UsuarioNaoEncontradoException;
import br.com.biblioteca.interfaces.Higienizavel;
import br.com.biblioteca.interfaces.Notificavel;
import br.com.biblioteca.model.*;
import br.com.biblioteca.service.GestorEmprestimo;
import br.com.biblioteca.dao.ItemAcervoDAO;
import br.com.biblioteca.dao.ItemAcervoDAOJDBC;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== TESTE DE CONEXÃO AO MYSQL (SERVIDOR DDNS) ===\n");

        ItemAcervoDAO dao = new ItemAcervoDAOJDBC();

        System.out.println("Conectando ao banco e buscando o acervo de livros...\n");
        List<ItemAcervo> itensDoBanco = dao.listarTodos();

        if (itensDoBanco.isEmpty()) {
            System.out.println("A tabela 'livros' está vazia ou a conexão falhou.");
        } else {
            System.out.println("--- ACERVO CARREGADO DO BANCO DE DADOS ---");
            for (ItemAcervo item : itensDoBanco) {

                // Exibe os dados comuns da Superclasse
                System.out.print("- Título: " + item.getTitulo() +
                        " | Disponível: " + (item.isDisponivel() ? "Sim" : "Não"));

                // Exibe os dados específicos fazendo o Downcasting seguro
                if (item instanceof LivroFisico) {
                    LivroFisico fisico = (LivroFisico) item;
                    System.out.print(" | Autor: " + fisico.getAutor() +
                            " | ISBN: " + fisico.getIsbn());
                }

                System.out.println(); // Quebra de linha
            }
        }
    }
}