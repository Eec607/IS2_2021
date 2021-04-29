package es.unican.is2.practica4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.fest.swing.fixture.FrameFixture;

public class SegurosGUITest {
	private FrameFixture demo;
	
	@Before
	public void setUp() throws Exception {
		SegurosGUI gui = new SegurosGUI();
		demo = new FrameFixture(gui);
		gui.setVisible(true);
	}
	
	@After
	public void tearDown() {
		demo.cleanUp();
	}
	
	// Caso de prueba v�lido
	@Test
	public void testGuiValido() {
		// 1) (Todo Riesgo, 1, 21/03/2021, TRUE): 900
		demo.comboBox("comboCobertura").selectItem("TODO_RIESGO");
		demo.textBox("txtPotencia").setText("1");
		demo.textBox("txtFechaUltimoSiniestro").setText("21/03/2021");
		demo.radioButton("btnMinusvalia").check();
		
		demo.button("btnCalcular").click();
		demo.textBox("txtPrecio").requireText("900.0");
	}
	
	// Casos de prueba no v�lidos
	@Test	
	public void testFechaInvalida() {
		// 1) (Todo Riesgo, 1, �El a�o pasado�, TRUE): 900
		demo.comboBox("comboCobertura").selectItem("TODO_RIESGO");
		demo.textBox("txtPotencia").setText("1");
		demo.textBox("txtFechaUltimoSiniestro").setText("El a�o pasado");
		demo.radioButton("btnMinusvalia").check();
		
		demo.button("btnCalcular").click();
		demo.textBox("txtPrecio").requireText("La fecha no se pudo parsear");
	}
	
	@Test
	public void testPotenciaInvalida() {
		// 2) (Todo Riesgo, �Veinte�, 21/03/2021, TRUE)
		demo.comboBox("comboCobertura").selectItem("TODO_RIESGO");
		demo.textBox("txtPotencia").setText("Veinte");
		demo.textBox("txtFechaUltimoSiniestro").setText("21/03/2021");
		demo.radioButton("btnMinusvalia").check();
				
		demo.button("btnCalcular").click();
		demo.textBox("txtPrecio").requireText("Potencia introducida no v�lida");
	}

}
