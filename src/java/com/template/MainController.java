package com.template;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class MainController
{
    @FXML private Button btnSalvar;
    @FXML private Button btnDeletar;
    @FXML private Button btnLimpar;
    @FXML private Button btnAtualizar;

    @FXML private TextField txtAnimal;
    @FXML private TextField txtCor;
    @FXML private TextField txtEspecie;
    @FXML private TextField txtIdade;
    @FXML private TextField txtSexo;

    @FXML private TableView<UsuarioDTO> tblAnimal;
    @FXML private TableColumn<UsuarioDTO, String> colCor;
    @FXML private TableColumn<UsuarioDTO, String> colEspecie;
    @FXML private TableColumn<UsuarioDTO, String> colIdade;
    @FXML private TableColumn<UsuarioDTO, String> colSexo;

    @FXML
    private void CarregarAnimais () {
        AnimaisDTO objAnimaisDao = new AnimaisDAO();
        ArrayList<AnimaisDTO> listaAnimais = objAnimaisDao.listarAnimais();
        tblAnimal.setItems(FXCollections.observableArrayList(listaAnimais));
    }

    @FXML
    private void BtnSalvarAction(ActionEvent event) {
        String animal = txtAnimal.getText();
        String cor = txtCor.getText();
        String especie = txtEspecie.getText();
        Int idade = Integer.parseInt(txtIdade.getText());
        String sexo = txtSexo.getText();
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        txtAnimal.clear();
        txtCor.clear();
        txtEspecie.clear();
        txtSexo.clear();
        txtIdade.clear();
    }

    @FXML
    private void BtnDeletarAction(ActionEvent event) {
        UsuarioDTO animalSelecionado = tblAnimal.getSelectionModel().getSelectedItem();

        if (animalSelecionado != null) {
            tblAnimal.getItems().remove(animalSelecionado);

            btnLimparAction(event);
        }
    }
    @FXML
    private void btnAtualizarAction(ActionEvent event) {
        AnimaisDTO animalSelecionado = tblAnimal.getSelectionModel().getSelectedItem();

        if (animalSelecionado != null) {
            animalSelecionado.setCor(txtCor.getText());
            animalSelecionado.setEspecie(txtEspecie.getText());
            animalSelecionado.setIdade(txtIdade.getText());
            animalSelecionado.setSexo(txtSexo.getText());

            tblAnimal.refresh();
            btnLimparAction(event);
        }
    }

    @FXML
    private void initialize()
    {
        // vincula uma coluna do banco ao do projeto
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        carregarAnimais();
    }

    @FXML
    private void carregarAnimais(){
        AnimaisDTO animaisDTO = tblAnimal.getSelectionModel().getSelectedItem();

        if(animaisDTO != null){
            txtCor.setText( String.valueOf(animaisDTO.getId()));
            txtIdade.setText( String.valueOf(animaisDTO.getId()));
            txtSexo.setText( String.valueOf(animaisDTO.getId()));
            txtEspecie.setText( String.valueOf(animaisDTO.getId()));
            txtAnimal.setText( String.valueOf(animaisDTO.getId()));
        }
    }
}