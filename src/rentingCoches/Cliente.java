package rentingCoches;

public class Cliente {
	
	private int idCliente;
	private String dni;
	private String nombre;
	private int edad;
	
	public Cliente(int idCliente, String dni, String nombre, int edad) {
		this.idCliente = idCliente;
		this.dni = dni;
		this.nombre = nombre;
		this.edad = edad;
	}
	
	public Cliente(String dni, String nombre, int edad) {
		this.dni = dni;
		this.nombre = nombre;
		this.edad = edad;
	}
	
	public Cliente() {
		
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
}
