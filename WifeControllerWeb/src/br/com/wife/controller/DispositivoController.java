package br.com.wife.controller;

import java.util.List;

import br.com.wife.dao.DispositivoDao;
import br.com.wife.model.Dispositivo;

public class DispositivoController implements IController<Dispositivo> {

	DispositivoDao dao;
	
	public DispositivoController() {
		// TODO Auto-generated constructor stub
		dao = new DispositivoDao();
	}
	
	@Override
	public boolean saveOrUpdate(Dispositivo objeto) {
		return dao.saveOrUpdate(objeto);
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Dispositivo> getAll() {
		return dao.getAll();
	}

	@Override
	public Dispositivo getById(int pkCodigo) {
		// TODO Auto-generated method stub
		return null;
	}

}
