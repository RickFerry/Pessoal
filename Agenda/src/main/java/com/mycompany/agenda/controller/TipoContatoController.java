  
package com.mycompany.agenda.controller;

import com.jfoenix.controls.JFXTextField;
import com.mycompany.agenda.dao.CrudGenericoDao;
import com.mycompany.agenda.dao.TipoContatoDao;
import com.mycompany.agenda.model.TipoContato;
import com.mycompany.agenda.util.Alerta;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class TipoContatoController implements Initializable, ICadastro {

    @FXML
    private Button btnNovo;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnExcluir;
    @FXML
    private TableView<TipoContato> tbView;
    @FXML
    private JFXTextField tfId;
    @FXML
    private JFXTextField tfDescricao;
    @FXML
    private JFXTextField tfPesquisar;
    @FXML
    private Label lblTitulo;
    
    private CrudGenericoDao<TipoContato> dao = new CrudGenericoDao<>();
    private ObservableList<TipoContato> obsLista = FXCollections.observableArrayList();
    private List<TipoContato> lista;
    private TipoContato objselecionado = new TipoContato();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblTitulo.setText("Cadastro de Tipo de Contato");
        criarColunasTabela();
        atualizarTabela();
        setCamposFormulario();
    }    

    @FXML
    private void incluirRegistro(ActionEvent event) {
        limparCamposFormulario();
    }

    @FXML
    private void salvarRegistro(ActionEvent event) {
        TipoContato objeto = new TipoContato();
        
        if(objselecionado != null){
            objeto.setId(objselecionado.getId());
        }
        
        objeto.setDescricao(tfDescricao.getText());
        
        if (dao.salvar(objeto)){
            Alerta.msgInformacao("Gravado!");
        }else{
            Alerta.msgInformacao("Erro!");
        }
        
        atualizarTabela();
        limparCamposFormulario();
    }

    @FXML
    private void excluirRegistro(ActionEvent event) {
        if(Alerta.msgConfirmacao(tfDescricao.getText())){
            dao.excluir(objselecionado);
            limparCamposFormulario();
            atualizarTabela();
            Alerta.msgInformacao("Exclusão realizada com sucesso!!!");
        }
    }


    @Override
    public void criarColunasTabela() {
        TableColumn<TipoContato, Long> colunaId = new TableColumn<>("ID");
        TableColumn<TipoContato, String> colunaDescricao = new TableColumn<>("DESCRIÇÃO");
        
        colunaId.setMinWidth(40);
        colunaId.setMaxWidth(40);
        tbView.getColumns().addAll(colunaId, colunaDescricao);
        tbView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        colunaId.setCellValueFactory(new PropertyValueFactory("id"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
    }

    @Override
    public void atualizarTabela() {
        obsLista.clear();
        lista = dao.consultar(tfPesquisar.getText(), "TipoContato");
        
        for (TipoContato t : lista) {
            obsLista.add(t);
        }
        
        tbView.getItems().setAll(obsLista);
        tbView.getSelectionModel().selectFirst();
    }

    @Override
    public void setCamposFormulario() {
        if(!tbView.getItems().isEmpty()){
            objselecionado = tbView.getItems().get(tbView.getSelectionModel().getSelectedIndex());
            tfId.setText(String.valueOf(objselecionado.getId()));
            tfDescricao.setText(objselecionado.getDescricao());
        }
    }

    @Override
    public void limparCamposFormulario() {
        objselecionado = null;
        tfId.clear();
        tfDescricao.clear();
        tfDescricao.requestFocus();
    }

    @FXML
    private void filtrarRegistros(KeyEvent event) {
        atualizarTabela();
    }

    @FXML
    private void clicarTabela(MouseEvent event) {
        setCamposFormulario();
    }

    @FXML
    private void moverTabela(KeyEvent event) {
        setCamposFormulario();
    }
}
