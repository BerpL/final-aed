package clases;

import java.util.ArrayList;

/**
 * Clase para gestionar la lista de habitaciones con operaciones CRUD
 * Usa ARRAYLIST para almacenamiento principal
 */
public class GestionHabitaciones {
	
	private ArrayList<Habitacion> listaHabitaciones;
	
	public GestionHabitaciones() {
		listaHabitaciones = new ArrayList<>();
		inicializarDatosMocked();
	}
	
	/**
	 * Inicializa datos mocked para pruebas
	 */
	private void inicializarDatosMocked() {
		agregar(new Habitacion("101", "Suite Presidencial", "1", 500.00, 4,
		                      "Disponible", "Suite de lujo con vista panorámica y jacuzzi privado",
		                      true, true, true, true, true));
		
		agregar(new Habitacion("102", "Suite", "1", 350.00, 3,
		                      "Ocupada", "Suite cómoda con todas las amenidades",
		                      true, true, true, true, false));
		
		agregar(new Habitacion("201", "Doble", "2", 200.00, 2,
		                      "Disponible", "Habitación doble estándar",
		                      true, true, true, false, false));
		
		agregar(new Habitacion("202", "Doble", "2", 200.00, 2,
		                      "Disponible", "Habitación doble estándar",
		                      true, true, true, false, false));
		
		agregar(new Habitacion("103", "Individual", "1", 100.00, 1,
		                      "Ocupada", "Habitación individual básica",
		                      true, true, true, false, false));
		
		agregar(new Habitacion("203", "Suite Ejecutiva", "2", 400.00, 3,
		                      "Disponible", "Suite ejecutiva con sala de trabajo",
		                      true, true, true, true, false));
		
		agregar(new Habitacion("301", "Doble Estándar", "3", 150.00, 2,
		                      "Disponible", "Habitación doble estándar",
		                      true, true, true, false, false));
		
		agregar(new Habitacion("302", "Individual", "3", 100.00, 1,
		                      "Mantenimiento", "Habitación individual en mantenimiento",
		                      true, true, true, false, false));
	}
	
	/**
	 * Agrega una nueva habitación a la lista
	 */
	public boolean agregar(Habitacion habitacion) {
		if (habitacion == null) {
			return false;
		}
		
		// Verificar que no exista otra habitación con el mismo número
		if (buscarPorNumero(habitacion.getNumero()) != null) {
			return false;
		}
		
		listaHabitaciones.add(habitacion);
		return true;
	}
	
	/**
	 * Busca una habitación por su número
	 */
	public Habitacion buscarPorNumero(String numero) {
		for (Habitacion h : listaHabitaciones) {
			if (h.getNumero().equals(numero)) {
				return h;
			}
		}
		return null;
	}
	
	/**
	 * Actualiza una habitación existente
	 */
	public boolean actualizar(Habitacion habitacionActualizada) {
		if (habitacionActualizada == null || habitacionActualizada.getNumero() == null) {
			return false;
		}
		
		for (int i = 0; i < listaHabitaciones.size(); i++) {
			if (listaHabitaciones.get(i).getNumero().equals(habitacionActualizada.getNumero())) {
				listaHabitaciones.set(i, habitacionActualizada);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Elimina una habitación por su número
	 */
	public boolean eliminar(String numero) {
		for (int i = 0; i < listaHabitaciones.size(); i++) {
			if (listaHabitaciones.get(i).getNumero().equals(numero)) {
				listaHabitaciones.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Obtiene la lista completa de habitaciones
	 */
	public ArrayList<Habitacion> obtenerTodas() {
		return new ArrayList<>(listaHabitaciones);
	}
	
	/**
	 * Obtiene habitaciones disponibles
	 */
	public ArrayList<Habitacion> obtenerDisponibles() {
		ArrayList<Habitacion> disponibles = new ArrayList<>();
		for (Habitacion h : listaHabitaciones) {
			if ("Disponible".equals(h.getEstado())) {
				disponibles.add(h);
			}
		}
		return disponibles;
	}
	
	/**
	 * Obtiene el número total de habitaciones
	 */
	public int obtenerCantidad() {
		return listaHabitaciones.size();
	}
	
	/**
	 * Obtiene un array de números de habitaciones para ComboBox
	 */
	public String[] obtenerNumerosHabitaciones() {
		String[] numeros = new String[listaHabitaciones.size()];
		for (int i = 0; i < listaHabitaciones.size(); i++) {
			numeros[i] = listaHabitaciones.get(i).getNumero();
		}
		return numeros;
	}
	
	/**
	 * Obtiene un array de números de habitaciones disponibles para ComboBox
	 */
	public String[] obtenerNumerosHabitacionesDisponibles() {
		ArrayList<Habitacion> disponibles = obtenerDisponibles();
		String[] numeros = new String[disponibles.size()];
		for (int i = 0; i < disponibles.size(); i++) {
			numeros[i] = disponibles.get(i).getNumero();
		}
		return numeros;
	}
}

