package br.com.fateczl.zikagames.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fateczl.zikagames.entity.Aluguel;

public class AluguelDAO extends ConnectionDB implements IBaseDAO<Aluguel> {
	
	@Override
	public void adicionar(Aluguel entity) {
		String sql = "INSERT INTO Aluguel (clienteId, jogoId, dataAluguel, dataDevolucao, valor)"
				+ " VALUES (?, ?, ?, ?, ?)";
		
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, entity.getClienteId());
			stmt.setInt(2, entity.getJogoId());
			stmt.setDate(3, java.sql.Date.valueOf(entity.getDataAluguel()));
			stmt.setDate(4, java.sql.Date.valueOf(entity.getDataDevolucao()));
			stmt.setDouble(5, entity.getValor());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao inserir um aluguel!");
		}		
	}

	@Override
	public List<Aluguel> pesquisar(String column) {
		List<Aluguel> alugueis = new ArrayList<>();
		String sql = "SELECT * FROM Aluguel WHERE clienteId = " + column;
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Aluguel a = new Aluguel();
				a.setId(Integer.parseInt(rs.getString("id")));
				a.setClienteId(Integer.parseInt(rs.getString("clienteId")));
				a.setJogoId(Integer.parseInt(rs.getString("jogoId")));
				a.setDataAluguel(rs.getDate("dataAluguel").toLocalDate());
				a.setDataDevolucao(rs.getDate("dataDevolucao").toLocalDate());
				a.setValor(Double.parseDouble(rs.getString("valor")));
				a.setAtivo(rs.getBoolean("ativo"));
				alugueis.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar os alugueis!");
		}
		
		return alugueis;
	}

}
