package rentingCoches;

public class Coche {
	
	private int idCoche;
	private String matricula;
	private String marca;
	private String color;
	private float precio;
	
	public Coche(int idCoche, String matricula, String marca, String color, float precio) {
		this.idCoche = idCoche;
		this.matricula = matricula;
		this.marca = marca;
		this.color = color;
		this.precio = precio;
	}

	public Coche(String matricula, String marca, String color, float precio) {
		this.matricula = matricula;
		this.marca = marca;
		this.color = color;
		this.precio = precio;
	}

	public Coche() {
		
	}

	public int getIdCoche() {
		return idCoche;
	}

	public void setIdCoche(int idCoche) {
		this.idCoche = idCoche;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
}
