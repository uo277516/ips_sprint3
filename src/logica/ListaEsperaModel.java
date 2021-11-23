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
}
