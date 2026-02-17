package ventanas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import componentes.BarraEstado;
import componentes.BarraTitulo;
import componentes.CampoFormulario;
import componentes.PanelNavegacion;
import componentes.TablaDatos;
import utilidades.Constantes;
import clases.GestionHuespedes;
import clases.Huesped;

/**
 * Ventana para la gestión de huéspedes
 */
public class HuespedesWindow {
    
    private BorderPane root;
    private BarraTitulo barraTitulo;
    private PanelNavegacion panelNavegacion;
    private BarraEstado barraEstado;
    private CampoFormulario campoDNI;
    private CampoFormulario campoNombre;
    private CampoFormulario campoApellidos;
    private CampoFormulario campoTelefono;
    private CampoFormulario campoEmail;
    private CampoFormulario campoDireccion;
    private DatePicker datePickerFechaNac;
    private ComboBox<String> cmbTipoDoc;
    private ComboBox<String> cmbGenero;
    private ComboBox<String> cmbNacionalidad;
    private CheckBox chkVIP;
    private CheckBox chkNewsletter;
    private TablaDatos tablaHuespedes;
    private GestionHuespedes gestionHuespedes;
    
    public HuespedesWindow() {
        // Obtener instancia compartida de gestión de huéspedes
        gestionHuespedes = utilidades.GestorDatos.getInstancia().getGestionHuespedes();
        
        root = new BorderPane();
        root.setStyle("-fx-background-color: #" + Constantes.COLOR_FONDO.toString().substring(2, 8) + ";");
        
        // Barra de título
        barraTitulo = new BarraTitulo("Sistema de Reserva de Hoteles - Gestión de Huéspedes");
        root.setTop(barraTitulo);
        
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
        
        root.setCenter(panelPrincipal);
        
        // Barra de estado
        barraEstado = new BarraEstado();
        actualizarBarraEstado();
        root.setBottom(barraEstado);
    }
    
    private void actualizarBarraEstado() {
        int cantidad = gestionHuespedes.obtenerCantidad();
        barraEstado.setEstado(cantidad + " huésped" + (cantidad != 1 ? "es" : "") + " registrado" + (cantidad != 1 ? "s" : ""));
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
        utilidades.EstiloComboBox.aplicarEstilo(cmbGenero, "Seleccionar género");
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
        utilidades.EstiloComboBox.aplicarEstilo(cmbNacionalidad, "Seleccionar nacionalidad");
        panelNacionalidad.getChildren().addAll(lblNacionalidad, cmbNacionalidad);
        
        // DatePicker Fecha de Nacimiento
        HBox panelFechaNac = new HBox(12);
        panelFechaNac.setAlignment(Pos.CENTER_LEFT);
        Label lblFechaNac = new Label("Fecha Nac:");
        lblFechaNac.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblFechaNac.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        datePickerFechaNac = new DatePicker();
        datePickerFechaNac.setPrefWidth(150);
        datePickerFechaNac.setPrefHeight(24);
        datePickerFechaNac.setMinHeight(24);
        datePickerFechaNac.setMaxHeight(24);
        datePickerFechaNac.setConverter(new utilidades.DateConverter());
        datePickerFechaNac.getStyleClass().add("aed-date-picker");
        datePickerFechaNac.setPromptText("dd/MM/yyyy");
        panelFechaNac.getChildren().addAll(lblFechaNac, datePickerFechaNac);
        
        fila3.getChildren().addAll(panelGenero, panelNacionalidad, panelFechaNac);
        
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
        utilidades.EstiloComboBox.aplicarEstilo(cmbTipoDoc, "Seleccionar tipo");
        panelTipoDoc.getChildren().addAll(lblTipoDoc, cmbTipoDoc);
        
        fila4.getChildren().addAll(campoDireccion, panelTipoDoc);
        
        // Fila 5 - Checkboxes
        HBox fila5 = new HBox(24);
        fila5.setAlignment(Pos.CENTER_LEFT);
        chkVIP = new CheckBox("Cliente VIP");
        chkVIP.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        chkNewsletter = new CheckBox("Recibir promociones por email");
        chkNewsletter.setFont(Constantes.FUENTE_NORMAL);
        utilidades.EstiloCheckBox.aplicarEstilo(chkVIP);
        utilidades.EstiloCheckBox.aplicarEstilo(chkNewsletter);
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
        btnAgregar.setOnAction(_ -> {
            // Validar campos
            if (campoDNI.getValor().isEmpty() || campoNombre.getValor().isEmpty() || campoApellidos.getValor().isEmpty()) {
                ventanas.dialogos.DialogoMensaje dialogoError = 
                    new ventanas.dialogos.DialogoMensaje("Error de Validación",
                        "Por favor complete todos los campos obligatorios (DNI, Nombres, Apellidos).",
                        ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                dialogoError.mostrar();
            } else {
                // Verificar si ya existe un huésped con ese documento
                if (gestionHuespedes.buscarPorDocumento(campoDNI.getValor()) != null) {
                    ventanas.dialogos.DialogoMensaje dialogoError = 
                        new ventanas.dialogos.DialogoMensaje("Error de Validación",
                            "Ya existe un huésped registrado con el documento: " + campoDNI.getValor(),
                            ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                    dialogoError.mostrar();
                    return;
                }
                
                // Crear nuevo huésped
                Huesped nuevoHuesped = new Huesped();
                nuevoHuesped.setDocumento(campoDNI.getValor());
                nuevoHuesped.setTipoDocumento(cmbTipoDoc.getValue() != null ? cmbTipoDoc.getValue() : "DNI");
                nuevoHuesped.setNombre(campoNombre.getValor());
                nuevoHuesped.setApellidos(campoApellidos.getValor());
                nuevoHuesped.setTelefono(campoTelefono.getValor());
                nuevoHuesped.setEmail(campoEmail.getValor());
                nuevoHuesped.setDireccion(campoDireccion.getValor());
                // Convertir LocalDate a String (dd/MM/yyyy)
                LocalDate fechaNac = datePickerFechaNac.getValue();
                if (fechaNac != null) {
                    nuevoHuesped.setFechaNacimiento(fechaNac.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                } else {
                    nuevoHuesped.setFechaNacimiento("");
                }
                nuevoHuesped.setGenero(cmbGenero.getValue() != null ? cmbGenero.getValue() : "");
                nuevoHuesped.setNacionalidad(cmbNacionalidad.getValue() != null ? cmbNacionalidad.getValue() : "");
                nuevoHuesped.setVip(chkVIP.isSelected());
                nuevoHuesped.setNewsletter(chkNewsletter.isSelected());
                
                // Agregar a la gestión
                if (gestionHuespedes.agregar(nuevoHuesped)) {
                    // Verificar que el ID se haya asignado correctamente
                    String idAsignado = nuevoHuesped.getId();
                    
                    // Actualizar tabla
                    cargarDatosEnTabla();
                    
                    // Verificar que el huésped se encuentre en la gestión
                    Huesped verificarHuesped = gestionHuespedes.buscarPorId(idAsignado);
                    if (verificarHuesped == null) {
                        ventanas.dialogos.DialogoMensaje dialogoError = 
                            new ventanas.dialogos.DialogoMensaje("Error",
                                "El huésped se agregó pero no se pudo verificar. ID: " + idAsignado,
                                ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                        dialogoError.mostrar();
                        return;
                    }
                    
                    // Mostrar diálogo de éxito
                    ventanas.dialogos.DialogoMensaje dialogoExito = 
                        new ventanas.dialogos.DialogoMensaje("Operación Exitosa",
                            "El huésped ha sido registrado exitosamente. ID: " + idAsignado,
                            ventanas.dialogos.DialogoMensaje.TipoMensaje.EXITO);
                    dialogoExito.mostrar();
                    
                    // Actualizar barra de estado
                    actualizarBarraEstado();
                    
                    // Limpiar formulario
                    limpiarFormulario();
                } else {
                    ventanas.dialogos.DialogoMensaje dialogoError = 
                        new ventanas.dialogos.DialogoMensaje("Error",
                            "No se pudo registrar el huésped. Intente nuevamente.",
                            ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                    dialogoError.mostrar();
                }
            }
        });
        
        Button btnLimpiar = new Button("Limpiar");
        btnLimpiar.setFont(Constantes.FUENTE_NORMAL);
        btnLimpiar.setStyle("-fx-background-color: #" + Constantes.COLOR_SECUNDARIO.toString().substring(2, 8) + 
                          "; -fx-text-fill: #" + Constantes.COLOR_TEXTO_PRINCIPAL.toString().substring(2, 8) + 
                          "; -fx-border-color: #" + Constantes.COLOR_BORDE_MEDIO.toString().substring(2, 8) + 
                          "; -fx-border-width: 1; -fx-padding: 6 16 6 16;");
        btnLimpiar.setOnAction(_ -> limpiarFormulario());
        
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
        
        // Configurar callbacks para los botones de acción
        // Ahora los callbacks reciben el ID directamente, no el índice de fila
        tablaHuespedes.setOnVer(id -> {
            if (id == null || id.isEmpty()) {
                return;
            }
            Huesped huesped = gestionHuespedes.buscarPorId(id);
            if (huesped != null) {
                ventanas.dialogos.DialogoDetalleHuesped dialogo = 
                    new ventanas.dialogos.DialogoDetalleHuesped(
                        huesped.getId(),
                        huesped.obtenerNombreCompleto(),
                        huesped.getDocumento(),
                        huesped.getTelefono(),
                        huesped.getEmail(),
                        huesped.getNacionalidad(),
                        huesped.isVip());
                dialogo.mostrar();
            }
        });
        
        tablaHuespedes.setOnEditar(id -> {
            if (id == null || id.isEmpty()) {
                ventanas.dialogos.DialogoMensaje dialogoError = 
                    new ventanas.dialogos.DialogoMensaje("Error",
                        "No se pudo identificar el huésped seleccionado.",
                        ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                dialogoError.mostrar();
                return;
            }
            
            Huesped huesped = gestionHuespedes.buscarPorId(id);
            if (huesped != null) {
                ventanas.dialogos.DialogoEditarHuesped dialogo = 
                    new ventanas.dialogos.DialogoEditarHuesped(
                        huesped.getDocumento(),
                        huesped.getNombre(),
                        huesped.getApellidos(),
                        huesped.getTelefono(),
                        huesped.getEmail(),
                        huesped.getNacionalidad(),
                        huesped.isVip());
                dialogo.mostrar();
                // Recargar tabla después de editar (si el diálogo actualiza)
                cargarDatosEnTabla();
            } else {
                ventanas.dialogos.DialogoMensaje dialogoError = 
                    new ventanas.dialogos.DialogoMensaje("Error",
                        "No se encontró el huésped con ID: " + id,
                        ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                dialogoError.mostrar();
            }
        });
        
        tablaHuespedes.setOnEliminar(id -> {
            if (id == null || id.isEmpty()) {
                return;
            }
            Huesped huesped = gestionHuespedes.buscarPorId(id);
            if (huesped != null) {
                ventanas.dialogos.DialogoConfirmacion dialogo = 
                    new ventanas.dialogos.DialogoConfirmacion(
                        "Confirmar Eliminación",
                        "¿Está seguro que desea eliminar al huésped?",
                        huesped.obtenerNombreCompleto(),
                        "alert-triangle",
                        true // esEliminacion
                    );
                dialogo.mostrar();
                if (dialogo.isConfirmado()) {
                    if (gestionHuespedes.eliminar(id)) {
                        cargarDatosEnTabla();
                        actualizarBarraEstado();
                        ventanas.dialogos.DialogoMensaje dialogoExito = 
                            new ventanas.dialogos.DialogoMensaje("Operación Exitosa",
                                "El huésped ha sido eliminado correctamente.",
                                ventanas.dialogos.DialogoMensaje.TipoMensaje.EXITO);
                        dialogoExito.mostrar();
                    }
                }
            }
        });
        
        // Cargar datos iniciales
        cargarDatosEnTabla();
        
        panel.getChildren().addAll(lblTituloTabla, tablaHuespedes);
        
        return panel;
    }
    
    public BorderPane getRoot() {
        return root;
    }
    
    public PanelNavegacion getPanelNavegacion() {
        return panelNavegacion;
    }
    
    private void limpiarFormulario() {
        campoDNI.setValor("");
        campoNombre.setValor("");
        campoApellidos.setValor("");
        campoTelefono.setValor("");
        campoEmail.setValor("");
        campoDireccion.setValor("");
        datePickerFechaNac.setValue(null);
        cmbTipoDoc.getSelectionModel().clearSelection();
        cmbGenero.getSelectionModel().clearSelection();
        cmbNacionalidad.getSelectionModel().clearSelection();
        chkVIP.setSelected(false);
        chkNewsletter.setSelected(false);
    }
    
    private void cargarDatosEnTabla() {
        // Limpiar tabla
        tablaHuespedes.limpiar();
        
        // Cargar datos desde la gestión - usar array para mantener el orden
        Huesped[] huespedes = gestionHuespedes.obtenerTodosArray();
        for (Huesped h : huespedes) {
            if (h != null && h.getId() != null) {
                tablaHuespedes.agregarFila(new String[]{
                    h.getId(),
                    h.obtenerNombreCompleto(),
                    h.getDocumento() != null ? h.getDocumento() : "",
                    h.getTelefono() != null ? h.getTelefono() : "",
                    h.obtenerVipTexto(),
                    ""
                });
            }
        }
    }
    
    /**
     * Notifica a otras ventanas que se agregó un nuevo huésped
     * Esto permite actualizar los ComboBoxes en otras ventanas
     */
    private void notificarNuevoHuesped() {
        // Obtener el gestor de ventanas desde el panel de navegación
        // y actualizar el ComboBox de huéspedes en ReservasWindow
        try {
            // Usar reflexión o un patrón de observador sería ideal,
            // pero por simplicidad, actualizamos directamente a través del GestorVentanas
            // Esto se manejará cuando se muestre la ventana de reservas
        } catch (Exception e) {
            // Si falla, no es crítico
        }
    }
}
