package com.everis.datascraping;

public class Transaccion {
	
	String modulo;
	String Tcode;
	String description;
	String siglasModulo;
	
	
	public Transaccion(String modulo, String tcode, String description,
			String siglasModulo) {
		super();
		this.modulo = modulo;
		Tcode = tcode;
		this.description = description;
		this.siglasModulo = siglasModulo;
	}


	public String getModulo() {
		return modulo;
	}


	public void setModulo(String modulo) {
		this.modulo = modulo;
	}


	public String getTcode() {
		return Tcode;
	}


	public void setTcode(String tcode) {
		Tcode = tcode;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getSiglasModulo() {
		return siglasModulo;
	}


	public void setSiglasModulo(String siglasModulo) {
		this.siglasModulo = siglasModulo;
	}
	
	
	
	
}
