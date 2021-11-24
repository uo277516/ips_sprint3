package igu.organizador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import logica.AtletaDto;
import logica.AtletaModel;
import logica.CompeticionDto;
import logica.InscripcionDto;
import logica.InscripcionModel;
import logica.ListaEsperaDto;
import logica.ListaEsperaModel;

public class VentanaListasEspera extends JFrame {

	private static final long serialVersionUID = 1L;
	private CompeticionDto comp;
	private ListaEsperaModel lem;
	private InscripcionModel im;
	private AtletaModel am;
	private JPanel contentPane;
	private JLabel lblTitleListaEsperaCompeticion;
	private JPanel panel;
	private JPanel panelTabla;
	private JTable table;
	private JLabel lblNumOrden;
	private JLabel lblDni;
	private JLabel lblNombre;
	private JLabel lblSexo;

	/**
	 * Create the frame.
	 * 
	 * @param competicionDto
	 */
	public VentanaListasEspera(CompeticionDto competicionDto) {
		this.comp = competicionDto;
		lem = new ListaEsperaModel();
		im = new InscripcionModel();
		am = new AtletaModel();
		setTitle("Lista de espera");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblTitleListaEsperaCompeticion(), BorderLayout.NORTH);
		contentPane.add(getPanel(), BorderLayout.CENTER);
	}

	private JLabel getLblTitleListaEsperaCompeticion() {
		if (lblTitleListaEsperaCompeticion == null) {
			lblTitleListaEsperaCompeticion = new JLabel("Lista de espera de " + this.comp.getNombre());
			lblTitleListaEsperaCompeticion.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitleListaEsperaCompeticion.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		}
		return lblTitleListaEsperaCompeticion;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPanelTabla(), BorderLayout.NORTH);
			panel.add(getTable());
		}
		return panel;
	}

	private JPanel getPanelTabla() {
		if (panelTabla == null) {
			panelTabla = new JPanel();
			panelTabla.setLayout(new GridLayout(0, 4, 0, 0));
			panelTabla.add(getLblNumOrden());
			panelTabla.add(getLblDni());
			panelTabla.add(getLblNombre());
			panelTabla.add(getLblSexo());
		}
		return panelTabla;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setToolTipText("Lista de espera");
			table.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

			table.setSelectionBackground(new Color(106, 31, 109));
			table.setBackground(Color.WHITE);
			DefaultTableModel modelo = new DefaultTableModel();

			table.setModel(modelo);
			modelo.addColumn("Nº orden");
			modelo.addColumn("DNI");
			modelo.addColumn("Nombre");
			modelo.addColumn("Sexo");

			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			table.getColumnModel().getColumn(0).setCellRenderer(tcr);
			table.getColumnModel().getColumn(1).setCellRenderer(tcr);
			table.getColumnModel().getColumn(2).setCellRenderer(tcr);
			table.getColumnModel().getColumn(3).setCellRenderer(tcr);

			ListaEsperaDto lista = lem.getListaByIdComp(this.comp.getId());
			List<AtletaDto> atletas = am.getAtletasByListaId(lista.getId());
			InscripcionDto ins;
			AtletaDto a;

			String[][] info = new String[atletas.size()][4];
			for (int i = 0; i < atletas.size(); i++) {
				a = atletas.get(i);
				ins = im.findInsByDniId(a.getDni(), this.comp.getId());
				info[i][0] = String.valueOf(i + 1);
				info[i][1] = a.getDni();
				info[i][2] = a.getNombre();
				info[i][3] = a.getSexo();
				modelo.addRow(info[i]);
			}
		}
		return table;
	}

	private JLabel getLblNumOrden() {
		if (lblNumOrden == null) {
			lblNumOrden = new JLabel("Nº orden");
			lblNumOrden.setHorizontalAlignment(SwingConstants.CENTER);
			lblNumOrden.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		}
		return lblNumOrden;
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI");
			lblDni.setHorizontalAlignment(SwingConstants.CENTER);
			lblDni.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		}
		return lblDni;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
			lblNombre.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		}
		return lblNombre;
	}

	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo");
			lblSexo.setHorizontalAlignment(SwingConstants.CENTER);
			lblSexo.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		}
		return lblSexo;
	}
}
