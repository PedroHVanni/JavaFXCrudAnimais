package com.template.model.dto;
/**
 * Classe DTO (Data Transfer Object)
 * Responsável por armazenar e transportar
 * os dados de um animal entre as camadas do sistema.
 */
public class AnimalDTO {

    private int id;
    private String nomeAnimal;
    private String cor;
    private String especie;
    private int idade;
    private String sexo;


    /**
     * Construtor utilitário para inserção/cadastro de registros
     * (sem a necessidade do ID, que é gerado pelo banco).
     */
    public AnimalDTO(String nomeAnimal, String cor, String especie, int idade, String sexo) {
        this.nomeAnimal = nomeAnimal;
        this.cor = cor;
        this.especie = especie;
        this.idade = idade;
        this.sexo = sexo;
    }

    /**
     * Construtor completo (utilizado na listagem e atualização de dados).
     */
    public AnimalDTO(int id, String nomeAnimal, String cor, String especie, int idade, String sexo) {
        this.id = id;
        this.nomeAnimal = nomeAnimal;
        this.cor = cor;
        this.especie = especie;
        this.idade = idade;
        this.sexo = sexo;
    }

    // --- GETTERS E SETTERS ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "AnimalDTO{" +
                "id=" + id +
                ", nomeAnimal='" + nomeAnimal + '\'' +
                ", cor='" + cor + '\'' +
                ", especie='" + especie + '\'' +
                ", idade=" + idade +
                ", sexo='" + sexo + '\'' +
                '}';
    }
}