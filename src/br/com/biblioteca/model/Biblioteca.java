// Declaração do Pacote
package br.com.biblioteca.model;

// Importação das classes necessárias para manipulação de listas do Java
import java.util.ArrayList;
import java.util.List;

// Classe que representa a Biblioteca e gerencia o acervo
public class Biblioteca {
    private String nome; // Nome da Biblioteca

    // MUDANÇA 1 (POLIMORFISMO): A lista agora guarda de forma genérica qualquer ItemAcervo
    private List<ItemAcervo> acervo;

    public Biblioteca(String nome){
        this.nome = nome; // Definição do nome da biblioteca
        this.acervo = new ArrayList<>(); // Inicializa a lista do acervo como uma lista vazia
    }

    // MUDANÇA 2 (POLIMORFISMO): O método agora aceita LivroFisico, Ebook, etc.
    public void adicionarItem(ItemAcervo item){
        this.acervo.add(item); // Inserção do objeto (que pode ser qualquer filho de ItemAcervo)
        System.out.println("Item '" + item.getTitulo() + "' adicionado ao acervo da " + this.nome);
    }

    // Método para listar todos os itens cadastrados no acervo
    public void listarAcervo() {
        System.out.println("\n--- ACERVO DA BIBLIOTECA: " + this.nome + " ---");

        // MUDANÇA 3: Laço de repetição que percorre a lista de forma genérica
        for (ItemAcervo item : this.acervo) {
            // Impressão do título e do status atual do item
            System.out.println("- " + item.getTitulo() + " | Status: " + (item.isDisponivel() ? "Disponível" : "Emprestado"));
        }
        System.out.println("----------------------------------\n");
    }
}