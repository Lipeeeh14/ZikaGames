package br.com.fateczl.zikagames.control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.fateczl.zikagames.dao.AluguelDAO;
import br.com.fateczl.zikagames.entity.Aluguel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AluguelControl implements IBaseControl {

	private StringProperty clienteOption = new SimpleStringProperty();
	private StringProperty jogoOption = new SimpleStringProperty();
	private ObjectProperty<LocalDate> dataDevolucao = new SimpleObjectProperty<LocalDate>();
	private DoubleProperty valor = new SimpleDoubleProperty();
	
	private ObservableList<Aluguel> alugueis = FXCollections.observableArrayList();
	private TableView<Aluguel> table = new TableView<Aluguel>();
	private AluguelDAO dao = new AluguelDAO();
	
	public AluguelControl() {
		TableColumn<Aluguel, String> idColumn = new TableColumn<Aluguel, String>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Aluguel, String> clienteIdColumn = new TableColumn<Aluguel, String>("Id do Cliente");
		clienteIdColumn.setCellValueFactory(new PropertyValueFactory<>("clienteId"));
		
		TableColumn<Aluguel, String> jogoIdColumn = new TableColumn<Aluguel, String>("Id do Cliente");
		jogoIdColumn.setCellValueFactory(new PropertyValueFactory<>("clienteId"));
			
		TableColumn<Aluguel, String> dataAluguelColumn = new TableColumn<Aluguel, String>("Data do Aluguel");
		dataAluguelColumn.setCellValueFactory((itemData) -> {
			LocalDate data = itemData.getValue().getDataAluguel();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");;
			return new ReadOnlyStringWrapper(data.format(formatter));
		});
		
		TableColumn<Aluguel, String> dataDevolucaoColumn = new TableColumn<Aluguel, String>("Data do Aluguel");
		dataDevolucaoColumn.setCellValueFactory((itemData) -> {
			LocalDate data = itemData.getValue().getDataAluguel();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");;
			return new ReadOnlyStringWrapper(data.format(formatter));
		});
		
		TableColumn<Aluguel, String> valorColumn = new TableColumn<Aluguel, String>("Valor");
		valorColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));
		
		TableColumn<Aluguel, String> ativoColumn = new TableColumn<Aluguel, String>("Ativo");
		ativoColumn.setCellValueFactory(new PropertyValueFactory<>("ativo"));
		
		TableColumn<Aluguel, String> emAtrasoColumn = new TableColumn<Aluguel, String>("Em Atraso");
		emAtrasoColumn.setCellValueFactory(new PropertyValueFactory<>("atraso"));
		
		table.getColumns().addAll(idColumn, clienteIdColumn, jogoIdColumn, dataAluguelColumn, dataDevolucaoColumn,
				valorColumn, ativoColumn, emAtrasoColumn);
		
		table.setItems(alugueis);
	}
	
	public StringProperty getCliente() {
		return clienteOption;
	}

	public StringProperty getJogo() {
		return jogoOption;
	}

	public ObjectProperty<LocalDate> getDataDevolucao() {
		return dataDevolucao;
	}

	public DoubleProperty getValor() {
		return valor;
	}

	@Override
	public void adicionar() {
		Aluguel aluguel = new Aluguel();
		int clienteId = Integer.parseInt(clienteOption.get().split(" ")[0]);
		//int jogoId = Integer.parseInt(jogoOption.get().split(" ")[0]);
		aluguel.setClienteId(clienteId);
		aluguel.setJogoId(1);
		aluguel.setDataAluguel(LocalDate.now());
		aluguel.setDataDevolucao(dataDevolucao.get());
		aluguel.setValor(valor.get());
		aluguel.setAtivo(true);
		
		dao.adicionar(aluguel);
	}

	@Override
	public void pesquisar() {
		alugueis.clear();
		List<Aluguel> lista = new ArrayList<>();
		lista = dao.pesquisar(clienteOption.get().split(" ")[0]);
		
		lista.forEach(e -> e.setAtraso(e.getDataDevolucao().isBefore(LocalDate.now())));
		
		alugueis.addAll(lista);
	}
	
	public TableView getTable() { return table; }
}
