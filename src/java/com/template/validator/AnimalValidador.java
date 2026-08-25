package com.template.validator;

import java.util.ArrayList;
import java.util.List;

import static com.template.util.DialogUtil.exibirErro;

public class AnimalValidador {

    private AnimalValidador() {
        // Impede a criação de objetos dessa classe
    }

    public static String validar(
            String animal,
            String cor,
            String especie,
            String idade,
            String sexo
    ) {

        List<Validador<String>> validadores = new ArrayList<>();

        // Campos obrigatórios
        validadores.add(
                new CampoObrigatorioValidador("Animal", animal)
        );

        validadores.add(
                new CampoObrigatorioValidador("Cor", cor)
        );

        validadores.add(
                new CampoObrigatorioValidador("Espécie", especie)
        );

        validadores.add(
                new CampoObrigatorioValidador("Idade", idade)
        );

        validadores.add(
                new CampoObrigatorioValidador("Sexo", sexo)
        );

        // Validação do nome do animal
        validadores.add(
                new NomeAnimalValidador(animal)
        );

        // Executa as validações
        for (Validador<String> validador : validadores) {

            if (!validador.validar(validador.getValor())) {

                return validador.getMensagemErro();
            }
        }

        return null;
    }
}