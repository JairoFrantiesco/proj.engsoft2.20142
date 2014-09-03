package br.com.wife.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import br.com.wife.dao.DaoGenerico;
import br.com.wife.dao.DispositivoDao;
import br.com.wife.dao.RastreamentoDao;
import br.com.wife.model.Dispositivo;
import br.com.wife.model.Rastreamento;
import br.com.wife.service.ServiceCapture;
import br.com.wife.util.MontaEstruturaBanco;


//import com.example.menu.Cadastro;
import com.example.wifecontroller.R;
import com.google.gson.Gson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Application;
import android.util.Log;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	RastreamentoDao daoRast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Ao iniciar o sistema cria as tabelas do banco caso ainda não exista.
		MontaEstruturaBanco db = new MontaEstruturaBanco(this);
		
		
		// TESTE PARA VERIRICAR SE OS DADOS FORAM INSERIDOS
		DispositivoDao daoDisp = new DispositivoDao(this);
		Dispositivo disp = daoDisp.getDispositivo();
		
		daoRast = new RastreamentoDao(this);
		
		//new ServiceCapture(this);
		
		Intent it = new Intent("SERVICE_CAPTURE");
		
		//ServiceCapture serv = new ServiceCapture(this);
		startService(it);

	}
	
	public void cadastraDispositivo(View v){
		startActivity(new Intent(this, CadastroActivity.class));
	}
	
	public void listaPosicoes(View v){
		if (daoRast.listAllRastreamento().size() > 0)
			startActivity(new Intent(this, ListaActivity.class));
		else
			Toast.makeText(this, "NÃO EXISTEM DADOS COLETADOS", Toast.LENGTH_LONG).show();
	}
	
	public void enviaDados(View v){
		
		Log.d("URL", "CHAMADA ENVIO DADOS JSON");
		
		// call AsynTask to perform network operation on separate thread
        new HttpAsyncTask().execute("http://ntsrv.netche.net.br:8080/WifeControllerWeb/rastreamento/send/");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public String POST(String url, List<Rastreamento> listRast){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
        	DefaultHttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-type", "application/json");
            
            for (Rastreamento rast : listRast) {
            	
            	// Converte o objeto para Json
                Gson gson = new Gson();
    			String json = gson.toJson(rast);
    			
    			Log.d("GSON", json);
    			
    			
    			// 5. set json to StringEntity
                StringEntity se = new StringEntity(json, "UTF8");

                // 6. set httpPost Entity
                httpPost.setEntity(se);

                // 7. Set some headers to inform server about the type of the content   
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");


                // 8. Execute POST request to the given URL
                HttpResponse httpResponse = httpclient.execute(httpPost);

                // 9. receive response as inputStream
                inputStream = httpResponse.getEntity().getContent();
                
                Log.d("RETORNO", convertInputStreamToString(inputStream));

               
                // Deleta o registro local
                daoRast.deletar(rast.getId());
                
			}

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }
	
	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }   
	
	//Envia os dados para o Webservice RestFull
	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

        	List<Rastreamento> listRast = daoRast.listAllRastreamento();

            return POST(urls[0], listRast);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Dados Enviados ao Webservice!", Toast.LENGTH_LONG).show();
       }
        
	}

}
