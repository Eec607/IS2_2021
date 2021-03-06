package es.unican.is2.practica3;

import java.util.Date;

public class Alarma implements Comparable<Alarma> {
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

	/**
	 * Redefinici?n del m?todo compareTo para que las alarmas se ordenen en la cola de prioridad en base a su hora 
	 * (cuanto m?s pr?xima al instante actual, m?s cercana estar? la alarma a la cabeza de la cola)
	 */
	public int compareTo(Alarma a) {
		return hora.compareTo(a.getHora());
	}
	
	@Override
	public String toString() {
		return "Id : " + id + ", Hora : " + hora;
	}
	
}
