package br.com.fateczl.zikagames.boundary;

import java.time.format.DateTimeFormatter;

import br.com.fateczl.zikagames.control.ClienteControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class GamesBoundary extends Application {

	private TextField txtNomeCliente = new TextField();
	private TextField txtCpf = new TextField();
	private TextField txtDataNasc = new TextField();
	private TextField txtTelefone = new TextField();
	private TextField txtEmail = new TextField();
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	
	private ClienteControl clienteControl = new ClienteControl();
	
	private LocalDateStringConverter dtc = 
			new LocalDateStringConverter(DateTimeFormatter.ofPattern("dd/MM/yyyy"), null); 
	
	public static void main(String[] args) {
		Application.launch(GamesBoundary.class, args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		TabPane tabPane = new TabPane();
		tabPane.getTabs().add(clienteTab());
		tabPane.getTabs().add(gameTab());
		VBox vBox = new VBox(tabPane);
		
		btnAdicionar.setOnAction((e) -> {
			clienteControl.adicionar();
			
			txtNomeCliente.setText("");
			txtCpf.setText("");
			txtDataNasc.setText("");
			txtTelefone.setText("");
			txtEmail.setText("");
		});
		
		btnPesquisar.setOnAction((e) -> {
			clienteControl.pesquisar();
		});
		
		Scene scn = new Scene(vBox, 800, 600);
		stage.setScene(scn);
	
		stage.setTitle("Zika Games");
		stage.show();
	}
	
	private Tab clienteTab() {
		Tab tabCliente = new Tab("Cliente");
		tabCliente.setClosable(false);
		
		BorderPane borderCliente = new BorderPane();
		GridPane gridCliente = new GridPane();
		borderCliente.setTop(gridCliente);
		componentesGridCliente(gridCliente);
		comunicacaoControlCliente();
		
		borderCliente.setCenter(clienteControl.getTable());
		
		tabCliente.setContent(borderCliente);
		
		return tabCliente;
	}
	
	private void componentesGridCliente(GridPane gridCliente) {
		gridCliente.add(new Label("Nome do cliente: "), 0, 0);
		gridCliente.add(txtNomeCliente, 1, 0);
		gridCliente.add(new Label("CPF: "), 0, 1);
		gridCliente.add(txtCpf, 1, 1);
		gridCliente.add(new Label("Data de Nascimento: "), 0, 2);
		gridCliente.add(txtDataNasc, 1, 2);
		gridCliente.add(new Label("Email: "), 0, 3);
		gridCliente.add(txtEmail, 1, 3);
		gridCliente.add(new Label("Telefone: "), 0, 4);
		gridCliente.add(txtTelefone, 1, 4);
		gridCliente.add(btnAdicionar, 0, 5);
		gridCliente.add(btnPesquisar, 1, 5);
	}
	
	private void comunicacaoControlCliente() {
		Bindings.bindBidirectional(clienteControl.getNome(), txtNomeCliente.textProperty());
		Bindings.bindBidirectional(clienteControl.getCpf(), txtCpf.textProperty());
		Bindings.bindBidirectional(txtDataNasc.textProperty(), clienteControl.getDataNascimento(), dtc);
		Bindings.bindBidirectional(clienteControl.getEmail(), txtEmail.textProperty());
		Bindings.bindBidirectional(clienteControl.getTelefone(), txtTelefone.textProperty());
		Bindings.bindBidirectional(clienteControl.getEmail(), txtEmail.textProperty());
	}
	
	private Tab gameTab() {
		Tab tabGame = new Tab("Jogo");
		tabGame.setClosable(false);
		
		return tabGame;
	} 
}
