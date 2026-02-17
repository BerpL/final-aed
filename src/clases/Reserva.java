package clases;

import java.time.LocalDate;

/**
 * Clase de datos para representar una reserva
 */
public class Reserva {
	
	private String idReserva;
	private String idHuesped;
	private String nombreHuesped;
	private String numeroHabitacion;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private int numeroNoches;
	private int numeroHuespedes;
	private String formaPago;
	private double total;
	private String estado;
	private boolean desayuno;
	private boolean parking;
	private boolean spa;
	private String observaciones;
	
	// Constructor por defecto
	public Reserva() {
		
	}
	
	// Constructor completo
	public Reserva(String idReserva, String idHuesped, String nombreHuesped,
	               String numeroHabitacion, LocalDate checkIn, LocalDate checkOut,
	               int numeroNoches, int numeroHuespedes, String formaPago,
	               double total, String estado, boolean desayuno, boolean parking,
	               boolean spa, String observaciones) {
		this.idReserva = idReserva;
		this.idHuesped = idHuesped;
		this.nombreHuesped = nombreHuesped;
		this.numeroHabitacion = numeroHabitacion;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.numeroNoches = numeroNoches;
		this.numeroHuespedes = numeroHuespedes;
		this.formaPago = formaPago;
		this.total = total;
		this.estado = estado;
		this.desayuno = desayuno;
		this.parking = parking;
		this.spa = spa;
		this.observaciones = observaciones;
	}
	
	// Getters y Setters
	public String getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(String idReserva) {
		this.idReserva = idReserva;
	}

	public String getIdHuesped() {
		return idHuesped;
	}

	public void setIdHuesped(String idHuesped) {
		this.idHuesped = idHuesped;
	}

	public String getNombreHuesped() {
		return nombreHuesped;
	}

	public void setNombreHuesped(String nombreHuesped) {
		this.nombreHuesped = nombreHuesped;
	}

	public String getNumeroHabitacion() {
		return numeroHabitacion;
	}

	public void setNumeroHabitacion(String numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public int getNumeroNoches() {
		return numeroNoches;
	}

	public void setNumeroNoches(int numeroNoches) {
		this.numeroNoches = numeroNoches;
	}

	public int getNumeroHuespedes() {
		return numeroHuespedes;
	}

	public void setNumeroHuespedes(int numeroHuespedes) {
		this.numeroHuespedes = numeroHuespedes;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isDesayuno() {
		return desayuno;
	}

	public void setDesayuno(boolean desayuno) {
		this.desayuno = desayuno;
	}

	public boolean isParking() {
		return parking;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public boolean isSpa() {
		return spa;
	}

	public void setSpa(boolean spa) {
		this.spa = spa;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	// Métodos auxiliares
	public String obtenerCheckInFormateado() {
		if (checkIn == null) return "";
		return checkIn.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public String obtenerCheckOutFormateado() {
		if (checkOut == null) return "";
		return checkOut.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public String obtenerTotalFormateado() {
		return String.format("S/. %.2f", total);
	}
	
	public void calcularNumeroNoches() {
		if (checkIn != null && checkOut != null) {
			numeroNoches = (int) java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);
		}
	}
}

