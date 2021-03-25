package es.unican.is2.practica3;

import java.util.Date;

public class Alarmas {
	
	private AlarmasState state;
	
	public Alarmas() {
		state = AlarmasState.init(this);
	}
	
	public void setState(AlarmasState value) {
		this.state = value;
	}
	
	public void alarmaOn(String id) {
		state.alarmaOn(this, id);
	}
	
	public void alarmaOff(String id) {
		state.alarmaOff(this, id);
	}
		
	public void nuevaAlarma(String id, Date hora) {
		state.nuevaAlarma(this, id, hora);
	}
	
	public void borraAlarma(String id) {
		state.borraAlarma(this, id);
	}
	
	public void apagar() {
		state.apagar(this);
	}
}
