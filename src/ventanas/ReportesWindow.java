package ventanas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import componentes.BarraEstado;
import componentes.BarraTitulo;
import componentes.IconoSimple;
import componentes.PanelNavegacion;
import componentes.TablaDatos;
import utilidades.Constantes;
import utilidades.GestorDatos;
import clases.GestionHabitaciones;
import clases.GestionReservas;
import clases.GestionHuespedes;
import clases.Habitacion;
import clases.Reserva;
import clases.Huesped;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Ventana para la generación de reportes
 */
public class ReportesWindow {
    
    private BorderPane root;
    private BarraTitulo barraTitulo;
    private PanelNavegacion panelNavegacion;
    private BarraEstado barraEstado;
    private TablaDatos tablaReporte;
    private Label lblTituloTabla;
    private VBox panelTablaContainer; // Contenedor para poder reemplazar la tabla
    private String reporteSeleccionado = "Ocupación de Habitaciones"; // Por defecto
    private Map<String, VBox> cardsReporte = new HashMap<>(); // Mapa para guardar las cards por tipo de reporte
    private GestionHabitaciones gestionHabitaciones;
    private GestionReservas gestionReservas;
    private GestionHuespedes gestionHuespedes;
    
    public ReportesWindow() {
        // Obtener instancias compartidas de las gestiones
        GestorDatos gestorDatos = GestorDatos.getInstancia();
        gestionHabitaciones = gestorDatos.getGestionHabitaciones();
        gestionReservas = gestorDatos.getGestionReservas();
        gestionHuespedes = gestorDatos.getGestionHuespedes();
        
        root = new BorderPane();
        root.setStyle("-fx-background-color: #" + Constantes.COLOR_FONDO.toString().substring(2, 8) + ";");
        
        barraTitulo = new BarraTitulo("Sistema de Reserva de Hoteles - Reportes");
        root.setTop(barraTitulo);
        
        BorderPane panelPrincipal = new BorderPane();
        panelPrincipal.setStyle("-fx-background-color: #" + Constantes.COLOR_FONDO.toString().substring(2, 8) + ";");
        
        panelNavegacion = new PanelNavegacion();
        panelNavegacion.seleccionarItem(5); // Reportes seleccionado
        panelPrincipal.setLeft(panelNavegacion);
        
        VBox panelContenido = crearPanelContenido();
        panelPrincipal.setCenter(panelContenido);
        
        root.setCenter(panelPrincipal);
        
        barraEstado = new BarraEstado();
        barraEstado.setEstado("Conectado a la base de datos");
        root.setBottom(barraEstado);
    }
    
    private VBox crearPanelContenido() {
        VBox panel = new VBox(16);
        panel.setPadding(new Insets(16));
        panel.setStyle("-fx-background-color: #" + Constantes.COLOR_BLANCO.toString().substring(2, 8) + ";");
        
        Label lblTitulo = new Label("Generación de Reportes");
        lblTitulo.setFont(Constantes.FUENTE_SUBTITULO);
        lblTitulo.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        
        Label lblDescripcion = new Label("Seleccione el tipo de reporte que desea generar:");
        lblDescripcion.setFont(Constantes.FUENTE_NORMAL);
        lblDescripcion.setTextFill(Constantes.COLOR_TEXTO_SECUNDARIO);
        
        HBox panelBotonesReporte = new HBox(16);
        panelBotonesReporte.setAlignment(Pos.CENTER_LEFT);
        
        // Card 1: Ocupación de Habitaciones (azul #0078D4)
        VBox card1 = crearCardReporte(
            IconoSimple.crearIconoCama(32, Color.web("#0078D4")),
            "Ocupación de Habitaciones",
            "Estado actual de habitaciones",
            "Ocupación de Habitaciones"
        );
        cardsReporte.put("Ocupación de Habitaciones", card1);
        
        // Card 2: Ingresos del Mes (verde #22C55E)
        VBox card2 = crearCardReporte(
            crearIconoTrendingUp(32, Color.web("#22C55E")),
            "Ingresos del Mes",
            "Resumen de ingresos mensuales",
            "Ingresos del Mes"
        );
        cardsReporte.put("Ingresos del Mes", card2);
        
        // Card 3: Reservas Pendientes (amarillo #F59E0B)
        VBox card3 = crearCardReporte(
            crearIconoCircleDot(32, Color.web("#F59E0B")),
            "Reservas Pendientes",
            "Reservas sin confirmar",
            "Reservas Pendientes"
        );
        cardsReporte.put("Reservas Pendientes", card3);
        
        panelBotonesReporte.getChildren().addAll(card1, card2, card3);
        
        // Aplicar estilo inicial a la card por defecto
        actualizarEstiloCards(card1, "Ocupación de Habitaciones");
        
        HBox panelGenerar = new HBox();
        panelGenerar.setAlignment(Pos.CENTER_LEFT);
        
        HBox btnGenerar = new HBox(8);
        btnGenerar.setAlignment(Pos.CENTER);
        btnGenerar.setPadding(new Insets(8, 24, 8, 24));
        btnGenerar.setStyle("-fx-background-color: #" + Constantes.COLOR_PRIMARIO.toString().substring(2, 8) + 
                           "; -fx-border-color: #" + Constantes.COLOR_PRIMARIO_OSCURO.toString().substring(2, 8) + 
                           "; -fx-border-width: 1; -fx-border-radius: 2; -fx-background-radius: 2;");
        btnGenerar.setCursor(javafx.scene.Cursor.HAND);
        
        IconoSimple iconoGenerar = crearIconoFileText(14, Color.WHITE);
        Label lblGenerar = new Label("Generar Reporte");
        lblGenerar.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblGenerar.setTextFill(Color.WHITE);
        btnGenerar.getChildren().addAll(iconoGenerar, lblGenerar);
        
        // Acción al hacer click en Generar Reporte
        btnGenerar.setOnMouseClicked(_ -> {
            ventanas.dialogos.DialogoVistaPreviaReporte dialogo = 
                new ventanas.dialogos.DialogoVistaPreviaReporte(reporteSeleccionado, obtenerDatosReporte());
            dialogo.mostrar();
        });
        
        panelGenerar.getChildren().add(btnGenerar);
        
        Region separador = new Region();
        separador.setPrefHeight(1);
        separador.setMaxHeight(1);
        separador.setStyle("-fx-background-color: #" + Constantes.COLOR_BORDE_CLARO.toString().substring(2, 8) + ";");
        
        VBox panelTabla = crearPanelTabla();
        
        panel.getChildren().addAll(lblTitulo, lblDescripcion, panelBotonesReporte, panelGenerar, separador, panelTabla);
        
        return panel;
    }
    
    private VBox crearPanelTabla() {
        VBox panel = new VBox(12);
        panel.setPadding(new Insets(16, 0, 0, 0));
        panel.setStyle("-fx-background-color: #" + Constantes.COLOR_BLANCO.toString().substring(2, 8) + ";");
        
        lblTituloTabla = new Label("Vista Previa: Ocupación de Habitaciones");
        lblTituloTabla.setFont(Constantes.FUENTE_SUBTITULO);
        lblTituloTabla.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        
        // Contenedor para la tabla (para poder reemplazarla)
        panelTablaContainer = new VBox();
        VBox.setVgrow(panelTablaContainer, javafx.scene.layout.Priority.ALWAYS);
        
        // Inicializar con datos de Ocupación de Habitaciones
        actualizarTabla("Ocupación de Habitaciones");
        
        panel.getChildren().addAll(lblTituloTabla, panelTablaContainer);
        
        return panel;
    }
    
    private void actualizarTabla(String tipoReporte) {
        reporteSeleccionado = tipoReporte;
        lblTituloTabla.setText("Vista Previa: " + tipoReporte);
        
        // Limpiar contenedor y crear nueva tabla
        panelTablaContainer.getChildren().clear();
        
        // Crear nueva tabla según el tipo de reporte usando datos reales de las gestiones
        if (tipoReporte.equals("Ocupación de Habitaciones")) {
            String[] columnas = {"Habitación", "Tipo", "Precio/Noche", "Huésped Actual", "Estado"};
            tablaReporte = new TablaDatos(columnas);
            cargarDatosOcupacionHabitaciones();
        } else if (tipoReporte.equals("Ingresos del Mes")) {
            String[] columnas = {"Mes", "Total Reservas", "Ingresos Totales", "Promedio por Reserva", "Estado"};
            tablaReporte = new TablaDatos(columnas);
            cargarDatosIngresosMes();
        } else if (tipoReporte.equals("Reservas Pendientes")) {
            String[] columnas = {"ID Reserva", "Huésped", "Habitación", "Check-In", "Check-Out", "Estado"};
            tablaReporte = new TablaDatos(columnas);
            cargarDatosReservasPendientes();
        }
        
        VBox.setVgrow(tablaReporte, javafx.scene.layout.Priority.ALWAYS);
        panelTablaContainer.getChildren().add(tablaReporte);
    }
    
    /**
     * Carga datos de ocupación de habitaciones desde GestionHabitaciones
     */
    private void cargarDatosOcupacionHabitaciones() {
        ArrayList<Habitacion> habitaciones = gestionHabitaciones.obtenerTodas();
        for (Habitacion h : habitaciones) {
            // Buscar huésped actual si la habitación está ocupada
            String huespedActual = "-";
            if ("Ocupada".equals(h.getEstado())) {
                // Buscar reserva activa para esta habitación
                ArrayList<Reserva> reservas = gestionReservas.buscarPorHabitacion(h.getNumero());
                LocalDate hoy = LocalDate.now();
                for (Reserva r : reservas) {
                    if (("Confirmada".equals(r.getEstado()) || "Pendiente".equals(r.getEstado())) &&
                        !r.getCheckOut().isBefore(hoy) && !r.getCheckIn().isAfter(hoy)) {
                        huespedActual = r.getNombreHuesped();
                        break;
                    }
                }
            }
            
            tablaReporte.agregarFila(new String[]{
                h.getNumero(),
                h.getTipo(),
                h.obtenerPrecioFormateado(),
                huespedActual,
                h.getEstado()
            });
        }
    }
    
    /**
     * Carga datos de ingresos del mes desde GestionReservas
     */
    private void cargarDatosIngresosMes() {
        ArrayList<Reserva> reservas = gestionReservas.obtenerTodas();
        Map<String, DatosMes> datosPorMes = new HashMap<>();
        
        // Agrupar reservas por mes
        for (Reserva r : reservas) {
            if ("Confirmada".equals(r.getEstado()) || "Completada".equals(r.getEstado())) {
                String mes = r.getCheckIn().format(DateTimeFormatter.ofPattern("MM/yyyy"));
                DatosMes datos = datosPorMes.getOrDefault(mes, new DatosMes(mes));
                datos.totalReservas++;
                datos.ingresosTotales += r.getTotal();
                datosPorMes.put(mes, datos);
            }
        }
        
        // Convertir a lista y ordenar por fecha (más reciente primero)
        ArrayList<DatosMes> listaMeses = new ArrayList<>(datosPorMes.values());
        listaMeses.sort((a, b) -> {
            // Comparar por fecha (formato MM/yyyy)
            String[] partesA = a.mes.split("/");
            String[] partesB = b.mes.split("/");
            int añoA = Integer.parseInt(partesA[1]);
            int añoB = Integer.parseInt(partesB[1]);
            if (añoA != añoB) return añoB - añoA; // Año más reciente primero
            int mesA = Integer.parseInt(partesA[0]);
            int mesB = Integer.parseInt(partesB[0]);
            return mesB - mesA; // Mes más reciente primero
        });
        
        // Agregar a la tabla
        for (DatosMes datos : listaMeses) {
            double promedio = datos.totalReservas > 0 ? datos.ingresosTotales / datos.totalReservas : 0;
            LocalDate ahora = LocalDate.now();
            String mesActual = ahora.format(DateTimeFormatter.ofPattern("MM/yyyy"));
            String estado = mesActual.equals(datos.mes) ? "En curso" : "Completado";
            
            tablaReporte.agregarFila(new String[]{
                datos.mes,
                String.valueOf(datos.totalReservas),
                String.format("S/. %,.2f", datos.ingresosTotales),
                String.format("S/. %,.2f", promedio),
                estado
            });
        }
    }
    
    /**
     * Carga datos de reservas pendientes desde GestionReservas
     */
    private void cargarDatosReservasPendientes() {
        ArrayList<Reserva> reservasPendientes = gestionReservas.obtenerPendientes();
        for (Reserva r : reservasPendientes) {
            tablaReporte.agregarFila(new String[]{
                r.getIdReserva(),
                r.getNombreHuesped(),
                r.getNumeroHabitacion(),
                r.obtenerCheckInFormateado(),
                r.obtenerCheckOutFormateado(),
                r.getEstado()
            });
        }
    }
    
    /**
     * Clase auxiliar para agrupar datos por mes
     */
    private static class DatosMes {
        String mes;
        int totalReservas;
        double ingresosTotales;
        
        DatosMes(String mes) {
            this.mes = mes;
            this.totalReservas = 0;
            this.ingresosTotales = 0.0;
        }
    }
    
    private ArrayList<String[]> obtenerDatosReporte() {
        ArrayList<String[]> datos = new ArrayList<>();
        
        if (reporteSeleccionado.equals("Ocupación de Habitaciones")) {
            datos.add(new String[]{"Habitación", "Tipo", "Precio/Noche", "Huésped Actual", "Estado"});
            ArrayList<Habitacion> habitaciones = gestionHabitaciones.obtenerTodas();
            for (Habitacion h : habitaciones) {
                String huespedActual = "-";
                if ("Ocupada".equals(h.getEstado())) {
                    ArrayList<Reserva> reservas = gestionReservas.buscarPorHabitacion(h.getNumero());
                    LocalDate hoy = LocalDate.now();
                    for (Reserva r : reservas) {
                        if (("Confirmada".equals(r.getEstado()) || "Pendiente".equals(r.getEstado())) &&
                            !r.getCheckOut().isBefore(hoy) && !r.getCheckIn().isAfter(hoy)) {
                            huespedActual = r.getNombreHuesped();
                            break;
                        }
                    }
                }
                datos.add(new String[]{
                    h.getNumero(),
                    h.getTipo(),
                    h.obtenerPrecioFormateado(),
                    huespedActual,
                    h.getEstado()
                });
            }
        } else if (reporteSeleccionado.equals("Ingresos del Mes")) {
            datos.add(new String[]{"Mes", "Total Reservas", "Ingresos Totales", "Promedio por Reserva", "Estado"});
            ArrayList<Reserva> reservas = gestionReservas.obtenerTodas();
            Map<String, DatosMes> datosPorMes = new HashMap<>();
            
            for (Reserva r : reservas) {
                if ("Confirmada".equals(r.getEstado()) || "Completada".equals(r.getEstado())) {
                    String mes = r.getCheckIn().format(DateTimeFormatter.ofPattern("MM/yyyy"));
                    DatosMes datosMes = datosPorMes.getOrDefault(mes, new DatosMes(mes));
                    datosMes.totalReservas++;
                    datosMes.ingresosTotales += r.getTotal();
                    datosPorMes.put(mes, datosMes);
                }
            }
            
            ArrayList<DatosMes> listaMeses = new ArrayList<>(datosPorMes.values());
            listaMeses.sort((a, b) -> {
                String[] partesA = a.mes.split("/");
                String[] partesB = b.mes.split("/");
                int añoA = Integer.parseInt(partesA[1]);
                int añoB = Integer.parseInt(partesB[1]);
                if (añoA != añoB) return añoB - añoA;
                int mesA = Integer.parseInt(partesA[0]);
                int mesB = Integer.parseInt(partesB[0]);
                return mesB - mesA;
            });
            
            for (DatosMes datosMes : listaMeses) {
                double promedio = datosMes.totalReservas > 0 ? datosMes.ingresosTotales / datosMes.totalReservas : 0;
                LocalDate ahora = LocalDate.now();
                String mesActual = ahora.format(DateTimeFormatter.ofPattern("MM/yyyy"));
                String estado = mesActual.equals(datosMes.mes) ? "En curso" : "Completado";
                
                datos.add(new String[]{
                    datosMes.mes,
                    String.valueOf(datosMes.totalReservas),
                    String.format("S/. %,.2f", datosMes.ingresosTotales),
                    String.format("S/. %,.2f", promedio),
                    estado
                });
            }
        } else if (reporteSeleccionado.equals("Reservas Pendientes")) {
            datos.add(new String[]{"ID Reserva", "Huésped", "Habitación", "Check-In", "Check-Out", "Estado"});
            ArrayList<Reserva> reservasPendientes = gestionReservas.obtenerPendientes();
            for (Reserva r : reservasPendientes) {
                datos.add(new String[]{
                    r.getIdReserva(),
                    r.getNombreHuesped(),
                    r.getNumeroHabitacion(),
                    r.obtenerCheckInFormateado(),
                    r.obtenerCheckOutFormateado(),
                    r.getEstado()
                });
            }
        }
        return datos;
    }
    
    public BorderPane getRoot() {
        return root;
    }
    
    private VBox crearCardReporte(IconoSimple icono, String titulo, String descripcion, String tipoReporte) {
        VBox card = new VBox(8);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(16));
        card.setPrefWidth(200);
        card.setStyle("-fx-background-color: #" + Constantes.COLOR_PANEL_FORMULARIO.toString().substring(2, 8) + 
                      "; -fx-border-color: #" + Constantes.COLOR_BORDE_CLARO.toString().substring(2, 8) + 
                      "; -fx-border-width: 1; -fx-border-radius: 2; -fx-background-radius: 2;");
        card.setCursor(javafx.scene.Cursor.HAND);
        
        Label lblTitulo = new Label(titulo);
        lblTitulo.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblTitulo.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        lblTitulo.setAlignment(Pos.CENTER);
        lblTitulo.setMaxWidth(Double.MAX_VALUE);
        
        Label lblDesc = new Label(descripcion);
        lblDesc.setFont(Constantes.FUENTE_PEQUENA);
        lblDesc.setTextFill(Constantes.COLOR_TEXTO_SECUNDARIO);
        lblDesc.setAlignment(Pos.CENTER);
        lblDesc.setMaxWidth(Double.MAX_VALUE);
        lblDesc.setWrapText(true);
        
        card.getChildren().addAll(icono, lblTitulo, lblDesc);
        
        // Acción al hacer click: actualizar la tabla
        card.setOnMouseClicked(_ -> {
            actualizarTabla(tipoReporte);
            // Actualizar estilo visual para mostrar selección
            actualizarEstiloCards(card, tipoReporte);
        });
        
        // Efecto hover (solo si no está seleccionada)
        card.setOnMouseEntered(_ -> {
            if (!reporteSeleccionado.equals(tipoReporte)) {
                card.setStyle("-fx-background-color: #F0F0F0; " +
                             "-fx-border-color: #" + Constantes.COLOR_BORDE_MEDIO.toString().substring(2, 8) + 
                             "; -fx-border-width: 1; -fx-border-radius: 2; -fx-background-radius: 2;");
            }
            // Si está seleccionada, mantener el estilo de selección
        });
        card.setOnMouseExited(_ -> {
            if (!reporteSeleccionado.equals(tipoReporte)) {
                // Volver al estilo normal si no está seleccionada
                card.setStyle("-fx-background-color: #" + Constantes.COLOR_PANEL_FORMULARIO.toString().substring(2, 8) + 
                             "; -fx-border-color: #" + Constantes.COLOR_BORDE_CLARO.toString().substring(2, 8) + 
                             "; -fx-border-width: 1; -fx-border-radius: 2; -fx-background-radius: 2;");
            } else {
                // Mantener el estilo de selección si está seleccionada
                card.setStyle("-fx-background-color: #E8F4FD; " +
                             "-fx-border-color: #0078D4; " +
                             "-fx-border-width: 2; -fx-border-radius: 2; -fx-background-radius: 2;");
            }
        });
        
        return card;
    }
    
    private void actualizarEstiloCards(VBox cardSeleccionado, String tipoReporte) {
        // Resetear el estilo de todas las cards al estilo normal
        for (java.util.Map.Entry<String, VBox> entry : cardsReporte.entrySet()) {
            VBox card = entry.getValue();
            if (!entry.getKey().equals(tipoReporte)) {
                // Estilo normal (no seleccionada)
                card.setStyle("-fx-background-color: #" + Constantes.COLOR_PANEL_FORMULARIO.toString().substring(2, 8) + 
                             "; -fx-border-color: #" + Constantes.COLOR_BORDE_CLARO.toString().substring(2, 8) + 
                             "; -fx-border-width: 1; -fx-border-radius: 2; -fx-background-radius: 2;");
            }
        }
        
        // Aplicar estilo de selección solo a la card seleccionada
        cardSeleccionado.setStyle("-fx-background-color: #E8F4FD; " +
                                 "-fx-border-color: #0078D4; " +
                                 "-fx-border-width: 2; -fx-border-radius: 2; -fx-background-radius: 2;");
    }
    
    private IconoSimple crearIconoTrendingUp(int tamano, Color color) {
        return IconoSimple.crearIconoTrendingUp(tamano, color);
    }
    
    private IconoSimple crearIconoCircleDot(int tamano, Color color) {
        return IconoSimple.crearIconoCircleDot(tamano, color);
    }
    
    private IconoSimple crearIconoFileText(int tamano, Color color) {
        return IconoSimple.crearIconoFileText(tamano, color);
    }
    
    public PanelNavegacion getPanelNavegacion() {
        return panelNavegacion;
    }
}
