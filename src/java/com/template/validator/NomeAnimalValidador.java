package com.template.validator;

import java.util.regex.Pattern;

class NomeAnimalValidador implements Validador<String> {

    private static final Pattern PADRAO_NOME =
            Pattern.compile("^[\\p{L} ]+$");

    private final String nomeAnimal;

    public NomeAnimalValidador(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    @Override
    public boolean validar(String valorAtual) {
        return nomeAnimal != null
                && PADRAO_NOME.matcher(nomeAnimal.trim()).matches();
    }

    @Override
    public String getMensagemErro() {
        return "O nome do animal deve conter apenas letras.";
    }

    @Override
    public String getValor() {
        return nomeAnimal;
    }
}