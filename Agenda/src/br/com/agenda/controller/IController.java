package br.com.agenda.controller;

import java.util.List;

public interface IController<T> {
	
	public boolean saveOrUpdate(T objeto);  
    public boolean delete(int id);  
    public List<T> getAll();  
    public T getById(int pkCodigo); 

}
