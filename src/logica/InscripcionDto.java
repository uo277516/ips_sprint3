package logica;

public class InscripcionDto {

	// Estados
	public static String INSCRITO = "Inscrito";
	public static String PRE_INSCRITO = "Pre-inscrito";
	public static String ANULADA = "Anulada";
	public static String ANULADA_DEVOLUCION = "Anulada - pendiente de devolución";
	public static String INSCRITO_DEVOLUCION = "Inscrito - pendiente de devolución";
	public static String PENDIENTE = "Pendiente";

	private String dni_a;
	private String id_c;
	private String categoria;
	private String email;
	private String fecha;
	private String metodo_pago;
	private float cantidad_pagada;
	private int horas;
	private int minutos;
	private String estado;

	private String club;
	private int tiempoPaso1;
	private int tiempoPaso2;
	private int tiempoPaso3;
	private int tiempoPaso4;


	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}


	public int getTiempoPaso1() {
		return tiempoPaso1;
	}

	public void setTiempoPaso1(int tiempoPaso1) {
		this.tiempoPaso1 = tiempoPaso1;
	}

	public int getTiempoPaso2() {
		return tiempoPaso2;
	}

	public void setTiempoPaso2(int tiempoPaso2) {
		this.tiempoPaso2 = tiempoPaso2;
	}

	public int getTiempoPaso3() {
		return tiempoPaso3;
	}

	public void setTiempoPaso3(int tiempoPaso3) {
		this.tiempoPaso3 = tiempoPaso3;
	}

	public int getTiempoPaso4() {
		return tiempoPaso4;
	}

	public void setTiempoPaso4(int tiempoPaso4) {
		this.tiempoPaso4 = tiempoPaso4;
	}

	public String getDni_a() {
		return dni_a;
	}

	public void setDni_a(String dni_a) {
		this.dni_a = dni_a;
	}

	public String getId_c() {
		return id_c;
	}

	public void setId_c(String id_c) {
		this.id_c = id_c;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getMetodo_pago() {
		return metodo_pago;
	}

	public void setMetodo_pago(String metodo_pago) {
		this.metodo_pago = metodo_pago;
	}

	public float getCantidad_pagada() {
		return cantidad_pagada;
	}

	public void setCantidad_pagada(float cantidad_pagada) {
		this.cantidad_pagada = cantidad_pagada;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	private String dorsal;

	public InscripcionDto() {
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDorsal() {
		return dorsal;
	}

	public void setDorsal(String dorsal) {
		this.dorsal = dorsal;
	}

	public String mostrarMisInscripcionesNombre(String nombre) {
		return "--> Nombre: " + nombre;
	}

	public String mostrarMisInscripcionesEstado() {
		return "      Estado de la inscripciï¿½n:" + " " + getEstado();
	}

	public String mostrarMisInscripcionesFecha() {
		return "      Fecha ï¿½ltimo cambio: " + getFecha();
	}

	@Override
	public String toString() {
		return "InscripcionDto [dni_a=" + dni_a + ", id_c=" + id_c + ", categoria=" + categoria + ", email=" + email
				+ ", fecha=" + fecha + ", metodo_pago=" + metodo_pago + ", cantidad_pagada=" + cantidad_pagada
				+ ", horas=" + horas + ", minutos=" + minutos + ", estado=" + estado + ", dorsal=" + dorsal + "]";
	}
	

	

}
