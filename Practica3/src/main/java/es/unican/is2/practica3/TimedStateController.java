package es.unican.is2.practica3;

import java.util.Date;

public class TimedStateController {
	boolean parar = false;
	
	public static TimedStateController getInstance() {
		return null;
	}
	
	public void startRelative(Alarmas context, TimedState estado, int tiempo) {
		int counter = 0;
		context.activarMelodia();
		while(!parar || counter < tiempo) {
			xD
		}
		context.desactivarMelodia();	
	}
	
	public void startAbsolute(Alarmas context, TimedState estado, Date instante) {
		
	}
	
	public void cancel() {
		parar = true;
	}
}
