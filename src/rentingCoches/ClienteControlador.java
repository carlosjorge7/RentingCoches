package rentingCoches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ClienteControlador {
	
	Conexion conexion = new Conexion();
	Connection conn = conexion.getConexion();
	
	public void insertarCliente(Cliente cliente) {
		String sql = "INSERT INTO clientes(dni, nombre, edad) values ('"+cliente.getDni()+"','"+cliente.getNombre()+"', '"+cliente.getEdad()+"')";
		try {
			Statement stmt = conn.createStatement();
            stmt.execute(sql);
           
            stmt.close();
		}
		catch(SQLException e) {
			System.out.println("Error al guardar un cliente");
			e.printStackTrace();
		}	
	}
	
	public ArrayList<Cliente> listarClientes() {
		String sql = "SELECT idCliente, dni, nombre, edad FROM clientes ORDER BY idCliente";
		
		ArrayList<Cliente> alClientes = new ArrayList<>();
		ResultSet rs = null;
		Cliente cliente;
		
		try {
			Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
            	cliente = new Cliente();
            	cliente.setIdCliente(rs.getInt(1));
            	cliente.setDni((rs.getString(2)));
            	cliente.setNombre(rs.getString(3));
            	cliente.setEdad(rs.getInt(4));
            	alClientes.add(cliente);
            }
            stmt.close();
            rs.close();
		}
		catch(SQLException e) {
			System.out.println("Error al listar los clientes");
			e.printStackTrace();
		}
		return alClientes;
	}
	
	public void eliminarCliente(int idCliente) {
		String sql = "DELETE FROM clientes WHERE idCliente=" + idCliente;
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();
		}
		catch(SQLException e) {
			System.out.println("Error al eliminar cliente");
			System.out.println("Dependecia de claves foraneas. No puedes eliminar un cliente que este asociado a la tabla registro. Antes has de eliminar el registro de la tabla registro para poder eliminar el cliente");
			e.printStackTrace();
		}
	}
	
	public void actualizarCliente(Cliente cliente) {
		String sql = "UPDATE clientes SET dni = " + "'" + cliente.getDni() + "', nombre = " + "'" + cliente.getNombre() + "', edad = " + cliente.getEdad() + " WHERE idCliente = " + cliente.getIdCliente();
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();
		}
		catch(SQLException e) {
			System.out.println("Error al actualizar cliente");
			e.printStackTrace();
		}
	}
	
	public int getId(String dni) {
		int id = -1;
		
		String sql = "SELECT idCliente FROM clientes WHERE dni=" + "'" + dni + "'";
		
		ResultSet rs = null;
		
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				id = rs.getInt(1);
	        }
			rs.close();
			stmt.close();
			stmt.close();
		}
		catch(SQLException e) {
			System.out.println("Error al obtener id del cliente");
			e.printStackTrace();
		}
		return id;
	}

}
