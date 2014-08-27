package br.com.wife.controller;

import br.com.wife.dao.DispositivoDao;
import br.com.wife.model.Dispositivo;
import br.com.wife.service.ServiceCapture;
import br.com.wife.util.MontaEstruturaBanco;


//import com.example.menu.Cadastro;
import com.example.wifecontroller.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Ao iniciar o sistema cria as tabelas do banco caso ainda não exista.
		MontaEstruturaBanco db = new MontaEstruturaBanco(this);
		
		
		// TESTE PARA VERIRICAR SE OS DADOS FORAM INSERIDOS
		DispositivoDao daoDisp = new DispositivoDao(this);
		Dispositivo disp = daoDisp.getDispositivo();
		
		new ServiceCapture(this);

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
