package es.unican.is2.practica3;

import java.util.Date;

public abstract class AlarmasState {
	
	private static Desprogramado estadoDesprogramado = new Desprogramado();
	private static Programado estadoProgramado = new Programado();
	private static Sonando estadoSonando = new Sonando();
	
	public static AlarmasState init(Alarmas context) {
		estadoDesprogramado.entryAction(context);
		return estadoDesprogramado;
	}
	
	public void alarmaOn(Alarmas context, String id) {};
	public void alarmaOff(Alarmas context, String id) {};
	public void nuevaAlarma(Alarmas context, String id, Date hora) {};
	public void borraAlarma(Alarmas context, String id) {};
	public void apagar(Alarmas context) {};
	
	public static AlarmasState getEstadoDesprogramado() {
		return estadoDesprogramado;
	};
	public static AlarmasState getEstadoProgramado() {
		return estadoProgramado;
	};
	public static AlarmasState getEstadoSonando() {
		return estadoSonando;
	};

}
