package es.unican.is2.practica3;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Programado extends AlarmasState {
	
	private static Timer timerProgramadoSonando;  // Timer para planificar tareas temporizadas ProgramadoSonandoTask
	private static ProgramadoSonandoTask programadoSonandoTask;
	
	public void borraAlarma (Alarmas context, String id) {
		context.eliminaAlarma(id);
	}
	
	public void nuevaAlarma (Alarmas context, String id, Date hora) {
		context.anhadeAlarma(id, hora);
		
		// Si la alarma anhadida está situada a la cabeza de la cola de prioridad (es la más próxima)
		// se reprograma el timer para que suene a la hora correspondiente a dicha alarma
		if (id.equals(context.alarmaMasProxima().getId())) {
			programadoSonandoTask.cancel();
			programadoSonandoTask = new ProgramadoSonandoTask(context);
			timerProgramadoSonando.schedule(programadoSonandoTask, context.alarmaMasProxima().getHora());
		}
	}
	
	public void alarmaOn (Alarmas context, String id) {
		// Obtiene la alarma a partir del mapa de alarmas del sistema y la activa
		Alarma alarma = context.alarma(id);
		context.activaAlarma(alarma);
		
		// Si la alarma activada esta situada a la cabeza de la cola de prioridad (es la más proxima)
		// se reprograma el timer para que suene a la hora correspondiente a dicha alarma
		if (id.equals(context.alarmaMasProxima().getId())) {
			programadoSonandoTask.cancel();
			programadoSonandoTask = new ProgramadoSonandoTask(context);
			timerProgramadoSonando.schedule(programadoSonandoTask, context.alarmaMasProxima().getHora());
		}
	}
	
	public void alarmaOff (Alarmas context, String id) {
		// Comprueba, mediante comparacion de id, si la alarma a desactivar se corresponde con la
		// alarma situada a la cabeza de la cola de prioridad (la mas proxima)
		boolean reschedule = id.equals(context.alarmaMasProxima().getId());
		
		// Obtiene la alarma a partir del mapa de alarmas del sistema y la desactiva
		Alarma alarma = context.alarma(id);
		context.desactivaAlarma(alarma);
		System.out.println("Cola: " + context.alarmasActivadas().size());
		
		// Si la alarma desactivada era la mas proxima, se cancela la tarea temporizada y se 
		// reprograma el timer para que suene a la hora correspondiente a la nueva alarma mas proxima
		if (reschedule && context.alarmasActivadas().size() > 0) {
			System.out.println("3");
			programadoSonandoTask.cancel();
			programadoSonandoTask = new ProgramadoSonandoTask(context);
			timerProgramadoSonando.schedule(programadoSonandoTask, context.alarmaMasProxima().getHora());
		
		} else if (context.alarmasActivadas().size() == 0) {
			System.out.println("5");
			context.setState(getEstadoDesprogramado());
			getEstadoProgramado().exitAction(context);
			getEstadoDesprogramado().entryAction(context);
			getEstadoDesprogramado().doAction(context);
		}
	}
	
	public void entryAction (Alarmas context, String estadoAnterior) {
		// Al entrar al estado Programado, se inicializan tanto timer como tarea temporizada
		timerProgramadoSonando = new Timer();
		programadoSonandoTask = new ProgramadoSonandoTask(context);
		System.out.println("1");
		
		// Si la cola de alarmas activadas no se encuentra vacia en el momento en el que se entra en este
		// estado, se programa el timer para que suene a la hora correspondiente a la alarma mas proxima
		if (context.alarmasActivadas().size() > 0) {
			System.out.println("2");
			timerProgramadoSonando.schedule(programadoSonandoTask, context.alarmaMasProxima().getHora());
		
		} else if (estadoAnterior != "Desprogramado") {
			System.out.println("4");
			context.setState(getEstadoDesprogramado());
			getEstadoProgramado().exitAction(context);
			getEstadoDesprogramado().entryAction(context);
			getEstadoDesprogramado().doAction(context);
		}
	}
	
	/**
	 * Tarea temporizada que realiza la transicion desde el estado Programado al estado Sonando cuando la
	 * hora actual se corresponde con la de la alarma mas proxima de la cola de activadas
	 */
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
