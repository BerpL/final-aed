package utilidades;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ventanas.MainWindow;
import ventanas.HuespedesWindow;
import ventanas.ReportesWindow;
import ventanas.ReservasWindow;
import ventanas.TiposHabitacionWindow;
import ventanas.HabitacionesWindow;
import componentes.PanelNavegacion;

/**
 * Gestor centralizado para cambiar entre diferentes ventanas de la aplicación
 */
public class GestorVentanas {
    
    private BorderPane contenedor;
    private StackPane stackPane;
    private MainWindow mainWindow;
    private HuespedesWindow huespedesWindow;
    private HabitacionesWindow habitacionesWindow;
    private TiposHabitacionWindow tiposHabitacionWindow;
    private ReservasWindow reservasWindow;
    private ReportesWindow reportesWindow;
    
    public GestorVentanas() {
        contenedor = new BorderPane();
        stackPane = new StackPane();
        
        // Crear todas las ventanas
        mainWindow = new MainWindow();
        huespedesWindow = new HuespedesWindow();
        habitacionesWindow = new HabitacionesWindow();
        tiposHabitacionWindow = new TiposHabitacionWindow();
        reservasWindow = new ReservasWindow();
        reportesWindow = new ReportesWindow();
        
        // Agregar ventanas al stack pane
        stackPane.getChildren().addAll(
            mainWindow.getRoot(),
            huespedesWindow.getRoot(),
            habitacionesWindow.getRoot(),
            tiposHabitacionWindow.getRoot(),
            reservasWindow.getRoot(),
            reportesWindow.getRoot()
        );
        
        contenedor.setCenter(stackPane);
        
        // Configurar navegación
        configurarNavegacion();
        
        // Configurar gestor de ventanas en MainWindow para los botones de acceso rápido
        mainWindow.setGestorVentanas(this);
    }
    
    private void configurarNavegacion() {
        PanelNavegacion.NavegacionListener listener = new PanelNavegacion.NavegacionListener() {
            @Override
            public void onItemSeleccionado(int indice) {
                switch(indice) {
                    case 0: // Navegación (Inicio)
                        mostrarPrincipal();
                        break;
                    case 1: // Huéspedes
                        mostrarHuespedes();
                        break;
                    case 2: // Habitaciones
                        mostrarHabitaciones();
                        break;
                    case 3: // Tipos Habitación
                        mostrarTiposHabitacion();
                        break;
                    case 4: // Reservas
                        mostrarReservas();
                        break;
                    case 5: // Reportes
                        mostrarReportes();
                        break;
                }
            }
        };
        
        // Configurar listeners en todos los paneles de navegación
        mainWindow.getPanelNavegacion().setNavegacionListener(listener);
        huespedesWindow.getPanelNavegacion().setNavegacionListener(listener);
        habitacionesWindow.getPanelNavegacion().setNavegacionListener(listener);
        tiposHabitacionWindow.getPanelNavegacion().setNavegacionListener(listener);
        reservasWindow.getPanelNavegacion().setNavegacionListener(listener);
        reportesWindow.getPanelNavegacion().setNavegacionListener(listener);
    }
    
    public BorderPane getContenedor() {
        return contenedor;
    }
    
    private void mostrarVentana(int indice) {
        for (int i = 0; i < stackPane.getChildren().size(); i++) {
            stackPane.getChildren().get(i).setVisible(i == indice);
            stackPane.getChildren().get(i).setManaged(i == indice);
        }
    }
    
    public void mostrarPrincipal() {
        mostrarVentana(0);
    }
    
    public void mostrarHuespedes() {
        mostrarVentana(1);
    }
    
    public void mostrarHabitaciones() {
        mostrarVentana(2);
    }
    
    public void mostrarTiposHabitacion() {
        mostrarVentana(3);
    }
    
    public void mostrarReservas() {
        mostrarVentana(4);
    }
    
    public void mostrarReportes() {
        mostrarVentana(5);
    }
}
