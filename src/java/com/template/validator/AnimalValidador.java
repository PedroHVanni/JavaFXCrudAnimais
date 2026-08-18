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

        // Verifica se algum campo obrigatório está vazio
        if (animal.trim().isEmpty() ||
                cor.trim().isEmpty() ||
                especie.trim().isEmpty() ||
                idade.trim().isEmpty() ||
                sexo.trim().isEmpty()) {

            return "Preencha todos os campos antes de prosseguir.";
        }

        // Validação da idade
        if (!PADRAO_IDADE.matcher(idade.trim()).matches()) {
            return "A idade deve conter apenas números.";
        }

        // Validação do sexo
        if (!PADRAO_SEXO.matcher(sexo.trim()).matches()) {
            return "O sexo deve ser Masculino ou Feminino.";
        }

        return null;
    }
}