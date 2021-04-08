package es.unican.is2.practica3;

import java.util.Timer;
import java.util.TimerTask;

public class Sonando extends AlarmasState /*implements TimedState*/ {
	
	private static Timer timerMelodia;
	private MelodiaTask melodiaTask;
	
	public Timer getTimerMelodia() {
		return timerMelodia;
	}
	
	public void apagar (Alarmas context) {
		this.exitAction(context);
		context.setState(getEstadoProgramado());
		getEstadoProgramado().entryAction(context);
		getEstadoProgramado().doAction(context);
		
	}
	
	public void entryAction (Alarmas context) {
		//context.controlador().startRelative(context, this, context.intervaloSonar());
		context.activarMelodia();
		timerMelodia = new Timer();
		melodiaTask = new MelodiaTask(context);
		timerMelodia.schedule(melodiaTask, 10000);;
	}
	
	public void exitAction (Alarmas context) {
		context.desactivarMelodia();
		String idAlarmaSonada = context.alarmaMasProxima().getId();
		context.eliminaAlarma(idAlarmaSonada);
	}
	
	public class MelodiaTask extends TimerTask {
		
		private Alarmas context;
		
		public MelodiaTask (Alarmas a) {
			context = a;
		}
		
		// Tarea que se ejecuta cuando se alcanza el tiempo
		public void run() {
			getEstadoSonando().exitAction(context);
			getEstadoProgramado().entryAction(context);
			getEstadoProgramado().doAction(context);
			context.setState(getEstadoProgramado());
		}
	}

	/**public void timeout(Alarmas context) {
		this.exitAction(context);
		getEstadoProgramado().entryAction(context);
		getEstadoProgramado().doAction(context);
		context.setState(getEstadoProgramado());
	}*/
}
