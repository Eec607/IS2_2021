package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {	// TOTAL: WMC = 18, CCog = 7

	private List<Movimiento> mMovimientos;
	private LocalDate mFechaDeCaducidadTarjetaDebito;
	private LocalDate mFechaDeCaducidadTarjetaCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta, LocalDate date, LocalDate date2) {	// CC +1
		super(numCuenta);
		this.mFechaDeCaducidadTarjetaDebito = date;
		this.mFechaDeCaducidadTarjetaCredito = date2;
		mMovimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}

	public void ingresar(double x) throws datoErroneoException {	// CC +1
		if (x <= 0)		// CC +1, CCog +1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Ingreso en efectivo");
		m.setI(x);
		this.mMovimientos.add(m);
	}

	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {	// CC +1
		if (x <= 0)				// CC +1, CCog +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		if (getSaldo() < x)		// CC +1, CCog +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada de efectivo");
		m.setI(-x);
		this.mMovimientos.add(m);
	}

	public void ingresar(String concepto, double x) throws datoErroneoException {	// CC +1
		if (x <= 0)		// CC +1, CCog +1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(x);
		this.mMovimientos.add(m);
	}

	public void retirar(String concepto, double x) 
			throws saldoInsuficienteException, datoErroneoException {	// CC +1
		if (getSaldo() < x)		// CC +1, CCog +1
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (x <= 0)				// CC +1, CCog +1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(-x);
		this.mMovimientos.add(m);
	}

	public double getSaldo() {	// CC +1
		double r = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) {	// CC +1, CCog +1
			Movimiento m = (Movimiento) mMovimientos.get(i);
			r += m.getI();
		}
		return r;
	}

	public void addMovimiento(Movimiento m) {	// CC +1
		mMovimientos.add(m);
	}

	public List<Movimiento> getMovimientos() {	// CC +1
		return mMovimientos;
	}

	public LocalDate getCaducidadDebito() {		// CC +1
		return this.mFechaDeCaducidadTarjetaDebito;
	}

	public LocalDate getCaducidadCredito() {	// CC +1
		return this.mFechaDeCaducidadTarjetaCredito;
	}

	public double getLimiteDebito() {			// CC +1
		return limiteDebito;
	}

}