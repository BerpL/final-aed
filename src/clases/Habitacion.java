package clases;

/**
 * Clase de datos para representar una habitación
 */
public class Habitacion {
	
	private String numero;
	private String tipo;
	private String piso;
	private double precioNoche;
	private int capacidad;
	private String estado;
	private String descripcion;
	private boolean wifi;
	private boolean tv;
	private boolean ac;
	private boolean minibar;
	private boolean jacuzzi;
	
	// Constructor por defecto
	public Habitacion() {
		
	}
	
	// Constructor completo
	public Habitacion(String numero, String tipo, String piso, double precioNoche,
	                  int capacidad, String estado, String descripcion, boolean wifi,
	                  boolean tv, boolean ac, boolean minibar, boolean jacuzzi) {
		this.numero = numero;
		this.tipo = tipo;
		this.piso = piso;
		this.precioNoche = precioNoche;
		this.capacidad = capacidad;
		this.estado = estado;
		this.descripcion = descripcion;
		this.wifi = wifi;
		this.tv = tv;
		this.ac = ac;
		this.minibar = minibar;
		this.jacuzzi = jacuzzi;
	}
	
	// Getters y Setters
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public double getPrecioNoche() {
		return precioNoche;
	}

	public void setPrecioNoche(double precioNoche) {
		this.precioNoche = precioNoche;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		return String.format("S/. %.2f", precioNoche);
	}
}

