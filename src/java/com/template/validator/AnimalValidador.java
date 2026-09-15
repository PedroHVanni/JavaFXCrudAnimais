package com.template.validator;

import java.util.ArrayList;
import java.util.List;

public class AnimalValidador implements IAnimalValidador {

    @Override
    public boolean validarAnimal(
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
                com.template.util.DialogUtil.exibirErro(
                        "Validação",
                        validador.getMensagemErro()
                );
                return false;
            }
        }

        // Garante que a idade possa ser convertida para inteiro.
        try {
            Integer.parseInt(idade.trim());
        } catch (NumberFormatException e) {
            com.template.util.DialogUtil.exibirErro(
                    "Validação",
                    "O campo Idade deve conter apenas números."
            );
            return false;
        }

        return true;
    }
}
