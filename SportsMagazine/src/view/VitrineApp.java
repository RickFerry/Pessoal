package view;

import controller.Carrinho;
import controller.Vitrine;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Produto;

public class VitrineApp extends Application{
	
	private AnchorPane pane;
	private static Stage stage;
	private TextField txtPesquisa;
	private DropShadow ds;
	private TableView<ItensProperty> tbVitrine;
	private TableColumn<ItensProperty, String> clProduto;
	private TableColumn<ItensProperty, Double> clPreco;
	private static ObservableList<ItensProperty> listItens = FXCollections.observableArrayList();
	private static Carrinho carrinho;
	
	public Stage getStage() {
		return stage;
	}
	
	public static Carrinho getCarrinho() {
		return carrinho;
	}

	public class ItensProperty {
		private SimpleStringProperty produto;
		private SimpleDoubleProperty preco;
		
		public ItensProperty(String produto, double preco) {
			this.produto = new SimpleStringProperty(produto);
			this.preco = new SimpleDoubleProperty(preco);
		}
		
		public String getProduto() {
			return produto.get();
		}
		
		public void setProduto(String produto) {
			this.produto.set(produto);
		}
		
		public double getPreco() {
			return preco.get();
		}
		
		public void setPreco(double preco) {
			this.preco.set(preco);
		}
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initItens();
		initListeners();
		
		Scene scene = new Scene(pane, 800, 600);
		
		tbVitrine.setItems(listItens);
		scene.getStylesheets().add("/style/Vitrine.css");
		
		stage.setScene(scene);
		stage.setTitle("Vitrine - GolFX");
		stage.setResizable(false);
		stage.show();
		initLayout();
		VitrineApp.stage = stage;
	}
	
	@SuppressWarnings("unchecked")
	private void initComponents() {
		pane = new AnchorPane();
		
		txtPesquisa = new TextField();
		
		// ou: txtPesquisa.setEffect(new DropShadow());
		ds = new DropShadow();
		
		tbVitrine = new TableView<ItensProperty>();

		clProduto = new TableColumn<ItensProperty, String>();
		clPreco = new TableColumn<ItensProperty, Double>();
		
		tbVitrine.getColumns().addAll(clProduto, clPreco);		
		pane.getChildren().addAll(txtPesquisa, tbVitrine);
		
		carrinho = new Carrinho();
		//setCarrinho(new CarrinhoController());
		
		clProduto.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("produto"));
		clPreco.setCellValueFactory(new PropertyValueFactory<ItensProperty, Double>("preco"));		
	}
	
	private void initLayout() {
		ds.setSpread(0.5);
		ds.setColor(Color.AQUAMARINE);
		
		txtPesquisa.setEffect(ds);
		txtPesquisa.setLayoutX(640);
		txtPesquisa.setLayoutY(10);
		txtPesquisa.setPromptText("Digite o item para pesquisa");
		
		tbVitrine.setPrefSize(780, 550);
		tbVitrine.setLayoutX(10);
		tbVitrine.setLayoutY(40);
	}
	
	private void initItens() {
		
		Vitrine v = new Vitrine();
		
		v.addProdutos(new Produto("Bola Topper", 15.00), new Produto("Luvas Umbro", 9.00),
				new Produto("Camisa Esportiva", 40.00), new Produto("Chuteira Nike Mercurial", 199.00),
				new Produto("Caneleira Topper", 10.00));
		
		for (Produto p : v.getProdutos())
			listItens.add(new ItensProperty(p.getProduto(), p.getPreco()));
	}
	
	private ObservableList<ItensProperty> findItems() {
		
		ObservableList<ItensProperty> itensEncontrados = FXCollections.observableArrayList();
		
		for (ItensProperty itens : listItens) {
			if(itens.getProduto().contains(txtPesquisa.getText())) {
				itensEncontrados.add(itens);
			}
		}
		return itensEncontrados;
	}
	
	private void initListeners() {
		 
		 tbVitrine.getSelectionModel().selectedItemProperty().addListener(
				 new ChangeListener<ItensProperty>() {
					@Override
					public void changed(ObservableValue<? extends ItensProperty> value,
							ItensProperty oldItem, ItensProperty newItem) {
						
						ItemApp.setProduto(new Produto(newItem.getProduto(), newItem.getPreco()));
						ItemApp.setIndex(tbVitrine.getSelectionModel().getSelectedIndex());
						
						try {
							new ItemApp().start(new Stage());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
		});
		 
		 txtPesquisa.setOnAction(new EventHandler<ActionEvent>() {	
			 @Override
			public void handle(ActionEvent event) {
				if (!txtPesquisa.getText().equals("")) {
					tbVitrine.setItems(findItems());
				}else{
					tbVitrine.setItems(listItens);
				}
			}
		}); 
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}