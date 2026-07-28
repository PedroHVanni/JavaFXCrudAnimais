package com.template.model.dao;

import com.template.model.ConexaoBD;
import com.template.model.dto.AnimalDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimaisDAO {

    public boolean cadastrarAnimal(AnimalDTO animal) {
        String sql = "INSERT INTO animais (nome_animal, cor, especie, idade, sexo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, animal.getNomeAnimal());
            ps.setString(2, animal.getCor());
            ps.setString(3, animal.getEspecie());
            ps.setInt(4, animal.getIdade());
            ps.setString(5, animal.getSexo());

            int linhas = ps.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar animal no banco de dados: " + e.getMessage(), e);
        }
    }

    public List<AnimalDTO> listarAnimais() {
        String sql = "SELECT * FROM animais ORDER BY id";
        List<AnimalDTO> lista = new ArrayList<>();

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                AnimalDTO animal = new AnimalDTO(
                        rs.getInt("id"),
                        rs.getString("nome_animal"),
                        rs.getString("cor"),
                        rs.getString("especie"),
                        rs.getInt("idade"),
                        rs.getString("sexo")
                );
                lista.add(animal);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar animais no banco de dados: " + e.getMessage(), e);
        }

        return lista;
    }

    public boolean alterarAnimal(AnimalDTO animal) {
        String sql = "UPDATE animais SET nome_animal = ?, cor = ?, especie = ?, idade = ?, sexo = ? WHERE id = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, animal.getNomeAnimal());
            ps.setString(2, animal.getCor());
            ps.setString(3, animal.getEspecie());
            ps.setInt(4, animal.getIdade());
            ps.setString(5, animal.getSexo());
            ps.setInt(6, animal.getId());

            int linhas = ps.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar animal no banco de dados: " + e.getMessage(), e);
        }
    }

    public boolean excluirAnimal(int id) {
        String sql = "DELETE FROM animais WHERE id = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, id);

            int linhas = ps.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir animal no banco de dados: " + e.getMessage(), e);
        }
    }
}