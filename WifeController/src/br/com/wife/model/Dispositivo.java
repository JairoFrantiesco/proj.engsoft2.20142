package br.com.wife.model;

public class Dispositivo {
	
	private Integer id;
	private String nmDispositivo;
	private String imei;
	private Integer intervalo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNmDispositivo() {
		return nmDispositivo;
	}
	public void setNmDispositivo(String nmDispositivo) {
		this.nmDispositivo = nmDispositivo;
	}
	public Integer getIntervalo() {
		return intervalo;
	}
	public void setIntervalo(Integer intervalo) {
		this.intervalo = intervalo;
	}
	public void setImei(String imei){
		this.imei = imei;
	}
	public String getImeiDispositivo() {
		return imei;
	}
	
}
