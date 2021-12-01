package igu.organizador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
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
	CompeticionModel cm = new CompeticionModel();
	private JPanel pnNorth;
	private JLabel label;
	private JPanel pnCheck;
	private JCheckBox chClub;
	private JCheckBox chTiempos;
	private JCheckBox chMedia;
	private JCheckBox chDiferencia;
	private JButton btChecks;
	private JLabel lblNewLabel;

	private DefaultTableModel modelo;

	private List<MarcaTiempo> tiempos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaClasificacion frame = new VentanaClasificacion("00");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public VentanaClasificacion(String id) throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAtletaListaEspera.class
				.getResource("/img/icono-plano-de-la-bandera-carreras-con-sombra-larga-colorido-198376094.jpg")));

		tiempos = cm.ordenarPorCategoria(cm.getClasificacion(id));
		setTitle("Clasificación:");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 488);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane());
		contentPane.add(getPnNorth(), BorderLayout.NORTH);

	}

	private JScrollPane getScrollPane() throws SQLException {
		JScrollPane scrollPane = new JScrollPane(getTable(), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(34, 100, 916, 96);
		return scrollPane;
	}

	@SuppressWarnings("serial")
	private JTable getTable() throws SQLException {
		if (table == null) {
			table = new JTable();
			table.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
			table.setFont(new Font("Tahoma", Font.PLAIN, 13));
			table.setSelectionBackground(Color.YELLOW);
			table.setBackground(Color.LIGHT_GRAY);

			modelo = new DefaultTableModel() {
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
			modelo.addColumn("Club");
			modelo.addColumn("Tiempo de paso 1");
			modelo.addColumn("Tiempo de paso 2");
			modelo.addColumn("Tiempo de paso 3");
			modelo.addColumn("Tiempo de paso 4");
			modelo.addColumn("Min/km");
			modelo.addColumn("Diferencia 1º (Minutos)");
			String[][] info = new String[tiempos.size()][15];

			for (int i = 0; i < tiempos.size(); i++) {
				info[i][0] = String.valueOf(tiempos.get(i).getPosicion());
				info[i][1] = tiempos.get(i).getDorsal();
				info[i][2] = tiempos.get(i).getNombre();
				info[i][3] = tiempos.get(i).getSexo().equals("femenino") ? "F" : "M";
				info[i][4] = String.valueOf(tiempos.get(i).getEdad());
				info[i][5] = String.valueOf(tiempos.get(i).getHoras());
				info[i][6] = String.valueOf(tiempos.get(i).getMinutos());
				info[i][7] = tiempos.get(i).getCategoria();
				modelo.addRow(info[i]);
			}
		}

		return table;
	}

	private void mostrarColumnas() throws SQLException {
		String cadena = "";
		if (getChClub().isSelected())
			cadena = cadena.concat("c");
		if (getChTiempos().isSelected())
			cadena = cadena.concat("t");
		if (getChMedia().isSelected())
			cadena = cadena.concat("m");
		if (getChDiferencia().isSelected())
			cadena = cadena.concat("d");
		switch (cadena) {
		case "ctmd":
			for (int i = 0; i < tiempos.size(); i++) {
				modelo.setValueAt(tiempos.get(i).getClub(), i, 8);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(0), i, 9);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(1), i, 10);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(2), i, 11);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(3), i, 12);
				modelo.setValueAt(tiempos.get(i).getMinutosKm(), i, 13);
				modelo.setValueAt('+' + String.valueOf(tiempos.get(i).getDiferencia()), i, 14);
			}
			break;
		case "ctm":
			for (int i = 0; i < tiempos.size(); i++) {
				modelo.setValueAt(tiempos.get(i).getClub(), i, 8);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(0), i, 9);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(1), i, 10);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(2), i, 11);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(3), i, 12);
				modelo.setValueAt(tiempos.get(i).getMinutosKm(), i, 13);
				modelo.setValueAt(null, i, 14);
			}
			break;
		case "ctd":
			for (int i = 0; i < tiempos.size(); i++) {
				modelo.setValueAt(tiempos.get(i).getClub(), i, 8);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(0), i, 9);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(1), i, 10);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(2), i, 11);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(3), i, 12);
				modelo.setValueAt(null, i, 13);
				modelo.setValueAt('+' + String.valueOf(tiempos.get(i).getDiferencia()), i, 14);
			}
			break;
		case "cmd":
			for (int i = 0; i < tiempos.size(); i++) {
				modelo.setValueAt(tiempos.get(i).getClub(), i, 8);
				modelo.setValueAt(null, i, 9);
				modelo.setValueAt(null, i, 10);
				modelo.setValueAt(null, i, 11);
				modelo.setValueAt(null, i, 12);
				modelo.setValueAt(null, i, 13);
				modelo.setValueAt('+' + String.valueOf(tiempos.get(i).getDiferencia()), i, 14);
			}
			break;
		case "tmd":
			for (int i = 0; i < tiempos.size(); i++) {
				modelo.setValueAt(null, i, 8);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(0), i, 9);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(1), i, 10);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(2), i, 11);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(3), i, 12);
				modelo.setValueAt(tiempos.get(i).getMinutosKm(), i, 13);
				modelo.setValueAt('+' + String.valueOf(tiempos.get(i).getDiferencia()), i, 14);
			}
			break;

		case "ct":
			for (int i = 0; i < tiempos.size(); i++) {
				modelo.setValueAt(tiempos.get(i).getClub(), i, 8);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(0), i, 9);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(1), i, 10);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(2), i, 11);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(3), i, 12);
				modelo.setValueAt(null, i, 13);
				modelo.setValueAt(null, i, 14);
			}
			break;

		case "cm":
			for (int i = 0; i < tiempos.size(); i++) {
				modelo.setValueAt(tiempos.get(i).getClub(), i, 8);
				modelo.setValueAt(null, i, 9);
				modelo.setValueAt(null, i, 10);
				modelo.setValueAt(null, i, 11);
				modelo.setValueAt(null, i, 12);
				modelo.setValueAt(tiempos.get(i).getMinutosKm(), i, 13);
				modelo.setValueAt(null, i, 14);
			}
			break;
		case "cd":
			for (int i = 0; i < tiempos.size(); i++) {
				modelo.setValueAt(tiempos.get(i).getClub(), i, 8);
				modelo.setValueAt(null, i, 9);
				modelo.setValueAt(null, i, 10);
				modelo.setValueAt(null, i, 11);
				modelo.setValueAt(null, i, 12);
				modelo.setValueAt(null, i, 13);
				modelo.setValueAt('+' + String.valueOf(tiempos.get(i).getDiferencia()), i, 14);
			}
			break;

		case "tm":
			for (int i = 0; i < tiempos.size(); i++) {
				modelo.setValueAt(null, i, 8);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(0), i, 9);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(1), i, 10);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(2), i, 11);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(3), i, 12);
				modelo.setValueAt(tiempos.get(i).getMinutosKm(), i, 13);
				modelo.setValueAt(null, i, 14);
			}
			break;
		case "td":
			for (int i = 0; i < tiempos.size(); i++) {
				modelo.setValueAt(null, i, 8);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(0), i, 9);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(1), i, 10);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(2), i, 11);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(3), i, 12);
				modelo.setValueAt(null, i, 13);
				modelo.setValueAt('+' + String.valueOf(tiempos.get(i).getDiferencia()), i, 14);
			}
			break;

		case "md":
			for (int i = 0; i < tiempos.size(); i++) {
				modelo.setValueAt(null, i, 8);
				modelo.setValueAt(null, i, 9);
				modelo.setValueAt(null, i, 10);
				modelo.setValueAt(null, i, 11);
				modelo.setValueAt(null, i, 12);
				modelo.setValueAt(tiempos.get(i).getMinutosKm(), i, 13);
				modelo.setValueAt('+' + String.valueOf(tiempos.get(i).getDiferencia()), i, 14);
			}
			break;

		case "c":
			for (int i = 0; i < tiempos.size(); i++) {
				modelo.setValueAt(tiempos.get(i).getClub(), i, 8);
				modelo.setValueAt(null, i, 9);
				modelo.setValueAt(null, i, 10);
				modelo.setValueAt(null, i, 11);
				modelo.setValueAt(null, i, 12);
				modelo.setValueAt(null, i, 13);
				modelo.setValueAt(null, i, 14);
			}
			break;
		case "t":
			for (int i = 0; i < tiempos.size(); i++) {
				modelo.setValueAt(null, i, 8);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(0), i, 9);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(1), i, 10);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(2), i, 11);
				modelo.setValueAt(tiempos.get(i).getTiemposPaso().get(3), i, 12);
				modelo.setValueAt(null, i, 13);
				modelo.setValueAt(null, i, 14);
			}
			break;
		case "m":
			for (int i = 0; i < tiempos.size(); i++) {
				modelo.setValueAt(null, i, 8);
				modelo.setValueAt(null, i, 9);
				modelo.setValueAt(null, i, 10);
				modelo.setValueAt(null, i, 11);
				modelo.setValueAt(null, i, 12);
				modelo.setValueAt(tiempos.get(i).getMinutosKm(), i, 13);
				modelo.setValueAt(null, i, 14);
			}
			break;
		case "d":
			for (int i = 0; i < tiempos.size(); i++) {
				modelo.setValueAt(null, i, 8);
				modelo.setValueAt(null, i, 9);
				modelo.setValueAt(null, i, 10);
				modelo.setValueAt(null, i, 11);
				modelo.setValueAt(null, i, 12);
				modelo.setValueAt(null, i, 13);
				modelo.setValueAt('+' + String.valueOf(tiempos.get(i).getDiferencia()), i, 14);
			}
			break;
		}
	}

	private JPanel getPnNorth() {
		if (pnNorth == null) {
			pnNorth = new JPanel();
			pnNorth.setLayout(new BorderLayout(0, 0));
			pnNorth.add(getLabel(), BorderLayout.NORTH);
			pnNorth.add(getPnCheck(), BorderLayout.CENTER);
			pnNorth.add(getLblNewLabel(), BorderLayout.WEST);
		}
		return pnNorth;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("A continuaci\u00F3n se muestra la clasificacion de <dynamic>");
			label.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return label;
	}

	private JPanel getPnCheck() {
		if (pnCheck == null) {
			pnCheck = new JPanel();
			pnCheck.add(getChClub());
			pnCheck.add(getChTiempos());
			pnCheck.add(getChMedia());
			pnCheck.add(getChDiferencia());
			pnCheck.add(getBtChecks());
		}
		return pnCheck;
	}

	private JCheckBox getChClub() {
		if (chClub == null) {
			chClub = new JCheckBox("Club");
		}
		return chClub;
	}

	private JCheckBox getChTiempos() {
		if (chTiempos == null) {
			chTiempos = new JCheckBox("Tiempos de paso");
		}
		return chTiempos;
	}

	private JCheckBox getChMedia() {
		if (chMedia == null) {
			chMedia = new JCheckBox("Minutos/km");
		}
		return chMedia;
	}

	private JCheckBox getChDiferencia() {
		if (chDiferencia == null) {
			chDiferencia = new JCheckBox("Diferencia respecto al primero");
		}
		return chDiferencia;
	}

	private JButton getBtChecks() {
		if (btChecks == null) {
			btChecks = new JButton("Ver");
			btChecks.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						mostrarColumnas();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			});
		}
		return btChecks;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Marque las opciones que desee ver:");
		}
		return lblNewLabel;
	}
}
