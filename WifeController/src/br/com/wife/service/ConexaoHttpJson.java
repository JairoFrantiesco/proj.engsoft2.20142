package br.com.wife.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ConexaoHttpJson {
	
	public static JSONObject enviarSolicitacao(String urlPost,JSONObject obj) throws ClientProtocolException, IOException, JSONException {      
	
	    HttpContext localContext = new BasicHttpContext();
	    HttpClient client = new DefaultHttpClient();  
	    HttpPost post = new HttpPost(urlPost); 
	    post.setHeader("Content-type", "application/json");
	
	    post.setEntity(new StringEntity(obj.toString()));
	    HttpResponse response = client.execute(post,localContext);  
	
	    HttpEntity entity = response.getEntity();
	    InputStream instream = entity.getContent();
	
	    String resultString= convertStreamToString(instream);
	
	    JSONObject jsonObjRecv = new JSONObject(resultString);
	
	    Log.i("json servidor", jsonObjRecv.toString());
	    instream.close();
	
	    return  jsonObjRecv;
	}
	
	private static String convertStreamToString(InputStream is) {
	
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();
	
	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }           
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {               
	            e.printStackTrace();            
	        }
	    }
	    return sb.toString();
	}
}
