package com.template.controller;

import com.template.model.dto.AnimalDTO;
import com.template.service.AnimalService;
import com.template.util.AnimalFormUtil;
import com.template.util.AnimalTableUtil;
import com.template.util.DialogUtil;
import com.template.validator.AnimalValidador;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class MainController {

    @FXML private Button btnSalvar, btnDeletar, btnLimpar, btnAtualizar;
    @FXML private TextField txtAnimal, txtCor, txtEspecie, txtIdade, txtSexo;
    @FXML private TableView<AnimalDTO> tblAnimal;
    @FXML private TableColumn<AnimalDTO, Integer> colId, colIdade;
    @FXML private TableColumn<AnimalDTO, String> colAnimal, colCor, colEspecie, colSexo;
    @FXML private Label lblTotalRegistros, lblMensagem;

    private final AnimalService animalService = new AnimalService();

    @FXML
    private void initialize() {
        AnimalTableUtil.configurarTabela(
                tblAnimal, colId, colAnimal, colCor,
                colEspecie, colIdade, colSexo
        );

        AnimalFormUtil.configurarCampoIdade(txtIdade);

        tblAnimal.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {
                    if (novo != null) {
                        AnimalFormUtil.preencherCampos(
                                novo, txtAnimal, txtCor, txtEspecie, txtIdade, txtSexo
                        );
                    }
                }
        );

        carregarAnimais();
        txtAnimal.requestFocus();
    }

    private void carregarAnimais() {
        try {
            List<AnimalDTO> lista = animalService.listarAnimais();
            tblAnimal.setItems(FXCollections.observableArrayList(lista));
            lblTotalRegistros.setText("Total de registros: " + lista.size());
        } catch (Exception e) {
            DialogUtil.exibirErro("Erro ao Carregar",
                    "Falha ao buscar animais: " + e.getMessage());
        }
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        if (!validarCampos()) return;

        try {
            AnimalDTO animal = AnimalFormUtil.criarAnimal(
                    txtAnimal, txtCor, txtEspecie, txtIdade, txtSexo
            );

            if (animalService.cadastrarAnimal(animal)) {
                DialogUtil.exibirInformacao("Sucesso", "Animal cadastrado com sucesso!");
                carregarAnimais();
                limparFormulario();
            } else {
                DialogUtil.exibirAviso("Atenção", "Não foi possível realizar o cadastro.");
            }

        } catch (Exception e) {
            DialogUtil.exibirErro("Erro de Cadastro",
                    "Ocorreu um erro ao salvar o animal: " + e.getMessage());
        }
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {
        AnimalDTO animal = tblAnimal.getSelectionModel().getSelectedItem();

        if (animal == null) {
            DialogUtil.exibirAviso("Seleção Pendente",
                    "Selecione um animal na tabela para excluir.");
            return;
        }

        if (!DialogUtil.exibirConfirmacao(
                "Confirmar Exclusão",
                "Tem certeza que deseja excluir o animal " + animal.getNomeAnimal() + "?")) {
            return;
        }

        try {
            if (animalService.excluirAnimal(animal.getId())) {
                DialogUtil.exibirInformacao("Sucesso", "Animal removido com sucesso!");
                carregarAnimais();
                limparFormulario();
            } else {
                DialogUtil.exibirAviso("Atenção",
                        "Não foi possível localizar o registro para exclusão.");
            }

        } catch (Exception e) {
            DialogUtil.exibirErro("Erro de Exclusão",
                    "Erro ao tentar excluir o animal: " + e.getMessage());
        }
    }

    @FXML
    private void btnAtualizarAction(ActionEvent event) {
        AnimalDTO animal = tblAnimal.getSelectionModel().getSelectedItem();

        if (animal == null) {
            DialogUtil.exibirAviso("Seleção Pendente",
                    "Selecione um animal na tabela para atualizar.");
            return;
        }

        if (!validarCampos()) return;

        try {
            AnimalFormUtil.atualizarAnimal(
                    animal, txtAnimal, txtCor, txtEspecie, txtIdade, txtSexo
            );

            if (animalService.alterarAnimal(animal)) {
                DialogUtil.exibirInformacao("Sucesso",
                        "Dados do animal atualizados com sucesso!");
                carregarAnimais();
                limparFormulario();
            } else {
                DialogUtil.exibirAviso("Atenção",
                        "Não foi possível atualizar o registro.");
            }

        } catch (Exception e) {
            DialogUtil.exibirErro("Erro de Atualização",
                    "Erro ao tentar atualizar os dados: " + e.getMessage());
        }
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        limparFormulario();
    }

    private void limparFormulario() {
        AnimalFormUtil.limparCampos(
                txtAnimal, txtCor, txtEspecie, txtIdade, txtSexo
        );

        tblAnimal.getSelectionModel().clearSelection();
        lblMensagem.setText("");
        txtAnimal.requestFocus();
    }

    private boolean validarCampos() {
        String mensagem = AnimalValidador.validar(
                txtAnimal.getText(),
                txtCor.getText(),
                txtEspecie.getText(),
                txtIdade.getText(),
                txtSexo.getText()
        );

        if (mensagem != null) {
            DialogUtil.exibirAviso("Validação", mensagem);
            return false;
        }

        return true;
    }
}