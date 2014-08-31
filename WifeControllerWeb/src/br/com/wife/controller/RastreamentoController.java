package br.com.wife.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.wife.dao.RastreamentoDao;
import br.com.wife.model.Rastreamento;

public class RastreamentoController implements IController<Rastreamento> {
	
	private RastreamentoDao daoRast;
	private Rastreamento rast;
	
	public RastreamentoController() {
		daoRast = new RastreamentoDao();
	}

	public ArrayList<Rastreamento> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveOrUpdate(Rastreamento objeto) {
		return daoRast.saveOrUpdate(objeto);
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Rastreamento> getAll() {
		return daoRast.getAll();
	}

	@Override
	public Rastreamento getById(int pkCodigo) {
		// TODO Auto-generated method stub
		return null;
	}

}
