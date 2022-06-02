package br.com.fateczl.zikagames.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fateczl.zikagames.entity.Jogo;

public class JogoDAO extends ConnectionDB implements IBaseDAO<Jogo> {

	@Override
	public void adicionar(Jogo entity) {
		String sql = "INSERT INTO Jogo (nome, classificacaoInd, preco)"
				+ " VALUES (?, ?, ?)";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, entity.getNome());
			stmt.setString(2, entity.getClassificacaoInd());
			stmt.setDouble(3, entity.getPreco());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar o jogo!");
		}		
	}

	@Override
	public List<Jogo> pesquisar(String column) {
		List<Jogo> jogos = new ArrayList<>();
		String sql = "SELECT * FROM Jogo WHERE nome LIKE '%" + column + "%'";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Jogo j = new Jogo();
				j.setId(Integer.parseInt(rs.getString("id")));
				j.setNome(rs.getString("nome"));
				j.setClassificacaoInd(rs.getString("classificacaoInd"));
				j.setPreco(rs.getDouble("preco"));
				
				jogos.add(j);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar os jogos!");
		}
		
		return jogos;
	}

}
