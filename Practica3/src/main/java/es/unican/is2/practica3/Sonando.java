package es.unican.is2.practica3;

public class Sonando extends AlarmasState implements TimedState {
	
	public void apagar (Alarmas context) {
		this.exitAction(context);
		context.setState(getEstadoProgramado());
		getEstadoProgramado().entryAction(context);
		getEstadoProgramado().doAction(context);
		
	}
	
	public void entryAction (Alarmas context) {
		context.controlador.startRelative(context, this, context.intervaloSonar());
	}
	
	public void exitAction (Alarmas context) {
		context.desactivarMelodia();
	}

	public void timeout(Alarmas context) {
		this.exitAction(context);
		getEstadoProgramado().entryAction(context);
		getEstadoProgramado().doAction(context);
		context.setState(getEstadoProgramado());
	}

}
