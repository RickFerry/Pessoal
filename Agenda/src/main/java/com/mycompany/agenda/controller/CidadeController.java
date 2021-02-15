
package com.mycompany.agenda.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.agenda.dao.CidadeDao;
import com.mycompany.agenda.dao.CrudGenericoDao;
import com.mycompany.agenda.dao.TipoContatoDao;
import com.mycompany.agenda.model.Cidade;
import com.mycompany.agenda.model.TipoContato;
import com.mycompany.agenda.util.Alerta;
import com.mycompany.agenda.util.Uf;
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

public class CidadeController implements Initializable, ICadastro {

    @FXML
    private JFXTextField tfId;
    @FXML
    private JFXTextField tfDescricao;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnExcluir;
    @FXML
    private TableView<Cidade> tbView;
    @FXML
    private JFXTextField tfPesquisar;
    @FXML
    private JFXComboBox<String> cmbUf;
    @FXML
    private JFXTextField txtCep;
    @FXML
    private Label lblTitulo;
    
    private CrudGenericoDao<Cidade> dao = new CrudGenericoDao<>();
    private ObservableList<Cidade> obsLista = FXCollections.observableArrayList();
    private List<Cidade> lista;
    private Cidade objselecionado = new Cidade();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblTitulo.setText("Cadastro de Cidades");
        cmbUf.setItems(Uf.gerarUf());
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
        Cidade objeto = new Cidade();
        
        if(objselecionado != null){
            objeto.setId(objselecionado.getId());
        }
        
        objeto.setDescricao(tfDescricao.getText());
        objeto.setCep(Long.parseLong(txtCep.getText()));
        objeto.setUf(cmbUf.getValue());
        
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
        if(CidadeDao.liberaExclusao(objselecionado.getId())){
            if(Alerta.msgConfirmacao(tfDescricao.getText())){
                dao.excluir(objselecionado);
                limparCamposFormulario();
                atualizarTabela();
                Alerta.msgInformacao("Exclusão realizada com sucesso!!!");
            }
        }else{
            Alerta.msgInformacao("Não autorizado.\nTipo de cidade em uso.");
        }
    }

    @Override
    public void criarColunasTabela() {
        TableColumn<Cidade, Long> colunaId = new TableColumn<>("ID");
        colunaId.setMinWidth(40);
        colunaId.setMaxWidth(40);
        
        TableColumn<Cidade, String> colunaDescricao = new TableColumn<>("DESCRIÇÃO");
        
        TableColumn<Cidade, String> colunaUf = new TableColumn<>("UF");
        colunaUf.setMinWidth(40);
        colunaUf.setMaxWidth(40);
        
        TableColumn<Cidade, String> colunaCep = new TableColumn<>("CEP");

        tbView.getColumns().addAll(colunaId, colunaDescricao, colunaUf, colunaCep);
        tbView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        colunaId.setCellValueFactory(new PropertyValueFactory("id"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        colunaUf.setCellValueFactory(new PropertyValueFactory("uf"));
        colunaCep.setCellValueFactory(new PropertyValueFactory("cep"));
    }

    @Override
    public void atualizarTabela() {
        obsLista.clear();
        lista = dao.consultar(tfPesquisar.getText(), "Cidade");
        
        for (Cidade t : lista) {
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
            txtCep.setText(String.valueOf(objselecionado.getCep()));
            cmbUf.setValue(objselecionado.getUf());
        }
    }

    @Override
    public void limparCamposFormulario() {
        objselecionado = null;
        tfId.clear();
        tfDescricao.clear();
        txtCep.clear();
        cmbUf.getSelectionModel().select(-1);
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
