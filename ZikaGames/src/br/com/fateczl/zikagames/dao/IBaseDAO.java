package br.com.fateczl.zikagames.dao;

import java.util.List;

public interface IBaseDAO<T> {
	void adicionar(T entity);
	List<T> pesquisar(String column);
}
