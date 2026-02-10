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
    private java.util.Map<String, VBox> cardsReporte = new java.util.HashMap<>(); // Mapa para guardar las cards por tipo de reporte
    
    public ReportesWindow() {
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
        
        // Crear nueva tabla según el tipo de reporte
        if (tipoReporte.equals("Ocupación de Habitaciones")) {
            String[] columnas = {"Habitación", "Tipo", "Precio/Noche", "Huésped Actual", "Estado"};
            tablaReporte = new TablaDatos(columnas);
            tablaReporte.agregarFila(new String[]{"101", "Suite Ejecutiva", "S/. 350.00", "Juan García López", "Ocupada"});
            tablaReporte.agregarFila(new String[]{"102", "Doble Estándar", "S/. 150.00", "-", "Disponible"});
            tablaReporte.agregarFila(new String[]{"103", "Individual", "S/. 100.00", "María Rodríguez", "Ocupada"});
            tablaReporte.agregarFila(new String[]{"201", "Suite Presidencial", "S/. 500.00", "Carlos Mendoza", "Ocupada"});
            tablaReporte.agregarFila(new String[]{"202", "Doble Estándar", "S/. 150.00", "-", "Disponible"});
        } else if (tipoReporte.equals("Ingresos del Mes")) {
            String[] columnas = {"Mes", "Total Reservas", "Ingresos Totales", "Promedio por Reserva", "Estado"};
            tablaReporte = new TablaDatos(columnas);
            tablaReporte.agregarFila(new String[]{"Enero 2024", "45", "S/. 67,500.00", "S/. 1,500.00", "Completado"});
            tablaReporte.agregarFila(new String[]{"Febrero 2024", "52", "S/. 78,000.00", "S/. 1,500.00", "Completado"});
            tablaReporte.agregarFila(new String[]{"Marzo 2024", "38", "S/. 57,000.00", "S/. 1,500.00", "Completado"});
            tablaReporte.agregarFila(new String[]{"Abril 2024", "41", "S/. 61,500.00", "S/. 1,500.00", "En curso"});
        } else if (tipoReporte.equals("Reservas Pendientes")) {
            String[] columnas = {"ID Reserva", "Huésped", "Habitación", "Check-In", "Check-Out", "Estado"};
            tablaReporte = new TablaDatos(columnas);
            tablaReporte.agregarFila(new String[]{"RES-001", "Ana Martínez", "101", "15/04/2024", "18/04/2024", "Pendiente"});
            tablaReporte.agregarFila(new String[]{"RES-002", "Luis Sánchez", "205", "20/04/2024", "25/04/2024", "Pendiente"});
            tablaReporte.agregarFila(new String[]{"RES-003", "Carmen López", "103", "22/04/2024", "24/04/2024", "Pendiente"});
        }
        
        VBox.setVgrow(tablaReporte, javafx.scene.layout.Priority.ALWAYS);
        panelTablaContainer.getChildren().add(tablaReporte);
    }
    
    private java.util.List<String[]> obtenerDatosReporte() {
        java.util.List<String[]> datos = new java.util.ArrayList<>();
        // Obtener los datos actuales de la tabla
        // Por ahora retornamos datos de ejemplo según el reporte seleccionado
        if (reporteSeleccionado.equals("Ocupación de Habitaciones")) {
            datos.add(new String[]{"Habitación", "Tipo", "Precio/Noche", "Huésped Actual", "Estado"});
            datos.add(new String[]{"101", "Suite Ejecutiva", "S/. 350.00", "Juan García López", "Ocupada"});
            datos.add(new String[]{"102", "Doble Estándar", "S/. 150.00", "-", "Disponible"});
            datos.add(new String[]{"103", "Individual", "S/. 100.00", "María Rodríguez", "Ocupada"});
        } else if (reporteSeleccionado.equals("Ingresos del Mes")) {
            datos.add(new String[]{"Mes", "Total Reservas", "Ingresos Totales", "Promedio por Reserva", "Estado"});
            datos.add(new String[]{"Enero 2024", "45", "S/. 67,500.00", "S/. 1,500.00", "Completado"});
            datos.add(new String[]{"Febrero 2024", "52", "S/. 78,000.00", "S/. 1,500.00", "Completado"});
            datos.add(new String[]{"Marzo 2024", "38", "S/. 57,000.00", "S/. 1,500.00", "Completado"});
        } else if (reporteSeleccionado.equals("Reservas Pendientes")) {
            datos.add(new String[]{"ID Reserva", "Huésped", "Habitación", "Check-In", "Check-Out", "Estado"});
            datos.add(new String[]{"RES-001", "Ana Martínez", "101", "15/04/2024", "18/04/2024", "Pendiente"});
            datos.add(new String[]{"RES-002", "Luis Sánchez", "205", "20/04/2024", "25/04/2024", "Pendiente"});
            datos.add(new String[]{"RES-003", "Carmen López", "103", "22/04/2024", "24/04/2024", "Pendiente"});
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
