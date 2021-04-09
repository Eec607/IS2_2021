package es.unican.is2.practica3;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Programado extends AlarmasState {
	
	private static Timer timerProgramadoSonando;
	private static ProgramadoSonandoTask programadoSonandoTask;
	
	public void borraAlarma (Alarmas context, String id) {
		context.eliminaAlarma(id);
	}
	
	public void nuevaAlarma (Alarmas context, String id, Date hora) {
		context.anhadeAlarma(id, hora);
		if (id.equals(context.alarmaMasProxima().getId())) {
			timerProgramadoSonando.schedule(programadoSonandoTask, context.alarmaMasProxima().getHora());
		}
	}
	
	public void alarmaOn (Alarmas context, String id) {
		Alarma alarma = context.alarma(id);
		context.activaAlarma(alarma);
		if (id.equals(context.alarmaMasProxima().getId())) {
			programadoSonandoTask = new ProgramadoSonandoTask(context);
			timerProgramadoSonando.schedule(programadoSonandoTask, context.alarmaMasProxima().getHora());
		}
	}
	
	public void alarmaOff (Alarmas context, String id) {
		boolean reschedule = id.equals(context.alarmaMasProxima().getId());
		Alarma alarma = context.alarma(id);
		context.desactivaAlarma(alarma);
		if (reschedule) {
			programadoSonandoTask.cancel();
			programadoSonandoTask = new ProgramadoSonandoTask(context);
			timerProgramadoSonando.schedule(programadoSonandoTask, context.alarmaMasProxima().getHora());
		}
	}
	
	public void entryAction (Alarmas context) {
		timerProgramadoSonando = new Timer();
		programadoSonandoTask = new ProgramadoSonandoTask(context);
		if (context.alarmaMasProxima() != null) {
			timerProgramadoSonando.schedule(programadoSonandoTask, context.alarmaMasProxima().getHora());
		}
	}
	
	public class ProgramadoSonandoTask extends TimerTask {
		
		private Alarmas context;
		
		public ProgramadoSonandoTask (Alarmas a) {
			context = a;
		}
		
		// Tarea que se ejecuta cuando se alcanza el tiempo en que la alarma más próxima debe sonar
		public void run() {
			getEstadoProgramado().exitAction(context);
			getEstadoSonando().entryAction(context);
			getEstadoSonando().doAction(context);
			context.setState(getEstadoSonando());
		}

	}
	
}
