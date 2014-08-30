package br.com.wife.dao;

import java.util.List;

public interface IGenericDao<T> {
	 
    public boolean saveOrUpdate(T objeto);  
    public boolean delete(int id);  
    public List<T> getAll();  
    public T getById(int pkCodigo); 

}
