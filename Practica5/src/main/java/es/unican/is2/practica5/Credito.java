package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class Credito extends Tarjeta {	// TOTAL: WMC = 17, CCog = 9
	
	private double mCredito;
	private List<Movimiento> mMovimientosMensuales;
	private List<Movimiento> mhistoricoMovimientos;
	
	
	public Credito(String numero, String titular, CuentaAhorro c, double credito) {	// CC +1
		super(numero, titular, c);
		mCredito = credito;
		mMovimientosMensuales = new LinkedList<Movimiento>();
		mhistoricoMovimientos = new LinkedList<Movimiento>();
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comisión del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {	// CC +1
		if (x<0)	// CC +1, CCog +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada en cajero automático");
		x += x * 0.05; // Añadimos una comisión de un 5%
		m.setI(-x);
		
		if (getGastosAcumulados()+x > mCredito) // CC +1, CCog +1
			throw new saldoInsuficienteException("Crédito insuficiente");
		else {	// CC +1, CCog +1
			mMovimientosMensuales.add(m);
		}
	}	// TOTAL: CC = 3, CCog = 3

	@Override
	public void pagoEnEstablecimiento(String datos, double x) 
			throws saldoInsuficienteException, datoErroneoException {	// CC +1
		if (x<0)	// CC +1, CCog +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + x > mCredito)	// CC +1, CCog +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Compra a crédito en: " + datos);
		m.setI(-x);
		mMovimientosMensuales.add(m);
	}	// TOTAL: CC = 3, CCog = 2
	
    public double getGastosAcumulados() {	// CC +1
		double r = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) {	// CC +1, CCog +1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			r += m.getI();
		}
		return -r;
	}	// TOTAL: CC = 3, CCog = 2
	
	
	public LocalDate getCaducidadCredito() {	// CC +1
		return this.mCuentaAsociada.getCaducidadCredito();
	}

	/**
	 * Método que se invoca automáticamente el día 1 de cada mes
	 */
	public void liquidar() {	// CC +1
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setF(now);
		liq.setC("Liquidación de operaciones tarjeta crédito");
		double r = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) {	// CC +1, CCog +1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			r += m.getI();
		}
		liq.setI(r);
	
		if (r != 0)	// CC +1, CCog +1
			mCuentaAsociada.addMovimiento(liq);
		
		mhistoricoMovimientos.addAll(mMovimientosMensuales);
		mMovimientosMensuales.clear();
	}	// TOTAL: CC = 3, CCog = 2

	public List<Movimiento> getMovimientosUltimoMes() {	// CC +1
		return mMovimientosMensuales;
	}
	
	public Cuenta getCuentaAsociada() {	// CC +1
		return mCuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() {	// CC +1
		return mhistoricoMovimientos;
	}

}