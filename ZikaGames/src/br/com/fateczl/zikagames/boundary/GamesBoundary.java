package br.com.fateczl.zikagames.boundary;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fateczl.zikagames.control.AluguelControl;
import br.com.fateczl.zikagames.control.ClienteControl;
import br.com.fateczl.zikagames.control.JogoControl;
import br.com.fateczl.zikagames.control.VendaControl;
import br.com.fateczl.zikagames.entity.Cliente;
import br.com.fateczl.zikagames.entity.Jogo;
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
	
	private ComboBox<String> cmbClientesAluguel = new ComboBox<>();	
	private ComboBox<String> cmbJogosAluguel = new ComboBox<>();
	private TextField txtDataDevolucao = new TextField();
	private TextField txtValor = new TextField();
	private Button btnAlugar = new Button("Alugar");
	private Button btnPesquisarAluguel = new Button("Pesquisar");
	
	private TextField txtJogo = new TextField();
	private ComboBox<String> cmbClassInd = new ComboBox<>();
	private TextField txtPreco = new TextField();
	private Button btnAdicionarJogo = new Button("Adicionar Game");
	private Button btnPesquisarJogo = new Button("Pesquisar Game");
	
	private ComboBox<String> cmbClientesVenda = new ComboBox<>();	
	private ComboBox<String> cmbJogosVenda = new ComboBox<>();
	private TextField txtValorVenda = new TextField();
	private Button btnVender = new Button("Vender");
	private Button btnPesquisarVenda = new Button("Pesquisar");
	
	private ClienteControl clienteControl = new ClienteControl();
	private AluguelControl aluguelControl = new AluguelControl();
	private JogoControl jogoControl = new JogoControl();
	private VendaControl vendaControl = new VendaControl();
	
	private LocalDateStringConverter dtc = 
			new LocalDateStringConverter(DateTimeFormatter.ofPattern("dd/MM/yyyy"), null); 
	
	public static void main(String[] args) {
		Application.launch(GamesBoundary.class, args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		TabPane tabPane = new TabPane();
		tabPane.getTabs().addAll(clienteTab(), gameTab(), aluguelTab(), vendaTab());
		VBox vBox = new VBox(tabPane);
		
		btnAdicionar.setOnAction((e) -> {
			try {
				clienteControl.adicionar();
				
				clienteControl.getTable();
				txtNomeCliente.setText("");
				txtCpf.setText("");
				txtDataNasc.setText("");
				txtTelefone.setText("");
				txtEmail.setText("");
				JOptionPane.showMessageDialog(null, "Cliente Adicionado com Sucesso!");
				carregarCombosAluguel();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		
		btnPesquisar.setOnAction((e) -> {
			clienteControl.pesquisar();
		});
		
		btnAlugar.setOnAction((e) -> {
			try {
				aluguelControl.adicionar();
				txtValor.setText("");
				txtDataDevolucao.setText("");
				JOptionPane.showMessageDialog(null, "Aluguel realizado com Sucesso!");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		
		btnPesquisarAluguel.setOnAction((e) -> {
			aluguelControl.pesquisar();
		});
		
		btnAdicionarJogo.setOnAction((e) -> {
			try {
				jogoControl.adicionar();
				txtJogo.setText("");
				txtPreco.setText("");
				JOptionPane.showMessageDialog(null, "Jogo Adicionado com Sucesso!");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		
		btnPesquisarJogo.setOnAction((e) -> {
			jogoControl.pesquisar();
		});
		
		btnVender.setOnAction((e) -> {
			try {
				vendaControl.adicionar();
				carregarCombosVenda();
				JOptionPane.showMessageDialog(null, "Venda realizada com Sucesso!");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		
		btnPesquisarVenda.setOnAction((e) -> {
			vendaControl.pesquisar();
		});
		
		Scene scn = new Scene(vBox, 600, 300);
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
	}
	
	private Tab gameTab() {
		Tab tabGame = new Tab("Jogos");
		tabGame.setClosable(false);

		BorderPane borderGame = new BorderPane();
		GridPane gridGame = new GridPane();
		borderGame.setTop(gridGame);
		componentesGridGame(gridGame);
		gridGame.setVgap(5);
		gridGame.setHgap(10);

		comunicacaoControlGame();

		borderGame.setCenter(jogoControl.getTable());

		tabGame.setContent(borderGame);

		return tabGame;
	}

	private void componentesGridGame(GridPane gridGame) {
		gridGame.add(new Label("Nome do Jogo: "), 0, 0);
		gridGame.add(txtJogo, 1, 0);
		gridGame.add(new Label("Classificação Indicativa: "), 2, 0);
		cmbClassInd.getItems().addAll("Livre", "12", "14", "16", "18");
		gridGame.add(cmbClassInd, 3, 0);
		gridGame.add(new Label("Preço: "), 0, 1);
		gridGame.add(txtPreco, 1, 1);
		gridGame.add(btnAdicionarJogo, 0, 5);
		gridGame.add(btnPesquisarJogo, 1, 5);
	}

	private void comunicacaoControlGame() {
		Bindings.bindBidirectional(jogoControl.getNome(), txtJogo.textProperty());
		cmbClassInd.valueProperty().bindBidirectional(jogoControl.getClassificacaoInd());
		
		StringConverter<? extends Number> converterNumber = new NumberStringConverter();
		Bindings.bindBidirectional(txtPreco.textProperty(), jogoControl.getPreco(), (StringConverter<Number>)
				converterNumber);
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
		
		carregarCombosAluguel();
		comunicacaoControlAluguel();
		
		borderAluguel.setCenter(aluguelControl.getTable());
		
		tabAluguel.setContent(borderAluguel);
		
		return tabAluguel;
	}
	
	private void componentesGridAluguel(GridPane gridAluguel) {
		gridAluguel.add(new Label("Clientes: "), 0, 0);
		gridAluguel.add(cmbClientesAluguel, 1, 0);
		gridAluguel.add(new Label("Jogos: "), 0, 1);
		gridAluguel.add(cmbJogosAluguel, 1, 1);
		gridAluguel.add(new Label("Valor: "), 2, 0);
		gridAluguel.add(txtValor, 3, 0);
		gridAluguel.add(new Label("Vencimento: "), 2, 1);
		gridAluguel.add(txtDataDevolucao, 3, 1);
		gridAluguel.add(btnAlugar, 0, 2);
		gridAluguel.add(btnPesquisarAluguel, 1, 2);
	}
	
	private void carregarCombosAluguel() {
		cmbClientesAluguel.getItems().removeAll(cmbClientesAluguel.getItems());
		cmbJogosAluguel.getItems().removeAll(cmbJogosAluguel.getItems());
		txtNomeCliente.setText("");
		clienteControl.pesquisar();	
		jogoControl.pesquisar();
		List<String> clientesCombo = obterValoresComboClientes(clienteControl.getClientes());
		List<String> jogosCombo = obterValoresComboJogos(jogoControl.getJogos());
		cmbClientesAluguel.getItems().addAll(clientesCombo);
		cmbJogosAluguel.getItems().addAll(jogosCombo);
	}
	
	private List<String> obterValoresComboClientes(List<Cliente> listaClientes) {
		List<String> dadosCombo = new ArrayList<>();
		for (Cliente cliente : listaClientes) {
			dadosCombo.add(cliente.getId() + " - " + cliente.getNome());
		}
		
		return dadosCombo;
	}
	
	private List<String> obterValoresComboJogos(List<Jogo> listaJogos) {
		List<String> dadosCombo = new ArrayList<>();
		for (Jogo jogo : listaJogos) {
			dadosCombo.add(jogo.getId() + " - " + jogo.getNome());
		}
		
		return dadosCombo;
	}
	
	private void comunicacaoControlAluguel() {
		cmbClientesAluguel.valueProperty().bindBidirectional(aluguelControl.getCliente());
		cmbJogosAluguel.valueProperty().bindBidirectional(aluguelControl.getJogo());
		
		StringConverter<? extends Number> converterNumber = new NumberStringConverter();
		Bindings.bindBidirectional(txtValor.textProperty(), aluguelControl.getValor(), (StringConverter<Number>)
				converterNumber);
		Bindings.bindBidirectional(txtDataDevolucao.textProperty(), aluguelControl.getDataDevolucao(), dtc);
	}
	
	private Tab vendaTab() {
		Tab tabVenda = new Tab("Venda");
		tabVenda.setClosable(false);
		
		BorderPane borderVenda = new BorderPane();
		GridPane gridVenda = new GridPane();
		borderVenda.setTop(gridVenda);
		componentesGridVenda(gridVenda);
		gridVenda.setVgap(5);
		gridVenda.setHgap(10);
		
		carregarCombosVenda();
		comunicacaoControlVenda();
		
		borderVenda.setCenter(vendaControl.getTable());
		
		tabVenda.setContent(borderVenda);
		
		return tabVenda;
	}

	private void componentesGridVenda(GridPane gridVenda) {
		gridVenda.add(new Label("Clientes: "), 0, 0);
		gridVenda.add(cmbClientesVenda, 1, 0);
		gridVenda.add(new Label("Jogos: "), 0, 1);
		gridVenda.add(cmbJogosVenda, 1, 1);
		gridVenda.add(new Label("Valor: "), 2, 0);
		gridVenda.add(txtValorVenda, 3, 0);

		gridVenda.add(btnVender, 0, 2);
		gridVenda.add(btnPesquisarVenda, 1, 2);		
	}
	
	private void carregarCombosVenda() {
		cmbClientesVenda.getItems().removeAll(cmbClientesVenda.getItems());
		cmbJogosVenda.getItems().removeAll(cmbJogosVenda.getItems());
		txtNomeCliente.setText("");
		clienteControl.pesquisar();	
		List<String> clientesCombo = obterValoresComboClientes(clienteControl.getClientes());
		List<String> jogosCombo = obterValoresComboJogos(jogoControl.getJogos());
		cmbClientesVenda.getItems().addAll(clientesCombo);
		cmbJogosVenda.getItems().addAll(jogosCombo);
	}

	private void comunicacaoControlVenda() {
		cmbClientesVenda.valueProperty().bindBidirectional(vendaControl.getCliente());
		cmbJogosVenda.valueProperty().bindBidirectional(vendaControl.getJogo());
		
		StringConverter<? extends Number> converterNumber = new NumberStringConverter();
		Bindings.bindBidirectional(txtValorVenda.textProperty(), vendaControl.getValor(), (StringConverter<Number>)
				converterNumber);
	}
}
