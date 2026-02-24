package ventanas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import componentes.BarraEstado;
import componentes.BarraTitulo;
import componentes.CampoFormulario;
import componentes.PanelNavegacion;
import componentes.TablaDatos;
import utilidades.Constantes;
import clases.GestionHabitaciones;
import clases.GestionTiposHabitacion;
import clases.Habitacion;

/**
 * Ventana para la gestión de habitaciones
 */
public class HabitacionesWindow {
    
    private BorderPane root;
    private BarraTitulo barraTitulo;
    private PanelNavegacion panelNavegacion;
    private BarraEstado barraEstado;
    private CampoFormulario campoNumero;
    private ComboBox<String> cmbTipo;
    private CampoFormulario campoPiso;
    private CampoFormulario campoPrecio;
    private ComboBox<String> cmbCapacidad;
    private ComboBox<String> cmbEstado;
    private TextArea txtDescripcion;
    private CheckBox chkWifi;
    private CheckBox chkTV;
    private CheckBox chkAC;
    private CheckBox chkMinibar;
    private CheckBox chkJacuzzi;
    private TablaDatos tablaHabitaciones;
    private GestionHabitaciones gestionHabitaciones;
    private GestionTiposHabitacion gestionTiposHabitacion;

    public HabitacionesWindow() {
        utilidades.GestorDatos gd = utilidades.GestorDatos.getInstancia();
        gestionHabitaciones = gd.getGestionHabitaciones();
        gestionTiposHabitacion = gd.getGestionTiposHabitacion();
        
        root = new BorderPane();
        root.setStyle("-fx-background-color: #" + Constantes.COLOR_FONDO.toString().substring(2, 8) + ";");
        
        barraTitulo = new BarraTitulo("Sistema de Reserva de Hoteles - Gestión de Habitaciones");
        root.setTop(barraTitulo);
        
        BorderPane panelPrincipal = new BorderPane();
        panelPrincipal.setStyle("-fx-background-color: #" + Constantes.COLOR_FONDO.toString().substring(2, 8) + ";");
        
        panelNavegacion = new PanelNavegacion();
        panelNavegacion.seleccionarItem(3); // Habitaciones seleccionado
        panelPrincipal.setLeft(panelNavegacion);
        
        VBox panelContenido = crearPanelContenido();
        panelPrincipal.setCenter(panelContenido);
        
        root.setCenter(panelPrincipal);
        
        barraEstado = new BarraEstado();
        actualizarBarraEstado();
        root.setBottom(barraEstado);
    }
    
    private void actualizarBarraEstado() {
        int cantidad = gestionHabitaciones.obtenerCantidad();
        barraEstado.setEstado(cantidad + " habitación" + (cantidad != 1 ? "es" : "") + " registrada" + (cantidad != 1 ? "s" : ""));
    }

    private static void aplicarFormatoSoloNumeros(CampoFormulario campo, int maxDigitos) {
        campo.getCampo().setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) return change;
            if (!newText.matches("[0-9]*") || newText.length() > maxDigitos) return null;
            return change;
        }));
    }

    private static void aplicarFormatoSoloNumerosDecimales(CampoFormulario campo) {
        campo.getCampo().setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) return change;
            if (!newText.matches("[0-9]*\\.?[0-9]*")) return null;
            return change;
        }));
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
        
        Label lblTitulo = new Label("Gestión de Habitaciones");
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
        
        Label lblTituloForm = new Label("Datos de la Habitación");
        lblTituloForm.setFont(Constantes.FUENTE_SUBTITULO);
        lblTituloForm.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        
        HBox fila1 = new HBox(24);
        fila1.setAlignment(Pos.CENTER_LEFT);
        campoNumero = new CampoFormulario("Número *", 150);
        aplicarFormatoSoloNumeros(campoNumero, 10);
        HBox panelTipo = new HBox(12);
        panelTipo.setAlignment(Pos.CENTER_LEFT);
        Label lblTipo = new Label("Tipo *:");
        lblTipo.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblTipo.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        cmbTipo = new ComboBox<>();
        cmbTipo.getItems().setAll(java.util.Arrays.asList(gestionTiposHabitacion.obtenerNombresTipos()));
        cmbTipo.setPrefWidth(200);
        cmbTipo.setPrefHeight(24);
        cmbTipo.setMinHeight(24);
        cmbTipo.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(cmbTipo, "Seleccionar tipo");
        panelTipo.getChildren().addAll(lblTipo, cmbTipo);
        campoPiso = new CampoFormulario("Piso *", 100);
        aplicarFormatoSoloNumeros(campoPiso, 3);
        fila1.getChildren().addAll(campoNumero, panelTipo, campoPiso);
        
        HBox fila2 = new HBox(24);
        fila2.setAlignment(Pos.CENTER_LEFT);
        campoPrecio = new CampoFormulario("Precio/Noche *", 120);
        aplicarFormatoSoloNumerosDecimales(campoPrecio);
        
        HBox panelCapacidad = new HBox(12);
        panelCapacidad.setAlignment(Pos.CENTER_LEFT);
        Label lblCapacidad = new Label("Capacidad *:");
        lblCapacidad.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblCapacidad.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        cmbCapacidad = new ComboBox<>();
        cmbCapacidad.getItems().addAll("1", "2", "3", "4", "5", "6");
        cmbCapacidad.setPrefWidth(60);
        cmbCapacidad.setPrefHeight(24);
        cmbCapacidad.setMinHeight(24);
        cmbCapacidad.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(cmbCapacidad, "Seleccionar");
        panelCapacidad.getChildren().addAll(lblCapacidad, cmbCapacidad);
        
        HBox panelEstado = new HBox(12);
        panelEstado.setAlignment(Pos.CENTER_LEFT);
        Label lblEstado = new Label("Estado *:");
        lblEstado.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblEstado.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        cmbEstado = new ComboBox<>();
        cmbEstado.getItems().addAll("Disponible", "Ocupada", "Mantenimiento");
        cmbEstado.setPrefWidth(140);
        cmbEstado.setPrefHeight(24);
        cmbEstado.setMinHeight(24);
        cmbEstado.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(cmbEstado, "Seleccionar estado");
        panelEstado.getChildren().addAll(lblEstado, cmbEstado);
        
        fila2.getChildren().addAll(campoPrecio, panelCapacidad, panelEstado);
        
        HBox fila3 = new HBox(24);
        fila3.setAlignment(Pos.CENTER_LEFT);
        
        VBox panelDescripcion = new VBox(8);
        Label lblDescripcion = new Label("Descripción:");
        lblDescripcion.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblDescripcion.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        txtDescripcion = new TextArea();
        txtDescripcion.setPrefRowCount(3);
        txtDescripcion.setPrefWidth(400);
        txtDescripcion.setPrefHeight(60);
        txtDescripcion.setWrapText(true); // Hacer wrap del texto automáticamente
        // Usar clase CSS personalizada para eliminar sombras y bordes azules
        txtDescripcion.getStyleClass().add("custom-textarea");
        // Aplicar estilos inline adicionales para asegurar que no haya sombras
        String colorBorde = Constantes.COLOR_BORDE_OSCURO.toString().substring(2, 8);
        String colorFondo = Constantes.COLOR_BLANCO.toString().substring(2, 8);
        txtDescripcion.setStyle(
            "-fx-background-color: #" + colorFondo + "; " +
            "-fx-border-color: #" + colorBorde + "; " +
            "-fx-border-width: 1; " +
            "-fx-border-radius: 0; " +
            "-fx-background-radius: 0; " +
            "-fx-padding: 4 8 4 8; " +
            "-fx-effect: null; " +
            "-fx-focus-color: transparent; " +
            "-fx-faint-focus-color: transparent; " +
            "-fx-background-insets: 0; " +
            "-fx-border-insets: 0; " +
            "-fx-control-inner-background: #" + colorFondo + "; " +
            "-fx-text-box-border: #" + colorBorde + ";");
        
        // Eliminar sombras y bordes azules del nodo interno después de que se renderice
        javafx.application.Platform.runLater(() -> {
            javafx.scene.Node content = txtDescripcion.lookup(".content");
            if (content != null) {
                content.setStyle("-fx-background-color: #" + colorFondo + "; -fx-effect: null;");
            }
            // También eliminar el efecto del TextArea mismo
            txtDescripcion.setStyle(txtDescripcion.getStyle() + " -fx-effect: null;");
        });
        
        panelDescripcion.getChildren().addAll(lblDescripcion, txtDescripcion);
        
        VBox panelAmenidades = new VBox(8);
        Label lblAmenidades = new Label("Amenidades:");
        lblAmenidades.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblAmenidades.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        
        VBox panelCheckboxes = new VBox(6);
        panelCheckboxes.setAlignment(Pos.TOP_LEFT);
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
        
        // Aplicar estilo personalizado a todos los checkboxes
        utilidades.EstiloCheckBox.aplicarEstilo(chkWifi);
        utilidades.EstiloCheckBox.aplicarEstilo(chkTV);
        utilidades.EstiloCheckBox.aplicarEstilo(chkAC);
        utilidades.EstiloCheckBox.aplicarEstilo(chkMinibar);
        utilidades.EstiloCheckBox.aplicarEstilo(chkJacuzzi);
        panelCheckboxes.getChildren().addAll(chkWifi, chkTV, chkAC, chkMinibar, chkJacuzzi);
        panelAmenidades.getChildren().addAll(lblAmenidades, panelCheckboxes);
        
        fila3.getChildren().addAll(panelDescripcion, panelAmenidades);
        
        HBox panelBotones = new HBox(8);
        panelBotones.setAlignment(Pos.CENTER_LEFT);
        Button btnAgregar = new Button("Guardar");
        btnAgregar.setFont(Constantes.FUENTE_NORMAL);
        btnAgregar.setStyle("-fx-background-color: #" + Constantes.COLOR_PRIMARIO.toString().substring(2, 8) + 
                           "; -fx-text-fill: #" + Constantes.COLOR_TEXTO_BLANCO.toString().substring(2, 8) + 
                           "; -fx-border-color: #" + Constantes.COLOR_PRIMARIO_OSCURO.toString().substring(2, 8) + 
                           "; -fx-border-width: 1; -fx-padding: 6 16 6 16;");
        btnAgregar.setOnAction(_ -> {
            String numeroVal = campoNumero.getValor() != null ? campoNumero.getValor().trim() : "";
            String tipoVal = cmbTipo.getValue() != null ? cmbTipo.getValue().trim() : "";
            String pisoVal = campoPiso.getValor() != null ? campoPiso.getValor().trim() : "";
            String precioStr = campoPrecio.getValor() != null ? campoPrecio.getValor().trim() : "";
            if (numeroVal.isEmpty() || tipoVal.isEmpty() || pisoVal.isEmpty() || precioStr.isEmpty() ||
                cmbCapacidad.getValue() == null || cmbEstado.getValue() == null) {
                ventanas.dialogos.DialogoMensaje dialogoError =
                    new ventanas.dialogos.DialogoMensaje("Error de Validación",
                        "Complete todos los campos obligatorios: Número, Tipo, Piso, Precio/Noche, Capacidad y Estado.",
                        ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                dialogoError.mostrar();
                return;
            }
            double precioVal;
            try {
                precioVal = Double.parseDouble(precioStr.replace(",", "."));
                if (precioVal <= 0) {
                    ventanas.dialogos.DialogoMensaje dialogoError =
                        new ventanas.dialogos.DialogoMensaje("Error de Validación",
                            "El precio por noche debe ser mayor a 0.",
                            ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                    dialogoError.mostrar();
                    return;
                }
            } catch (NumberFormatException e) {
                ventanas.dialogos.DialogoMensaje dialogoError =
                    new ventanas.dialogos.DialogoMensaje("Error de Validación",
                        "El precio debe ser un número válido.",
                        ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                dialogoError.mostrar();
                return;
            }
            if (gestionHabitaciones.buscarPorNumero(numeroVal) != null) {
                ventanas.dialogos.DialogoMensaje dialogoError =
                    new ventanas.dialogos.DialogoMensaje("Error de Validación",
                        "Ya existe una habitación con el número: " + numeroVal,
                        ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                dialogoError.mostrar();
                return;
            }
            Habitacion nuevaHabitacion = new Habitacion();
            nuevaHabitacion.setNumero(numeroVal);
            nuevaHabitacion.setTipo(tipoVal);
            nuevaHabitacion.setPiso(pisoVal);
            nuevaHabitacion.setPrecioNoche(precioVal);
            nuevaHabitacion.setCapacidad(Integer.parseInt(cmbCapacidad.getValue()));
            nuevaHabitacion.setEstado(cmbEstado.getValue());
            nuevaHabitacion.setDescripcion(txtDescripcion.getText() != null ? txtDescripcion.getText().trim() : "");
            nuevaHabitacion.setWifi(chkWifi.isSelected());
            nuevaHabitacion.setTv(chkTV.isSelected());
            nuevaHabitacion.setAc(chkAC.isSelected());
            nuevaHabitacion.setMinibar(chkMinibar.isSelected());
            nuevaHabitacion.setJacuzzi(chkJacuzzi.isSelected());
            if (gestionHabitaciones.agregar(nuevaHabitacion)) {
                cargarDatosEnTabla();
                ventanas.dialogos.DialogoMensaje dialogoExito =
                    new ventanas.dialogos.DialogoMensaje("Operación Exitosa",
                        "La habitación ha sido registrada exitosamente.",
                        ventanas.dialogos.DialogoMensaje.TipoMensaje.EXITO);
                dialogoExito.mostrar();
                actualizarBarraEstado();
                limpiarFormulario();
            } else {
                ventanas.dialogos.DialogoMensaje dialogoError =
                    new ventanas.dialogos.DialogoMensaje("Error",
                        "No se pudo registrar la habitación. Intente nuevamente.",
                        ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                dialogoError.mostrar();
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
        
        panel.getChildren().addAll(lblTituloForm, fila1, fila2, fila3, panelBotones);
        
        return panel;
    }
    
    private VBox crearPanelTabla() {
        VBox panel = new VBox(8);
        panel.setPadding(new Insets(0));
        panel.setStyle("-fx-background-color: #" + Constantes.COLOR_BLANCO.toString().substring(2, 8) + ";");
        VBox.setVgrow(panel, javafx.scene.layout.Priority.ALWAYS);
        
        Label lblTituloTabla = new Label("Lista de Habitaciones");
        lblTituloTabla.setFont(Constantes.FUENTE_SUBTITULO);
        lblTituloTabla.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        
        String[] columnas = {"Número", "Tipo", "Piso", "Precio/Noche", "Capacidad", "Estado", "Acciones"};
        tablaHabitaciones = new TablaDatos(columnas);
        VBox.setVgrow(tablaHabitaciones, javafx.scene.layout.Priority.ALWAYS);
        
        // Configurar callbacks para los botones de acción
        // Ahora los callbacks reciben el número de habitación directamente, no el índice
        tablaHabitaciones.setOnVer(numero -> {
            if (numero == null || numero.isEmpty()) {
                return;
            }
            Habitacion habitacion = gestionHabitaciones.buscarPorNumero(numero);
            if (habitacion != null) {
                ventanas.dialogos.DialogoDetalleHabitacion dialogo = 
                    new ventanas.dialogos.DialogoDetalleHabitacion(
                        habitacion.getNumero(),
                        habitacion.getTipo(),
                        habitacion.getPiso(),
                        String.valueOf(habitacion.getCapacidad()),
                        habitacion.obtenerPrecioFormateado(),
                        habitacion.getEstado(),
                        habitacion.obtenerAmenidades());
                dialogo.mostrar();
            }
        });
        
        tablaHabitaciones.setOnEditar(numero -> {
            if (numero == null || numero.isEmpty()) {
                return;
            }
            Habitacion habitacion = gestionHabitaciones.buscarPorNumero(numero);
            if (habitacion != null) {
                ventanas.dialogos.DialogoEditarHabitacion dialogo = 
                    new ventanas.dialogos.DialogoEditarHabitacion(
                        habitacion.getNumero(),
                        habitacion.getTipo(),
                        habitacion.getPiso(),
                        String.valueOf(habitacion.getCapacidad()),
                        habitacion.obtenerPrecioFormateado(),
                        habitacion.getEstado(),
                        habitacion.obtenerAmenidades());
                dialogo.mostrar();
                // Recargar tabla después de editar
                cargarDatosEnTabla();
            }
        });
        
        tablaHabitaciones.setOnEliminar(numero -> {
            if (numero == null || numero.isEmpty()) {
                return;
            }
            Habitacion habitacion = gestionHabitaciones.buscarPorNumero(numero);
            if (habitacion != null) {
                ventanas.dialogos.DialogoConfirmacion dialogo = 
                    new ventanas.dialogos.DialogoConfirmacion(
                        "Confirmar Eliminación",
                        "¿Está seguro que desea eliminar la habitación?",
                        "Habitación " + habitacion.getNumero() + " - " + habitacion.getTipo(),
                        "trash-2",
                        true // esEliminacion
                    );
                dialogo.mostrar();
                if (dialogo.isConfirmado()) {
                    if (gestionHabitaciones.eliminar(numero)) {
                        cargarDatosEnTabla();
                        actualizarBarraEstado();
                        ventanas.dialogos.DialogoMensaje dialogoExito = 
                            new ventanas.dialogos.DialogoMensaje("Operación Exitosa",
                                "La habitación ha sido eliminada correctamente.",
                                ventanas.dialogos.DialogoMensaje.TipoMensaje.EXITO);
                        dialogoExito.mostrar();
                    }
                }
            }
        });
        
        // Cargar datos iniciales
        cargarDatosEnTabla();
        
        panel.getChildren().addAll(lblTituloTabla, tablaHabitaciones);
        
        return panel;
    }
    
    public BorderPane getRoot() {
        return root;
    }

    /** Refresca el desplegable de tipos con la lista actual del gestor (llamar al mostrar esta ventana). */
    public void refrescarListaTipos() {
        cmbTipo.getItems().setAll(java.util.Arrays.asList(gestionTiposHabitacion.obtenerNombresTipos()));
    }

    public PanelNavegacion getPanelNavegacion() {
        return panelNavegacion;
    }
    
    private void limpiarFormulario() {
        campoNumero.setValor("");
        cmbTipo.getSelectionModel().clearSelection();
        campoPiso.setValor("");
        campoPrecio.setValor("");
        cmbCapacidad.getSelectionModel().clearSelection();
        cmbEstado.getSelectionModel().clearSelection();
        txtDescripcion.setText("");
        chkWifi.setSelected(false);
        chkTV.setSelected(false);
        chkAC.setSelected(false);
        chkMinibar.setSelected(false);
        chkJacuzzi.setSelected(false);
    }
    
    private void cargarDatosEnTabla() {
        // Limpiar tabla
        tablaHabitaciones.limpiar();
        
        // Cargar datos desde la gestión
        java.util.ArrayList<Habitacion> habitaciones = gestionHabitaciones.obtenerTodas();
        for (Habitacion h : habitaciones) {
            tablaHabitaciones.agregarFila(new String[]{
                h.getNumero(),
                h.getTipo(),
                h.getPiso(),
                h.obtenerPrecioFormateado(),
                String.valueOf(h.getCapacidad()),
                h.getEstado(),
                ""
            });
        }
    }
}
