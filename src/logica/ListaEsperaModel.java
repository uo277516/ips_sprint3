package logica;

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
}