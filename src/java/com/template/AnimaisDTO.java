package com.template;

public class AnimaisDTO {
    private int id;
    private String animal;
    private String cor;
    private String especie;
    private String idade;
    private String sexo;

    // Retorna o ID do animal
    public int getId() {
        return id;
    }

    // Define o ID do animal
    public void setId(int id) {
        this.id = id;
    }

    // Retorna o nome do animal
    public String getAnimal() {
        return animal;
    }

    // Define o nome do animal
    public void setAnimal(String animal) {
        this.animal = animal;
    }

    // Retorna a cor do animal
    public String getCor() {
        return cor;
    }

    // Define a cor do animal
    public void setCor(String cor) {
        this.cor = cor;
    }

    // Retorna a espécie do animal
    public String getEspecie() {
        return especie;
    }

    // Define a espécie do animal
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    // Retorna a idade do animal
    public String getIdade() {
        return idade;
    }

    // Define a idade do animal
    public void setIdade(String idade) {
        this.idade = idade;
    }

    // Retorna o sexo do animal
    public String getSexo() {
        return sexo;
    }

    // Define o sexo do animal
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    // Define como o objeto será exibido no console
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
