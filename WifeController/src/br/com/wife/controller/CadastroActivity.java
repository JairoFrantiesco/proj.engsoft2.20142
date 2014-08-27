package br.com.wife.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import br.com.wife.dao.DispositivoDao;
import br.com.wife.model.Dispositivo;

import com.example.wifecontroller.R;

@SuppressLint("CutPasteId") public class CadastroActivity extends Activity{
	
	
	public static final Integer[] MINUTOS = new Integer[]{1,5,10,30,60}; 
	
	public EditText edNome;
	public Spinner tempo;
	public Button btCadastra;
	
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

		//Definição do evento do clique do botao cadastrar
		btCadastra.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				
				//Salva as informações de cadastro
				disp.setId(1);
				disp.setNmDispositivo(edNome.getText().toString());
				disp.setIntervalo((Integer) tempo.getSelectedItem());
				
				daoDisp.atualizar(disp);
				
				//Exibe uma mensagem de sucesso
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
