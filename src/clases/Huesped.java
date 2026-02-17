package clases;

/**
 * Clase de datos para representar un huésped
 */
public class Huesped {
	
	private String id;
	private String documento;
	private String tipoDocumento;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String email;
	private String direccion;
	private String fechaNacimiento;
	private String genero;
	private String nacionalidad;
	private boolean vip;
	private boolean newsletter;
	
	// Constructor por defecto
	public Huesped() {
		
	}
	
	// Constructor completo
	public Huesped(String id, String documento, String tipoDocumento, String nombre, 
	               String apellidos, String telefono, String email, String direccion,
	               String fechaNacimiento, String genero, String nacionalidad, 
	               boolean vip, boolean newsletter) {
		this.id = id;
		this.documento = documento;
		this.tipoDocumento = tipoDocumento;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
		this.nacionalidad = nacionalidad;
		this.vip = vip;
		this.newsletter = newsletter;
	}
	
	// Getters y Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	public boolean isNewsletter() {
		return newsletter;
	}

	public void setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
	}
	
	// Métodos auxiliares
	public String obtenerNombreCompleto() {
		return nombre + " " + apellidos;
	}
	
	public String obtenerVipTexto() {
		return vip ? "Si" : "No";
	}
}
