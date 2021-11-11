package igu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logica.CategoriaDto;
import logica.CategoriaModel;

@SuppressWarnings("serial")
public class VentanaCreacionActualizaCate extends JFrame {

	private JPanel contentPane;
	private JTextArea txtrAadaLosDatos;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblEdadMin;
	private JTextField txtEdadMin;
	private JLabel lblEdadMax;
	private JTextField txtEdadMax;
	private JLabel lblSexo;
	private JTextField txtSexo;
	private JButton btnValidar;
	private VentanaCategorias vcc;
	private CategoriaDto c;
	private String id_comp;
	private CategoriaModel cat;

	/**
	 * Create the frame.
	 */
	public VentanaCreacionActualizaCate() {
		cat = new CategoriaModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 298);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		contentPane.add(getTxtrAadaLosDatos());
		contentPane.add(getLblNombre());
		contentPane.add(getTxtNombre());
		contentPane.add(getLblEdadMin());
		contentPane.add(getLblEdadMin());
		contentPane.add(getTxtEdadMin());
		contentPane.add(getLblEdadMax());
		contentPane.add(getTxtEdadMax());
		contentPane.add(getLblSexo());
		contentPane.add(getTxtSexo());
		contentPane.add(getBtnValidar());
	}
	
	public VentanaCreacionActualizaCate(VentanaCategorias ventanaCategorias, CategoriaDto cate, String id_comp2) {
		this.vcc=ventanaCategorias;
		this.c=cate;
		this.id_comp=id_comp2;
		cat = new CategoriaModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 298);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTxtrAadaLosDatos());
		contentPane.add(getLblNombre());
		contentPane.add(getTxtNombre());
		contentPane.add(getLblEdadMin());
		contentPane.add(getLblEdadMin());
		contentPane.add(getTxtEdadMin());
		contentPane.add(getLblEdadMax());
		contentPane.add(getTxtEdadMax());
		contentPane.add(getLblSexo());
		contentPane.add(getTxtSexo());
		contentPane.add(getBtnValidar());
	}

	public VentanaCreacionActualizaCate(VentanaCategorias ventanaCategorias, CategoriaDto cate, String id_comp2,
			int edad) {
		this.vcc=ventanaCategorias;
		this.c=cate;
		this.id_comp=id_comp2;
		cat = new CategoriaModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 298);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTxtrAadaLosDatos());
		contentPane.add(getLblNombre());
		contentPane.add(getTxtNombre());
		contentPane.add(getLblEdadMin());
		contentPane.add(getLblEdadMin());
		contentPane.add(getTxtEdadMin());
		contentPane.add(getLblEdadMax());
		contentPane.add(getTxtEdadMax());
		contentPane.add(getLblSexo());
		contentPane.add(getTxtSexo());
		contentPane.add(getBtnValidar());
		txtEdadMin.setText(String.valueOf(edad));
		txtEdadMin.setEditable(false);
		String[] nombrePartido = cate.getNombre().split(" ");
		String nombre = nombrePartido[0];
		txtNombre.setText(nombre);
		txtSexo.setText(cate.getSexo());
		txtSexo.setEditable(false);
	}

	public VentanaCreacionActualizaCate(VentanaCategorias ventanaCategorias, String id_comp2, int edad,String genero) {
		this.vcc=ventanaCategorias;
		this.id_comp=id_comp2;
		cat = new CategoriaModel();
		c = new CategoriaDto();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 298);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTxtrAadaLosDatos());
		contentPane.add(getLblNombre());
		contentPane.add(getTxtNombre());
		contentPane.add(getLblEdadMin());
		contentPane.add(getLblEdadMin());
		contentPane.add(getTxtEdadMin());
		contentPane.add(getLblEdadMax());
		contentPane.add(getTxtEdadMax());
		contentPane.add(getLblSexo());
		contentPane.add(getTxtSexo());
		contentPane.add(getBtnValidar());
		txtEdadMin.setText(String.valueOf(edad));
		txtEdadMin.setEditable(false);
		txtSexo.setText(genero);
		txtSexo.setEditable(false);
	}

	public VentanaCreacionActualizaCate(VentanaCategorias ventanaCategorias, String id_comp2) {
		this.vcc=ventanaCategorias;
		this.id_comp=id_comp2;
		cat = new CategoriaModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 298);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTxtrAadaLosDatos());
		contentPane.add(getLblNombre());
		contentPane.add(getTxtNombre());
		contentPane.add(getLblEdadMin());
		contentPane.add(getLblEdadMin());
		contentPane.add(getTxtEdadMin());
		contentPane.add(getLblEdadMax());
		contentPane.add(getTxtEdadMax());
		contentPane.add(getLblSexo());
		contentPane.add(getTxtSexo());
		contentPane.add(getBtnValidar());
	}

	private JTextArea getTxtrAadaLosDatos() {
		if (txtrAadaLosDatos == null) {
			txtrAadaLosDatos = new JTextArea();
			txtrAadaLosDatos.setEditable(false);
			txtrAadaLosDatos.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtrAadaLosDatos.setText("A\u00F1ada los datos de la nueva categor\u00EDa:");
			txtrAadaLosDatos.setBounds(29, 11, 563, 30);
		}
		return txtrAadaLosDatos;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNombre.setBounds(29, 64, 67, 14);
		}
		return lblNombre;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtNombre.setBounds(106, 60, 345, 23);
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}
	private JLabel getLblEdadMin() {
		if (lblEdadMin == null) {
			lblEdadMin = new JLabel("Edad min:");
			lblEdadMin.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblEdadMin.setBounds(29, 105, 67, 14);
		}
		return lblEdadMin;
	}
	private JTextField getTxtEdadMin() {
		if (txtEdadMin == null) {
			txtEdadMin = new JTextField();
			txtEdadMin.setBounds(106, 102, 214, 23);
			txtEdadMin.setColumns(10);
		}
		return txtEdadMin;
	}
	private JLabel getLblEdadMax() {
		if (lblEdadMax == null) {
			lblEdadMax = new JLabel("Edad max:");
			lblEdadMax.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblEdadMax.setBounds(29, 149, 67, 14);
		}
		return lblEdadMax;
	}
	private JTextField getTxtEdadMax() {
		if (txtEdadMax == null) {
			txtEdadMax = new JTextField();
			txtEdadMax.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtEdadMax.setColumns(10);
			txtEdadMax.setBounds(106, 145, 214, 23);
		}
		return txtEdadMax;
	}
	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo:");
			lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblSexo.setBounds(29, 187, 67, 14);
		}
		return lblSexo;
	}
	private JTextField getTxtSexo() {
		if (txtSexo == null) {
			txtSexo = new JTextField();
			txtSexo.setColumns(10);
			txtSexo.setBounds(106, 185, 214, 21);
		}
		return txtSexo;
	}
	private JButton getBtnValidar() {
		if (btnValidar == null) {
			btnValidar = new JButton("Validar");
			btnValidar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (txtNombre.getText().equals("") || txtEdadMin.getText().equals("") || txtEdadMax.getText().equals("") || txtSexo.getText().equals("")) {
						System.out.println("Hola");
						mostrarCamposVacios();
					}else {
						if(!compruebaSoloNumeros(txtEdadMin.getText())) {
							mostrarEdadesMal();
							txtEdadMin.setText(""); 
						}else if(!compruebaSoloNumeros(txtEdadMax.getText())) {
							mostrarEdadesMal();
							txtEdadMax.setText(""); 
						}
						else if(!compruebaEdadMayorQueMin(txtEdadMax.getText())) {
							mostrarEdadesRangosMal();
							txtEdadMax.setText(""); 
						}else if(!comprobarTipo(txtSexo.getText())) {
							mostrarErrorTipo();
							txtSexo.setText("");
						}else {
							validar();
						}
						
					}
				}

				

				

				
			});
			btnValidar.setForeground(Color.WHITE);
			btnValidar.setBackground(Color.GREEN);
			btnValidar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnValidar.setBounds(381, 222, 89, 23);
		}
		return btnValidar;
	}
	
	private void validar() {
		String id = UUID.randomUUID().toString();
		c.setNombre(getTxtNombre().getText());
		c.setEdad_min(Integer.parseInt(getTxtEdadMin().getText()));
		c.setEdad_max(Integer.parseInt(getTxtEdadMax().getText()));
		c.setSexo(getTxtSexo().getText());
		c.setId(id);
		cat.insertarNueva(id, c.getNombre(), c.getEdad_min(), c.getEdad_max(), c.getSexo());
		cat.insertarPertenece(id, this.id_comp);
		JOptionPane.showMessageDialog(this, "Se ha creado la nueva categoria");
		vcc.actualizarTablaCategorias(c);
		this.dispose();
		
	}
	
	private boolean compruebaEdadMayorQueMin(String text) {
		int min = Integer.parseInt(getTxtEdadMin().getText());
		int max = Integer.parseInt(getTxtEdadMax().getText());
		if (min < max )
			return true;
		return false;
	}
	
	private void mostrarCamposVacios() {
		JOptionPane.showMessageDialog(this, "Error: Campos vacios.");

	}
	
	private void mostrarEdadesMal() {
		JOptionPane.showMessageDialog(this, "Error: Las edades solo pueden ser numeros");

	}
	
	private void mostrarEdadesRangosMal() {
		JOptionPane.showMessageDialog(this, "Error: Las edad Maxima debe ser menor que la minima");

	}
	
	private boolean compruebaSoloNumeros(String text) {
		String numero="";
		String minumero="";
		int textsize = text.length();
		String[] numeros= {"0","1","2","3","4","5","6","7","8","9"};
		for (int i=0;i<text.length();i++) {
			numero=text.substring(i,i+1);
			for (int j=0;j<numeros.length;j++) {
				if (numero.equals(numeros[j])) {
					minumero=minumero+numeros[j];
				}
			}
		}
		if (minumero.length()==textsize) {
			return true;
		}else
			return false;
	}
	
	private boolean comprobarTipo(String text) {
		if (text.equals("Femenino") || text.equals("femenino") || text.equals("FEMENINO") || text.equals("MASCULINO") || text.equals("Masculino") || text.equals("masculino"))
			return true;
		return false;
	}
	
	private void mostrarErrorTipo() {
		JOptionPane.showMessageDialog(this, "Erro: El sexo debe ser masculino/femenino");
		
	}

}
