package es.unican.is2.practica3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
				String id = fieldId.getText();
				Date hora = (Date) spinnerHora.getValue();
				alarmas.nuevaAlarma(id, hora);
				if (!(modeloActivadas.contains(id) || modeloDesactivadas.contains(id))) {
					modeloActivadas.addElement(id);
					alarmasActivadas.setModel(modeloActivadas);
				}
			}
		});
		btnNueva.setBounds(21, 115, 148, 23);
		frame.getContentPane().add(btnNueva);
		
		JButton btnApagar = new JButton("APAGAR");
		btnApagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (alarmas.sonando()) {
					AlarmasState.getEstadoSonando().apagar(alarmas);
				}
			}
		});
		btnApagar.setBounds(39, 149, 112, 40);
		frame.getContentPane().add(btnApagar);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String idAlarma = alarmasDesactivadas.getSelectedValue();
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
				String idAlarma = alarmasDesactivadas.getSelectedValue();
				if (!modeloActivadas.contains(idAlarma)) {
					alarmas.alarmaOn(idAlarma);
					modeloActivadas.addElement(idAlarma);
					modeloDesactivadas.removeElement(idAlarma);
					alarmasActivadas.setModel(modeloActivadas);
					alarmasDesactivadas.setModel(modeloDesactivadas);
				}
			}
		});
		btnOn.setBounds(267, 323, 58, 23);
		frame.getContentPane().add(btnOn);
		
		JButton btnOff = new JButton("OFF");
		btnOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String idAlarma = alarmasActivadas.getSelectedValue();
				if (!modeloDesactivadas.contains(idAlarma)) {
					alarmas.alarmaOff(idAlarma);
					modeloDesactivadas.addElement(idAlarma);
					modeloActivadas.removeElement(idAlarma);
					alarmasDesactivadas.setModel(modeloDesactivadas);
					alarmasActivadas.setModel(modeloActivadas);
				}
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
		
		JLabel lblDesactivadas = new JLabel("Alarmas desactivadas");
		lblDesactivadas.setBounds(274, 174, 133, 14);
		frame.getContentPane().add(lblDesactivadas);
		
		JLabel lblActivadas = new JLabel("Alarmas activadas");
		lblActivadas.setBounds(274, 33, 118, 14);
		frame.getContentPane().add(lblActivadas);
		
		spinnerHora = new JSpinner();
		spinnerHora.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
		spinnerHora.setEditor(new JSpinner.DateEditor(spinnerHora, "HH:mm"));
		spinnerHora.setBounds(89, 87, 80, 20);
		frame.getContentPane().add(spinnerHora);
	}
}
