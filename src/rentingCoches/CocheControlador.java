package rentingCoches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;

public class CocheControlador {
	
	Conexion conexion = new Conexion();
	Connection conn = conexion.getConexion();
	
	public void insertarCoche(Coche coche) {
		String sql = "INSERT INTO coches(matricula, marca, color, precio) values ('"+coche.getMatricula()+"','"+coche.getMarca()+"', '"+coche.getColor()+"', "+coche.getPrecio()+")";
		try {
			Statement stmt = conn.createStatement();
            stmt.execute(sql);
            stmt.close();
		}
		catch(SQLException e) {
			System.out.println("Error al guardar un coche");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Coche> listarCoches() {
		String sql = "SELECT idCoche, matricula, marca, color, precio FROM coches ORDER BY idCoche";
		
		ArrayList<Coche> alCoches = new ArrayList<>();
		ResultSet rs = null;
		Coche coche;
		
		try {
			Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
            	coche = new Coche();
            	coche.setIdCoche(rs.getInt(1));
            	coche.setMatricula((rs.getString(2)));
            	coche.setMarca(rs.getString(3));
            	coche.setColor(rs.getString(4));
            	coche.setPrecio(rs.getInt(5));
            	alCoches.add(coche);
            }
            stmt.close();
            rs.close();
		}
		catch(SQLException e) {
			System.out.println("Error al listar los coches");
			e.printStackTrace();
		}
		return alCoches;
	}
	
	public void eliminarCoche(int idCoche) {
		String sql = "DELETE FROM coches WHERE idCoche=" + idCoche;
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();
		}
		catch(SQLException e) {
			System.out.println("Error al eliminar coche");
			System.out.println("Dependecia de claves foraneas. No puedes eliminar un coche que este asociado a la tabla registro. Antes has de eliminar el registro de la tabla registro para poder eliminar el coche");
			e.printStackTrace();
		}
	}
	
	public void actualizarCoche(Coche coche) {
		String sql = "UPDATE coches SET matricula = " + "'" + coche.getMarca() + "', marca = " + "'" + coche.getMarca() + "', color = " + "'" + coche.getColor() + "', precio = " + coche.getPrecio() + " WHERE idCoche = " + coche.getIdCoche();
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();
		}
		catch(SQLException e) {
			System.out.println("Error al actualizar coche");
			e.printStackTrace();
		}
	}
	
	public int getId(String matricula) {
		int id = -1;
		
		String sql = "SELECT idCoche FROM coches WHERE matricula=" + "'" + matricula + "'";
		
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
			System.out.println("Error al obtener el id coche");
			e.printStackTrace();
		}
		return id;
	}
	
	public void procedureCoche(String matricula, float aumento) {
		
		// Hace un update del precio de un coche
		
		String sql = "CALL p_aumentar_precio_coche('" + matricula + "', " + aumento + ");";
		
		try {
			CallableStatement stmt = (CallableStatement) conn.prepareCall(sql);
            stmt.execute(sql);
            stmt.close();
		}
		catch(SQLException e) {
			System.out.println("Error al ejecutar el procede de actualizar");
			e.printStackTrace();
		}
	}

}
