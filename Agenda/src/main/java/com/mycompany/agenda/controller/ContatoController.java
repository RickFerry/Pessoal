
package com.mycompany.agenda.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.agenda.dao.ComboBoxGenericoDao;
import com.mycompany.agenda.dao.CrudGenericoDao;
import com.mycompany.agenda.model.Cidade;
import com.mycompany.agenda.model.Contato;
import com.mycompany.agenda.model.TipoContato;
import com.mycompany.agenda.util.Alerta;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
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
        
        criarColunasTabela();
        atualizarTabela();
        setCamposFormulario();
        
        cmbCidade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tfUf.setText(cmbCidade.getSelectionModel().getSelectedItem().getUf());
                tfCep.setText(String.valueOf(cmbCidade.getSelectionModel().getSelectedItem().getCep()));                
            }
        });
    }

    @FXML
    private void incluirRegistro(ActionEvent event) {
        limparCamposFormulario();
    }

    @FXML
    private void salvarRegistro(ActionEvent event) {
        Contato contato = new Contato();
        
        if(objSelecionado != null){
            contato.setId(objSelecionado.getId());
        }
        
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
            atualizarTabela();
        }else{
            Alerta.msgInformacao("Erro de gravação!!!");
        }
    }

    @FXML
    private void excluirRegistro(ActionEvent event) {
        if(Alerta.msgConfirmacao(tfDescricao.getText())){
            dao.excluir(objSelecionado);
            limparCamposFormulario();
            atualizarTabela();
            Alerta.msgInformacao("Exclusão realizada com sucesso!!!");
        }
    }

    @Override
    public void criarColunasTabela() {
        TableColumn<Contato, Long> colunaId = new TableColumn<>("ID");
        TableColumn<Contato, String> colunaDescricao = new TableColumn<>("DESCRIÇÃO");
        TableColumn<Contato, TipoContato> colunaTipoContato = new TableColumn<>("TIPO CONTATO");
        TableColumn<Contato, Cidade> colunaCidade = new TableColumn<>("CIDADE");
        TableColumn<Contato, LocalDate> colunaNascimento = new TableColumn<>("NASCIMENTO");
   
        tbView.getColumns().addAll(colunaId, colunaDescricao, colunaTipoContato, colunaCidade, colunaNascimento);
        
        colunaId.setCellValueFactory(new PropertyValueFactory("id")); 
        colunaDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        colunaTipoContato.setCellValueFactory(new PropertyValueFactory("tipoContato"));
        colunaCidade.setCellValueFactory(new PropertyValueFactory("cidade"));
        colunaNascimento.setCellValueFactory(new PropertyValueFactory("nascimento"));
        
        colunaId.prefWidthProperty().bind(tbView.widthProperty().multiply(0.05));
        colunaDescricao.prefWidthProperty().bind(tbView.widthProperty().multiply(0.4));
        colunaTipoContato.prefWidthProperty().bind(tbView.widthProperty().multiply(0.2));
        colunaCidade.prefWidthProperty().bind(tbView.widthProperty().multiply(0.2));
        colunaNascimento.prefWidthProperty().bind(tbView.widthProperty().multiply(0.15));
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        colunaNascimento.setCellFactory(tc -> new TableCell<Contato, LocalDate>(){
            @Override
            protected void updateItem(LocalDate data, boolean empty){
                super.updateItem(data, empty);
                if(data != null){
                    setText(formato.format(data));
                }else{
                    setText(null);
                }
            }
        });
    }

    @Override
    public void atualizarTabela() {
        obslista.clear();
        lista = dao.consultar(tfPesquisar.getText(), "Contato");
        
        for (Contato t : lista) {
            obslista.add(t);
        }
        
        tbView.getItems().setAll(obslista);
        tbView.getSelectionModel().selectFirst();
    }

    @Override
    public void setCamposFormulario() {
        if(!tbView.getItems().isEmpty()){
            objSelecionado = tbView.getItems().get(tbView.getSelectionModel().getSelectedIndex());
            tfId.setText(String.valueOf(objSelecionado.getId()));
            tfDescricao.setText(objSelecionado.getDescricao());
            tfEndereco.setText(objSelecionado.getEndereco());
            tfNumero.setText(String.valueOf(objSelecionado.getNumero()));
            tfTelefone1.setText(String.valueOf(objSelecionado.getTelefone1()));
            tfTelefone2.setText(String.valueOf(objSelecionado.getTelefone2()));
            tfEmail.setText(objSelecionado.getEmail());
            
            chkAtivo.setSelected(objSelecionado.isAtivo());
            
            if(objSelecionado.getSexo().equals("M")){
                rdMasculino.setSelected(true);
            }else{
                rdFeminino.setSelected(true);
            }
            
            dtNascimento.setValue(objSelecionado.getNascimento());
            
            TipoContato tpContato = new TipoContato();
            tpContato.setId(objSelecionado.getTipoContato().getId());
            tpContato.setDescricao(objSelecionado.getTipoContato().getDescricao());
            cmbTipoContato.getSelectionModel().selectFirst();
            cmbTipoContato.setValue(tpContato);
            
            Cidade cdSelecionada = new Cidade();
            cdSelecionada.setId(objSelecionado.getCidade().getId());
            cdSelecionada.setDescricao(objSelecionado.getCidade().getDescricao());
            cdSelecionada.setUf(objSelecionado.getCidade().getUf());
            cdSelecionada.setCep(objSelecionado.getCidade().getCep());
            cmbCidade.getSelectionModel().selectFirst();
            cmbCidade.setValue(cdSelecionada);
        }
    }

    @Override
    public void limparCamposFormulario() {
        tfDescricao.clear();
        tfEndereco.clear();
        tfNumero.clear();
        tfTelefone1.clear();
        tfTelefone2.clear();
        tfEmail.clear();
        rdMasculino.setSelected(true);
        chkAtivo.setSelected(true);
        cmbTipoContato.getSelectionModel().select(-1);
        cmbCidade.getSelectionModel().select(-1);
        dtNascimento.setValue(null);
        objSelecionado = null;
        
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