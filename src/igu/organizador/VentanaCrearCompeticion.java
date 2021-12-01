package igu.organizador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import igu.VentanaInicial;
import igu.atleta.VentanaAtletaListaEspera;
import logica.CompeticionDto;
import logica.CompeticionModel;
import logica.ListaEsperaModel;

@SuppressWarnings("serial")
public class VentanaCrearCompeticion extends JFrame {

	private JPanel contentPane;
	private CompeticionModel comp;
	private ListaEsperaModel listam;
	private JTextArea txtAreaInfo;
	private JPanel pnDatosBasicos;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblFechaComp;
	private JTextField txtFechaComp;
	private JLabel lblFormato;
	private JLabel lblTipo;
	private JLabel lblDistancia;
	private JTextField txtDistancia;
	private JLabel lblPlazas;
	private JTextField txtPlazas;
	private JButton btnValidar;
	private JLabel lblTiposCarreras;
	private JLabel lblKm;
	private JPanel pnPlazos;
	private JLabel lblFechaInicio;
	private JTextField txtFechaIniico;
	private JLabel lblFechaFin;
	private JTextField txtFechaFin;
	private JLabel lblCuota;
	private JTextField txtCuota;
	private JButton btnInsertar;
	private JScrollPane scrollPane;
	private JTextArea textArea;

	private String id_comp;
	private int plazos = 0;
	private JLabel lblDorsales;
	private JTextField txtDorsales;
	private JLabel lblGestionarCat;
	private JButton btnGestionar;
	private JButton btnFinalizar;
	private JComboBox<String> comboBox;
	private JLabel lblCanc;
	private JRadioButton radioSiC;
	private JRadioButton radioNoC;
	private JPanel panel;
	private JLabel lblFMC;
	private JLabel lblNewLabel;
	private JTextField txtFMC;
	private JTextField txtPCCanc;
	private JLabel lblDdmmaaaa;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblNewLabel_1;
	private JPanel pnlListaEspera;
	private JLabel lblListaEspera;
	private JRadioButton rdbtnSiLista;
	private JRadioButton rdbtnNoLista;
	private final ButtonGroup buttonGroupListaEspera = new ButtonGroup();
	private VentanaInicial vi;

	/**
	 * Create the frame.
	 */
	public VentanaCrearCompeticion(VentanaInicial vi) {
		this.vi=vi;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAtletaListaEspera.class.getResource("/img/icono-plano-de-la-bandera-carreras-con-sombra-larga-colorido-198376094.jpg")));

		comp = new CompeticionModel();
		listam = new ListaEsperaModel();
		setTitle("Creaci\u00F3n de competiciones:");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		setBounds(100, 100, 746, 655);
//
//		setBounds(100, 100, 598, 655);

		setBounds(100, 40000, 1250, 662);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTxtAreaInfo());
		contentPane.add(getPnDatosBasicos());
		contentPane.add(getPnPlazos());
		btnValidar.setEnabled(true);
		pnPlazos.setVisible(false);
		txtNombre.setEditable(true);
		txtDistancia.setEditable(true);
		txtFechaComp.setEditable(true);
		txtPlazas.setEditable(true);
		txtDorsales.setEditable(true);
		contentPane.add(getLblGestionarCat());
		contentPane.add(getBtnGestionar());
		contentPane.add(getBtnFinalizar());
		contentPane.add(getPnlListaEspera());
		lblGestionarCat.setVisible(false);
		btnGestionar.setVisible(false);
		btnFinalizar.setVisible(false);
		pnlListaEspera.setVisible(false);
	}

	private JTextArea getTxtAreaInfo() {
		if (txtAreaInfo == null) {
			txtAreaInfo = new JTextArea();
			txtAreaInfo.setEditable(false);
			txtAreaInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtAreaInfo.setText(
					"Para crear una nueva competici\u00F3n deber\u00E1 ingresar los siguientes datos b\u00E1sicos: \r\nnombre, fecha competici\u00F3n, tipo carrera, distancia y plazas.\r\nUna vez introducidos se deber\u00E1n planificar los plazos de isncripci\u00F3n. M\u00EDnimo uno y m\u00E1ximo 3.\r\nPor \u00FAltimo se han de especificar las distintas categor\u00EDas que tendr\u00E1 la competici\u00F3n.");
			txtAreaInfo.setBounds(20, 11, 561, 68);
		}
		return txtAreaInfo;
	}

	private JPanel getPnDatosBasicos() {
		if (pnDatosBasicos == null) {
			pnDatosBasicos = new JPanel();
			pnDatosBasicos.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
					"Datos b\u00E1sicos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnDatosBasicos.setBackground(Color.WHITE);
			pnDatosBasicos.setBounds(30, 100, 551, 444);
			pnDatosBasicos.setLayout(null);
			pnDatosBasicos.add(getLblNombre());
			pnDatosBasicos.add(getTxtNombre());
			pnDatosBasicos.add(getLblFechaComp());
			pnDatosBasicos.add(getTxtFechaComp());
			pnDatosBasicos.add(getLblFormato());
			pnDatosBasicos.add(getLblTipo());
			pnDatosBasicos.add(getLblDistancia());
			pnDatosBasicos.add(getTxtDistancia());
			pnDatosBasicos.add(getLblPlazas());
			pnDatosBasicos.add(getTxtPlazas());
			pnDatosBasicos.add(getBtnValidar());
			pnDatosBasicos.add(getLblTiposCarreras());
			pnDatosBasicos.add(getLblKm());
			pnDatosBasicos.add(getLblDorsales());
			pnDatosBasicos.add(getTxtDorsales());
			pnDatosBasicos.add(getComboBox());
			pnDatosBasicos.add(getLblCanc());
			pnDatosBasicos.add(getRadioSiC());
			pnDatosBasicos.add(getRadioNoC());
			pnDatosBasicos.add(getPanel());
		}
		return pnDatosBasicos;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:\r\n");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNombre.setBounds(10, 32, 68, 22);
		}
		return lblNombre;
	}

	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtNombre.setBounds(103, 32, 307, 22);
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}

	private JLabel getLblFechaComp() {
		if (lblFechaComp == null) {
			lblFechaComp = new JLabel("Fecha Comp:\r\n");
			lblFechaComp.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblFechaComp.setBounds(10, 65, 84, 22);
		}
		return lblFechaComp;
	}

	private JTextField getTxtFechaComp() {
		if (txtFechaComp == null) {
			txtFechaComp = new JTextField();
			txtFechaComp.setBounds(103, 65, 121, 22);
			txtFechaComp.setColumns(10);
		}
		return txtFechaComp;
	}

	private JLabel getLblFormato() {
		if (lblFormato == null) {
			lblFormato = new JLabel("dd/MM/aaaa --- posterior a la actual");
			lblFormato.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblFormato.setBounds(248, 69, 192, 17);
		}
		return lblFormato;
	}

	private JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("Tipo:");
			lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTipo.setBounds(10, 98, 68, 22);
		}
		return lblTipo;
	}

	private JLabel getLblDistancia() {
		if (lblDistancia == null) {
			lblDistancia = new JLabel("Distancia:");
			lblDistancia.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDistancia.setBounds(10, 131, 68, 22);
		}
		return lblDistancia;
	}

	private JTextField getTxtDistancia() {
		if (txtDistancia == null) {
			txtDistancia = new JTextField();
			txtDistancia.setBounds(103, 132, 121, 23);
			txtDistancia.setColumns(10);
		}
		return txtDistancia;
	}

	private JLabel getLblPlazas() {
		if (lblPlazas == null) {
			lblPlazas = new JLabel("Plazas:");
			lblPlazas.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblPlazas.setBounds(10, 164, 68, 25);
		}
		return lblPlazas;
	}

	private JTextField getTxtPlazas() {
		if (txtPlazas == null) {
			txtPlazas = new JTextField();
			txtPlazas.setBounds(103, 166, 121, 22);
			txtPlazas.setColumns(10);
		}
		return txtPlazas;
	}

	private JButton getBtnValidar() {
		if (btnValidar == null) {
			btnValidar = new JButton("Validar");
			btnValidar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (txtNombre.getText().equals("") || txtFechaComp.getText().equals("")
							|| txtPlazas.getText().equals("") || txtDistancia.getText().equals("")
							|| (radioSiC.isSelected() && txtFMC.getText().equals("")
									|| radioSiC.isSelected() && txtPCCanc.getText().equals(""))) {
						System.out.println("Hola");
						mostrarCamposVacios();
					} else {
						try {
							if (!soloNumerosFecha(txtFechaComp.getText())) {
								mostrarErrorFecha();
								txtFechaComp.setText("");
							} else if (!fechaValida(txtFechaComp.getText())) {
								mostrarErrorFecha();
								txtFechaComp.setText("");
							} else if (!compruebaSoloNumeros(getTxtDistancia().getText())) {
								mostrarErrorDistancia();
								txtDistancia.setText("");
							} else if (!compruebaSoloNumeros(getTxtPlazas().getText())) {
								mostrarErrorPlazas();
								txtPlazas.setText("");
							} else if (!compruebaSoloNumeros(getTxtDorsales().getText())) {
								mostrarErrorPlazas();
								txtDorsales.setText("");
							} else if (radioSiC.isSelected() && !fechaValida(txtFMC.getText())) {
								mostrarErrorFecha();
								txtFMC.setText("");
							} else if (radioSiC.isSelected()) {
								insertarDatosBasicosConCancelacion();
								mostrarDatosBasicosCorrectos();
								btnValidar.setEnabled(false);
								pnPlazos.setVisible(true);
								pnlListaEspera.setVisible(true);
								txtNombre.setEditable(false);
								txtDistancia.setEditable(false);
								txtFechaComp.setEditable(false);
								txtPlazas.setEditable(false);
								comboBox.setEditable(false);
								txtDorsales.setEditable(false);
							} else {
								insertarDatosBasicos();
								mostrarDatosBasicosCorrectos();
								btnValidar.setEnabled(false);
								pnPlazos.setVisible(true);
								pnlListaEspera.setVisible(true);
								txtNombre.setEditable(false);
								txtDistancia.setEditable(false);
								txtFechaComp.setEditable(false);
								txtPlazas.setEditable(false);
								comboBox.setEditable(false);
								txtDorsales.setEditable(false);
							}
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

				}
			});
			btnValidar.setForeground(Color.WHITE);
			btnValidar.setBackground(Color.GREEN);
			btnValidar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnValidar.setBounds(440, 411, 101, 22);
		}
		return btnValidar;
	}

	protected void insertarDatosBasicosConCancelacion() {
		String id = UUID.randomUUID().toString();
		this.id_comp = id;
		String tipo = getComboBox().getSelectedItem().toString();
		comp.insertarDatosBasicosConCancelacion(id, getTxtNombre().getText(), getTxtFechaComp().getText(), tipo,
				Integer.parseInt(getTxtDistancia().getText()), Integer.parseInt(getTxtPlazas().getText()),
				Integer.parseInt(getTxtDorsales().getText()), getTxtFMC().getText(),
				Double.parseDouble(getTxtPCCanc().getText()));

	}

	private void insertarDatosBasicos() {
		String id = UUID.randomUUID().toString();
		this.id_comp = id;
		String tipo = getComboBox().getSelectedItem().toString();
		comp.insertarDatosBasicos(id, getTxtNombre().getText(), getTxtFechaComp().getText(), tipo,
				Integer.parseInt(getTxtDistancia().getText()), Integer.parseInt(getTxtPlazas().getText()),
				Integer.parseInt(getTxtDorsales().getText()));

	}

	private void mostrarDatosBasicosCorrectos() {
		JOptionPane.showMessageDialog(this, "Se han validado los datos básicos");
	}

	private void mostrarErrorPlazas() {
		JOptionPane.showMessageDialog(this, "Error: Plazas incorrectas, introduzca solo números.");

	}

	private void mostrarErrorDistancia() {
		JOptionPane.showMessageDialog(this, "Error: Distancia incorrecta, introduzca solo números.");

	}

	private void mostrarCamposVacios() {
		JOptionPane.showMessageDialog(this, "Error: Campos vacios.");

	}

	private void mostrarErrorFecha() {
		JOptionPane.showMessageDialog(this, "Error: Fecha incorrecta.");

	}

	private boolean compruebaSoloNumeros(String text) {
		String numero = "";
		String minumero = "";
		int textsize = text.length();
		String[] numeros = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		for (int i = 0; i < text.length(); i++) {
			numero = text.substring(i, i + 1);
			for (int j = 0; j < numeros.length; j++) {
				if (numero.equals(numeros[j])) {
					minumero = minumero + numeros[j];
				}
			}
		}
		if (minumero.length() == textsize) {
			return true;
		} else
			return false;
	}

	private boolean fechaValida(String fecha) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		Date fechaTarjeta = formato.parse(fecha);
		Date fechaActual = formato.parse(cambiarFormatoFecha());

		if (fechaTarjeta.before(fechaActual)) {
			return false;
		}
		return true;
	}

	private boolean soloNumerosFecha(String fecha) throws ParseException {
		String numero = "";
		int contador = 0;
		String minumero = "";
		String[] posiciones = new String[fecha.length()];
		String[] numeros = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		for (int i = 0; i < fecha.length(); i++) {
			numero = fecha.substring(i, i + 1);
			for (int j = 0; j < numeros.length; j++) {
				if (numero.equals(numeros[j])) {
					minumero = minumero + numeros[j];
				}
			}
			if (numero.equals("/")) {
				posiciones[i] = "/";
				contador++;
			}
		}
		if (contador == 2 && minumero.length() == 8) {
			if (posiciones[2] != null && posiciones[5] != null) {
				return true;
			} else
				return false;
		} else
			return false;
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

	private JLabel getLblTiposCarreras() {
		if (lblTiposCarreras == null) {
			lblTiposCarreras = new JLabel("monta\u00F1a/asfalto");
			lblTiposCarreras.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblTiposCarreras.setBounds(248, 102, 95, 17);
		}
		return lblTiposCarreras;
	}

	private JLabel getLblKm() {
		if (lblKm == null) {
			lblKm = new JLabel("En km");
			lblKm.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblKm.setBounds(247, 136, 49, 14);
		}
		return lblKm;
	}

	private JPanel getPnPlazos() {
		if (pnPlazos == null) {
			pnPlazos = new JPanel();
			pnPlazos.setBorder(new TitledBorder(null, "Plazos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnPlazos.setBackground(Color.WHITE);
			pnPlazos.setBounds(619, 100, 551, 189);
			pnPlazos.setLayout(null);
			pnPlazos.add(getLblFechaInicio());
			pnPlazos.add(getTxtFechaIniico());
			pnPlazos.add(getLblFechaFin());
			pnPlazos.add(getTxtFechaFin());
			pnPlazos.add(getLblCuota());
			pnPlazos.add(getTxtCuota());
			pnPlazos.add(getBtnInsertar());
			pnPlazos.add(getScrollPane());
			pnPlazos.setVisible(false);
		}
		return pnPlazos;
	}

	private JLabel getLblFechaInicio() {
		if (lblFechaInicio == null) {
			lblFechaInicio = new JLabel("Fecha inicio:");
			lblFechaInicio.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblFechaInicio.setBounds(10, 26, 87, 16);
		}
		return lblFechaInicio;
	}

	private JTextField getTxtFechaIniico() {
		if (txtFechaIniico == null) {
			txtFechaIniico = new JTextField();
			txtFechaIniico.setColumns(10);
			txtFechaIniico.setBounds(94, 24, 87, 22);
		}
		return txtFechaIniico;
	}

	private JLabel getLblFechaFin() {
		if (lblFechaFin == null) {
			lblFechaFin = new JLabel("Fecha fin:");
			lblFechaFin.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblFechaFin.setBounds(191, 27, 77, 14);
		}
		return lblFechaFin;
	}

	private JTextField getTxtFechaFin() {
		if (txtFechaFin == null) {
			txtFechaFin = new JTextField();
			txtFechaFin.setColumns(10);
			txtFechaFin.setBounds(260, 24, 87, 22);
		}
		return txtFechaFin;
	}

	private JLabel getLblCuota() {
		if (lblCuota == null) {
			lblCuota = new JLabel("Cuota:");
			lblCuota.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblCuota.setBounds(357, 27, 49, 14);
		}
		return lblCuota;
	}

	private JTextField getTxtCuota() {
		if (txtCuota == null) {
			txtCuota = new JTextField();
			txtCuota.setColumns(10);
			txtCuota.setBounds(400, 24, 87, 22);
		}
		return txtCuota;
	}

	private JButton getBtnInsertar() {
		if (btnInsertar == null) {
			btnInsertar = new JButton("Insertar\r\n");
			btnInsertar.setBackground(Color.GREEN);
			btnInsertar.setForeground(Color.WHITE);
			btnInsertar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnInsertar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (txtFechaIniico.getText().equals("") || txtFechaFin.getText().equals("")
							|| txtCuota.getText().equals("")) {
						mostrarCamposVacios();
					} else {
						try {
							if (!soloNumerosFechaInicioPlazos(txtFechaIniico.getText())) {
								mostrarFechaInicioNoBineFormada();
								txtFechaIniico.setText("");
							} else if (!fechaValidaInicio(txtFechaIniico.getText())) {
								mostrarFechaInicioIncorrecta();
								txtFechaIniico.setText("");
							} else if (!soloNumerosFechaFinPlazos(txtFechaFin.getText())) {
								mostrarFechaFinNoBineFormada();
								txtFechaFin.setText("");
							} else if (!fechaValidaFin(txtFechaFin.getText())) {
								mostrarFechaFinIncorrecta();
								txtFechaFin.setText("");
							} else if (!compruebaSoloNumeros(getTxtCuota().getText())) {
								mostrarErrorCuota();
								txtCuota.setText("");
							} else {
								System.out.println("Todo correcto");
								plazos++;
								actualizarTextArea(plazos);
								actualizarCompeticion(plazos);
								actualizarTxtInicio();
								getLblGestionarCat().setVisible(true);
								btnGestionar.setVisible(true);
								txtFechaFin.setText("");
								txtCuota.setText("");
								if (plazos == 3) {
									btnInsertar.setEnabled(false);
									txtFechaFin.setText("");
									txtFechaFin.setEditable(false);
									txtFechaIniico.setText("");
									txtFechaIniico.setEditable(false);
									txtCuota.setText("");
									txtCuota.setEditable(false);
									mensajeMaximoPlazos();
								}
							}
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

				}

			});
			btnInsertar.setBounds(440, 157, 101, 23);
		}
		return btnInsertar;
	}

	private void actualizarCompeticion(int plazos) {
		if (plazos == 1) {
			comp.actualizarCopeticion1(getTxtFechaIniico().getText(), getTxtFechaFin().getText(),
					Float.parseFloat(getTxtCuota().getText()), id_comp);
		} else if (plazos == 2) {
			comp.actualizarCopeticion2(getTxtFechaIniico().getText(), getTxtFechaFin().getText(),
					Float.parseFloat(getTxtCuota().getText()), id_comp);
		} else
			comp.actualizarCopeticion3(getTxtFechaIniico().getText(), getTxtFechaFin().getText(),
					Float.parseFloat(getTxtCuota().getText()), id_comp);
	}

	private void actualizarTxtInicio() throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		Date fechaIniico = formato.parse(getTxtFechaFin().getText());
		Date nuevaFecha = sumarDiasAFecha(fechaIniico, 1);
		String nuevaStirng = formato.format(nuevaFecha);
		txtFechaIniico.setText(nuevaStirng);
		txtFechaIniico.setEditable(false);

	}

	public static Date sumarDiasAFecha(Date fecha, int dias) {
		if (dias == 0)
			return fecha;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.DAY_OF_YEAR, dias);
		return calendar.getTime();
	}

	private void actualizarTextArea(int plazos) {
		String cadena = textArea.getText();
		if (plazos == 1) {
			cadena += "Plazo " + plazos + ":\n" + "    Fecha Inicio: " + getTxtFechaIniico().getText()
					+ "----Fecha Fin: " + getTxtFechaFin().getText() + "----Cuota: " + getTxtCuota().getText()
					+ "\u20AC";
		} else {
			cadena += "\n\nPlazo " + plazos + ":\n" + "    Fecha Inicio: " + getTxtFechaIniico().getText()
					+ "----Fecha Fin: " + getTxtFechaFin().getText() + "----Cuota: " + getTxtCuota().getText()
					+ "\u20AC";
		}
		textArea.setText(cadena);

	}

	private void mensajeMaximoPlazos() {
		JOptionPane.showMessageDialog(this, "Se han alcanzado el máximo de plazos.");

	}

	private void mostrarErrorCuota() {
		JOptionPane.showMessageDialog(this, "Error: La cuota solo pueden ser números");

	}

	private void mostrarFechaFinIncorrecta() {
		JOptionPane.showMessageDialog(this,
				"Error: La fecha fin debe ser anterior a la de competeción y posterior a la de inicio");

	}

	private void mostrarFechaInicioNoBineFormada() {
		JOptionPane.showMessageDialog(this, "Error: Fecha incorrecta.");

	}

	private void mostrarFechaFinNoBineFormada() {
		JOptionPane.showMessageDialog(this, "Error: Fecha incorrecta.");

	}

	private void mostrarFechaInicioIncorrecta() {
		JOptionPane.showMessageDialog(this, "Error: La fecha inicio debe ser anterior a la competición");

	}

	private boolean fechaValidaFin(String fecha) throws ParseException {
		List<CompeticionDto> competicion = comp.getCompeticionById(this.id_comp);
		String fecha_comp = competicion.get(0).getF_comp();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		Date fechaFin = formato.parse(fecha);
		Date fechaCompeticion = formato.parse(fecha_comp);
		Date fechaInicio = formato.parse(getTxtFechaIniico().getText());

		if (!fechaFin.before(fechaCompeticion) || fechaFin.before(fechaInicio)) {
			return false;
		}
		return true;
	}

	private boolean soloNumerosFechaFinPlazos(String fecha) {
		String numero = "";
		int contador = 0;
		String minumero = "";
		String[] posiciones = new String[fecha.length()];
		String[] numeros = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		for (int i = 0; i < fecha.length(); i++) {
			numero = fecha.substring(i, i + 1);
			for (int j = 0; j < numeros.length; j++) {
				if (numero.equals(numeros[j])) {
					minumero = minumero + numeros[j];
				}
			}
			if (numero.equals("/")) {
				posiciones[i] = "/";
				contador++;
			}
		}

		if (contador == 2 && minumero.length() == 8) {
			if (posiciones[2] != null && posiciones[5] != null) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	private boolean fechaValidaInicio(String fecha) throws ParseException {
		List<CompeticionDto> competicion = comp.getCompeticionById(this.id_comp);
		String fecha_comp = competicion.get(0).getF_comp();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		Date fechaInicio = formato.parse(fecha);
		Date fechaCompeticion = formato.parse(fecha_comp);

		if (!fechaInicio.before(fechaCompeticion)) {
			return false;
		}
		return true;
	}

	private boolean soloNumerosFechaInicioPlazos(String fecha) {

		String numero = "";
		int contador = 0;
		String minumero = "";
		String[] posiciones = new String[fecha.length()];
		String[] numeros = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		for (int i = 0; i < fecha.length(); i++) {
			numero = fecha.substring(i, i + 1);
			for (int j = 0; j < numeros.length; j++) {
				if (numero.equals(numeros[j])) {
					minumero = minumero + numeros[j];
				}
			}
			if (numero.equals("/")) {
				posiciones[i] = "/";
				contador++;
			}
		}

		if (contador == 2 && minumero.length() == 8) {
			if (posiciones[2] != null && posiciones[5] != null) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(30, 57, 376, 101);
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
		}
		return textArea;
	}

	private JLabel getLblDorsales() {
		if (lblDorsales == null) {
			lblDorsales = new JLabel("Dorsales vip:");
			lblDorsales.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDorsales.setBounds(10, 208, 77, 14);
		}
		return lblDorsales;
	}

	private JTextField getTxtDorsales() {
		if (txtDorsales == null) {
			txtDorsales = new JTextField();
			txtDorsales.setBounds(103, 205, 121, 20);
			txtDorsales.setColumns(10);
		}
		return txtDorsales;
	}

	private JLabel getLblGestionarCat() {
		if (lblGestionarCat == null) {
			lblGestionarCat = new JLabel("Gestionar las categorias de la competici\u00F3n:");
			lblGestionarCat.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblGestionarCat.setBounds(619, 311, 256, 30);
		}
		return lblGestionarCat;
	}

	private JButton getBtnGestionar() {
		if (btnGestionar == null) {
			btnGestionar = new JButton("Gestionar");
			btnGestionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostratVentanaCrearCategorias();
				}

			});
			btnGestionar.setBackground(Color.GREEN);
			btnGestionar.setForeground(Color.WHITE);
			btnGestionar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnGestionar.setBounds(876, 314, 103, 23);
		}
		return btnGestionar;
	}

	public void prepararVuelta() {
		this.setVisible(true);
		btnFinalizar.setVisible(true);
		btnGestionar.setEnabled(false);

	}

	private void mostratVentanaCrearCategorias() {

		VentanaCategorias vc = new VentanaCategorias(this, id_comp);
		this.setVisible(false);
		vc.setLocationRelativeTo(this);
		vc.setVisible(true);

	}

	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getRdbtnSiLista().isSelected()) {
						// Añadir lista de espera
						String idLista = UUID.randomUUID().toString();
						listam.addLista(idLista, id_comp);
						comp.actualizarCompeticionIdLista(idLista, id_comp);
					}
					vi.setVisible(true);
					dispose();
				}
			});
			btnFinalizar.setForeground(Color.WHITE);
			btnFinalizar.setBackground(Color.RED);
			btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnFinalizar.setBounds(1126, 579, 89, 23);
		}
		return btnFinalizar;
	}

	private JComboBox<String> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<String>();
			comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "monta\u00F1a", "asfalto" }));
			comboBox.setBounds(103, 98, 121, 22);
		}
		return comboBox;
	}

	private JLabel getLblCanc() {
		if (lblCanc == null) {
			lblCanc = new JLabel("\u00BFDeseas aplicar una pol\u00EDtica de cancelaci\u00F3n?");
			lblCanc.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblCanc.setBounds(10, 255, 299, 13);
		}
		return lblCanc;
	}

	private JRadioButton getRadioSiC() {
		if (radioSiC == null) {
			radioSiC = new JRadioButton("Si");
			radioSiC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtFMC.setEnabled(true);
					txtPCCanc.setEnabled(true);
					lblFMC.setEnabled(true);
					lblNewLabel.setEnabled(true);
				}
			});
			buttonGroup.add(radioSiC);
			radioSiC.setFont(new Font("Tahoma", Font.PLAIN, 13));
			radioSiC.setBounds(23, 286, 103, 21);
		}
		return radioSiC;
	}

	private JRadioButton getRadioNoC() {
		if (radioNoC == null) {
			radioNoC = new JRadioButton("No");
			radioNoC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtFMC.setEnabled(false);
					txtPCCanc.setEnabled(false);
					lblFMC.setEnabled(false);
					lblNewLabel.setEnabled(false);
				}
			});
			radioNoC.setSelected(true);
			buttonGroup.add(radioNoC);
			radioNoC.setFont(new Font("Tahoma", Font.PLAIN, 13));
			radioNoC.setBounds(148, 286, 103, 21);
		}
		return radioNoC;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(
					new TitledBorder(null, "Cancelaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBackground(Color.WHITE);
			panel.setBounds(10, 313, 516, 88);
			panel.setLayout(null);
			panel.add(getLblFMC());
			panel.add(getLblNewLabel());
			panel.add(getTxtFMC());
			panel.add(getTxtPCCanc());
			panel.add(getLblDdmmaaaa());
			txtFMC.setEnabled(false);
			txtPCCanc.setEnabled(false);
			lblFMC.setEnabled(false);
			lblNewLabel.setEnabled(false);
			panel.add(getLblNewLabel_1());
		}
		return panel;
	}

	private JLabel getLblFMC() {
		if (lblFMC == null) {
			lblFMC = new JLabel("Fecha m\u00E1xima cancelaci\u00F3n:");
			lblFMC.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblFMC.setBounds(10, 30, 200, 13);
		}
		return lblFMC;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Porcentaje cuota a devolver:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel.setBounds(10, 53, 200, 13);
		}
		return lblNewLabel;
	}

	private JTextField getTxtFMC() {
		if (txtFMC == null) {
			txtFMC = new JTextField();
			txtFMC.setBounds(197, 28, 128, 19);
			txtFMC.setColumns(10);
		}
		return txtFMC;
	}

	private JTextField getTxtPCCanc() {
		if (txtPCCanc == null) {
			txtPCCanc = new JTextField();
			txtPCCanc.setBounds(197, 53, 128, 19);
			txtPCCanc.setColumns(10);
		}
		return txtPCCanc;
	}

	private JLabel getLblDdmmaaaa() {
		if (lblDdmmaaaa == null) {
			lblDdmmaaaa = new JLabel("dd/MM/aaaa");
			lblDdmmaaaa.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblDdmmaaaa.setBounds(335, 29, 95, 17);
		}
		return lblDdmmaaaa;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Formato: xx.yy");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNewLabel_1.setBounds(335, 54, 100, 13);
		}
		return lblNewLabel_1;
	}

	private JPanel getPnlListaEspera() {
		if (pnlListaEspera == null) {
			pnlListaEspera = new JPanel();
			pnlListaEspera.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
					"Lista de espera", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnlListaEspera.setBackground(Color.WHITE);
			pnlListaEspera.setBounds(619, 352, 551, 97);
			pnlListaEspera.setLayout(null);
			pnlListaEspera.add(getLblListaEspera());
			pnlListaEspera.add(getRdbtnSiLista());
			pnlListaEspera.add(getRdbtnNoLista());
			pnlListaEspera.setVisible(false);
		}
		return pnlListaEspera;
	}

	private JLabel getLblListaEspera() {
		if (lblListaEspera == null) {
			lblListaEspera = new JLabel("¿Desea que esta competición tenga lista de espera?");
			lblListaEspera.setBounds(18, 29, 511, 16);
		}
		return lblListaEspera;
	}

	private JRadioButton getRdbtnSiLista() {
		if (rdbtnSiLista == null) {
			rdbtnSiLista = new JRadioButton("Sí");
			buttonGroupListaEspera.add(rdbtnSiLista);
			rdbtnSiLista.setBounds(18, 57, 70, 23);
		}
		return rdbtnSiLista;
	}

	private JRadioButton getRdbtnNoLista() {
		if (rdbtnNoLista == null) {
			rdbtnNoLista = new JRadioButton("No");
			buttonGroupListaEspera.add(rdbtnNoLista);
			rdbtnNoLista.setSelected(true);
			rdbtnNoLista.setBounds(88, 57, 70, 23);
		}
		return rdbtnNoLista;
	}
}
