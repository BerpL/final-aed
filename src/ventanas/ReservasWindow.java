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
import componentes.BarraEstado;
import componentes.BarraTitulo;
import componentes.CampoFormulario;
import componentes.PanelNavegacion;
import componentes.TablaDatos;
import utilidades.Constantes;

/**
 * Ventana para el registro de reservas
 */
public class ReservasWindow {
    
    private BorderPane root;
    private BarraTitulo barraTitulo;
    private PanelNavegacion panelNavegacion;
    private BarraEstado barraEstado;
    private ComboBox<String> cmbHuesped;
    private ComboBox<String> cmbHabitacion;
    private DatePicker datePickerCheckIn;
    private DatePicker datePickerCheckOut;
    private CampoFormulario campoNumHuespedes;
    private ComboBox<String> cmbTipoPago;
    private Label lblTotalValor;
    private CheckBox chkDesayuno;
    private CheckBox chkParking;
    private CheckBox chkSpa;
    private TablaDatos tablaReservas;
    
    public ReservasWindow() {
        root = new BorderPane();
        root.setStyle("-fx-background-color: #" + Constantes.COLOR_FONDO.toString().substring(2, 8) + ";");
        
        barraTitulo = new BarraTitulo("Sistema de Reserva de Hoteles - Registro de Reservas");
        root.setTop(barraTitulo);
        
        BorderPane panelPrincipal = new BorderPane();
        panelPrincipal.setStyle("-fx-background-color: #" + Constantes.COLOR_FONDO.toString().substring(2, 8) + ";");
        
        panelNavegacion = new PanelNavegacion();
        panelNavegacion.seleccionarItem(4); // Reservas seleccionado
        panelPrincipal.setLeft(panelNavegacion);
        
        VBox panelContenido = crearPanelContenido();
        panelPrincipal.setCenter(panelContenido);
        
        root.setCenter(panelPrincipal);
        
        barraEstado = new BarraEstado();
        barraEstado.setEstado("5 reservas registradas");
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
        
        Label lblTitulo = new Label("Registro de Reservas");
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
        
        Label lblTituloForm = new Label("Nueva Reserva");
        lblTituloForm.setFont(Constantes.FUENTE_SUBTITULO);
        lblTituloForm.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        
        HBox fila1 = new HBox(24);
        fila1.setAlignment(Pos.CENTER_LEFT);
        
        HBox panelHuesped = new HBox(12);
        panelHuesped.setAlignment(Pos.CENTER_LEFT);
        Label lblHuesped = new Label("Huésped:");
        lblHuesped.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblHuesped.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        cmbHuesped = new ComboBox<>();
        cmbHuesped.getItems().addAll("Juan Pérez", "María García");
        cmbHuesped.setPrefWidth(250);
        cmbHuesped.setPrefHeight(24);
        cmbHuesped.setMinHeight(24);
        cmbHuesped.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(cmbHuesped, "Seleccionar huésped");
        panelHuesped.getChildren().addAll(lblHuesped, cmbHuesped);
        
        HBox panelHabitacion = new HBox(12);
        panelHabitacion.setAlignment(Pos.CENTER_LEFT);
        Label lblHabitacion = new Label("Habitación:");
        lblHabitacion.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblHabitacion.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        cmbHabitacion = new ComboBox<>();
        cmbHabitacion.getItems().addAll("101 - Suite", "102 - Suite", "201 - Doble");
        cmbHabitacion.setPrefWidth(250);
        cmbHabitacion.setPrefHeight(24);
        cmbHabitacion.setMinHeight(24);
        cmbHabitacion.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(cmbHabitacion, "Seleccionar habitación");
        panelHabitacion.getChildren().addAll(lblHabitacion, cmbHabitacion);
        
        fila1.getChildren().addAll(panelHuesped, panelHabitacion);
        
        HBox fila2 = new HBox(24);
        fila2.setAlignment(Pos.CENTER_LEFT);
        
        // DatePicker Check-In
        HBox panelCheckIn = new HBox(12);
        panelCheckIn.setAlignment(Pos.CENTER_LEFT);
        Label lblCheckIn = new Label("Check-In:");
        lblCheckIn.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblCheckIn.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        datePickerCheckIn = new DatePicker();
        datePickerCheckIn.setPrefWidth(150);
        datePickerCheckIn.setPrefHeight(24);
        datePickerCheckIn.setMinHeight(24);
        datePickerCheckIn.setMaxHeight(24);
        datePickerCheckIn.setConverter(new utilidades.DateConverter());
        datePickerCheckIn.getStyleClass().add("aed-date-picker");
        datePickerCheckIn.setPromptText("dd/MM/yyyy");
        panelCheckIn.getChildren().addAll(lblCheckIn, datePickerCheckIn);
        
        // DatePicker Check-Out
        HBox panelCheckOut = new HBox(12);
        panelCheckOut.setAlignment(Pos.CENTER_LEFT);
        Label lblCheckOut = new Label("Check-Out:");
        lblCheckOut.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblCheckOut.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        datePickerCheckOut = new DatePicker();
        datePickerCheckOut.setPrefWidth(150);
        datePickerCheckOut.setPrefHeight(24);
        datePickerCheckOut.setMinHeight(24);
        datePickerCheckOut.setMaxHeight(24);
        datePickerCheckOut.setConverter(new utilidades.DateConverter());
        datePickerCheckOut.getStyleClass().add("aed-date-picker");
        datePickerCheckOut.setPromptText("dd/MM/yyyy");
        
        // Validar que Check-Out no sea anterior a Check-In
        datePickerCheckIn.valueProperty().addListener((_, oldDate, newDate) -> {
            if (newDate != null && datePickerCheckOut.getValue() != null) {
                if (datePickerCheckOut.getValue().isBefore(newDate)) {
                    datePickerCheckOut.setValue(newDate);
                }
            }
        });
        
        datePickerCheckOut.setDayCellFactory(picker -> new javafx.scene.control.DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate checkInDate = datePickerCheckIn.getValue();
                if (checkInDate != null && date != null && date.isBefore(checkInDate)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #F0F0F0;");
                }
            }
        });
        
        panelCheckOut.getChildren().addAll(lblCheckOut, datePickerCheckOut);
        fila2.getChildren().addAll(panelCheckIn, panelCheckOut);
        
        HBox fila3 = new HBox(24);
        fila3.setAlignment(Pos.CENTER_LEFT);
        campoNumHuespedes = new CampoFormulario("N° Huéspedes", 120);
        
        HBox panelTipoPago = new HBox(12);
        panelTipoPago.setAlignment(Pos.CENTER_LEFT);
        Label lblTipoPago = new Label("Forma de Pago:");
        lblTipoPago.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblTipoPago.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        cmbTipoPago = new ComboBox<>();
        cmbTipoPago.getItems().addAll("Tarjeta Crédito", "Efectivo", "Transferencia");
        cmbTipoPago.setPrefWidth(200);
        cmbTipoPago.setPrefHeight(24);
        cmbTipoPago.setMinHeight(24);
        cmbTipoPago.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(cmbTipoPago, "Seleccionar forma de pago");
        panelTipoPago.getChildren().addAll(lblTipoPago, cmbTipoPago);
        
        // Campo Total - solo texto, no es input
        HBox panelTotal = new HBox(12);
        panelTotal.setAlignment(Pos.CENTER_LEFT);
        Label lblTotal = new Label("Total:");
        lblTotal.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblTotal.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        lblTotalValor = new Label("S/. 750.00");
        lblTotalValor.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblTotalValor.setTextFill(Constantes.COLOR_PRIMARIO); // Color azul (#0078D4)
        panelTotal.getChildren().addAll(lblTotal, lblTotalValor);
        
        fila3.getChildren().addAll(campoNumHuespedes, panelTipoPago, panelTotal);
        
        HBox fila4 = new HBox(24);
        fila4.setAlignment(Pos.CENTER_LEFT);
        Label lblServicios = new Label("Servicios Adicionales:");
        lblServicios.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblServicios.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        chkDesayuno = new CheckBox("Desayuno (S/.10.00)");
        chkParking = new CheckBox("Parking (S/.5.00)");
        chkSpa = new CheckBox("Spa (S/.20.00)");
        utilidades.EstiloCheckBox.aplicarEstilo(chkDesayuno);
        utilidades.EstiloCheckBox.aplicarEstilo(chkParking);
        utilidades.EstiloCheckBox.aplicarEstilo(chkSpa);
        fila4.getChildren().addAll(lblServicios, chkDesayuno, chkParking, chkSpa);
        
        HBox panelAdvertencia = new HBox(8);
        panelAdvertencia.setAlignment(Pos.CENTER_LEFT);
        panelAdvertencia.setPadding(new Insets(8, 12, 8, 12));
        panelAdvertencia.setStyle("-fx-background-color: #" + Constantes.COLOR_ADVERTENCIA.toString().substring(2, 8) + 
                                 "; -fx-border-color: #FFE69C; -fx-border-width: 1;");
        Label lblAdvertencia = new Label("Verifique disponibilidad antes de confirmar. Las fechas indicadas incluyen early check-in.");
        lblAdvertencia.setFont(Constantes.FUENTE_PEQUENA);
        lblAdvertencia.setTextFill(javafx.scene.paint.Color.rgb(133, 100, 4));
        panelAdvertencia.getChildren().add(lblAdvertencia);
        
        HBox panelBotones = new HBox(8);
        panelBotones.setAlignment(Pos.CENTER_LEFT);
        Button btnRegistrar = new Button("Registrar Reserva");
        btnRegistrar.setFont(Constantes.FUENTE_NORMAL);
        btnRegistrar.setStyle("-fx-background-color: #" + Constantes.COLOR_PRIMARIO.toString().substring(2, 8) + 
                             "; -fx-text-fill: #" + Constantes.COLOR_TEXTO_BLANCO.toString().substring(2, 8) + 
                             "; -fx-border-color: #" + Constantes.COLOR_PRIMARIO_OSCURO.toString().substring(2, 8) + 
                             "; -fx-border-width: 1; -fx-padding: 6 16 6 16;");
        btnRegistrar.setOnAction(_ -> {
            // Validar campos
            if (cmbHuesped.getSelectionModel().getSelectedIndex() < 0 || 
                cmbHabitacion.getSelectionModel().getSelectedIndex() < 0 ||
                datePickerCheckIn.getValue() == null || datePickerCheckOut.getValue() == null) {
                ventanas.dialogos.DialogoMensaje dialogoError = 
                    new ventanas.dialogos.DialogoMensaje("Error de Validación",
                        "La habitación seleccionada no está disponible para las fechas indicadas. Por favor seleccione otras fechas u otra habitación.",
                        ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                dialogoError.mostrar();
            } else {
                // Agregar a la tabla
                String codigo = "RES" + String.format("%03d", tablaReservas.getTabla().getItems().size() + 1);
                String checkInStr = datePickerCheckIn.getValue() != null ? 
                    datePickerCheckIn.getValue().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
                String checkOutStr = datePickerCheckOut.getValue() != null ? 
                    datePickerCheckOut.getValue().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
                
                tablaReservas.agregarFila(new String[]{
                    codigo,
                    cmbHuesped.getValue(),
                    cmbHabitacion.getValue(),
                    checkInStr,
                    checkOutStr,
                    "Activo",
                    ""
                });
                
                // Mostrar diálogo de éxito
                ventanas.dialogos.DialogoMensaje dialogoExito = 
                    new ventanas.dialogos.DialogoMensaje("Operación Exitosa",
                        "La reserva ha sido registrada exitosamente. Se ha enviado confirmación al huésped.",
                        ventanas.dialogos.DialogoMensaje.TipoMensaje.EXITO);
                dialogoExito.mostrar();
                
                // Limpiar formulario
                limpiarFormulario();
            }
        });
        
        Button btnLimpiar = new Button("Limpiar");
        btnLimpiar.setFont(Constantes.FUENTE_NORMAL);
        btnLimpiar.setStyle("-fx-background-color: #" + Constantes.COLOR_SECUNDARIO.toString().substring(2, 8) + 
                          "; -fx-text-fill: #" + Constantes.COLOR_TEXTO_PRINCIPAL.toString().substring(2, 8) + 
                          "; -fx-border-color: #" + Constantes.COLOR_BORDE_MEDIO.toString().substring(2, 8) + 
                          "; -fx-border-width: 1; -fx-padding: 6 16 6 16;");
        btnLimpiar.setOnAction(_ -> limpiarFormulario());
        
        panelBotones.getChildren().addAll(btnRegistrar, btnLimpiar);
        
        panel.getChildren().addAll(lblTituloForm, fila1, fila2, fila3, fila4, panelAdvertencia, panelBotones);
        
        return panel;
    }
    
    private VBox crearPanelTabla() {
        VBox panel = new VBox(8);
        panel.setPadding(new Insets(0));
        panel.setStyle("-fx-background-color: #" + Constantes.COLOR_BLANCO.toString().substring(2, 8) + ";");
        VBox.setVgrow(panel, javafx.scene.layout.Priority.ALWAYS);
        
        Label lblTituloTabla = new Label("Lista de Reservas");
        lblTituloTabla.setFont(Constantes.FUENTE_SUBTITULO);
        lblTituloTabla.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        
        String[] columnas = {"Código", "Huésped", "Habitación", "Check-In", "Check-Out", "Estado", "Acciones"};
        tablaReservas = new TablaDatos(columnas);
        VBox.setVgrow(tablaReservas, javafx.scene.layout.Priority.ALWAYS);
        
        // Configurar callbacks para los botones de acción
        tablaReservas.setOnVer(fila -> {
            String codigo = tablaReservas.getValor(fila, 0);
            String huesped = tablaReservas.getValor(fila, 1);
            String habitacion = tablaReservas.getValor(fila, 2);
            String checkIn = tablaReservas.getValor(fila, 3);
            String checkOut = tablaReservas.getValor(fila, 4);
            String estado = tablaReservas.getValor(fila, 5);
            
            // Calcular noches (simplificado, en producción se calcularía de las fechas)
            String noches = "3";
            String nroHuespedes = "2";
            String total = "S/. 2,550.00";
            String observaciones = "Cliente VIP, preparar champagne de cortesía.";
            
            ventanas.dialogos.DialogoDetalleReserva dialogo = 
                new ventanas.dialogos.DialogoDetalleReserva(codigo, huesped, habitacion,
                    checkIn, checkOut, noches, nroHuespedes, total, estado, observaciones);
            dialogo.mostrar();
        });
        
        tablaReservas.setOnEditar(fila -> {
            String huesped = tablaReservas.getValor(fila, 1);
            String habitacion = tablaReservas.getValor(fila, 2);
            String checkIn = tablaReservas.getValor(fila, 3);
            String checkOut = tablaReservas.getValor(fila, 4);
            String estado = tablaReservas.getValor(fila, 5);
            
            ventanas.dialogos.DialogoEditarReserva dialogo = 
                new ventanas.dialogos.DialogoEditarReserva(huesped, habitacion, checkIn, 
                    checkOut, "2", estado, "Cliente VIP, preparar champagne de cortesía.");
            dialogo.mostrar();
        });
        
        tablaReservas.setOnEliminar(fila -> {
            String codigo = tablaReservas.getValor(fila, 0);
            String huesped = tablaReservas.getValor(fila, 1);
            ventanas.dialogos.DialogoConfirmacion dialogo = 
                new ventanas.dialogos.DialogoConfirmacion(
                    "Cancelar Reserva",
                    "¿Está seguro que desea cancelar esta reserva? Esta acción cancelará la reserva y liberará la habitación.",
                    "Reserva: " + codigo + " - " + huesped,
                    "info",
                    false // esEliminacion (título azul)
                );
            dialogo.mostrar();
            if (dialogo.isConfirmado()) {
                tablaReservas.eliminarFila(fila);
                ventanas.dialogos.DialogoMensaje dialogoExito = 
                    new ventanas.dialogos.DialogoMensaje("Operación Exitosa",
                        "La reserva ha sido cancelada correctamente. Se enviará notificación al huésped.",
                        ventanas.dialogos.DialogoMensaje.TipoMensaje.EXITO);
                dialogoExito.mostrar();
            }
        });
        
        tablaReservas.agregarFila(new String[]{"RES001", "Juan Perez Lopez", "101-Suite", "15/07/2024", "17/07/2024", "Activo", ""});
        tablaReservas.agregarFila(new String[]{"RES002", "Maria Rodriguez", "203-Doble", "20/07/2024", "22/07/2024", "Pendiente", ""});
        
        panel.getChildren().addAll(lblTituloTabla, tablaReservas);
        
        return panel;
    }
    
    public BorderPane getRoot() {
        return root;
    }
    
    public PanelNavegacion getPanelNavegacion() {
        return panelNavegacion;
    }
    
    private void limpiarFormulario() {
        cmbHuesped.getSelectionModel().clearSelection();
        cmbHabitacion.getSelectionModel().clearSelection();
        datePickerCheckIn.setValue(null);
        datePickerCheckOut.setValue(null);
        campoNumHuespedes.setValor("");
        cmbTipoPago.getSelectionModel().clearSelection();
        lblTotalValor.setText("S/. 0.00");
        chkDesayuno.setSelected(false);
        chkParking.setSelected(false);
        chkSpa.setSelected(false);
    }
}
