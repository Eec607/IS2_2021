package es.unican.is2.practica4;

public enum Cobertura {
	TERCEROS("TERCEROS", 400.0),
	TODO_RIESGO("TODO_RIESGO", 1000.0),
	TERCEROS_LUNAS("TERCEROS_LUNAS", 600.0);
	
	private final String valor;
	private final double precioBase;
	
	Cobertura(String valor, double precioBase) {
        this.valor = valor;
        this.precioBase = precioBase;
    }

    public String getValor() {
        return valor;
    }

    public double getPrecioBase() {
        return precioBase;
    }
}
