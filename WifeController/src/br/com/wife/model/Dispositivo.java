package br.com.wife.model;

public class Dispositivo {
	
	private Integer id;
	private String imei;
	private String nmDispositivo;
	private Integer intervalo;
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
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
	
	

}
