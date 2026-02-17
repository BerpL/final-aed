package clases;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase para gestionar la lista de reservas con operaciones CRUD
 * Usa ARRAYLIST para almacenamiento principal
 */
public class GestionReservas {
	
	private ArrayList<Reserva> listaReservas;
	private int contadorId;
	
	public GestionReservas() {
		listaReservas = new ArrayList<>();
		contadorId = 1;
		inicializarDatosMocked();
		// Inicializar contadorId basándose en los IDs existentes
		inicializarContadorId();
	}
	
	/**
	 * Inicializa datos mocked para pruebas
	 */
	private void inicializarDatosMocked() {
		LocalDate hoy = LocalDate.now();
		
		agregar(new Reserva("RES-001", "0001", "Juan Carlos Cepeda de la Cruz", "101",
		                   hoy.plusDays(5), hoy.plusDays(8), 3, 2, "Tarjeta Crédito",
		                   1500.00, "Confirmada", true, false, true, "Cliente VIP"));
		
		agregar(new Reserva("RES-002", "0002", "Maria Rosa Rodriguez Perez", "201",
		                   hoy.plusDays(10), hoy.plusDays(12), 2, 1, "Efectivo",
		                   400.00, "Confirmada", false, true, false, ""));
		
		agregar(new Reserva("RES-003", "0003", "Pedro Antonio Martinez", "102",
		                   hoy.plusDays(15), hoy.plusDays(18), 3, 2, "Transferencia",
		                   1050.00, "Pendiente", true, true, false, ""));
		
		agregar(new Reserva("RES-004", "0001", "Juan Carlos Cepeda de la Cruz", "203",
		                   hoy.minusDays(5), hoy.minusDays(2), 3, 3, "Tarjeta Crédito",
		                   1200.00, "Completada", true, true, true, "Estadía exitosa"));
		
		agregar(new Reserva("RES-005", "0002", "Maria Rosa Rodriguez Perez", "301",
		                   hoy.plusDays(20), hoy.plusDays(22), 2, 1, "Efectivo",
		                   300.00, "Pendiente", false, false, false, ""));
	}
	
	/**
	 * Inicializa el contadorId basándose en el máximo ID existente
	 */
	private void inicializarContadorId() {
		int maxId = 0;
		for (Reserva r : listaReservas) {
			if (r != null && r.getIdReserva() != null) {
				try {
					// Extraer el número del ID (ej: "RES-001" -> 1)
					String idStr = r.getIdReserva().trim();
					if (idStr.startsWith("RES-")) {
						String numStr = idStr.substring(4); // Quitar "RES-"
						int idNum = Integer.parseInt(numStr);
						if (idNum > maxId) {
							maxId = idNum;
						}
					}
				} catch (NumberFormatException e) {
					// Si el ID no tiene el formato esperado, ignorarlo
				}
			}
		}
		// El siguiente ID será maxId + 1
		contadorId = maxId + 1;
	}
	
	/**
	 * Agrega una nueva reserva a la lista
	 */
	public boolean agregar(Reserva reserva) {
		if (reserva == null) {
			return false;
		}
		
		// Generar ID automático si no tiene
		if (reserva.getIdReserva() == null || reserva.getIdReserva().isEmpty()) {
			reserva.setIdReserva("RES-" + String.format("%03d", contadorId++));
		}
		
		// Calcular número de noches automáticamente
		reserva.calcularNumeroNoches();
		
		listaReservas.add(reserva);
		return true;
	}
	
	/**
	 * Busca una reserva por su ID
	 */
	public Reserva buscarPorId(String idReserva) {
		for (Reserva r : listaReservas) {
			if (r.getIdReserva().equals(idReserva)) {
				return r;
			}
		}
		return null;
	}
	
	/**
	 * Busca reservas por ID de huésped
	 */
	public ArrayList<Reserva> buscarPorHuesped(String idHuesped) {
		ArrayList<Reserva> reservas = new ArrayList<>();
		for (Reserva r : listaReservas) {
			if (r.getIdHuesped().equals(idHuesped)) {
				reservas.add(r);
			}
		}
		return reservas;
	}
	
	/**
	 * Busca reservas por número de habitación
	 */
	public ArrayList<Reserva> buscarPorHabitacion(String numeroHabitacion) {
		ArrayList<Reserva> reservas = new ArrayList<>();
		for (Reserva r : listaReservas) {
			if (r.getNumeroHabitacion().equals(numeroHabitacion)) {
				reservas.add(r);
			}
		}
		return reservas;
	}
	
	/**
	 * Actualiza una reserva existente
	 */
	public boolean actualizar(Reserva reservaActualizada) {
		if (reservaActualizada == null || reservaActualizada.getIdReserva() == null) {
			return false;
		}
		
		// Recalcular número de noches
		reservaActualizada.calcularNumeroNoches();
		
		for (int i = 0; i < listaReservas.size(); i++) {
			if (listaReservas.get(i).getIdReserva().equals(reservaActualizada.getIdReserva())) {
				listaReservas.set(i, reservaActualizada);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Elimina una reserva por su ID
	 */
	public boolean eliminar(String idReserva) {
		for (int i = 0; i < listaReservas.size(); i++) {
			if (listaReservas.get(i).getIdReserva().equals(idReserva)) {
				listaReservas.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Obtiene la lista completa de reservas
	 */
	public ArrayList<Reserva> obtenerTodas() {
		return new ArrayList<>(listaReservas);
	}
	
	/**
	 * Obtiene reservas pendientes
	 */
	public ArrayList<Reserva> obtenerPendientes() {
		ArrayList<Reserva> pendientes = new ArrayList<>();
		for (Reserva r : listaReservas) {
			if ("Pendiente".equals(r.getEstado())) {
				pendientes.add(r);
			}
		}
		return pendientes;
	}
	
	/**
	 * Obtiene reservas confirmadas
	 */
	public ArrayList<Reserva> obtenerConfirmadas() {
		ArrayList<Reserva> confirmadas = new ArrayList<>();
		for (Reserva r : listaReservas) {
			if ("Confirmada".equals(r.getEstado())) {
				confirmadas.add(r);
			}
		}
		return confirmadas;
	}
	
	/**
	 * Obtiene el número total de reservas
	 */
	public int obtenerCantidad() {
		return listaReservas.size();
	}
	
	/**
	 * Verifica si una habitación está disponible en un rango de fechas
	 */
	public boolean estaDisponible(String numeroHabitacion, LocalDate checkIn, LocalDate checkOut) {
		for (Reserva r : listaReservas) {
			if (r.getNumeroHabitacion().equals(numeroHabitacion) &&
			    ("Confirmada".equals(r.getEstado()) || "Pendiente".equals(r.getEstado()))) {
				
				// Verificar solapamiento de fechas
				if (!(checkOut.isBefore(r.getCheckIn()) || checkIn.isAfter(r.getCheckOut()))) {
					return false; // Hay solapamiento
				}
			}
		}
		return true; // No hay solapamiento, está disponible
	}
}

