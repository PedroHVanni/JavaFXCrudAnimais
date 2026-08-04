package com.template.validator;

import java.util.regex.Pattern;

public class AnimalValidador {

    // Aceita apenas números
    private static final Pattern PADRAO_IDADE =
            Pattern.compile("^\\d+$");

    // Aceita apenas Masculino ou Feminino (maiúsculas e minúsculas)
    private static final Pattern PADRAO_SEXO =
            Pattern.compile("^(?i)(masculino|feminino)$");

    public static String validar(String animal,
                                 String cor,
                                 String especie,
                                 String idade,
                                 String sexo) {

        if (animal == null || animal.trim().isEmpty()) {
            return "Informe o nome do animal.";
        }

        if (cor == null || cor.trim().isEmpty()) {
            return "Informe a cor do animal.";
        }

        if (especie == null || especie.trim().isEmpty()) {
            return "Informe a espécie do animal.";
        }

        if (idade == null || idade.trim().isEmpty()) {
            return "Informe a idade do animal.";
        }

        // Validação da idade usando Pattern
        if (!PADRAO_IDADE.matcher(idade.trim()).matches()) {
            return "A idade deve conter apenas números.";
        }

        int valorIdade = Integer.parseInt(idade);

        if (valorIdade < 0) {
            return "A idade não pode ser negativa.";
        }

        if (sexo == null || sexo.trim().isEmpty()) {
            return "Informe o sexo do animal.";
        }

        // Validação do sexo usando Pattern
        if (!PADRAO_SEXO.matcher(sexo.trim()).matches()) {
            return "O sexo deve ser Masculino ou Feminino.";
        }

        return null;
    }
}