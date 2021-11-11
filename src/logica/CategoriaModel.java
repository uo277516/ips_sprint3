package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.BaseDatos;
import util.DtoAssembler;

public class CategoriaModel {

	public static String sqlListarTodas = "select * from categoria where length(id) < 3 order by edad_min";
	public static String sqlFindById = "select * from categoria where id =?";
	public static String sqlInsertarCategoria = "insert into categoria (id,nombre,edad_min,edad_max,sexo) values (?,?,?,?,?)";
	public static String sqlCategoriasPropias ="select c.id,c.nombre,c.edad_min,c.edad_max,c.sexo from categoria c, competicion com, pertenece p where com.id=p.id_comp and p.id_cat=c.id and com.id=? order by c.edad_min";
	public static String sqlInsertarPertenece = "insert into pertenece (id_cat,id_comp) values (?,?)";
	public static String sqlContarMas = "select c.id,c.nombre,c.edad_min,c.edad_max,c.sexo from categoria c, competicion com, pertenece p where com.id=p.id_comp and p.id_cat=c.id and com.id=? and c.sexo='masculino'";
	public static String sqlContarFem = "select c.id,c.nombre,c.edad_min,c.edad_max,c.sexo from categoria c, competicion com, pertenece p where com.id=p.id_comp and p.id_cat=c.id and com.id=? and c.sexo='femenino'";
	public static String sqlFinfByTodos = "select c.id,c.nombre,c.edad_min,c.edad_max,c.sexo from categoria c, competicion com, pertenece p where com.id=p.id_comp and p.id_cat=c.id and com.id=? and c.nombre=? and c.edad_min=? and c.edad_max=? and c.sexo=?";
	public static String sqlFindEdadMax = "select max(edad_max) as maximo  from categoria c, competicion com, pertenece p where com.id=p.id_comp and p.id_cat=c.id and com.id=?";
	public static String sqlFinfBySexo = "select c.id,c.nombre,c.edad_min,c.edad_max,c.sexo from categoria c, competicion com, pertenece p where com.id=p.id_comp and p.id_cat=c.id and com.id=? and c.sexo=?";
	public List<CategoriaDto> getCategorias() {
		List<CategoriaDto> lista = new ArrayList<>();
		try {
			lista = getAllCompeticionesP();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public CategoriaDto[] getCategoriasArray() {
		CategoriaDto[] articulos = null;
		try {
			articulos = getAllCompeticionesP().toArray(new CategoriaDto[getAllCompeticionesP().size()]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articulos;
	}

	private List<CategoriaDto> getAllCompeticionesP() throws SQLException {
		List<CategoriaDto> listaCat = new ArrayList<CategoriaDto>();

		// Conexión a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlListarTodas);
			rs = pst.executeQuery();

			// Añadimos los pedidos a la lista
			listaCat = DtoAssembler.toCategoriaDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}
		return listaCat;
	}

	public List<CategoriaDto> findById(String id) {
		List<CategoriaDto> lista = new ArrayList<>();
		try {
			lista = findCatById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}


	public List<CategoriaDto> findCatById(String id) throws SQLException {
		List<CategoriaDto> listaCat = new ArrayList<CategoriaDto>();

		// Conexión a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlFindById);
			pst.setString(1, id);
			rs = pst.executeQuery();

			// Añadimos los pedidos a la lista
			listaCat = DtoAssembler.toCategoriaDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}
		return listaCat;
	}

	public void insertarNueva(String id,String nombre, int edad_min,int edad_max,String sexo){
		try {
			insertarNuevaP(id, nombre, edad_min,edad_max,sexo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void insertarNuevaP(String id,String nombre, int edad_min,int edad_max,String sexo) throws SQLException {
		// Conexión a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlInsertarCategoria);

			pst.setString(1, id);
			pst.setString(2, nombre);
			pst.setInt(3, edad_min);
			pst.setInt(4, edad_max);
			pst.setString(5, sexo);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}
	}

	public List<CategoriaDto> getCategoriasPropias(String id_comp) {
		List<CategoriaDto> lista = new ArrayList<>();
		try {
			lista = getCategoriasPropiasP(id_comp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	private List<CategoriaDto> getCategoriasPropiasP(String id_comp) throws SQLException {
		List<CategoriaDto> listaCat = new ArrayList<CategoriaDto>();

		// Conexión a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlCategoriasPropias);
			pst.setString(1, id_comp);
			rs = pst.executeQuery();

			// Añadimos los pedidos a la lista
			listaCat = DtoAssembler.toCategoriaDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}
		return listaCat;
	}

	public void insertarPertenece(String id, String id_comp) {
		try {
			insertarPerteneceP(id, id_comp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void insertarPerteneceP(String id, String id_comp) throws SQLException {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlInsertarPertenece);


			pst.setString(1, id);
			pst.setString(2, id_comp);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}

	}

	public List<CategoriaDto> cuentaCategoriasMas(String id) {
		List<CategoriaDto> result =new ArrayList<>();
		try {
			result =cuentaCategoriasMasP(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	private List<CategoriaDto> cuentaCategoriasMasP(String id) throws SQLException {
		List<CategoriaDto> result =new ArrayList<>();
		ResultSet rs =null;
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlContarMas);
			pst.setString(1, id);

			rs = pst.executeQuery();

			if (rs != null)
				result = DtoAssembler.toCategoriaDtoList(rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (rs != null)
				rs.close();
			pst.close();
			c.close();
		}
		return result;

	}

	public List<CategoriaDto> cuentaCategoriasFem(String id) {
		List<CategoriaDto> result =new ArrayList<>();
		try {
			result =cuentaCategoriasFemP(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	private List<CategoriaDto> cuentaCategoriasFemP(String id) throws SQLException {
		List<CategoriaDto> result =new ArrayList<>();
		ResultSet rs =null;
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlContarFem);
			pst.setString(1, id);
			rs = pst.executeQuery();

			if (rs != null)
				result = DtoAssembler.toCategoriaDtoList(rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (rs != null)
				rs.close();
			pst.close();
			c.close();
		}
		return result;

	}

	public List<CategoriaDto> findCategoriaByTodo(String id_comp,String nombre, int edad_min, int edad_max, String sexo) {
		List<CategoriaDto> result =new ArrayList<>();
		try {
			result =findCategoriaByTodoP(id_comp,nombre, edad_min,edad_max, sexo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	private List<CategoriaDto> findCategoriaByTodoP(String id_comp,String nombre, int edad_min, int edad_max, String sexo) throws SQLException{
		List<CategoriaDto> listaCat = new ArrayList<CategoriaDto>();

		// Conexión a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlFinfByTodos);
			pst.setString(1, id_comp);
			pst.setString(2, nombre);
			pst.setInt(3, edad_min);
			pst.setInt(4, edad_max);
			pst.setString(5, sexo);
			rs = pst.executeQuery();
			// Añadimos los pedidos a la lista
			if (rs != null)
				listaCat = DtoAssembler.toCategoriaDtoList(rs);
			System.out.println("Tamaño: "+listaCat.size());

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (rs!= null)
				rs.close();
			pst.close();
			c.close();
		}
		return listaCat;
	}

	public int findEdadMax(String id_comp) {
		int result =0;
		try {
			result =findEdadMaxP(id_comp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	private int findEdadMaxP(String id_comp) throws SQLException {
		// Conexión a la base de datos
		int result =0;
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlFindEdadMax);
			pst.setString(1, id_comp);
			
			rs = pst.executeQuery();
			result=rs.getInt("maximo");
			// Añadimos los pedidos a la lista
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			rs.close();
			pst.close();
			c.close();
		}
		return result;
	}

	public List<CategoriaDto> findCateBySex(String id_comp, String sexo) {
		List<CategoriaDto> result =new ArrayList<>();
		try {
			result =findCateBySexP(id_comp,sexo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	private List<CategoriaDto> findCateBySexP(String id_comp, String sexo) throws SQLException {
		List<CategoriaDto> listaCat = new ArrayList<CategoriaDto>();

		// Conexión a la base de datos
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = BaseDatos.getConnection();
			pst = c.prepareStatement(sqlFinfBySexo);
			pst.setString(1, id_comp);
			pst.setString(2, sexo);
			rs = pst.executeQuery();
			// Añadimos los pedidos a la lista
			if (rs != null)
				listaCat = DtoAssembler.toCategoriaDtoList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (rs!= null)
				rs.close();
			pst.close();
			c.close();
		}
		return listaCat;
	}

}
