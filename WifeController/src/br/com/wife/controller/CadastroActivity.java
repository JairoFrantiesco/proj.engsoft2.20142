package br.com.wife.controller;

import br.com.wife.dao.DispositivoDao;
import br.com.wife.model.Dispositivo;


import com.example.wifecontroller.R;

import android.app.Activity;
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
import android.widget.Toast;

public class CadastroActivity extends Activity{
	
	
	public static final Integer[] MINUTOS = new Integer[]{1,5,10,30,60}; 
	
	public EditText edNome;
	public Spinner tempo;
	public Button btCadastra;
	String imei;
	
	Dispositivo disp;
	DispositivoDao daoDisp = new DispositivoDao(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);
		
		TelephonyManager tm = (TelephonyManager) getSystemService(this.TELEPHONY_SERVICE);
    	// get IMEI
    	imei = tm.getDeviceId();
		
		disp = daoDisp.getDispositivo();
	
		edNome = (EditText) findViewById(R.id.edNome);
		tempo = (Spinner) findViewById(R.id.tempo);
		btCadastra = (Button) findViewById(R.id.btCadastra);
		
		edNome.setText(imei);

		//Definição do evento do clique do botao cadastrar
		btCadastra.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				
				//Salva as informações de cadastro
				disp.setId(1);
				disp.setNmDispositivo(imei);
				disp.setIntervalo((Integer) tempo.getSelectedItem());
				
				daoDisp.atualizar(disp);
				
				//Exibe uma mensagem de sucesso
				Toast.makeText(getApplicationContext(), "Dados atualizados com sucesso!", Toast.LENGTH_LONG).show();
				
				finish();

			}
		});
		
	    Spinner combo = (Spinner) findViewById(R.id.tempo);
	    ArrayAdapter adp = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, MINUTOS);
	    adp.setDropDownViewResource(android.R.layout.simple_spinner_item);
	    combo.setAdapter(adp);
	    
	    if(disp.getIntervalo() != null)
		    for (int i = 0; i < combo.getCount(); i++) {
		    	int f = (Integer) combo.getItemAtPosition(i);
				if (f == disp.getIntervalo()) {
					combo.setSelection(i);
				}
			}
	    
	}

}
