package br.com.wife.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.wife.dao.DispositivoDao;
import br.com.wife.dao.RastreamentoDao;
import br.com.wife.model.Dispositivo;
import br.com.wife.model.Rastreamento;
import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Telephony;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/***********  Create class and implements with LocationListener **************/
    public class ServiceCapture extends Service implements LocationListener {
         
            private LocationManager locationManager;
            
            private int tentativasNET = 0;
            
            Dispositivo disp;
            DispositivoDao daoDisp;
            
            Rastreamento rast;
            RastreamentoDao daoRast;
            
            @Override
			public int onStartCommand(Intent intent, int flags, int startId) {
			
					//this.myActivity = myActivity;
                
                daoDisp = new DispositivoDao(this);
                
                // Busca os dados cadastros do dispositivo
                disp = daoDisp.getDispositivo();
            	
                /********** get Gps location service LocationManager object ***********/
                locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
                 
                // Pega o intervalo definido no cadastro do dispositivo
                Integer intervalo = (disp.getIntervalo() * 60) * 1000;
                
                // localização via GPS
                locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                        intervalo,   // Intervalo de tempo que captura a posição
                        0, this);
               // this.statusGPS = true;
                this.tentativasNET = 0;
                
                // localização via wi-fi ou 3G
                locationManager.requestLocationUpdates( LocationManager.NETWORK_PROVIDER,
                		intervalo,   // Intervalo de tempo que captura a posição
                		0, this);
                 
                /********* After registration onLocationChanged method  ********/
                /********* called periodically after each "intervalo" minutes ***********/
				
			return super.onStartCommand(intent, flags, startId);
			}
             
            
             
            /************* Called after each "intervalo" minutes **********/
            @Override
            public void onLocationChanged(Location location) {
            	
            	String str = "Latitude: "+location.getLatitude()+"Longitude: "+location.getLongitude();
                
            	rast = new Rastreamento();
                rast.setDispositivo(disp);
            	rast.setGpsLat(Double.toString(location.getLatitude()));
            	rast.setGpsLong(Double.toString(location.getLongitude()));
            		
            	Calendar  cal = Calendar.getInstance();
            	cal.setTime(new Date());
            	Date data_atual = cal.getTime();
            	SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm");
            	
            	SimpleDateFormat dtFm = new SimpleDateFormat("dd/MM/yy");

            	rast.setData(dtFm.format(data_atual));
            	rast.setHora(dateFormat_hora.format(data_atual));
            	
            	TelephonyManager tm = (TelephonyManager) getSystemService(this.TELEPHONY_SERVICE);

            	// get IMEI
            	String imei = tm.getDeviceId();
            	
            	rast.setImei(imei);
       
            	
            	//String imei = SystemProperties.get("ro.gsm.imei");
            	
            	daoRast = new RastreamentoDao(this);
            	daoRast.inserir(rast);
            	
            	Toast.makeText(this, "DADOS GPS COLETADOS", Toast.LENGTH_LONG).show();
            	
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
                 
            }

			@Override
			public IBinder onBind(Intent arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			

   }