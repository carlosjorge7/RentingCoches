package rentingCoches;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	public Connection getConexion() {
		String url = "jdbc:mysql://localhost/rentingcoches";
		String usuario = "root";
		String contrasena = "";
		Connection con = null;
		
	    try{
	        con = DriverManager.getConnection(url, usuario, contrasena);
	        if(con != null) {
	        	 //System.out.println(">> Conexion exitosa a rentingcoches");
	        }
	    }
	    catch(SQLException e){
	    	System.out.println(">> Error de conexion" + e.getMessage());
	    }
	    return con;
	}

}
