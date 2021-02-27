package view;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Produto;

public class ItemApp extends Application {

	private AnchorPane pane;
	private InnerShadow is;
	private ImageView imgItem;
	private Label lblPreco, lblDescricao;
	private Button btnAddCarrinho;
	public static Stage stage;
	private static Produto produto;
	private static int index;
	private static String[] images = { "/images/bola-topper.jpg", "/images/camisa-esportiva.jpg",
			"/images/caneleira-topper.jpg", "/images/Chuteira-Nike-Mercurial.jpg", "/images/luvas-umbro.jpg" };

	public static Produto getProduto() {
		return produto;
	}

	public static void setProduto(Produto produto) {
		ItemApp.produto = produto;
	}

	public static Stage getStage() {
		return stage;
	}

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		ItemApp.index = index;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initListeners();

		Scene scene = new Scene(pane, 600, 400);
		scene.getStylesheets().add("/style/Itens.css");

		initTimeline();
		initTransition();

		stage.setScene(scene);
		stage.setTitle("Vitrine - GolFX");
		stage.setResizable(false);
		stage.show();
		initLayout();
		ItemApp.stage = stage;
	}
	
	private void initComponents() {
		pane = new AnchorPane();
		lblDescricao = new Label("Descrição: ");
		lblPreco = new Label("Preço: ");
		btnAddCarrinho = new Button("Adiciona ao carrinho");
		
		is = new InnerShadow();

		imgItem = new ImageView(new Image(images[index]));
		imgItem.setEffect(new Reflection());
		
		pane.getChildren().addAll(imgItem, lblDescricao, lblPreco, btnAddCarrinho);
	}
	
	private void initLayout() {
		lblDescricao.setLayoutX(0);
		lblDescricao.setLayoutY(0);

		lblPreco.setLayoutX(0);
		lblPreco.setLayoutY(20);

		is.setColor(Color.CRIMSON);
		btnAddCarrinho.setEffect(is);
		btnAddCarrinho.setLayoutX(70);
		btnAddCarrinho.setLayoutY(40);

		imgItem.setFitWidth(350);
		imgItem.setFitHeight(250);
	}
	
	private void initTransition() {
		FadeTransition transition = new FadeTransition(
		Duration.millis(3000), imgItem);
		transition.setFromValue(0.0);
		transition.setToValue(1.0);

		ScaleTransition sTransition = new ScaleTransition(
		Duration.millis(2000), btnAddCarrinho);
		sTransition.setToX(1.5);
		sTransition.setToY(1.5);
		sTransition.setAutoReverse(true);
		
		//ParallelTransition pTransition = new ParallelTransition();
		//pTransition.getChildren().addAll(sTransition, transition);
		//pTransition.play();
		SequentialTransition st = new SequentialTransition();
		st.getChildren().addAll(transition, sTransition);
		st.play();
	}
	
	private void initTimeline() {
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(imgItem.opacityProperty(), 0.0);
		KeyFrame kf = new KeyFrame(Duration.millis(2000), kv);
		timeline.getKeyFrames().add(kf);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();
	}

	private void initListeners() {
		
		btnAddCarrinho.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				ScaleTransition transition = new ScaleTransition(
						Duration.millis(2000), btnAddCarrinho);
			
				transition.setToX(1.5);
				transition.setToY(1.5);
				transition.play();
			}
		});
		
		btnAddCarrinho.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				ScaleTransition transition = new ScaleTransition(
						Duration.millis(2000), btnAddCarrinho);
				
				transition.setToX(1.0);
				transition.setToY(1.0);
				transition.play();
			}
		});

		btnAddCarrinho.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				VitrineApp.getCarrinho().addProdutos(produto);

				try {
					new CarrinhoApp().start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}