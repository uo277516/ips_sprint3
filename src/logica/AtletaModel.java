package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import util.BaseDatos;
import util.DtoAssembler;

public class AtletaModel {

	public static String sql1 = "select * from atleta";
	public static String sql2 = "select * from atleta, inscripcion, competicion" + "		where "
			+ " 	atleta.dni = inscripcion.dni_a and " + "     inscripcion.id_c = competicion.id and "
			+ "		inscripcion.email=? and " + "     competicion.nombre=?";
	public static String sql3 = "select * from atleta where atleta.email=?";
	public static String COMPID_ATL = "select a.dni, a.nombre, a.sexo, a.f_nac, a.email"
			+ " from atleta as a, inscripcion as i" + " where a.dni = i.dni_a" + " and i.id_c = ?";

	public static String sqlFindByDni = "select * from atleta where dni=?";
	public static String sqlFindByEmail = "select * from atleta where email=?";
	public static String sqlFindById = "select * from atleta where id=?";
	// String dni, String nombre, String sexo, String fecha, String email
	public static String sqlAdd = "insert into Atleta(dni, nombre, sexo, f_nac, email) values (?,?,?,?,?)";

	public static String findAtletaByDniId = "select * from atleta, inscripcion, competicion" + "		where "
			+ " 	atleta.dni = inscripcion.dni_a and " + "     inscripcion.id_c = competicion.id and "
			+ "		inscripcion.dni_a=? and " + "     competicion.nombre=?";
	public static String addAtletaAListaEspera = "insert into en_espera(id_listaespera, dni_atleta, num_orden) values(?,?,?)";
	public static String hayGenteEnLista = "select * from atleta a, en_espera e where e.id_listaespera = ? and e.dni_atleta = a.dni";

	public List<AtletaDto> getAtletas() throws SQLException {
		return getAllAtletas();
	}

	private List<AtletaDto> getAllAtletas() throws SQLException {
		List<AtletaDto> listaAtletas = new ArrayList<AtletaDto>();

		// Conexiï¿½n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql1);
			rs = pst.executeQuery();

			// Aï¿½adimos los pedidos a la lista
			listaAtletas = DtoAssembler.toAtletaDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

		for (AtletaDto atletaDto : listaAtletas) {
			System.out.println(atletaDto);
		}
		return listaAtletas;
	}

	public boolean atletaAlredyRegistred(String email, String cmpe) {
		boolean op = false;
		try {
			op = yaEstaRegistrado(email, cmpe);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return op;
	}

	/*
	 * true si ya esta registrado, false si no (lo guay false)
	 */
	private boolean yaEstaRegistrado(String emailAtleta, String nombreCompe) throws SQLException {
		List<AtletaDto> listaAtletas = new ArrayList<AtletaDto>();

		// Conexiï¿½n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql2);
			pst.setString(1, emailAtleta);
			pst.setString(2, nombreCompe);
			rs = pst.executeQuery();

			// Aï¿½adimos los pedidos a la lista
			listaAtletas = DtoAssembler.toAtletaDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

//        for (AtletaDto atletaDto : listaAtletas) {
//			System.out.println(atletaDto.getDni() + " " + atletaDto.getF_nac()
//			);
//		}
		if (listaAtletas.size() > 0) { // si ya esta registrado en esa carrera
			System.out.println("Ya se ha registrado en esta competicion");
			return true;
		} else {
			System.out.println("no registrado, puede registrarse");
			return false;
		}
	}

	public boolean findAtletaByDniId(String dni, String cmpe) {
		boolean op = false;
		try {
			op = findAtletaByDniIdP(dni, cmpe);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return op;
	}

	/*
	 * true si ya esta registrado, false si no (lo guay false)
	 */
	private boolean findAtletaByDniIdP(String dniAtleta, String nombreCompe) throws SQLException {
		List<AtletaDto> listaAtletas = new ArrayList<AtletaDto>();

		// Conexiï¿½n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(findAtletaByDniId);
			pst.setString(1, dniAtleta);
			pst.setString(2, nombreCompe);
			rs = pst.executeQuery();

			// Aï¿½adimos los pedidos a la lista
			listaAtletas = DtoAssembler.toAtletaDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

//        for (AtletaDto atletaDto : listaAtletas) {
//			System.out.println(atletaDto.getDni() + " " + atletaDto.getF_nac()
//			);
//		}
		if (listaAtletas.size() > 0) { // si ya esta registrado en esa carrera
			System.out.println("Ya se ha registrado en esta competicion");
			return true;
		} else {
			System.out.println("no registrado, puede registrarse");
			return false;
		}
	}

	public boolean atletaEnBase(String email) {
		boolean op = false;
		try {
			op = atletaEnBaseP(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return op;
	}

	public boolean atletaEnBaseP(String email) throws SQLException {
		boolean op = false;
		// Conexiï¿½n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql3);
			pst.setString(1, email);
			rs = pst.executeQuery();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (rs == null) {
				op = false;
			} else {
				rs.close();
				op = true;
			}

			pst.close();
			c.close();
		}
		return op;
	}

	public List<AtletaDto> atletaYaRegistradoEnLaBase(String email) {
		List<AtletaDto> atletas = new ArrayList<AtletaDto>();
		try {
			atletas = atletaYaRegistradoEnLaBaseP(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("algo mal ventana atleta model");
		}
		return atletas;
	}

	private List<AtletaDto> atletaYaRegistradoEnLaBaseP(String email) throws SQLException {
		List<AtletaDto> atletas = new ArrayList<AtletaDto>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql3);
			pst.setString(1, email);
			rs = pst.executeQuery();

			atletas = DtoAssembler.toAtletaDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

		return atletas;
	}

	public List<AtletaDto> getAletasDeUnaCompeticion(String id) throws SQLException {
		List<AtletaDto> atletas = new ArrayList<AtletaDto>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(COMPID_ATL);
			pst.setString(1, id);
			rs = pst.executeQuery();

			atletas = DtoAssembler.toAtletaDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

		return atletas;
	}

	public int yearsAtleta(String email) {
		int years = 0;
		try {
			years = yearsAtletaByEmail(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return years;
	}

	private int yearsAtletaByEmail(String email) throws SQLException {
		int years = 0;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql3);
			pst.setString(1, email);
			rs = pst.executeQuery();
			rs.next();
			String fecha = rs.getString("f_nac");
			int year = Integer.valueOf((fecha.split("/")[2]));
			int yearActual = LocalDate.now().getYear();
			years = yearActual - year;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}
		return years;
	}

	public AtletaDto findAtletaByDni(String dni) {
		AtletaDto a = null;
		try {
			a = findAtletaByDniP(dni);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	private AtletaDto findAtletaByDniP(String dni) throws SQLException {
		AtletaDto a;

		// Conexiï¿½n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlFindByDni);
			pst.setString(1, dni);
			rs = pst.executeQuery();

			rs.next();
			a = DtoAssembler.toAtletaDto(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}
		return a;
	}

	public AtletaDto findAtletaByEmail(String email) {
		AtletaDto a = null;
		try {
			a = findAtletaByEmailP(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;

	}

	private AtletaDto findAtletaByEmailP(String email) throws SQLException {
		AtletaDto a;

		// Conexiï¿½n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlFindByEmail);
			pst.setString(1, email);
			rs = pst.executeQuery();

			rs.next();
			a = DtoAssembler.toAtletaDto(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}
		return a;
	}

	public AtletaDto findAtletaById(String id) throws SQLException {
		AtletaDto a;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlFindById);
			pst.setString(1, id);
			rs = pst.executeQuery();

			rs.next();
			a = DtoAssembler.toAtletaDto(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}
		return a;
	}

	public void addAtleta(String dni, String nombre, String sexo, String fecha, String email) {
		try {
			addAtletaP(dni, nombre, sexo, fecha, email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addAtletaP(String dni, String nombre, String sexo, String fecha, String email) throws SQLException {

		// Conexión a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		// ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlAdd);
			pst.setString(1, dni);
			pst.setString(2, nombre);
			pst.setString(3, sexo);
			pst.setString(4, fecha);
			pst.setString(5, email);
			pst.executeUpdate();

//			rs.next();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			// rs.close();
			pst.close();
			c.close();
		}
	}

	public List<AtletaDto> atletaYaEnBaseDatosDni(String dni) {
		List<AtletaDto> list = new ArrayList<>();
		try {
			list = atletaYaEnBaseDatosDniP(dni);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private List<AtletaDto> atletaYaEnBaseDatosDniP(String dni) throws SQLException {
		List<AtletaDto> atletas = new ArrayList<AtletaDto>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlFindByDni);
			pst.setString(1, dni);
			rs = pst.executeQuery();

			atletas = DtoAssembler.toAtletaDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

		return atletas;
	}

	public List<AtletaDto> atletaYaRegistradoEnLaBaseDni(String dni) {
		List<AtletaDto> atletas = new ArrayList<AtletaDto>();
		try {
			atletas = atletaYaRegistradoEnLaBaseDniP(dni);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("algo mal ventana atleta model");
		}
		return atletas;
	}

	private List<AtletaDto> atletaYaRegistradoEnLaBaseDniP(String email) throws SQLException {
		List<AtletaDto> atletas = new ArrayList<AtletaDto>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement("select * from atleta where atleta.dni=?");
			pst.setString(1, email);
			rs = pst.executeQuery();

			atletas = DtoAssembler.toAtletaDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

		return atletas;
	}

	public void addAtletaAListaEspera(String dniA, String idL, int orden) {
		try {
			addAtletaAListaEsperaP(dniA, idL, orden);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addAtletaAListaEsperaP(String dniA, String idL, int orden) throws SQLException {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(addAtletaAListaEspera);
			pst.setString(1, idL);
			pst.setString(2, dniA);
			pst.setInt(3, orden);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}
	}

	public boolean hayGenteEnLista(String idLista) {
		boolean ret = false;
		try {
			ret = hayGenteEnListaP(idLista);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	private boolean hayGenteEnListaP(String idLista) throws SQLException {
		List<AtletaDto> listaAtletas = new ArrayList<AtletaDto>();

		// Conexiï¿½n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(hayGenteEnLista);
			pst.setString(1, idLista);
			rs = pst.executeQuery();

			// Aï¿½adimos los pedidos a la lista
			listaAtletas = DtoAssembler.toAtletaDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}
		if (listaAtletas.size() > 0) { // si ya esta registrado en esa carrera
			System.out.println("Ya se ha registrado en esta competicion");
			return true;
		} else {
			System.out.println("no registrado, puede registrarse");
			return false;
		}
	}

	public int calcularEdad(String fechaNac) {
		if (fechaNac != null) {
			String[] trozos = fechaNac.split("/");
			Integer añoNac = Integer.parseInt(trozos[2]);
			return LocalDateTime.now().getYear() - añoNac;
		}
		return 0;
	}
}
