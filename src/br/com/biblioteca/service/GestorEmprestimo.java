// Pacote responsável por classes de serviço e regras de negócio do sistema
package br.com.biblioteca.service;

// Importação dos modelos
import br.com.biblioteca.model.ItemAcervo;
import br.com.biblioteca.model.Usuario;

public class GestorEmprestimo {
    public void registrarEmprestimo(Usuario usuario, ItemAcervo item){
        System.out.println("\n--- PROCESSANDO SOLICITAÇÃO DE EMPRÉSTIMO ---");
        // Exibe quem está solicitando
        usuario.exibirPerfil();
        // Execução da regra de empréstimo específica do item
        boolean sucesso = item.emprestar();

        if (sucesso){
            System.out.println("SUCESSO: Item gerado para " + usuario.getNome() +". Prazo para devolução: " + usuario.getPrazoEmprestimoDias() + "dias.");
        } else {
            System.out.println("FALHA: Não foi possível realizar o empréstimo do item");
        }
        System.out.println("----------------------------------------\n");
    }
}