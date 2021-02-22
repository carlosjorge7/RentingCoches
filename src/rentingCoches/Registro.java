package rentingCoches;

public class Registro {
	
	private int idRegistro;
	private String fecha_inicio;
	private String fecha_fin;
	private int idCoche;
	private int idCliente;
	
	private String matricula;
	private String dni;
	
	public Registro(int idRegistro, String fecha_inicio, String fecha_fin, int idCoche, int idCliente) {
		this.idRegistro = idRegistro;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.idCoche = idCoche;
		this.idCliente = idCliente;
	}

	public Registro(String fecha_inicio, String fecha_fin, int idCoche, int idCliente) {
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.idCoche = idCoche;
		this.idCliente = idCliente;
	}

	public Registro() {
		
	}

	public int getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(int idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public int getIdCoche() {
		return idCoche;
	}

	public void setIdCoche(int idCoche) {
		this.idCoche = idCoche;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	// atributos adicionales matricula de coche y dni de cliente
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	
	
}
