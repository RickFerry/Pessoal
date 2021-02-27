package view;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Produto;

public class CarrinhoApp extends Application {

	private AnchorPane pane;
	private static Stage stage;
	private TableView<ItensProperty> tbCarrinho;
	private TableColumn<ItensProperty, String> columnProduto;
	private TableColumn<ItensProperty, Double> columnPreco;
	private Button btnExcluir, btnVoltar, btnConfirmar;
	private static ObservableList<ItensProperty> listItens = FXCollections.observableArrayList();

	public static Stage getStage() {
		return stage;
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
		Scene scene = new Scene(pane, 500, 300);

		scene.getStylesheets().add("/style/Itens.css");

		stage.setScene(scene);
		stage.setTitle("Vitrine - GolFX");
		stage.setResizable(false);
		stage.show();
		initLayout();
		CarrinhoApp.stage = stage;
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		pane = new AnchorPane();

		btnConfirmar = new Button("Confirmar");

		btnExcluir = new Button("Excluir");

		btnVoltar = new Button("Voltar");

		tbCarrinho = new TableView<ItensProperty>();

		columnProduto = new TableColumn<ItensProperty, String>();
		columnPreco = new TableColumn<ItensProperty, Double>();

		columnProduto.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("produto"));
		columnPreco.setCellValueFactory(new PropertyValueFactory<ItensProperty, Double>("preco"));

		tbCarrinho.getColumns().addAll(columnProduto, columnPreco);

		pane.getChildren().addAll(btnConfirmar, btnExcluir, btnVoltar, tbCarrinho);
	}
	
	private void initItens() {
		for (Produto p : VitrineApp.getCarrinho().getProdutos())
			listItens.add(new ItensProperty(p.getProduto(), p.getPreco()));
	}
	
	private void initLayout() {
		btnConfirmar.setLayoutX(215);
		btnConfirmar.setLayoutY(5);

		btnExcluir.setLayoutX(438);
		btnExcluir.setLayoutY(5);
		
		btnVoltar.setLayoutX(8);
		btnVoltar.setLayoutY(5);
		
		tbCarrinho.setPrefSize(480, 250);
		tbCarrinho.setLayoutX(10);
		tbCarrinho.setLayoutY(40);
	}

	private void initListeners() {
		btnConfirmar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Thread thread = new Thread() {
					public void run() {
						try {
							sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Compra realizada com sucesso!");

						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								CarrinhoApp.getStage().close();
								ItemApp.getStage().close();
							}
						});
					};
				};
				thread.start();
			}
		});

		btnVoltar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CarrinhoApp.getStage().close();
				ItemApp.getStage().close();
			}
		});

		btnExcluir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				VitrineApp.getCarrinho()
						.removeProduto(new Produto(tbCarrinho.getSelectionModel().getSelectedItem().getProduto(),
								tbCarrinho.getSelectionModel().getSelectedItem().getPreco()));

				tbCarrinho.getItems().remove(tbCarrinho.getSelectionModel().getSelectedItem());
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
