package br.com.biblioteca.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // O aluno deve trocar 'nomedoaluno' pelo sufixo exato do seu banco de dados
    private static final String URL = "jdbc:mysql://benserverplex.ddns.net:3306/alunos_ADS43B_nomedoaluno?useSSL=false&serverTimezone=America/Sao_Paulo";
    private static final String USER = "alunos";
    private static final String PASSWORD = "senhaAlunos";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}