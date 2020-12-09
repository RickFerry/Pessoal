
package com.mycompany.agenda.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class UsuarioController implements Initializable, ICadastro {

    @FXML
    private Button btnNovo;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnExcluir;
    @FXML
    private TableView<?> tbView;
    @FXML
    private JFXTextField tfId;
    @FXML
    private JFXTextField tfDescricao;
    @FXML
    private JFXPasswordField pfPassword;
    @FXML
    private JFXTextField tfPesquisar;
    @FXML
    private Label lblTitulo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void incluirRegistro(ActionEvent event) {
    }

    @FXML
    private void salvarRegistro(ActionEvent event) {
    }

    @FXML
    private void excluirRegistro(ActionEvent event) {
    }


    @Override
    public void criarColunasTabela() {
    
    }

    @Override
    public void atualizarTabela() {
    
    }

    @Override
    public void setCamposFormulario() {
    
    }

    @Override
    public void limparCamposFormulario() {
    
    }

    @FXML
    private void filtrarRegistros(KeyEvent event) {
    }

    @FXML
    private void clicarTabela(MouseEvent event) {
    }

    @FXML
    private void moverTabela(KeyEvent event) {
    }
    
}
