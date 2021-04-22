package es.unican.is2.practica4;

import java.time.LocalDate;

public class Seguro {
	
	private LocalDate fechaUltimoSiniestro;
	private int potenciaCV;
	private Cliente tomadorSeguro;
	private Cobertura coberturaSeguro;
	
	public Seguro(int potencia, Cliente cliente, Cobertura cobertura) 
			throws DatoIncorrectoException {
		if (potencia <= 0 || cliente == null || cobertura == null) {
			throw new DatoIncorrectoException();
		}
		potenciaCV = potencia;
		tomadorSeguro = cliente;
		coberturaSeguro = cobertura;
	}
	
	public void setFechaUltimoSiniestro(LocalDate fecha) {
		fechaUltimoSiniestro = fecha;
	}
	
	public double precio() throws DatoIncorrectoException{
		if (fechaUltimoSiniestro == null) {
			throw new DatoIncorrectoException();
		}
		
		double precioTotal = coberturaSeguro.getPrecioBase();
		LocalDate diferenciaFechas = LocalDate.now().minusYears(fechaUltimoSiniestro.getYear());
		if (diferenciaFechas.getYear() < 0) {
			throw new DatoIncorrectoException();
		}
		
		// Aplica el impuesto adicional por la potencia del coche
		if (potenciaCV >= 90) {
			if (potenciaCV > 110) {
				precioTotal += precioTotal * 0.2;
			} else {
				precioTotal += precioTotal * 0.05;
			}
		}
		
		// Comprueba si ha de aplicarse descuento por siniestro reciente
		if (diferenciaFechas.getYear() < 1) {
			precioTotal += 200.0;
		} else if (diferenciaFechas.getYear() < 3) {
			precioTotal += 50.0;
		}
		
		// Comprueba si ha de aplicarse descuento por minusvalia
		if (tomadorSeguro.minusvalia()) {
			precioTotal -= precioTotal * 0.25;
		}
		
		return precioTotal;
	}
	
}
