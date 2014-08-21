package br.com.wife.util;

import br.com.wife.dao.DaoGenerico;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
public class MontaEstruturaBanco extends DaoGenerico {
	
	private SQLiteDatabase db;
	
	private Context context;
	
	// Tabela de Motivos
    private static final String DADOS = 
    		"CREATE TABLE IF NOT EXISTS DADOS ( " +
            " ID integer PRIMARY KEY, " +
            " NMDISPOSITIVO varchar(50), " +
            " DATA varchar(10), " +
            " HORA varchar(5), " +
            " );";

 	public MontaEstruturaBanco(Context context) {
 		super(context);

 		this.context = context;
 		
 		Log.i("BASE DE DADOS", "CRIANDO ESTRUTURA");
		
		dbOpen();
		Log.d("VERSÃO BASE ATUAL", Integer.toString(db.getVersion()));
		
		// Cria as tabelas
		db.execSQL(DADOS);

		dbClose();


	}	
 	
 	// Abre o banco
 	private void dbOpen(){
 		this.db = this.getWritableDatabase();
 	}
 	
 	// Fecha o banco
 	private void dbClose(){
 		this.db.close();
 	}

}
