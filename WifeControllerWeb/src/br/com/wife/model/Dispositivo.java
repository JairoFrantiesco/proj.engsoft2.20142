package br.com.wife.model;

public class Dispositivo {

	private Integer id;
	private String imei;	// Código IMEI do aparelho. Código único de cada dispositivo
	private String nmDispositivo;
	
	
	public String getNmDispositivo() {
		return nmDispositivo;
	}
	public void setNmDispositivo(String nmDispositivo) {
		this.nmDispositivo = nmDispositivo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}

}
