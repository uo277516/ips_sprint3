package igu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.AtletaDto;
import logica.AtletaModel;
import logica.CompeticionDto;
import logica.InscripcionDto;
import logica.InscripcionModel;

public class VentanaAtletaInscripcion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private CompeticionDto competition;
	private JLabel lblTituloInscripciones;
	private JLabel lblDniAtleta;
	private JLabel lblNombre;
	private Panel panel;
	private Panel pnlAtletaInsTitulo;
	private Panel pnlTxtArea;
	private JTable table;
	private JLabel lblCategoria;
	private JLabel lblFecha;
	private JLabel lblEstado;
	private InscripcionModel im;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaAtletaInscripcion frame = new VentanaAtletaInscripcion();
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
	 * @param comp
	 * @throws SQLException
	 */
	public VentanaAtletaInscripcion(CompeticionDto comp) throws SQLException {
		setMinimumSize(new Dimension(550, 200));
		this.competition = comp;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblTituloInscripciones(), BorderLayout.NORTH);
		contentPane.add(getPanel(), BorderLayout.CENTER);
		this.im = new InscripcionModel();
		updateEstadosIncripciones();
	}

	private JLabel getLblTituloInscripciones() {
		if (lblTituloInscripciones == null) {
			lblTituloInscripciones = new JLabel("Inscripciones para la " + this.competition.getNombre());
			lblTituloInscripciones.setHorizontalTextPosition(SwingConstants.CENTER);
			lblTituloInscripciones.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		}
		return lblTituloInscripciones;
	}

	private JLabel getLblDniAtleta() {
		if (lblDniAtleta == null) {
			lblDniAtleta = new JLabel("DNI");
			lblDniAtleta.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		}
		return lblDniAtleta;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
			lblNombre.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		}
		return lblNombre;
	}

	private AtletaDto getAtleta(String dni) throws SQLException {
		AtletaModel am = new AtletaModel();
		return am.findAtletaByDni(dni);
	}

	private List<InscripcionDto> getInscripciones() throws SQLException {
		InscripcionModel im = new InscripcionModel();
		return im.getInscripcionesDeUnaCompeticion(this.competition.getId());
	}

	private Panel getPanel() throws SQLException {
		if (panel == null) {
			panel = new Panel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPnlAtletaInsTitulo(), BorderLayout.NORTH);
			panel.add(getPanel_1_1(), BorderLayout.CENTER);
		}
		return panel;
	}

	private Panel getPnlAtletaInsTitulo() throws SQLException {
		if (pnlAtletaInsTitulo == null) {
			pnlAtletaInsTitulo = new Panel();
			pnlAtletaInsTitulo.setLayout(new GridLayout(0, 5, 0, 0));
			pnlAtletaInsTitulo.add(getLblDniAtleta());
			pnlAtletaInsTitulo.add(getLblNombre());
			pnlAtletaInsTitulo.add(getLblCategoria());
			pnlAtletaInsTitulo.add(getLblFecha());
			pnlAtletaInsTitulo.add(getLblEstado());
		}
		return pnlAtletaInsTitulo;
	}

	private Panel getPanel_1_1() throws SQLException {
		if (pnlTxtArea == null) {
			pnlTxtArea = new Panel();
			pnlTxtArea.setLayout(new BorderLayout(0, 0));
			pnlTxtArea.add(getTable(), BorderLayout.CENTER);
		}
		return pnlTxtArea;
	}

	private JTable getTable() throws SQLException {
		if (table == null) {
			table = new JTable();
			table.setToolTipText("Inscripciones");
			table.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			table.setSelectionBackground(new Color(106, 31, 109));
			table.setBackground(Color.WHITE);
			DefaultTableModel modelo = new DefaultTableModel();
			// tabla cambaida
			table.setModel(modelo);
			modelo.addColumn("DNI");
			modelo.addColumn("Nombre");
			modelo.addColumn("Categoría");
			modelo.addColumn("Fecha");
			modelo.addColumn("Estado");
			String[][] info = new String[getInscripciones().size()][5];
			List<InscripcionDto> inscripciones = getInscripciones();
			AtletaDto a;

			for (int i = 0; i < inscripciones.size(); i++) {
				a = getAtleta(inscripciones.get(i).getDni_a());
				info[i][0] = a.getDni();
				info[i][1] = a.getNombre();
				info[i][2] = inscripciones.get(i).getCategoria();
				info[i][3] = inscripciones.get(i).getFecha();
				info[i][4] = inscripciones.get(i).getEstado();
				modelo.addRow(info[i]);
			}
		}
		return table;
	}

	private JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel("Categoría");
			lblCategoria.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		}
		return lblCategoria;
	}

	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha");
			lblFecha.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		}
		return lblFecha;
	}

	private JLabel getLblEstado() {
		if (lblEstado == null) {
			lblEstado = new JLabel("Estado");
			lblEstado.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		}
		return lblEstado;
	}

	private void updateEstadosIncripciones() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {

			String fileName = getNombreFichero();
			archivo = new File(fileName);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			List<String[]> transferenciasBanco = new LinkedList<>();
			List<String> dniTransferenciasBanco = new LinkedList<>();
			List<InscripcionDto> transferencias = im.getInscripcionesMetodoPago("transferencia");

			while ((linea = br.readLine()) != null) {
				String[] line = linea.split("@");
				transferenciasBanco.add(line);
				dniTransferenciasBanco.add(line[0]);
			}

			for (InscripcionDto ins : transferencias) {
				if (dniTransferenciasBanco.contains(ins.getDni_a())) {
					// Transferencia del banco
					int index = dniTransferenciasBanco.indexOf(ins.getDni_a());
					updateEstadoInsTransBanco(transferenciasBanco.get(index));
				} else {
					// Todavía no ha pagado
					updateEstadoInsTrans(ins);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private void updateEstadoInsTrans(InscripcionDto ins) {
		// Obtenemos la fecha de inscripción
		String[] fecha = ins.getFecha().split("/");
		LocalDate fechaIns = LocalDate.of(Integer.valueOf(fecha[2]), Integer.valueOf(fecha[1]),
				Integer.valueOf(fecha[0]));

		// Obtenemos la fecha de hoy
		LocalDate hoy = LocalDate.now();
		long dias = ChronoUnit.DAYS.between(fechaIns, hoy);

		// La fecha de inscripción es previa a la de hoy y ha pasado el plazo de 48h
		if (fechaIns.compareTo(hoy) < 0 && dias >= 3) {
			im.actualizarInscripcionEstado("Anulada", ins.getDni_a(), ins.getId_c());
		} else if (fechaIns.compareTo(hoy) < 0) {
			im.actualizarInscripcionEstado("Pre-inscrito", ins.getDni_a(), ins.getId_c());
		}
	}

	private void updateEstadoInsTransBanco(String[] line) {
		// DNI @ dia-mes-año @ cantidad ingresada
//		// Obtenemos los datos;
		String dnia = line[0];

		// Obtenemos la fecha de ahora y de pago
		LocalDate ahora = LocalDate.now();
		String[] dateFichero = line[1].split("-");
		LocalDate fechaPago = LocalDate.of(Integer.valueOf(dateFichero[2]), Integer.valueOf(dateFichero[1]),
				Integer.valueOf(dateFichero[0]));

		// Obtenemos la inscripción del atleta
		InscripcionDto ins = im.findInsByDniId(dnia, this.competition.getId());

		// Obtenemos la fecha de la inscripción
		String[] fecha = ins.getFecha().split("/");
		LocalDate fechaIns = LocalDate.of(Integer.valueOf(fecha[2]), Integer.valueOf(fecha[1]),
				Integer.valueOf(fecha[0]));

		if (fechaIns.compareTo(ahora) < 0 && fechaIns.compareTo(fechaPago) < 0) {
			// La fecha de inscripción es antes de que la actual y la de pago
			long dias = ChronoUnit.DAYS.between(fechaIns, fechaPago);
			if (dias >= 3) {
				// Fuera del plazo
				updateFueraDelPlazo(line, dnia);
			} else {
				// Dentro del plazo
				updateDentroDelPlazo(line, dnia, fechaPago);
			}
		}
	}

	private void updateFueraDelPlazo(String[] line, String dnia) {
		if (Float.valueOf(line[2]) > 0) {
			// Pagó y hay que devolvérselo
			im.actualizarInscripcionEstado("Anulada - pendiente de devolución", dnia, this.competition.getId());
		} else {
			// No pagó
			im.actualizarInscripcionEstado("Anulada", dnia, this.competition.getId());
		}
	}

	private void updateDentroDelPlazo(String[] line, String dnia, LocalDate fechaPago) {
		float cuotaAPagar = calcularCuotaPlazo(fechaPago);
		float pago = Float.valueOf(line[2]);
		if (cuotaAPagar == pago) {
			// Paga justo
			im.actualizarInscripcionEstado("Inscrito", dnia, this.competition.getId());
			im.actualizarInscripcionCantPagada(pago, dnia, this.competition.getId());
		} else if (cuotaAPagar < pago) {
			// Paga de más
			im.actualizarInscripcionEstado("Inscrito - pendiente de devolución", dnia, this.competition.getId());
			im.actualizarInscripcionCantPagada(pago, dnia, this.competition.getId());
		} else {
			// Paga de menos
			if (pago == 0) {
				im.actualizarInscripcionEstado("Anulada", dnia, this.competition.getId());
			} else {
				im.actualizarInscripcionEstado("Anulada - pendiente de devolución", dnia, this.competition.getId());
			}

		}
	}

	private String getNombreFichero() {
		String[] a = this.competition.getNombre().toLowerCase().split(" ");
		String nombre = "banco";
		for (String s : a) {
			nombre += s;
		}
		return nombre;
	}

	private float calcularCuotaPlazo(LocalDate date) {
		LocalDate di, df;
		String[] inicio, fin;

		// Coger fechas plazos
		if (this.competition.getF_inicio1() != null && this.competition.getF_fin1() != null) {
			inicio = this.competition.getF_inicio1().split("/");
			fin = this.competition.getF_fin1().split("/");
			di = LocalDate.of(Integer.valueOf(inicio[2]), Integer.valueOf(inicio[1]), Integer.valueOf(inicio[0]));
			df = LocalDate.of(Integer.valueOf(fin[2]), Integer.valueOf(fin[1]), Integer.valueOf(fin[0]));
			if (di.compareTo(date) < 0 && date.compareTo(df) < 0) {
				// Está en el primer plazo
				return this.competition.getCuota1();
			}
		}

		if (this.competition.getF_inicio2() != null && this.competition.getF_fin2() != null) {
			inicio = this.competition.getF_inicio2().split("/");
			fin = this.competition.getF_fin2().split("/");
			di = LocalDate.of(Integer.valueOf(inicio[2]), Integer.valueOf(inicio[1]), Integer.valueOf(inicio[0]));
			df = LocalDate.of(Integer.valueOf(fin[2]), Integer.valueOf(fin[1]), Integer.valueOf(fin[0]));
			if (di.compareTo(date) < 0 && date.compareTo(df) < 0) {
				// Está en el segundo plazo
				return this.competition.getCuota2();
			}
		}

		if (this.competition.getF_inicio3() != null && this.competition.getF_fin3() != null) {
			inicio = this.competition.getF_inicio3().split("/");
			fin = this.competition.getF_fin3().split("/");
			di = LocalDate.of(Integer.valueOf(inicio[2]), Integer.valueOf(inicio[1]), Integer.valueOf(inicio[0]));
			df = LocalDate.of(Integer.valueOf(fin[2]), Integer.valueOf(fin[1]), Integer.valueOf(fin[0]));
			if (di.compareTo(date) < 0 && date.compareTo(df) < 0) {
				// Está en el tercer plazo
				return this.competition.getCuota3();
			}
		}

		return 0;
	}
}
