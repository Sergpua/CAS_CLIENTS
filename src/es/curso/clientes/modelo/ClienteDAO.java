package es.curso.clientes.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO {
	
	private static final String URL =
			"jdbc:mysql://localhost:3306/empresa3";
	
	private Connection conexion;
	

	@Override
	public void conectar(String login, String pass) throws DAOException {
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			conexion = DriverManager.getConnection(URL, login, pass);
		} catch (ClassNotFoundException | SQLException e) {
			throw new DAOException(e.getMessage());
		}				
	}

	@Override
	public int grabar(Cliente c) throws DAOException {
		PreparedStatement ps;
		String sql;
		int n = 0;
		
		try {
			sql = "insert into clientes(idcliente,nombre,pais) "+
				     " values(?,?,?)";
			ps = conexion.prepareStatement(sql);
			ps.setString(1, c.getIdcliente());
			ps.setString(2, c.getNombre());
			ps.setString(3, c.getPais());
			
			n = ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return n;
	}

	@Override
	public int borrar(String idcliente) throws DAOException {
		// delete from clientes where idcliente=?
		PreparedStatement ps;
		String sql;
		int n;
		
		try {
			sql = "delete from clientes where idcliente=?";
			ps = conexion.prepareStatement(sql);
			ps.setString(1, idcliente);
						
			n = ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return n;
	}

	@Override
	public void actualizar(Cliente cliente) throws DAOException {
		// update clientes set nombre=?,pais=? where icliente=?
		PreparedStatement ps;
		String sql;
		int n;
		
		try {
			sql = "update clientes set nombre=?,pais=? where icliente=?";
			ps = conexion.prepareStatement(sql);
			ps.setString(1, cliente.getNombre());
			ps.setString(2, cliente.getPais());
			ps.setString(3, cliente.getIdcliente());
			
			n = ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Cliente leer(String idcliente) throws DAOException {
		Cliente cliente=null;
		PreparedStatement ps;
		String sql;
		ResultSet rst;
				
		try {
			sql = "select idcliente,nombre,pais from clientes "+
		      "where idcliente=?";
			ps=conexion.prepareStatement(sql);
			ps.setString(1, idcliente);
			rst = ps.executeQuery();
			
			if (rst.next()){
				cliente = new Cliente();
				cliente.setIdcliente(rst.getString("idcliente"));
				cliente.setNombre(rst.getString("nombre"));
				cliente.setPais(rst.getString("pais"));
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
		return cliente;
	}

	@Override
	public List<Cliente> leerTodos() throws DAOException {
		Cliente cliente=null;
		PreparedStatement ps;
		String sql;
		ResultSet rst;
		List<Cliente> clientes = new ArrayList<>();
				
		try {
			sql = "select idcliente,nombre,pais from clientes";
		    
			ps=conexion.prepareStatement(sql);		
			rst = ps.executeQuery();
			
			while (rst.next()){
				cliente = new Cliente();
				cliente.setIdcliente(rst.getString("idcliente"));
				cliente.setNombre(rst.getString("nombre"));
				cliente.setPais(rst.getString("pais"));
				
				clientes.add(cliente);
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage());
		}
		return clientes;
	}

	@Override
	public void cerrar() throws DAOException {
		// TODO Auto-generated method stub
		
	}
	
	public List<String> getPaises(){
		PreparedStatement ps;
		String sql;
		ResultSet rst;
		List<String> paises = new ArrayList<>();
				
		try {
			sql = "select distinct pais from clientes order by pais";
			ps=conexion.prepareStatement(sql);
			rst = ps.executeQuery();
			
			while(rst.next()){
				paises.add(rst.getString("pais"));
				
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paises;
	}

}
