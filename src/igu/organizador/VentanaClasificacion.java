package igu.organizador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import igu.atleta.VentanaAtletaListaEspera;
import logica.CompeticionModel;
import logica.MarcaTiempo;

public class VentanaClasificacion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTable table;
	private JLabel label;

	CompeticionModel cm = new CompeticionModel();
	private String id;
	private int n;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaClasificacion frame = new VentanaClasificacion("5f3b7f31-c2af-45e8-8d4d-8b44f8706af6");
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
	 * @throws SQLException
	 */
	public VentanaClasificacion(String id) throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAtletaListaEspera.class.getResource("/img/icono-plano-de-la-bandera-carreras-con-sombra-larga-colorido-198376094.jpg")));

		this.id = id;
		setTitle("Clasificaci�n:");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 488);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblCompeticiones(), BorderLayout.NORTH);
		contentPane.add(getScrollPane());

	}

	private JLabel getLblCompeticiones() {
		if (label == null) {
			label = new JLabel("A continuación se muestra la clasificacion de "
					+ (cm.getCompeticionById(String.valueOf(id)).get(0).getNombre()));
			label.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		}
		return label;
	}

	private JScrollPane getScrollPane() throws SQLException {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 100 * n, 916, 96);
		scrollPane.setViewportView(getTable());
		return scrollPane;
	}

	private JTable getTable() throws SQLException {
		if (table == null) {
			table = new JTable();
			table.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
			table.setFont(new Font("Tahoma", Font.PLAIN, 13));
			table.setSelectionBackground(Color.YELLOW);
			table.setBackground(Color.LIGHT_GRAY);
			@SuppressWarnings("serial")
			DefaultTableModel modelo = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) { // all cells false
					return false;
				}

			};
			table.setModel(modelo);
			modelo.addColumn("Pos");
			modelo.addColumn("Dorsal");
			modelo.addColumn("Nombre");
			modelo.addColumn("Sexo");
			modelo.addColumn("Edad");
			modelo.addColumn("Horas");
			modelo.addColumn("Minutos");
			modelo.addColumn("Categoría");
			List<MarcaTiempo> tiempos = cm.getClasificacion(id);
			String[][] info = new String[tiempos.size()][8];
			tiempos = cm.ordenarPorCategoria(tiempos);
			for (int i = 0; i < tiempos.size(); i++) {
				info[i][0] = String.valueOf(tiempos.get(i).getPosicion());
				info[i][1] = tiempos.get(i).getDorsal();
				info[i][2] = tiempos.get(i).getNombre();
				info[i][3] = tiempos.get(i).getSexo();
				info[i][4] = tiempos.get(i).getEdad();
				info[i][5] = String.valueOf(tiempos.get(i).getHoras());
				info[i][6] = String.valueOf(tiempos.get(i).getMinutos());
				info[i][7] = tiempos.get(i).getCategoria();
				modelo.addRow(info[i]);
			}
		}

		return table;
	}
}
