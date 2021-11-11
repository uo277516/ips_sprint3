package logica;

public class CategoriaDto {
	
	private String id;
	private String nombre;
	private int edad_min;
	private int edad_max;
	private String sexo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad_min() {
		return edad_min;
	}
	public void setEdad_min(int edad_min) {
		this.edad_min = edad_min;
	}
	public int getEdad_max() {
		return edad_max;
	}
	public void setEdad_max(int edad_max) {
		this.edad_max = edad_max;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	@Override
	public String toString() {
		return "CategoriaDto [id=" + id + ", nombre=" + nombre + ", edad_min=" + edad_min + ", edad_max=" + edad_max
				+ ", sexo=" + sexo + "]";
	}
	
	
	

}
