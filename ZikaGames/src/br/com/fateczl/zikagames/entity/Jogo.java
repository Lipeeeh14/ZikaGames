package br.com.fateczl.zikagames.entity;

public class Jogo {
	private int id;
	private String nome;
	private String classificacaoInd;
	private double preco;
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getClassificacaoInd() {
		return classificacaoInd;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setClassificacaoInd(String classificacaoInd) {
		this.classificacaoInd = classificacaoInd;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
}
