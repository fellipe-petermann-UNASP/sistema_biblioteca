// Declaração do pacote
package br.com.biblioteca.model;

// Importação da interface
import br.com.biblioteca.interfaces.Higienizavel;

// MUDANÇA 1: Herda de ItemAcervo e assina o contrato Higienizavel
public class LivroFisico extends ItemAcervo implements Higienizavel {

    // MUDANÇA 2: Removemos id, titulo e disponivel! Eles já vêm herdados da superclasse.
    private String autor; // Nome do autor do livro
    private String isbn; // Código ISBN do livro

    // Construtor para inicializar o livro
    public LivroFisico(Long id, String titulo, String autor, String isbn) {
        // MUDANÇA 3: O super() aciona o construtor da superclasse para preencher id, titulo e disponivel
        super(id, titulo);
        this.autor = autor;
        this.isbn = isbn;
    }

    // MUDANÇA 4: Sobrescrita OBRIGATÓRIA do método abstrato de ItemAcervo
    @Override
    public boolean emprestar() {
        if(this.disponivel) { // Acessa o atributo 'disponivel' que é protected na classe mãe
            this.disponivel = false;
            System.out.println("O livro físico '" + this.titulo + "' foi emprestado com sucesso!");
            return true;
        }
        System.out.println("ALERTA: O livro '" + this.titulo + "' já está emprestado!");
        return false;
    }

    // MUDANÇA 5: Implementação OBRIGATÓRIA do contrato Higienizavel
    @Override
    public double calcularCustoHigienizacao() {
        return 5.00; // Custo fixo de higienização de um livro físico
    }

    // MUDANÇA 6: Removemos os métodos devolver(), getId(), getTitulo() e isDisponivel().
    // Eles já existem na classe mãe e são herdados automaticamente! Só precisamos dos getters específicos daqui.
    public String getAutor() {
        return this.autor;
    }

    public String getIsbn() {
        return this.isbn;
    }
}