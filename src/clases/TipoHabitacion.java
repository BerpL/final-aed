package clases;

/**
 * Clase de datos para representar un tipo de habitación
 */
public class TipoHabitacion {
	
	private String codigo;
	private String nombre;
	private double precioBase;
	private int capacidad;
	private String tipoCamas;
	private double area;
	private boolean wifi;
	private boolean tv;
	private boolean ac;
	private boolean minibar;
	private boolean jacuzzi;
	private String descripcion;
	
	// Constructor por defecto
	public TipoHabitacion() {
		
	}
	
	// Constructor completo
	public TipoHabitacion(String codigo, String nombre, double precioBase, int capacidad,
	                      String tipoCamas, double area, boolean wifi, boolean tv,
	                      boolean ac, boolean minibar, boolean jacuzzi, String descripcion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.capacidad = capacidad;
		this.tipoCamas = tipoCamas;
		this.area = area;
		this.wifi = wifi;
		this.tv = tv;
		this.ac = ac;
		this.minibar = minibar;
		this.jacuzzi = jacuzzi;
		this.descripcion = descripcion;
	}
	
	// Getters y Setters
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getTipoCamas() {
		return tipoCamas;
	}

	public void setTipoCamas(String tipoCamas) {
		this.tipoCamas = tipoCamas;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public boolean isWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	public boolean isTv() {
		return tv;
	}

	public void setTv(boolean tv) {
		this.tv = tv;
	}

	public boolean isAc() {
		return ac;
	}

	public void setAc(boolean ac) {
		this.ac = ac;
	}

	public boolean isMinibar() {
		return minibar;
	}

	public void setMinibar(boolean minibar) {
		this.minibar = minibar;
	}

	public boolean isJacuzzi() {
		return jacuzzi;
	}

	public void setJacuzzi(boolean jacuzzi) {
		this.jacuzzi = jacuzzi;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	// Métodos auxiliares
	public String obtenerAmenidades() {
		java.util.ArrayList<String> amenidades = new java.util.ArrayList<>();
		if (wifi) amenidades.add("WiFi");
		if (tv) amenidades.add("TV Cable");
		if (ac) amenidades.add("A/C");
		if (minibar) amenidades.add("Minibar");
		if (jacuzzi) amenidades.add("Jacuzzi");
		return String.join(", ", amenidades);
	}
	
	public String obtenerPrecioFormateado() {
		return String.format("S/. %.2f", precioBase);
	}
	
	public String obtenerCapacidadTexto() {
		return capacidad + " personas";
	}
}

