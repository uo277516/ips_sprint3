package igu;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import logica.AtletaDto;
import logica.AtletaModel;
import logica.CompeticionDto;
import logica.CompeticionModel;
import logica.InscripcionDto;
import logica.InscripcionModel;
import util.DtoAssembler;

@SuppressWarnings("serial")
public class VentanaTarjetaCredito extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JLabel lblNumero;
	private JTextField txtNumero;
	private JLabel lblFecha;
	private JLabel lblCvc;
	private JTextField txtFecha;
	private JTextField txtCvc;
	private JButton btnValidar;
	private JLabel lblFormato;
	private JLabel lblInfo;
	private JPanel panel;
	private JLabel lblJusti;
	private JButton btnFinalizar;
	@SuppressWarnings("unused")
	private VentanaInscripcion vi;
	private AtletaDto atleta;
	private CompeticionDto competicion;
	@SuppressWarnings("unused")
	private InscripcionDto inscripcion;
	@SuppressWarnings("unused")
	private AtletaModel atl;
	private CompeticionModel comp;
	private InscripcionModel ins;
	private JScrollPane scrollPane;
	private JTextArea textAreaJusti;

	

	/**
	 * Create the frame.
	 */
	public VentanaTarjetaCredito(VentanaInscripcion vi, CompeticionDto cDto, AtletaDto aDto) {
		this.vi=vi;
		this.competicion=cDto;
		this.atleta=aDto;
		ins = new InscripcionModel();
		atl = new AtletaModel();
		comp = new CompeticionModel();
		setTitle("Pago con tarjeta de cr\u00E9dito:");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 671);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTextArea());
		contentPane.add(getPanel());
		contentPane.add(getLblJusti());
		contentPane.add(getBtnFinalizar());
		contentPane.add(getScrollPane());
	}
	

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
			textArea.setText("Para realizar el pago mediante tarjeta de cr\u00E9dito necesita registrar los datos de esta. \r\nUna vez validados, se emitir\u00E1 un justificante de pago realizado y pasar\u00E1 a estado inscrito en la\r\ncompeteci\u00F3n.");
			textArea.setBounds(27, 21, 634, 58);
		}
		return textArea;
	}
	private JLabel getLblNumero() {
		if (lblNumero == null) {
			lblNumero = new JLabel("N\u00FAmero Tarjeta:");
			lblNumero.setBounds(21, 33, 114, 23);
			lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblNumero;
	}
	private JTextField getTxtNumero() {
		if (txtNumero == null) {
			txtNumero = new JTextField();
			txtNumero.setBounds(177, 30, 418, 31);
			txtNumero.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtNumero.setColumns(10);
		}
		return txtNumero;
	}
	
	private boolean compruebaSoloNumeros(String text) {
		String numero="";
		String minumero="";
		int textsize = text.length();
		String[] numeros= {"0","1","2","3","4","5","6","7","8","9"};
		for (int i=0;i<text.length();i++) {
			numero=text.substring(i,i+1);
			for (int j=0;j<numeros.length;j++) {
				if (numero.equals(numeros[j])) {
					minumero=minumero+numeros[j];
				}
			}
		}
		if (minumero.length()==textsize) {
			return true;
		}else
			return false;
	}
	
	private boolean fechaValida(String fecha) throws ParseException {
		SimpleDateFormat formato =new SimpleDateFormat("dd/MM/yyyy");
		
		Date fechaTarjeta = formato.parse(fecha);
		Date fechaActual = formato.parse(cambiarFormatoFecha());
		
		if (fechaTarjeta.before(fechaActual)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Comprueba si la cadena esta formada por numeros
	 * @param dni
	 * @return
	 * @throws ParseException 
	 */
	private boolean soloNumerosFecha(String fecha){
		String numero="";
		int contador =0;
		String minumero="";
		String[] posiciones = new String[fecha.length()];
		String[] numeros= {"0","1","2","3","4","5","6","7","8","9"};
		for (int i=0;i<fecha.length();i++) {
			numero=fecha.substring(i,i+1);
			for (int j=0;j<numeros.length;j++) {
				if (numero.equals(numeros[j])) {
					minumero=minumero+numeros[j];
				}
			}
			if (numero.equals("/")) {
				posiciones[i] = "/";
				contador++;
			}
		}
		
		
		if (contador==2 && minumero.length()==8) {
			if (posiciones[2] != null && posiciones[5]!=null) {
				return true;
			}else
				return false;
		}else
			return false;
				
		
	}
	
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha de Caducidad:");
			lblFecha.setBounds(21, 94, 131, 23);
			lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblFecha;
	}
	private JLabel getLblCvc() {
		if (lblCvc == null) {
			lblCvc = new JLabel("CVC:");
			lblCvc.setBounds(21, 164, 67, 23);
			lblCvc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblCvc;
	}
	private JTextField getTxtFecha() {
		if (txtFecha == null) {
			txtFecha = new JTextField();
			txtFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtFecha.setBounds(177, 92, 173, 31);
			txtFecha.setColumns(10);
		}
		return txtFecha;
	}
	private JTextField getTxtCvc() {
		if (txtCvc == null) {
			txtCvc = new JTextField();
			txtCvc.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtCvc.setBounds(98, 162, 173, 31);
			txtCvc.setColumns(10);
		}
		return txtCvc;
	}
	
	private boolean soloNumeros3(String fecha) {
		String numero="";
		String minumero="";
		String[] numeros= {"0","1","2","3","4","5","6","7","8","9"};
		for (int i=0;i<fecha.length();i++) {
			numero=fecha.substring(i,i+1);
			for (int j=0;j<numeros.length;j++) {
				if (numero.equals(numeros[j])) {
					minumero=minumero+numeros[j];
				}
			}
		}
		if  (minumero.length()==3) {
			return true;
		}else
			return false;
	}
	
	
	private JButton getBtnValidar() {
		if (btnValidar == null) {
			btnValidar = new JButton("Validar");
			btnValidar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (txtNumero.getText().equals("") && txtFecha.getText().equals("") && txtCvc.getText().equals("")) {
						mostrarMensajeVacio();
					}else {
						if (!compruebaSoloNumeros(txtNumero.getText())) {
							mostrarErrorNumero();
							txtNumero.setText("");
						} else
							try {
								if(!soloNumerosFecha(txtFecha.getText())) {
									mostrarErrorFecha();
									txtFecha.setText(""); 
								}else if(!fechaValida(txtFecha.getText())) {
									mostrarErrorFecha();
									txtFecha.setText(""); 	
								}else if(!soloNumeros3(txtCvc.getText())) {
									mostrarErrorCvc();
									txtCvc.setText("");
								}else {
									pagarInscripcion();
									btnValidar.setEnabled(false);
								}
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
					}
				
				}

				
			});
			btnValidar.setBounds(506, 198, 89, 31);
			btnValidar.setForeground(Color.WHITE);
			btnValidar.setBackground(Color.GREEN);
			btnValidar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnValidar;
	}
	private void pagarInscripcion() throws ParseException {
		JOptionPane.showMessageDialog(this, "Pago realizado correctamente, se generará un justificante de la operación.");
		String fechaString = cambiarFormatoFecha();
		inscripcion = ins.findInsByDniId(atleta.getDni(), competicion.getId());
		float cuota = sacarCuota(fechaString);
		String cadena ="";
		cadena = 
		"Datos del Atleta:"+"\n"+ 
				"\tNombre: "+atleta.getNombre() +"\n" +
				"\tSexo: "+atleta.getSexo()+"\n"
				+"\tDNI: "+atleta.getDni() + "\n"+
		"Competeción:"+ "\n" 
				+"\tNombre: "+ competicion.getNombre()+"\n"+
				"\tFecha: "+competicion.getF_comp()+"\n"+
				"\tDistancia: "+ competicion.getDistancia()+"km" + "\n"+
		"Cuota: "+cuota+"\u20AC"+"\n"+
		"Fecha de pago: "+ fechaString+"\n";
		textAreaJusti.setText(cadena);
		consultasUpdate();
		
	}
	
	@SuppressWarnings("unused")
	private float sacarCuota(String fechaString) throws ParseException {
		boolean plazo2 = false;
		boolean plazo3 = false;
		boolean plazo1 = DtoAssembler.compararFecha(competicion.getF_fin1(), fechaString, competicion.getF_inicio1());
		if (competicion.getF_fin2() != null)
			plazo2 = DtoAssembler.compararFecha(competicion.getF_fin2(), fechaString, competicion.getF_inicio2());
		if (competicion.getF_fin3() != null)
			plazo3 = DtoAssembler.compararFecha(competicion.getF_fin3(), fechaString, competicion.getF_inicio3());
		
		if (plazo3) {
			return competicion.getCuota3();
		}else if (plazo2) {
			return competicion.getCuota2();
		}else
			return competicion.getCuota1();
		
	}


	private void consultasUpdate() {
		ins.actualizarInscripcionEstado("Inscrito",atleta.getDni(),competicion.getId());
		comp.actualizarPlazas(competicion.getId());
		ins.actualizarInscripcionFecha(cambiarFormatoFecha(), atleta.getDni(), competicion.getId());
		ins.cambiarMetodoPago("tarjeta bancaria",atleta.getDni(), competicion.getId());
	}


	private String cambiarFormatoFecha() {
		String fechaString = String.valueOf(LocalDate.now());
		String[] fechaPartida = fechaString.split("-");
		String result ="";
		for (int i = 0; i < fechaPartida.length; i++) {
			result="/"+fechaPartida[i]+result;
		}
		return result.substring(1);
		
	}

	private void mostrarErrorCvc() {
		JOptionPane.showMessageDialog(this, "Error: CVC introducido incorrecto");
		
	}

	private void mostrarErrorFecha() {
		JOptionPane.showMessageDialog(this, "Error: Formato fecha incorrecta");
		
	}

	private void mostrarErrorNumero() {
		JOptionPane.showMessageDialog(this, "Error: Formato introducido incorrecto: Solo nï¿½meros porfavor");
		
	}

	private void mostrarMensajeVacio() {
		JOptionPane.showMessageDialog(this, "Error: Campos vacios");
		
	}

	private JLabel getLblFormato() {
		if (lblFormato == null) {
			lblFormato = new JLabel("dd/MM/aaaa");
			lblFormato.setBounds(385, 94, 95, 23);
			lblFormato.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lblFormato;
	}
	private JLabel getLblInfo() {
		if (lblInfo == null) {
			lblInfo = new JLabel("C\u00F3digo de 3 d\u00EDgitos que aparece en la parte trasera de la tarjeta.");
			lblInfo.setBounds(281, 164, 374, 23);
			lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		}
		return lblInfo;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Datos Tarjeta:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBackground(Color.WHITE);
			panel.setBounds(27, 111, 624, 245);
			panel.setLayout(null);
			panel.add(getLblNumero());
			panel.add(getTxtNumero());
			panel.add(getTxtFecha());
			panel.add(getLblFecha());
			panel.add(getLblFormato());
			panel.add(getLblCvc());
			panel.add(getTxtCvc());
			panel.add(getLblInfo());
			panel.add(getBtnValidar());
		}
		return panel;
	}
	private JLabel getLblJusti() {
		if (lblJusti == null) {
			lblJusti = new JLabel("Justificante de pago:");
			lblJusti.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblJusti.setBounds(27, 367, 140, 21);
		}
		return lblJusti;
	}
	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnFinalizar.setForeground(Color.WHITE);
			btnFinalizar.setBackground(Color.RED);
			btnFinalizar.setBounds(562, 601, 89, 23);
		}
		return btnFinalizar;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(27, 399, 504, 192);
			scrollPane.setViewportView(getTextArea_1_1());
		}
		return scrollPane;
	}
	private JTextArea getTextArea_1_1() {
		if (textAreaJusti == null) {
			textAreaJusti = new JTextArea();
			textAreaJusti.setEditable(false);
		}
		return textAreaJusti;
	}
}
