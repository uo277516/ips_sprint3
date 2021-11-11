package igu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import logica.CategoriaDto;
import logica.CompeticionModel;

public class VentanaClasificacion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	@SuppressWarnings("unused")
	private JTextArea textArea;
	private JList<String> clasificacion;
	@SuppressWarnings("unused")
	private JTable table;
	private JLabel label;

	CompeticionModel cm = new CompeticionModel();
	private String id;
	private int n;
//	private JScrollPane scrollPane;
//	private JPanel panel;
//	private JLabel lblNewLabel;
	private JList<String> list;
	private JPanel panel_1;
	private List<CategoriaDto> categorias;

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
		this.id = id;
		categorias = cm.getCategoriasByCompeticion(id);
		setTitle("Clasificación:");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 488);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblCompeticiones(), BorderLayout.NORTH);
		contentPane.add(getPanel_1(), BorderLayout.CENTER);
		// contentPane.add(getScrollPane());
		
		crearClasificaciones();
	}

	private JLabel getLblCompeticiones() {
		if (label == null) {
			label = new JLabel("A continuación se muestra la clasificacion de "
					+ (cm.getCompeticionById(String.valueOf(id)).get(0).getNombre()));
			label.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		}
		return label;
	}

	private JList<String> getListaClasificacion(String categoria) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		if (clasificacion == null) {
			clasificacion = new JList<String>();

			try {
				cm.getClasificacionPorEdad(id, categoria).forEach(s -> model.addElement(s));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		clasificacion.setModel(model);
		return clasificacion;
	}

//	private JPanel getPanel(String categoria, int n) {
//		JPanel panel = null;
//		if (panel == null) {
//			panel = new JPanel();
//			panel.setBounds(24, n * 47, 442, 142);
//			panel.add(getLblCategoria(categoria));
//			panel.add(getScrollLista(categoria));
//		}
//		return panel;
//	}
//
//	private JScrollPane getScrollLista(String categoria) {
//		JScrollPane scrollLista = null;
//		if (scrollLista == null) {
//			scrollLista = new JScrollPane();
//			scrollLista.setViewportView(getListaClasificacion(categoria));
//		}
//		return scrollLista;
//	}
//
//	private JLabel getLblCategoria(String categoria) {
//		JLabel labelCategoria = null;
//		if (labelCategoria == null) {
//			labelCategoria = new JLabel("Categoria " + categoria);
//			labelCategoria.setFont(new Font("Lucida Grande", Font.BOLD, 14));
//		}
//		return labelCategoria;
//	}

	private void crearClasificaciones() throws SQLException {
		n = 1;
		
		for (CategoriaDto c : categorias)
			crearClasificacion(c, n++);

	}

	private void crearClasificacion(CategoriaDto c, int n) throws SQLException {
		cm.getClasificacionPorEdad(id, c.getNombre()).forEach((a) -> System.out.println(a));
		panel_1.add(getScrollPane(c.getNombre(), n));
	}

	private JScrollPane getScrollPane(String categoria, int n) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 100*n, 916, 96);
		scrollPane.setViewportView(getPanel(categoria));
		scrollPane.setColumnHeaderView(getLblNewLabel(categoria));
		return scrollPane;
	}

	private JPanel getPanel(String categoria) {
		JPanel panel = new JPanel();
		panel.add(getList(categoria));
		//panel.add(getLblNewLabel(categoria));

		return panel;
	}

	private JLabel getLblNewLabel(String categoria) {
		JLabel lblNewLabel = new JLabel("Categoria " + categoria);
		return lblNewLabel;
	}

	private JList<String> getList(String categoria) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		JList<String> clasificacion = new JList<String>();

		try {
			cm.getClasificacionPorEdad(id, categoria).forEach(s -> model.addElement(s));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		clasificacion.setModel(model);
		return clasificacion;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new GridLayout(categorias.size(), 0, 0, 0));
		}
		return panel_1;
	}
}
