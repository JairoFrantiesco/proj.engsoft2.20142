package br.com.wife.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Rastreamento {
	
	private Integer id;
	private Dispositivo dispositivo;
	private String imei;
	private String data;
	private String hora;
	private String gpsLat;
	private String gpsLong; 
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getGpsLat() {
		return gpsLat;
	}
	public void setGpsLat(String gpsLat) {
		this.gpsLat = gpsLat;
	}
	public String getGpsLong() {
		return gpsLong;
	}
	public void setGpsLong(String gpsLong) {
		this.gpsLong = gpsLong;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Dispositivo getDispositivo() {
		return dispositivo;
	}
	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public JSONObject getJSONObject() {
		JSONObject objJSON = new JSONObject();
		
		try {
			objJSON.accumulate("NmDispositivo", this.getDispositivo().getNmDispositivo());
			objJSON.accumulate("data", this.getData());
			objJSON.accumulate("hora", this.getData());
			objJSON.accumulate("gpsLat", this.getData());
			objJSON.accumulate("gpsLong", this.getData());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return objJSON;
	}

}