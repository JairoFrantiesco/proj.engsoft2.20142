package br.com.wife.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

	private static String DRIVER = "com.mysql.jdbc.Driver";  
   // private static String URL = "jdbc:mysql://107.170.76.43/wife";  
    
    private static String URL = "jdbc:mysql://localhost/wife"; 
    
    private static String USER = "wife";  
    private static String PASSWD = "qw12as12zx"; 

	public Connection criarConexao(){
		
		Connection conexao = null;
		
		try {
			
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(URL, USER, PASSWD);
			
			System.out.println("Conexão criada com sucesso!");
			
			//criarTabelas();
			
		} catch (Exception e) {
			System.out.println("Erro ao criar conexão com o banco: " + URL);
			e.printStackTrace();
		}
		return conexao;
	}
	
	
	public void fecharConexao(Connection conexao, PreparedStatement pstmt, ResultSet rs){
		
		try {
			
			if(conexao != null){
				conexao.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			if(rs != null){
				rs.close();
			}
					
		} catch (Exception e) {
			System.out.println("Erro ao fechar conexão com o banco: " + URL);
		}
	}

}

