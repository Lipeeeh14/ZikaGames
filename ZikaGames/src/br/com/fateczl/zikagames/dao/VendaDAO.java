package br.com.fateczl.zikagames.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fateczl.zikagames.entity.Venda;

public class VendaDAO extends ConnectionDB implements IBaseDAO<Venda>{
	
	@Override
	public void adicionar(Venda entity) {
		String sql = "INSERT INTO Venda (clienteId, jogoId, valor)"
				+ " VALUES (?, ?, ?)";
		
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, entity.getClienteId());
			stmt.setInt(2, entity.getJogoId());
			stmt.setDouble(3, entity.getValor());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao vender um jogo!");
		}		
	}

	@Override
	public List<Venda> pesquisar(String column) {
		List<Venda> vendas = new ArrayList<>();
		String sql = column.isEmpty() ? "SELECT * FROM Venda" :
			"SELECT * FROM Venda WHERE clienteId = " + column;
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Venda v = new Venda();
				v.setId(Integer.parseInt(rs.getString("id")));
				v.setClienteId(Integer.parseInt(rs.getString("clienteId")));
				v.setJogoId(Integer.parseInt(rs.getString("jogoId")));
				v.setValor(Double.parseDouble(rs.getString("valor")));
				vendas.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar as vendas!");
		}
		
		return vendas;
	}
}
