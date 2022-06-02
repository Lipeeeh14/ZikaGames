package br.com.fateczl.zikagames.control;

import java.util.ArrayList;
import java.util.List;

import br.com.fateczl.zikagames.dao.VendaDAO;
import br.com.fateczl.zikagames.entity.Venda;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class VendaControl implements IBaseControl {
	
	private StringProperty clienteOption = new SimpleStringProperty();
	private StringProperty jogoOption = new SimpleStringProperty();
	private DoubleProperty valor = new SimpleDoubleProperty();

	private ObservableList<Venda> vendas = FXCollections.observableArrayList();
	private TableView<Venda> table = new TableView<Venda>();
	private VendaDAO dao = new VendaDAO();
	
	public VendaControl() {
		TableColumn<Venda, String> clienteIdColumn = new TableColumn<Venda, String>("Id do Cliente");
		clienteIdColumn.setCellValueFactory(new PropertyValueFactory<>("clienteId"));

		TableColumn<Venda, String> jogoIdColumn = new TableColumn<Venda, String>("Id do Jogo");
		jogoIdColumn.setCellValueFactory(new PropertyValueFactory<>("jogoId"));

		TableColumn<Venda, String> valorColumn = new TableColumn<Venda, String>("Valor");
		valorColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));

		table.getColumns().addAll(clienteIdColumn, jogoIdColumn, valorColumn);

		table.setItems(vendas);
	}

	public StringProperty getCliente() {
		return clienteOption;
	}

	public StringProperty getJogo() {
		return jogoOption;
	}

	public DoubleProperty getValor() {
		return valor;
	}

	public List<Venda> getVendas() {
		return vendas;
	}
	
	@Override
	public void adicionar() throws Exception {
		Venda venda = new Venda();
		venda.setClienteId(Integer.parseInt(clienteOption.get().split(" ")[0]));
		venda.setJogoId(Integer.parseInt(jogoOption.get().split(" ")[0]));
		venda.setValor(valor.get());
		
		dao.adicionar(venda);
		vendas.add(venda);
	}

	@Override
	public void pesquisar() {
		vendas.clear();
		List<Venda> lista = new ArrayList<>();
		BooleanBinding t = clienteOption.isNotEmpty();
		String clienteId = t.get() ? clienteOption.get().split(" ")[0] : "";
		lista = dao.pesquisar(clienteId);

		vendas.addAll(lista);
	}
	
	public TableView getTable() {
		return table;
	}
}
