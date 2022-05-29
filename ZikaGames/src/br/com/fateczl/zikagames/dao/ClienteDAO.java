package br.com.fateczl.zikagames.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fateczl.zikagames.entity.Cliente;

public class ClienteDAO extends ConnectionDB implements IBaseDAO<Cliente> {

	@Override
	public void adicionar(Cliente entity) {
		String sql = "INSERT INTO Cliente (nome, cpf, dataNascimento, telefone, email)"
				+ " VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, entity.getNome());
			stmt.setString(2, entity.getCpf());
			stmt.setDate(3, java.sql.Date.valueOf(entity.getDataNascimento()));
			stmt.setString(4, entity.getTelefone());
			stmt.setString(5, entity.getEmail());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Cliente> pesquisar(String column) {
		List<Cliente> clientes = new ArrayList<>();
		String sql = "SELECT * FROM Cliente WHERE nome LIKE '%" + column + "%'";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Cliente c = new Cliente();
				c.setId(Integer.parseInt(rs.getString("id")));
				c.setNome(rs.getString("nome"));
				c.setCpf(rs.getString("cpf"));
				c.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
				c.setTelefone(rs.getString("telefone"));
				c.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clientes;
	}

}
