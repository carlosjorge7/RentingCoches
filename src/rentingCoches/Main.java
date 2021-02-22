package rentingCoches;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static ClienteControlador clienteControlador = new ClienteControlador();
	public static CocheControlador cocheControlador = new CocheControlador();
	public static RegistroControlador registroControlador = new RegistroControlador();
	
	public static void main(String[] args) {
		
		Conexion conexion = new Conexion();
		
		Scanner sc = new Scanner(System.in);
		String opcion = "";
		
		Coche coche;
		Integer idCoche;
		String matricula, marca, color;
		float precio;
		
		Cliente cliente;
		Integer idCliente, edad;
		String dni, nombre;
		
		Registro registro;
		Integer idRegistro;
		String fecha_inicio, fecha_fin;
		
		Connection conn;
		
		String res;
		
		while(!opcion.equals("salir")) {
        	 System.out.println("INGRESA UNA OPCION");
        	 System.out.println("Menú(\n crea modelo \n insertar \n listar \n listar nombres tablas \n mostrar campos tablas \n mostrar claves \n eliminar \n actualizar \n procedure \n salir)");
             opcion = sc.nextLine();
             
             switch(opcion) {
             
             	 case "crear modelo":
             		 conn = conexion.getConexion();
             		 crearModelo(conn);
             		 separador();
             		 break;
             
	             case "insertar":
	            	 System.out.println("cliente?, coche?, registro?");
	            	 res = sc.nextLine();
	            	 if(res.equals("cliente")) {
		            	 System.out.println("Dni cliente");
		            	 dni = sc.nextLine();
		            	 System.out.println("Nombre cliente: ");
		            	 nombre = sc.nextLine();
		            	 System.out.println("Edad cliente: ");
		            	 edad = sc.nextInt();
		            	 
		            	 cliente = new Cliente(dni, nombre, edad);
		            
		            	 clienteControlador.insertarCliente(cliente);
		            	 System.out.println("Cliente guardado");
	            	 }
	            	 else if(res.equals("coche")) { 
	            		 System.out.println("Matricula: ");
	            		 matricula = sc.nextLine();
	            		 System.out.println("Marca: ");
	            		 marca = sc.nextLine();
	            		 System.out.println("Color: ");
	            		 color = sc.nextLine();
	            		 System.out.println("Precio: ");
	            		 precio = sc.nextFloat();
	            		 
	            		 coche = new Coche(matricula, marca, color, precio);
	            
	            		 cocheControlador.insertarCoche(coche);
	            		 System.out.println("Coche guardado");	            		 	 
	            	 }
	            	 else if(res.equals("registro")) {
	            		 System.out.println("DNI del cliente");
	            		 dni = sc.nextLine();
	            		 
	            		 System.out.println("MATRICULA del coche");
	            		 matricula = sc.nextLine();

	            		 idCliente = clienteControlador.getId(dni);

	            		 idCoche = cocheControlador.getId(matricula);
	            		
		            	 separador();
		      
		            	 if(idCliente != -1 && idCoche != -1) {
		            		 System.out.println("Fecha de inicio YYY-MM-DD: ");
			            	 fecha_inicio = sc.nextLine();
			            	 System.out.println("Fecha de fin : ");
			            	 fecha_fin = sc.nextLine();
			            	 
			            	 registro = new Registro(fecha_inicio, fecha_fin, idCoche, idCliente);

			            	 registroControlador.insertarRegistro(registro);
			            	 System.out.println("Registro guardado con exito"); 
		            	 }
		            	 else {
		            		 System.out.println("No existe el cliente o el coche");
		            	 }
		            	
	            	 }
	            	 break;
	            	 
	             case "listar":
	            	 System.out.println("cliente?, coche?, registro?");
	            	 res = sc.nextLine();
	            	 
	            	 if(res.equals("cliente")) {
	            		 ArrayList<Cliente> clientes = clienteControlador.listarClientes();
	            		 System.out.println("n|Dni|Nombre|Edad");
	            		 separador();
		            	 for(Cliente c: clientes) {
		            		 System.out.println(c.getIdCliente() + "|" + c.getDni()+ "|" +c.getNombre()+ "|" + c.getEdad());
		            	 }
		            	 separador();
	            	 }
	            	 else if(res.equals("coche")) {
	            		 ArrayList<Coche> coches = cocheControlador.listarCoches();
	            		 System.out.println("n|Matricula|Marca|Color|Precio");
	            		 separador();
	            		 for(Coche co: coches) {
	            			 System.out.println(co.getIdCoche() + "|" + co.getMatricula() + "|" + co.getMarca() + "|" + co.getColor() + "|" + co.getPrecio());
	            		 }
	            		 separador();
	            	 }
	            	 else if(res.equals("registro")) {
	            		 ArrayList<Registro> registros = registroControlador.listarRegistros();
	            		 System.out.println("n|FechaInicio|FechaFin|Matricula|Dni");
	            		 separador();
	            		 for(Registro r: registros) {
	            			 System.out.println(r.getIdRegistro() + "|" + r.getFecha_inicio() + "|" + r.getFecha_fin() + "|" + r.getMatricula() + "|" + r.getDni());
	            		 }
	            		 separador();
	            	 }
	            	 break;
	            	 
	             case "listar nombres tablas":
	            	 try {
	            		 conn = conexion.getConexion();
	            		 DatabaseMetaData metaDatos = conn.getMetaData();
	            		 // Usamos el metodo getTables
	            		 ResultSet rs = metaDatos.getTables(null, "rentingcoches", null, null);
	            		 System.out.println("Nombres de las tablas de la base de datos rentingcoches");
	            		 separador();
	            		 while(rs.next()) {
	            			   String tabla = rs.getString(3);
	            			   System.out.println("TABLA="  + tabla);
	            		 }
	            		 separador();
	            	 }
	            	 catch(SQLException e) {
	            		 e.printStackTrace();
	            	 }
		            	
	            	 break;
	            	 
	             case "mostrar campos tablas":
	            	 try {
	            		 conn = conexion.getConexion();
	            		 DatabaseMetaData metaDatos = conn.getMetaData();
	            		 // usamos el metodo getColumns
	            		 ResultSet rs =  metaDatos.getColumns(null, "rentingcoches", null, null);
	            		 System.out.println("Nombres de los campos por tabla de la base de datos rentingcoches");
	            		 separador();
	            		 while(rs.next()) {
		            			String tabla = rs.getString("TABLE_NAME");
		         				String nombCol = rs.getString("COLUMN_NAME");
		         				String tipoCol = rs.getString("TYPE_NAME");
		         				String tamCol = rs.getString("COLUMN_SIZE");
		         				String nula = rs.getString("IS_NULLABLE");
		         				System.out.printf("Tabla: %s, Columna: %s, Tipo: %s, Tamaño: %s," + "¿Puede ser nula?: %s %n", tabla, nombCol, tipoCol, tamCol, nula);	
	            		 }
	            		 separador();
	            	 }
	            	 catch(SQLException e) {
	            		 e.printStackTrace();
	            	 }
	            	 break;
	            	 
	             case "mostrar claves":
	            	 try {
	            		conn = conexion.getConexion();
	            		DatabaseMetaData metaDatos = conn.getMetaData();
	            		
	            		ResultSet pkClientes = metaDatos.getPrimaryKeys("rentingcoches", null, "clientes");
	         			System.out.println("Clave primaria de la tabla clientes");
	         			separador();
	         			while (pkClientes.next()){
	         		         System.out.println("Table name: "+ pkClientes.getString("TABLE_NAME"));
	         		         System.out.println("Column name: "+ pkClientes.getString("COLUMN_NAME"));
	         		         System.out.println("Catalog name: "+ pkClientes.getString("TABLE_CAT"));
	         		         System.out.println("Primary key sequence: " + pkClientes.getString("KEY_SEQ"));
	         		         System.out.println("Primary key name: " + pkClientes.getString("PK_NAME"));
	         		         System.out.println(" ");
	         			}
	         			separador();
	         			
	         			ResultSet pkCoches = metaDatos.getPrimaryKeys("rentingcoches", null, "coches");
	         			System.out.println("Clave primaria de la tabla coches");
	         			separador();
	         			while (pkCoches.next()){
	         		         System.out.println("Table name: "+ pkCoches.getString("TABLE_NAME"));
	         		         System.out.println("Column name: "+ pkCoches.getString("COLUMN_NAME"));
	         		         System.out.println("Catalog name: "+ pkCoches.getString("TABLE_CAT"));
	         		         System.out.println("Primary key sequence: " + pkCoches.getString("KEY_SEQ"));
	         		         System.out.println("Primary key name: " + pkCoches.getString("PK_NAME"));
	         		         System.out.println(" ");
	         			}
	         			separador();
	         			
	         			ResultSet pkRegistros = metaDatos.getPrimaryKeys("rentingcoches", null, "registros");
	         			System.out.println("Clave primaria de la tabla registros");
	         			separador();
	         			while (pkRegistros.next()){
	         		         System.out.println("Table name: "+ pkRegistros.getString("TABLE_NAME"));
	         		         System.out.println("Column name: "+ pkRegistros.getString("COLUMN_NAME"));
	         		         System.out.println("Catalog name: "+ pkRegistros.getString("TABLE_CAT"));
	         		         System.out.println("Primary key sequence: " + pkRegistros.getString("KEY_SEQ"));
	         		         System.out.println("Primary key name: " + pkRegistros.getString("PK_NAME"));
	         		         System.out.println(" ");
	         			}
	         			separador();
	  
	            	    ResultSet rs = metaDatos.getExportedKeys("rentingcoches", "registros", "clientes");
	            	    ResultSet rs1 = metaDatos.getExportedKeys("rentingcoches", "registros", "coches");
	            		System.out.println("Claves foraneas de la tabla registros");
	            	    separador();
	            	    if(rs.next()) {
	            	        System.out.println("Foreign key : " + rs.getString("FKCOLUMN_NAME"));
	            	    }
	            	    if(rs1.next()) {
	            	    	 System.out.println("Foreign key : " + rs1.getString("FKCOLUMN_NAME"));
	            	    }
	            	    separador();
	            	    rs.close();
	            	 } 
	            	 catch(SQLException e) {
	            		 e.printStackTrace();
	            	 }
	            	 break;

	             case "eliminar":
	            	 System.out.println("cliente?, coche?, registro?");
	            	 res = sc.nextLine();
	            	 if(res.equals("cliente")) {
	            		 System.out.println("Dni: ");
	            		 dni = sc.nextLine();

	            		 idCliente = clienteControlador.getId(dni);
	            		 // Si es != -1, es que el cliente existe
	            		 if(idCliente != -1) {
	            			 clienteControlador.eliminarCliente(idCliente);
	            			 System.out.println("Cliente eliminado");
	            		 }
	            		 else {
	            			 System.out.println("El cliente no existe");
	            		 }
	            	 }
	            	 else if(res.equals("coche")) {
	            		 System.out.println("Matricula: ");
	            		 matricula = sc.nextLine();
	            		 
	            		 idCoche = cocheControlador.getId(matricula);
	            		 
	            		 if(idCoche != -1) {
	            			 cocheControlador.eliminarCoche(idCoche);
	            			 System.out.println("Coche eliminado");
	            		 }
	            		 else {
	            			 System.out.println("El coche no existe");
	            		 } 
	            	 }
					 else if(res.equals("registro")) {
						 System.out.println("Dni: ");
	            		 dni = sc.nextLine();
	            		 System.out.println("Matricula: ");
	            		 matricula = sc.nextLine();
	            		 
	            		 idCliente = clienteControlador.getId(dni);
	            		 
	            		 System.out.println(idCliente);
	            		 
            			 idCoche = cocheControlador.getId(matricula);
            			 
            			 System.out.println(idCoche);
            			 
            			 // tiene que existir el cliente y el coche en la tabla registros, si lo queremos eliminar
            			 if(idCliente != -1 && idCoche != -1) {
            				 registroControlador = new RegistroControlador();
    	            		 ArrayList<Registro> registros = registroControlador.listarRegistros();
    	            		 
    	            		 for(Registro r: registros) {
    	            			 if(r.getMatricula().equals(matricula) && r.getDni().equals(dni)) {
    	            				 idRegistro = r.getIdRegistro();
    	            				 registroControlador.eliminarRegistro(idRegistro);
    	            				 System.out.println("Registro eliminado");
    	            			 }
    	            		 }
            			 }
	      
					 }
	            	 
	            	 break;
	            	 
	             case "actualizar":
	            	 System.out.println("cliente?, coche?, registro?");
	            	 res = sc.nextLine();
	            	 if(res.equals("cliente")) {
	            		System.out.println("Dni :");
	            		dni = sc.nextLine();
	            		
	            		idCliente = clienteControlador.getId(dni);
	            		
	            		separador();
	            		
	            		if(idCliente != -1) {
	            			cliente = new Cliente();
	            			
	            			cliente.setIdCliente(idCliente);
	            			
	            			System.out.println("Nuevo dni :");
	            			dni = sc.nextLine();
	            			cliente.setDni(dni);
	            			
	            			System.out.println("Nuevo nombre : ");
	            			nombre = sc.nextLine();
	            			cliente.setNombre(nombre);
	            			
	            			System.out.println("Nueva edad : ");
	            			edad = sc.nextInt();
	            			cliente.setEdad(edad);
	            			
	            			clienteControlador.actualizarCliente(cliente);
	            			System.out.println("Cliente actualizado");
	            		}
	            		else {
	            			System.out.println("No existe el cliente");
	            		}
	            	 }
	            	 else if(res.equals("coche")) {
	            		System.out.println("Matricula:");
	            		matricula = sc.nextLine();
	            		
	            		idCoche = cocheControlador.getId(matricula);
	            		
	            		separador();
	            		if(idCoche != -1) {
	            			coche = new Coche();
	            			
	            			coche.setIdCoche(idCoche);
	            			
	            			System.out.println("Nueva matricula:");
	            			matricula = sc.nextLine();
	            			coche.setMatricula(matricula);
	            			
	            			System.out.println("Nueva marca");
	            			marca = sc.nextLine();
	            			coche.setMarca(marca);
	            			
	            			System.out.println("Nuevo color");
	            			color = sc.nextLine();
	            			coche.setColor(color);
	            			
	            			System.out.println("Nuevo precio");
	            			precio = sc.nextFloat();
	            			coche.setPrecio(precio);
	            			
	            			cocheControlador.actualizarCoche(coche);
	            			System.out.println("Coche actualizado");
	            		}
	            		else {
	            			System.out.println("No existe el coche");
	            		}
	            		separador();
	            	 }
					 else if(res.equals("registro")) {
						 
						 System.out.println("Matricula coche : ");
						 matricula = sc.nextLine();
						 
						 System.out.println("Dni cliente : ");
						 dni = sc.nextLine();
						 
						 // Sacamos sus ids
						 idCoche = cocheControlador.getId(matricula);
						 idCliente = clienteControlador.getId(dni);
						 
						 idRegistro = registroControlador.getId(idCoche, idCliente);
						 
						 if(idRegistro != -1) {
							 registro = new Registro();
							 
							 registro.setIdRegistro(idRegistro);
							 
							 System.out.println("Nueva fecha inicio: ");
							 fecha_inicio = sc.nextLine();
							 registro.setFecha_inicio(fecha_inicio);
							 
							 System.out.println("Nueva fecha fin : ");
							 fecha_fin = sc.nextLine();
							 registro.setFecha_fin(fecha_fin);
							 
							 System.out.println("Nueva matricula : ");
							 matricula = sc.nextLine();
							 idCoche = cocheControlador.getId(matricula);
							 if(idCoche != -1) {
								 registro.setIdCoche(idCoche);
							 }
							 else {
								 System.out.println("El coche no existe");
							 }
							 
							 System.out.println("Nuevo cliente : ");
							 dni = sc.nextLine();
							 idCliente = clienteControlador.getId(dni);
							 if(idCliente != -1) {
								 registro.setIdCliente(idCliente);
							 }
							 else {
								 System.out.println("El cliente no existe");
							 }
							 
							 registroControlador.actualizarRegistro(registro);
							 System.out.println("Registro actualizado");
							 
						 }
						 else {
							 System.out.println("No existe el registro");
						 }
						
					 }
	            	 break;
	            	 
	             case "procedure":
	            	 System.out.println("Matricula:");
	            	 matricula = sc.nextLine();
	            	 System.out.println("Aumento");
	            	 float aumento = sc.nextFloat();
	            	
	            	 idCoche = cocheControlador.getId(matricula);
	            	 
	            	 separador();
	            	 if(idCoche != -1) {
	            		 cocheControlador.procedureCoche(matricula, aumento);
		            	 System.out.println("Procedimiento ejecutado, he aumentado " + aumento + "euros al coche con la matricula " + matricula); 
	            	 }
	            	 else {
	            		 System.out.println("El coche no existe");
	            	 }
	             	 separador();
	        
	            	 break;
	            	 
	             case "salir":
	            	 conn = conexion.getConexion();
	            	 separador();
	            	 System.out.println("Cerrando la conexion a la base de datos rentingcoches ...");
	            	 System.out.println("FIN DEL PROGRAMA! CIAO");
	            	 separador();
	            	 cerrarConexion(conn);
	            	 break;
             }
             
        }
        sc.close(); 
			
	}
	
	public static void cerrarConexion(Connection conn) {
		try {
			conn.close();
		}
		catch(SQLException e) {
			System.out.println("Error al cerrar la conexion");
			e.printStackTrace();
		}
	}
	
	private static void separador() {
		 System.out.println("________________________________________");
	}
	
	private static void crearModelo(Connection conn) {
		
		// Vamos a leer el fichero script.txt y enviarselo al modelo (Previamente creada con create database rentingcoches;)
 		
 		File file = new File("./src/sql/script.txt");
 		
 		if(!file.exists()) {
 			System.out.println("No existe el fichero Script.txt");
 			return;
 		}
 		
 		System.out.println("Fichero a cargar: " + file.getName());
 		
 		// Leemos el fichero
 		BufferedReader entrada = null;
 		try {
 			entrada = new BufferedReader(new FileReader(file));
 		} 
 		catch (FileNotFoundException e) {
 			System.out.println("ERROR NO HAY FILE: " + e.getMessage());
 		}
 		
 		// Convertimos el fichero a cadena
 		String linea = null;
 		StringBuilder stringBuilder = new StringBuilder();
 		String salto = System.lineSeparator();
 		try {
 			while ((linea = entrada.readLine()) != null) {
 				stringBuilder.append(linea);
 				stringBuilder.append(salto);
 			}
 			System.out.println("Convirtiendo el fichero a cadena...");
 		}
 		catch (IOException e) {
 			System.out.println("ERROR DE E/S, al operar " + e.getMessage());
 		}
 		String[] consulta = stringBuilder.toString().split(";");
 		
 		// cargamos el modelo
 		try {
 			Statement sents = conn.createStatement();
 			for (int i=0; i < consulta.length; i++) {
 				
 				if (!consulta[i].trim().equals("")){
 					sents.executeUpdate(consulta[i]+";");
 				}
 			} 
 			System.out.println("Script cargado con exito");
 			conn.close();
 			sents.close();
 		}
 		catch (SQLException e) {
 			System.out.println("ERROR AL EJECUTAR EL SCRIPT: " + e.getMessage());
 		}
	}

}
