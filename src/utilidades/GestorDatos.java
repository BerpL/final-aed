package utilidades;

import clases.GestionHuespedes;
import clases.GestionHabitaciones;
import clases.GestionTiposHabitacion;
import clases.GestionReservas;

/**
 * Gestor centralizado para compartir instancias de las clases de gestión
 * entre todas las ventanas de la aplicación
 */
public class GestorDatos {
    
    private static GestorDatos instancia;
    
    private GestionHuespedes gestionHuespedes;
    private GestionHabitaciones gestionHabitaciones;
    private GestionTiposHabitacion gestionTiposHabitacion;
    private GestionReservas gestionReservas;
    
    private GestorDatos() {
        // Inicializar todas las gestiones
        gestionHuespedes = new GestionHuespedes();
        gestionHabitaciones = new GestionHabitaciones();
        gestionTiposHabitacion = new GestionTiposHabitacion();
        gestionReservas = new GestionReservas();
    }
    
    /**
     * Obtiene la instancia única del gestor de datos (Singleton)
     */
    public static GestorDatos getInstancia() {
        if (instancia == null) {
            instancia = new GestorDatos();
        }
        return instancia;
    }
    
    /**
     * Obtiene la instancia compartida de GestionHuespedes
     */
    public GestionHuespedes getGestionHuespedes() {
        return gestionHuespedes;
    }
    
    /**
     * Obtiene la instancia compartida de GestionHabitaciones
     */
    public GestionHabitaciones getGestionHabitaciones() {
        return gestionHabitaciones;
    }
    
    /**
     * Obtiene la instancia compartida de GestionTiposHabitacion
     */
    public GestionTiposHabitacion getGestionTiposHabitacion() {
        return gestionTiposHabitacion;
    }
    
    /**
     * Obtiene la instancia compartida de GestionReservas
     */
    public GestionReservas getGestionReservas() {
        return gestionReservas;
    }
}

