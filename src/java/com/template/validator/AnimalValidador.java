package com.template.validator;

import java.util.regex.Pattern;

public class AnimalValidador {

    private static final Pattern PADRAO_IDADE =
            Pattern.compile("^\\d+$");

    private static final Pattern PADRAO_SEXO =
            Pattern.compile("^(?i)(masculino|feminino)$");

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

        if (campoVazio(animal)) {
            return "O campo Animal é obrigatório.";
        }

        if (campoVazio(cor)) {
            return "O campo Cor é obrigatório.";
        }

        if (campoVazio(especie)) {
            return "O campo Espécie é obrigatório.";
        }

        if (campoVazio(idade)) {
            return "O campo Idade é obrigatório.";
        }

        if (!PADRAO_IDADE.matcher(idade.trim()).matches()) {
            return "A idade deve conter apenas números.";
        }

        if (campoVazio(sexo)) {
            return "O campo Sexo é obrigatório.";
        }

        if (!PADRAO_SEXO.matcher(sexo.trim()).matches()) {
            return "O sexo deve ser Masculino ou Feminino.";
        }

        return null;
    }

    private static boolean campoVazio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}