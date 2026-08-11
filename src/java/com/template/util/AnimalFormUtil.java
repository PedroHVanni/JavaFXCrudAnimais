package com.template.util;

import com.template.model.dto.AnimalDTO;
import javafx.scene.control.TextField;

public class AnimalFormUtil {

    private AnimalFormUtil() {
        // Impede a criação de objetos dessa classe
    }

    public static void limparCampos(
            TextField txtAnimal,
            TextField txtCor,
            TextField txtEspecie,
            TextField txtIdade,
            TextField txtSexo
    ) {

        txtAnimal.clear();
        txtCor.clear();
        txtEspecie.clear();
        txtIdade.clear();
        txtSexo.clear();

        txtAnimal.requestFocus();
    }

    public static void preencherCampos(
            AnimalDTO animal,
            TextField txtAnimal,
            TextField txtCor,
            TextField txtEspecie,
            TextField txtIdade,
            TextField txtSexo
    ) {

        if (animal == null) {
            return;
        }

        txtAnimal.setText(animal.getNomeAnimal());
        txtCor.setText(animal.getCor());
        txtEspecie.setText(animal.getEspecie());
        txtIdade.setText(String.valueOf(animal.getIdade()));
        txtSexo.setText(animal.getSexo());
    }

    public static AnimalDTO criarAnimal(
            TextField txtAnimal,
            TextField txtCor,
            TextField txtEspecie,
            TextField txtIdade,
            TextField txtSexo
    ) {

        return new AnimalDTO(
                txtAnimal.getText().trim(),
                txtCor.getText().trim(),
                txtEspecie.getText().trim(),
                Integer.parseInt(txtIdade.getText().trim()),
                txtSexo.getText().trim()
        );
    }

    public static void atualizarAnimal(
            AnimalDTO animal,
            TextField txtAnimal,
            TextField txtCor,
            TextField txtEspecie,
            TextField txtIdade,
            TextField txtSexo
    ) {

        animal.setNomeAnimal(txtAnimal.getText().trim());
        animal.setCor(txtCor.getText().trim());
        animal.setEspecie(txtEspecie.getText().trim());
        animal.setIdade(
                Integer.parseInt(txtIdade.getText().trim())
        );
        animal.setSexo(txtSexo.getText().trim());
    }

    public static void configurarCampoIdade(TextField txtIdade) {

        txtIdade.textProperty().addListener(
                (obs, oldValue, newValue) -> {

                    if (!newValue.matches("\\d*")) {

                        txtIdade.setText(
                                newValue.replaceAll("[^\\d]", "")
                        );
                    }
                }
        );
    }
}