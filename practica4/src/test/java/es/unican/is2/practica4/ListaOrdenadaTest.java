package es.unican.is2.practica4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

//import es.unican.is2.containers.ListaOrdenada;

public class ListaOrdenadaTest {
	private ListaOrdenada<Integer> listaOrdenada;

	@Before
	public void setUp() throws Exception {
		listaOrdenada = new ListaOrdenada<Integer>();
	}

	@Test
	public void testAdd() {
		// Caso valido: Se anhade un elemento valido
		int initialSize = listaOrdenada.size();
		listaOrdenada.add(1);
		assertTrue(listaOrdenada.size() == initialSize + 1);

		// Caso no valido: Se anhade un elemento nulo
		int initialSize2 = listaOrdenada.size();
		try {
			listaOrdenada.add(null);
			fail("No se ha lanzado la excepción");
		} catch (NullPointerException e) {
			// Debe lanzarse la excepción
			assertTrue(listaOrdenada.size() == initialSize2);
		}
	}

	@Test
	public void testSize() {
		// Como la lista esta vacia inicialmente, el tamanho debe ser 0
		assertTrue(listaOrdenada.size() == 0);
		listaOrdenada.add(10);
		assertTrue(listaOrdenada.size() == 1);	// Tras anhadir un nuevo elemento, el tamanho debe ser 1
	}

	@Test
	public void testGet() {
		// Se anhaden elementos a la lista para hacer las pruebas
		listaOrdenada.add(1);
		listaOrdenada.add(3);
		listaOrdenada.add(2);
		
		// Casos validos: Se usan indices comprendidos entre 0 y el numero de elementos menos 1
		assertTrue(listaOrdenada.get(0) == 1);
		assertTrue(listaOrdenada.get(1) == 2);	// La lista ordena sus valores, luego el 2 debe estar antes que el 3
		assertTrue(listaOrdenada.get(2) == 3);	// Pese a que 3 se anhadido antes que 2, la lista lo reordena detras

		// Casos no validos: Se utilizan indices no validos
		// 1) Indice mayor o igual al tamanho
		try {
			listaOrdenada.get(3);
			fail("No se ha lanzado la excepción");
		} catch (IndexOutOfBoundsException e) {
			// Debe lanzarse la excepción
		}
		// 2) Indice menor que 0
		try {
			listaOrdenada.get(-1);
			fail("No se ha lanzado la excepción");
		} catch (IndexOutOfBoundsException e) {
			// Debe lanzarse la excepción
		}
	}
	
	@Test
	public void testRemove() {		
		// Se anhaden elementos a la lista para probar los casos validos
		listaOrdenada.add(1);
		listaOrdenada.add(2);
		
		// Casos validos: Se usan indices comprendidos entre 0 y el numero de elementos menos 1
		assertTrue(listaOrdenada.remove(0) == 1);
		assertTrue(listaOrdenada.remove(0) == 2);
		
		// Se anhaden nuevamente elementos a la lista para probar los casos no validos
		listaOrdenada.add(1);
		listaOrdenada.add(2);

		// Casos no validos: Se utilizan indices no validos
		// 1) Indice mayor o igual al tamanho
		try {
			listaOrdenada.remove(2);
			fail("No se ha lanzado la excepción");
		} catch (IndexOutOfBoundsException e) {
			// Debe lanzarse la excepción
		}
		// 2) Indice menor que 0
		try {
			listaOrdenada.remove(-1);
			fail("No se ha lanzado la excepción");
		} catch (IndexOutOfBoundsException e) {
			// Debe lanzarse la excepción
		}
	}

	@Test
	public void testClear() {
		// Se ejecuta clear sobre una lista vacia
		listaOrdenada.clear();
		assertTrue(listaOrdenada.size() == 0);
		
		// Se ejecuta clear sobre una lista con elementos
		listaOrdenada.add(1);
		listaOrdenada.add(2);
		listaOrdenada.clear();
		assertTrue(listaOrdenada.size() == 0);
	}
}
