package br.com.fateczl.zikagames.boundary;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fateczl.zikagames.control.AluguelControl;
import br.com.fateczl.zikagames.control.ClienteControl;
import br.com.fateczl.zikagames.entity.Cliente;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;

public class GamesBoundary extends Application {

	private TextField txtNomeCliente = new TextField();
	private TextField txtCpf = new TextField();
	private TextField txtDataNasc = new TextField();
	private TextField txtTelefone = new TextField();
	private TextField txtEmail = new TextField();
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private ComboBox<String> cmbClientes = new ComboBox<>();
	private ComboBox<String> cmbJogos = new ComboBox<>();
	private TextField txtDataDevolucao = new TextField();
	private TextField txtValor = new TextField();
	private Button btnAlugar = new Button("Alugar");
	private Button btnPesquisarAluguel = new Button("Pesquisar");
	
	private ClienteControl clienteControl = new ClienteControl();
	private AluguelControl aluguelControl = new AluguelControl();
	
	private LocalDateStringConverter dtc = 
			new LocalDateStringConverter(DateTimeFormatter.ofPattern("dd/MM/yyyy"), null); 
	
	public static void main(String[] args) {
		Application.launch(GamesBoundary.class, args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		TabPane tabPane = new TabPane();
		tabPane.getTabs().addAll(clienteTab(), gameTab(), aluguelTab());
		VBox vBox = new VBox(tabPane);
		
		btnAdicionar.setOnAction((e) -> {
			clienteControl.adicionar();
			
			txtNomeCliente.setText("");
			txtCpf.setText("");
			txtDataNasc.setText("");
			txtTelefone.setText("");
			txtEmail.setText("");
			JOptionPane.showMessageDialog(null, "Cliente Adicionado com Sucesso!");
		});
		
		btnPesquisar.setOnAction((e) -> {
			clienteControl.pesquisar();
		});
		
		btnAlugar.setOnAction((e) -> {
			aluguelControl.adicionar();
			
			txtValor.setText("");
			txtDataDevolucao.setText("");
		});
		
		btnPesquisarAluguel.setOnAction((e) -> {
			aluguelControl.pesquisar();
		});
		
		Scene scn = new Scene(vBox, 800, 400);
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
		gridCliente.setVgap(5);
		gridCliente.setHgap(10);
		
		comunicacaoControlCliente();
		
		borderCliente.setCenter(clienteControl.getTable());
		
		tabCliente.setContent(borderCliente);
		
		return tabCliente;
	}
	
	private void componentesGridCliente(GridPane gridCliente) {
		gridCliente.add(new Label("Nome do Cliente: "), 0, 0);
		gridCliente.add(txtNomeCliente, 1, 0);
		gridCliente.add(new Label("CPF: "), 2, 0);
		gridCliente.add(txtCpf, 3, 0);
		gridCliente.add(new Label("Data de Nascimento: "), 0, 1);
		gridCliente.add(txtDataNasc, 1, 1);
		gridCliente.add(new Label("Email: "), 2, 1);
		gridCliente.add(txtEmail, 3, 1);
		gridCliente.add(new Label("Telefone: "), 0, 2);
		gridCliente.add(txtTelefone, 1, 2);
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
	
	private Tab aluguelTab() {
		Tab tabAluguel = new Tab("Aluguel");
		tabAluguel.setClosable(false);
		
		BorderPane borderAluguel = new BorderPane();
		GridPane gridAluguel = new GridPane();
		borderAluguel.setTop(gridAluguel);
		componentesGridAluguel(gridAluguel);
		gridAluguel.setVgap(5);
		gridAluguel.setHgap(10);
		
		carregarCombos();
		comunicacaoControlAluguel();
		
		borderAluguel.setCenter(aluguelControl.getTable());
		
		tabAluguel.setContent(borderAluguel);
		
		return tabAluguel;
	}
	
	private void componentesGridAluguel(GridPane gridAluguel) {
		gridAluguel.add(new Label("Clientes: "), 0, 0);
		gridAluguel.add(cmbClientes, 1, 0);
		gridAluguel.add(new Label("Jogos: "), 0, 1);
		gridAluguel.add(cmbJogos, 1, 1);
		gridAluguel.add(new Label("Valor: "), 2, 0);
		gridAluguel.add(txtValor, 3, 0);
		gridAluguel.add(new Label("Vencimento: "), 2, 1);
		gridAluguel.add(txtDataDevolucao, 3, 1);
		gridAluguel.add(btnAlugar, 0, 2);
		gridAluguel.add(btnPesquisarAluguel, 1, 2);
	}
	
	private void carregarCombos() {
		txtNomeCliente.setText("");
		clienteControl.pesquisar();
		List<String> clientesCombo = obterValoresComboClientes(clienteControl.getClientes());
		cmbClientes.getItems().addAll(clientesCombo);
	}
	
	private List<String> obterValoresComboClientes(List<Cliente> listaClientes) {
		List<String> dadosCombo = new ArrayList<>();
		for (Cliente cliente : listaClientes) {
			dadosCombo.add(cliente.getId() + " - " + cliente.getNome());
		}
		
		return dadosCombo;
	}
	
	private void comunicacaoControlAluguel() {
		cmbClientes.valueProperty().bindBidirectional(aluguelControl.getCliente());
		cmbJogos.valueProperty().bindBidirectional(aluguelControl.getJogo());
		
		StringConverter<? extends Number> converterNumber = new NumberStringConverter();
		Bindings.bindBidirectional(txtValor.textProperty(), aluguelControl.getValor(), (StringConverter<Number>)
				converterNumber);
		Bindings.bindBidirectional(txtDataDevolucao.textProperty(), aluguelControl.getDataDevolucao(), dtc);
	}
}
