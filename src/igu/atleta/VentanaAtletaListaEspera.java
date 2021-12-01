package igu.atleta;

import java.awt.Color;
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
import logica.competicion.CompeticionDto;
import logica.listaEspera.ListaEsperaDto;
import logica.listaEspera.ListaEsperaModel;

import java.awt.Toolkit;

public class VentanaAtletaListaEspera extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblMeterEmail;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JButton btnSiguiente;
	@SuppressWarnings("unused")
	private VentanaMostrarCarreras vmc;
	private CompeticionDto competicion;
	private AtletaModel atl;
	private ListaEsperaModel lem;
	private JLabel lblNewLabel;

	/**
	 * Create the frame.
	 * 
	 * @param competicion
	 * @param ventanaMostrarCarreras
	 */
	public VentanaAtletaListaEspera(VentanaMostrarCarreras ventanaMostrarCarreras, CompeticionDto competicion) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAtletaListaEspera.class.getResource("/img/icono-plano-de-la-bandera-carreras-con-sombra-larga-colorido-198376094.jpg")));
		this.vmc = ventanaMostrarCarreras;
		this.lem = new ListaEsperaModel();
		this.competicion = competicion;
		this.atl = new AtletaModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 208);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblMeterEmail());
		contentPane.add(getLblEmail());
		contentPane.add(getTxtEmail());
		contentPane.add(getBtnSiguiente());
		contentPane.add(getLblNewLabel());
	}

	private JLabel getLblMeterEmail() {
		if (lblMeterEmail == null) {
			lblMeterEmail = new JLabel("Para inscribirte en la lista de espera de la competición, por favor ");
			lblMeterEmail.setBounds(16, 10, 441, 33);
		}
		return lblMeterEmail;
	}

	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email:");
			lblEmail.setBounds(16, 78, 50, 16);
		}
		return lblEmail;
	}

	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setBounds(73, 73, 225, 26);
			txtEmail.setColumns(10);
		}
		return txtEmail;
	}

	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (registradoAtletaEnBase()) {
						// Si está en la base
						// Una vez que meta el correo sse le añade a la lista de espera
						addAListaDeEspera();
					} else {
						// Si no está en la base se le preguntará si se quiere registrar
						registroDeAtleta();
					}
				}
			});
			btnSiguiente.setBounds(16, 111, 117, 29);
		}
		return btnSiguiente;
	}

	private boolean registradoAtletaEnBase() {
		if (atl.atletaYaRegistradoEnLaBase(txtEmail.getText()).isEmpty()) {
			return false;
		} else
			return true;
	}

	private void registroDeAtleta() {
		int reply = JOptionPane.showConfirmDialog(this, "No est�s registrado, �quieres registrarte?",
				"Ventana registro", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			// Se le envia a la ventana de registro
			VentanaRegistro vPal = new VentanaRegistro(null, this);
			vPal.setLocationRelativeTo(this);
			vPal.setVisible(true);
		} else {
			// NO
			JOptionPane.showMessageDialog(this, "�Hasta la pr�xima!");
		}
	}

	private void addAListaDeEspera() {
		ListaEsperaDto lista = lem.getListaByIdComp(this.competicion.getId());
		// Cojo el número de orden que le toca al atleta
		int orden;
		if (atl.hayGenteEnLista(lista.getId())) {
			orden = lem.getNextNumOrden(lista.getId());
		} else {
			orden = 1;
		}
		// Cojo el id del atleta
		String email = txtEmail.getText();
		AtletaDto a = atl.findAtletaByEmail(email);
		String dnia = a.getDni();
		atl.addAtletaAListaEspera(dnia, lista.getId(), orden);
		JOptionPane.showMessageDialog(this, "Ya est� a�adido a la lista de espera de " + this.competicion.getNombre()
				+ ", su posici�n en la lista es " + orden);
		System.exit(0);
	}

	public CompeticionDto getCompeticion() {
		return this.competicion;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("introduzca su email:");
			lblNewLabel.setBounds(16, 42, 132, 13);
		}
		return lblNewLabel;
	}
}
