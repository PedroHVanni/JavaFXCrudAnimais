package com.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimaisDAO {

    /**
     * Método responsável por cadastrar um novo animal
     * no banco de dados.
     */
    public void cadastrarAnimal(AnimaisDTO animal) {

        // Comando SQL para inserir um novo registro
        String sql = "INSERT INTO animais (nome_animal, cor, especie, idade, sexo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            // Preenche os parâmetros da consulta SQL
            ps.setString(1, animal.getAnimal());
            ps.setString(2, animal.getCor());
            ps.setString(3, animal.getEspecie());
            ps.setInt(4, Integer.parseInt(animal.getIdade()));
            ps.setString(5, animal.getSexo());

            // Executa o INSERT
            int linhas = ps.executeUpdate();

            // Verifica se o cadastro foi realizado
            if (linhas > 0) {
                System.out.println("Animal cadastrado com sucesso!");
            }

        } catch (SQLException e) {

            // Exibe erro caso ocorra algum problema
            System.out.println("Erro ao cadastrar animal: " + e.getMessage());
        }
    }

    /**
     * Método responsável por buscar todos os animais
     * cadastrados no banco de dados.
     */
    public List<AnimaisDTO> listarAnimais() {

        // Consulta SQL para listar os registros
        String sql = "SELECT * FROM animais ORDER BY id";

        // Lista que armazenará os animais encontrados
        List<AnimaisDTO> lista = new ArrayList<>();

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Percorre todos os registros retornados
            while (rs.next()) {

                // Cria um objeto com os dados do banco
                AnimaisDTO animal = new AnimaisDTO(
                        rs.getInt("id"),
                        rs.getString("nome_animal"),
                        rs.getString("cor"),
                        rs.getString("especie"),
                        String.valueOf(rs.getInt("idade")),
                        rs.getString("sexo")
                );

                // Adiciona o animal na lista
                lista.add(animal);
            }

        } catch (SQLException e) {

            // Exibe erro caso ocorra algum problema
            System.out.println("Erro ao listar animais: " + e.getMessage());
        }

        // Retorna a lista completa
        return lista;
    }

    /**
     * Método responsável por atualizar os dados
     * de um animal existente.
     */
    public void alterarAnimal(AnimaisDTO animal) {

        // Comando SQL para atualizar um registro
        String sql = "UPDATE animais SET nome_animal = ?, cor = ?, especie = ?, idade = ?, sexo = ? WHERE id = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            // Define os novos valores
            ps.setString(1, animal.getAnimal());
            ps.setString(2, animal.getCor());
            ps.setString(3, animal.getEspecie());
            ps.setInt(4, Integer.parseInt(animal.getIdade()));
            ps.setString(5, animal.getSexo());

            // Define qual registro será atualizado
            ps.setInt(6, animal.getId());

            // Executa o UPDATE
            int linhas = ps.executeUpdate();

            // Verifica se houve alteração
            if (linhas > 0) {
                System.out.println("Animal atualizado com sucesso!");
            } else {
                System.out.println("ID não encontrado.");
            }

        } catch (SQLException e) {

            // Exibe erro caso ocorra algum problema
            System.out.println("Erro ao atualizar animal: " + e.getMessage());
        }
    }

    /**
     * Método responsável por excluir um animal
     * através do ID.
     */
    public void excluirAnimal(int id) {

        // Comando SQL para remover um registro
        String sql = "DELETE FROM animais WHERE id = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            // Define o ID que será removido
            ps.setInt(1, id);

            // Executa o DELETE
            int linhas = ps.executeUpdate();

            // Verifica se o registro foi removido
            if (linhas > 0) {
                System.out.println("Animal excluído com sucesso!");
            } else {
                System.out.println("ID não encontrado.");
            }

        } catch (SQLException e) {

            // Exibe erro caso ocorra algum problema
            System.out.println("Erro ao excluir animal: " + e.getMessage());
        }
    }
}