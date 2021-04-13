package es.unican.is2.practica3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;

import java.util.Date;
import java.util.Calendar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JList;
import javax.swing.DefaultListModel;

public class InterfazGrafica {

	private JFrame frame;
	private JTextField fieldId;
	private JSpinner spinnerHora;
	private Alarmas alarmas;
	private JList<String> alarmasDesactivadas;
	private DefaultListModel<String> modeloDesactivadas;
	private JList<String> alarmasActivadas;
	private DefaultListModel<String> modeloActivadas;
	private final JTextArea areaSonando;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazGrafica window = new InterfazGrafica();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazGrafica() {
		this.areaSonando = new JTextArea();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		alarmas = new Alarmas();
		modeloDesactivadas = new DefaultListModel<String>();
		modeloActivadas = new DefaultListModel<String>();
		
		alarmasDesactivadas = new JList<String>();
		alarmasDesactivadas.setBounds(267, 188, 140, 125);
		frame.getContentPane().add(alarmasDesactivadas);
		
		alarmasActivadas = new JList<String>();
		alarmasActivadas.setBounds(267, 47, 140, 118);
		frame.getContentPane().add(alarmasActivadas);
		
		JButton btnNueva = new JButton("Nueva Alarma");
		btnNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Al pulsar el boton, se toman los valores introducidos en los campos id y hora
				// para crear una nueva alarma
				String id = fieldId.getText();
				Date hora = (Date) spinnerHora.getValue();
				//try {
					alarmas.nuevaAlarma(id, hora);
					
					// Si la alarma creada no se encuentra ya en la lista de activadas, se anhade al 
					// modelo de dicha lista y se refresca para que los cambios se vean en la gui
					if (!(modeloActivadas.contains(id) || modeloDesactivadas.contains(id))) {
						modeloActivadas.addElement(id);
						alarmasActivadas.setModel(modeloActivadas);
					}
				/*} catch (IllegalStateException ex) {
					System.out.println("monkaS");
				}*/
			}
		});
		btnNueva.setBounds(21, 115, 158, 23);
		frame.getContentPane().add(btnNueva);
		
		JButton btnApagar = new JButton("APAGAR");
		btnApagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Si al pulsar el boton una alarma se encuentra sonando, se apaga inmediatamente
				if (alarmas.sonando()) {
					AlarmasState.getEstadoSonando().apagar(alarmas);
				}
			}
		});
		btnApagar.setBounds(39, 149, 118, 40);
		frame.getContentPane().add(btnApagar);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Al pulsar el boton, se toma la alarma resaltada en la lista de alarmas desactivadas
				String idAlarma = alarmasDesactivadas.getSelectedValue();
				
				// Control de errores: No permite eliminar una alarma que no este previamente desactivada
				if (!modeloActivadas.contains(idAlarma)) {
					alarmas.eliminaAlarma(idAlarma);
					modeloDesactivadas.removeElement(idAlarma);
					alarmasDesactivadas.setModel(modeloDesactivadas);
				}
			}
		});
		btnEliminar.setBounds(267, 357, 140, 23);
		frame.getContentPane().add(btnEliminar);
		
		JButton btnOn = new JButton("ON");
		btnOn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String idAlarma = alarmasDesactivadas.getSelectedValue();
					
					// Control de errores: No permite activar alarmas que se encuentren ya en la lista de activadas
					if (!modeloActivadas.contains(idAlarma)) {
						alarmas.alarmaOn(idAlarma);
						modeloActivadas.addElement(idAlarma);
						modeloDesactivadas.removeElement(idAlarma);
						alarmasActivadas.setModel(modeloActivadas);
						alarmasDesactivadas.setModel(modeloDesactivadas);
					}
				} catch (NullPointerException ex) {}
			}
		});
		btnOn.setBounds(267, 323, 58, 23);
		frame.getContentPane().add(btnOn);
		
		JButton btnOff = new JButton("OFF");
		btnOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String idAlarma = alarmasActivadas.getSelectedValue();	
					// Control de errores: No permite desactivar alarmas que se encuentren ya en la lista de desactivadas
					if (!modeloDesactivadas.contains(idAlarma)) {
						alarmas.alarmaOff(idAlarma);
						modeloDesactivadas.addElement(idAlarma);
						modeloActivadas.removeElement(idAlarma);
						alarmasDesactivadas.setModel(modeloDesactivadas);
						alarmasActivadas.setModel(modeloActivadas);
					}
				} catch (NullPointerException ex) {}	
			}
		});
		btnOff.setBounds(349, 323, 58, 23);
		frame.getContentPane().add(btnOff);
		
		JLabel lblId = new JLabel("ID Alarma");
		lblId.setBounds(21, 56, 58, 14);
		frame.getContentPane().add(lblId);
		
		JLabel lblHora = new JLabel("Hora Alarma");
		lblHora.setBounds(21, 90, 80, 14);
		frame.getContentPane().add(lblHora);
		
		fieldId = new JTextField();
		fieldId.setBounds(99, 53, 80, 20);
		frame.getContentPane().add(fieldId);
		fieldId.setColumns(10);
		
		JLabel lblDesactivadas = new JLabel("Alarmas desactivadas");
		lblDesactivadas.setBounds(274, 174, 133, 14);
		frame.getContentPane().add(lblDesactivadas);
		
		JLabel lblActivadas = new JLabel("Alarmas activadas");
		lblActivadas.setBounds(274, 33, 118, 14);
		frame.getContentPane().add(lblActivadas);
		
		// Se modifica el formato del JSpinner para que solo muestre horas y minutos
		spinnerHora = new JSpinner();
		spinnerHora.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
		spinnerHora.setEditor(new JSpinner.DateEditor(spinnerHora, "HH:mm"));
		
		spinnerHora.setBounds(99, 87, 80, 20);
		frame.getContentPane().add(spinnerHora);
		
		areaSonando.setBounds(21, 217, 199, 96);
		frame.getContentPane().add(areaSonando);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				areaSonando.selectAll();
				areaSonando.replaceSelection("");
			}
		});
		btnClear.setBounds(21, 323, 199, 23);
		frame.getContentPane().add(btnClear);
		
		JLabel lblConsola = new JLabel("Consola");
		lblConsola.setBounds(23, 203, 80, 14);
		frame.getContentPane().add(lblConsola);
		//redirectSystemStreams();
	}
	
	private void updateTextArea(final String text) {
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	        areaSonando.append(text);
	      }
	    });
	}
	
	private void redirectSystemStreams() {
	    OutputStream out = new OutputStream() {
	      @Override
	      public void write(int b) throws IOException {
	        updateTextArea(String.valueOf((char) b));
	      }

	      @Override
	      public void write(byte[] b, int off, int len) throws IOException {
	        updateTextArea(new String(b, off, len));
	      }

	      @Override
	      public void write(byte[] b) throws IOException {
	        write(b, 0, b.length);
	      }
	    };

	    System.setOut(new PrintStream(out, true));
	    System.setErr(new PrintStream(out, true));
	}
}
