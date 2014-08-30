package br.com.wife.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.wife.factory.ConnectionFactory;
import br.com.wife.model.Dispositivo;

public class DispositivoDao implements IGenericDao<Dispositivo> {
	
	private Connection connection;
	
	private static String INSERT = "INSERT INTO DISPOSITIVO(IDIMEI, NOME) VALUES (?,?)";
	private static String DELETE = "DELETE FROM DISPOSITIVO WHERE IDIMEI = ?";
	private static String UPDATE = "UPDATE FROM DISPOSITIVO SET NOME = ? WHERE IDIMEI = ?";
	private static String SELECT_ALL = "SELECT * FROM DISPOSITIVO";
	private static String SELECT_ID = "SELECT * FROM DISPOSITIVO WHERE IDIMEI = ?";
	
	public DispositivoDao() {
	
			ConnectionFactory fac = new ConnectionFactory();
			this.connection = fac.criarConexao();
    
	}

	@Override
	public boolean saveOrUpdate(Dispositivo objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Dispositivo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dispositivo getById(int pkCodigo) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
