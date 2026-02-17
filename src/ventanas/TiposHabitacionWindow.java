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
import componentes.BarraTitulo;
import componentes.CampoFormulario;
import componentes.PanelNavegacion;
import componentes.TablaDatos;
import utilidades.Constantes;
import clases.GestionTiposHabitacion;
import clases.TipoHabitacion;

/**
 * Ventana para la gestión de tipos de habitación
 */
public class TiposHabitacionWindow {
    
    private BorderPane root;
    private BarraTitulo barraTitulo;
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
    private GestionTiposHabitacion gestionTipos;
    private ComboBox<String> cmbCapacidad;
    private ComboBox<String> cmbCamas;
    
    public TiposHabitacionWindow() {
        // Inicializar gestión de tipos
        gestionTipos = utilidades.GestorDatos.getInstancia().getGestionTiposHabitacion();
        
        root = new BorderPane();
        root.setStyle("-fx-background-color: #" + Constantes.COLOR_FONDO.toString().substring(2, 8) + ";");
        
        barraTitulo = new BarraTitulo("Sistema de Reserva de Hoteles - Tipos de Habitación");
        root.setTop(barraTitulo);
        
        BorderPane panelPrincipal = new BorderPane();
        panelPrincipal.setStyle("-fx-background-color: #" + Constantes.COLOR_FONDO.toString().substring(2, 8) + ";");
        
        panelNavegacion = new PanelNavegacion();
        panelNavegacion.seleccionarItem(3); // Tipos Habitación seleccionado
        panelPrincipal.setLeft(panelNavegacion);
        
        VBox panelContenido = crearPanelContenido();
        panelPrincipal.setCenter(panelContenido);
        
        root.setCenter(panelPrincipal);
        
        barraEstado = new BarraEstado();
        actualizarBarraEstado();
        root.setBottom(barraEstado);
    }
    
    private void actualizarBarraEstado() {
        int cantidad = gestionTipos.obtenerCantidad();
        barraEstado.setEstado(cantidad + " tipo" + (cantidad != 1 ? "s" : "") + " registrado" + (cantidad != 1 ? "s" : ""));
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
        cmbCapacidad = new ComboBox<>();
        cmbCapacidad.getItems().addAll("1", "2", "3", "4", "5", "6");
        cmbCapacidad.setPrefWidth(80);
        cmbCapacidad.setPrefHeight(24);
        cmbCapacidad.setMinHeight(24);
        cmbCapacidad.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(cmbCapacidad, "Seleccionar");
        panelCapacidad.getChildren().addAll(lblCapacidad, cmbCapacidad);
        
        HBox panelCamas = new HBox(12);
        panelCamas.setAlignment(Pos.CENTER_LEFT);
        Label lblCamas = new Label("Tipo de Camas:");
        lblCamas.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblCamas.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        cmbCamas = new ComboBox<>();
        cmbCamas.getItems().addAll("Individual", "Doble", "Queen", "King");
        cmbCamas.setPrefWidth(150);
        cmbCamas.setPrefHeight(24);
        cmbCamas.setMinHeight(24);
        cmbCamas.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(cmbCamas, "Seleccionar tipo");
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
        utilidades.EstiloCheckBox.aplicarEstilo(chkWifi);
        utilidades.EstiloCheckBox.aplicarEstilo(chkTV);
        utilidades.EstiloCheckBox.aplicarEstilo(chkAC);
        utilidades.EstiloCheckBox.aplicarEstilo(chkMinibar);
        utilidades.EstiloCheckBox.aplicarEstilo(chkJacuzzi);
        fila3.getChildren().addAll(lblAmenidades, chkWifi, chkTV, chkAC, chkMinibar, chkJacuzzi);
        
        HBox panelBotones = new HBox(8);
        panelBotones.setAlignment(Pos.CENTER_LEFT);
        Button btnAgregar = new Button("Agregar");
        btnAgregar.setFont(Constantes.FUENTE_NORMAL);
        btnAgregar.setStyle("-fx-background-color: #" + Constantes.COLOR_PRIMARIO.toString().substring(2, 8) + 
                           "; -fx-text-fill: #" + Constantes.COLOR_TEXTO_BLANCO.toString().substring(2, 8) + 
                           "; -fx-border-color: #" + Constantes.COLOR_PRIMARIO_OSCURO.toString().substring(2, 8) + 
                           "; -fx-border-width: 1; -fx-padding: 6 16 6 16;");
        btnAgregar.setOnAction(_ -> {
            // Validar campos
            if (campoCodigo.getValor().isEmpty() || campoNombre.getValor().isEmpty() || 
                campoPrecio.getValor().isEmpty() || cmbCapacidad.getValue() == null ||
                cmbCamas.getValue() == null || campoArea.getValor().isEmpty()) {
                ventanas.dialogos.DialogoMensaje dialogoError = 
                    new ventanas.dialogos.DialogoMensaje("Error de Validación",
                        "Por favor complete todos los campos obligatorios.",
                        ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                dialogoError.mostrar();
            } else {
                // Verificar si ya existe un tipo con ese código
                if (gestionTipos.buscarPorCodigo(campoCodigo.getValor()) != null) {
                    ventanas.dialogos.DialogoMensaje dialogoError = 
                        new ventanas.dialogos.DialogoMensaje("Error de Validación",
                            "Ya existe un tipo de habitación con el código: " + campoCodigo.getValor(),
                            ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                    dialogoError.mostrar();
                    return;
                }
                
                try {
                    // Crear nuevo tipo de habitación
                    TipoHabitacion nuevoTipo = new TipoHabitacion();
                    nuevoTipo.setCodigo(campoCodigo.getValor());
                    nuevoTipo.setNombre(campoNombre.getValor());
                    nuevoTipo.setPrecioBase(Double.parseDouble(campoPrecio.getValor()));
                    nuevoTipo.setCapacidad(Integer.parseInt(cmbCapacidad.getValue()));
                    nuevoTipo.setTipoCamas(cmbCamas.getValue());
                    nuevoTipo.setArea(Double.parseDouble(campoArea.getValor()));
                    nuevoTipo.setWifi(chkWifi.isSelected());
                    nuevoTipo.setTv(chkTV.isSelected());
                    nuevoTipo.setAc(chkAC.isSelected());
                    nuevoTipo.setMinibar(chkMinibar.isSelected());
                    nuevoTipo.setJacuzzi(chkJacuzzi.isSelected());
                    nuevoTipo.setDescripcion(""); // Se puede agregar campo de descripción si es necesario
                    
                    // Agregar a la gestión
                    if (gestionTipos.agregar(nuevoTipo)) {
                        // Actualizar tabla
                        cargarDatosEnTabla();
                        
                        // Mostrar diálogo de éxito
                        ventanas.dialogos.DialogoMensaje dialogoExito = 
                            new ventanas.dialogos.DialogoMensaje("Operación Exitosa",
                                "El tipo de habitación ha sido registrado exitosamente.",
                                ventanas.dialogos.DialogoMensaje.TipoMensaje.EXITO);
                        dialogoExito.mostrar();
                        
                        // Actualizar barra de estado
                        actualizarBarraEstado();
                        
                        // Limpiar formulario
                        limpiarFormulario();
                    } else {
                        ventanas.dialogos.DialogoMensaje dialogoError = 
                            new ventanas.dialogos.DialogoMensaje("Error",
                                "No se pudo registrar el tipo de habitación. Intente nuevamente.",
                                ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                        dialogoError.mostrar();
                    }
                } catch (NumberFormatException e) {
                    ventanas.dialogos.DialogoMensaje dialogoError = 
                        new ventanas.dialogos.DialogoMensaje("Error de Validación",
                            "El precio y el área deben ser números válidos.",
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
        
        // Configurar callbacks para los botones de acción
        // Ahora los callbacks reciben el código directamente, no el índice
        tablaTipos.setOnVer(codigo -> {
            if (codigo == null || codigo.isEmpty()) {
                return;
            }
            TipoHabitacion tipo = gestionTipos.buscarPorCodigo(codigo);
            if (tipo != null) {
                ventanas.dialogos.DialogoDetalleTipoHabitacion dialogo = 
                    new ventanas.dialogos.DialogoDetalleTipoHabitacion(
                        tipo.getCodigo(),
                        tipo.getNombre(),
                        tipo.obtenerCapacidadTexto(),
                        tipo.obtenerPrecioFormateado(),
                        tipo.getDescripcion());
                dialogo.mostrar();
            }
        });
        
        tablaTipos.setOnEditar(codigo -> {
            if (codigo == null || codigo.isEmpty()) {
                return;
            }
            TipoHabitacion tipo = gestionTipos.buscarPorCodigo(codigo);
            if (tipo != null) {
                ventanas.dialogos.DialogoEditarTipoHabitacion dialogo = 
                    new ventanas.dialogos.DialogoEditarTipoHabitacion(
                        tipo.getCodigo(),
                        tipo.getNombre(),
                        String.valueOf(tipo.getCapacidad()),
                        tipo.obtenerPrecioFormateado(),
                        tipo.getDescripcion());
                dialogo.mostrar();
                // Recargar tabla después de editar
                cargarDatosEnTabla();
            }
        });
        
        tablaTipos.setOnEliminar(codigo -> {
            if (codigo == null || codigo.isEmpty()) {
                return;
            }
            TipoHabitacion tipo = gestionTipos.buscarPorCodigo(codigo);
            if (tipo != null) {
                ventanas.dialogos.DialogoConfirmacion dialogo = 
                    new ventanas.dialogos.DialogoConfirmacion(
                        "Confirmar Eliminación",
                        "¿Está seguro que desea eliminar este tipo de habitación?",
                        tipo.getNombre() + " (" + tipo.getCodigo() + ")",
                        "trash-2",
                        true // esEliminacion
                    );
                dialogo.mostrar();
                if (dialogo.isConfirmado()) {
                    if (gestionTipos.eliminar(codigo)) {
                        cargarDatosEnTabla();
                        actualizarBarraEstado();
                        ventanas.dialogos.DialogoMensaje dialogoExito = 
                            new ventanas.dialogos.DialogoMensaje("Operación Exitosa",
                                "El tipo de habitación ha sido eliminado correctamente.",
                                ventanas.dialogos.DialogoMensaje.TipoMensaje.EXITO);
                        dialogoExito.mostrar();
                    }
                }
            }
        });
        
        // Cargar datos iniciales
        cargarDatosEnTabla();
        
        panel.getChildren().addAll(lblTituloTabla, tablaTipos);
        
        return panel;
    }
    
    public BorderPane getRoot() {
        return root;
    }
    
    public PanelNavegacion getPanelNavegacion() {
        return panelNavegacion;
    }
    
    private void limpiarFormulario() {
        campoCodigo.setValor("");
        campoNombre.setValor("");
        campoPrecio.setValor("");
        campoArea.setValor("");
        cmbCapacidad.getSelectionModel().clearSelection();
        cmbCamas.getSelectionModel().clearSelection();
        chkWifi.setSelected(false);
        chkTV.setSelected(false);
        chkAC.setSelected(false);
        chkMinibar.setSelected(false);
        chkJacuzzi.setSelected(false);
    }
    
    private void cargarDatosEnTabla() {
        // Limpiar tabla
        tablaTipos.limpiar();
        
        // Cargar datos desde la gestión
        java.util.ArrayList<TipoHabitacion> tipos = gestionTipos.obtenerTodos();
        for (TipoHabitacion t : tipos) {
            tablaTipos.agregarFila(new String[]{
                t.getCodigo(),
                t.getNombre(),
                t.obtenerPrecioFormateado(),
                ""
            });
        }
    }
}
