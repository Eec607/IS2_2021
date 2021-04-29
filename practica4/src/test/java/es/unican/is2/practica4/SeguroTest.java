package es.unican.is2.practica4;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class SeguroTest {
	private Seguro seguro;
	private Cliente clienteConMinusvalia, clienteSinMinusvalia;
	
	@Before
	public void setUp() throws Exception {
		clienteConMinusvalia = new Cliente("YasuoMain", "002005F", true);
		clienteSinMinusvalia = new Cliente("loltyler1", "7523458A", false);
	}
	
	@Test
	public void testConstructorSeguro() {
		// Casos de prueba v�lidos
		// 1) (1, Cliente, Terceros)
		try {
			seguro = new Seguro(1, clienteConMinusvalia, Cobertura.TERCEROS);
		} catch (DatoIncorrectoException e) {
			fail("No deber�a lanzar la excepci�n");
		}
		// 2) (10, Cliente, Terceros+Lunas)
		try {
			seguro = new Seguro(10, clienteConMinusvalia, Cobertura.TERCEROS_LUNAS);
		} catch (DatoIncorrectoException e) {
			fail("No deber�a lanzar la excepci�n");
		}
		// 3) (1, Cliente, Todo Riesgo)
		try {
			seguro = new Seguro(1, clienteConMinusvalia, Cobertura.TODO_RIESGO);
		} catch (DatoIncorrectoException e) {
			fail("No deber�a lanzar la excepci�n");
		}
		
		// Casos de prueba no v�lidos
		// 1) (0, Cliente, Terceros)
		try {
			seguro = new Seguro(0, clienteConMinusvalia, Cobertura.TERCEROS);
			fail("No se ha lanzado la excepci�n");
		} catch (DatoIncorrectoException e) {
			// Debe lanzarse la excepci�n
		}
		// 2) (-5, Cliente, Terceros)
		try {
			seguro = new Seguro(-5, clienteConMinusvalia, Cobertura.TERCEROS);
			fail("No se ha lanzado la excepci�n");
		} catch (DatoIncorrectoException e) {
			// Debe lanzarse la excepci�n
		}
		// 3) (1, NULL, Terceros)
		try {
			seguro = new Seguro(1, null, Cobertura.TERCEROS);
			fail("No se ha lanzado la excepci�n");
		} catch (DatoIncorrectoException e) {
			// Debe lanzarse la excepci�n
		}
		// 4) (1, Cliente, NULL)
		try {
			seguro = new Seguro(1, clienteConMinusvalia, null);
			fail("No se ha lanzado la excepci�n");
		} catch (DatoIncorrectoException e) {
			// Debe lanzarse la excepci�n
		}
	}
	
	@Test
	public void testPrecio() {
		// Casos de prueba v�lidos
		// 1) (Todo Riesgo, 85, 0, TRUE): 900
		try {
			seguro = new Seguro(85, clienteConMinusvalia, Cobertura.TODO_RIESGO);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(1));
			assertTrue(seguro.precio()==900.0);
		} catch(DatoIncorrectoException e) {
			fail("No deber�a lanzar la excepci�n");
		}
		// 2) (Terceros+Lunas, 90, 1, TRUE): 510
		try {
			seguro = new Seguro(90, clienteConMinusvalia, Cobertura.TERCEROS_LUNAS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(1));
			assertTrue(seguro.precio()==510.0);
		} catch(DatoIncorrectoException e) {
			fail("No deber�a lanzar la excepci�n");
		}
		// 3) (Terceros, 120, 4, FALSE): 480
		try {
			seguro = new Seguro(120, clienteSinMinusvalia, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(4));
			assertTrue(seguro.precio()==480.0);
		} catch(DatoIncorrectoException e) {
			fail("No deber�a lanzar la excepci�n");
		}
		
		// Casos de prueba no v�lidos
		// 1) (Todo Riesgo, 85, 0, TRUE), fechaUltimoSiniestro == NULL
		try {
			seguro = new Seguro(85, clienteConMinusvalia, Cobertura.TODO_RIESGO);
			assertTrue(seguro.precio()==900.0);
			fail("No se ha lanzado la excepcion");
		} catch (DatoIncorrectoException e) {
			// Debe lanzarse la excepci�n
		}
		// 2) (Todo Riesgo, 85, -1, TRUE)
		try {
			seguro = new Seguro(85, clienteConMinusvalia, Cobertura.TODO_RIESGO);
			seguro.setFechaUltimoSiniestro(LocalDate.now().plusYears(1));
			assertTrue(seguro.precio()==900.0);
			fail("No se ha lanzado la excepcion");
		} catch (DatoIncorrectoException e) {
			// Debe lanzarse la excepci�n
		}
	}
}