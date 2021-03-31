package es.unican.is2.practica3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JPanel;

public class InterfazGrafica {

	private JFrame frame;
	private JTextField fieldId;
	private JSpinner spinnerHora;
	private Alarmas alarmas;
	private AlarmasState alarmasState;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		alarmas = new Alarmas();
		
		JButton btnNueva = new JButton("Nueva Alarma");
		btnNueva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = fieldId.getText();
				Date hora = (Date) spinnerHora.getValue();
				alarmas.nuevaAlarma(id, hora);
			}
		});
		btnNueva.setBounds(21, 115, 148, 23);
		frame.getContentPane().add(btnNueva);
		
		JButton btnApagar = new JButton("APAGAR");
		btnApagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnApagar.setBounds(39, 149, 112, 40);
		frame.getContentPane().add(btnApagar);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnEliminar.setBounds(267, 357, 140, 23);
		frame.getContentPane().add(btnEliminar);
		
		JButton btnOn = new JButton("ON");
		btnOn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnOn.setBounds(267, 323, 58, 23);
		frame.getContentPane().add(btnOn);
		
		JButton btnOff = new JButton("OFF");
		btnOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnOff.setBounds(349, 323, 58, 23);
		frame.getContentPane().add(btnOff);
		
		JLabel lblId = new JLabel("ID Alarma");
		lblId.setBounds(21, 56, 58, 14);
		frame.getContentPane().add(lblId);
		
		JLabel lblHora = new JLabel("Hora Alarma");
		lblHora.setBounds(21, 90, 68, 14);
		frame.getContentPane().add(lblHora);
		
		fieldId = new JTextField();
		fieldId.setBounds(89, 53, 80, 20);
		frame.getContentPane().add(fieldId);
		fieldId.setColumns(10);
		
		JTextPane panelDesactivadas = new JTextPane();
		panelDesactivadas.setBounds(267, 189, 140, 123);
		frame.getContentPane().add(panelDesactivadas);
		
		JLabel lblDesactivadas = new JLabel("Alarmas desactivadas");
		lblDesactivadas.setBounds(274, 174, 133, 14);
		frame.getContentPane().add(lblDesactivadas);
		
		JLabel lblActivadas = new JLabel("Alarmas activadas");
		lblActivadas.setBounds(274, 33, 118, 14);
		frame.getContentPane().add(lblActivadas);
		
		JTextPane panelActivadas = new JTextPane();
		panelActivadas.setBounds(267, 46, 140, 117);
		frame.getContentPane().add(panelActivadas);
		
		spinnerHora = new JSpinner();
		spinnerHora.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
		spinnerHora.setEditor(new JSpinner.DateEditor(spinnerHora, "HH:mm"));
		spinnerHora.setBounds(89, 87, 80, 20);
		frame.getContentPane().add(spinnerHora);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(alarmas.toString());
			}
		});
		btnNewButton.setBounds(80, 304, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
