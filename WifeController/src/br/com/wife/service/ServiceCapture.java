package br.com.wife.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;
import br.com.wife.dao.DispositivoDao;
import br.com.wife.dao.RastreamentoDao;
import br.com.wife.model.Dispositivo;
import br.com.wife.model.Rastreamento;

/***********  Create class and implements with LocationListener **************/
    public class ServiceCapture implements LocationListener {
         
            private LocationManager locationManager;
            private Activity myActivity;
            
            private int tentativasNET = 0;
            
            Dispositivo disp;
            DispositivoDao daoDisp;
            
            Rastreamento rast;
            RastreamentoDao daoRast;
             
            public ServiceCapture(Activity myActivity) {
                this.myActivity = myActivity;
                
                daoDisp = new DispositivoDao(myActivity);
                
                // Busca os dados cadastros do dispositivo
                disp = daoDisp.getDispositivo();
            	
                /********** get Gps location service LocationManager object ***********/
                locationManager = (LocationManager) this.myActivity.getSystemService(Context.LOCATION_SERVICE);
                 
                // Pega o intervalo definido no cadastro do dispositivo
                Integer intervalo = (disp.getIntervalo() * 60) * 1000;
                
                // localização via GPS
                locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                        intervalo,   // Intervalo de tempo que captura a posição
                        0, this);
                this.tentativasNET = 0;
                
                // localização via wi-fi ou 3G
//                locationManager.requestLocationUpdates( LocationManager.NETWORK_PROVIDER,
//                		intervalo,   // Intervalo de tempo que captura a posição
//                		0, this);
                 
                /********* After registration onLocationChanged method  ********/
                /********* called periodically after each "intervalo" minutes ***********/
            }
            
            private void enviaJson(final Context cont) {
                new Thread() {
                    @Override
                    public void run() {
	                    super.run();
	                    RastreamentoDao coordDB = new RastreamentoDao(cont);
	                    List<Rastreamento> coordenadas = coordDB.listAllRastreamento();
	                    JSONArray arrJSON = new JSONArray();
	                    for (Rastreamento coordenada : coordenadas) {
	                    	arrJSON.put(coordenada.getJSONObject());
	                    }
	                    JSONObject objJSON = new JSONObject();
	                    try {                       
	                         String url = "http://ntsrv.netche.net.br:8080/WifeControllerWeb/rastreamento/send";//EXEMPLO
	                         objJSON.accumulate("rastreamento", arrJSON.toString());
	                         ConexaoHttpJson.enviarSolicitacao(url, objJSON);                           
                         } catch (ClientProtocolException e) {
                             e.printStackTrace();
                         } catch (IOException e) {
                             e.printStackTrace();
                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                    }
                }.start();   
            }
             
            /************* Called after each "intervalo" minutes **********/
            @Override
            public void onLocationChanged(Location location) {
            	// O código abaixo pega localização via GPS.
            	// Porém pega localização via NET somente quando GPS desligado 
            	//  ou se as 3 últimas tentativas foram NET (onde supostamente o GPS ta falhando).
            	// Quando pegar GPS novamente, zera a contagem.
            	
            	if (location.getProvider().contentEquals(LocationManager.NETWORK_PROVIDER)) {
            		if (this.tentativasNET < 3) {
            			this.tentativasNET++;
            		}
            	} else {
            		this.tentativasNET = 0;
            	}
            	
            	if ((this.tentativasNET == 0) || (this.tentativasNET >= 3)) {
            		String str = "LLatitude: "+location.getLatitude()+"Longitude: "+location.getLongitude();
            		
                    rast = new Rastreamento();
                    rast.setDispositivo(disp);
                    rast.setGpsLat(Double.toString(location.getLatitude()));
                    rast.setGpsLong(Double.toString(location.getLongitude()));
    	
    	            Calendar  cal = Calendar.getInstance();
    	            cal.setTime(new Date());
    	            Date data_atual = cal.getTime();
    	            SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm:ss");
    	            rast.setData(data_atual.toString());
    	            rast.setHora(dateFormat_hora.format(data_atual));

                    daoRast = new RastreamentoDao(myActivity);
                    daoRast.inserir(rast);
            		
                    this.enviaJson(this.myActivity.getBaseContext());
//            		String url = "http://ntsrv.netche.net.br:8080/WifeControllerWeb/rastreamento/send";
//            		SincronizaDados ws = new SincronizaDados();
//            		str = ws.enviarDadosPost(url);
            		
            		Toast.makeText(this.myActivity.getBaseContext(), str, Toast.LENGTH_LONG).show();
            	}
            }
         
            @Override
            public void onProviderDisabled(String provider) {
                /******** Called when User off Gps or net *********/
                if (provider.contentEquals(LocationManager.GPS_PROVIDER)) {
                	this.tentativasNET = 3;
                }
            	
                //Toast.makeText(this.myActivity.getBaseContext(), " GPS DESLIGADO ", Toast.LENGTH_LONG).show();
            }
         
            @Override
            public void onProviderEnabled(String provider) {
                /******** Called when User on Gps or net *********/
                 
                //Toast.makeText(this.myActivity.getBaseContext(), "GPS LIGADO ", Toast.LENGTH_LONG).show();
            }
         
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // TODO Auto-generated method stub
                 
            	//Toast.makeText(this.myActivity.getBaseContext(), "GPS LIGADO ", Toast.LENGTH_LONG).show();
            }

   }