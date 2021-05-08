package es.unican.is2.practica5;

import java.time.LocalDate;

public class Debito extends Tarjeta {	// TOTAL: WMC = 8, CCog = 2
	
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, CuentaAhorro c) {	// CC +1
		super(numero, titular, c);
	}
	
	
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {	// CC +1
		if (saldoDiarioDisponible<x) {	// CC +1, CCog +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Retirada en cajero automático", x);
		saldoDiarioDisponible-=x;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) 
			throws saldoInsuficienteException, datoErroneoException {	// CC +1
		if (saldoDiarioDisponible<x) {	// CC +1, CCog +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	
	public LocalDate getCaducidadDebito() {		// CC +1
		return this.mCuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * Método invocado automáticamente a las 00:00 de cada día
	 */
	public void restableceSaldo() {		// CC +1
		saldoDiarioDisponible = mCuentaAsociada.getLimiteDebito();
	}
	
	public CuentaAhorro getCuentaAsociada() {	// CC +1
		return mCuentaAsociada;
	}

}