package br.com.wife.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

	// Caminho do banco de dados.
	private static final String DRIVER = "org.sqlite.JDBC";
	//private static final String URL = "jdbc:sqlite:wife.db";
	
	// Caminho absoluto
	private static final String URL = "jdbc:sqlite:C:\\Users\\Diego\\git\\restful\\Restful\\wife.db";

	// Tabela com os dados de rastreamento
    private static final String RASTREAMENTO = 
    		"CREATE TABLE IF NOT EXISTS RASTREAMENTO ( " +
            " ID integer PRIMARY KEY, " +
            " FKIMEI integer, " + 	// FK da tabela de dispositivo. Código IMEI do dispositivo
            " DATA varchar(10), " +
            " HORA varchar(5), " +
            " GPSLAT varchar(25), " +			// Dados de latitude
            " GPSLONG varchar(25) " +			// Dados de longitude
            " );";	
    
    // Tabela com os dados do dispositivo
    private static final String DISPOSITIVO = 
    		"CREATE TABLE IF NOT EXISTS DISPOSITIVO ( " +
            " IDIMEI integer PRIMARY KEY, " +	// Código do IMEI no telefone
            " NOME varchar(30) " +
            " );";

	public Connection criarConexao(){
		
		Connection conexao = null;
		
		try {
			
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(URL);
			
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

	public void criarTabelas(){
		
		Connection con = criarConexao();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(DISPOSITIVO);
			pstmt.execute();
			
			pstmt = con.prepareStatement(RASTREAMENTO);
			pstmt.execute();
			
			pstmt = con.prepareStatement("INSERT INTO DISPOSITIVO(NOME) VALUES('DIEGO CELULAR')");
			pstmt.execute();
			
			System.out.println("TABELAS CRIADAS");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

