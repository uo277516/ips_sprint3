package igu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.AtletaModel;
import logica.CompeticionDto;
import logica.CompeticionModel;
import logica.InscripcionDto;
import logica.InscripcionModel;

public class VentanaAsignarDorsales extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFecha;
	private InscripcionModel ins;
	@SuppressWarnings("unused")
	private AtletaModel atl;
	private CompeticionModel comp;
	private JButton btnAsignar;
	private JLabel lblCompeticiones;
	private JScrollPane scrollPane;
	private JTable table;
	@SuppressWarnings("unused")
	private VentanaInicial vi;
	private JLabel lblInfo;

//		/**
//		 * Launch the application.
//		 */
//		public static void main(String[] args) {
//			EventQueue.invokeLater(new Runnable() {
//				public void run() {
//					try {
//						VentanaMostrarCarreras frame = new VentanaMostrarCarreras();
//						frame.setVisible(true);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			});
//		}

	/**
	 * Create the frame.
	 * @param vI 
	 * @throws ParseException 
	 */
	public VentanaAsignarDorsales(VentanaInicial vI) {
		this.vi=vI;
		ins = new InscripcionModel();
		atl = new AtletaModel();
		comp = new CompeticionModel();
		setTitle("Selecci\u00F3n de Competici\u00F3n:");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 521);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTextFecha());
		contentPane.add(getBtnAsignar());
		contentPane.add(getLblCompeticiones());
		contentPane.add(getScrollPane());
		contentPane.add(getLblInfo());
	}
	private JTextField getTextFecha() {
		if (textFecha == null) {
			textFecha = new JTextField();
			textFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
			textFecha.setEditable(false);
			textFecha.setBounds(392, 45, 86, 20);
			textFecha.setColumns(10);
			textFecha.setText(cambiarFormatoFecha());
			//cambiarFormatoFecha();
		}
		return textFecha;
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
	private JButton getBtnAsignar() {
		if (btnAsignar == null) {
			btnAsignar = new JButton("Asignar dorsales");
			btnAsignar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println(table.getSelectedRowCount());
					if (table.getSelectedRowCount()>1) {
						mostrarErrorSoloUna();
					}
					else if (table.getSelectedRow() != -1) {
						asignarDorsales();
					}  
					else {
						errorNoCarreraSeleccionada();
					}
				}
			});
			btnAsignar.setBackground(Color.GREEN);
			btnAsignar.setForeground(Color.WHITE);
			btnAsignar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnAsignar.setBounds(507, 440, 159, 23);
		}
		return btnAsignar;
	}

	protected void mostrarErrorSoloUna() {
		JOptionPane.showMessageDialog(this, "Error: Solo puede seleccionar una competicion de cada vez.");


	}
	protected void asignarDorsales() {

		int fila = table.getSelectedRow();
		String identificador = (String) table.getValueAt(fila,0);

		String c= (String) table.getValueAt(fila, 5);
		int contador = Integer.parseInt(c);
		List<InscripcionDto> inscripcionesDeEsaCompe = ins.getInscripcionesDeUnaCompeticion(identificador);
		CompeticionDto compe = comp.getCompeticionById(identificador).get(0);
		for (InscripcionDto i : inscripcionesDeEsaCompe) 
		{
			if (compe.getNum_plazas()>=contador) {
				ins.asignarDorsalesInsConIdComp(i.getDni_a(), i.getId_c(), contador);
				contador++;
			}
		}
		JOptionPane.showMessageDialog(this, "Los dorsales han sido asignados correctamente.");

		//List<CompeticionDto> compe = comp.getCompeticionById(identificador);
	}

	protected void errorNoCarreraSeleccionada() {
		JOptionPane.showMessageDialog(this, "Error: Seleccione una carrera para asignarle los dorsales");
	}

	//	protected void pasarAInscripcion() throws SQLException {
	//		this.dispose();
	//		CompeticionDto competicion = crearCompeticion();
	//		VentanaInscripcion vPal = new VentanaInscripcion(this, competicion);
	//		vPal.setLocationRelativeTo(this);
	//		vPal.setVisible(true);
	//	}

	//	private CompeticionDto crearCompeticion() throws SQLException {
	//		int fila = table.getSelectedRow();
	//		String identificador = (String) table.getValueAt(fila,0);
	//		System.out.println(identificador);
	//		List<CompeticionDto> compe = comp.getCompeticionById(identificador);
	//		// TODO Auto-generated method stub
	//		return compe.get(0);
	//	}

	private JLabel getLblCompeticiones() {
		if (lblCompeticiones == null) {
			lblCompeticiones = new JLabel("Competiciones con todos plazos cerrados actualmente a d\u00EDa:");
			lblCompeticiones.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblCompeticiones.setBounds(24, 49, 358, 13);
		}
		return lblCompeticiones;
	}
	private JScrollPane getScrollPane(){
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 148, 656, 271);
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
			DefaultTableModel modelo = new DefaultTableModel();
			table.setModel(modelo);
			modelo.addColumn("ID");modelo.addColumn("Nombre");modelo.addColumn("Fecha Competición");modelo.addColumn("Tipo");modelo.addColumn("Distancia");modelo.addColumn("Dorsales vip");

			List<CompeticionDto> competiciones = comp.getListaCompCerradas(cambiarFormatoFecha());
			String[][] info = new String[competiciones.size()][6];
			//List<AtletaDto> atletas = getAtletas();
			//List<InscripcionDto> inscripciones = getInscripciones();
			for(int i = 0; i < competiciones.size(); i++) {
				info[i][0] = competiciones.get(i).getId();
				info[i][1] = competiciones.get(i).getNombre();info[i][2] = competiciones.get(i).getF_comp();
				info[i][3] = competiciones.get(i).getTipo();info[i][4] = competiciones.get(i).getDistancia()+"km";
				info[i][5] = String.valueOf(competiciones.get(i).getDorsales_vip());
				modelo.addRow(info[i]);
			}
		}

		return table;
	}



	//	private String sacarFechaFin(CompeticionDto competicionDto) {
	//		if (competicionDto.getF_fin3() != null)
	//			return competicionDto.getF_fin3();
	//		else if (competicionDto.getF_fin2() != null)
	//			return competicionDto.getF_fin2();
	//		
	//		return competicionDto.getF_fin1();
	//	}




	private JLabel getLblInfo() {
		if (lblInfo == null) {
			lblInfo = new JLabel("Seleccione la carrera para asignar los dorsales de sus competidores:");
			lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblInfo.setBounds(24, 105, 431, 33);
		}
		return lblInfo;
	}
}
