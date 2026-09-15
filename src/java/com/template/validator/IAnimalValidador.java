package com.template.validator;

public interface IAnimalValidador {

    boolean validarAnimal(
            String animal,
            String cor,
            String especie,
            String idade,
            String sexo
    );
}
