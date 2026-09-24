package br.com.biblioteca;

import br.com.biblioteca.interfaces.Higienizavel;
import br.com.biblioteca.interfaces.Notificavel;
import br.com.biblioteca.model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== INICIANDO SISTEMA DA BIBLIOTECA ===\n");

        // 1. Instanciando a Biblioteca
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central da Faculdade");

        // 2. Instanciando Usuários (Upcasting implícito não é obrigatório aqui, mas os objetos são válidos)
        Aluno aluno1 = new Aluno(1L, "João Silva", "joao@aluno.edu.br", "2026A1", "Engenharia de Software");
        Professor prof1 = new Professor(2L, "Dra. Ana", "ana@docente.edu.br", "Computação", "SIAPE-9090");

        // 3. Instanciando Itens do Acervo (A variável é do tipo da Superclasse Abstrata!)
        // POLIMORFISMO: O tipo declarado é ItemAcervo, o tipo real é LivroFisico ou Ebook.
        ItemAcervo livro1 = new LivroFisico(101L, "Código Limpo", "Robert C. Martin", "978-8576082675");
        ItemAcervo ebook1 = new Ebook(201L, "Entendendo Algoritmos", 15.5, "PDF");

        // --- TESTE 1: POLIMORFISMO NA BIBLIOTECA ---
        System.out.println("--- TESTE 1: ADICIONANDO ITENS (POLIMORFISMO) ---");
        biblioteca.adicionarItem(livro1);
        biblioteca.adicionarItem(ebook1);
        biblioteca.listarAcervo();

        // --- TESTE 2: POLIMORFISMO NOS USUÁRIOS (REGRAS DE EMPRÉSTIMO) ---
        System.out.println("--- TESTE 2: PERFIS E REGRAS ESPECÍFICAS ---");
        aluno1.exibirPerfil();
        System.out.println("Prazo do Aluno: " + aluno1.getPrazoEmprestimoDias() + " dias.");

        prof1.exibirPerfil();
        System.out.println("Prazo do Professor: " + prof1.getPrazoEmprestimoDias() + " dias.\n");

        // --- TESTE 3: INTERFACE HIGIENIZAVEL ---
        System.out.println("--- TESTE 3: CONTRATO DE HIGIENIZAÇÃO ---");
        // Criamos uma lista que aceita APENAS coisas que podem ser higienizadas
        List<Higienizavel> itensParaLimpar = new ArrayList<>();

        // Fazemos o casting de 'livro1' (que é ItemAcervo) para 'LivroFisico' antes de adicionar
        itensParaLimpar.add((LivroFisico) livro1);
        // itensParaLimpar.add((Ebook) ebook1); // SE DESCOMENTAR, DÁ ERRO DE COMPILAÇÃO! Ebook não é Higienizavel.

        double custoTotalLimpeza = 0;
        for(Higienizavel item : itensParaLimpar) {
            custoTotalLimpeza += item.calcularCustoHigienizacao();
        }
        System.out.println("Custo total de higienização do acervo físico: R$ " + custoTotalLimpeza + "\n");

        // --- TESTE 4: INTERFACE NOTIFICAVEL ---
        System.out.println("--- TESTE 4: SISTEMA DE NOTIFICAÇÕES ---");
        List<Notificavel> listaNotificacoes = new ArrayList<>();
        listaNotificacoes.add(aluno1);
        listaNotificacoes.add(prof1);

        for(Notificavel destinatario : listaNotificacoes) {
            destinatario.enviarNotificacao("Aviso: A biblioteca fechará para manutenção neste feriado.");
        }
    }
}