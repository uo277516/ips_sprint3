package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import util.BaseDatos;
import util.DtoAssembler;

public class InscripcionModel {

	public static String sql1 = "select * from inscripcion";
	public static String sql2 = "select * from inscripcion where inscripcion.email=?";
	public static String sql3 = "insert into inscripcion (dni_a, id_c, email, estado,cantidad_pagada,fecha,categoria) values (?,?,?,'Pre-inscrito',?,?,?)";
	public static String sql4 = "select * from atleta where atleta.email=?";
	public static String sql6 = "select * from inscripcion where inscripcion.email=? order by inscripcion.fecha asc";
	public static String sql5 = "select * from inscripcion where inscripcion.dni_a=? order by inscripcion.fecha asc";
	public static String COMPID_INS = "select * from inscripcion where id_c=?";

	public static String CAT_INS_DNI_ID = "select categoria from inscripcion where dni_a = ? and id_c = ?";
	public static String sql6Ins = "select * from inscripcion where dni_a=? and id_c =?";

	public static String sql7UpdateEstado = "update inscripcion set estado=? where dni_a=? and id_c=?";
	public static String sqlUpdateCantidadPagada = "update inscripcion set cantidad_pagada=? where dni_a=? and id_c=?";
	public static String sql7UpdateFecha = "update inscripcion set fecha=? where dni_a=? and id_c=?";
	public static String sql7UpdatePago = "update inscripcion set metodo_pago=? where dni_a=? and id_c=?";

	public static String sql_InscripcionesPorTiempo = "select  * from inscripcion i, atleta a where i.dni_a = a.dni and id_c = ? order by horas is null, minutos is null, horas, minutos asc";
	public static String sql_InscripcionesPorTiempoYCategoria = "select  * from inscripcion i, atleta a where i.dni_a = a.dni and ? order by horas is null, minutos is null, horas, minutos asc";
	public static String sql_InscripcionesPorTiempoYSexo = "select  * from inscripcion i, atleta a where i.dni_a = a.dni and i.id_c = ? and a.sexo=? order by horas is null, minutos is null, horas, minutos asc";
	public static String sql_InscripcionesPorTiempoYEdad = "select * from inscripcion i, atleta a where i.dni_a = a.dni and i.id_c = ? and i.categoria = ? order by horas is null, minutos is null, horas, minutos asc";
	public static String sql_InscripcionesMetodoPago = "select * from inscripcion where metodo_pago=?";

	public static String sqlActualizarDorsales = "update inscripcion set dorsal=? where id_c=? and dni_a=?";
	public static String sqlActEnComp = "update competicion set d_asig=1 where id=?";

	public static String updateTimes = "update inscripcion set horas = ? , minutos = ? where dorsal = ? and id_c = ?";

	public AtletaDto findAtletaEmail(String email) {
		AtletaDto a = null;
		try {
			a = findAtletaByEmail(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	public AtletaDto findAtletaEmail2(String email) {
		AtletaDto a = null;
		try {
			a = findAtletaByEmail2(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	private AtletaDto findAtletaByEmail2(String email) throws SQLException {
		AtletaDto a = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql4);
			pst.setString(1, email);
			// System.out.println(pst);
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

	private AtletaDto findAtletaByEmail(String email) throws SQLException {
		AtletaDto a = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql4);
			pst.setString(1, email);
			// System.out.println(pst);
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

	public List<InscripcionDto> getInscripciones() throws SQLException {
		return getAllInscripciones();
	}

	private List<InscripcionDto> getAllInscripciones() throws SQLException {
		List<InscripcionDto> listaInscrpcines = new ArrayList<InscripcionDto>();

		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql1);
			rs = pst.executeQuery();

			// A�adimos los pedidos a la lista
			listaInscrpcines = DtoAssembler.toInscripcionDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

		for (InscripcionDto atletaDto : listaInscrpcines) {
			System.out.println(atletaDto);
		}
		return listaInscrpcines;
	}

	public boolean isEmailRegistred(String email) {
		boolean op = false;
		try {
			op = existsThisEmail(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("algo fue mal");
			e.printStackTrace();
		}
		return op;
	}

	private boolean existsThisEmail(String email) throws SQLException {
		List<InscripcionDto> listaAtletas = new ArrayList<InscripcionDto>();

		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql2);
			pst.setString(1, email);
			rs = pst.executeQuery();

			// A�adimos los pedidos a la lista
			listaAtletas = DtoAssembler.toInscripcionDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
//        	if (rs==null) return false;
//        	else {
			rs.close();
			pst.close();
			c.close();
//        	}
		}

		if (listaAtletas.size() > 0)
			return true;
		else
			return false;
	}

	public void agregarInscripcion(String text, String id, float f, String fecha) {
		try {
			agregarParticipante(text, id, f, fecha);
		} catch (SQLException e) {
			System.out.println("no se pudo a�adir -- inscripcion model");
			e.printStackTrace();
		}
	}

	private void agregarParticipante(String email, String id, float f, String fecha) throws SQLException {
		AtletaDto a = findAtletaEmail2(email);
		String dni = a.getDni();
		String cat = calcularCategoria(a.getF_nac(), id, a.getSexo());
		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
//        ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql3);
			pst.setString(1, dni);
			pst.setString(2, id);
			pst.setString(3, email);
			pst.setFloat(4, f);
			pst.setString(5, fecha);
			pst.setString(6, cat);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {

			pst.close();
			c.close();
		}
	}

	private String calcularCategoria(String fecha, String idComp, String sexo) {
		CategoriaModel cm = new CategoriaModel();
		List<CategoriaDto> categorias = cm.findCateBySex(idComp, sexo);

		String[] fechaArray = fecha.split("/");
		int year = Integer.valueOf(fechaArray[2]);
		int yearActual = LocalDate.now().getYear();
		int cat = yearActual - year;

		for (CategoriaDto c : categorias) {
			if (c.getEdad_min() <= cat && cat <= c.getEdad_max()) {
				return c.getNombre();
			}
		}
		return null;
	}

	public List<InscripcionDto> buscarInsByDni(String dni) {
		List<InscripcionDto> listaDni = null;
		try {
			listaDni = buscarInsByDniP(dni);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDni;
	}

	public List<InscripcionDto> buscarInsByEmail(String email) {
		List<InscripcionDto> listaE = null;
		try {
			listaE = buscarInsByEmailP(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaE;
	}

	private List<InscripcionDto> buscarInsByDniP(String dni) throws SQLException {
		List<InscripcionDto> listaDni = new ArrayList<InscripcionDto>();

		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql5);
			pst.setString(1, dni);
			rs = pst.executeQuery();

			// A�adimos los pedidos a la lista
			listaDni = DtoAssembler.toInscripcionDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

		for (InscripcionDto i : listaDni) {
			System.out.println(i.getDni_a());
		}
		return listaDni;
	}

	public List<InscripcionDto> buscarInsByEmailP(String email) throws SQLException {
		List<InscripcionDto> listaE = new ArrayList<InscripcionDto>();

		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql6);
			pst.setString(1, email);
			rs = pst.executeQuery();

			// A�adimos los pedidos a la lista
			listaE = DtoAssembler.toInscripcionDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

		for (InscripcionDto i : listaE) {
			System.out.println(i.getEmail());
		}
		return listaE;
	}

	public List<InscripcionDto> getInscripcionesDeUnaCompeticion(String id) {
		List<InscripcionDto> l = null;
		try {
			l = getInscripcionesDeUnaCompeticionP(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

	private List<InscripcionDto> getInscripcionesDeUnaCompeticionP(String id) throws SQLException {
		List<InscripcionDto> inscripciones = new ArrayList<InscripcionDto>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(COMPID_INS);
			pst.setString(1, id);
			rs = pst.executeQuery();

			inscripciones = DtoAssembler.toInscripcionDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}
		return inscripciones;
	}

	public String getCategoriaByDniId(String dni, String id) {
		String a = "";
		try {
			a = getCategoria(dni, id);
		} catch (SQLException e) {
			System.out.println("no se pudo a�adir -- inscripcion model");
			e.printStackTrace();
		}
		return a;
	}

	private String getCategoria(String dni, String id) throws SQLException {
		String a = "";
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(CAT_INS_DNI_ID);
			pst.setString(1, dni);
			pst.setString(2, id);
			rs = pst.executeQuery();
			rs.next();

			a = rs.getString(1);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}
		return a;
	}

	public InscripcionDto findInsByDniId(String dni_a, String id_c) {
		InscripcionDto ins = null;
		try {
			ins = findInsByDniIdP(dni_a, id_c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ins;
	}

	private InscripcionDto findInsByDniIdP(String dni_a, String id_c) throws SQLException {
		InscripcionDto a = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql6Ins);
			pst.setString(1, dni_a);
			pst.setString(2, id_c);
			rs = pst.executeQuery();
			rs.next();

			a = DtoAssembler.toInscripcionDto(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}
		return a;
	}

	public void actualizarInscripcionEstado(String estado, String dni, String id) {
		try {
			actualizarEstado(estado, dni, id);
		} catch (SQLException e) {
			System.out.println("no se pudo actuliazar");
			e.printStackTrace();
		}
	}

	private void actualizarEstado(String estado, String dni, String id) throws SQLException {
		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
//        ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql7UpdateEstado);
			pst.setString(1, estado);
			pst.setString(2, dni);
			pst.setString(3, id);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}

	}

	public void actualizarInscripcionFecha(String fecha, String dni, String id) {
		try {
			actualizarFechaP(fecha, dni, id);
		} catch (SQLException e) {
			System.out.println("no se pudo actuliazar");
			e.printStackTrace();
		}
	}

	private void actualizarFechaP(String fecha, String dni, String id) throws SQLException {
		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
//        ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql7UpdateFecha);
			pst.setString(1, fecha);
			pst.setString(2, dni);
			pst.setString(3, id);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}

	}

	public void cambiarMetodoPago(String string, String dni, String id) {
		try {
			cambiarMetodoPagoP(string, dni, id);
		} catch (SQLException e) {
			System.out.println("no se pudo actuliazar");
			e.printStackTrace();
		}
	}

	public void cambiarMetodoPagoP(String string, String dni, String id) throws SQLException {
		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
//        ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql7UpdatePago);
			pst.setString(1, string);
			pst.setString(2, dni);
			pst.setString(3, id);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}

	}

	public List<InscripcionDto> getInscripcionesPorTiempo(String carreraId) throws SQLException {
		List<InscripcionDto> listaInscripciones = new ArrayList<InscripcionDto>();

		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql_InscripcionesPorTiempo);
			pst.setString(1, carreraId);
			rs = pst.executeQuery();

			listaInscripciones = DtoAssembler.toInscripcionDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

		return listaInscripciones;
	}

//aver
	public List<InscripcionDto> getInscripcionesPorTiempoYSexo(String carreraId, String sexo) throws SQLException {
		List<InscripcionDto> listaInscripciones = new ArrayList<InscripcionDto>();

		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql_InscripcionesPorTiempoYSexo);
			pst.setString(1, sexo);
			rs = pst.executeQuery();

			listaInscripciones = DtoAssembler.toInscripcionDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (rs != null)
				rs.close();
			pst.close();
			c.close();
		}

		return listaInscripciones;
	}

	public List<InscripcionDto> getInscripcionesPorTiempoYEdad(String carreraId, String categoria) throws SQLException {
		List<InscripcionDto> listaInscripciones = new ArrayList<InscripcionDto>();

		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql_InscripcionesPorTiempoYEdad);
			pst.setString(1, carreraId);
			pst.setString(2, categoria);
			rs = pst.executeQuery();

			listaInscripciones = DtoAssembler.toInscripcionDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (rs != null)
				rs.close();
			pst.close();
			c.close();
		}

		return listaInscripciones;
	}

	public void asignarDorsalesInsConIdComp(String dni_a, String id_c, int dorsal) {
		try {
			asignarDorsalesInsConIdCompP(dni_a, id_c, dorsal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void asignarDorsalesInsConIdCompP(String dni_a, String id_c, int dorsal) throws SQLException {
		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		// ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlActualizarDorsales);
			pst.setString(3, dni_a);
			pst.setString(2, id_c);
			pst.setInt(1, dorsal);
			int a = pst.executeUpdate();
			System.out.println(a);

			pst2 = c.prepareStatement(sqlActEnComp);
			pst2.setString(1, id_c);
			int b = pst2.executeUpdate();
			System.out.println(b);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}
	}

	public List<InscripcionDto> getInscripcionesMetodoPago(String metodoPago) {
		List<InscripcionDto> ins = null;
		try {
			ins = getInscripcionesMetodoPagoP(metodoPago.toLowerCase());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ins;
	}

	private List<InscripcionDto> getInscripcionesMetodoPagoP(String metodoPago) throws SQLException {
		List<InscripcionDto> listaInscrpcines = new ArrayList<InscripcionDto>();

		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql_InscripcionesMetodoPago);
			pst.setString(1, metodoPago);
			rs = pst.executeQuery();

			// A�adimos los pedidos a la lista
			listaInscrpcines = DtoAssembler.toInscripcionDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

		for (InscripcionDto atletaDto : listaInscrpcines) {
			System.out.println(atletaDto);
		}
		return listaInscrpcines;
	}

	public void actualizarInscripcionCantPagada(float cantidad, String dni, String id) {
		try {
			actualizarInscripcionCantPagadaP(cantidad, dni, id);
		} catch (SQLException e) {
			System.out.println("no se pudo actuliazar");
			e.printStackTrace();
		}
	}

	private void actualizarInscripcionCantPagadaP(float cantidad, String dni, String id) throws SQLException {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlUpdateCantidadPagada);
			pst.setFloat(1, cantidad);
			pst.setString(2, dni);
			pst.setString(3, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}

	}

	public void actualizarTiempoDorsal(String competicionId, String dorsal, int horas, int minutos)
			throws SQLException {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(updateTimes);
			pst.setString(3, dorsal);
			pst.setString(4, competicionId);
			pst.setInt(1, horas);
			pst.setInt(2, minutos);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}
	}

}
