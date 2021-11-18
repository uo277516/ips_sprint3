package logica;

public class MarcaTiempo {

	String nombre;
	String posicion;
	String sexo;
	String edad;
	String dorsal;
	String tiempoInicial;
	String tiempoFinal;
	String categoria;
	int horas;
	int minutos;

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public int getHoras() {

		calcularTiempo();
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public int getMinutos() {
		calcularTiempo();
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public MarcaTiempo() {

	}

	public String getDorsal() {
		return dorsal;
	}

	public void setDorsal(String dorsal) {
		this.dorsal = dorsal;
	}

	public String getTiempoInicial() {
		return tiempoInicial;
	}

	public void setTiempoInicial(String tiempoInicial) {
		this.tiempoInicial = tiempoInicial;
	}

	public String getTiempoFinal() {
		return tiempoFinal;
	}

	public void setTiempoFinal(String tiempoFinal) {
		this.tiempoFinal = tiempoFinal;
	}

	public void calcularTiempo() {
		if (tiempoFinal != null && tiempoInicial != null) {
			int DuracionSegundos = Integer.valueOf(tiempoFinal) - Integer.valueOf(tiempoInicial);
			setHoras(DuracionSegundos / 3600);
			setMinutos((DuracionSegundos % 3600) / 60);
		}

	}

}