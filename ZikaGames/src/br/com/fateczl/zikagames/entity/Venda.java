package br.com.fateczl.zikagames.entity;

public class Venda {
	private int id;
	private int clienteId;
	private int jogoId;
	private double valor;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClienteId() {
		return clienteId;
	}
	
	public int getJogoId() {
		return jogoId;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
	
	public void setJogoId(int jogoId) {
		this.jogoId = jogoId;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}	
	
}
