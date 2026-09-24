package br.com.biblioteca.model;

// Subclasse Ebook herda de ItemAcervo, mas NÃO assina o contrato Higienizavel
public class Ebook extends ItemAcervo {
    private double tamanhoMB;
    private String formato;

    public Ebook(Long id, String titulo, double tamanhoMB, String formato) {
        // Envia id e titulo para a classe mãe
        super(id, titulo);
        this.tamanhoMB = tamanhoMB;
        this.formato = formato;
    }

    // Sobrescrita OBRIGATÓRIA: Ebooks nunca ficam indisponíveis
    @Override
    public boolean emprestar() {
        System.out.println("Download do E-book '" + this.titulo + "' (" + this.formato + " - " + this.tamanhoMB + "MB) liberado com sucesso!");
        return true; // Sempre disponível, não altera o status 'this.disponivel'
    }

    // Getters específicos do Ebook
    public double getTamanhoMB() {
        return this.tamanhoMB;
    }

    public String getFormato() {
        return this.formato;
    }
}