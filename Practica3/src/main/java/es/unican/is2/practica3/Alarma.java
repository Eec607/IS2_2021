package es.unican.is2.practica3;

import java.util.Date;

public class Alarma {
	private String id;
	private Date hora;
	
	public Alarma(String id, Date hora) {
		this.id = id;
		this.hora = hora;
	}
	
	public String getId() {
		return id;
	}
	
	public Date getHora() {
		return hora;
	}
}
