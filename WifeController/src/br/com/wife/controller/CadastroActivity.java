package br.com.wife.controller;

import br.com.wife.model.DadosUsuario;


import com.example.wifecontroller.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	
	DadosUsuario dUsuario = new DadosUsuario();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);
	
		edNome = (EditText) findViewById(R.id.edNome);
		tempo = (Spinner) findViewById(R.id.tempo);
		btCadastra = (Button) findViewById(R.id.btCadastra);

		//Definição do evento do clique do botao cadastrar
		btCadastra.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				
				//Salva as informações de cadastro
				dUsuario.setNome(edNome.getText().toString());
				dUsuario.setTempoAtualizacao((Integer) tempo.getSelectedItem());
				
				//Exibe uma mensagem de sucesso
				Toast.makeText(getApplicationContext(), "Cadastro conluído!", Toast.LENGTH_LONG).show();
				
				//Chama a tela de visualização

			}
		});
		
	    Spinner combo = (Spinner) findViewById(R.id.tempo);
	    ArrayAdapter adp = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, MINUTOS);
	    adp.setDropDownViewResource(android.R.layout.simple_spinner_item);
	    combo.setAdapter(adp);
	    
	}

}
