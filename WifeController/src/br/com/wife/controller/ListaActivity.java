package br.com.wife.controller;

import java.util.Arrays;
import java.util.List;

import br.com.wife.dao.RastreamentoDao;
import br.com.wife.model.Rastreamento;

import com.example.wifecontroller.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class ListaActivity extends Activity {

	// Atributos de tela

	private ListView lvListagem;

	// para TESTES - excluir depois

	// coordenadas
	private String teste[] = { "30.2105, 50.3698", "30.5190, 50.5655" };

	// datas só para exemplo, as corretas serao recebidas do DB
	private String data[] = { "25/08/2014", "26/08/2014" };

	// arrayAdapter converte listas ou vetores em view
	private ArrayAdapter<String> adapter;
	private ArrayAdapter<String> dataAdapter;

	// Definição do layout de exibição da listagem
	private int adapterLayout = android.R.layout.simple_list_item_1;

	private Button gerar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// ligação da tela ao seu controlador
		setContentView(R.layout.activity_lista);

		// ligacao dos componentes de tela aos atricutos da activity

		lvListagem = (ListView) findViewById(R.id.lvListagem);

		gerar = (Button) findViewById(R.id.gerar);

		gerar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				// criar ação do botao

			}
		});
	}

	private void carregaCoordenadas() {
		// criar obj dao

		// obj arrayAdapter sabe converter listas ou vetores em View
		this.adapter = new ArrayAdapter<String>(this, adapterLayout, teste);
		// associacao do adapter a listView
		this.lvListagem.setAdapter(adapter);

	}

	private void carregaDatas() {
		// criar obj dao

		// recupera datas arquivadas no db e coloca no spinner
		Spinner datas = (Spinner) findViewById(R.id.data);
		dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, data);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_item);
		datas.setAdapter(dataAdapter);

	}

	protected void onResume() {
		super.onResume();

		// carrega datas e coordenadas
		this.carregaCoordenadas();
		this.carregaDatas();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
