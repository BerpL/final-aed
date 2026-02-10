package ventanas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import componentes.BarraEstado;
import componentes.BarraMenu;
import componentes.BarraTitulo;
import componentes.CampoFormulario;
import componentes.PanelNavegacion;
import componentes.TablaDatos;
import utilidades.Constantes;

/**
 * Ventana para la gestión de tipos de habitación
 */
public class TiposHabitacionWindow {
    
    private BorderPane root;
    private BarraTitulo barraTitulo;
    private BarraMenu barraMenu;
    private PanelNavegacion panelNavegacion;
    private BarraEstado barraEstado;
    private CampoFormulario campoCodigo;
    private CampoFormulario campoNombre;
    private CampoFormulario campoPrecio;
    private CampoFormulario campoArea;
    private CheckBox chkWifi;
    private CheckBox chkTV;
    private CheckBox chkAC;
    private CheckBox chkMinibar;
    private CheckBox chkJacuzzi;
    private TablaDatos tablaTipos;
    
    public TiposHabitacionWindow() {
        root = new BorderPane();
        root.setStyle("-fx-background-color: #" + Constantes.COLOR_FONDO.toString().substring(2, 8) + ";");
        
        barraTitulo = new BarraTitulo("Sistema de Reserva de Hoteles - Tipos de Habitación");
        root.setTop(barraTitulo);
        
        barraMenu = new BarraMenu();
        
        BorderPane panelPrincipal = new BorderPane();
        panelPrincipal.setStyle("-fx-background-color: #" + Constantes.COLOR_FONDO.toString().substring(2, 8) + ";");
        
        panelNavegacion = new PanelNavegacion();
        panelNavegacion.seleccionarItem(3); // Tipos Habitación seleccionado
        panelPrincipal.setLeft(panelNavegacion);
        
        VBox panelContenido = crearPanelContenido();
        panelPrincipal.setCenter(panelContenido);
        
        VBox contenedorPrincipal = new VBox();
        contenedorPrincipal.getChildren().addAll(barraMenu, panelPrincipal);
        VBox.setVgrow(panelPrincipal, javafx.scene.layout.Priority.ALWAYS);
        root.setCenter(contenedorPrincipal);
        
        barraEstado = new BarraEstado();
        barraEstado.setEstado("5 tipos registrados");
        root.setBottom(barraEstado);
    }
    
    private VBox crearPanelContenido() {
        VBox panel = new VBox(16);
        panel.setPadding(new Insets(16));
        panel.setStyle("-fx-background-color: #" + Constantes.COLOR_BLANCO.toString().substring(2, 8) + ";");
        
        HBox panelTitulo = new HBox();
        panelTitulo.setAlignment(Pos.CENTER_LEFT);
        panelTitulo.setPrefHeight(40);
        panelTitulo.setStyle("-fx-border-color: #" + Constantes.COLOR_BORDE_CLARO.toString().substring(2, 8) + 
                           "; -fx-border-width: 0 0 1 0;");
        
        Label lblTitulo = new Label("Gestión de Tipos de Habitación");
        lblTitulo.setFont(Constantes.FUENTE_TITULO);
        lblTitulo.setTextFill(Constantes.COLOR_PRIMARIO);
        panelTitulo.getChildren().add(lblTitulo);
        
        VBox panelFormulario = crearPanelFormulario();
        VBox panelTabla = crearPanelTabla();
        VBox.setVgrow(panelTabla, javafx.scene.layout.Priority.ALWAYS);
        
        panel.getChildren().addAll(panelTitulo, panelFormulario, panelTabla);
        
        return panel;
    }
    
    private VBox crearPanelFormulario() {
        VBox panel = new VBox(12);
        panel.setPadding(new Insets(16));
        panel.setStyle("-fx-background-color: #" + Constantes.COLOR_PANEL_FORMULARIO.toString().substring(2, 8) + 
                      "; -fx-border-color: #" + Constantes.COLOR_BORDE_CLARO.toString().substring(2, 8) + 
                      "; -fx-border-width: 1;");
        
        Label lblTituloForm = new Label("Datos del Tipo de Habitación");
        lblTituloForm.setFont(Constantes.FUENTE_SUBTITULO);
        lblTituloForm.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        
        HBox fila1 = new HBox(24);
        fila1.setAlignment(Pos.CENTER_LEFT);
        campoCodigo = new CampoFormulario("Código", 80);
        campoNombre = new CampoFormulario("Nombre del Tipo", 200);
        campoPrecio = new CampoFormulario("Precio Base", 120);
        fila1.getChildren().addAll(campoCodigo, campoNombre, campoPrecio);
        
        HBox fila2 = new HBox(24);
        fila2.setAlignment(Pos.CENTER_LEFT);
        
        HBox panelCapacidad = new HBox(12);
        panelCapacidad.setAlignment(Pos.CENTER_LEFT);
        Label lblCapacidad = new Label("Capacidad:");
        lblCapacidad.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblCapacidad.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        ComboBox<String> cmbCapacidad = new ComboBox<>();
        cmbCapacidad.getItems().addAll("1", "2", "3", "4", "5", "6");
        cmbCapacidad.setPrefWidth(80);
        cmbCapacidad.setPrefHeight(24);
        cmbCapacidad.setMinHeight(24);
        cmbCapacidad.setMaxHeight(24);
        panelCapacidad.getChildren().addAll(lblCapacidad, cmbCapacidad);
        
        HBox panelCamas = new HBox(12);
        panelCamas.setAlignment(Pos.CENTER_LEFT);
        Label lblCamas = new Label("Tipo de Camas:");
        lblCamas.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblCamas.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        ComboBox<String> cmbCamas = new ComboBox<>();
        cmbCamas.getItems().addAll("Individual", "Doble", "Queen", "King");
        cmbCamas.setPrefWidth(150);
        cmbCamas.setPrefHeight(24);
        cmbCamas.setMinHeight(24);
        cmbCamas.setMaxHeight(24);
        panelCamas.getChildren().addAll(lblCamas, cmbCamas);
        
        campoArea = new CampoFormulario("Área (m²)", 80);
        fila2.getChildren().addAll(panelCapacidad, panelCamas, campoArea);
        
        HBox fila3 = new HBox(24);
        fila3.setAlignment(Pos.CENTER_LEFT);
        Label lblAmenidades = new Label("Amenidades Incluidas:");
        lblAmenidades.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblAmenidades.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        chkWifi = new CheckBox("WiFi");
        chkTV = new CheckBox("TV Cable");
        chkAC = new CheckBox("A/C");
        chkMinibar = new CheckBox("Minibar");
        chkJacuzzi = new CheckBox("Jacuzzi");
        chkWifi.setFont(Constantes.FUENTE_PEQUENA);
        chkTV.setFont(Constantes.FUENTE_PEQUENA);
        chkAC.setFont(Constantes.FUENTE_PEQUENA);
        chkMinibar.setFont(Constantes.FUENTE_PEQUENA);
        chkJacuzzi.setFont(Constantes.FUENTE_PEQUENA);
        fila3.getChildren().addAll(lblAmenidades, chkWifi, chkTV, chkAC, chkMinibar, chkJacuzzi);
        
        HBox panelBotones = new HBox(8);
        panelBotones.setAlignment(Pos.CENTER_LEFT);
        Button btnAgregar = new Button("Agregar");
        btnAgregar.setFont(Constantes.FUENTE_NORMAL);
        btnAgregar.setStyle("-fx-background-color: #" + Constantes.COLOR_PRIMARIO.toString().substring(2, 8) + 
                           "; -fx-text-fill: #" + Constantes.COLOR_TEXTO_BLANCO.toString().substring(2, 8) + 
                           "; -fx-border-color: #" + Constantes.COLOR_PRIMARIO_OSCURO.toString().substring(2, 8) + 
                           "; -fx-border-width: 1; -fx-padding: 6 16 6 16;");
        
        Button btnLimpiar = new Button("Limpiar");
        btnLimpiar.setFont(Constantes.FUENTE_NORMAL);
        btnLimpiar.setStyle("-fx-background-color: #" + Constantes.COLOR_SECUNDARIO.toString().substring(2, 8) + 
                          "; -fx-text-fill: #" + Constantes.COLOR_TEXTO_PRINCIPAL.toString().substring(2, 8) + 
                          "; -fx-border-color: #" + Constantes.COLOR_BORDE_MEDIO.toString().substring(2, 8) + 
                          "; -fx-border-width: 1; -fx-padding: 6 16 6 16;");
        
        panelBotones.getChildren().addAll(btnAgregar, btnLimpiar);
        
        panel.getChildren().addAll(lblTituloForm, fila1, fila2, fila3, panelBotones);
        
        return panel;
    }
    
    private VBox crearPanelTabla() {
        VBox panel = new VBox(8);
        panel.setPadding(new Insets(0));
        panel.setStyle("-fx-background-color: #" + Constantes.COLOR_BLANCO.toString().substring(2, 8) + ";");
        VBox.setVgrow(panel, javafx.scene.layout.Priority.ALWAYS);
        
        Label lblTituloTabla = new Label("Lista de Tipos de Habitación");
        lblTituloTabla.setFont(Constantes.FUENTE_SUBTITULO);
        lblTituloTabla.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        
        String[] columnas = {"Código", "Nombre del Tipo", "Precio Base", "Acciones"};
        tablaTipos = new TablaDatos(columnas);
        VBox.setVgrow(tablaTipos, javafx.scene.layout.Priority.ALWAYS);
        
        tablaTipos.agregarFila(new String[]{"SUITE-PRES", "Suite Presidencial", "S/. 500.00", ""});
        tablaTipos.agregarFila(new String[]{"SUITE", "Suite", "S/. 350.00", ""});
        tablaTipos.agregarFila(new String[]{"DOBLE", "Habitación Doble", "S/. 200.00", ""});
        
        panel.getChildren().addAll(lblTituloTabla, tablaTipos);
        
        return panel;
    }
    
    public BorderPane getRoot() {
        return root;
    }
    
    public PanelNavegacion getPanelNavegacion() {
        return panelNavegacion;
    }
}
