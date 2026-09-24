// Pacote de modelos do sistema.
package br.com.biblioteca.model;

// A classe Professor herda de Usuario. 
public class Professor extends Usuario {
    // Atributo específico do professor (Departamento ao qual pertence).
    private String departamento;
    // Atributo específico do professor (Registro SIAPE ou funcional).
    private String registroFuncional;

    // Construtor da subclasse Professor. 
    public Professor(Long id, String nome, String email, String departamento, String registroFuncional) {
        // Repassa os dados comuns para o construtor da superclasse Usuario.
        super(id, nome, email);
        // Define o departamento.
        this.departamento = departamento;
        // Define o registro funcional.
        this.registroFuncional = registroFuncional;
    }

    @Override
    public int getPrazoEmprestimoDias(){
        return 30; // Regra estendida para professores -> 30 dias
    }

    @Override
    public int getLimiteLivros(){
        return 10; // Regra estendida para professores -> 10 livros
    }

    @Override
    public void exibirPerfil(){
        super.exibirPerfil(); // Reaproveitar a função da Superclasse
        // Imprimir dos dados específicos do Professor
        System.out.println("Tipo: PROFESSOR | Depto: " + this.departamento + " | Registro: " + this.registroFuncional);
        System.out.println("------------------------------");
    }

    // Getter específico do departamento. 
    public String getDepartamento() {
        return this.departamento;
    }

    // Getter específico do registro funcional.
    public String getRegistroFuncional() { return this.registroFuncional; }
}