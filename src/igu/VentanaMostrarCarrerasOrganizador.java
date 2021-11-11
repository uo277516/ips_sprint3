package igu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import logica.CompeticionDto;
import logica.CompeticionModel;

public class VentanaMostrarCarrerasOrganizador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<CompeticionDto> cmboxCarreras;
	private CompeticionModel comp;
	private JButton btnAceptar;
	private JLabel lblCompeticiones;
	private JTextArea txtInfoCarrera;
	@SuppressWarnings("unused")
	private VentanaInicial vi;

	private String opcion;

	/**
	 * Create the frame.
	 * 
	 * @param ventanaInicial
	 */
	public VentanaMostrarCarrerasOrganizador(String opcion) {
		this.opcion = opcion;
		comp = new CompeticionModel();
		setTitle("Selecci\u00F3n de Competici\u00F3n:");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 348);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCmboxCarreras());
		contentPane.add(getBtnAceptar());
		contentPane.add(getLblCompeticiones());
		contentPane.add(getTxtInfoCarrera());
	}

	private JComboBox<CompeticionDto> getCmboxCarreras() {
		if (cmboxCarreras == null) {
			cmboxCarreras = new JComboBox<CompeticionDto>();
			cmboxCarreras.setBounds(24, 137, 471, 22);
			cmboxCarreras.setModel(new DefaultComboBoxModel<CompeticionDto>(comp.getCompeticionesArray()));
		}
		return cmboxCarreras;
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.setOpaque(true);
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (opcion == "i")
							mostrarVentanaAtletaInscripcion();
						else if (opcion == "c")
							mostrarVentanaCompeticion();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			});
			btnAceptar.setBackground(Color.GREEN);
			btnAceptar.setForeground(Color.BLACK);
			btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnAceptar.setBounds(423, 275, 89, 23);
		}
		return btnAceptar;
	}

	private void mostrarVentanaAtletaInscripcion() throws SQLException {
		VentanaAtletaInscripcion vai = new VentanaAtletaInscripcion(
				(CompeticionDto) getCmboxCarreras().getSelectedItem());
		this.dispose();
		vai.setVisible(true);
	}

	private void mostrarVentanaCompeticion() throws SQLException {
		VentanaClasificacion vai = new VentanaClasificacion(
				((CompeticionDto) getCmboxCarreras().getSelectedItem()).getId());
		this.dispose();
		vai.setVisible(true);

	}

	private JLabel getLblCompeticiones() {
		if (lblCompeticiones == null) {
			lblCompeticiones = new JLabel("Competiciones");
			lblCompeticiones.setFont(new Font("Lucida Grande", Font.BOLD, 25));
			lblCompeticiones.setBounds(24, 6, 389, 46);
		}
		return lblCompeticiones;
	}

	private JTextArea getTxtInfoCarrera() {
		if (txtInfoCarrera == null) {
			txtInfoCarrera = new JTextArea();
			txtInfoCarrera.setFocusable(false);
			txtInfoCarrera.setEditable(false);
			txtInfoCarrera.setText(
					"Se muestra: Nombre - Fecha de la competición - Tipo - Distancia - Cuota \n - Fecha de fin de inscripción");
			txtInfoCarrera.setBounds(24, 76, 471, 49);
		}
		return txtInfoCarrera;
	}
}
