package logica;

public class MarcaTiempo {
	String dorsal;
	String tiempoInicial;
	String tiempoFinal;
	int horas;
	int minutos;
	
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
		int DuracionSegundos = Integer.valueOf(tiempoFinal) - Integer.valueOf(tiempoInicial);
		setHoras(DuracionSegundos / 3600);
		setMinutos((DuracionSegundos % 3600) / 60);
		
	}
	
}