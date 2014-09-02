package br.com.wife.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import br.com.wife.dao.DispositivoDao;
import br.com.wife.model.Dispositivo;
import br.com.wife.service.ServiceCapture;
import br.com.wife.util.MontaEstruturaBanco;

import com.example.wifecontroller.R;
//import com.example.menu.Cadastro;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Ao iniciar o sistema cria as tabelas do banco caso ainda não exista.
	
		new MontaEstruturaBanco(this);
		
		DispositivoDao dao = new DispositivoDao(this);
		Dispositivo disp = dao.getDispositivo();
		
		if (disp.getImeiDispositivo().equals("0")) {
			
			setContentView(R.layout.activity_main);
			
			new ServiceCapture(this); //Comentar para testar <---
			
		}else{
			startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
			finish();
		}

	}
	
	public void cadastraDispositivo(View v){
		startActivity(new Intent(this, CadastroActivity.class));
	}
	
	public void listaPosicoes(View v){
		startActivity(new Intent(this, ListaActivity.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
