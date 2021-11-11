package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.BaseDatos;
import util.DtoAssembler;

public class CompeticionModel {

	public static String sql1 = "select * from competicion";
	public static String sql2ById = "select * from competicion where id=?";
	public static String sqlActualizarPlazas = "update competicion set num_plazas = num_plazas-1 where id =?";
	public static String sqlInsertarCompeticionBasicos = "insert into competicion (nombre,f_comp,tipo,distancia,num_plazas,dorsales_vip,id,d_asig) values (?,?,?,?,?,?,?,0)";
	public static String sqlFinCom = "select * from competicion where id =?";
	public static String sqlActualizarCompeticion1 = "update competicion set f_inicio1=?, f_fin1=?, cuota1=? where id=?";
	public static String sqlActualizarCompeticion2 = "update competicion set f_inicio2=?, f_fin2=?, cuota2=? where id=?";
	public static String sqlActualizarCompeticion3 = "update competicion set f_inicio3=?, f_fin3=?, cuota3=? where id=?";
	public static String findCategoriasByCompeticion = "select * from categoria c, pertenece p, competicion co where co.id = p.id_comp and c.id = p.id_cat and co.id = ?";

	private InscripcionModel im = new InscripcionModel();
	private AtletaModel am = new AtletaModel();

	public List<CompeticionDto> getCompeticiones() throws SQLException {
		return getAllCompeticiones();
	}

	public CompeticionDto[] getCompeticionesArray() {
		CompeticionDto[] articulos = null;
		try {
			articulos = getAllCompeticiones().toArray(new CompeticionDto[getAllCompeticiones().size()]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articulos;
	}

	private List<CompeticionDto> getAllCompeticiones() throws SQLException {
		List<CompeticionDto> listaCompeticiones = new ArrayList<CompeticionDto>();

		// Conexiï¿½n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql1);
			rs = pst.executeQuery();

			// Aï¿½adimos los pedidos a la lista
			listaCompeticiones = DtoAssembler.toCompeticionDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

		for (CompeticionDto atletaDto : listaCompeticiones) {
			System.out.println(atletaDto);
		}
		return listaCompeticiones;
	}

	public CompeticionDto[] getCompetcionesFecha(String fecha) {
		CompeticionDto[] articulos = null;
		try {
			articulos = filtrarPorFecha(fecha).toArray(new CompeticionDto[getAllCompeticiones().size()]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articulos;
	}

	public List<CompeticionDto> getCompetcionesFechaLista(String fecha) {
		List<CompeticionDto> articulos = null;
		try {
			articulos = filtrarPorFecha(fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articulos;
	}

	public void insertarDatosBasicos(String id, String nombre, String fecha, String tipo, int distancia, int plazas) {
		try {
			insertarDatosBasicosPrivado(id, nombre, fecha, tipo, distancia, plazas);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void insertarDatosBasicosPrivado(String id, String nombre, String fecha, String tipo, int distancia,
			int plazas) throws SQLException {
		// Conexiï¿½n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlInsertarCompeticionBasicos);
			if (pst != null)
				System.out.println("Adios");

			pst.setString(1, nombre);
			pst.setString(2, fecha);
			pst.setString(3, tipo);
			pst.setInt(4, distancia);
			pst.setInt(5, plazas);
			pst.setString(6, id);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}
	}

	private List<CompeticionDto> filtrarPorFecha(String fecha) throws SQLException {
		List<CompeticionDto> listaCompeticiones = new ArrayList<CompeticionDto>();

		// Conexiï¿½n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql1);
			rs = pst.executeQuery();

			// Aï¿½adimos los pedidos a la lista
			listaCompeticiones = DtoAssembler.toCompeticionDtoListPorFecha(rs, fecha);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

		// for (AtletaDto atletaDto : listaPedidos) {
		// System.out.println(atletaDto.getDni() + " " + atletaDto.getF_nac()
		// );
		// }
		return listaCompeticiones;
	}

	public List<CompeticionDto> getListaCompCerradas(String fecha) {
		List<CompeticionDto> articulos = null;
		try {
			articulos = listaCompeticionesCerradas(fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articulos;
	}

	private List<CompeticionDto> listaCompeticionesCerradas(String fecha) throws SQLException {
		List<CompeticionDto> listaCompeticiones = new ArrayList<CompeticionDto>();

		// Conexiï¿½n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql1);
			rs = pst.executeQuery();

			// Aï¿½adimos los pedidos a la lista
			listaCompeticiones = DtoAssembler.toCompeticionDtoListPorFechaCerradas(rs, fecha);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}
		return listaCompeticiones;
	}

	public List<CompeticionDto> getCompeticionById(String id) {
		List<CompeticionDto> listaCompeticiones = null;
		try {
			listaCompeticiones = getCompeticionByIdP(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaCompeticiones;
	}

	public List<CompeticionDto> getCompeticionByIdP(String identificador) throws SQLException {
		List<CompeticionDto> listaCompeticiones = new ArrayList<CompeticionDto>();

		// Conexiï¿½n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql2ById);
			pst.setString(1, identificador);
			rs = pst.executeQuery();

			// Aï¿½adimos los pedidos a la lista
			listaCompeticiones = DtoAssembler.toCompeticionDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

		for (CompeticionDto atletaDto : listaCompeticiones) {
			System.out.println(atletaDto);
		}
		return listaCompeticiones;
	}

	public void actualizarPlazas(String id) {
		try {
			actualizarPlazasP(id);
		} catch (SQLException e) {
			System.out.println("no se pudo actuliazar");
			e.printStackTrace();
		}
	}

	private void actualizarPlazasP(String id) throws SQLException {
		// Conexiï¿½n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		// ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlActualizarPlazas);
			pst.setString(1, id);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}

	}

	public void actualizarCopeticion1(String fechaInicio, String fechaFin, float cuota, String id) {
		try {
			actualizarCopeticion1P(fechaInicio, fechaFin, cuota, id);
		} catch (SQLException e) {
			System.out.println("no se pudo actuliazar");
			e.printStackTrace();
		}
	}

	private void actualizarCopeticion1P(String fechaInicio, String fechaFin, float cuota, String id)
			throws SQLException {
		// Conexiï¿½n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		// ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlActualizarCompeticion1);
			pst.setString(1, fechaInicio);
			pst.setString(2, fechaFin);
			pst.setFloat(3, cuota);
			pst.setString(4, id);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}

	}

	public void actualizarCopeticion2(String fechaInicio, String fechaFin, float cuota, String id) {
		try {
			actualizarCopeticion2P(fechaInicio, fechaFin, cuota, id);
		} catch (SQLException e) {
			System.out.println("no se pudo actuliazar");
			e.printStackTrace();
		}
	}

	private void actualizarCopeticion2P(String fechaInicio, String fechaFin, float cuota, String id)
			throws SQLException {
		// Conexiï¿½n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		// ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlActualizarCompeticion2);
			pst.setString(1, fechaInicio);
			pst.setString(2, fechaFin);
			pst.setFloat(3, cuota);
			pst.setString(4, id);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}

	}

	public void actualizarCopeticion3(String fechaInicio, String fechaFin, float cuota, String id) {
		try {
			actualizarCopeticion3P(fechaInicio, fechaFin, cuota, id);
		} catch (SQLException e) {
			System.out.println("no se pudo actuliazar");
			e.printStackTrace();
		}
	}

	private void actualizarCopeticion3P(String fechaInicio, String fechaFin, float cuota, String id)
			throws SQLException {
		// Conexiï¿½n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		// ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlActualizarCompeticion3);
			pst.setString(1, fechaInicio);
			pst.setString(2, fechaFin);
			pst.setFloat(3, cuota);
			pst.setString(4, id);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}

	}

	public List<String> getClasificacion(String carreraId) throws SQLException {
		List<String> clasificacion = new ArrayList<String>();
		AtletaDto a;
		List<InscripcionDto> inscripciones = im.getInscripcionesPorTiempo(carreraId);
		for (InscripcionDto i : inscripciones) {
			a = am.findAtletaByDni(i.getDni_a());
			if (i.getHoras() == 0 && i.getMinutos() == 0)
				clasificacion.add("Nombre: " + a.getNombre() + " - Sexo: " + a.getSexo() + " - Tiempo: --- ");
			else
				clasificacion.add("Nombre: " + a.getNombre() + " - Sexo: " + a.getSexo() + " - Tiempo: " + i.getHoras()
						+ "h " + i.getMinutos() + " minutos");
		}
		return clasificacion;
	}

	public List<String> getClasificacionPorSexo(String id, String categoria) throws SQLException {
		if (categoria == "General") {
			return getClasificacion(id);
		} else {
			List<String> clasificacion = new ArrayList<String>();
			AtletaDto a;
			List<InscripcionDto> inscripciones = im.getInscripcionesPorTiempoYSexo(id, categoria);
			for (InscripcionDto i : inscripciones) {
				a = am.findAtletaByDni(i.getDni_a());
				if (i.getHoras() == 0 && i.getMinutos() == 0)
					clasificacion.add("Nombre: " + a.getNombre() + " - Sexo: " + a.getSexo() + " - Tiempo: --- ");
				else
					clasificacion.add("Nombre: " + a.getNombre() + " - Sexo: " + a.getSexo() + " - Tiempo: "
							+ i.getHoras() + "h " + i.getMinutos() + " minutos");
			}
			return clasificacion;
		}
	}

	public List<String> getClasificacionPorEdad(String id, String categoria) throws SQLException {
		int posicion = 1;
		if (categoria == "General") {
			return getClasificacion(id);
		} else {
			List<String> clasificacion = new ArrayList<String>();
			AtletaDto a;
			List<InscripcionDto> inscripciones = im.getInscripcionesPorTiempoYEdad(id, categoria);
			for (InscripcionDto i : inscripciones) {
				a = am.findAtletaByDni(i.getDni_a());
				if (i.getHoras() == 0 && i.getMinutos() == 0)
					clasificacion.add(
							"Posición: " + posicion++ + " - Dorsal: " + i.getDorsal() + " - Nombre: " + a.getNombre()
									+ " - Sexo: " + a.getSexo() + " - Edad: " + a.getF_nac() + " - Tiempo: --- ");
				else
					clasificacion.add("Posición: " + posicion++ + " - Dorsal: " + i.getDorsal() + " - Nombre: "
							+ a.getNombre() + " - Sexo: " + a.getSexo() + " - Edad: " + a.getF_nac() + " - Tiempo: "
							+ i.getHoras() + "h " + i.getMinutos() + " minutos");
			}
			return clasificacion;
		}
	}

	public List<CategoriaDto> getCategoriasByCompeticion(String id) throws SQLException {
		List<CategoriaDto> categorias = new ArrayList<CategoriaDto>();
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(findCategoriasByCompeticion);
			pst.setString(1, id);
			rs = pst.executeQuery();

			categorias = DtoAssembler.toCategoriaDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

		return categorias;
	}

	public void insertarDatosBasicos(String id, String nombre, String fecha, String tipo, int distancia, int plazas,
			int dorsales) {
		try {
			insertarDatosBasicosPrivado(id, nombre, fecha, tipo, distancia, plazas, dorsales);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void insertarDatosBasicosPrivado(String id, String nombre, String fecha, String tipo, int distancia,
			int plazas, int dorsales) throws SQLException {
		// Conexiï¿½n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlInsertarCompeticionBasicos);
			if (pst != null)
				System.out.println("Adios");

			pst.setString(1, nombre);
			pst.setString(2, fecha);
			pst.setString(3, tipo);
			pst.setInt(4, distancia);
			pst.setInt(5, plazas);
			pst.setInt(6, dorsales);
			pst.setString(7, id);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}
	}

	public void actualizarTiempos(String competicionId, ArrayList<MarcaTiempo> tiempos) throws SQLException {
		for (MarcaTiempo t : tiempos) {
			im.actualizarTiempoDorsal(competicionId, t.getDorsal(), t.getHoras(), t.getMinutos());
		}
		System.out.println("Tiempos actualizados");
	}

}
