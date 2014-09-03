package br.com.wife.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wife.factory.ConnectionFactory;
import br.com.wife.model.Dispositivo;
import br.com.wife.model.Rastreamento;

public class RastreamentoDao implements IGenericDao<Rastreamento> {
	
	Connection conexao;
	ConnectionFactory conFactory;
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private static String INSERT = "INSERT INTO RASTREAMENTO(FKIMEI, DATA, HORA, GPSLAT, GPSLONG) VALUES (?,?,?,?,?)";
	
	public RastreamentoDao() {
		ConnectionFactory conFactory = new ConnectionFactory();
		conexao = conFactory.criarConexao();
	}

	@Override
	public boolean saveOrUpdate(Rastreamento objeto) {
		
			return save(objeto);

		
	}

	private boolean update(Rastreamento objeto) {
		// TODO Auto-generated method stub
		
		return true;
	}

	private boolean save(Rastreamento objeto) {
		
		System.out.println("HORA: " + objeto.getHora());
		System.out.println("DATA: " + objeto.getData());
		System.out.println("GPS LAT: " + objeto.getGpsLat());
		System.out.println("GPS LONG: " + objeto.getGpsLong());
		System.out.println("IMEI: " + objeto.getImei());
		
		try {
			
			pstmt = conexao.prepareStatement(INSERT);

			pstmt.setString(1, objeto.getImei());
			pstmt.setString(2, objeto.getData());
			pstmt.setString(3, objeto.getHora());
			pstmt.setString(4, objeto.getGpsLat());
			pstmt.setString(5, objeto.getGpsLong());
			
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
	public ArrayList<Rastreamento> getAll() {

		ArrayList<Rastreamento> listaRastreamento = new ArrayList<Rastreamento>();
		
		try {
			pstmt = conexao.prepareStatement("SELECT * FROM RASTREAMENTO ORDER BY ID");
			rs = pstmt.executeQuery();
			
			DispositivoDao daoDis = new DispositivoDao();
			
			while(rs.next()){
				Rastreamento rast = new Rastreamento();
				
				Dispositivo disp = daoDis.getByImei(rs.getString("FKIMEI"));
				
				rast.setId(rs.getInt("ID"));
				
				//Falta setar o dispositivo
				rast.setDispositivo(disp);
				
				rast.setData(rs.getString("DATA"));
				rast.setHora(rs.getString("HORA"));
				
				rast.setGpsLat(rs.getString("GPSLAT"));
				rast.setGpsLong(rs.getString("GPSLONG"));

				listaRastreamento.add(rast);
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
			e.printStackTrace();
		} 
		
		return listaRastreamento;
		
	}

	@Override
	public Rastreamento getById(int pkCodigo) {
		// TODO Auto-generated method stub
		return null;
	}

}
