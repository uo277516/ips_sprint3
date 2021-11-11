package logica;

public class AtletaDto {
	
	private String nombre;
	private String email;
	private String dni;
	public String getNombre() {//nombre
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getF_nac() {
		return f_nac;
	}


	public void setF_nac(String f_nac) {
		this.f_nac = f_nac;
	}


	private String sexo;
	private String f_nac;
	
	
	public AtletaDto()
	{
	}




	@Override
	public String toString() {
		return "AtletaDto [nombre=" + nombre + ", email=" + email + ", dni=" + dni + ", sexo=" + sexo + ", f_nac="
				+ f_nac + "]";
	}


	public void setEmail(String email) {
		this.email=email;
	}
	
	

}
