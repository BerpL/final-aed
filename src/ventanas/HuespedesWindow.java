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
 * Ventana para la gestión de huéspedes
 */
public class HuespedesWindow {
    
    private BorderPane root;
    private BarraTitulo barraTitulo;
    private BarraMenu barraMenu;
    private PanelNavegacion panelNavegacion;
    private BarraEstado barraEstado;
    private CampoFormulario campoDNI;
    private CampoFormulario campoNombre;
    private CampoFormulario campoApellidos;
    private CampoFormulario campoTelefono;
    private CampoFormulario campoEmail;
    private CampoFormulario campoDireccion;
    private CampoFormulario campoFechaNac;
    private ComboBox<String> cmbTipoDoc;
    private ComboBox<String> cmbGenero;
    private ComboBox<String> cmbNacionalidad;
    private CheckBox chkVIP;
    private CheckBox chkNewsletter;
    private TablaDatos tablaHuespedes;
    
    public HuespedesWindow() {
        root = new BorderPane();
        root.setStyle("-fx-background-color: #" + Constantes.COLOR_FONDO.toString().substring(2, 8) + ";");
        
        // Barra de título
        barraTitulo = new BarraTitulo("Sistema de Reserva de Hoteles - Gestión de Huéspedes");
        root.setTop(barraTitulo);
        
        // Barra de menú
        barraMenu = new BarraMenu();
        
        // Panel principal
        BorderPane panelPrincipal = new BorderPane();
        panelPrincipal.setStyle("-fx-background-color: #" + Constantes.COLOR_FONDO.toString().substring(2, 8) + ";");
        
        // Panel de navegación
        panelNavegacion = new PanelNavegacion();
        panelNavegacion.seleccionarItem(1); // Huéspedes seleccionado
        panelPrincipal.setLeft(panelNavegacion);
        
        // Panel de contenido
        VBox panelContenido = crearPanelContenido();
        panelPrincipal.setCenter(panelContenido);
        
        // Contenedor para menú y contenido principal
        VBox contenedorPrincipal = new VBox();
        contenedorPrincipal.getChildren().addAll(barraMenu, panelPrincipal);
        // Hacer que el panelPrincipal ocupe todo el espacio vertical disponible
        VBox.setVgrow(panelPrincipal, javafx.scene.layout.Priority.ALWAYS);
        root.setCenter(contenedorPrincipal);
        
        // Barra de estado
        barraEstado = new BarraEstado();
        barraEstado.setEstado("3 huéspedes registrados");
        root.setBottom(barraEstado);
    }
    
    private VBox crearPanelContenido() {
        VBox panel = new VBox(16);
        panel.setPadding(new Insets(16));
        panel.setStyle("-fx-background-color: #" + Constantes.COLOR_BLANCO.toString().substring(2, 8) + ";");
        
        // Título del panel
        HBox panelTitulo = new HBox();
        panelTitulo.setAlignment(Pos.CENTER_LEFT);
        panelTitulo.setPrefHeight(40);
        panelTitulo.setStyle("-fx-border-color: #" + Constantes.COLOR_BORDE_CLARO.toString().substring(2, 8) + 
                           "; -fx-border-width: 0 0 1 0;");
        
        Label lblTitulo = new Label("Gestión de Huéspedes");
        lblTitulo.setFont(Constantes.FUENTE_TITULO);
        lblTitulo.setTextFill(Constantes.COLOR_PRIMARIO);
        panelTitulo.getChildren().add(lblTitulo);
        
        // Panel de formulario
        VBox panelFormulario = crearPanelFormulario();
        
        // Panel de tabla - debe expandirse para ocupar el espacio restante
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
        
        Label lblTituloForm = new Label("Datos del Huésped");
        lblTituloForm.setFont(Constantes.FUENTE_SUBTITULO);
        lblTituloForm.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        
        // Fila 1: DNI, Nombres, Apellidos
        HBox fila1 = new HBox(24);
        fila1.setAlignment(Pos.CENTER_LEFT);
        campoDNI = new CampoFormulario("DNI", 120);
        campoNombre = new CampoFormulario("Nombres", 200);
        campoApellidos = new CampoFormulario("Apellidos", 200);
        fila1.getChildren().addAll(campoDNI, campoNombre, campoApellidos);
        
        // Fila 2: Teléfono, Email
        HBox fila2 = new HBox(24);
        fila2.setAlignment(Pos.CENTER_LEFT);
        campoTelefono = new CampoFormulario("Teléfono", 150);
        campoEmail = new CampoFormulario("Email", 250);
        fila2.getChildren().addAll(campoTelefono, campoEmail);
        
        // Fila 3: Género, Nacionalidad, Fecha Nac
        HBox fila3 = new HBox(24);
        fila3.setAlignment(Pos.CENTER_LEFT);
        
        HBox panelGenero = new HBox(12);
        panelGenero.setAlignment(Pos.CENTER_LEFT);
        Label lblGenero = new Label("Género:");
        lblGenero.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblGenero.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        cmbGenero = new ComboBox<>();
        cmbGenero.getItems().addAll("Masculino", "Femenino");
        cmbGenero.setPrefWidth(160);
        cmbGenero.setPrefHeight(24);
        cmbGenero.setMinHeight(24);
        cmbGenero.setMaxHeight(24);
        panelGenero.getChildren().addAll(lblGenero, cmbGenero);
        
        HBox panelNacionalidad = new HBox(12);
        panelNacionalidad.setAlignment(Pos.CENTER_LEFT);
        Label lblNacionalidad = new Label("Nacionalidad:");
        lblNacionalidad.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblNacionalidad.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        cmbNacionalidad = new ComboBox<>();
        cmbNacionalidad.getItems().addAll("Peruana", "Extranjera");
        cmbNacionalidad.setPrefWidth(160);
        cmbNacionalidad.setPrefHeight(24);
        cmbNacionalidad.setMinHeight(24);
        cmbNacionalidad.setMaxHeight(24);
        panelNacionalidad.getChildren().addAll(lblNacionalidad, cmbNacionalidad);
        
        campoFechaNac = new CampoFormulario("Fecha Nac", 120);
        fila3.getChildren().addAll(panelGenero, panelNacionalidad, campoFechaNac);
        
        // Fila 4: Dirección, Tipo Doc
        HBox fila4 = new HBox(24);
        fila4.setAlignment(Pos.CENTER_LEFT);
        campoDireccion = new CampoFormulario("Dirección", 350);
        
        HBox panelTipoDoc = new HBox(12);
        panelTipoDoc.setAlignment(Pos.CENTER_LEFT);
        Label lblTipoDoc = new Label("Tipo Doc:");
        lblTipoDoc.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblTipoDoc.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        cmbTipoDoc = new ComboBox<>();
        cmbTipoDoc.getItems().addAll("DNI", "Pasaporte", "Carné Extranjería");
        cmbTipoDoc.setPrefWidth(120);
        cmbTipoDoc.setPrefHeight(24);
        cmbTipoDoc.setMinHeight(24);
        cmbTipoDoc.setMaxHeight(24);
        panelTipoDoc.getChildren().addAll(lblTipoDoc, cmbTipoDoc);
        
        fila4.getChildren().addAll(campoDireccion, panelTipoDoc);
        
        // Fila 5 - Checkboxes
        HBox fila5 = new HBox(24);
        fila5.setAlignment(Pos.CENTER_LEFT);
        chkVIP = new CheckBox("Cliente VIP");
        chkVIP.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        chkNewsletter = new CheckBox("Recibir promociones por email");
        chkNewsletter.setFont(Constantes.FUENTE_NORMAL);
        fila5.getChildren().addAll(chkVIP, chkNewsletter);
        
        // Botones
        HBox panelBotones = new HBox(8);
        panelBotones.setAlignment(Pos.CENTER_LEFT);
        Button btnAgregar = new Button("Guardar");
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
        
        panel.getChildren().addAll(lblTituloForm, fila1, fila2, fila3, fila4, fila5, panelBotones);
        
        return panel;
    }
    
    private VBox crearPanelTabla() {
        VBox panel = new VBox(8);
        panel.setPadding(new Insets(0));
        panel.setStyle("-fx-background-color: #" + Constantes.COLOR_BLANCO.toString().substring(2, 8) + ";");
        VBox.setVgrow(panel, javafx.scene.layout.Priority.ALWAYS);
        
        Label lblTituloTabla = new Label("Lista de Huéspedes");
        lblTituloTabla.setFont(Constantes.FUENTE_SUBTITULO);
        lblTituloTabla.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        
        String[] columnas = {"ID", "Nombre Completo", "Documento", "Teléfono", "VIP", "Acciones"};
        tablaHuespedes = new TablaDatos(columnas);
        
        // Hacer que la tabla ocupe todo el espacio disponible
        VBox.setVgrow(tablaHuespedes, javafx.scene.layout.Priority.ALWAYS);
        
        // Datos de ejemplo
        tablaHuespedes.agregarFila(new String[]{"0001", "Juan Carlos Cepeda de la Cruz", "012345678", "987654321", "Si", ""});
        tablaHuespedes.agregarFila(new String[]{"0002", "Maria Rosa Rodriguez Perez", "987654321", "912345678", "No", ""});
        tablaHuespedes.agregarFila(new String[]{"0003", "Pedro Antonio Martinez", "456789012", "952789053", "Si", ""});
        
        panel.getChildren().addAll(lblTituloTabla, tablaHuespedes);
        
        return panel;
    }
    
    public BorderPane getRoot() {
        return root;
    }
    
    public PanelNavegacion getPanelNavegacion() {
        return panelNavegacion;
    }
}
