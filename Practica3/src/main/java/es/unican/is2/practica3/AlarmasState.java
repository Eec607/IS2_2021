package es.unican.is2.practica3;

public abstract class AlarmasState {
	
	private static Desprogramado estadoDesprogramado = new Desprogramado();
	private static Programado estadoProgramado = new Programado();
	private static Sonando estadoSonando = new Sonando();
	
	public static AlarmasState init()

}
