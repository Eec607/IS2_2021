package es.unican.is2.practica3;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Alarmas {
	
	protected static final int INTERVALO_SONAR = 10000;		// Intervalo en ms que debe "sonar" una alarma
	private AlarmasState state;
	private Map<String, Alarma> alarmasSistema = new HashMap<String, Alarma>();
	private PriorityQueue<Alarma> alarmasActivadas = new PriorityQueue<Alarma>();
	private Map<String, Alarma> alarmasDesactivadas = new HashMap<String, Alarma>();
	private boolean sonando = false;		// Variable que indica si una alarma est� sonando o no
	
	
	public Alarmas() {
		state = AlarmasState.init(this);
	}
	
	/**
	 * Devuelve una alarma en funci�n del id indicado
	 * @param id id de la alarma deseada
	 * @return la alarma correspondiente al id o null en caso de que no se den coincidencias
	 */
	public Alarma alarma(String id) {
		return alarmasSistema.get(id);
	}
	
	public int intervaloSonar() {
		return INTERVALO_SONAR;
	}
	
	public boolean sonando() {
		return sonando;
	}

	/**
	 * Anhade una alarma al sistema y a la cola de activadas
	 * @param id id de la alarma a anhadir
	 * @param hora hora de la alarma a anhadir
	 * @return true si la alarma se ha anhadido correctamente y false en caso contrario (porque ya existe)
	 */
	public boolean anhadeAlarma(String id, Date hora) {
		if (alarmasSistema.containsKey(id)) {
			return false;
		}
		
		Alarma nuevaAlarma = new Alarma(id, actualizaDate(hora));
		alarmasSistema.put(id, nuevaAlarma);
		alarmasActivadas.add(nuevaAlarma);
		return true;
	}
	
	/**
	 * Elimina una alarma al sistema
	 * @param id id de la alarma a eliminar
	 * @return true si la alarma se ha eliminado correctamente y false en caso contrario (porque no existe)
	 */
	public boolean eliminaAlarma(String id) {
		if (!alarmasSistema.containsKey(id)) {
			return false;
		}
		
		// Elimina la alarma del mapa de alarmas del sistema
		Alarma alarmaEliminada = alarmasSistema.remove(id);
		
		// Elimina la alarma bien del mapa de alarmas desactivadas o bien de la cola de activadas
		if (alarmasDesactivadas.containsKey(id)) {
			alarmasDesactivadas.remove(id);
			return true;
		}
		alarmasActivadas.remove(alarmaEliminada);
		return true;
	}
	
	/**
	 * Devuelve la alarma situada en la cabeza de la cola (sin eliminarla de esta)
	 * @return la alarma situada en la cabeza de la cola
	 */
	public Alarma alarmaMasProxima() {
		return alarmasActivadas.peek();
	}
	
	/**
	 * Desactiva la alarma indicada: la mueve de la cola de activadas al mapa de desactivadas
	 * @param a alarma a desactivar
	 */
	public void desactivaAlarma(Alarma a) {
		alarmasActivadas.remove(a);
		alarmasDesactivadas.put(a.getId(), a);
	}
	
	/**
	 * Activa la alarma indicada: la mueve de la cola de activadas al mapa de desactivadas
	 * @param alarma a activar
	 */
	public void activaAlarma(Alarma alarma) {
		alarmasDesactivadas.remove(alarma.getId());
		alarmasActivadas.add(alarma);
	}
	
	/**
	 * Simula activaci�n de la melod�a de una alarma
	 */
	public void activarMelodia() {
		sonando = true;
		System.out.println("M�todo activarMelodia() llamado");
	}
	
	/**
	 * Simula desctivaci�n de la melod�a de una alarma
	 */
	public void desactivarMelodia() {
		sonando = false;
		System.out.println("M�todo desactivarMelodia() llamado");
	}
	
	/**
	 * Cambia el estado del objeto al indicado
	 * @param value estado al que cambiar
	 */
	public void setState(AlarmasState value) {
		this.state = value;
	}
	
	/**
	 * M�todo que representa la se�al alarmaOn
	 * @param id id de la alarma a encender
	 */
	public void alarmaOn(String id) {
		state.alarmaOn(this, id);
	}
	
	/**
	 * M�todo que representa la se�al alarmaOff
	 * @param id id de la alarma a apagar
	 */
	public void alarmaOff(String id) {
		state.alarmaOff(this, id);
	}
	
	/**
	 * M�todo que representa la se�al nuevaAlarma
	 * @param id id de la alarma a anhadir
	 * @param hora hora de la alarma a anhadir
	 */
	public void nuevaAlarma(String id, Date hora) {
		state.nuevaAlarma(this, id, hora);
	}
	
	/**
	 * M�todo que representa la se�al borraAlarma
	 * @param id id de la alarma a borrar
	 */
	public void borraAlarma(String id) {
		state.borraAlarma(this, id);
	}
	
	/**
	 * M�todo que representa la se�al apagar
	 */
	public void apagar() {
		state.apagar(this);
	}
	
	/**
	 * Actualiza la fecha de un objeto del tipo Date pasado como par�metro
	 * (necesario debido a que las fechas obtenidas a partir de un JSpinner corresponden a 1970)
	 * @param hora objeto Date del cual queremos actualizar la fecha
	 * @return el objeto Date actualizado
	 */
	@SuppressWarnings("deprecation")
	private Date actualizaDate(Date hora) {
		Date dateActualizada = Calendar.getInstance().getTime();
		dateActualizada.setHours(hora.getHours());
		dateActualizada.setMinutes(hora.getMinutes());
		dateActualizada.setSeconds(0);
		return dateActualizada;
	}
	
	/**
	 * M�todo toString() del mapa de alarmas del sistema
	 */
	public String toString() {
		String string = "";
		for (Map.Entry<String, Alarma> entry : alarmasSistema.entrySet()) {
	        string += entry.getValue() + "\n";
	    }
		return string;
	}
}
