package igu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.CategoriaDto;
import logica.CategoriaModel;

@SuppressWarnings("serial")
public class VentanaCategorias extends JFrame {

	private JPanel contentPane;
	private JTextArea txtrPuedeCrearO;
	private JLabel lblCategoriasDefecto;
	private JScrollPane scrollPane;
	private JTable tableEstandar;
	private JScrollPane scrollPane_1;
	private JLabel lblCategorias;
	private JButton btnAnadir;
	private JButton btnConfirmar;
	private JButton btnCrear;

	private CategoriaModel cat;
	private JTable tableCate;

	private String id_comp;
	private VentanaCrearCompeticion vcc;

	/**
	 * Create the frame.
	 */
	public VentanaCategorias(VentanaCrearCompeticion vcc, String id) {
		this.vcc = vcc;
		this.id_comp = id;
		cat = new CategoriaModel();
		setTitle("Ventana categorias:");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 964, 461);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTxtrPuedeCrearO());
		contentPane.add(getLblCategoriasDefecto());
		contentPane.add(getScrollPane());
		contentPane.add(getScrollPane_1());
		contentPane.add(getLblCategorias());
		contentPane.add(getBtnAnadir());

		contentPane.add(getBtnConfirmar());
		contentPane.add(getBtnCrear());
		btnConfirmar.setEnabled(false);
	}

	private JTextArea getTxtrPuedeCrearO() {
		if (txtrPuedeCrearO == null) {
			txtrPuedeCrearO = new JTextArea();
			txtrPuedeCrearO.setEditable(false);
			txtrPuedeCrearO.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtrPuedeCrearO.setText(
					"Puede crear o modificar las categorias que desee. Para ello puede usar las categorias por defecto o crear una propia de la categoria. \r\nPara que el sistema valide los rangos de edades estos han de ser continuos. Comienza por la categoria de menor edad para ajustar los rangos");
			txtrPuedeCrearO.setBounds(38, 22, 880, 70);
		}
		return txtrPuedeCrearO;
	}

	private JLabel getLblCategoriasDefecto() {
		if (lblCategoriasDefecto == null) {
			lblCategoriasDefecto = new JLabel("Categorias por defecto:");
			lblCategoriasDefecto.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblCategoriasDefecto.setBounds(38, 97, 139, 20);
		}
		return lblCategoriasDefecto;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(38, 152, 415, 225);
			scrollPane.setViewportView(getTableEstandar());
		}
		return scrollPane;
	}

	private JTable getTableEstandar() {
		if (tableEstandar == null) {
			tableEstandar = new JTable();
			tableEstandar.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
			tableEstandar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			tableEstandar.setSelectionBackground(Color.YELLOW);
			tableEstandar.setBackground(Color.LIGHT_GRAY);
			String[] tabla = { "Id", "Nombre", "Edad Min", "Edad Max", "Sexo" };
			DefaultTableModel modelo = new DefaultTableModel() {
				public boolean isCellEditable(int fila, int columnas) {
					if (columnas == 5)
						return true;
					return false;

				}
			};
			tableEstandar.setModel(modelo);
			modelo.addColumn("Iden");
			modelo.addColumn("Nombre");
			modelo.addColumn("Edad Min");
			modelo.addColumn("Edad Max");
			modelo.addColumn("Sexo");
			List<CategoriaDto> competiciones = cat.getCategorias();
			String[][] info = new String[competiciones.size()][5];
			// List<AtletaDto> atletas = getAtletas();
			// List<InscripcionDto> inscripciones = getInscripciones();
			float cuota = 0;
			String fecha = "";
			for (int i = 0; i < competiciones.size(); i++) {
				info[i][0] = competiciones.get(i).getId();
				info[i][1] = competiciones.get(i).getNombre();
				info[i][2] = String.valueOf(competiciones.get(i).getEdad_min());
				info[i][3] = String.valueOf(competiciones.get(i).getEdad_max());
				info[i][4] = competiciones.get(i).getSexo();
				modelo.addRow(info[i]);
			}
		}
		System.out.println(tableEstandar.getRowCount());
		return tableEstandar;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(503, 152, 415, 225);
			scrollPane_1.setViewportView(getTableCate());
		}
		return scrollPane_1;
	}

	private JLabel getLblCategorias() {
		if (lblCategorias == null) {
			lblCategorias = new JLabel("Categorias competicion:");
			lblCategorias.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblCategorias.setBounds(503, 101, 166, 20);
		}
		return lblCategorias;
	}

	private JButton getBtnAnadir() {
		if (btnAnadir == null) {
			btnAnadir = new JButton("A\u00F1adir");
			btnAnadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CategoriaDto cate = sacarCategoriaSeleccionada();
					List<CategoriaDto> list = new ArrayList<>();
					if (cate != null) {
						int seleccion = opcionActualizarOno();
						if (seleccion == 1) {
							CategoriaDto catNueva = crearNuevaCategoria(cate);
							if (catNueva != null) {
								actualizarTablaCategorias(catNueva);
							} else {
								mostrarCategoriaInsertada();
							}
						} else if (seleccion == 0) {
							if (cate.getEdad_min() == 18) {
								mostrarVentanaActualizar(cate, cate.getEdad_min());
							} else {
								if (cate.getSexo().equals("masculino")) {
									list = cat.findCateBySex(id_comp, "masculino");
									int max = encontrarMaximaEdad(list);
									mostrarVentanaActualizar(cate, max + 1);
								} else if (cate.getSexo().equals("femenino")) {
									list = cat.findCateBySex(id_comp, "femenino");
									int max = encontrarMaximaEdad(list);
									mostrarVentanaActualizar(cate, max + 1);
								}

							}
						}

					}
				}

			});
			btnAnadir.setForeground(Color.WHITE);
			btnAnadir.setBackground(Color.GREEN);
			btnAnadir.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnAnadir.setBounds(364, 386, 89, 23);
		}

		return btnAnadir;

	}

	private void mostrarVentanaActualizar(CategoriaDto cate, int edad) {

		// CompeticionDto competicion = crearCompeticion();
		VentanaCreacionActualizaCate vPal = new VentanaCreacionActualizaCate(this, cate, this.id_comp, edad);

		vPal.setLocationRelativeTo(this);

		vPal.setVisible(true);

	}

	private void mostrarVentanaActualizar(int edad, String genero) {
		// CompeticionDto competicion = crearCompeticion();
		VentanaCreacionActualizaCate vPal = new VentanaCreacionActualizaCate(this, id_comp, edad, genero);

		vPal.setLocationRelativeTo(this);
		vPal.setVisible(true);

	}

	private int opcionActualizarOno() {

		return JOptionPane.showOptionDialog(null, "¿Desea modificar esta categoría estandar?", "Seleccione una opción:",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono por defecto.
				new Object[] { "Si", "No" }, // null para YES, NO y CANCEL
				"opcion 1");
	}

	private void mostrarCategoriaInsertada() {
		JOptionPane.showMessageDialog(this, "Categoría ya insertada");
	}

	public void actualizarTablaCategorias(CategoriaDto catNueva) {
		int size = tableCate.getRowCount();
		tableCate = new JTable();
		tableCate.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		tableCate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tableCate.setSelectionBackground(Color.YELLOW);
		tableCate.setBackground(Color.LIGHT_GRAY);
		btnConfirmar.setEnabled(true);
		String[] tabla = { "Id", "Nombre", "Edad Min", "Edad Max", "Sexo" };
		DefaultTableModel modelo = new DefaultTableModel() {
			public boolean isCellEditable(int fila, int columnas) {
				if (columnas == 5)
					return true;
				return false;

			}
		};
		tableCate.setModel(modelo);
		modelo.addColumn("Iden");
		modelo.addColumn("Nombre");
		modelo.addColumn("Edad Min");
		modelo.addColumn("Edad Max");
		modelo.addColumn("Sexo");
		List<CategoriaDto> competiciones = cat.getCategoriasPropias(this.id_comp);
		String[][] info = new String[competiciones.size()][5];
		// List<AtletaDto> atletas = getAtletas();
		// List<InscripcionDto> inscripciones = getInscripciones();
		float cuota = 0;
		String fecha = "";
		for (int i = 0; i < competiciones.size(); i++) {
			info[i][0] = competiciones.get(i).getId();
			info[i][1] = competiciones.get(i).getNombre();
			info[i][2] = String.valueOf(competiciones.get(i).getEdad_min());
			info[i][3] = String.valueOf(competiciones.get(i).getEdad_max());
			info[i][4] = competiciones.get(i).getSexo();
			modelo.addRow(info[i]);
		}
		scrollPane_1.setViewportView(tableCate);
	}

	private CategoriaDto crearNuevaCategoria(CategoriaDto cate) {
		boolean aux = false;
		List<CategoriaDto> categorias = new ArrayList<>();
		String id = UUID.randomUUID().toString();
		String[] nombrePartido = cate.getNombre().split(" ");
		String nombre = nombrePartido[0];
		int edadmin = cate.getEdad_min();
		int edadmax = cate.getEdad_max();
		String sexo = cate.getSexo();
		if (tableCate.getRowCount() > 0) {
			if (!compruebaCategoriaNoInsertada(cate, nombre)) {
				cat.insertarNueva(id, nombre, edadmin, edadmax, sexo);
				cat.insertarPertenece(id, id_comp);
				categorias = cat.findById(id);
			}
		} else {
			cat.insertarNueva(id, nombre, edadmin, edadmax, sexo);
			cat.insertarPertenece(id, id_comp);
			categorias = cat.findById(id);
		}
		if (categorias.size() > 0)
			return categorias.get(0);
		return null;
	}

	private boolean compruebaCategoriaNoInsertada(CategoriaDto cate, String nombre) {
		List<CategoriaDto> list = cat.findCategoriaByTodo(this.id_comp, nombre, cate.getEdad_min(), cate.getEdad_max(),
				cate.getSexo());
		if (list.size() > 0)
			return true;
		return false;

	}

	private CategoriaDto sacarCategoriaSeleccionada() {
		List<CategoriaDto> numMasculino = cat.cuentaCategoriasMas(this.id_comp);
		List<CategoriaDto> numFemenino = cat.cuentaCategoriasFem(this.id_comp);
		String id = "";
		List<CategoriaDto> cate = new ArrayList<>();
		int fila = tableEstandar.getSelectedRow();
		int edad_min = 0;
		if (fila == -1) {
			JOptionPane.showMessageDialog(this, "No hay categoria seleccionada");
			return null;
		}
		if (tableEstandar.getValueAt(fila, 4).equals("masculino")) {
			if (numMasculino.size() > 0) {
				List<CategoriaDto> list = cat.findCateBySex(this.id_comp, "masculino");
				int max = encontrarMaximaEdad(list);
				String min = (String) tableEstandar.getValueAt(fila, 2);
				if (max + 1 == Integer.parseInt(min)) {
					id = (String) tableEstandar.getValueAt(fila, 0);
					cate = cat.findById(id);
				} else {
					JOptionPane.showMessageDialog(this,
							"No deben existir huecos entre las edade o categoria ya insertada");
				}
			} else {
				edad_min = Integer.parseInt((String) tableEstandar.getValueAt(fila, 2));
				if (edad_min == 18) {
					id = (String) tableEstandar.getValueAt(fila, 0);
					cate = cat.findById(id);
				} else {
					JOptionPane.showMessageDialog(this,
							"La primera categoria seleccionada debe comenzar apartir de la edad minima 18");
				}
			}
		} else {
			if (numFemenino.size() > 0) {
				List<CategoriaDto> list = cat.findCateBySex(this.id_comp, "femenino");
				int max = encontrarMaximaEdad(list);
				String min = (String) tableEstandar.getValueAt(fila, 2);
				if (max + 1 == Integer.parseInt(min)) {
					id = (String) tableEstandar.getValueAt(fila, 0);
					cate = cat.findById(id);
				} else {
					JOptionPane.showMessageDialog(this,
							"No deben existir huecos entre las edades o categoria ya insertada");
				}
			} else {
				edad_min = Integer.parseInt((String) tableEstandar.getValueAt(fila, 2));
				if (edad_min == 18) {
					id = (String) tableEstandar.getValueAt(fila, 0);
					cate = cat.findById(id);
				} else {
					JOptionPane.showMessageDialog(this,
							"La primera categoria seleccionada debe comenzar apartir de la edad minima 18");
				}
			}
		}

		if (cate.size() > 0)
			return cate.get(0);
		return null;

	}

	private int encontrarMaximaEdad(List<CategoriaDto> list) {
		int max = 0;
		for (CategoriaDto categoriaDto : list) {
			if (categoriaDto.getEdad_max() > max)
				max = categoriaDto.getEdad_max();
		}
		return max;
	}

	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrarVentana();
				}

			});
			btnConfirmar.setForeground(Color.WHITE);
			btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnConfirmar.setBackground(Color.GREEN);
			btnConfirmar.setBounds(811, 388, 107, 23);
		}
		return btnConfirmar;
	}

	private void cerrarVentana() {
		int respuesta = JOptionPane.showConfirmDialog(null, "Estan listas las categorias?", "Confirmar categorias",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (respuesta == 0) {
			vcc.prepararVuelta();
			this.dispose();
		}

	}

	private JButton getBtnCrear() {
		if (btnCrear == null) {
			btnCrear = new JButton("Crear");
			btnCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int opcion = opcionSexo();
					if (opcion == 0) {
						List<CategoriaDto> numMasculino = cat.cuentaCategoriasMas(id_comp);
						if (numMasculino.size() == 0) {
							mostrarVentanaActualizar(18, "masculino");
						} else {
							List<CategoriaDto> list = cat.findCateBySex(id_comp, "masculino");
							int max = encontrarMaximaEdad(list);
							mostrarVentanaActualizar(max + 1, "masculino");
						}
					} else if (opcion == 1) {
						List<CategoriaDto> numFemenino = cat.cuentaCategoriasFem(id_comp);
						if (numFemenino.size() == 0) {
							mostrarVentanaActualizar(18, "femenino");
						} else {
							List<CategoriaDto> list = cat.findCateBySex(id_comp, "femenino");
							int max = encontrarMaximaEdad(list);
							mostrarVentanaActualizar(max + 1, "femenino");
						}
					}
				}

			});
			btnCrear.setForeground(Color.WHITE);
			btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnCrear.setBackground(Color.YELLOW);
			btnCrear.setBounds(704, 388, 97, 23);
		}
		return btnCrear;
	}

	private int opcionSexo() {
		return JOptionPane.showOptionDialog(null, "¿Para que genero es la categoria?", "Seleccione una opción:",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // null para icono por defecto.
				new Object[] { "Masculino", "Femenino" }, // null para YES, NO y CANCEL
				"opcion 1");
	}

	private JTable getTableCate() {
		if (tableCate == null) {
			tableCate = new JTable();
			tableCate = new JTable();
			tableCate.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
			tableCate.setFont(new Font("Tahoma", Font.PLAIN, 13));
			tableCate.setSelectionBackground(Color.YELLOW);
			tableCate.setBackground(Color.LIGHT_GRAY);
			String[] tabla = { "Id", "Nombre", "Edad Min", "Edad Max", "Sexo" };
			DefaultTableModel modelo = new DefaultTableModel() {
				public boolean isCellEditable(int fila, int columnas) {
					if (columnas == 5)
						return true;
					return false;

				}
			};
			tableCate.setModel(modelo);
			modelo.addColumn("Iden");
			modelo.addColumn("Nombre");
			modelo.addColumn("Edad Min");
			modelo.addColumn("Edad Max");
			modelo.addColumn("Sexo");
			// List<CategoriaDto> competiciones = cat.getCategorias();
			// String[][] info = new String[competiciones.size()][5];
			// //List<AtletaDto> atletas = getAtletas();
			// //List<InscripcionDto> inscripciones = getInscripciones();
			// float cuota=0;
			// String fecha ="";
			// for(int i = 0; i < competiciones.size(); i++) {
			// info[i][0] = competiciones.get(i).getId();
			// info[i][1] = competiciones.get(i).getNombre();
			// info[i][2] = String.valueOf(competiciones.get(i).getEdad_min());
			// info[i][3] = String.valueOf(competiciones.get(i).getEdad_max());
			// info[i][4] = competiciones.get(i).getSexo();
			// modelo.addRow(info[i]);
			// }
		}
		System.out.println(tableCate.getRowCount());
		return tableCate;
	}
}
