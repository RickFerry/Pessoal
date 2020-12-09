
package com.mycompany.agenda.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.agenda.dao.ComboBoxGenericoDao;
import com.mycompany.agenda.dao.CrudGenericoDao;
import com.mycompany.agenda.dao.TipoContatoDao;
import com.mycompany.agenda.model.Cidade;
import com.mycompany.agenda.model.Contato;
import com.mycompany.agenda.model.TipoContato;
import com.mycompany.agenda.util.Alerta;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ContatoController implements Initializable, ICadastro {

    @FXML
    private JFXTextField tfId;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnExcluir;
    @FXML
    private TableView<Contato> tbView;
    @FXML
    private JFXTextField tfPesquisar;
    @FXML
    private JFXTextField tfEndereco;
    @FXML
    private JFXTextField tfNumero;
    @FXML
    private JFXTextField tfUf;
    @FXML
    private JFXComboBox<Cidade> cmbCidade;
    @FXML
    private JFXTextField tfCep;
    @FXML
    private JFXTextField tfTelefone1;
    @FXML
    private JFXTextField tfTelefone2;
    @FXML
    private JFXComboBox<TipoContato> cmbTipoContato;
    @FXML
    private Label lblTitulo;    
    @FXML
    private JFXCheckBox chkAtivo;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXRadioButton rdFeminino;
    @FXML
    private ToggleGroup sexo;
    @FXML
    private JFXRadioButton rdMasculino;
    @FXML
    private JFXDatePicker dtNascimento;
        @FXML
    private JFXTextField tfDescricao;
    

    private ComboBoxGenericoDao<TipoContato> comboBoxTipoContatodao = new ComboBoxGenericoDao();
    private ComboBoxGenericoDao<Cidade> comboBoxCidadedao = new ComboBoxGenericoDao();
    private CrudGenericoDao dao = new CrudGenericoDao();
    private List<Contato> lista;
    private ObservableList<Contato> obslista = FXCollections.observableArrayList();
    private Contato objSelecionado = new Contato();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblTitulo.setText("Cadastro de Contatos");
        cmbTipoContato.setItems(comboBoxTipoContatodao.comboBox("TipoContato"));
        cmbCidade.setItems(comboBoxCidadedao.comboBox("Cidade"));
        
        cmbCidade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tfUf.setText(cmbCidade.getSelectionModel().getSelectedItem().getUf());
                tfCep.setText(String.valueOf(cmbCidade.getSelectionModel().getSelectedItem().getCep()));
            }});
    }       

    @FXML
    private void incluirRegistro(ActionEvent event) {
    }

    @FXML
    private void salvarRegistro(ActionEvent event) {
        Contato contato = new Contato();
        
        contato.setDescricao(tfDescricao.getText());
        contato.setEndereco(tfEndereco.getText());
        contato.setNumero(Integer.parseInt(tfNumero.getText()));
        contato.setCidade(cmbCidade.getSelectionModel().getSelectedItem());
        contato.setTipoContato(cmbTipoContato.getSelectionModel().getSelectedItem());
        contato.setEmail(tfEmail.getText());
        contato.setTelefone1(Long.parseLong(tfTelefone1.getText()));
        contato.setTelefone2(Long.parseLong(tfTelefone2.getText()));
        
        LocalDate dtnascimento = dtNascimento.getValue();
        contato.setNascimento(dtnascimento);
        
        if(chkAtivo.isSelected()){
            contato.setAtivo(true);
        }else{
            contato.setAtivo(false);
        }
        
        if(rdMasculino.isSelected()){
            contato.setSexo("M");
        }else{
            contato.setSexo("F");
        }
        
        if(dao.salvar(contato)){
            Alerta.msgInformacao("Gração bem sucedida!!!");
        }else{
            Alerta.msgInformacao("Erro de gravação!!!");
        }
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
