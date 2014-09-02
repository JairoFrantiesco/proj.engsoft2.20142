package br.com.wife.util;

import android.content.Context;
import android.util.Log;
import br.com.wife.dao.DaoGenerico;
import br.com.wife.dao.DispositivoDao;
public class MontaEstruturaBanco extends DaoGenerico {

	//private Context context;
	
	// Tabela com os dados de rastreamento
    private static final String RASTREAMENTO = 
    		"CREATE TABLE IF NOT EXISTS RASTREAMENTO ( " +
            " ID integer PRIMARY KEY, " +
            " FKDISPOSITIVO integer, " + 	// FK da tabela de dispositivo
            " DATA varchar(10), " +
            " HORA varchar(5), " +
            " GPSLAT varchar(25), " +			// Dados de latitude
            " GPSLONG varchar(25) " +			// Dados de longitude
            " );";	
    
    // Tabela com os dados do dispositivo
    private static final String DISPOSITIVO = 
    		"CREATE TABLE IF NOT EXISTS DISPOSITIVO ( " +
            " ID integer PRIMARY KEY, " +
            " NOME varchar(30), " +
            " IMEI varchar(20), " +
            " INTERVALO integer " +
            " );";

  public MontaEstruturaBanco(Context context) {
 		super(context);

 		//this.context = context;
 		
 		Log.i("BASE DE DADOS", "CRIANDO ESTRUTURA");
		
		dbOpen();
//		Log.d("VERSÃO BASE ATUAL", Integer.toString(db.getVersion()));
		
		// Cria as tabelas
		db.execSQL(DISPOSITIVO);
		db.execSQL(RASTREAMENTO);
		
		dbClose();

		
		//Insere registro padrão dos dados do dispositivo ao criar pela primeira vez a base.
		DispositivoDao dao = new DispositivoDao(context);
		
		if(dao.quantRegistro() == 0){
			dbOpen();
			db.execSQL("INSERT INTO DISPOSITIVO(NOME, IMEI, INTERVALO) VALUES('TESTE', '0', 10)");
			dbClose();
		}
		 
	}	

}
