package br.com.wife.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/*Essa classe irá sincronizar os dados armazenados
 * locamente para o webservice.
 * Irá rodar como um serviço também*/
public class SincronizaDados extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
