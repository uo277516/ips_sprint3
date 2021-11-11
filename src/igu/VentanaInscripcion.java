package igu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import logica.AtletaDto;
import logica.AtletaModel;
import logica.CompeticionDto;
import logica.CompeticionModel;
import logica.InscripcionModel;

public class VentanaInscripcion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPedir;
	private TextField txtEmail;
	private Label lblEmail;
	private JButton btnValidar;
	private JLabel lblInfoJus;
	private InscripcionModel ins;
	private AtletaModel atl;
	@SuppressWarnings("unused")
	private CompeticionModel comp;
	private CompeticionDto cSeleccionada;
	@SuppressWarnings("unused")
	private VentanaMostrarCarreras vC;
	private JTextArea textArea;
	private AtletaDto atleta;
	private JButton btnSiguiente;
	@SuppressWarnings("unused")
	private VentanaRegistro vR;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaInscripci�n frame = new VentanaInscripci�n();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * 
	 * @param competicionDto
	 * @param ventanaMostrarCarreras
	 */
	public VentanaInscripcion(VentanaMostrarCarreras ventanaMostrarCarreras, CompeticionDto competicionDto) {
		ins = new InscripcionModel();
		atl = new AtletaModel();
		comp = new CompeticionModel();
		this.vC = ventanaMostrarCarreras;
		this.cSeleccionada = competicionDto;
		setTitle("Inscripci\u00F3n de carreras");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 618, 422);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblPedir());
		contentPane.add(getTxtEmail());
		contentPane.add(getLblEmail());
		contentPane.add(getBtnValidar());
		contentPane.add(getLblInfoJus());
		contentPane.add(getTextArea());
		contentPane.add(getBtnSiguiente());
	}

	private JLabel getLblPedir() {
		if (lblPedir == null) {
			lblPedir = new JLabel("Para inscribirte en una nueva carrera, por favor introduzca su email:");
			lblPedir.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblPedir.setBounds(20, 28, 460, 29);
		}
		return lblPedir;
	}

	private TextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new TextField();
			txtEmail.setBounds(101, 72, 189, 21);
		}
		return txtEmail;
	}

	private Label getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new Label("E-mail:");
			lblEmail.setBounds(36, 72, 59, 21);
		}
		return lblEmail;
	}

	protected boolean comprobarCampos() {
		// TODO Auto-generated method stub
		return false;
	}

	private JButton getBtnValidar() {
		if (btnValidar == null) {
			btnValidar = new JButton("Validar");
			btnValidar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (txtEmail.getText().equals("")) { // si esta vacio
						mostrarErrorVacio();
					} else if (yaRegistradoEnlaCarrera()) { // si ya en la carrera
						mostrarErrorYaRegistrado();
					} else if (registradoAtletaEnBase()) { // si ya en la base de datos
						textArea.setEnabled(true);
						lblInfoJus.setVisible(true);
						btnSiguiente.setEnabled(true);
						inscribirParticipante();
						textArea.setText(getInformacion());
					} else if (!registradoAtletaEnBase()) { // si no en la base de datos
						System.out.println("patata");
						mostrarMensajeNoInscritoSiQuiere();
					} else if (!haySuficientesPlazas()) { // si no plazos
						mostrarErrorPlazas();
					} else if (esMenor()) { // si es menor
						mostrarErrorMenor();
					}
				}
			});
			btnValidar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnValidar.setBackground(SystemColor.activeCaption);
			btnValidar.setBounds(20, 131, 106, 21);
		}
		return btnValidar;
	}

	protected void mostrarMensajeNoInscritoSiQuiere() {
		int reply = JOptionPane.showConfirmDialog(this, "No est�s registrado en la base �te gustar�a registrarte?",
				"Ventana registro", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			mostrarVentanaRegistro();
			txtEmail.setText("");
		} else {
			JOptionPane.showMessageDialog(this, "Lo sentimos, pero entonces no puede inscribirse.");
			// System.exit(0);
		}
	}

	private void mostrarVentanaRegistro() {
		// this.dispose();
		// CompeticionDto competicion = crearCompeticion();
		VentanaRegistro vPal = new VentanaRegistro(this);
		vPal.setLocationRelativeTo(this);
		vPal.setVisible(true);
	}

	protected void mostrarErrorDatosNoRegistrados() {
		JOptionPane.showMessageDialog(this, "Sus datos todavia no han sido registrados");

	}

	private boolean registradoAtletaEnBase() {
		if (atl.atletaYaRegistradoEnLaBase(txtEmail.getText()).isEmpty()) {
			// si no hay ninguno
			return false;
		} else
			return true;
	}

	protected void mostrarErrorMenor() {
		JOptionPane.showMessageDialog(this,
				"Usted es menor de edad, por lo tanto no puede participar en la competicion");

	}

	private boolean esMenor() {
		int years = atl.yearsAtleta(txtEmail.getText());
		if (years >= 18) {
			return false;
		} else {
			return true;
		}
	}

	protected void inscribirParticipante() {
		System.out.println(txtEmail.getText());
		System.out.println(cSeleccionada.getId());
		float n = 10.0f + cSeleccionada.getCuota1();
		ins.agregarInscripcion(txtEmail.getText(), cSeleccionada.getId(), n, cambiarFormatoFecha());

	}

	protected boolean haySuficientesPlazas() {
		int plazas = cSeleccionada.getNum_plazas();
		if (plazas > 0)
			return true;
		else
			return false;
	}

	protected void mostrarErrorPlazas() {
		JOptionPane.showMessageDialog(this, "No hay plazas disponibles,lo sentimos");

	}

	protected void mostrarErrorYaRegistrado() {
		JOptionPane.showMessageDialog(this, "Ya te has inscrito en esta carrera");

	}

	protected boolean yaRegistradoEnlaCarrera() {
		String email = txtEmail.getText();
		// CompeticionDto c = (CompeticionDto) comboBox.getSelectedItem();
		String nombre = cSeleccionada.getNombre();
		return atl.atletaAlredyRegistred(email, nombre);
	}

	protected void mostrarErrorVacio() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, "El campo email no puede estar vac�o");
	}

	/*
	 * Como resultado de la inscripci�n, el atleta recibir� un justificante con su
	 * nombre, la competici�n, categor�a en la que participar� (ver siguientes HUs),
	 * fecha inscripci�n y cantidad que debe abonar en concepto de inscripci�n.
	 */
	private String getInformacion() {
		String s = "";
		float n = 10.0f + cogerCuotaSegunFecha();
		atleta = ins.findAtletaEmail(txtEmail.getText());
		return s += "Nombre del atleta: " + atleta.getNombre() + "\n" + "Competición: " + cSeleccionada.getNombre()
				+ "\n" + "Categoría: " + ins.getCategoriaByDniId(atleta.getDni(), cSeleccionada.getId()) + "\n"
				+ "Fecha de inscripción: " + cambiarFormatoFecha() + "\n" + "Cantidad a abonar: " + n;
	}

	private float cogerCuotaSegunFecha() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaActual = null;
		Date fechaInicio1 = null;
		Date fechaInicio2 = null;
		Date fechaInicio3 = null;
		Date fechaFin1 = null;
		Date fechaFin2 = null;
		Date fechaFin3 = null;

		try {
			fechaActual = formato.parse(cambiarFormatoFecha());
			if (cSeleccionada.getF_inicio1() != null) {
				fechaInicio1 = formato.parse(cSeleccionada.getF_inicio1());
				fechaFin1 = formato.parse(cSeleccionada.getF_fin1());
			}
			if (cSeleccionada.getF_inicio2() != null) {
				fechaInicio2 = formato.parse(cSeleccionada.getF_inicio2());
				fechaFin2 = formato.parse(cSeleccionada.getF_fin2());
			}
			if (cSeleccionada.getF_inicio3() != null) {
				fechaInicio3 = formato.parse(cSeleccionada.getF_inicio3());
				fechaFin3 = formato.parse(cSeleccionada.getF_fin3());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (cSeleccionada.getF_inicio1() != null) {
			if (fechaActual.before(fechaFin1) && fechaActual.after(fechaInicio1)) {
				return cSeleccionada.getCuota1();
			}
		} else if (cSeleccionada.getF_inicio2() != null) {
			if (fechaActual.before(fechaFin2) && fechaActual.after(fechaInicio2)) {
				return cSeleccionada.getCuota2();
			}
		} else if (cSeleccionada.getF_inicio3() != null) {
			if (fechaActual.before(fechaFin3) && fechaActual.after(fechaInicio3)) {
				return cSeleccionada.getCuota3();
			}
		}
		return -600;

	}

	private String cambiarFormatoFecha() {
		String fechaString = String.valueOf(LocalDate.now());
		String[] fechaPartida = fechaString.split("-");
		String result = "";
		for (int i = 0; i < fechaPartida.length; i++) {
			result = "/" + fechaPartida[i] + result;
		}
		return result.substring(1);

	}

	private JLabel getLblInfoJus() {
		if (lblInfoJus == null) {
			lblInfoJus = new JLabel("A continuaci\u00F3n, se imprimir\u00E1 el siguiente justificante informante:");
			lblInfoJus.setVisible(false);
			lblInfoJus.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblInfoJus.setBounds(20, 172, 374, 21);
		}
		return lblInfoJus;
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
			textArea.setBounds(20, 206, 404, 139);
		}
		return textArea;
	}

	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setEnabled(false);
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int seleccion = JOptionPane.showOptionDialog(null, "Método de pago",
							"Seleccione una opción para pagar su inscripción", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, // null para icono por defecto.
							new Object[] { "Tarjeta de crédito", "Tranferencia" }, // null para YES, NO y CANCEL
							"opcion 1");

					if (seleccion != -1)
						System.out.println("seleccionada opcion " + (seleccion + 1));
					if (seleccion == 0) // tarjeta de credito
					{
						mostrarVentanaTarjetaDeCredito();
					} else if (seleccion == 1) {
						mostrarVentanaTransferencia();
					}
				}
			});
			btnSiguiente.setBackground(Color.GREEN);
			btnSiguiente.setForeground(Color.WHITE);
			btnSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnSiguiente.setBounds(488, 351, 106, 23);
		}
		return btnSiguiente;
	}

	protected void mostrarVentanaTransferencia() {
		this.dispose();
		VentanaTransferencia vPal = new VentanaTransferencia();
		vPal.setLocationRelativeTo(this);
		vPal.setVisible(true);

	}

	protected void mostrarVentanaTarjetaDeCredito() {
		this.dispose();
		// CompeticionDto competicion = crearCompeticion();
		VentanaTarjetaCredito vPal = new VentanaTarjetaCredito(this, cSeleccionada, atleta);
		vPal.setLocationRelativeTo(this);
		vPal.setVisible(true);
	}
}
