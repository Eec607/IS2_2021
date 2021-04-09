package es.unican.is2.practica3;

import java.util.Timer;
import java.util.TimerTask;

public class Sonando extends AlarmasState /*implements TimedState*/ {
	
	private static Timer timerMelodia;
	private MelodiaTask melodiaTask;
	
	public void apagar (Alarmas context) {
		this.exitAction(context);
		context.setState(getEstadoProgramado());
		getEstadoProgramado().entryAction(context);
		getEstadoProgramado().doAction(context);
		
	}
	
	public void entryAction (Alarmas context) {
		timerMelodia = new Timer();
		melodiaTask = new MelodiaTask(context);
		context.activarMelodia();
		timerMelodia.schedule(melodiaTask, Alarmas.INTERVALO_SONAR);
	}
	
	public void exitAction (Alarmas context) {
		context.desactivarMelodia();
		String idAlarmaSonada = context.alarmaMasProxima().getId();
		context.eliminaAlarma(idAlarmaSonada);
		timerMelodia.cancel();
	}
	
	public class MelodiaTask extends TimerTask {
		
		private Alarmas context;
		
		public MelodiaTask (Alarmas a) {
			context = a;
		}
		
		// Tarea que se ejecuta cuando se alcanza el tiempo indicado en INTERVALO_SONAR
		public void run() {
			getEstadoSonando().exitAction(context);
			getEstadoProgramado().entryAction(context);
			getEstadoProgramado().doAction(context);
			context.setState(getEstadoProgramado());
		}
	}
}
