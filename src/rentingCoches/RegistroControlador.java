package rentingCoches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RegistroControlador {
	
	Conexion conexion = new Conexion();
	Connection conn = conexion.getConexion();
	
	public void insertarRegistro(Registro registro) {
		String sql = "INSERT INTO registros(fecha_inicio, fecha_fin, idCoche, idCliente) values ('"+registro.getFecha_inicio()+"','"+registro.getFecha_fin()+"', "+registro.getIdCoche()+", "+registro.getIdCliente()+")";
		
		try {
			Statement stmt = conn.createStatement();
            stmt.execute(sql); 
            stmt.close();
		}
		catch(SQLException e) {
			System.out.println("Error al guardar un registro");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Registro> listarRegistros() {
		String sql = "SELECT r.idRegistro, r.fecha_inicio, r.fecha_fin, co.matricula, c.dni\r\n"
				+ "FROM registros r, coches co, clientes c\r\n"
				+ "where r.idCoche = co.idCoche AND r.idCliente = c.idCliente\r\n"
				+ "ORDER BY r.idRegistro";
		
		ArrayList<Registro> alRegistros = new ArrayList<>();
		ResultSet rs = null;
		Registro registro;
		
		try {
			Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
            	registro = new Registro();
            	registro.setIdRegistro(rs.getInt(1));
            	registro.setFecha_inicio(rs.getString(2));
            	registro.setFecha_fin(rs.getString(3));
            	registro.setMatricula(rs.getString(4));
            	registro.setDni(rs.getString(5));
            	alRegistros.add(registro);
            }
            stmt.close();
            rs.close();
		}
		catch(SQLException e) {
			System.out.println("Error al listar los clientes");
			e.printStackTrace();
		}
		return alRegistros;
	}
	
	public void actualizarRegistro(Registro registro) {
		String sql = "UPDATE registros SET fecha_inicio = " + "'" + registro.getFecha_inicio() + "', fecha_fin = " + "'" + registro.getFecha_fin() + "', idCoche = " + registro.getIdCoche() + ", idCliente = " + registro.getIdCliente() + " WHERE idRegistro = " + registro.getIdRegistro();
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();
		}
		catch(SQLException e) {
			System.out.println("Error al actualizar registro");
			e.printStackTrace();
		}
	}
	
	public void eliminarRegistro(int idRegistro) {
		String sql = "DELETE FROM registros WHERE idRegistro=" + idRegistro;	
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();
		}
		catch(SQLException e) {
			System.out.println("Error al eliminar registro");
			e.printStackTrace();
		}
	}
	
	public int getId(int idCoche, int idCliente) {
		int id = -1;
		
		String sql = "SELECT idRegistro FROM registros WHERE idCoche = " + idCoche + " AND idCliente = " + idCliente;
		
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
			System.out.println("Error al obtener el id registro");
			e.printStackTrace();
		}
		return id;
	}

}
