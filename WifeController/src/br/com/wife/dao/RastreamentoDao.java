package br.com.wife.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.wife.model.Dispositivo;
import br.com.wife.model.Rastreamento;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RastreamentoDao extends DaoGenerico {

	private static String TABELA = "RASTREAMENTO";
	
	private Dispositivo disp;

	public RastreamentoDao(Context context) {
		super(context);
		
		// Pega os dados do dispositivo
		DispositivoDao dao = new DispositivoDao(context);
		disp = dao.getDispositivo();
		
	}
	
	// Insere dados do rastreamento
	public int inserir(Rastreamento dados) {
			
		dbOpen();
			
		ContentValues values = new ContentValues();

			values.put("FKDISPOSITIVO", dados.getDispositivo().getId());
			values.put("DATA", dados.getData());
			values.put("HORA", dados.getHora());
			values.put("GPSLAT", dados.getGpsLat());
			values.put("GPSLONG", dados.getGpsLong());
			
		int i = (int) db.insert(TABELA, "", values);

		dbClose();
			
		return i;
	}

	// Deleta um registro
	public int deletar(int id) {
			
		dbOpen();
			
		String where = "ID" + "=?";

		String _id = String.valueOf(id);
		String[] whereArgs = new String[] { _id };
			
		int i = db.delete(TABELA, where, whereArgs);
			
		dbClose();
			
		return i;
	}
		
	// Retorna uma lista com os dados inseridos localmente
	public List<Rastreamento> listAllRastreamento() {
			
		dbOpen();
					
		Cursor c = db.rawQuery("SELECT * FROM "+ TABELA +" ORDER BY ID", null);

		List<Rastreamento> listaRastreamento = new ArrayList<Rastreamento>();

		if (c.moveToFirst()) {

			do {
					Rastreamento rast = new Rastreamento();
					
					rast.setId(c.getInt(c.getColumnIndex("ID")));
					rast.setDispositivo(disp);
					rast.setData(c.getString(c.getColumnIndex("DATA")));
					rast.setHora(c.getString(c.getColumnIndex("HORA")));
					rast.setGpsLat(c.getString(c.getColumnIndex("GPSLAT")));
					rast.setGpsLong(c.getString(c.getColumnIndex("GPSLONG")));
					
					listaRastreamento.add(rast);

				} while (c.moveToNext());

			}

		c.close();
		dbClose();
			
		return listaRastreamento;
	
	}

}
