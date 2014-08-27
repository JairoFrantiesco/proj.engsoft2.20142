package br.com.wife.service;

import br.com.wife.dao.DispositivoDao;
import br.com.wife.model.Dispositivo;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

/***********  Create class and implements with LocationListener **************/
    public class ServiceCapture implements LocationListener {
         
            private LocationManager locationManager;
            private Activity myActivity;
            
            Dispositivo disp;
            DispositivoDao daoDisp;
             
            public ServiceCapture(Activity myActivity) {
                this.myActivity = myActivity;
                
                daoDisp = new DispositivoDao(myActivity);
                
                // Busca os dados cadastros do dispositivo
                disp = daoDisp.getDispositivo();
            	
                /********** get Gps location service LocationManager object ***********/
                locationManager = (LocationManager) this.myActivity.getSystemService(Context.LOCATION_SERVICE);
                 
                /* CAL METHOD requestLocationUpdates */
                   
                  // Parameters :
                  //   First(provider)    :  the name of the provider with which to register
                  //   Second(minTime)    :  the minimum time interval for notifications,
                  //                         in milliseconds. This field is only used as a hint
                  //                         to conserve power, and actual time between location
                  //                         updates may be greater or lesser than this value.
                  //   Third(minDistance) :  the minimum distance interval for notifications, in meters
                  //   Fourth(listener)   :  a {#link LocationListener} whose onLocationChanged(Location)
                  //                         method will be called for each location update
                
                
                // Pega o intervalo definido no cadastro do dispositivo
                Integer intervalo = (disp.getIntervalo() * 60) * 1000;
                
                locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                        intervalo,   // Intervalo de tempo que captura a posição
                        0, this);
                 
                /********* After registration onLocationChanged method  ********/
                /********* called periodically after each 3 sec ***********/
            }
             
            /************* Called after each 3 sec **********/
            @Override
            public void onLocationChanged(Location location) {
                    
                String str = "Latitude: "+location.getLatitude()+"Longitude: "+location.getLongitude();
  
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