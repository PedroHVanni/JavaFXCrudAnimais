package com.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/Animais";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    // Método que retorna uma conexão com o banco
    public static Connection conectar() {
        try {
            // Carrega o driver do PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Estabelece a conexão
            return DriverManager.getConnection(URL, USUARIO, SENHA);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver não encontrado: " + e.getMessage());

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco: " + e.getMessage());
        }
    }
}
