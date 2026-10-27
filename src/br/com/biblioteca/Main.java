package br.com.biblioteca;

import br.com.biblioteca.event.EmprestimoListener;
import br.com.biblioteca.exception.ItemIndisponivelException;
import br.com.biblioteca.exception.LimiteEmprestimoExcedidoException;
import br.com.biblioteca.exception.UsuarioNaoEncontradoException;
import br.com.biblioteca.interfaces.Higienizavel;
import br.com.biblioteca.interfaces.Notificavel;
import br.com.biblioteca.model.*;
import br.com.biblioteca.service.GestorEmprestimo;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println("=== TESTE DE INTEGRACAO - SISTEMA DE BIBLIOTECA ===");
        System.out.println("====================================================\n");

        // -----------------------------------------------------------------
        // EXERCÍCIO 1: Carga Inicial de Dados e Ordenação
        // -----------------------------------------------------------------
        System.out.println("--- EXERCÍCIO 1: CARGA DE DADOS E ORDENAÇÃO ---");
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central UNASP");

        // Instanciando itens
        LivroFisico livro1 = new LivroFisico(101L, "Código Limpo", "Robert C. Martin", "978-8576082675");
        LivroFisico livro2 = new LivroFisico(102L, "Estruturas de Dados em Java", "Loiane Groner", "978-8575226926");
        Ebook ebook1 = new Ebook(201L, "Entendendo Algoritmos", 12.5, "PDF");
        Ebook ebook2 = new Ebook(202L, "Design Patterns", 18.0, "EPUB");

        // Adicionando ao acervo
        biblioteca.adicionarItem(livro1);
        biblioteca.adicionarItem(livro2);
        biblioteca.adicionarItem(ebook1);
        biblioteca.adicionarItem(ebook2);

        // Instanciando usuários
        Aluno aluno1 = new Aluno(1L, "João Silva", "joao@aluno.edu.br", "2026A1", "Ciência da Computação");
        Aluno aluno2 = new Aluno(2L, "Maria Oliveira", "maria@aluno.edu.br", "2026A2", "Engenharia de Software");
        Professor prof1 = new Professor(3L, "Dra. Ana", "ana@docente.edu.br", "Computação", "SIAPE-9090");

        // Registrando no Map de usuários
        biblioteca.registrarUsuario(aluno1, aluno1.getMatricula());
        biblioteca.registrarUsuario(aluno2, aluno2.getMatricula());
        biblioteca.registrarUsuario(prof1, "SIAPE-9090");

        // Listando o acervo (deve aparecer ordenado alfabeticamente devido ao Comparable)
        biblioteca.listarAcervo();

        // -----------------------------------------------------------------
        // EXERCÍCIO 2: Configuração de Listeners de Eventos
        // -----------------------------------------------------------------
        System.out.println("\n--- EXERCÍCIO 2: CONFIGURAÇÃO DE LISTENERS (EVENTOS) ---");
        GestorEmprestimo gestor = new GestorEmprestimo();

        // Listener 1: Notificação por e-mail
        gestor.registrarListener((usuario, item) -> {
            System.out.println("        [E-MAIL] Notificação enviada para " + usuario.getEmail()
                    + " referente ao item: '" + item.getTitulo() + "'");
        });

        // Listener 2: Auditoria do Sistema
        gestor.registrarListener((usuario, item) -> {
            System.out.println("          [LOG AUDITORIA] Empréstimo registrado com sucesso para ID Usuario: " + usuario.getId());
        });

        System.out.println("Listeners de eventos registrados com sucesso.");

        // -----------------------------------------------------------------
        // EXERCÍCIO 3: Caminho Feliz de Empréstimo
        // -----------------------------------------------------------------
        System.out.println("\n--- EXERCÍCIO 3: EMPRÉSTIMO COM SUCESSO ---");
        try {
            gestor.realizarEmprestimo(aluno1, ebook1);
        } catch (ItemIndisponivelException | LimiteEmprestimoExcedidoException e) {
            System.err.println("  Erro inesperado: " + e.getMessage());
        } finally {
            System.out.println("[SISTEMA] Operação de empréstimo 1 finalizada.");
        }

        // -----------------------------------------------------------------
        // EXERCÍCIO 4: Validação de Item Indisponível
        // -----------------------------------------------------------------
        System.out.println("\n--- EXERCÍCIO 4: TESTE DE ITEM INDISPONÍVEL ---");
        try {
            // Tentando emprestar o mesmo ebook1 que acabou de ser emprestado ao aluno1
            gestor.realizarEmprestimo(prof1, ebook1);
        } catch (ItemIndisponivelException e) {
            System.err.println("  EXCEÇÃO CAPTURADA: " + e.getMessage());
        } catch (LimiteEmprestimoExcedidoException e) {
            System.err.println("  Erro de limite: " + e.getMessage());
        } finally {
            System.out.println("[SISTEMA] Operação de empréstimo 2 finalizada.");
        }

        // -----------------------------------------------------------------
        // EXERCÍCIO 5: Validação de Limite Excedido
        // -----------------------------------------------------------------
        System.out.println("\n--- EXERCÍCIO 5: TESTE DE LIMITE EXCEDIDO ---");
        try {
            // Aluno tem limite de 3 livros. Já pegou 1 (ebook1). Vamos tentar estourar o limite:
            gestor.realizarEmprestimo(aluno1, livro1); // 2º item
            gestor.realizarEmprestimo(aluno1, livro2); // 3º item (atingiu o limite)

            System.out.println("Tentando pegar o 4º item para estourar o limite...");
            gestor.realizarEmprestimo(aluno1, ebook2); // 4º item -> Deve lançar LimiteEmprestimoExcedidoException

        } catch (ItemIndisponivelException e) {
            System.err.println("  Erro de item: " + e.getMessage());
        } catch (LimiteEmprestimoExcedidoException e) {
            System.err.println("  EXCEÇÃO CAPTURADA: " + e.getMessage());
        } finally {
            System.out.println("[SISTEMA] Operação de empréstimo em lote finalizada.");
        }

        // -----------------------------------------------------------------
        // EXERCÍCIO 6: Polimorfismo com Interfaces (Higienizavel / Notificavel)
        // -----------------------------------------------------------------
        System.out.println("\n--- EXERCÍCIO 6: POLIMORFISMO COM INTERFACES ---");

        // 1. Processando Custo de Higienização
        List<Higienizavel> itensFisicos = new ArrayList<>();
        itensFisicos.add(livro1);
        itensFisicos.add(livro2);

        double custoTotal = 0;
        for (Higienizavel item : itensFisicos) {
            custoTotal += item.calcularCustoHigienizacao();
        }
        System.out.println("Custo total estimado para higienização do acervo físico: R$ " + custoTotal);

        // 2. Disparo de Notificações em Lote
        List<Notificavel> destinatarios = new ArrayList<>();
        destinatarios.add(aluno1);
        destinatarios.add(aluno2);
        destinatarios.add(prof1);

        System.out.println("\nDisparando avisos globais:");
        for (Notificavel destinatario : destinatarios) {
            destinatario.enviarNotificacao("Atenção: Renovação de acervo agendada para sexta-feira.");
        }

        // -----------------------------------------------------------------
        // EXERCÍCIO 7: Busca por Chave com Exceção Customizada
        // -----------------------------------------------------------------
        System.out.println("\n--- EXERCÍCIO 7: BUSCA NO MAP E UNCHECKED EXCEPTION ---");
        try {
            String chaveBusca = "MATRICULA-INEXISTENTE";
            Usuario usuarioBuscado = biblioteca.buscarUsuario(chaveBusca);

            if (usuarioBuscado == null) {
                throw new UsuarioNaoEncontradoException("Usuário com a chave '" + chaveBusca + "' não existe no cadastro!");
            }

            System.out.println("Usuário encontrado: " + usuarioBuscado.getNome());
        } catch (UsuarioNaoEncontradoException e) {
            System.err.println("  EXCEÇÃO CAPTURADA: " + e.getMessage());
        }
    }
}
