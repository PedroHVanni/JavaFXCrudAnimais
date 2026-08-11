
package com.template.util;

import com.template.model.dto.AnimalDTO;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AnimalTableUtil {

    private AnimalTableUtil() {
        // Impede a criação de objetos dessa classe
    }

    public static void configurarTabela(
            TableView<AnimalDTO> tabela,
            TableColumn<AnimalDTO, Integer> colId,
            TableColumn<AnimalDTO, String> colAnimal,
            TableColumn<AnimalDTO, String> colCor,
            TableColumn<AnimalDTO, String> colEspecie,
            TableColumn<AnimalDTO, Integer> colIdade,
            TableColumn<AnimalDTO, String> colSexo
    ) {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        colAnimal.setCellValueFactory(
                new PropertyValueFactory<>("nomeAnimal")
        );

        colCor.setCellValueFactory(
                new PropertyValueFactory<>("cor")
        );

        colEspecie.setCellValueFactory(
                new PropertyValueFactory<>("especie")
        );

        colIdade.setCellValueFactory(
                new PropertyValueFactory<>("idade")
        );

        colSexo.setCellValueFactory(
                new PropertyValueFactory<>("sexo")
        );
    }
}
