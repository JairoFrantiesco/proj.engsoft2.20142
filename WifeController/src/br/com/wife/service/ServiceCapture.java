package br.com.wife.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* Essa classe ir� capturar os dados do gps e
 * armazenar locamente para depois ser enviado para o webservice.
 * Roda como um servi�o em background */
public class ServiceCapture extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
