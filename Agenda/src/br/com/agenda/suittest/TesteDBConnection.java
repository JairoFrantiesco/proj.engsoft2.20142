package br.com.agenda.suittest;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.agenda.dao.DBConnection;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TesteDBConnection extends TestCase {
		
	public static TestSuite suite() {
		return new TestSuite(TesteDBConnection.class);
	}
	
	public void testaGetConnection() throws SQLException {
		
	}
	
}
