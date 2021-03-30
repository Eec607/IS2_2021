package es.unican.is2.practica3;

import java.util.Date;

public class Programado extends AlarmasState {
	
	public void borraAlarma (Alarmas context, String id) {
		context.eliminaAlarma(id);
	}
	
	public void nuevaAlarma (Alarmas context, String id, Date hora) {
		context.anhadeAlarma(id, hora);
	}
	
	public void alarmaOn (Alarmas context, String id) {
		Alarma alarma = context.alarma(id);
		context.activaAlarma(alarma);
	}
	
	public void alarmaOff (Alarmas context, String id) {
		Alarma alarma = context.alarma(id);
		context.desactivaAlarma(alarma);
	}
	
}
