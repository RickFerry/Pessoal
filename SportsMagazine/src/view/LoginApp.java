package view;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginApp extends Application {

	private AnchorPane pane;
	private TextField txtLogin;
	private PasswordField txtSenha;
	private Button btnEntrar, btnSair;
	private static Stage stage;
	
	public static Stage getStage() {
		return stage;
	}

	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initListeners();
		Scene scene = new Scene(pane, 400, 300);
		scene.getStylesheets().add("/style/Login.css");

		stage.setScene(scene);
		stage.setTitle("Login - GolFX");
		stage.setResizable(false);
		stage.show();
		initLayout();
		LoginApp.stage = stage;
	}

	private void initComponents() {
		pane = new AnchorPane();
		pane.getStyleClass().add("pane");

		txtLogin = new TextField();
		txtSenha = new PasswordField();
		btnEntrar = new Button("Entrar");
		btnEntrar.getStyleClass().add("btnEntrar");
		
		btnSair = new Button("Sair");
		btnSair.getStyleClass().add("btnSair");

		pane.getChildren().addAll(txtLogin, txtSenha, btnEntrar, btnSair);
	}

	private void initLayout() {

		txtLogin.setLayoutX((pane.getWidth() - txtLogin.getWidth()) / 2);
		txtLogin.setLayoutY(50);
		txtLogin.setPromptText("Digite seu login");

		txtSenha.setLayoutX((pane.getWidth() - txtSenha.getWidth()) / 2);
		txtSenha.setLayoutY(100);
		txtSenha.setPromptText("Digite sua senha");

		btnEntrar.setLayoutX((pane.getWidth() - btnEntrar.getWidth()) / 2);
		btnEntrar.setLayoutY(150);

		btnSair.setLayoutX((pane.getWidth() - btnSair.getWidth()) / 2);
		btnSair.setLayoutY(200);
	}

	private void initListeners() {
		btnSair.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				fecharAplicacao();
			}
		});

		btnEntrar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				logar();
			}
		});
	}

	private void fecharAplicacao() {
		System.exit(0);
	}

	private void logar() {
		if (txtLogin.getText().equals("admin") && txtSenha.getText().equals("casadocodigo")) {
			try {
				new VitrineApp().start(new Stage());
				LoginApp.getStage().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Login e/ou senhainválidos", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}