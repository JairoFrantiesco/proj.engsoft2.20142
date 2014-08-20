package br.com.agenda.view;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.agenda.dao.ConnectionFactory;
import br.com.agenda.util.CreateDB;

public class FrmPrincipal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
			
			System.out.println("CONECTADO!");
			
			//Cria as tabelas
			CreateDB.createTables();
		

	}

}
