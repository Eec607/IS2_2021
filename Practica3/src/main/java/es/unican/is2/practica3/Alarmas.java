package es.unican.is2.practica3;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class Alarmas {
	
	private static final int INTERVALO_SONAR = 10;
	private AlarmasState state;
	private Map<String, Alarma> alarmasSistema = new HashMap<String, Alarma>();
	private PriorityQueue<Alarma> alarmasActivadas = new PriorityQueue<Alarma>();
	private Map<String, Alarma> alarmasDesactivadas = new HashMap<String, Alarma>();
	private TimedStateController controlador = new TimedStateController();
	private boolean sonando = false;
	
	
	public Alarmas() {
		state = AlarmasState.init(this);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Alarma alarma(String id) {
		return alarmasSistema.get(id);
	}
	
	public int intervaloSonar() {
		return INTERVALO_SONAR;
	}
	
	public TimedStateController controlador() {
		return controlador;
	}
	
	public boolean sonando() {
		return sonando;
	}

	/**
	 * 
	 * @param id
	 * @param hora
	 * @return
	 */
	public boolean anhadeAlarma(String id, Date hora) {
		if (alarmasSistema.containsKey(id)) {
			return false;
		}
		
		Alarma nuevaAlarma = new Alarma(id, hora);
		alarmasSistema.put(id, nuevaAlarma);
		alarmasDesactivadas.put(id, nuevaAlarma);
		return true;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean eliminaAlarma(String id) {
		if (!alarmasSistema.containsKey(id)) {
			return false;
		}
		
		Alarma alarmaEliminada = alarmasSistema.remove(id);
		if (!alarmasDesactivadas.containsKey(id)) {
			alarmasDesactivadas.remove(id);
			return true;
		}
		
		alarmasActivadas.remove(alarmaEliminada);
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public Alarma alarmaMasProxima() {
		return alarmasActivadas.peek();
	}
	
	/**
	 * 
	 * @param a
	 */
	public void desactivaAlarma(Alarma a) {
		alarmasActivadas.remove(a);
		alarmasDesactivadas.put(a.getId(), a);
	}
	
	/**
	 * 
	 * @param alarma
	 */
	public void activaAlarma(Alarma alarma) {
		alarmasDesactivadas.remove(alarma.getId());
		alarmasActivadas.add(alarma);
	}
	
	/**
	 * 
	 * @return
	 */
	public Alarma[] alarmasActivadas() {
		return (Alarma[]) alarmasActivadas.toArray();
	}
	
	/**
	 * 
	 * @return
	 */
	public Alarma[] alarmasDesactivadas() {
		return alarmasDesactivadas.values().toArray(new Alarma[0]);
	}
	
	/**
	 * 
	 */
	public void activarMelodia() {
		sonando = true;
		System.out.println("Método activarMelodia() llamado");
	}
	
	/**
	 * 
	 */
	public void desactivarMelodia() {
		sonando = false;
		System.out.println("Método desactivarMelodia() llamado");
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setState(AlarmasState value) {
		this.state = value;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void alarmaOn(String id) {
		state.alarmaOn(this, id);
	}
	
	/**
	 * 
	 * @param id
	 */
	public void alarmaOff(String id) {
		state.alarmaOff(this, id);
	}
	
	/**
	 * 
	 * @param id
	 * @param hora
	 */
	public void nuevaAlarma(String id, Date hora) {
		state.nuevaAlarma(this, id, hora);
	}
	
	/**
	 * 
	 * @param id
	 */
	public void borraAlarma(String id) {
		state.borraAlarma(this, id);
	}
	
	/**
	 * 
	 */
	public void apagar() {
		state.apagar(this);
	}
	
	public String[] idsActivadas() {
		String[] ids = {};
		int i = 0;
		Iterator<Alarma> iter = alarmasActivadas.iterator();
		while (iter.hasNext()) {
			ids[i] = iter.next().getId();
			++i;
	    }
		return ids;
	}
	
	public String[] idsDesactivadas() {
		String[] ids = {};
		int i = 0;
		for (Map.Entry<String, Alarma> entry : alarmasDesactivadas.entrySet()) {
			ids[i] = entry.getValue().getId();
			++i;
	    }
		return ids;
	}
	
	public String toString() {
		String string = "";
		for (Map.Entry<String, Alarma> entry : alarmasSistema.entrySet()) {
	        string += entry.getValue() + "\n";
	    }
		return string;
	}
}
