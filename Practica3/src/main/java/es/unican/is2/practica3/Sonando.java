package es.unican.is2.practica3;

public class Sonando extends AlarmasState {
	
	public void apagar (Alarmas context) {
		this.exitAction(context);
		context.setState(getEstadoProgramado());
		getEstadoProgramado().entryAction(context);
		getEstadoProgramado().doAction(context);
		
	}
	
	public void entryAction (Alarmas context) {
		context.activarMelodia();
	}
	
	public void exitAction (Alarmas context) {
		context.desactivarMelodia();
	}

}
