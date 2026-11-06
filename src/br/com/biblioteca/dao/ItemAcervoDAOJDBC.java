package br.com.biblioteca.dao;

import br.com.biblioteca.db.ConnectionFactory;
import br.com.biblioteca.model.ItemAcervo;
import br.com.biblioteca.model.LivroFisico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemAcervoDAOJDBC implements ItemAcervoDAO {

    @Override
    public List<ItemAcervo> listarTodos() {
        List<ItemAcervo> acervo = new ArrayList<>();

        // JOIN entre 'livros' e 'autores' para montar o objeto completo no Java
        String sql = "SELECT l.isbn, l.titulo, l.quantidade_estoque, a.nome AS autor_nome " +
                "FROM livros l " +
                "LEFT JOIN autores a ON l.autor_id = a.id";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String isbn = rs.getString("isbn");
                String titulo = rs.getString("titulo");
                int estoque = rs.getInt("quantidade_estoque");
                String nomeAutor = rs.getString("autor_nome");

                if (nomeAutor == null) {
                    nomeAutor = "Autor Desconhecido";
                }

                boolean disponivel = estoque > 0;
                Long idSimulado = (long) Math.abs(isbn.hashCode());

                LivroFisico livro = new LivroFisico(idSimulado, titulo, nomeAutor, isbn);
                livro.setDisponivel(disponivel);

                acervo.add(livro);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao consultar o banco de dados: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return acervo;
    }

    @Override
    public List<ItemAcervo> buscarPorTitulo(String tituloBusca) {
        List<ItemAcervo> acervoEncontrado = new ArrayList<>();

        // Mesma query, mas com o filtro do MySQL (WHERE)
        String sql = "SELECT l.isbn, l.titulo, l.quantidade_estoque, a.nome AS autor_nome " +
                "FROM livros l " +
                "LEFT JOIN autores a ON l.autor_id = a.id " +
                "WHERE l.titulo LIKE ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Substitui a interrogação (?) pelo texto digitado
            stmt.setString(1, "%" + tituloBusca + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String isbn = rs.getString("isbn");
                    String titulo = rs.getString("titulo");
                    int estoque = rs.getInt("quantidade_estoque");
                    String nomeAutor = rs.getString("autor_nome");

                    if (nomeAutor == null) nomeAutor = "Autor Desconhecido";

                    boolean disponivel = estoque > 0;
                    Long idSimulado = (long) Math.abs(isbn.hashCode());

                    LivroFisico livro = new LivroFisico(idSimulado, titulo, nomeAutor, isbn);
                    livro.setDisponivel(disponivel);
                    acervoEncontrado.add(livro);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar livro por título: " + e.getMessage());
        }
        return acervoEncontrado;
    }
}