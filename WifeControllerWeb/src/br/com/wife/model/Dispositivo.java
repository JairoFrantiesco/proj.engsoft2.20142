package br.com.wife.model;

public class Dispositivo {

	private Integer idImei;	// Código IMEI do aparelho. Código único de cada dispositivo
	private String nmDispositivo;
	
	
	public String getNmDispositivo() {
		return nmDispositivo;
	}
	public void setNmDispositivo(String nmDispositivo) {
		this.nmDispositivo = nmDispositivo;
	}
	public Integer getCodImei() {
		return idImei;
	}
	public void setCodImei(Integer codImei) {
		this.idImei = codImei;
	}

}
