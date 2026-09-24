// Pacote de modelos do sistema de biblioteca.
package br.com.biblioteca.model;

// Importar a Interface
import br.com.biblioteca.interfaces.Notificavel;

// Declaração da Superclasse Usuario que servirá de base para os tipos de usuários. 
// A classe Usuario agora implementa o contrato Notificavel
public abstract class Usuario implements Notificavel {
    // 'protected' permite que as subclasses (Aluno, Professor) acessem o id diretamente.
    protected Long id;
    // 'protected' disponibiliza o atributo 'nome' para as classes filhas.
    protected String nome;
    // 'protected' disponibiliza o e-mail para as classes filhas.
    protected String email;

    // Construtor da Superclasse: exige os dados básicos de qualquer usuário. 
    public Usuario(Long id, String nome, String email) {
        // Inicializa o ID.
        this.id = id;
        // Inicializa o nome do usuário.
        this.nome = nome;
        // Inicializa o e-mail do usuário.
        this.email = email;
    }

    // Métodos abstratos. Como Usuario é abstrata, não damos corpo a estes métodos. As subclasses (aluno, professor) são obrigadas a implementar
    public abstract int getPrazoEmprestimoDias();
    public abstract int getLimiteLivros();

    // Implementação do Contrato da Interface: Regra padrão de envio de e-mail para o usuário
    @Override
    public void enviarNotificacao(String mensagem){
        System.out.println("Enviando email para " + this.email + ": " + mensagem);
    }

    // Método comum a todos os usuários para exibir os dados básicos. 
    public void exibirPerfil() {
        // Imprime o ID e o Nome.
        System.out.println("ID: " + this.id + " | Nome: " + this.nome);
        // Imprime o E-mail.
        System.out.println("E-mail: " + this.email);
    }

    // Getter para leitura do ID. 
    public Long getId() {
        return this.id;
    }

    // Getter para leitura do Nome. 
    public String getNome() {
        return this.nome;
    }
}