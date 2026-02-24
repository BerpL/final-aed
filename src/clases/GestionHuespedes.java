package clases;

import java.util.ArrayList;

/**
 * Clase para gestionar la lista de huéspedes con operaciones CRUD
 * Usa ARRAY para almacenamiento principal
 */
public class GestionHuespedes {
	
	private Huesped[] listaHuespedes;
	private int cantidad;
	private int contadorId;
	private static final int TAMANO_INICIAL = 10;
	
	public GestionHuespedes() {
		listaHuespedes = new Huesped[TAMANO_INICIAL];
		cantidad = 0;
		contadorId = 1;
		inicializarDatosMocked();
		// Inicializar contadorId basándose en los IDs existentes
		inicializarContadorId();
	}
	
	/**
	 * Inicializa datos mocked para pruebas
	 */
	private void inicializarDatosMocked() {
		// DNI con exactamente 8 dígitos para pasar validación al editar
		agregar(new Huesped("0001", "12345678", "DNI", "Juan Carlos", "Cepeda de la Cruz",
		                    "987654321", "juan.carlos@email.com", "Av. Principal 123",
		                    "15/05/1985", "Masculino", "Peruana", true, false));
		
		agregar(new Huesped("0002", "87654321", "DNI", "Maria Rosa", "Rodriguez Perez",
		                    "912345678", "maria.rodriguez@email.com", "Jr. Los Olivos 456",
		                    "22/08/1990", "Femenino", "Peruana", false, true));
		
		agregar(new Huesped("0003", "45678901", "DNI", "Pedro Antonio", "Martinez",
		                    "952789053", "pedro.martinez@email.com", "Calle Real 789",
		                    "10/12/1988", "Masculino", "Peruana", true, true));
	}
	
	/**
	 * Inicializa el contadorId basándose en el máximo ID existente
	 */
	private void inicializarContadorId() {
		int maxId = 0;
		for (int i = 0; i < cantidad; i++) {
			if (listaHuespedes[i] != null && listaHuespedes[i].getId() != null) {
				try {
					// Extraer el número del ID (ej: "0001" -> 1)
					String idStr = listaHuespedes[i].getId().trim();
					int idNum = Integer.parseInt(idStr);
					if (idNum > maxId) {
						maxId = idNum;
					}
				} catch (NumberFormatException e) {
					// Si el ID no es numérico, ignorarlo
				}
			}
		}
		// El siguiente ID será maxId + 1
		contadorId = maxId + 1;
	}
	
	/**
	 * Redimensiona el array si es necesario
	 */
	private void redimensionarArray() {
		if (cantidad >= listaHuespedes.length) {
			Huesped[] nuevoArray = new Huesped[listaHuespedes.length * 2];
			for (int i = 0; i < cantidad; i++) {
				nuevoArray[i] = listaHuespedes[i];
			}
			listaHuespedes = nuevoArray;
		}
	}
	
	/**
	 * Agrega un nuevo huésped a la lista
	 */
	public boolean agregar(Huesped huesped) {
		if (huesped == null) {
			return false;
		}
		
		// Generar ID automático si no tiene
		if (huesped.getId() == null || huesped.getId().isEmpty()) {
			huesped.setId(String.format("%04d", contadorId++));
		}
		
		// Redimensionar si es necesario
		redimensionarArray();
		
		listaHuespedes[cantidad] = huesped;
		cantidad++;
		return true;
	}
	
	/**
	 * Busca un huésped por su ID
	 */
	public Huesped buscarPorId(String id) {
		for (int i = 0; i < cantidad; i++) {
			if (listaHuespedes[i].getId().equals(id)) {
				return listaHuespedes[i];
			}
		}
		return null;
	}
	
	/**
	 * Busca un huésped por su documento
	 */
	public Huesped buscarPorDocumento(String documento) {
		for (int i = 0; i < cantidad; i++) {
			if (listaHuespedes[i].getDocumento().equals(documento)) {
				return listaHuespedes[i];
			}
		}
		return null;
	}
	
	/**
	 * Actualiza un huésped existente
	 */
	public boolean actualizar(Huesped huespedActualizado) {
		if (huespedActualizado == null || huespedActualizado.getId() == null) {
			return false;
		}
		
		for (int i = 0; i < cantidad; i++) {
			if (listaHuespedes[i].getId().equals(huespedActualizado.getId())) {
				listaHuespedes[i] = huespedActualizado;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Elimina un huésped por su ID
	 */
	public boolean eliminar(String id) {
		for (int i = 0; i < cantidad; i++) {
			if (listaHuespedes[i].getId().equals(id)) {
				// Desplazar elementos hacia la izquierda
				for (int j = i; j < cantidad - 1; j++) {
					listaHuespedes[j] = listaHuespedes[j + 1];
				}
				listaHuespedes[cantidad - 1] = null;
				cantidad--;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Obtiene la lista completa de huéspedes como ArrayList
	 */
	public ArrayList<Huesped> obtenerTodos() {
		ArrayList<Huesped> lista = new ArrayList<>();
		for (int i = 0; i < cantidad; i++) {
			lista.add(listaHuespedes[i]);
		}
		return lista;
	}
	
	/**
	 * Obtiene la lista completa de huéspedes como array
	 */
	public Huesped[] obtenerTodosArray() {
		Huesped[] resultado = new Huesped[cantidad];
		for (int i = 0; i < cantidad; i++) {
			resultado[i] = listaHuespedes[i];
		}
		return resultado;
	}
	
	/**
	 * Obtiene el número total de huéspedes
	 */
	public int obtenerCantidad() {
		return cantidad;
	}
	
	/**
	 * Obtiene un array de nombres completos para ComboBox
	 */
	public String[] obtenerNombresCompletos() {
		String[] nombres = new String[cantidad];
		for (int i = 0; i < cantidad; i++) {
			nombres[i] = listaHuespedes[i].obtenerNombreCompleto();
		}
		return nombres;
	}
}
