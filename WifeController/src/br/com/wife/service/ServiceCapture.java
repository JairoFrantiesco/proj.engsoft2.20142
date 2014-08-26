package br.com.wife.service;

import android.app.Activity;
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
             
            public ServiceCapture(Activity myActivity) {
                this.myActivity = myActivity;
            	
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
                
                 
                locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                        3000,   // 3 sec
                        3, this);
                 
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
                 
                Toast.makeText(this.myActivity.getBaseContext(), "Gps alterado off ", Toast.LENGTH_LONG).show();
            }
         
            @Override
            public void onProviderEnabled(String provider) {
                 
                /******** Called when User on Gps  *********/
                 
                Toast.makeText(this.myActivity.getBaseContext(), "Gps alterado on ", Toast.LENGTH_LONG).show();
            }
         
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // TODO Auto-generated method stub
                 
            }

   }
    
    
    /*
    public class MainActivity extends Activity {

    	@Override
    	protected void onCreate(Bundle savedInstanceState) {
    		super.onCreate(savedInstanceState);
    		setContentView(R.layout.activity_main);
    		
    		new ServiceCapture(this);
    	}
    */