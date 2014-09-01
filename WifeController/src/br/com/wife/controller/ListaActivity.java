package br.com.wife.controller;


import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import br.com.wife.dao.RastreamentoDao;

import com.example.wifecontroller.R;

public class ListaActivity extends Activity {

	// Atributos de tela

	private ListView lvListagem;

	// para TESTES - excluir depois

	// coordenadas
	private List<String> coordenadas;

	// datas só para exemplo, as corretas serao recebidas do DB
	private List<String> data;
	private String dataTopo = null;

	// arrayAdapter converte listas ou vetores em view
	private ArrayAdapter<String> adapter;
	private ArrayAdapter<String> dataAdapter;

	// Definição do layout de exibição da listagem
	private int adapterLayout = android.R.layout.simple_list_item_1;

	private Spinner datas;
	private Button gerar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// ligação da tela ao seu controlador
		setContentView(R.layout.activity_lista);

		// ligacao dos componentes de tela aos atricutos da activity

		lvListagem = (ListView) findViewById(R.id.lvListagem);
		datas = (Spinner) findViewById(R.id.data);
		
		gerar = (Button) findViewById(R.id.gerar);

		gerar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				String data = datas.getSelectedItem().toString();
				carregaCoordenadas(data);
				Toast.makeText(ListaActivity.this, data, Toast.LENGTH_LONG).show();
			}
		});
	}

	private void carregaCoordenadas(String data) {
		// criar obj dao
		RastreamentoDao coordDB = new RastreamentoDao(this);
		
		coordenadas = coordDB.retornaCoordenadas(data);
		
		// obj arrayAdapter sabe converter listas ou vetores em View
		this.adapter = new ArrayAdapter<String>(this, adapterLayout, coordenadas);
		// associacao do adapter a listView
		this.lvListagem.setAdapter(adapter);

	}

	private void carregaDatas() {
		// criar obj dao
		RastreamentoDao dataDB = new RastreamentoDao(this);
		
		//somente para testes -->
		/*
		Rastreamento a = new Rastreamento();
		a.setData("26/08/2014");
		a.setId(1);
		a.setGpsLat("105.6661");
		a.setGpsLong("208.9996");
		a.setDispositivo(new Dispositivo());
		
		dataDB.inserir(a);
		
		Rastreamento a2 = a;
		a2.setId(2);
		a2.setData("27/08/2014");
		a2.setHora("05:10");
		a2.setGpsLat("500.3666");
		
		
		dataDB.inserir(a2);	
		*/
		
		data = dataDB.retornaDatas();
		dataTopo = data.get(0);
		// recupera datas arquivadas no db e coloca no spinner
		
		dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, data);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_item);
		datas.setAdapter(dataAdapter);

	}

	protected void onResume() {
		super.onResume();

		// carrega datas e coordenadas
		
		this.carregaDatas();
		this.carregaCoordenadas(dataTopo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
