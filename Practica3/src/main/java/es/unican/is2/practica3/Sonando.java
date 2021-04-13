package es.unican.is2.practica3;

import java.util.Timer;
import java.util.TimerTask;

public class Sonando extends AlarmasState {
	
	private static Timer timerMelodia;		// Timer para planificar tareas temporizadas MelodiaTask
	private MelodiaTask melodiaTask;
	
	public void apagar (Alarmas context) {
		// Al apagar la alarma que se encuentra sonando, se sale del estado Sonando
		this.exitAction(context);
		
		context.setState(getEstadoProgramado());
		getEstadoProgramado().entryAction(context);
		getEstadoProgramado().doAction(context);
		
	}
	
	public void entryAction (Alarmas context) {
		// Al entrar al estado Sonando, se inicializan tanto timer como tarea temporizada
		timerMelodia = new Timer();
		melodiaTask = new MelodiaTask(context);
		
		// Inicia la simulacion de la activacion de la melodia durante el intervalo (en ms) indicado
		context.activarMelodia();
		timerMelodia.schedule(melodiaTask, Alarmas.INTERVALO_SONAR);
	}
	
	public void exitAction (Alarmas context) {
		// Antes de salir del estado Sonando, debe desactivarse tanto la simulacion de la melodia 
		// de la alarma como el timer con el que se planifico la melodiaTask correspondiente
		context.desactivarMelodia();
		timerMelodia.cancel();
		
		// Se elimina la alarma que ha terminado de sonar del sistema
		String idAlarmaSonada = context.alarmaMasProxima().getId();
		context.eliminaAlarma(idAlarmaSonada);
	}
	
	/**
	 * Tarea temporizada que simula el intervalo durante el que una alarma suena y que, 
	 * al terminar, realiza la transicion hasta el estado Programado
	 *
	 */
	public class MelodiaTask extends TimerTask {
		
		private Alarmas context;
		
		public MelodiaTask (Alarmas a) {
			context = a;
		}
		
		// Tarea que se ejecuta cuando se cumple el delay indicado en INTERVALO_SONAR
		public void run() {
			getEstadoSonando().exitAction(context);
			getEstadoProgramado().entryAction(context, "Sonando");
			getEstadoProgramado().doAction(context);
			context.setState(getEstadoProgramado());
		}
	}
}
