package es.unican.is2.practica5;

import java.util.LinkedList;
import java.util.List;

public class Cliente {	// TOTAL: WMC = 8, CCog = 10
	
	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();

 	public Cliente(String titular, String calle, String zip, String localidad, 
 			String telefono, String dni) {  // CC +1
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}	// TOTAL: CC = 1
	
	public void cambiaDireccion(String calle, String zip, String localidad) {	// CC +1
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	} 	// TOTAL: CC = 1
	
	public double getSaldoTotal() {	// CC +1
		double total = 0.0;
		for (Cuenta c: Cuentas) {  // CC +1, CCog +1
			if (c instanceof CuentaAhorro) {	// CC +1, CCog +2
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  {	// CC +1, CCog +3
				for (Valor v: ((CuentaValores) c).getValores()) {	// CC +1, CCog +4
					total += v.getCotizacionActual()*v.getNumValores();
				}
			}
		}
		return total;
	}	// TOTAL: CC = 5, CCog = 10
	
	public void anhadeCuenta(Cuenta c) {	// CC +1
		Cuentas.add(c);
	}	// TOTAL: CC = 1
	
}