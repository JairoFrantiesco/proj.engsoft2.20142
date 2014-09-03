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
import br.com.wife.model.Rastreamento;

public class DispositivoDao implements IGenericDao<Dispositivo> {
	
	Connection conexao;
	ConnectionFactory conFactory;
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private static String INSERT = "INSERT INTO DISPOSITIVO(IMEI, NOME) VALUES (?,?)";
	private static String DELETE = "DELETE FROM DISPOSITIVO WHERE ID = ?";
	private static String UPDATE = "UPDATE FROM DISPOSITIVO SET NOME = ? WHERE ID = ?";
	private static String SELECT_ALL = "SELECT * FROM DISPOSITIVO ORDER BY ID";
	private static String SELECT_ID = "SELECT * FROM DISPOSITIVO WHERE IMEI = ?";
	
	public DispositivoDao() {
	
			ConnectionFactory fac = new ConnectionFactory();
			this.conexao = fac.criarConexao();
    
	}

	@Override
	public boolean saveOrUpdate(Dispositivo objeto) {
		return save(objeto);
	}

	private boolean save(Dispositivo objeto) {
		
		try {
			
			pstmt = conexao.prepareStatement(INSERT);
			
			// Código imei de teste
			pstmt.setString(1, objeto.getImei());
			pstmt.setString(2, objeto.getNmDispositivo());
			
			pstmt.execute();


			pstmt.close();   
			
			System.out.println("DADOS SALVOS");
			
			return true; // Conseguiu gravar
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}  
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Dispositivo> getAll() {
		
		ArrayList<Dispositivo> lista = new ArrayList<Dispositivo>();
		
		try {
			pstmt = conexao.prepareStatement(SELECT_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Dispositivo disp = new Dispositivo();
				
				disp.setId(rs.getInt("ID"));
				disp.setImei(rs.getString("IMEI"));
				disp.setNmDispositivo(rs.getString("NOME"));

				lista.add(disp);
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
			e.printStackTrace();
		} 
		
		return lista;
		
	}

	@Override
	public Dispositivo getById(int pkCodigo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Dispositivo getByImei(String imei){
		
		Dispositivo disp = new Dispositivo();
		
		try {
			pstmt = conexao.prepareStatement(SELECT_ID);
			pstmt.setString(1, imei);
			rs = pstmt.executeQuery();
			rs.next();
	
			disp.setId(rs.getInt("ID"));
			disp.setImei(rs.getString("IMEI"));
			disp.setNmDispositivo(rs.getString("NOME"));

			
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
			e.printStackTrace();
		} 
		
		return disp;
	}

	
}
