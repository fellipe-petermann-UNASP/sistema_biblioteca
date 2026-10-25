package br.com.biblioteca;

import br.com.biblioteca.event.EmprestimoListener;
import br.com.biblioteca.exception.ItemIndisponivelException;
import br.com.biblioteca.exception.LimiteEmprestimoExcedidoException;
import br.com.biblioteca.model.*;
import br.com.biblioteca.service.GestorEmprestimo;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE BIBLIOTECA - CONSOLIDAÇÃO ===\n");

        Biblioteca biblioteca = new Biblioteca("Biblioteca UNASP");
        GestorEmprestimo gestor = new GestorEmprestimo();

        // 1. REGISTRO DE EVENTO (Handler / Callback)
        gestor.registrarListener((usuario, item) -> {
            System.out.println("🔔 [EVENTO DISPARADO] Enviando notificação para " + usuario.getEmail()
                    + " sobre a retirada do item: " + item.getTitulo());
        });

        // 2. CADASTRO DE DADOS
        Aluno aluno = new Aluno(1L, "João Silva", "joao@aluno.edu.br", "2026A1", "Ciência da Computação");
        Professor prof = new Professor(2L, "Dra. Ana", "ana@docente.edu.br", "Computação", "SIAPE-9090");

        LivroFisico livro1 = new LivroFisico(101L, "Código Limpo", "Robert C. Martin", "978-8576082675");
        Ebook ebook1 = new Ebook(201L, "Entendendo Algoritmos", 12.5, "PDF");

        biblioteca.adicionarItem(livro1);
        biblioteca.adicionarItem(ebook1);
        biblioteca.registrarUsuario(aluno, aluno.getMatricula());

        // 3. BLOCOS TRY-CATCH-FINALLY (Tratamento de Exceções)
        System.out.println("\n--- TESTE DE EMPRÉSTIMO COM EVENTOS E EXCEÇÕES ---");
        try {
            gestor.realizarEmprestimo(aluno, livro1);

            // Tentativa de reempréstimo do mesmo item (Dispara ItemIndisponivelException)
            gestor.realizarEmprestimo(prof, livro1);

        } catch (ItemIndisponivelException e) {
            System.err.println("❌ FALHA DE REGRA DE NEGÓCIO: " + e.getMessage());
        } catch (LimiteEmprestimoExcedidoException e) {
            System.err.println("❌ LIMITE EXCEDIDO: " + e.getMessage());
        } finally {
            System.out.println("\nLOG: Operação de empréstimo finalizada.");
        }
    }
}