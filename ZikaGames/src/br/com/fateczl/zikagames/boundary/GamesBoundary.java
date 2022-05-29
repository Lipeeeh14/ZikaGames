package br.com.fateczl.zikagames.boundary;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GamesBoundary extends Application {

	private TextField txtNome = new TextField();
	private TextField txtRg = new TextField();
	private TextField txtCpf = new TextField();
	private TextField txtDataNasc = new TextField();
	
	public static void main(String[] args) {
		Application.launch(GamesBoundary.class, args);
	}
	
	public Tab clienteTab() {
		Tab tabCliente = new Tab("Cliente");
		
		return tabCliente;
	}
	
	public Tab gameTab() {
		Tab tabGame = new Tab("Jogo");
		
		return tabGame;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		TabPane tabPane = new TabPane();
		tabPane.getTabs().add(clienteTab());
		tabPane.getTabs().add(gameTab());
		VBox vBox = new VBox(tabPane);
		Scene scn = new Scene(vBox, 800, 600);
		stage.setScene(scn);
	
		stage.setTitle("Zika Games");
		stage.show();
	}

}
