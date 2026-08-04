package com.template.controller;

import com.template.validator.AnimalValidador;
import com.template.model.dto.AnimalDTO;
import com.template.model.dao.AnimaisDAO;
import com.template.util.DialogUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class MainController {

    @FXML private Button btnSalvar;
    @FXML private Button btnDeletar;
    @FXML private Button btnLimpar;
    @FXML private Button btnAtualizar;

    @FXML private TextField txtAnimal;
    @FXML private TextField txtCor;
    @FXML private TextField txtEspecie;
    @FXML private TextField txtIdade;
    @FXML private TextField txtSexo;

    @FXML private TableView<AnimalDTO> tblAnimal;

    @FXML private TableColumn<AnimalDTO, Integer> colId;
    @FXML private TableColumn<AnimalDTO, String> colAnimal;
    @FXML private TableColumn<AnimalDTO, String> colCor;
    @FXML private TableColumn<AnimalDTO, String> colEspecie;
    @FXML private TableColumn<AnimalDTO, Integer> colIdade;
    @FXML private TableColumn<AnimalDTO, String> colSexo;

    @FXML private Label lblTotalRegistros;
    @FXML private Label lblMensagem;

    private final AnimaisDAO animaisDAO = new AnimaisDAO();

    @FXML
    private void carregarAnimais() {
        try {
            List<AnimalDTO> lista = animaisDAO.listarAnimais();

            tblAnimal.setItems(FXCollections.observableArrayList(lista));

            lblTotalRegistros.setText("Total de registros: " + lista.size());

        } catch (Exception e) {
            DialogUtil.exibirErro(
                    "Erro ao Carregar",
                    "Falha ao buscar animais: " + e.getMessage()
            );
        }
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        if (!validarCampos()) {
            return;
        }

        try {
            int idade = Integer.parseInt(txtIdade.getText().trim());

            AnimalDTO novoAnimal = new AnimalDTO(
                    txtAnimal.getText().trim(),
                    txtCor.getText().trim(),
                    txtEspecie.getText().trim(),
                    idade,
                    txtSexo.getText().trim()
            );

            boolean sucesso = animaisDAO.cadastrarAnimal(novoAnimal);

            if (sucesso) {
                DialogUtil.exibirInformacao("Sucesso", "Animal cadastrado com sucesso!");
                carregarAnimais();
                btnLimparAction(event);
            } else {
                DialogUtil.exibirAviso("Atenção", "Não foi possível realizar o cadastro.");
            }

        } catch (NumberFormatException e) {
            DialogUtil.exibirAviso("Campo Inválido", "A idade deve ser um número inteiro válido.");
        } catch (Exception e) {
            DialogUtil.exibirErro("Erro de Cadastro", "Ocorreu um erro ao salvar o animal: " + e.getMessage());
        }
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {
        AnimalDTO animalSelecionado = tblAnimal.getSelectionModel().getSelectedItem();

        if (animalSelecionado == null) {
            DialogUtil.exibirAviso("Seleção Pendente", "Por favor, selecione um animal na tabela para excluir.");
            return;
        }

        boolean confirmou = DialogUtil.exibirConfirmacao("Confirmar Exclusão",
                "Tem certeza que deseja excluir o animal " + animalSelecionado.getNomeAnimal() + "?");

        if (confirmou) {
            try {
                boolean sucesso = animaisDAO.excluirAnimal(animalSelecionado.getId());

                if (sucesso) {
                    DialogUtil.exibirInformacao("Sucesso", "Animal removido com sucesso!");
                    carregarAnimais();
                    btnLimparAction(event);
                } else {
                    DialogUtil.exibirAviso("Atenção", "Não foi possível localizar o registro para exclusão.");
                }
            } catch (Exception e) {
                DialogUtil.exibirErro("Erro de Exclusão", "Erro ao tentar excluir o animal: " + e.getMessage());
            }
        }
    }

    @FXML
    private void btnAtualizarAction(ActionEvent event) {
        AnimalDTO animalSelecionado = tblAnimal.getSelectionModel().getSelectedItem();

        if (animalSelecionado == null) {
            DialogUtil.exibirAviso("Seleção Pendente", "Selecione um animal na tabela para atualizar.");
            return;
        }

        if (!validarCampos()) {
            return;
        }

        try {
            int idade = Integer.parseInt(txtIdade.getText().trim());

            animalSelecionado.setNomeAnimal(txtAnimal.getText().trim());
            animalSelecionado.setCor(txtCor.getText().trim());
            animalSelecionado.setEspecie(txtEspecie.getText().trim());
            animalSelecionado.setIdade(idade);
            animalSelecionado.setSexo(txtSexo.getText().trim());

            boolean sucesso = animaisDAO.alterarAnimal(animalSelecionado);

            if (sucesso) {
                DialogUtil.exibirInformacao("Sucesso", "Dados do animal atualizados com sucesso!");
                carregarAnimais();
                btnLimparAction(event);
            } else {
                DialogUtil.exibirAviso("Atenção", "Não foi possível atualizar o registro.");
            }

        } catch (NumberFormatException e) {
            DialogUtil.exibirAviso("Campo Inválido", "A idade deve ser um número inteiro válido.");
        } catch (Exception e) {
            DialogUtil.exibirErro("Erro de Atualização", "Erro ao tentar atualizar os dados: " + e.getMessage());
        }
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        txtAnimal.clear();
        txtCor.clear();
        txtEspecie.clear();
        txtIdade.clear();
        txtSexo.clear();

        tblAnimal.getSelectionModel().clearSelection();
        lblMensagem.setText("");
        txtAnimal.requestFocus();
    }

    @FXML
    private void selecionarAnimal() {
        AnimalDTO animal = tblAnimal.getSelectionModel().getSelectedItem();

        if (animal != null) {
            txtAnimal.setText(animal.getNomeAnimal());
            txtCor.setText(animal.getCor());
            txtEspecie.setText(animal.getEspecie());
            txtIdade.setText(String.valueOf(animal.getIdade()));
            txtSexo.setText(animal.getSexo());
        }
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

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAnimal.setCellValueFactory(new PropertyValueFactory<>("nomeAnimal"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));

        carregarAnimais();

        tblAnimal.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> {
                    if (newValue != null) {
                        selecionarAnimal();
                    }
                });

        txtIdade.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtIdade.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        txtAnimal.requestFocus();
    }
}