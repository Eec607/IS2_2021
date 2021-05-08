package es.unican.is2.practica5;

public abstract class Cuenta {	// TOTAL: WMC = 2
	
	private String numCuenta;
	
	public Cuenta(String numCuenta) {	// CC +1
		this.numCuenta = numCuenta;
	}
	
	public String getNumCuenta() {	// CC +1
		return numCuenta;
	}
	
	
}
