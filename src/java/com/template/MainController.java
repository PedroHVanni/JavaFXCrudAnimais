package com.template;

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

    // Botões da tela
    @FXML private Button btnSalvar;
    @FXML private Button btnDeletar;
    @FXML private Button btnLimpar;
    @FXML private Button btnAtualizar;

    // Campos de texto para entrada dos dados
    @FXML private TextField txtAnimal;
    @FXML private TextField txtCor;
    @FXML private TextField txtEspecie;
    @FXML private TextField txtIdade;
    @FXML private TextField txtSexo;

    // Tabela que exibirá os animais cadastrados
    @FXML private TableView<AnimaisDTO> tblAnimal;

    // Colunas da tabela
    @FXML private TableColumn<AnimaisDTO, Integer> colId;
    @FXML private TableColumn<AnimaisDTO, String> colAnimal;
    @FXML private TableColumn<AnimaisDTO, String> colCor;
    @FXML private TableColumn<AnimaisDTO, String> colEspecie;
    @FXML private TableColumn<AnimaisDTO, String> colIdade;
    @FXML private TableColumn<AnimaisDTO, String> colSexo;

    @FXML private Label lblTotalRegistros;

    /**
     * Carrega todos os animais do banco de dados
     * e atualiza a tabela.
     */
    @FXML
    private void carregarAnimais() {

        AnimaisDAO dao = new AnimaisDAO();

        List<AnimaisDTO> lista = dao.listarAnimais();

        tblAnimal.setItems(
                FXCollections.observableArrayList(lista)
        );

        tblAnimal.refresh();

        lblTotalRegistros.setText(
                "Total de registros: " + lista.size()
        );
    }

    /**
     * Evento do botão Salvar.
     * Cadastra um novo animal no banco.
     */
    @FXML
    private void BtnSalvarAction(ActionEvent event) {

        try {

            // Cria objeto para armazenar os dados digitados
            AnimaisDTO animal = new AnimaisDTO();

            animal.setAnimal(txtAnimal.getText());
            animal.setCor(txtCor.getText());
            animal.setEspecie(txtEspecie.getText());
            animal.setIdade(txtIdade.getText());
            animal.setSexo(txtSexo.getText());

            // Salva no banco
            AnimaisDAO dao = new AnimaisDAO();
            dao.cadastrarAnimal(animal);

            // Atualiza a tabela
            carregarAnimais();

            // Limpa os campos
            btnLimparAction(event);

        } catch (Exception e) {

            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    /**
     * Evento do botão Deletar.
     * Remove o animal selecionado da tabela e do banco.
     */
    @FXML
    private void BtnDeletarAction(ActionEvent event) {

        // Obtém o item selecionado
        AnimaisDTO animalSelecionado =
                tblAnimal.getSelectionModel().getSelectedItem();

        if (animalSelecionado != null) {

            // Exclui do banco
            AnimaisDAO dao = new AnimaisDAO();
            dao.excluirAnimal(animalSelecionado.getId());

            // Atualiza tabela
            carregarAnimais();

            // Limpa os campos
            btnLimparAction(event);
        }
    }

    /**
     * Evento do botão Atualizar.
     * Atualiza os dados do animal selecionado.
     */
    @FXML
    private void btnAtualizarAction(ActionEvent event) {

        AnimaisDTO animalSelecionado =
                tblAnimal.getSelectionModel().getSelectedItem();

        if (animalSelecionado == null) {
            System.out.println("Nenhum animal selecionado!");
            return;
        }

        animalSelecionado.setAnimal(txtAnimal.getText());
        animalSelecionado.setCor(txtCor.getText());
        animalSelecionado.setEspecie(txtEspecie.getText());
        animalSelecionado.setIdade(txtIdade.getText());
        animalSelecionado.setSexo(txtSexo.getText());

        AnimaisDAO dao = new AnimaisDAO();
        dao.alterarAnimal(animalSelecionado);

        carregarAnimais();

        btnLimparAction(event);

        System.out.println("Atualização concluída!");
    }

    /**
     * Evento do botão Limpar.
     * Limpa todos os campos da tela.
     */
    @FXML
    private void btnLimparAction(ActionEvent event) {

        txtAnimal.clear();
        txtCor.clear();
        txtEspecie.clear();
        txtIdade.clear();
        txtSexo.clear();

        tblAnimal.getSelectionModel().clearSelection();

        // Volta o cursor para o primeiro campo
        txtAnimal.requestFocus();
    }
    /**
     * Carrega os dados do animal selecionado
     * nos campos de texto.
     */
    @FXML
    private void selecionarAnimal() {

        AnimaisDTO animal =
                tblAnimal.getSelectionModel().getSelectedItem();

        if (animal != null) {

            txtAnimal.setText(animal.getAnimal());
            txtCor.setText(animal.getCor());
            txtEspecie.setText(animal.getEspecie());
            txtIdade.setText(animal.getIdade());
            txtSexo.setText(animal.getSexo());
        }
    }

    /**
     * Método executado automaticamente ao abrir a tela.
     * Configura as colunas da tabela e carrega os dados.
     */
    @FXML
    private void initialize() {

        // Liga as colunas aos atributos da classe AnimaisDTO
        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        colAnimal.setCellValueFactory(
                new PropertyValueFactory<>("animal"));

        colCor.setCellValueFactory(
                new PropertyValueFactory<>("cor"));

        colEspecie.setCellValueFactory(
                new PropertyValueFactory<>("especie"));

        colIdade.setCellValueFactory(
                new PropertyValueFactory<>("idade"));

        colSexo.setCellValueFactory(
                new PropertyValueFactory<>("sexo"));

        // Carrega os dados do banco na tabela
        carregarAnimais();

        // Detecta quando o usuário seleciona uma linha da tabela
        tblAnimal.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> {

                    if (newValue != null) {
                        selecionarAnimal();
                    }
                });

        // Campo idade aceita apenas números
        txtIdade.textProperty().addListener((obs, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                txtIdade.setText(
                        newValue.replaceAll("[^\\d]", "")
                );
            }
        });

        // Cursor inicia no primeiro campo
                txtAnimal.requestFocus();
    }

}