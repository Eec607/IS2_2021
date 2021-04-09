package es.unican.is2.practica3;

import java.util.Date;

public class Desprogramado extends AlarmasState {
	
	public void borraAlarma (Alarmas context, String id) {
		context.eliminaAlarma(id);
	}
	
	public void nuevaAlarma (Alarmas context, String id, Date hora) {
		context.setState(getEstadoProgramado());
		getEstadoProgramado().entryAction(context);
		getEstadoProgramado().doAction(context);
		getEstadoProgramado().nuevaAlarma(context, id, hora);
	}
	
	public void alarmaOn (Alarmas context, String id) {
		Alarma alarma = context.alarma(id);
		context.activaAlarma(alarma);
		context.setState(getEstadoProgramado());
		getEstadoProgramado().entryAction(context);
		getEstadoProgramado().doAction(context);
	}

}
