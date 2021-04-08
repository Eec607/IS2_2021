package es.unican.is2.practica3;

import java.util.Date;
import java.util.Timer;

public class TimedStateController {
	private static TimedStateController cont;
	private static Timer timer;
	
	private TimedStateController() {
		timer = new Timer();
	}
	
	public static TimedStateController getInstance() {
		if (cont == null) {
			cont = new TimedStateController();
        }
        return cont;
	}
	
	public void startRelative(Alarmas context, TimedState estado, int tiempo) {
		context.activarMelodia();
		timer.schedule(null, 0);
		context.desactivarMelodia();	
	}
	
	public void startAbsolute(Alarmas context, TimedState estado, Date instante) {
		
	}
	
	public void cancel() {
		timer.cancel();
	}
}
