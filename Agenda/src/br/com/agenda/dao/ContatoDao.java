package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda.model.Contato;

public class ContatoDao implements IGenericDao<Contato> {
	
	private Connection connection;
	
	private static String INSERT = "INSERT INTO CONTATO(NOME, TELEFONE) VALUES (?,?)";
	private static String DELETE = "DELETE FROM CONTATO WHERE IDCONTATO = ?";
	private static String UPDATE = "UPDATE FROM CONTATO SET NOME = ?, TELEFONE = ? WHERE IDCONTATO = ?";
	private static String SELECT_ALL = "SELECT * FROM CONTATO";
	private static String SELECT_ID = "SELECT * FROM CONTATO WHERE IDCONTATO = ?";
	
	public ContatoDao() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
	}

	@Override
	public boolean saveOrUpdate(Contato objeto) {
		
		if(objeto.getIdContato() != null)
			return update(objeto);
		else
			return save(objeto);
		
	}
	
	private boolean save(Contato contato) {
		
		PreparedStatement pstm;
		try {
			
			pstm = this.connection.prepareStatement(INSERT);
			pstm.setString(1, contato.getNome());  
			pstm.setString(2, contato.getTelefone());  


			pstm.execute();


			pstm.close();   
			
			return true; // Conseguiu gravar
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}  
	}

	private boolean update(Contato contato) {
		
		PreparedStatement pstm;
		try {
			
			pstm = this.connection.prepareStatement(UPDATE);
			pstm.setInt(1, contato.getIdContato());   


			pstm.executeUpdate();  


			pstm.close();      
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}  
	}

	@Override
	public boolean delete(int id) {
		
		PreparedStatement pstm;
		try {
			
			pstm = this.connection.prepareStatement(DELETE);
			pstm.setInt(1, id);  


			pstm.execute();


			pstm.close();   
			
			return true; // Conseguiu deletar
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}  
		
	}

	@Override
	public List<Contato> getAll() {
		
		List<Contato> listContatos = new ArrayList<Contato>();
		
		PreparedStatement pstm;
		try {
			pstm = this.connection.prepareStatement(SELECT_ALL);
			
			// execute select SQL stetement
			ResultSet rs = pstm.executeQuery(SELECT_ALL);
			 
			while (rs.next()) {
				
				Contato contato = new Contato();
				
				contato.setIdContato(rs.getInt("IDCONTATO"));
				contato.setTelefone(rs.getString("TELEFONE"));
			 
				listContatos.add(contato);
			 
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listContatos;
		
	}

	@Override
	public Contato getById(int pkCodigo) {
		// TODO Auto-generated method stub
		return null;
	}

}
