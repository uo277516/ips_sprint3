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

import javax.swing.JButton;
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
import javax.swing.table.DefaultTableModel;

import logica.AtletaDto;
import logica.AtletaModel;
import logica.CategoriaDto;
import logica.CategoriaModel;
import logica.CompeticionDto;
import logica.CompeticionModel;
import logica.InscripcionDto;
import logica.InscripcionModel;
import util.FileUtil;

@SuppressWarnings("serial")
public class VentanaLoteFile extends JFrame {

	private JPanel contentPane;
	private JTextArea txtrParaInscribirA;
	private JLabel lblNombreClub;
	private JTextField txtNombreClub;
	private JLabel lblNombreCom;
	private JTextField txtNombreComp;
	private JLabel lblAtletasLote;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnAnadirLote;

	private List<AtletaDto> list = new ArrayList<>();
	//private String idComp = "cfd49f81-6dd1-4991-8c46-cbc1f5e48044";
	private CompeticionModel cmodel = new CompeticionModel();
	private InscripcionModel inmodel = new InscripcionModel();
	//private CompeticionDto comp =cmodel.getCompeticionById(idComp).get(0);
	private CompeticionDto comp;
	private AtletaModel amodel = new AtletaModel();
	private JButton btnFinalizar;
	private JTextField txtFichero;
	private JLabel lblFichero;
	private JLabel lblNewLabel;
	private JButton btnValidar;
	private VentanaMostrarCarrerasClub vc;


	/**
	 * Create the frame.
	 */
	public VentanaLoteFile(VentanaMostrarCarrerasClub vc,CompeticionDto com) {
		this.comp=com;
		this.vc = vc;
		setTitle("Ventana Inscripcion Club");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 497);
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
		contentPane.add(getLblAtletasLote());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnAnadirLote());
		txtNombreComp.setText(comp.getNombre());
		txtNombreComp.setEditable(false);
		contentPane.add(getBtnFinalizar());
		contentPane.add(getTxtFichero());
		contentPane.add(getLblFichero());
		contentPane.add(getLblNewLabel());
		contentPane.add(getBtnValidar());
	}
	private JTextArea getTxtrParaInscribirA() {
		if (txtrParaInscribirA == null) {
			txtrParaInscribirA = new JTextArea();
			txtrParaInscribirA.setEditable(false);
			txtrParaInscribirA.setText("Para isncribir a los atletas debe insertar el nombre del fichero.\r\nSi no hay plazas suficientes para todos, solo se inscribir\u00E1n a los primeros corredores del lote.");
			txtrParaInscribirA.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtrParaInscribirA.setBounds(22, 11, 553, 54);
		}
		return txtrParaInscribirA;
	}
	private JLabel getLblNombreClub() {
		if (lblNombreClub == null) {
			lblNombreClub = new JLabel("Nombre del Club:");
			lblNombreClub.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNombreClub.setBounds(22, 167, 111, 14);
		}
		return lblNombreClub;
	}
	private JTextField getTxtNombreClub() {
		if (txtNombreClub == null) {
			txtNombreClub = new JTextField();
			txtNombreClub.setEditable(false);
			txtNombreClub.setBounds(143, 165, 335, 20);
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




	private void actualizarTabla() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		String[][] info = new String[list.size()][5];	
		for (int i = 0; i < list.size(); i++) {
			info[i][0] = list.get(i).getDni();
			info[i][1]= list.get(i).getNombre();
			info[i][2] = list.get(i).getF_nac();
			info [i][3] = list.get(i).getSexo();
			info[i][4] = list.get(i).getEmail();
			modelo.addRow(info[i]);
		}


		scrollPane.setViewportView(table);
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


	private JLabel getLblAtletasLote() {
		if (lblAtletasLote == null) {
			lblAtletasLote = new JLabel("Atletas en el Lote:");
			lblAtletasLote.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblAtletasLote.setBounds(22, 221, 111, 14);
		}
		return lblAtletasLote;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(22, 246, 553, 171);
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
			btnAnadirLote.setBounds(389, 428, 89, 23);
		}
		return btnAnadirLote;
	}

	private void mesnajeAtletasInsertados(int num) {
		String cadena = "Se ha rellenado la solicitud de forma correcta.\n";
		String cadenaExtra="Los siguientes atletas no tienen plaza: \n";
		int contador=0;
		if (num != list.size()) {
			for (int i = 0; i < list.size(); i++) {
				List<InscripcionDto> l = inmodel.findInscripcionByDniId(list.get(i).getDni(),comp.getId());
				if (l.isEmpty()) {
					contador++;
					cadenaExtra+=list.get(i).getNombre() + " DNI: "+ list.get(i).getDni()+"\n";
				}
			}
			if (contador!=0) {
				cadena +=cadenaExtra;
			}
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
				List<AtletaDto> atDni = amodel.atletaYaEnBaseDatosDni(list.get(i).getDni());
				//List<AtletaDto> atEmail = amodel.atletaYaRegistradoEnLaBase(list.get(i).getEmail());
				if (atDni.isEmpty()) {
					amodel.addAtleta(list.get(i).getDni(), list.get(i).getNombre(),list.get(i).getSexo(), list.get(i).getF_nac(),list.get(i).getEmail());
				}
				//insertar inscripcion
				List<InscripcionDto> auxDni = inmodel.findInscripcionByDniId(list.get(i).getDni(), comp.getId());
				List<InscripcionDto> auxEmail = inmodel.findInscripcionByEmailId(list.get(i).getEmail(), comp.getId());
				if (auxDni.isEmpty() && auxEmail.isEmpty()) {
					inmodel.insertarInscripcionClub(list.get(i).getDni(),comp.getId(),categoria,list.get(i).getEmail(),cambiarForFecha(),"por_club",
							cuota,"CLUB",getTxtNombreClub().getText());
					cmodel.actualizarPlazas(comp.getId());
					contador++;
				}else {
					JOptionPane.showMessageDialog(this, "DNI "+list.get(i).getDni()+" ya insertado en la competicion.");
				}


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
	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					volverInicio();
				}
			});
			btnFinalizar.setForeground(Color.WHITE);
			btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnFinalizar.setEnabled(false);
			btnFinalizar.setBackground(Color.RED);
			btnFinalizar.setBounds(486, 428, 89, 23);
			btnFinalizar.setEnabled(false);
		}
		return btnFinalizar;
	}
	
	private void volverInicio() {
		vc.getVentanaInicial().setVisible(true);
		this.dispose();
		
	}
	
	private JTextField getTxtFichero() {
		if (txtFichero == null) {
			txtFichero = new JTextField();
			txtFichero.setBounds(97, 120, 189, 20);
			txtFichero.setColumns(10);
		}
		return txtFichero;
	}
	private JLabel getLblFichero() {
		if (lblFichero == null) {
			lblFichero = new JLabel("Fichero:");
			lblFichero.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblFichero.setBounds(22, 122, 65, 14);
		}
		return lblFichero;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel(".txt");
			lblNewLabel.setBounds(296, 123, 49, 14);
		}
		return lblNewLabel;
	}
	private JButton getBtnValidar() {
		if (btnValidar == null) {
			btnValidar = new JButton("Validar");
			btnValidar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String nombreEquipo="";
					nombreEquipo=FileUtil.loadFileTicket(getTxtFichero().getText(), list);

					txtNombreClub.setText(nombreEquipo);
					txtNombreClub.setEditable(false);
					if (!nombreEquipo.equals("")) {
						btnValidar.setEnabled(false);
					}
					comprobarAtletasYaEnCarrera();
					actualizarTabla();

					btnAnadirLote.setEnabled(true);


				}


			});
			btnValidar.setForeground(Color.WHITE);
			btnValidar.setBackground(Color.GREEN);
			btnValidar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnValidar.setBounds(336, 119, 89, 23);
		}
		return btnValidar;
	}

	private void comprobarAtletasYaEnCarrera() {
		for (int i = 0; i < list.size(); i++) {
			if (atletaYaEnCarrera(list.get(i).getDni())) {
				mostrarAtletaYaEnCarrera(list.get(i).getDni());
				list.remove(i);
			}
		}

	}

	private boolean atletaYaEnCarrera(String dni) {
		List<InscripcionDto> aux = inmodel.findInscripcionByDniId(dni, comp.getId());
		if (aux.isEmpty())
			return false;
		return true;
	}

	private void mostrarAtletaYaEnCarrera(String dni) {
		JOptionPane.showMessageDialog(this, "Atleta con DNI "+dni+" ya registrado en la competicion.");
	}
}
