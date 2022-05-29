package br.com.fateczl.zikagames.control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
		TableColumn<Cliente, String> nomeColumn = new TableColumn<Cliente, String>();
		nomeColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		
		TableColumn<Cliente, String> cpfColumn = new TableColumn<Cliente, String>();
		cpfColumn.setCellValueFactory(new PropertyValueFactory<>("CPF"));
		
		TableColumn<Cliente, String> dataNascColumn = new TableColumn<Cliente, String>();
		dataNascColumn.setCellValueFactory((itemData) -> {
			LocalDate data = itemData.getValue().getDataNascimento();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return new ReadOnlyStringWrapper(data.format(formatter));
		});
		
		TableColumn<Cliente, String> emailColumn = new TableColumn<Cliente, String>();
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
		
		TableColumn<Cliente, String> telefoneColumn = new TableColumn<Cliente, String>();
		telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
		
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
	public void adicionar() {
		Cliente cliente = new Cliente();
		cliente.setNome(nome.get());
		cliente.setCpf(cpf.get());
		cliente.setDataNascimento(dataNascimento.get());
		cliente.setTelefone(telefone.get());
		
		dao.adicionar(cliente);		
	}

	@Override
	public void pesquisar() {
		this.clientes.addAll(dao.pesquisar(nome.get()));
		
	}
	
	public TableView getTable() { return table; }
}
