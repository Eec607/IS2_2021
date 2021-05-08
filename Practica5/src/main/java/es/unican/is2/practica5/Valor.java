package es.unican.is2.practica5;

public class Valor {	// TOTAL: WMC = 6
	
	private String entidad;	
	private int numValores;
	private double cotizacionActual;
	
	public Valor(String entidad, int numValores, double cotizacionActual) {	// CC +1
		super();
		this.entidad = entidad;
		this.numValores = numValores;
		this.cotizacionActual = cotizacionActual;
	}
	
	public int getNumValores() {	// CC +1
		return numValores;
	}

	public void setNumValores(int numValores) {	// CC +1
		this.numValores = numValores;
	}

	public double getCotizacionActual() {		// CC +1
		return cotizacionActual;
	}

	public void setCotizacionActual(double cotizacionActual) {	// CC +1
		this.cotizacionActual = cotizacionActual;
	}

	public String getEntidad() {	// CC +1
		return entidad;
	}


}