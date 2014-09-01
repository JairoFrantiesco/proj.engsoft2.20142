package br.com.wife.service;

import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;
import br.com.wife.dao.RastreamentoDao;
import br.com.wife.model.Rastreamento;

/*Essa classe irá sincronizar os dados armazenados
 * locamente para o webservice.
 * Irá rodar como um serviço também*/
public class SincronizaDados extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	public String enviarDadosPost(String url) {
		// TODO Auto-generated method stub
	
        InputStream inputStream = null;
        String result = "";
        
        if (this.isConnected()) {
	        try {
	
	            // 1. create HttpClient
	            HttpClient httpclient = new DefaultHttpClient();
	
	            // 2. make POST request to the given URL
	            HttpPost httpPost = new HttpPost(url);
	
	            String json = this.getJSONString();
	
	            // 5. set json to StringEntity
	            StringEntity se = new StringEntity(json);
	
	            // 6. set httpPost Entity
	            httpPost.setEntity(se);
	
	            // 7. Set some headers to inform server about the type of the content   
	            httpPost.setHeader("Accept", "application/json");
	            httpPost.setHeader("Content-type", "application/json");
	
	            // 8. Execute POST request to the given URL
	            HttpResponse httpResponse = httpclient.execute(httpPost);
	
	            // 9. receive response as inputStream
	            inputStream = httpResponse.getEntity().getContent();
	
	            // 10. convert inputstream to string
	            if(inputStream != null)
	                //result = convertInputStreamToString(inputStream);
	            	result = "It work!";
	            else
	                result = "Did not work!";
	
	        } catch (Exception e) {
	            Log.d("InputStream", e.getLocalizedMessage());
	        }
	
        }
        // 11. return result
        return result;
	}
	
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) 
                return true;
            else
                return false;    
    }
	
	public String getJSONString() {
		RastreamentoDao coordDB = new RastreamentoDao(this);
		List<Rastreamento> coordenadas = coordDB.listAllRastreamento();
		
		JSONArray arrJSON = new JSONArray();
		for (Rastreamento coordenada : coordenadas) {
			arrJSON.put(coordenada.getJSONObject());
		}
		
		return arrJSON.toString();
	}

}