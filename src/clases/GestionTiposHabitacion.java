package clases;

import java.util.ArrayList;

/**
 * Clase para gestionar la lista de tipos de habitación con operaciones CRUD
 * Usa ARRAY para almacenamiento principal
 */
public class GestionTiposHabitacion {
	
	private TipoHabitacion[] listaTipos;
	private int cantidad;
	private static final int TAMANO_INICIAL = 10;
	
	public GestionTiposHabitacion() {
		listaTipos = new TipoHabitacion[TAMANO_INICIAL];
		cantidad = 0;
		inicializarDatosMocked();
	}
	
	/**
	 * Inicializa datos mocked para pruebas
	 */
	private void inicializarDatosMocked() {
		agregar(new TipoHabitacion("SUITE-PRES", "Suite Presidencial", 500.00, 4,
		                          "King", 80.0, true, true, true, true, true,
		                          "Suite de lujo con vista panorámica, jacuzzi privado y servicio de mayordomo."));
		
		agregar(new TipoHabitacion("SUITE", "Suite", 350.00, 3,
		                          "Queen", 60.0, true, true, true, true, false,
		                          "Suite cómoda con todas las amenidades básicas y algunas premium."));
		
		agregar(new TipoHabitacion("DOBLE", "Habitación Doble", 200.00, 2,
		                          "Doble", 35.0, true, true, true, false, false,
		                          "Habitación doble estándar ideal para parejas."));
		
		agregar(new TipoHabitacion("INDIVIDUAL", "Habitación Individual", 100.00, 1,
		                          "Individual", 20.0, true, true, true, false, false,
		                          "Habitación individual básica para una persona."));
		
		agregar(new TipoHabitacion("SUITE-EJEC", "Suite Ejecutiva", 400.00, 3,
		                          "King", 65.0, true, true, true, true, false,
		                          "Suite ejecutiva con sala de trabajo y todas las comodidades."));
	}
	
	/**
	 * Redimensiona el array si es necesario
	 */
	private void redimensionarArray() {
		if (cantidad >= listaTipos.length) {
			TipoHabitacion[] nuevoArray = new TipoHabitacion[listaTipos.length * 2];
			for (int i = 0; i < cantidad; i++) {
				nuevoArray[i] = listaTipos[i];
			}
			listaTipos = nuevoArray;
		}
	}
	
	/**
	 * Agrega un nuevo tipo de habitación a la lista
	 */
	public boolean agregar(TipoHabitacion tipo) {
		if (tipo == null) {
			return false;
		}
		
		// Verificar que no exista otro tipo con el mismo código
		if (buscarPorCodigo(tipo.getCodigo()) != null) {
			return false;
		}
		
		// Redimensionar si es necesario
		redimensionarArray();
		
		listaTipos[cantidad] = tipo;
		cantidad++;
		return true;
	}
	
	/**
	 * Busca un tipo de habitación por su código
	 */
	public TipoHabitacion buscarPorCodigo(String codigo) {
		for (int i = 0; i < cantidad; i++) {
			if (listaTipos[i].getCodigo().equals(codigo)) {
				return listaTipos[i];
			}
		}
		return null;
	}

	/**
	 * Busca un tipo de habitación por su nombre
	 */
	public TipoHabitacion buscarPorNombre(String nombre) {
		if (nombre == null) return null;
		for (int i = 0; i < cantidad; i++) {
			if (nombre.equals(listaTipos[i].getNombre())) {
				return listaTipos[i];
			}
		}
		return null;
	}

	/**
	 * Actualiza un tipo de habitación existente
	 */
	public boolean actualizar(TipoHabitacion tipoActualizado) {
		if (tipoActualizado == null || tipoActualizado.getCodigo() == null) {
			return false;
		}
		
		for (int i = 0; i < cantidad; i++) {
			if (listaTipos[i].getCodigo().equals(tipoActualizado.getCodigo())) {
				listaTipos[i] = tipoActualizado;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Elimina un tipo de habitación por su código
	 */
	public boolean eliminar(String codigo) {
		for (int i = 0; i < cantidad; i++) {
			if (listaTipos[i].getCodigo().equals(codigo)) {
				// Desplazar elementos hacia la izquierda
				for (int j = i; j < cantidad - 1; j++) {
					listaTipos[j] = listaTipos[j + 1];
				}
				listaTipos[cantidad - 1] = null;
				cantidad--;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Obtiene la lista completa de tipos de habitación como ArrayList
	 */
	public ArrayList<TipoHabitacion> obtenerTodos() {
		ArrayList<TipoHabitacion> lista = new ArrayList<>();
		for (int i = 0; i < cantidad; i++) {
			lista.add(listaTipos[i]);
		}
		return lista;
	}
	
	/**
	 * Obtiene la lista completa de tipos de habitación como array
	 */
	public TipoHabitacion[] obtenerTodosArray() {
		TipoHabitacion[] resultado = new TipoHabitacion[cantidad];
		for (int i = 0; i < cantidad; i++) {
			resultado[i] = listaTipos[i];
		}
		return resultado;
	}
	
	/**
	 * Obtiene el número total de tipos de habitación
	 */
	public int obtenerCantidad() {
		return cantidad;
	}
	
	/**
	 * Obtiene un array de nombres de tipos para ComboBox
	 */
	public String[] obtenerNombresTipos() {
		String[] nombres = new String[cantidad];
		for (int i = 0; i < cantidad; i++) {
			nombres[i] = listaTipos[i].getNombre();
		}
		return nombres;
	}
}
