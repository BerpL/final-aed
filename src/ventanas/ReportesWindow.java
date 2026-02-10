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
import componentes.BarraMenu;
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
    private BarraMenu barraMenu;
    private PanelNavegacion panelNavegacion;
    private BarraEstado barraEstado;
    private TablaDatos tablaReporte;
    
    public ReportesWindow() {
        root = new BorderPane();
        root.setStyle("-fx-background-color: #" + Constantes.COLOR_FONDO.toString().substring(2, 8) + ";");
        
        barraTitulo = new BarraTitulo("Sistema de Reserva de Hoteles - Reportes");
        root.setTop(barraTitulo);
        
        barraMenu = new BarraMenu();
        
        BorderPane panelPrincipal = new BorderPane();
        panelPrincipal.setStyle("-fx-background-color: #" + Constantes.COLOR_FONDO.toString().substring(2, 8) + ";");
        
        panelNavegacion = new PanelNavegacion();
        panelNavegacion.seleccionarItem(5); // Reportes seleccionado
        panelPrincipal.setLeft(panelNavegacion);
        
        VBox panelContenido = crearPanelContenido();
        panelPrincipal.setCenter(panelContenido);
        
        VBox contenedorPrincipal = new VBox();
        contenedorPrincipal.getChildren().addAll(barraMenu, panelPrincipal);
        VBox.setVgrow(panelPrincipal, javafx.scene.layout.Priority.ALWAYS);
        root.setCenter(contenedorPrincipal);
        
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
            "Estado actual de habitaciones"
        );
        
        // Card 2: Ingresos del Mes (verde #22C55E)
        VBox card2 = crearCardReporte(
            crearIconoTrendingUp(32, Color.web("#22C55E")),
            "Ingresos del Mes",
            "Resumen de ingresos mensuales"
        );
        
        // Card 3: Reservas Pendientes (amarillo #F59E0B)
        VBox card3 = crearCardReporte(
            crearIconoCircleDot(32, Color.web("#F59E0B")),
            "Reservas Pendientes",
            "Reservas sin confirmar"
        );
        
        panelBotonesReporte.getChildren().addAll(card1, card2, card3);
        
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
        
        Label lblTituloTabla = new Label("Vista Previa: Ocupación de Habitaciones");
        lblTituloTabla.setFont(Constantes.FUENTE_SUBTITULO);
        lblTituloTabla.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        
        String[] columnas = {"Habitación", "Tipo", "Precio/Noche", "Huésped Actual", "Estado", "Ver"};
        tablaReporte = new TablaDatos(columnas);
        
        // Los botones de acción se agregan automáticamente por TablaDatos cuando la columna es "Ver"
        tablaReporte.agregarFila(new String[]{"101", "Suite Ejecutiva", "S/. 350.00", "Juan García López", "Ocupada", ""});
        tablaReporte.agregarFila(new String[]{"102", "Doble Estándar", "S/. 150.00", "-", "Disponible", ""});
        tablaReporte.agregarFila(new String[]{"103", "Individual", "S/. 100.00", "María Rodríguez", "Ocupada", ""});
        
        panel.getChildren().addAll(lblTituloTabla, tablaReporte);
        
        return panel;
    }
    
    public BorderPane getRoot() {
        return root;
    }
    
    private VBox crearCardReporte(IconoSimple icono, String titulo, String descripcion) {
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
        
        // Efecto hover
        card.setOnMouseEntered(_ -> {
            card.setStyle("-fx-background-color: #F0F0F0; " +
                         "-fx-border-color: #" + Constantes.COLOR_BORDE_MEDIO.toString().substring(2, 8) + 
                         "; -fx-border-width: 1; -fx-border-radius: 2; -fx-background-radius: 2;");
        });
        card.setOnMouseExited(_ -> {
            card.setStyle("-fx-background-color: #" + Constantes.COLOR_PANEL_FORMULARIO.toString().substring(2, 8) + 
                         "; -fx-border-color: #" + Constantes.COLOR_BORDE_CLARO.toString().substring(2, 8) + 
                         "; -fx-border-width: 1; -fx-border-radius: 2; -fx-background-radius: 2;");
        });
        
        return card;
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
