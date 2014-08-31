package br.com.wife.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rastreamento {
	
	private Integer id;
	private Dispositivo dispositivo; // FK NA TABELA = FKIMEI
	private String data;
	private String hora;
	private String gpsLat;
	private String gpsLong;
	
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
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "GPSlat: "+this.getGpsLat();
	}

}
