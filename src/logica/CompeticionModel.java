package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import util.BaseDatos;
import util.DtoAssembler;

public class CompeticionModel {

	public static String sql1 = "select * from competicion where num_plazas>0";
	public static String sql2ById = "select * from competicion where id=?";
	public static String sqlReducirPlazas = "update competicion set num_plazas = num_plazas-1 where id =?";
	public static String sqlAumentarPlazas = "update competicion set num_plazas = num_plazas+1 where id =?";

	public static String sqlInsertarCompeticionBasicos = "insert into competicion (nombre,f_comp,tipo,distancia,num_plazas,dorsales_vip,id,d_asig, hay_politica) values (?,?,?,?,?,?,?,0,0)";
	public static String sqlInsertarCompeticionBasicosConCancelacion = "insert into competicion (nombre,f_comp,tipo,distancia,num_plazas,dorsales_vip,id,d_asig, f_canc, p_cuota_canc, hay_politica) values (?,?,?,?,?,?,?,0,?,?,1)";

	public static String sqlFinCom = "select * from competicion where id =?";
	public static String sqlActualizarCompeticion1 = "update competicion set f_inicio1=?, f_fin1=?, cuota1=? where id=?";
	public static String sqlActualizarCompeticion2 = "update competicion set f_inicio2=?, f_fin2=?, cuota2=? where id=?";
	public static String sqlActualizarCompeticion3 = "update competicion set f_inicio3=?, f_fin3=?, cuota3=? where id=?";
	public static String findCategoriasByCompeticion = "select * from categoria c, pertenece p, competicion co where co.id = p.id_comp and c.id = p.id_cat and co.id = ?";
	public static String findCompByIdListaEspera = "select * from competicion where id_listaespera = ?";

	public static String actualizarIdListaEspera = "update competicion set id_listaespera = ? where id = ?";
	public static String getCompsByFechaYListaEspera = "select * from competicion c, listaespera l where l.id_comp = c.id";

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

		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql1);
			rs = pst.executeQuery();

			// A�adimos los pedidos a la lista
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

	public List<CompeticionDto> getCompetcionesFechaListaPlazasMayor3(String fecha) {
		List<CompeticionDto> articulos = null;
		try {
			articulos = filtrarPorFechaPlazasMayor3(fecha);
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
		// Conexi�n a la base de datos
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

		String[] cosas = fecha.split("/");
		cosas[0] = String.valueOf(Integer.parseInt(cosas[0]) + 1);
		String f = cosas[0] + "/" + cosas[1] + "/" + cosas[2];
		System.out.println(f);

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(getCompsByFechaYListaEspera);
			rs = pst.executeQuery();

			listaCompeticiones = DtoAssembler.toCompeticionDtoListPorFecha(rs, f);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}
		return listaCompeticiones;
	}

	private List<CompeticionDto> filtrarPorFechaPlazasMayor3(String fecha) throws SQLException {
		List<CompeticionDto> listaCompeticiones = new ArrayList<CompeticionDto>();

		String[] cosas = fecha.split("/");
		cosas[0] = String.valueOf(Integer.parseInt(cosas[0]) + 1);
		String f = cosas[0] + "/" + cosas[1] + "/" + cosas[2];
		System.out.println(f);
		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql1);
			rs = pst.executeQuery();

			// A�adimos los pedidos a la lista
			listaCompeticiones = DtoAssembler.toCompeticionDtoListPorFechaPlazasMayor3(rs, f);

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

	public List<CompeticionDto> getCompeticionesEnFechaYConListaDeEspera(String fecha) {
		List<CompeticionDto> competiciones = null;
		try {
			competiciones = filtrarPorFechaYLista(fecha);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return competiciones;
	}

	private List<CompeticionDto> filtrarPorFechaYLista(String fecha) throws SQLException {
		List<CompeticionDto> listaCompeticiones = new ArrayList<CompeticionDto>();

		String[] cosas = fecha.split("/");
		cosas[0] = String.valueOf(Integer.parseInt(cosas[0]) + 1);
		String f = cosas[0] + "/" + cosas[1] + "/" + cosas[2];
		System.out.println(f);
		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql1);
			rs = pst.executeQuery();

			// A�adimos los pedidos a la lista
			listaCompeticiones = DtoAssembler.toCompeticionDtoListPorFecha(rs, f);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}
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

		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql1);
			rs = pst.executeQuery();

			// A�adimos los pedidos a la lista
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

	private List<CompeticionDto> getCompeticionByIdP(String identificador) throws SQLException {
		List<CompeticionDto> listaCompeticiones = new ArrayList<CompeticionDto>();

		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sql2ById);
			pst.setString(1, identificador);
			rs = pst.executeQuery();

			// A�adimos los pedidos a la lista
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

	public void reducirPlazas(String id) {
		try {
			reducirPlazasP(id);
		} catch (SQLException e) {
			System.out.println("no se pudo actuliazar");
			e.printStackTrace();
		}
	}

	private void reducirPlazasP(String id) throws SQLException {
		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		// ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlReducirPlazas);
			pst.setString(1, id);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}

	}

	public void aumentarPlazas(String id) {
		try {
			aumentarPlazasP(id);
		} catch (SQLException e) {
			System.out.println("no se pudo actuliazar");
			e.printStackTrace();
		}
	}

	private void aumentarPlazasP(String id) throws SQLException {
		// Conexi�n a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		// ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlAumentarPlazas);
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
		// Conexi�n a la base de datos
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
		// Conexi�n a la base de datos
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
		// Conexi�n a la base de datos
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

	public List<MarcaTiempo> getClasificacion(String carreraId) throws SQLException {
		List<MarcaTiempo> clasificacion = new ArrayList<MarcaTiempo>();
		AtletaDto a;
		MarcaTiempo mt;
		int posicion = 1;
		List<InscripcionDto> inscripciones = im.getInscripcionesPorTiempo(carreraId);
		for (InscripcionDto i : inscripciones) {
			mt = new MarcaTiempo();
			a = am.findAtletaByDni(i.getDni_a());
			mt.setPosicion(String.valueOf(posicion++));
			mt.setNombre(a.getNombre());
			mt.setSexo(a.getSexo());
			mt.setHoras(i.getHoras());
			mt.setDorsal(i.getDorsal());
			mt.setEdad(a.getF_nac());
			mt.setMinutos(i.getMinutos());
			mt.setCategoria(i.getCategoria());
			clasificacion.add(mt);
//			if (i.getHoras() == 0 && i.getMinutos() == 0) {
//				clasificacion.add("Nombre: " + a.getNombre() + " - Sexo: " + a.getSexo() + " - Tiempo: --- ");
//			}else {
//				clasificacion.add("Nombre: " + a.getNombre() + " - Sexo: " + a.getSexo() + " - Tiempo: " + i.getHoras()
//				+ "h " + i.getMinutos() + " minutos");
//			}
		}
		return clasificacion;
	}

	public List<MarcaTiempo> ordenarPorCategoria(List<MarcaTiempo> listaSinOrdenar) {
		listaSinOrdenar.sort(Comparator.comparing(MarcaTiempo::getCategoria));
		return listaSinOrdenar;
	}

	public List<MarcaTiempo> getClasificacionPorSexo(String id, String categoria) throws SQLException {
		if (categoria == "General") {
			return getClasificacion(id);
		} else {
			List<MarcaTiempo> clasificacion = new ArrayList<MarcaTiempo>();
			AtletaDto a;
			MarcaTiempo mt;
			List<InscripcionDto> inscripciones = im.getInscripcionesPorTiempoYSexo(id, categoria);
			for (InscripcionDto i : inscripciones) {
				a = am.findAtletaByDni(i.getDni_a());
				mt = new MarcaTiempo();
				mt.setNombre(a.getNombre());
				mt.setSexo(a.getSexo());
				mt.setHoras(i.getHoras());
				mt.setMinutos(i.getMinutos());
				clasificacion.add(mt);
			}
			return clasificacion;
		}
	}

	public List<MarcaTiempo> getClasificacionPorEdad(String id, String categoria) throws SQLException {
		int posicion = 1;
		if (categoria == "General") {
			return getClasificacion(id);
		} else {
			List<MarcaTiempo> clasificacion = new ArrayList<MarcaTiempo>();
			AtletaDto a;
			MarcaTiempo mt;
			List<InscripcionDto> inscripciones = im.getInscripcionesPorTiempoYEdad(id, categoria);
			for (InscripcionDto i : inscripciones) {
				a = am.findAtletaByDni(i.getDni_a());
				mt = new MarcaTiempo();
				mt.setNombre(a.getNombre());
				mt.setSexo(a.getSexo());
				mt.setHoras(i.getHoras());
				mt.setMinutos(i.getMinutos());
				mt.setPosicion(String.valueOf(posicion++));
				mt.setDorsal(i.getDorsal());
				mt.setEdad(a.getF_nac());
				clasificacion.add(mt);
//				if (i.getHoras() == 0 && i.getMinutos() == 0) {
//					clasificacion.add(
//							"Posici�n: " + posicion++ + " - Dorsal: " + i.getDorsal() + " - Nombre: " + a.getNombre()
//							+ " - Sexo: " + a.getSexo() + " - Edad: " + a.getF_nac() + " - Tiempo: --- ");
//				}else {
//					clasificacion.add("Posici�n: " + posicion++ + " - Dorsal: " + i.getDorsal() + " - Nombre: "
//							+ a.getNombre() + " - Sexo: " + a.getSexo() + " - Edad: " + a.getF_nac() + " - Tiempo: "
//							+ i.getHoras() + "h " + i.getMinutos() + " minutos");
//				}
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
		// Conexi�n a la base de datos
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

	public void insertarDatosBasicosConCancelacion(String id, String nombre, String fecha, String tipo, int distancia,
			int plazas, int dorsales, String f_max_c, double p_cuota_canc) {
		try {
			insertarDatosBasicosConCancelacionPrivado(id, nombre, fecha, tipo, distancia, plazas, dorsales, f_max_c,
					p_cuota_canc);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void insertarDatosBasicosConCancelacionPrivado(String id, String nombre, String fecha, String tipo,
			int distancia, int plazas, int dorsales, String f_max_c, double p_cuota_canc) throws SQLException {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlInsertarCompeticionBasicosConCancelacion);
			if (pst != null)
				System.out.println("Adios");

			pst.setString(1, nombre);
			pst.setString(2, fecha);
			pst.setString(3, tipo);
			pst.setInt(4, distancia);
			pst.setInt(5, plazas);
			pst.setInt(6, dorsales);
			pst.setString(7, id);
			pst.setString(8, f_max_c);
			pst.setDouble(9, p_cuota_canc);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}

	}

	public void reducirNumPlazas(int num_plazas, String id) {
		try {
			reducirNumPlazasP(num_plazas, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void reducirNumPlazasP(int num_plazas, String id) throws SQLException {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement("update competicion set num_plazas = ? where competicion.id=?");
			if (pst != null)
				System.out.println("Adios");

			pst.setInt(1, num_plazas);
			pst.setString(2, id);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}
	}

	public int findNumPlazas(String id) {
		int plazas = 0;
		try {
			plazas = findNumPlazasP(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return plazas;
	}

	private int findNumPlazasP(String id) throws SQLException {
		List<CompeticionDto> competicion = new ArrayList<CompeticionDto>();
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(findCategoriasByCompeticion);
			pst.setString(1, id);
			rs = pst.executeQuery();

			competicion = DtoAssembler.toCompeticionDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

		return competicion.get(0).getNum_plazas();
	}

	public CompeticionDto getCompByIdListaEspera(String idLista) {
		CompeticionDto c = null;
		try {
			c = getCompByIdListaEsperaP(idLista);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	private CompeticionDto getCompByIdListaEsperaP(String idLista) throws SQLException {
		CompeticionDto comp = null;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(findCompByIdListaEspera);
			pst.setString(1, idLista);
			rs = pst.executeQuery();

			rs.next();
			comp = DtoAssembler.toCompeticionDto(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}
		return comp;
	}

	public void actualizarCompeticionIdLista(String idLista, String idComp) {
		try {
			actualizarCompeticionIdListaP(idLista, idComp);
		} catch (SQLException e) {
			System.out.println("no se pudo actualizar");
			e.printStackTrace();
		}
	}

	private void actualizarCompeticionIdListaP(String idLista, String idComp) throws SQLException {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(actualizarIdListaEspera);
			pst.setString(1, idLista);
			pst.setString(2, idComp);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}
	}
}
