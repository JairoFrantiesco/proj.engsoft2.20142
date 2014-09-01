package br.com.wife.controller;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import br.com.wife.dao.DispositivoDao;
import br.com.wife.model.Dispositivo;

import com.example.wifecontroller.R;


public class CadastroActivity extends Activity{
	
	
	public static final Integer[] MINUTOS = new Integer[]{10,20,30,40,50,60}; 
	
	public EditText edNome;
	public Spinner tempo;
	public Button btCadastra;
	public String imei;

	Dispositivo disp;
	DispositivoDao daoDisp = new DispositivoDao(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);
		
		disp = daoDisp.getDispositivo();
	
		edNome = (EditText) findViewById(R.id.edNome);
		tempo = (Spinner) findViewById(R.id.tempo);
		btCadastra = (Button) findViewById(R.id.btCadastra);
		
		edNome.setText(disp.getNmDispositivo().toString());
		
		//Pega o IMEI do dispositivo
		TelephonyManager mTelephonyMgr;
		mTelephonyMgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		 
		//Atribui o imei na variavel
		imei = mTelephonyMgr.getDeviceId();
		
		//Salva o imei
		disp.setImei(imei);
		 
		//Printa na tela
		TextView tv = (TextView) findViewById(R.id.Imei);
		tv.setText("IMEI: " + imei);
				 

		//DefiniÃ§Ã£o do evento do clique do botão cadastrar
		btCadastra.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				
				//Salva as informaÃ§Ãµes de cadastro
				disp.setId(1);
				disp.setNmDispositivo(edNome.getText().toString());
				disp.setIntervalo((Integer) tempo.getSelectedItem());
				
				daoDisp.atualizar(disp);
				
				//Exibe uma mensagem de sucesso :p
				Toast.makeText(getApplicationContext(), "Dados atualizados com sucesso!", Toast.LENGTH_LONG).show();
				
				finish();

			}
		});
		
	    Spinner combo = (Spinner) findViewById(R.id.tempo);
	    ArrayAdapter<Integer> adp = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, MINUTOS);
	    adp.setDropDownViewResource(android.R.layout.simple_spinner_item);
	    combo.setAdapter(adp);
	    
	}

}
