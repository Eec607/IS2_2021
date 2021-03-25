package es.unican.is2.practica3;

import java.util.Date;

public class Desprogramado extends AlarmasState {
	
	public void borraAlarma (Alarmas context, String id) {
		context.eliminaAlarma(id);
	}
	
	public void nuevaAlarma (Alarmas context, String id, Date hora) {
		context.anhadeAlarma(new Alarma(id));
		context.setState(getEstadoProgramado());
		getEstadoProgramado().entryAction(context);
		getEstadoProgramado().doAction(context);
	}
	
	public void alarmaOn (Alarmas context, String id) {
		context.activaAlarma(id);
		context.setState(getEstadoProgramado());
		getEstadoProgramado().entryAction(context);
		getEstadoProgramado().doAction(context);
	}

}
