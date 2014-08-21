package br.com.wife.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.wife.model.DadosRastreamento;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DadosDao extends DaoGenerico {

private static String TABELA = "DADOS";
	
	private SQLiteDatabase db;

	public DadosDao(Context context) {
		super(context);
	}
	
	// Abre o banco
	private void dbOpen(){ this.db = this.getWritableDatabase(); }
					
	// Fecha o banco
	private void dbClose(){ this.db.close(); }
		
	// Insere novo motivo
	public int inserir(DadosRastreamento dados) {
			
		dbOpen();
			
		ContentValues values = new ContentValues();
			
			/*
			values.put("idWebservice", motivo.getIdWebservice());
			values.put("descricao", motivo.getDescricao());
			values.put("idServico", motivo.getIdServico());
			values.put("tipo", motivo.getTipo());
			*/
			
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
	public List<DadosRastreamento> listarDados() {
			
		dbOpen();
					
		Cursor c = db.rawQuery("SELECT * FROM "+ TABELA +" ORDER BY ID", null);

		List<DadosRastreamento> dados = new ArrayList<DadosRastreamento>();

		if (c.moveToFirst()) {

			/*
				// Loop até o final
				do {
					Motivo motivo = new Motivo();
							
					motivos.add(motivo);
					
					motivo.setIdWebservice(c.getInt(0));
					motivo.setDescricao(c.getString(1));
					motivo.setIdServico(c.getInt(2));
					motivo.setTipo(c.getInt(3));

					
				} while (c.moveToNext());
				*/
			}

		c.close();
		dbClose();
			
		return dados;
	
	}

}
