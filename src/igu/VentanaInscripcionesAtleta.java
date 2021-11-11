package igu;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.CompeticionModel;
import logica.InscripcionDto;
import logica.InscripcionModel;

public class VentanaInscripcionesAtleta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPreguntar;
	private JLabel lblPreguntar2;
	private JPanel panel;
	private JRadioButton radioDni;
	private JRadioButton radioEmail;
	private final ButtonGroup gr = new ButtonGroup();
	private JButton btnSig;
	private JLabel lblDni;
	private JTextField txtDNI;
	private JLabel lblEmail;
	private JButton btnMostrar;
	private InscripcionModel im;
	private List<InscripcionDto> insAtleta;
	private CompeticionModel cm;
	@SuppressWarnings("unused")
	private VentanaInicial vi;
	private JScrollPane scrollPane;
	@SuppressWarnings("unused")
	private DefaultListModel<String> listaINs;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaInscripcionesAtleta frame = new VentanaInscripcionesAtleta();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public VentanaInscripcionesAtleta(VentanaInicial vi) {
		this.vi=vi;
		im= new InscripcionModel();
		cm = new CompeticionModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 498);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblPreguntar());
		contentPane.add(getLblPreguntar2());
		contentPane.add(getPanel());
		contentPane.add(getBtnSig());
		contentPane.add(getLblDni());
		contentPane.add(getTxtDNI());
		contentPane.add(getLblEmail());
		contentPane.add(getBtnMostrar());
//		contentPane.add(getScrollPane());
		//contentPane.add(getList());
	}

	private JLabel getLblPreguntar() {
		if (lblPreguntar == null) {
			lblPreguntar = new JLabel("Para conocer el estado de sus inscripciones, elija el dato personal ");
			lblPreguntar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblPreguntar.setBounds(21, 43, 399, 22);
		}
		return lblPreguntar;
	}
	private JLabel getLblPreguntar2() {
		if (lblPreguntar2 == null) {
			lblPreguntar2 = new JLabel("que prefiere seleccionar y rellene el campo:\r\n");
			lblPreguntar2.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblPreguntar2.setBounds(21, 64, 404, 13);
		}
		return lblPreguntar2;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Elija la opción", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBackground(Color.WHITE);
			panel.setBounds(21, 103, 307, 63);
			panel.add(getRadioDni());
			panel.add(getRadioEmail());
		}
		return panel;
	}
	private JRadioButton getRadioDni() {
		if (radioDni == null) {
			radioDni = new JRadioButton("DNI");
			radioDni.setFont(new Font("Tahoma", Font.PLAIN, 13));
			radioDni.setBounds(29, 23, 103, 21);
			gr.add(radioDni);
		}
		return radioDni;
	}
	private JRadioButton getRadioEmail() {
		if (radioEmail == null) {
			radioEmail = new JRadioButton("Email");
			radioEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
			radioEmail.setBounds(164, 23, 103, 21);
			gr.add(radioEmail);
		}
		return radioEmail;
	}
	private JButton getBtnSig() {
		if (btnSig == null) {
			btnSig = new JButton("Siguiente");
			btnSig.setBackground(SystemColor.inactiveCaption);
			btnSig.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarParaIntroducir();
				}
			});
			btnSig.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnSig.setBounds(382, 123, 110, 21);
		}
		return btnSig;
	}
	
	protected void mostrarParaIntroducir() {
		if (!radioDni.isSelected() && !radioEmail.isSelected())
		{
			JOptionPane.showMessageDialog(this, "Tienes que seleccionar una opción");
		} else {
			radioDni.setEnabled(false);
			radioEmail.setEnabled(false);
			if (radioDni.isSelected()) {
				lblDni.setVisible(true);
			}
			else {
				lblEmail.setVisible(true);
			}
		txtDNI.setEnabled(true);
		btnMostrar.setEnabled(true);
		}
		
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI:");
			lblDni.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDni.setBounds(33, 188, 45, 13);
			lblDni.setVisible(false);
		}
		return lblDni;
	}
	private JTextField getTxtDNI() {
		if (txtDNI == null) {
			txtDNI = new JTextField();
			txtDNI.setBounds(88, 183, 160, 22);
			txtDNI.setColumns(10);
			txtDNI.setEnabled(false);
		}
		return txtDNI;
	}
	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email:");
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblEmail.setBounds(33, 188, 45, 13);
			lblEmail.setVisible(false);
		}
		return lblEmail;
	}
	private JButton getBtnMostrar() {
		if (btnMostrar == null) {
			btnMostrar = new JButton("Mostrar inscripciones");
			btnMostrar.setEnabled(false);
			btnMostrar.setBackground(new Color(124, 252, 0));
			btnMostrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarInscripciones();
				}
			});
			btnMostrar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnMostrar.setBounds(316, 184, 160, 21);
		}
		return btnMostrar;
	}

	protected void mostrarInscripciones() {
		if (txtDNI.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this, "Debes rellenar el campo");
		} else {
			buscarInscripciones();
		}
	}

	private void buscarInscripciones() {
		if (radioDni.isSelected()) {
			//buscardni
			System.out.println("dni");
			insAtleta=im.buscarInsByDni(txtDNI.getText());
		}
		else if (radioEmail.isSelected())
		{
			//buscaremail
			System.out.println("email");
			insAtleta=im.buscarInsByEmail(txtDNI.getText());
		}
//		rellenarElTexto();
		contentPane.add(getScrollPane());
		mostrarInfo();
		
	}

//	private void rellenarElTexto() {
//		for(int i=0; i<insAtleta.size(); i++)
//		{
//			listaINs.addElement(insAtleta.get(i).mostrarMisInscripcionesNombre(cm.getCompeticionById(insAtleta.get(i).getId_c()).get(0).getNombre()));
//			listaINs.addElement(insAtleta.get(i).mostrarMisInscripcionesEstado());
//			listaINs.addElement(insAtleta.get(i).mostrarMisInscripcionesFecha());
//
//		}
//	}

	private void mostrarInfo() {
		scrollPane.setEnabled(true);
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setEnabled(false);
			scrollPane.setBounds(30, 237, 498, 181);
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
			table.setEnabled(false);
			DefaultTableModel modelo = new DefaultTableModel();
			table.setModel(modelo);
			modelo.addColumn("Nombre");
			modelo.addColumn("Estado");
			modelo.addColumn("Fecha último cambio");
			String[][] info = new String[insAtleta.size()][3];
			for(int i = 0; i < insAtleta.size(); i++) {
				info[i][0] = String.valueOf(cm.getCompeticionById(insAtleta.get(i).getId_c()).get(0).getNombre());
				info[i][1] = insAtleta.get(i).getEstado();
				info[i][2] = insAtleta.get(i).getFecha();
				modelo.addRow(info[i]);
			}
		
		}
		return table;
	}
}
