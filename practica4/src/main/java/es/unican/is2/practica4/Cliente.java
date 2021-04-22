package es.unican.is2.practica4;

public class Cliente {
	private String nombre;
	private String dni;
	private boolean minusvalia;
	
	public Cliente(String nombre, String dni, boolean minusvalia) {
		this.nombre = nombre;
		this.dni = dni;
		this.minusvalia = minusvalia;
	}
	
	public String nombre() {
		return nombre;
	}
	
	public String dni() {
		return dni;
	}
	
	public boolean minusvalia() {
		return minusvalia;
	}	
}
