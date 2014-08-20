package br.com.agenda.controller;

import java.util.List;

import br.com.agenda.dao.ContatoDao;
import br.com.agenda.model.Contato;

public class ContatoController implements IController<Contato> {

	private ContatoDao daoContato;
	
	public ContatoController() {
		daoContato = new ContatoDao();
	}
	
	@Override
	public boolean saveOrUpdate(Contato objeto) {
		
		return daoContato.saveOrUpdate(objeto);
	}

	@Override
	public boolean delete(int id) {
		
		return daoContato.delete(id);
		
	}

	@Override
	public List<Contato> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contato getById(int pkCodigo) {
		// TODO Auto-generated method stub
		return null;
	}

}
