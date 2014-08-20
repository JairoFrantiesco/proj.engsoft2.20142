package br.com.agenda.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.agenda.dao.ConnectionFactory;

public class CreateDB {
	
	 // CRIA AS TABELAS
	private static final String TBL_CONTATO = "CREATE TABLE IF NOT EXISTS CONTATO"
            + "  (IDCONTATO IDENTITY PRIMARY KEY,"
            + "   NOME VARCHAR(50),"
            + "   EMAIL VARCHAR(50),"
            + "   TELEFONE VARCHAR(15),"
            + "   CELULAR VARCHAR(15) );";
	
	public static void createTables(){
		Connection con;
		
		try {
			con = ConnectionFactory.getConnection();
			
			Statement stmt = con.createStatement();
		    stmt.execute(TBL_CONTATO);
		    System.out.println("TABELA CONTATO CRIADA COM SUCESSO!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
