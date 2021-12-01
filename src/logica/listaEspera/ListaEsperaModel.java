package logica.listaEspera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.BaseDatos;
import util.DtoAssembler;

public class ListaEsperaModel {

	public static String getAllListaEspera = "select * from listaespera";
	public static String getListaByIdComp = "select * from listaespera where id_comp = ?";
	public static String addLista = "insert into listaespera(id, id_comp) values(?, ?)";
	public static String findNextNumOrden = "select max(num_orden) as orden from en_espera where id_listaespera = ?";

	public List<ListaEsperaDto> getListas() {
		List<ListaEsperaDto> lista = null;
		try {
			lista = getAllListas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	private List<ListaEsperaDto> getAllListas() throws SQLException {
		List<ListaEsperaDto> listaListasEspera = new ArrayList<ListaEsperaDto>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(getAllListaEspera);
			rs = pst.executeQuery();

			listaListasEspera = DtoAssembler.toListaEsperaDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}

		for (ListaEsperaDto atletaDto : listaListasEspera) {
			System.out.println(atletaDto);
		}
		return listaListasEspera;
	}

	public ListaEsperaDto getListaByIdComp(String idComp) {
		ListaEsperaDto lista = null;
		try {
			lista = getListaByIdCompP(idComp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	private ListaEsperaDto getListaByIdCompP(String idComp) throws SQLException {
		ListaEsperaDto lista;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(getListaByIdComp);
			pst.setString(1, idComp);
			rs = pst.executeQuery();

			rs.next();
			lista = DtoAssembler.toListaDto(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}
		return lista;
	}

	public void addLista(String id, String idComp) {
		try {
			addListaP(id, idComp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addListaP(String id, String idComp) throws SQLException {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(addLista);
			pst.setString(1, id);
			pst.setString(2, idComp);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}
	}

	public boolean tieneListaDeEspera(String idComp) {
		boolean op = false;
		try {
			op = tieneListaDeEsperaP(idComp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return op;
	}

	private boolean tieneListaDeEsperaP(String idComp) throws SQLException {
		List<ListaEsperaDto> listas = new ArrayList<ListaEsperaDto>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(getListaByIdComp);
			pst.setString(1, idComp);
			rs = pst.executeQuery();

			listas = DtoAssembler.toListaEsperaDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}
		if (listas.size() > 0) {
			System.out.println("Tiene lista de espera");
			return true;
		} else {
			System.out.println("No tiene lista de espera");
			return false;
		}
	}

	public int getNextNumOrden(String id) {
		int orden = 0;
		try {
			orden = getNextNumOrdenP(id) + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orden;
	}

	private int getNextNumOrdenP(String id) throws SQLException {
		int result = 0;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(findNextNumOrden);
			pst.setString(1, id);

			rs = pst.executeQuery();
			rs.next();
			result = rs.getInt("orden");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			pst.close();
			c.close();
		}
		return result;
	}
}
