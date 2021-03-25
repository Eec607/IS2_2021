package es.unican.is2.practica3;

import java.util.Date;

public class Programado extends AlarmasState {
	
	public void borraAlarma (Alarmas context, String id) {
		context.eliminaAlarma(id);
	}
	
	public void nuevaAlarma (Alarmas context, String id, Date hora) {
		context.anhadeAlarma(new Alarma(id));
	}
	
	public void alarmaOn (Alarmas context, String id) {
		context.activaAlarma(id);
	}
	
	public void alarmaOff (Alarmas context, String id) {
		context.desactivaAlarma(id);
	}
	
}
