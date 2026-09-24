package br.com.biblioteca.model;

// MUDANÇA 1: A classe agora é 'abstract'. Não pode mais ser instanciada com 'new ItemAcervo(...)'
public abstract class ItemAcervo {
    protected Long id;
    protected String titulo;
    protected boolean disponivel;

    public ItemAcervo(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.disponivel = true;
    }

    // MUDANÇA 2: Método abstrato. Não tem mais corpo { }.
    // Obriga as subclasses (LivroFisico, Ebook) a implementarem a regra de empréstimo delas
    public abstract boolean emprestar();

    // Métodos concretos continuam normais
    public void devolver() {
        this.disponivel = true;
        System.out.println("Item '" + this.titulo + "' devolvido.");
    }

    // MUDANÇA 3: Getters necessários para o Polimorfismo funcionar na classe Biblioteca
    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() { // Getter para boolean geralmente começa com 'is'
        return disponivel;
    }

    public Long getId() {
        return id;
    }
}