package br.com.fateczl.zikagames.control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fateczl.zikagames.dao.ClienteDAO;
import br.com.fateczl.zikagames.entity.Cliente;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClienteControl implements IBaseControl {
	private StringProperty nome = new SimpleStringProperty();
	private StringProperty cpf = new SimpleStringProperty();
	private ObjectProperty<LocalDate> dataNascimento = new SimpleObjectProperty<LocalDate>();
	private StringProperty email = new SimpleStringProperty();
	private StringProperty telefone = new SimpleStringProperty();
	
	private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
	private TableView<Cliente> table = new TableView<Cliente>();
	private ClienteDAO dao = new ClienteDAO();
	
	public ClienteControl() {		
		TableColumn<Cliente, String> nomeColumn = new TableColumn<Cliente, String>("Nome");
		nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Cliente, String> cpfColumn = new TableColumn<Cliente, String>("CPF");
		cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		
		TableColumn<Cliente, String> dataNascColumn = new TableColumn<Cliente, String>("Data de Nascimento");
		dataNascColumn.setCellValueFactory((itemData) -> {
			LocalDate data = itemData.getValue().getDataNascimento();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return new ReadOnlyStringWrapper(data.format(formatter));
		});
		
		TableColumn<Cliente, String> emailColumn = new TableColumn<Cliente, String>("Email");
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		TableColumn<Cliente, String> telefoneColumn = new TableColumn<Cliente, String>("Telefone");
		telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		
		table.getColumns().addAll(nomeColumn, cpfColumn, dataNascColumn, emailColumn, telefoneColumn);
		
		table.setItems(clientes);
	}
	
	public StringProperty getNome() {
		return nome;
	}
	
	public StringProperty getCpf() {
		return cpf;
	}
	
	public ObjectProperty<LocalDate> getDataNascimento() {
		return dataNascimento;
	}
	
	public StringProperty getEmail() {
		return email;
	}
	
	public StringProperty getTelefone() {
		return telefone;
	}
	
	public void setTelefone(StringProperty telefone) {
		this.telefone = telefone;
	}

	@Override
	public void adicionar() throws Exception {
		if (validarDataNascimento()) {
			Cliente cliente = new Cliente();
			cliente.setNome(nome.get());
			cliente.setCpf(cpf.get());
			cliente.setDataNascimento(dataNascimento.get());
			cliente.setTelefone(telefone.get());
			cliente.setEmail(email.get());
			
			dao.adicionar(cliente);
			clientes.add(cliente);
		} else {
			throw new InputMismatchException("O cliente não pode ser menor de 18 anos, tente novamente!");
		}
	}

	@Override
	public void pesquisar() {
		clientes.clear();
		clientes.addAll(dao.pesquisar(nome.get()));
	}
	
	public TableView getTable() { return table; }
	
	public List<Cliente> getClientes() { return clientes; }
	
	private boolean validarDataNascimento() {
		return dataNascimento.get().until(LocalDate.now(), ChronoUnit.YEARS) >= 18;
	}
}
