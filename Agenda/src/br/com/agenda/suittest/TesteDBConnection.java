package br.com.agenda.suittest;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TesteDBConnection extends TestCase {
		
	public static TestSuite suite() {
		return new TestSuite(TesteDBConnection.class);
	}
	
	public void testaGetConnection() throws SQLException {
		
	}
	
}
