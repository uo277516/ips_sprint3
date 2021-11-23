package igu.atleta;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

import igu.VentanaInicial;
import logica.CompeticionDto;
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
	private JButton btnCancelar;
	private JButton btnFinalizar;
	
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
		setBounds(100, 100, 664, 518);
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
		contentPane.add(getBtnCancelar());
		contentPane.add(getBtnFinalizar());
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
					btnCancelar.setEnabled(true);
					btnFinalizar.setEnabled(true);
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
//			table.setEnabled(false);
			DefaultTableModel modelo = new DefaultTableModel();
			table.setModel(modelo);
			modelo.addColumn("ID comp");
			modelo.addColumn("Nombre");
			modelo.addColumn("Estado");
			modelo.addColumn("Fecha último cambio");
			String[][] info = new String[insAtleta.size()][4];
			for(int i = 0; i < insAtleta.size(); i++) {
				info[i][0] = insAtleta.get(i).getId_c();
				info[i][1] = String.valueOf(cm.getCompeticionById(insAtleta.get(i).getId_c()).get(0).getNombre());
				info[i][2] = insAtleta.get(i).getEstado();
				info[i][3] = insAtleta.get(i).getFecha();
				modelo.addRow(info[i]);
			}
		
		}
		return table;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar inscripci\u00F3n");
			btnCancelar.setEnabled(false);
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancelarInscripcion();
				}
			});
			btnCancelar.setBackground(new Color(255, 255, 51));
			btnCancelar.setForeground(Color.BLACK);
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnCancelar.setBounds(30, 439, 178, 21);
		}
		return btnCancelar;
	}
	
	protected void cancelarInscripcion() {
		if (table.getSelectedRow() != -1) {
			CompeticionDto compe = cm.getCompeticionById(insAtleta.get(table.getSelectedRow()).getId_c()).get(0);
			InscripcionDto ins = insAtleta.get(table.getSelectedRow());
			
			if (compe.getHay_politica()==1 && compararFecha(compe.getF_canc()) ) { //si tiene política y no se pasó la fecha de cancelación 
				double dinero = (compe.getP_cuota_canc()/100)*ins.getCantidad_pagada();
				
				if ( ins.getEstado().toUpperCase().equals("INSCRITO") )
				{
					
					System.out.println("entra");
		            im.cancelarInscripcionPagada(ins.getDni_a(), ins.getId_c());
		            cm.reducirPlazas(ins.getId_c());
		    		JOptionPane.showMessageDialog(this, "Se ha desinscrito de la competición. A continuación, se imprimirá un justificante con la siguiente información:"
		    				+ "\n    Competición: "  + compe
		    				+ "\n    Dinero a devolver: " + dinero);
	
				}
				else if (ins.getEstado().toUpperCase().equals("CANCELADO") || 
						ins.getEstado().toUpperCase().equals("CANCELADO-PENDIENTE DE DEVOLUCION"))
				{
		    		JOptionPane.showMessageDialog(this, "Usted ya se ha desinscrito de esta competición");

				}
				else {
		            im.cancelarInscripcion(ins.getDni_a(), ins.getId_c());
		            cm.reducirPlazas(ins.getId_c());
		    		JOptionPane.showMessageDialog(this, "Se ha desinscrito de la competición. A continuación, se imprimirá un justificante con la siguiente información:"
		    				+ "\n     Competición: "  + compe
		    				+ "\n     Dinero a devolver " + dinero );
				}
			}
			else {
				 JOptionPane.showMessageDialog(this, "Error: No se puede cancelar su inscripción.");
			}
		}else {
            errorNoCarreraSeleccionada();
        }
		
	}
	
	private boolean compararFecha(String fecha) {
		// TODO Auto-generated method stub
		
		
		String[] cosas = fecha.split("/");
		cosas[0] = String.valueOf(Integer.parseInt(cosas[0]) + 1);
		String f = cosas[0] + "/" + cosas[1] + "/" + cosas[2];
		
		SimpleDateFormat formato =new SimpleDateFormat("dd/MM/yyyy");
		Date fechaFinCancelacion = null;
		Date fechaHoy = null;
		
		
		try {
			fechaFinCancelacion = formato.parse(f);
			fechaHoy = formato.parse(cambiarFormatoFecha());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (fechaHoy.before(fechaFinCancelacion))
			return true;
		else
			return false;
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

	protected void errorNoCarreraSeleccionada() {
		 JOptionPane.showMessageDialog(this, "Error: Seleccione una carrera para registrarse");
	}

	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.setForeground(Color.WHITE);
			btnFinalizar.setEnabled(false);
			btnFinalizar.setBackground(new Color(255, 0, 0));
			btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnFinalizar.setBounds(555, 450, 85, 21);
		}
		return btnFinalizar;
	}
}
