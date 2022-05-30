package br.com.fateczl.zikagames.entity;

import java.time.LocalDate;

public class Aluguel {
	private int id;
	private int clienteId;
	private int jogoId;
	private LocalDate dataAluguel;
	private LocalDate dataDevolucao;
	private double valor;
	private boolean atraso;
	private boolean ativo;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getClienteId() {
		return clienteId;
	}
	
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
	
	public int getJogoId() {
		return jogoId;
	}
	
	public void setJogoId(int jogoId) {
		this.jogoId = jogoId;
	}
	
	public LocalDate getDataAluguel() {
		return dataAluguel;
	}
	
	public void setDataAluguel(LocalDate dataAluguel) {
		this.dataAluguel = dataAluguel;
	}
	
	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}
	
	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public boolean isAtraso() {
		return atraso;
	}
	
	public void setAtraso(boolean atraso) {
		this.atraso = atraso;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}	
	
}
