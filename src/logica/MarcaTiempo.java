package logica;

import java.util.List;

public class MarcaTiempo {

	String nombre;
	String posicion;
	String sexo;
	int edad;
	String dorsal;
	String tiempoInicial;
	String tiempoFinal;
	String categoria;

	String club;
	List<Integer> tiemposPaso;
	double minutosKm;
	int diferencia;

	int horas;
	int minutos;

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public List<Integer> getTiemposPaso() {
		return tiemposPaso;
	}

	public void setTiemposPaso(List<Integer> tiemposPaso2) {
		this.tiemposPaso = tiemposPaso2;
	}

	public double getMinutosKm() {
		return minutosKm;
	}

	public void setMinutosKm(double minutosKm) {
		this.minutosKm = minutosKm;
	}

	public int getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(int i) {
		this.diferencia = i;
	}

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

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
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