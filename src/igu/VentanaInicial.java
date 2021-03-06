package igu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import igu.atleta.VentanaInscripcionesAtleta;
import igu.atleta.VentanaMostrarCarreras;
import igu.club.VentanaMostrarCarrerasClub;
import igu.organizador.VentanaAsignarDorsales;
import igu.organizador.VentanaCrearCompeticion;
import igu.organizador.VentanaMostrarCarrerasOrganizador;
import logica.competicion.CompeticionModel;
import logica.competicion.MarcaTiempo;

public class VentanaInicial extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFileChooser chooser;
	private JButton btnClub;

	private int ndatos;
	private String competicionId;
	private CompeticionModel cm;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicial frame = new VentanaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaInicial() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicial.class
				.getResource("/img/icono-plano-de-la-bandera-carreras-con-sombra-larga-colorido-198376094.jpg")));
		ndatos = 0;
		cm = new CompeticionModel();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 413);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPregunat = new JLabel("\u00BFDesea entrar como atleta o como organizador?");
		lblPregunat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPregunat.setBounds(119, 220, 302, 52);
		contentPane.add(lblPregunat);

		JButton btnAtleta = new JButton("Atleta");
		btnAtleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elegirAsAtleta();
			}
		});
		btnAtleta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtleta.setBounds(26, 294, 131, 35);
		contentPane.add(btnAtleta);

		JButton btnOrganizador = new JButton("Organizador");
		btnOrganizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					elegirAsOrganizador();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnOrganizador.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnOrganizador.setBounds(368, 294, 122, 35);
		contentPane.add(btnOrganizador);

		JLabel llblBienvenido = new JLabel("\u00A1Bienvenido!");
		llblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 23));
		llblBienvenido.setBounds(182, 35, 196, 35);
		contentPane.add(llblBienvenido);
		contentPane.add(getBtnClub());
		contentPane.add(getLblNewLabel());
	}

	protected void elegirAsOrganizador() throws FileNotFoundException {
		int seleccion = JOptionPane.showOptionDialog(this, "Seleccione la opci??n que quiere realizar",
				"Inicio como organizador", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null
																													// para
																													// icono
																													// por
																													// defecto.
				new Object[] { "Consultar inscripciones", "Consultar clasificaciones", "Cargar tiempos",
						"Asignar dorsales", "Crear Competicion" }, // null
				// para
				// YES,
				// NO y
				// CANCEL
				"opcion 1");

		if (seleccion != -1)
			System.out.println("seleccionada opcion " + (seleccion + 1));
		if (seleccion == 0) // organizador
		{
			mostrarVentanaInscripciones(); // tania
		} else if (seleccion == 1) {
			mostrarVentanaClasificaciones();
		} // moises
		else if (seleccion == 2) {
			if (actualizarClasificaciones()) {
				JOptionPane.showMessageDialog(this,
						"Datos de " + cm.getCompeticionById(competicionId).get(0).getNombre() + " cargados. \n"
								+ "Se han actualizado los tiempos de " + ndatos + " atletas");
				ndatos = 0;
			} else
				JOptionPane.showMessageDialog(this, "Los Tiempos no ha podido cargarse."); // moises
		} else if (seleccion == 3) {
			asignarDorsales();
		} else if (seleccion == 4) {
			mostrarVentanaCrearCompeticion();
		}
	}

	private void mostrarVentanaCrearCompeticion() {
		// this.dispose();
		// CompeticionDto competicion = crearCompeticion();
		VentanaCrearCompeticion vPal = new VentanaCrearCompeticion(this);
		vPal.setLocationRelativeTo(this);
		vPal.setVisible(true);
		this.setVisible(false);
	}

	private boolean actualizarClasificaciones() throws FileNotFoundException {
		File file = cargarFicherosTiempos();
		return getValues(file);

	}

	protected File cargarFicherosTiempos() {
		int respuesta = getChooser().showOpenDialog(null);
		if (respuesta == 0) {
			File file = (File) chooser.getSelectedFile();
			return file;
		}
		return null;
	}

	public boolean getValues(File file) {
		competicionId = null;
		CompeticionModel cm = new CompeticionModel();
		MarcaTiempo mt;
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String strLine;
		ArrayList<MarcaTiempo> tiempos = new ArrayList<MarcaTiempo>();
		try {
			while ((strLine = reader.readLine()) != null) {
				String[] trozos = strLine.split(" ");
				if (trozos.length == 1)
					competicionId = trozos[0];
				else if (trozos.length == 3) {
					ndatos++;
					mt = new MarcaTiempo();
					mt.setDorsal(trozos[0]);
					mt.setTiempoInicial(trozos[1]);
					mt.setTiempoFinal(trozos[2]);
					tiempos.add(mt);
				} else if (trozos.length > 3) {
					ndatos++;
					mt = new MarcaTiempo();
					mt.setDorsal(trozos[0]);
					mt.setTiempoInicial(trozos[1]);
					Integer[] tiemposPaso = new Integer[4];
					for (int i = 2; i < trozos.length - 1; i++)
						try {
							tiemposPaso[i - 2] = Integer.valueOf(trozos[i]);
						} catch (NumberFormatException n) {
							JOptionPane.showMessageDialog(this,
									"Los Tiempos no ha podido cargarse. (Formato de fichero incorrecto)");
						}
					mt.setTiemposPaso(tiemposPaso);
					mt.setTiempoFinal(trozos[trozos.length - 1]);
					tiempos.add(mt);
				} else {
					JOptionPane.showMessageDialog(this,
							"El archivo " + file.getName() + " no sigue el formato correcto"); // moises
					return false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		try {
			cm.actualizarTiempos(competicionId, tiempos);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public JFileChooser getChooser() {
		if (chooser == null)
			chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		return chooser;
	}

	private void asignarDorsales() {
		this.dispose();
		VentanaAsignarDorsales vPal = new VentanaAsignarDorsales(this);
		vPal.setLocationRelativeTo(this);
		vPal.setVisible(true);

	}

	private void mostrarVentanaClasificaciones() {
		this.dispose();
		VentanaMostrarCarrerasOrganizador vPal = new VentanaMostrarCarrerasOrganizador("c");
		vPal.setLocationRelativeTo(this);
		vPal.setVisible(true);

	}

	private void mostrarVentanaInscripciones() {
		this.dispose();
		VentanaMostrarCarrerasOrganizador vPal = new VentanaMostrarCarrerasOrganizador("i");
		vPal.setLocationRelativeTo(this);
		vPal.setVisible(true);
	}

	protected void elegirAsAtleta() {
		int seleccion = JOptionPane.showOptionDialog(this,
				"??Desea inscribirse o conocer el estado de sus inscripciones?", "Inicio como atleta",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono por defecto.
				new Object[] { "Inscribirme", "Conocer mi estado" }, // null para YES, NO y CANCEL
				"opcion 1");

		if (seleccion != -1)
			System.out.println("seleccionada opcion " + (seleccion + 1));
		if (seleccion == 0) // tarjeta de credito
		{
			mostrarVentanaCarreras();
		} else if (seleccion == 1) {
			mostrarVentanaConocerEstado();
		}
	}

	private void mostrarVentanaConocerEstado() {
		this.dispose();
		VentanaInscripcionesAtleta vPal = new VentanaInscripcionesAtleta(this);
		vPal.setLocationRelativeTo(this);
		vPal.setVisible(true);
	}

	private void mostrarVentanaCarreras() {
		this.dispose();
		VentanaMostrarCarreras vPal = new VentanaMostrarCarreras(this);
		vPal.setLocationRelativeTo(this);
		vPal.setVisible(true);

	}

	private JButton getBtnClub() {
		if (btnClub == null) {
			btnClub = new JButton("Club");
			btnClub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					elegirSiNoClub();
				}
			});
			btnClub.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnClub.setBounds(196, 294, 131, 35);
		}
		return btnClub;
	}

	protected void elegirSiNoClub() {
		int seleccion = JOptionPane.showOptionDialog(this, "??Desea inscribir un lote de atletas?",
				"Inscripcion de lotes", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null
																												// para
																												// icono
																												// por
																												// defecto.
				new Object[] { "Si" }, // null para YES, NO y CANCEL
				"opcion 1");

		if (seleccion != -1)
			System.out.println("seleccionada opcion " + (seleccion + 1));
		if (seleccion == 0) // tarjeta de credito
		{
			mostrarVentanaCarrerasClub();
		}
	}

	private void mostrarVentanaCarrerasClub() {
		// this.dispose();
		// CompeticionDto competicion = crearCompeticion();
		VentanaMostrarCarrerasClub vPal = new VentanaMostrarCarrerasClub(this);
		vPal.setLocationRelativeTo(this);
		vPal.setVisible(true);
		this.setVisible(false);
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(VentanaInicial.class.getResource("/img/image1.png")));
			lblNewLabel.setBounds(72, 68, 445, 155);
		}
		return lblNewLabel;
	}
}
