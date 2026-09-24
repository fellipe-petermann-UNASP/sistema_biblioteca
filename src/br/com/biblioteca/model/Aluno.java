// Pacote de modelos do sistema.
package br.com.biblioteca.model;

// A palavra-chave 'extends' estabelece que Aluno É UM Usuario (Herança). 
public class Aluno extends Usuario {
    // Atributo específico da subclasse Aluno (código de matrícula).
    private String matricula;
    // Atributo específico da subclasse Aluno (nome do curso).
    private String curso;

    // Construtor da Subclasse Aluno.
    public Aluno(Long id, String nome, String email, String matricula, String curso) {
        // A instrução 'super(...)' DEVE ser a primeira linha. Chama o construtor da Superclasse Usuario.
        super(id, nome, email);
        // Inicializa os atributos exclusivos da classe Aluno.
        this.matricula = matricula;
        // Inicializa o curso do aluno.
        this.curso = curso;
    }

    @Override
    public int getPrazoEmprestimoDias(){
        return 14; // Regra específica para alunos -> 14 dias
    }

    @Override
    public int getLimiteLivros(){
        return 5; // Retorna a regra específica para alunos -> 5 livros
    }

    @Override
    public void exibirPerfil(){
        // Executar o exibirPerfil da Superclasse
        super.exibirPerfil();
        // Adicionar as informações específicas do Aluno
        System.out.println("Tipo: ALUNO | Matrícula: " + this.matricula + " | Curso: " + this.curso);
        System.out.println("------------------------");
    }

    // Método específico do Aluno para renovar a carteirinha da biblioteca. 
    public void renovarMatricula() {
        // Como 'nome' é protected na pai, o Aluno consegue ler 'this.nome' diretamente aqui.
        System.out.println("Matrícula do aluno(a) " + this.nome + " (" + this.matricula + ") renovada com sucesso!");
    }

    // Getter para a matrícula. 
    public String getMatricula() {
        return this.matricula;
    }

    // Getter para o curso. 
    public String getCurso() {
        return this.curso;
    }
}