package br.com.wife.controller;

import br.com.wife.dao.DispositivoDao;
import br.com.wife.model.Dispositivo;
import br.com.wife.util.MontaEstruturaBanco;

//import com.example.menu.Cadastro;
import com.example.wifecontroller.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.content.Intent;
import android.view.Menu;

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
		
		Log.d("ID DISP", disp.getId().toString());
		Log.d("NOME DISP", disp.getNmDispositivo());
		Log.d("INTERVALO DISP", disp.getIntervalo().toString());
		
		new ServiceCapture(this);
		//para testar no simulador após aberto, botão DDMS, aba "Emulator Control".
		
		//Tela de cadastro
		startActivity(new Intent(this, CadastroActivity.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
