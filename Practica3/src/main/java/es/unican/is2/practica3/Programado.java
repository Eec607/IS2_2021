package es.unican.is2.practica3;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Programado extends AlarmasState {
	
	private static Timer timerProgramadoSonando = new Timer();
	private ProgramadoSonandoTask programadoSonandoTask;
	
	public void borraAlarma (Alarmas context, String id) {
		context.eliminaAlarma(id);
	}
	
	public void nuevaAlarma (Alarmas context, String id, Date hora) {
		context.anhadeAlarma(id, hora);
	}
	
	public void alarmaOn (Alarmas context, String id) {
		Alarma alarma = context.alarma(id);
		context.activaAlarma(alarma);
		System.out.println(alarma.getHora());
		programadoSonandoTask = new ProgramadoSonandoTask(context);
		timerProgramadoSonando.schedule(programadoSonandoTask, alarma.getHora());
	}
	
	public void alarmaOff (Alarmas context, String id) {
		Alarma alarma = context.alarma(id);
		context.desactivaAlarma(alarma);
		programadoSonandoTask.cancel();
	}
	
	public class ProgramadoSonandoTask extends TimerTask {
		
		private Alarmas context;
		
		public ProgramadoSonandoTask (Alarmas a) {
			context = a;
		}
		
		// Tarea que se ejecuta cuando se alcanza el tiempo
		public void run() {
			getEstadoProgramado().exitAction(context);
			getEstadoSonando().entryAction(context);
			getEstadoSonando().doAction(context);
			context.setState(getEstadoSonando());
		}

	}
	
}
