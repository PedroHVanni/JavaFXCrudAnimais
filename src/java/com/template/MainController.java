package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;




public class MainController
{
    @FXML private Button btnSalvar;
    @FXML private Button btnDeletar;
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
}