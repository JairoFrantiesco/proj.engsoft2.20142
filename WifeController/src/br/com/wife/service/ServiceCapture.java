package br.com.wife.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
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
            
            Dispositivo disp;
            DispositivoDao daoDisp;
            
            Rastreamento rast;
            RastreamentoDao daoRast;
             
            public ServiceCapture(Activity myActivity) {
                this.myActivity = myActivity;
                
                daoDisp = new DispositivoDao(myActivity);
                disp = daoDisp.getDispositivo();
            	
                /********** get Gps location service LocationManager object ***********/
                locationManager = (LocationManager) this.myActivity.getSystemService(Context.LOCATION_SERVICE);
                 
                // Pega o intervalo definido no cadastro do dispositivo
                Integer intervalo = (disp.getIntervalo() * 60) * 1000;
                
                locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                        intervalo,   // Intervalo de tempo que captura a posição
                        0, this);
                 
                /********* After registration onLocationChanged method  ********/
                /********* called periodically after each 3 sec ***********/
            }
             
            /************* Called after each 3 sec **********/
            @SuppressLint("SimpleDateFormat") @Override
            public void onLocationChanged(Location location) {
                    
                String str = "Localização "+disp.getNmDispositivo()+": Lat.: "+location.getLatitude()+" / Long.: "+location.getLongitude();
  
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
                
                Toast.makeText(this.myActivity.getBaseContext(), str, Toast.LENGTH_LONG).show();
            }
         
            @Override
            public void onProviderDisabled(String provider) {
                 
                /******** Called when User off Gps *********/
                 
                Toast.makeText(this.myActivity.getBaseContext(), " GPS DESLIGADO ", Toast.LENGTH_LONG).show();
            }
         
            @Override
            public void onProviderEnabled(String provider) {
                 
                /******** Called when User on Gps  *********/
                 
                Toast.makeText(this.myActivity.getBaseContext(), "GPS LIGADO ", Toast.LENGTH_LONG).show();
            }
         
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // TODO Auto-generated method stub
                 
            }

   }