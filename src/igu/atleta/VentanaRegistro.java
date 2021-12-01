package igu.atleta;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logica.atleta.AtletaDto;
import logica.atleta.AtletaModel;
import logica.listaEspera.ListaEsperaDto;
import logica.listaEspera.ListaEsperaModel;

public class VentanaRegistro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VentanaInscripcion vI;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtFecha;
	private JTextField txtEmail;

	private JTextField txtSExo;
	private AtletaModel at;
	private VentanaAtletaListaEspera vale;
	private ListaEsperaModel lem;

	//	/**
	//	 * Launch the application.
	//	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					VentanaRegistro frame = new VentanaRegistro();
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
	 * @param ventanaInscripcion
	 */
	public VentanaRegistro(VentanaInscripcion ventanaInscripcion, VentanaAtletaListaEspera ventanaAtletaListaEspera) {
		setTitle("Registro");
		
		if (ventanaInscripcion != null) {
			this.vI = ventanaInscripcion;
		}
		if (ventanaAtletaListaEspera != null) {
			this.vale = ventanaAtletaListaEspera;
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAtletaListaEspera.class.getResource("/img/icono-plano-de-la-bandera-carreras-con-sombra-larga-colorido-198376094.jpg")));

		at = new AtletaModel();
		this.lem = new ListaEsperaModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 347);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInfo = new JLabel(
				"Para registrarse, por favor ingrese su DNI, nombre, fecha de nacimiento, email y sexo.\r\n");
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInfo.setBounds(10, 37, 519, 21);
		contentPane.add(lblInfo);

		JLabel lblDNi = new JLabel("DNI:");
		lblDNi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDNi.setBounds(10, 85, 45, 13);
		contentPane.add(lblDNi);

		JLabel lblNimbre = new JLabel("Nombre y apellidos:");
		lblNimbre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNimbre.setBounds(10, 118, 135, 13);
		contentPane.add(lblNimbre);

		JLabel lblFecha = new JLabel("Fecha de nacimiento:");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFecha.setBounds(10, 157, 135, 13);
		contentPane.add(lblFecha);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(10, 197, 45, 13);
		contentPane.add(lblEmail);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSexo.setBounds(10, 235, 45, 13);
		contentPane.add(lblSexo);

		txtDni = new JTextField();
		txtDni.setBounds(63, 83, 175, 19);
		contentPane.add(txtDni);
		txtDni.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(142, 116, 272, 19);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtFecha = new JTextField();
		txtFecha.setBounds(142, 155, 175, 19);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(65, 195, 173, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		txtSExo = new JTextField();
		txtSExo.setBounds(65, 233, 173, 19);
		contentPane.add(txtSExo);
		txtSExo.setColumns(10);

		JLabel lblSexoDes = new JLabel("femenino/masculino");
		lblSexoDes.setForeground(new Color(128, 128, 128));
		lblSexoDes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSexoDes.setBounds(264, 236, 129, 13);
		contentPane.add(lblSexoDes);

		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkCamposNoVacios() && checkFechaFormato() && checkSexo() && !registradoAtletaEnBase()
						&& !registradoAtletaEnBaseDni()) {
					System.out.println("todo ok");
					addAtleta();
					if (vI != null) {
						mostrarVentanaInscripcionAgain();
					} else if (vale != null) {
						// A lista de espera
						aListaDeEspera();
					}

				}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(449, 276, 85, 21);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("dd/mm/yyyy");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(348, 158, 103, 13);
		contentPane.add(lblNewLabel);
	}
	
	private void aListaDeEspera() {
		ListaEsperaDto lista = lem.getListaByIdComp(this.vale.getCompeticion().getId());
		// Cojo el número de orden que le toca al atleta
		int orden;
		if (at.hayGenteEnLista(lista.getId())) {
			orden = lem.getNextNumOrden(lista.getId());
		} else {
			orden = 1;
		}
		// Cojo el id del atleta
		String email = txtEmail.getText();
		AtletaDto a = at.findAtletaByEmail(email);
		String dnia = a.getDni();
		at.addAtletaAListaEspera(dnia, lista.getId(), orden);
		JOptionPane.showMessageDialog(this, "Ya está añadido a la lista de espera de "
				+ this.vale.getCompeticion().getNombre() + ", su posición en la lista es " + orden);
		System.exit(0);
	}

	protected boolean registradoAtletaEnBaseDni() {
		if (at.atletaYaRegistradoEnLaBaseDni(txtDni.getText()).isEmpty()) {
			// si no hay ninguno
			return false;
		} else
			JOptionPane.showMessageDialog(this, "Este dni ya está registrado en la base. ");
		return true;
	}

	private boolean registradoAtletaEnBase() {
		if (at.atletaYaRegistradoEnLaBase(txtEmail.getText()).isEmpty()) {
			// si no hay ninguno
			return false;
		} else
			JOptionPane.showMessageDialog(this, "Este email ya está registrado en la base. ");
		return true;
	}

	protected void mostrarVentanaInscripcionAgain() {
		// TODO Auto-generated method stubthis.dispose();
		JOptionPane.showMessageDialog(this, "Te has registrado correctamente. ");
		this.vI.setEmail(txtEmail.getText());
		this.dispose();

	}

	protected void addAtleta() {
		// String dni, String nombre, String sexo, String fecha, String email
		at.addAtleta(txtDni.getText(), txtNombre.getText(), txtSExo.getText(), txtFecha.getText(), txtEmail.getText());
	}

	protected boolean checkFechaFormato() {
		String fecha = txtFecha.getText();
		String numero="";
		int contador =0;
		String minumero="";
		String[] posiciones = new String[fecha.length()];
		String[] numeros= {"0","1","2","3","4","5","6","7","8","9"};
		for (int i=0;i<fecha.length();i++) {
			numero=fecha.substring(i,i+1);
			for (int j=0;j<numeros.length;j++) {
				if (numero.equals(numeros[j])) {
					minumero=minumero+numeros[j];
				}
			}
			if (numero.equals("/")) {
				posiciones[i] = "/";
				contador++;
			}
		}
		if (contador==2 && minumero.length()==8) {
			if (posiciones[2] != null && posiciones[5]!=null) {
				return true;
			}else
				return false;
		}else
			return false;

	}

	protected boolean checkSexo() {
		if (txtSExo.getText().equals("femenino"))
			return true;
		else if (txtSExo.getText().equals("masculino"))
			return true;
		else
			return false;
		//		return true;
	}

	protected boolean checkCamposNoVacios() {
		if (txtDni.getText().isEmpty() || txtEmail.getText().isEmpty() || txtFecha.getText().isEmpty()
				|| txtNombre.getText().isEmpty() || txtSExo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ningún campo puede ser vacío.");
			return false;
		}
		return true;
	}
}
