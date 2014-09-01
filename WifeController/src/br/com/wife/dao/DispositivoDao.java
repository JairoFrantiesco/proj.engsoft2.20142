package br.com.wife.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.com.wife.model.Dispositivo;

public class DispositivoDao extends DaoGenerico {

	private static String TABELA = "DISPOSITIVO";
	
	public DispositivoDao(Context context) {
		super(context);
	}
	
	// Atualiza os dados do dispositivo
	public int atualizar(Dispositivo disp) {
			
		dbOpen();
			
		ContentValues values = new ContentValues();
			
		values.put("NOME", disp.getNmDispositivo());
		values.put("INTERVALO", disp.getIntervalo());
					
		String id = String.valueOf(disp.getId());

		String where = "id" + "=?";
		String[] whereArgs = new String[] { id };

		int i = db.update(TABELA, values, where, whereArgs);
			
		dbClose();
			
	return i;
	}
	
	// Verifica se existe registro com os dados do dispositivo
	// Essa tabela possui somente 1 registro
	public Integer quantRegistro(){
			
		dbOpen();	
		Cursor c = db.rawQuery("SELECT * FROM " + TABELA, null);
		
		int i = c.getCount();
			
		c.close();
		dbClose();

	return i;
	}
	
	// Retorna os dados do dispositivo
	public Dispositivo getDispositivo(){
			
		dbOpen();
		
		// Retorna os dados cadastros do dispositivo
		Cursor c = db.rawQuery("SELECT * FROM " + TABELA, null);
			
		if (c.getCount() > 0){
				
			Dispositivo disp = new Dispositivo();
				
			c.moveToFirst();
			disp.setId(c.getInt(c.getColumnIndex("ID")));
			disp.setNmDispositivo(c.getString(c.getColumnIndex("NOME")));
			disp.setIntervalo(c.getInt(c.getColumnIndex("INTERVALO")));
				
			c.close();
			dbClose();
				
			return disp;
		}
			
	c.close();
	dbClose();
	return null;
	}

}
