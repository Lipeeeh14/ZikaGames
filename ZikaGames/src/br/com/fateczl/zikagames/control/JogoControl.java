package br.com.fateczl.zikagames.control;

import java.util.ArrayList;
import java.util.List;

import br.com.fateczl.zikagames.dao.JogoDAO;
import br.com.fateczl.zikagames.entity.Jogo;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class JogoControl implements IBaseControl {
	private StringProperty nome = new SimpleStringProperty();
	private StringProperty classificacaoInd = new SimpleStringProperty();
	private DoubleProperty preco = new SimpleDoubleProperty();
	
	private ObservableList<Jogo> jogos = FXCollections.observableArrayList();
	private TableView<Jogo> table = new TableView<Jogo>();
	private JogoDAO dao = new JogoDAO();
	
	public JogoControl() {
		TableColumn<Jogo, String> nomeColumn = new TableColumn<Jogo, String>("Nome");
		nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Jogo, String> classificacaoIndColumn = new TableColumn<Jogo, String>("Classificação Indicativa");
		classificacaoIndColumn.setCellValueFactory(new PropertyValueFactory<>("classificacaoInd"));
		
		TableColumn<Jogo, String> precoColumn = new TableColumn<Jogo, String>("Preço");
		precoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));
		
		table.getColumns().addAll(nomeColumn, classificacaoIndColumn, precoColumn);
		
		table.setItems(jogos);
	}
		
	public StringProperty getNome() {
		return nome;
	}

	public StringProperty getClassificacaoInd() {
		return classificacaoInd;
	}

	public DoubleProperty getPreco() {
		return preco;
	}

	@Override
	public void adicionar() throws Exception {
		Jogo jogo = new Jogo();
		jogo.setNome(nome.get());
		jogo.setClassificacaoInd(classificacaoInd.get());
		jogo.setPreco(preco.get());
		
		dao.adicionar(jogo);
		jogos.add(jogo);
	}

	@Override
	public void pesquisar() {
		jogos.clear();
		List<Jogo> lista = new ArrayList<>();
		lista = dao.pesquisar(nome.get());

		jogos.addAll(lista);
	}
	
	public List<Jogo> getJogos() { return jogos; }
	
	public TableView getTable() {
		return table;
	}
}
