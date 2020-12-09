package com.mycompany.agenda.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaPrincipalController implements Initializable {

    @FXML
    private MenuBar brMenu;
    @FXML
    private Menu mnArquivo;
    @FXML
    private MenuItem smnContato;
    @FXML
    private MenuItem smnTipoContato;
    @FXML
    private MenuItem smnCidade;
    @FXML
    private MenuItem smnUsuarios;
    @FXML
    private MenuItem smnSair;
    @FXML
    private Menu mnRelatorios;
    @FXML
    private MenuItem smnContatosGeral;
    @FXML
    private MenuItem smnContatosEndereco;
    @FXML
    private MenuItem smnContatosTelefone;
    @FXML
    private MenuItem smnContatosTipo;
    @FXML
    private Menu mnSobre;
    @FXML
    private MenuItem smnSistema;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        smnContato.setGraphic(new ImageView("/icons/MenuContatoIcon.png"));
        smnTipoContato.setGraphic(new ImageView("/icons/ContactType.png"));
        smnCidade.setGraphic(new ImageView("/icons/City.png"));
        smnUsuarios.setGraphic(new ImageView("/icons/User.png"));
        smnSair.setGraphic(new ImageView("/icons/Exit.png"));
        
        smnContatosGeral.setGraphic(new ImageView("/icons/Report.png"));
        smnContatosEndereco.setGraphic(new ImageView("/icons/Report.png"));
        smnContatosTelefone.setGraphic(new ImageView("/icons/Report.png"));
        smnContatosTipo.setGraphic(new ImageView("/icons/Report.png"));
        
        smnSistema.setGraphic(new ImageView("/icons/System.png"));
    }    

    @FXML
    private void acessarContato(ActionEvent event) {
        abrirFormulario("contato_view");
    }

    @FXML
    private void acessarTipoContato(ActionEvent event) {
        abrirFormulario("tipo_contato_view");
    }

    @FXML
    private void acessarCidade(ActionEvent event) {
        abrirFormulario("cidade_view");
    }

    @FXML
    private void acessarUsuario(ActionEvent event) {
        abrirFormulario("usuario_view");
    }

    @FXML
    private void acessarSair(ActionEvent event) {
    }

    @FXML
    private void acessarContatoGeral(ActionEvent event) {
    }

    @FXML
    private void acessarContatoEndereco(ActionEvent event) {
    }

    @FXML
    private void acessarContatoTelefone(ActionEvent event) {
    }

    @FXML
    private void acessarContatoTipo(ActionEvent event) {
    }

    @FXML
    private void acessarSistema(ActionEvent event) {
    }
    
    public void abrirFormulario(String formulario) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/"+formulario+".fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Formulário");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}