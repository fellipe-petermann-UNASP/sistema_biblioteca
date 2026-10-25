package br.com.biblioteca.model;

public abstract class ItemAcervo implements Comparable<ItemAcervo> {
    protected Long id;
    protected String titulo;
    protected boolean disponivel;

    public ItemAcervo(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.disponivel = true; // Por padrão, todo item novo inicia disponível
    }

    // Método abstrato: obriga as subclasses (LivroFisico, Ebook) a implementarem sua regra
    public abstract boolean emprestar();

    // Métodos concretos de devolução
    public void devolver() {
        this.disponivel = true;
        System.out.println("Item '" + this.titulo + "' devolvido.");
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    // CORREÇÃO: Atualiza a variável de instância com o valor recebido
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    // Ordenação alfabética por título (Bloco 07)
    @Override
    public int compareTo(ItemAcervo outroItem) {
        return this.titulo.compareToIgnoreCase(outroItem.getTitulo());
    }
}