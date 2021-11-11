package logica;

public class CompeticionDto {
	
	private String id;
	private String nombre;
	private String f_comp;
	private String tipo;
	private String distancia;
	private int num_plazas;
	private float cuota1;
	private String f_fin1;
	private String f_inicio1;
	private float cuota2;
	private String f_fin2;
	private String f_inicio2;
	private float cuota3;
	private String f_fin3;
	private String f_inicio3;
	private int dorsales_vip;
	private int d_asig; //1-> true | 0-> false
	
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
	public String getF_comp() {
		return f_comp;
	}
	public void setF_comp(String f_comp) {
		this.f_comp = f_comp;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDistancia() {
		return distancia;
	}
	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}
	public int getNum_plazas() {
		return num_plazas;
	}
	public void setNum_plazas(int num_plazas) {
		this.num_plazas = num_plazas;
	}
	
	public float getCuota1() {
		return cuota1;
	}
	public void setCuota1(float cuota1) {
		this.cuota1 = cuota1;
	}
	public String getF_fin1() {
		return f_fin1;
	}
	public void setF_fin1(String f_fin1) {
		this.f_fin1 = f_fin1;
	}
	public String getF_inicio1() {
		return f_inicio1;
	}
	public void setF_inicio1(String f_inicio1) {
		this.f_inicio1 = f_inicio1;
	}
	public float getCuota2() {
		return cuota2;
	}
	public void setCuota2(float cuota2) {
		this.cuota2 = cuota2;
	}
	public String getF_fin2() {
		return f_fin2;
	}
	public void setF_fin2(String f_fin2) {
		this.f_fin2 = f_fin2;
	}
	public String getF_inicio2() {
		return f_inicio2;
	}
	public void setF_inicio2(String f_inicio2) {
		this.f_inicio2 = f_inicio2;
	}
	public float getCuota3() {
		return cuota3;
	}
	public void setCuota3(float cuota3) {
		this.cuota3 = cuota3;
	}
	public String getF_fin3() {
		return f_fin3;
	}
	public void setF_fin3(String f_fin3) {
		this.f_fin3 = f_fin3;
	}
	public String getF_inicio3() {
		return f_inicio3;
	}
	public void setF_inicio3(String f_inicio3) {
		this.f_inicio3 = f_inicio3;
	}
	public CompeticionDto()
	{
	}
	
//	public String toString()
//	{
//		return this.nombre + " - " + this.f_comp + " - " + this.distancia + "km.";
//	}
	
	public String toString()
	{
		return this.nombre + " - " + this.f_comp + " - " +this.tipo+" - "+ this.distancia +" - "+ 
					this.num_plazas + " plazas disponibles ";
	}
	public int getDorsales_vip() {
		return dorsales_vip;
	}
	public void setDorsales_vip(int dorsales_vip) {
		this.dorsales_vip = dorsales_vip;
	}
	public int getD_asig() {
		return d_asig;
	}
	public void setD_asig(int d_asig) {
		this.d_asig = d_asig;
	}
	
	

}
