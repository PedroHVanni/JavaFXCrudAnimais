package com.template;

/**
 * Classe DTO (Data Transfer Object)
 * Responsável por armazenar e transportar
 * os dados de um animal entre as camadas
 * do sistema.
 */
public class AnimaisDTO {

    // Identificador único do animal
    private int id;

    // Nome do animal
    private String animal;

    // Cor do animal
    private String cor;

    // Espécie do animal
    private String especie;

    // Idade do animal
    private String idade;

    // Sexo do animal
    private String sexo;

    /**
     * Construtor vazio.
     * Necessário para criar objetos sem
     * informar valores inicialmente.
     */
    public AnimaisDTO() {
    }

    /**
     * Construtor completo.
     * Utilizado para criar objetos já
     * preenchidos com todos os dados.
     */
    public AnimaisDTO(int id, String animal, String cor,
                      String especie, String idade, String sexo) {

        this.id = id;
        this.animal = animal;
        this.cor = cor;
        this.especie = especie;
        this.idade = idade;
        this.sexo = sexo;
    }

    /**
     * Retorna o ID do animal.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do animal.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome do animal.
     */
    public String getAnimal() {
        return animal;
    }

    /**
     * Define o nome do animal.
     */
    public void setAnimal(String animal) {
        this.animal = animal;
    }

    /**
     * Retorna a cor do animal.
     */
    public String getCor() {
        return cor;
    }

    /**
     * Define a cor do animal.
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * Retorna a espécie do animal.
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * Define a espécie do animal.
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /**
     * Retorna a idade do animal.
     */
    public String getIdade() {
        return idade;
    }

    /**
     * Define a idade do animal.
     */
    public void setIdade(String idade) {
        this.idade = idade;
    }

    /**
     * Retorna o sexo do animal.
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Define o sexo do animal.
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Sobrescreve o método toString().
     * Define como o objeto será exibido
     * quando for impresso no console.
     */
    @Override
    public String toString() {
        return "\nID: " + id +
                "\nAnimal: " + animal +
                "\nCor: " + cor +
                "\nEspécie: " + especie +
                "\nIdade: " + idade +
                "\nSexo: " + sexo +
                "\n-------------------------";
    }
}