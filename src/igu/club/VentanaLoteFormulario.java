package igu.club;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.AtletaDto;
import logica.AtletaModel;
import logica.CategoriaDto;
import logica.CategoriaModel;
import logica.CompeticionDto;
import logica.CompeticionModel;
import logica.InscripcionDto;
import logica.InscripcionModel;

@SuppressWarnings("serial")
public class VentanaLoteFormulario extends JFrame {

	private JPanel contentPane;
	private JTextArea txtrParaInscribirA;
	private JLabel lblNombreClub;
	private JTextField txtNombreClub;
	private JLabel lblNombreCom;
	private JTextField txtNombreComp;
	private JPanel panel;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblDni;
	private JTextField txtDni;
	private JLabel lblFecha;
	private JTextField txtFecha;
	private JLabel lblSexo;
	private JComboBox<String> comSexo;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JButton btnValidar;
	private JLabel lblAtletasLote;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnAnadirLote;
	private JLabel lblNewLabel;

	private List<AtletaDto> list = new ArrayList<>();
	//private String idComp = "cfd49f81-6dd1-4991-8c46-cbc1f5e48044";
	private CompeticionModel cmodel = new CompeticionModel();
	private InscripcionModel inmodel = new InscripcionModel();
	//private CompeticionDto comp =cmodel.getCompeticionById(idComp).get(0);
	private CompeticionDto comp;
	private AtletaModel amodel = new AtletaModel();
	private JButton btnFinalizar;

	/**
	 * Create the frame.
	 */
	public VentanaLoteFormulario(CompeticionDto com) {
		this.comp=com;
		setTitle("Ventana Inscripcion Club");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 666);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		contentPane.add(getTxtrParaInscribirA());
		contentPane.add(getLblNombreClub());
		contentPane.add(getTxtNombreClub());
		contentPane.add(getLblNombreCom());
		contentPane.add(getTxtNombreComp());
		contentPane.add(getPanel());
		contentPane.add(getLblAtletasLote());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnAnadirLote());
		txtNombreComp.setText(comp.getNombre());
		txtNombreComp.setEditable(false);
		contentPane.add(getBtnFinalizar());
	}
	private JTextArea getTxtrParaInscribirA() {
		if (txtrParaInscribirA == null) {
			txtrParaInscribirA = new JTextArea();
			txtrParaInscribirA.setEditable(false);
			txtrParaInscribirA.setText("Para inscribir a los corredores en la competicion, primero de el nombre del club.\r\nUna vez registrado deber\u00E1 rellenar los datos de los corredores.\r\nSi no hay plazas suficientes para todos, solo se inscribir\u00E1n a los primeros corredores del lote.");
			txtrParaInscribirA.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtrParaInscribirA.setBounds(22, 11, 553, 54);
		}
		return txtrParaInscribirA;
	}
	private JLabel getLblNombreClub() {
		if (lblNombreClub == null) {
			lblNombreClub = new JLabel("Nombre del Club:");
			lblNombreClub.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNombreClub.setBounds(22, 114, 111, 14);
		}
		return lblNombreClub;
	}
	private JTextField getTxtNombreClub() {
		if (txtNombreClub == null) {
			txtNombreClub = new JTextField();
			txtNombreClub.setBounds(143, 112, 335, 20);
			txtNombreClub.setColumns(10);
		}
		return txtNombreClub;
	}
	private JLabel getLblNombreCom() {
		if (lblNombreCom == null) {
			lblNombreCom = new JLabel("Nombre de la competicion:");
			lblNombreCom.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNombreCom.setBounds(22, 76, 160, 14);
		}
		return lblNombreCom;
	}
	private JTextField getTxtNombreComp() {
		if (txtNombreComp == null) {
			txtNombreComp = new JTextField();
			txtNombreComp.setColumns(10);
			txtNombreComp.setBounds(192, 76, 335, 20);
		}
		return txtNombreComp;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Datos Atletas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBackground(Color.WHITE);
			panel.setBounds(24, 154, 551, 225);
			panel.setLayout(null);
			panel.add(getLblNombre());
			panel.add(getTxtNombre());
			panel.add(getLblDni());
			panel.add(getTxtDni());
			panel.add(getLblFecha());
			panel.add(getTxtFecha());
			panel.add(getLblSexo());
			panel.add(getComSexo());
			panel.add(getLblEmail());
			panel.add(getTxtEmail());
			panel.add(getBtnValidar());
			panel.add(getLblNewLabel());
		}
		return panel;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre y Apellidos:");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNombre.setBounds(10, 32, 125, 14);
		}
		return lblNombre;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(145, 30, 327, 20);
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI:");
			lblDni.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDni.setBounds(10, 66, 49, 14);
		}
		return lblDni;
	}
	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.setBounds(69, 64, 146, 20);
			txtDni.setColumns(10);
		}
		return txtDni;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha Nacimiento:");
			lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblFecha.setBounds(10, 97, 110, 14);
		}
		return lblFecha;
	}
	private JTextField getTxtFecha() {
		if (txtFecha == null) {
			txtFecha = new JTextField();
			txtFecha.setColumns(10);
			txtFecha.setBounds(145, 95, 146, 20);
		}
		return txtFecha;
	}
	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo:");
			lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblSexo.setBounds(10, 133, 49, 14);
		}
		return lblSexo;
	}
	private JComboBox<String> getComSexo() {
		if (comSexo == null) {
			comSexo = new JComboBox<String>();
			comSexo.setModel(new DefaultComboBoxModel<String>(new String[] {"masculino", "femenino"}));
			comSexo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			comSexo.setBounds(69, 129, 146, 22);
		}
		return comSexo;
	}
	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email:");
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblEmail.setBounds(10, 168, 49, 14);
		}
		return lblEmail;
	}
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setBounds(69, 166, 222, 20);
			txtEmail.setColumns(10);
		}
		return txtEmail;
	}
	private JButton getBtnValidar() {
		if (btnValidar == null) {
			btnValidar = new JButton("Validar");
			btnValidar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (txtNombre.getText().equals("") || getTxtNombreClub().getText().equals("") || 
							txtNombre.getText().equals("") || txtDni.getText().equals("")|| txtEmail.getText().equals("") || txtFecha.getText().equals("")) {
						mostrarErroVacios();
					}else if (!compruebaSoloNumeros(getTxtDni().getText())) {
						mostrarErrorNumeros();
						txtDni.setText("");
					}else if(!fechaValida(txtFecha.getText())) {
						mostrarErrorFecha();
						txtFecha.setText("");
					}else if(atletaYaEnCarrera()) {
						mostrarAtletaYaEnCarrera(getTxtDni().getText());
						borrarTodosTxt();
					}else {
						mostrarAtletaInsertado(getTxtDni().getText());
						actualizarTabla();
						borrarTodosTxt();
						txtNombreClub.setEditable(false);
						txtNombreComp.setEditable(false);
						btnAnadirLote.setEnabled(true);
					}
				}
			});
			btnValidar.setForeground(Color.WHITE);
			btnValidar.setBackground(Color.GREEN);
			btnValidar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnValidar.setBounds(452, 191, 89, 23);
		}
		return btnValidar;
	}

	private boolean atletaYaEnCarrera() {
		List<InscripcionDto> aux = inmodel.findInscripcionByDniId(getTxtDni().getText(), comp.getId());
		if (aux.isEmpty())
			return false;
		return true;
	}

	private void mostrarAtletaYaEnCarrera(String dni) {
		JOptionPane.showMessageDialog(this, "Atleta con DNI "+dni+" ya registrado en la competicion.");
	}

	private void borrarTodosTxt() {
		txtNombre.setText("");
		txtEmail.setText("");
		txtFecha.setText("");
		txtDni.setText("");
	}

	private void actualizarTabla() {
		AtletaDto atleta = new AtletaDto();
		atleta.setDni(getTxtDni().getText());
		atleta.setNombre(getTxtNombre().getText());
		atleta.setSexo(comSexo.getSelectedItem().toString());
		atleta.setEmail(getTxtEmail().getText());
		atleta.setF_nac(getTxtFecha().getText());
		list.add(atleta);
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		String[][] info = new String[1][5];	
		int lastSize = list.size()-1;
		info[0][0] = list.get(lastSize).getDni();
		info[0][1]= list.get(lastSize).getNombre();
		info[0][2] = list.get(lastSize).getF_nac();
		info [0][3] = list.get(lastSize).getSexo();
		info[0][4] = list.get(lastSize).getEmail();
		modelo.addRow(info[0]);

		scrollPane.setViewportView(table);
	}



	private void mostrarAtletaInsertado(String text) {
		JOptionPane.showMessageDialog(this, "Atleta con DNI "+text+" insertado correctamente.");

	}

	private void mostrarErrorFecha() {
		JOptionPane.showMessageDialog(this, "Error: Fecha incorrecta.");

	}

	private boolean fechaValida(String fecha){
		SimpleDateFormat formato =new SimpleDateFormat("dd/MM/yyyy");

		Date fechaTarjeta=null;
		try {
			fechaTarjeta = formato.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date fechaActual=null;
		try {
			fechaActual = formato.parse(cambiarFormatoFecha());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (fechaTarjeta.after(fechaActual)) {
			return false;
		}
		return true;
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

	private void mostrarErrorNumeros() {
		JOptionPane.showMessageDialog(this, "Error: El dni solo puede estar formado por numeros.");

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

	private void mostrarErroVacios() {
		JOptionPane.showMessageDialog(this, "Error: Los campos de texto deben estar rellenados.");

	}
	private JLabel getLblAtletasLote() {
		if (lblAtletasLote == null) {
			lblAtletasLote = new JLabel("Atletas en el Lote:");
			lblAtletasLote.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblAtletasLote.setBounds(22, 390, 111, 14);
		}
		return lblAtletasLote;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(22, 415, 553, 171);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {

			table = new JTable();
			table.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
			table.setFont(new Font("Tahoma", Font.PLAIN, 13));
			table.setSelectionBackground(Color.YELLOW);
			table.setBackground(Color.LIGHT_GRAY);
			DefaultTableModel modelo = new DefaultTableModel() {
				public boolean isCellEditable(int fila, int columnas) {
					if (columnas == 5)
						return true;
					return false;

				}
			};
			table.setModel(modelo);
			modelo.addColumn("DNI");modelo.addColumn("Nombre");modelo.addColumn("Fecha Nac");modelo.addColumn("Sexo");modelo.addColumn("Email");
		}

		return table;
	}
	private JButton getBtnAnadirLote() {
		if (btnAnadirLote == null) {
			btnAnadirLote = new JButton("A\u00F1adir");
			btnAnadirLote.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int numAtletasInsertados = insertarAtletasIns();
					btnAnadirLote.setEnabled(false);
					btnValidar.setEnabled(false);
					btnFinalizar.setEnabled(true);
					mesnajeAtletasInsertados(numAtletasInsertados);
					
				}
			});
			btnAnadirLote.setEnabled(false);
			btnAnadirLote.setForeground(Color.WHITE);
			btnAnadirLote.setBackground(Color.GREEN);
			btnAnadirLote.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnAnadirLote.setBounds(388, 597, 89, 23);
		}
		return btnAnadirLote;
	}
	
	private void mesnajeAtletasInsertados(int num) {
		String cadena = "Se ha rellenado la solicitud de forma correcta.\n";
		String cadenaExtra="Los siguientes atletas no tienen plaza: \n";
		if (num != list.size()) {
			for (int i = num; i < list.size(); i++) {
				cadenaExtra+=list.get(i).getDni()+"\n";
			}
			cadena +=cadenaExtra;
			JOptionPane.showMessageDialog(this,cadena );
		}else {
			JOptionPane.showMessageDialog(this,cadena );
		}
		
	}

	private int insertarAtletasIns() {
		int plazas1=0;
		int contador=0;
		String categoria="";
		float cuota =  10.f + cogerCuotaSegunFecha();
		for (int i = 0; i < list.size(); i++) {
			plazas1 = cmodel.findNumPlazas(comp.getId());
			if (plazas1 >0) {
				categoria = calcularCategoria(list.get(i).getF_nac(), comp.getId(), list.get(i).getSexo());
				//insertar atleta
				List<AtletaDto> at = amodel.atletaYaEnBaseDatosDni(list.get(i).getDni());
				if (at.isEmpty()) {
					amodel.addAtleta(list.get(i).getDni(), list.get(i).getNombre(),list.get(i).getSexo(), list.get(i).getF_nac(),list.get(i).getEmail());
				}
				//insertar inscripcion
				inmodel.insertarInscripcionClub(list.get(i).getDni(),comp.getId(),categoria,list.get(i).getEmail(),cambiarForFecha(),"por_club",
						cuota,"CLUB",getTxtNombreClub().getText());
				//restarPlazas
				cmodel.reducirPlazas(comp.getId());
				contador++;
			}else {
				break;
			}
		}
		return contador;

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
			if (comp.getF_inicio1() != null) {
				fechaInicio1 = formato.parse(comp.getF_inicio1());
				fechaFin1 = formato.parse(comp.getF_fin1());
			}
			if (comp.getF_inicio2() != null) {
				fechaInicio2 = formato.parse(comp.getF_inicio2());
				fechaFin2 = formato.parse(comp.getF_fin2());
			}
			if (comp.getF_inicio3() != null) {
				fechaInicio3 = formato.parse(comp.getF_inicio3());
				fechaFin3 = formato.parse(comp.getF_fin3());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (comp.getF_inicio1() != null) {
			if (fechaActual.before(fechaFin1) && fechaActual.after(fechaInicio1)) {
				return comp.getCuota1();
			}
		} else if (comp.getF_inicio2() != null) {
			if (fechaActual.before(fechaFin2) && fechaActual.after(fechaInicio2)) {
				return comp.getCuota2();
			}
		} else if (comp.getF_inicio3() != null) {
			if (fechaActual.before(fechaFin3) && fechaActual.after(fechaInicio3)) {
				return comp.getCuota3();
			}
		}
		return -600;
	}

	
	private String calcularCategoria(String fecha, String idComp, String sexo) {
		CategoriaModel cm = new CategoriaModel();
		List<CategoriaDto> categorias = cm.findCateBySex(idComp, sexo);

		String[] fechaArray = fecha.split("/");
		int year = Integer.valueOf(fechaArray[2]);
		int yearActual = LocalDate.now().getYear();
		int cat = yearActual - year;

		for (CategoriaDto c : categorias) {
			if (c.getEdad_min() <= cat && cat <= c.getEdad_max()) {
				return c.getNombre();
			}
		}
		return null;
	}

	private String cambiarForFecha() {
		String fechaString = String.valueOf(LocalDate.now());
		String[] fechaPartida = fechaString.split("-");
		String result = "";
		for (int i = 0; i < fechaPartida.length; i++) {
			result = "/" + fechaPartida[i] + result;
		}
		return result.substring(1);

	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("dd/MM/aaaa");
			lblNewLabel.setBounds(316, 98, 75, 14);
		}
		return lblNewLabel;
	}
	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnFinalizar.setForeground(Color.WHITE);
			btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnFinalizar.setEnabled(false);
			btnFinalizar.setBackground(Color.RED);
			btnFinalizar.setBounds(487, 599, 89, 23);
			btnFinalizar.setEnabled(false);
		}
		return btnFinalizar;
	}
}
