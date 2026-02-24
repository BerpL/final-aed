package ventanas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
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
import clases.GestionReservas;
import clases.GestionHuespedes;
import clases.GestionHabitaciones;
import clases.Reserva;
import clases.Huesped;
import clases.Habitacion;

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
    private GestionReservas gestionReservas;
    private GestionHuespedes gestionHuespedes;
    private GestionHabitaciones gestionHabitaciones;
    
    public ReservasWindow() {
        // Obtener instancias compartidas de las gestiones
        utilidades.GestorDatos gestorDatos = utilidades.GestorDatos.getInstancia();
        gestionReservas = gestorDatos.getGestionReservas();
        gestionHuespedes = gestorDatos.getGestionHuespedes();
        gestionHabitaciones = gestorDatos.getGestionHabitaciones();
        
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
        actualizarBarraEstado();
        root.setBottom(barraEstado);
    }
    
    private void actualizarBarraEstado() {
        int cantidad = gestionReservas.obtenerCantidad();
        barraEstado.setEstado(cantidad + " reserva" + (cantidad != 1 ? "s" : "") + " registrada" + (cantidad != 1 ? "s" : ""));
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
        Label lblHuesped = new Label("Huésped *:");
        lblHuesped.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblHuesped.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        cmbHuesped = new ComboBox<>();
        // Cargar huéspedes desde la gestión
        actualizarComboHuespedes();
        cmbHuesped.setPrefWidth(250);
        cmbHuesped.setPrefHeight(24);
        cmbHuesped.setMinHeight(24);
        cmbHuesped.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(cmbHuesped, "Seleccionar huésped");
        panelHuesped.getChildren().addAll(lblHuesped, cmbHuesped);
        
        HBox panelHabitacion = new HBox(12);
        panelHabitacion.setAlignment(Pos.CENTER_LEFT);
        Label lblHabitacion = new Label("Habitación *:");
        lblHabitacion.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblHabitacion.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        cmbHabitacion = new ComboBox<>();
        // Cargar habitaciones disponibles desde la gestión
        actualizarComboHabitaciones();
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
        Label lblCheckIn = new Label("Check-In *:");
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
        Label lblCheckOut = new Label("Check-Out *:");
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
        datePickerCheckIn.valueProperty().addListener((_, _, newDate) -> {
            if (newDate != null && datePickerCheckOut.getValue() != null) {
                if (datePickerCheckOut.getValue().isBefore(newDate)) {
                    datePickerCheckOut.setValue(newDate);
                }
            }
        });
        
        datePickerCheckOut.setDayCellFactory(_ -> new javafx.scene.control.DateCell() {
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
        campoNumHuespedes = new CampoFormulario("N° Huéspedes *", 120);
        campoNumHuespedes.getCampo().setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) return change;
            if (!newText.matches("[0-9]*") || newText.length() > 2) return null;
            return change;
        }));
        
        HBox panelTipoPago = new HBox(12);
        panelTipoPago.setAlignment(Pos.CENTER_LEFT);
        Label lblTipoPago = new Label("Forma de Pago *:");
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
        lblTotalValor = new Label("S/. 0.00");
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
        
        // Agregar listeners para actualizar el total dinámicamente
        configurarListenersTotal();
        
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
            String numHuespedesStr = campoNumHuespedes.getValor() != null ? campoNumHuespedes.getValor().trim() : "";
            if (cmbHuesped.getValue() == null || cmbHuesped.getValue().trim().isEmpty() ||
                cmbHabitacion.getValue() == null || cmbHabitacion.getValue().trim().isEmpty() ||
                datePickerCheckIn.getValue() == null || datePickerCheckOut.getValue() == null ||
                numHuespedesStr.isEmpty() || cmbTipoPago.getValue() == null || cmbTipoPago.getValue().trim().isEmpty()) {
                ventanas.dialogos.DialogoMensaje dialogoError =
                    new ventanas.dialogos.DialogoMensaje("Error de Validación",
                        "Complete todos los campos obligatorios: Huésped, Habitación, Check-In, Check-Out, N° Huéspedes y Forma de Pago.",
                        ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                dialogoError.mostrar();
                return;
            }
            int numeroHuespedes;
            try {
                numeroHuespedes = Integer.parseInt(numHuespedesStr);
                if (numeroHuespedes < 1) {
                    ventanas.dialogos.DialogoMensaje d = new ventanas.dialogos.DialogoMensaje("Error de Validación",
                        "El número de huéspedes debe ser al menos 1.", ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                    d.mostrar();
                    return;
                }
            } catch (NumberFormatException e) {
                ventanas.dialogos.DialogoMensaje d = new ventanas.dialogos.DialogoMensaje("Error de Validación",
                    "El número de huéspedes debe ser un número válido.", ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                d.mostrar();
                return;
            }
            if (!datePickerCheckOut.getValue().isAfter(datePickerCheckIn.getValue()) &&
                !datePickerCheckOut.getValue().equals(datePickerCheckIn.getValue())) {
                ventanas.dialogos.DialogoMensaje d = new ventanas.dialogos.DialogoMensaje("Error de Validación",
                    "La fecha de Check-Out debe ser igual o posterior al Check-In.", ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                d.mostrar();
                return;
            }
            String nombreHuesped = cmbHuesped.getValue();
            String numeroHabitacion = cmbHabitacion.getValue().split(" - ")[0];
            LocalDate checkIn = datePickerCheckIn.getValue();
            LocalDate checkOut = datePickerCheckOut.getValue();
            Habitacion habitacionCheck = gestionHabitaciones.buscarPorNumero(numeroHabitacion);
            if (habitacionCheck != null && numeroHuespedes > habitacionCheck.getCapacidad()) {
                ventanas.dialogos.DialogoMensaje d = new ventanas.dialogos.DialogoMensaje("Error de Validación",
                    "El número de huéspedes no puede superar la capacidad de la habitación (" + habitacionCheck.getCapacidad() + ").",
                    ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                d.mostrar();
                return;
            }
            
            // Verificar disponibilidad
            if (!gestionReservas.estaDisponible(numeroHabitacion, checkIn, checkOut)) {
                ventanas.dialogos.DialogoMensaje dialogoError = 
                    new ventanas.dialogos.DialogoMensaje("Error de Validación",
                        "La habitación seleccionada no está disponible para las fechas indicadas. Por favor seleccione otras fechas u otra habitación.",
                        ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                dialogoError.mostrar();
                return;
            }
            
            try {
                // Buscar huésped por nombre
                Huesped huesped = null;
                java.util.ArrayList<Huesped> huespedes = gestionHuespedes.obtenerTodos();
                for (Huesped h : huespedes) {
                    if (h.obtenerNombreCompleto().equals(nombreHuesped)) {
                        huesped = h;
                        break;
                    }
                }
                
                if (huesped == null) {
                    ventanas.dialogos.DialogoMensaje dialogoError = 
                        new ventanas.dialogos.DialogoMensaje("Error",
                            "No se encontró el huésped seleccionado.",
                            ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                    dialogoError.mostrar();
                    return;
                }
                
                // Obtener habitación
                Habitacion habitacion = gestionHabitaciones.buscarPorNumero(numeroHabitacion);
                if (habitacion == null) {
                    ventanas.dialogos.DialogoMensaje dialogoError = 
                        new ventanas.dialogos.DialogoMensaje("Error",
                            "No se encontró la habitación seleccionada.",
                            ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                    dialogoError.mostrar();
                    return;
                }
                
                // Calcular total
                int numeroNoches = (int) java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);
                double total = habitacion.getPrecioNoche() * numeroNoches;
                if (chkDesayuno.isSelected()) total += 10.00 * numeroNoches * numeroHuespedes;
                if (chkParking.isSelected()) total += 5.00 * numeroNoches;
                if (chkSpa.isSelected()) total += 20.00 * numeroNoches;

                // Crear nueva reserva
                Reserva nuevaReserva = new Reserva();
                nuevaReserva.setIdHuesped(huesped.getId());
                nuevaReserva.setNombreHuesped(huesped.obtenerNombreCompleto());
                nuevaReserva.setNumeroHabitacion(numeroHabitacion);
                nuevaReserva.setCheckIn(checkIn);
                nuevaReserva.setCheckOut(checkOut);
                nuevaReserva.setNumeroNoches(numeroNoches);
                nuevaReserva.setNumeroHuespedes(numeroHuespedes);
                nuevaReserva.setFormaPago(cmbTipoPago.getValue());
                nuevaReserva.setTotal(total);
                nuevaReserva.setEstado("Pendiente");
                nuevaReserva.setDesayuno(chkDesayuno.isSelected());
                nuevaReserva.setParking(chkParking.isSelected());
                nuevaReserva.setSpa(chkSpa.isSelected());
                nuevaReserva.setObservaciones("");
                
                // Agregar a la gestión
                if (gestionReservas.agregar(nuevaReserva)) {
                    // Actualizar tabla
                    cargarDatosEnTabla();
                    
                    // Actualizar barra de estado
                    actualizarBarraEstado();
                    
                    // Mostrar diálogo de éxito
                    ventanas.dialogos.DialogoMensaje dialogoExito = 
                        new ventanas.dialogos.DialogoMensaje("Operación Exitosa",
                            "La reserva ha sido registrada exitosamente. Se ha enviado confirmación al huésped.",
                            ventanas.dialogos.DialogoMensaje.TipoMensaje.EXITO);
                    dialogoExito.mostrar();
                    
                    // Limpiar formulario
                    limpiarFormulario();
                } else {
                    ventanas.dialogos.DialogoMensaje dialogoError = 
                        new ventanas.dialogos.DialogoMensaje("Error",
                            "No se pudo registrar la reserva. Intente nuevamente.",
                            ventanas.dialogos.DialogoMensaje.TipoMensaje.ERROR);
                    dialogoError.mostrar();
                }
            } catch (NumberFormatException e) {
                ventanas.dialogos.DialogoMensaje dialogoError = 
                    new ventanas.dialogos.DialogoMensaje("Error de Validación",
                        "El número de huéspedes debe ser un número válido.",
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
        // Ahora los callbacks reciben el ID de reserva directamente, no el índice
        tablaReservas.setOnVer(idReserva -> {
            if (idReserva == null || idReserva.isEmpty()) {
                return;
            }
            Reserva reserva = gestionReservas.buscarPorId(idReserva);
            if (reserva != null) {
                ventanas.dialogos.DialogoDetalleReserva dialogo = 
                    new ventanas.dialogos.DialogoDetalleReserva(
                        reserva.getIdReserva(),
                        reserva.getNombreHuesped(),
                        reserva.getNumeroHabitacion(),
                        reserva.obtenerCheckInFormateado(),
                        reserva.obtenerCheckOutFormateado(),
                        String.valueOf(reserva.getNumeroNoches()),
                        String.valueOf(reserva.getNumeroHuespedes()),
                        reserva.obtenerTotalFormateado(),
                        reserva.getEstado(),
                        reserva.getObservaciones());
                dialogo.mostrar();
            }
        });
        
        tablaReservas.setOnEditar(idReserva -> {
            if (idReserva == null || idReserva.isEmpty()) {
                return;
            }
            Reserva reserva = gestionReservas.buscarPorId(idReserva);
            if (reserva != null) {
                ventanas.dialogos.DialogoEditarReserva dialogo = 
                    new ventanas.dialogos.DialogoEditarReserva(
                        reserva.getNombreHuesped(),
                        reserva.getNumeroHabitacion(),
                        reserva.obtenerCheckInFormateado(),
                        reserva.obtenerCheckOutFormateado(),
                        String.valueOf(reserva.getNumeroHuespedes()),
                        reserva.getEstado(),
                        reserva.getObservaciones());
                dialogo.mostrar();
                // Recargar tabla después de editar
                cargarDatosEnTabla();
            }
        });
        
        tablaReservas.setOnEliminar(idReserva -> {
            if (idReserva == null || idReserva.isEmpty()) {
                return;
            }
            Reserva reserva = gestionReservas.buscarPorId(idReserva);
            if (reserva != null) {
                ventanas.dialogos.DialogoConfirmacion dialogo = 
                    new ventanas.dialogos.DialogoConfirmacion(
                        "Cancelar Reserva",
                        "¿Está seguro que desea cancelar esta reserva? Esta acción cancelará la reserva y liberará la habitación.",
                        "Reserva: " + reserva.getIdReserva() + " - " + reserva.getNombreHuesped(),
                        "info",
                        false // esEliminacion (título azul)
                    );
                dialogo.mostrar();
                if (dialogo.isConfirmado()) {
                    if (gestionReservas.eliminar(idReserva)) {
                        cargarDatosEnTabla();
                        actualizarBarraEstado();
                        ventanas.dialogos.DialogoMensaje dialogoExito = 
                            new ventanas.dialogos.DialogoMensaje("Operación Exitosa",
                                "La reserva ha sido cancelada correctamente. Se enviará notificación al huésped.",
                                ventanas.dialogos.DialogoMensaje.TipoMensaje.EXITO);
                        dialogoExito.mostrar();
                    }
                }
            }
        });
        
        // Cargar datos iniciales
        cargarDatosEnTabla();
        
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
    
    /** Refresca el desplegable de habitaciones desde el gestor (disponibles). Llamar al mostrar esta ventana. */
    public void actualizarComboHabitaciones() {
        if (cmbHabitacion == null) return;
        cmbHabitacion.getItems().clear();
        java.util.ArrayList<Habitacion> habitaciones = gestionHabitaciones.obtenerDisponibles();
        for (Habitacion h : habitaciones) {
            cmbHabitacion.getItems().add(h.getNumero() + " - " + h.getTipo());
        }
    }

    public void actualizarComboHuespedes() {
        if (cmbHuesped != null) {
            cmbHuesped.getItems().clear();
            String[] nombresHuespedes = gestionHuespedes.obtenerNombresCompletos();
            cmbHuesped.getItems().addAll(nombresHuespedes);
        }
    }
    
    /**
     * Configura listeners para actualizar el total dinámicamente
     */
    private void configurarListenersTotal() {
        // Listener para cambio de habitación
        cmbHabitacion.valueProperty().addListener((_, _, _) -> actualizarTotal());
        
        // Listener para cambio de fechas
        datePickerCheckIn.valueProperty().addListener((_, _, _) -> actualizarTotal());
        datePickerCheckOut.valueProperty().addListener((_, _, _) -> actualizarTotal());
        
        // Listener para cambio de número de huéspedes
        campoNumHuespedes.getCampo().textProperty().addListener((_, _, _) -> actualizarTotal());
        
        // Listeners para servicios adicionales
        chkDesayuno.selectedProperty().addListener((_, _, _) -> actualizarTotal());
        chkParking.selectedProperty().addListener((_, _, _) -> actualizarTotal());
        chkSpa.selectedProperty().addListener((_, _, _) -> actualizarTotal());
    }
    
    /**
     * Calcula y actualiza el total dinámicamente
     */
    private void actualizarTotal() {
        double total = 0.0;
        
        // Verificar que haya habitación seleccionada
        if (cmbHabitacion.getValue() == null || cmbHabitacion.getValue().isEmpty()) {
            lblTotalValor.setText("S/. 0.00");
            return;
        }
        
        // Obtener habitación seleccionada
        String numeroHabitacion = cmbHabitacion.getValue().split(" - ")[0];
        Habitacion habitacion = gestionHabitaciones.buscarPorNumero(numeroHabitacion);
        
        if (habitacion == null) {
            lblTotalValor.setText("S/. 0.00");
            return;
        }
        
        // Calcular número de noches
        LocalDate checkIn = datePickerCheckIn.getValue();
        LocalDate checkOut = datePickerCheckOut.getValue();
        
        if (checkIn == null || checkOut == null || checkOut.isBefore(checkIn) || checkOut.isEqual(checkIn)) {
            lblTotalValor.setText("S/. 0.00");
            return;
        }
        
        int numeroNoches = (int) java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);
        if (numeroNoches <= 0) {
            lblTotalValor.setText("S/. 0.00");
            return;
        }
        
        // Calcular precio base (habitación * noches)
        total = habitacion.getPrecioNoche() * numeroNoches;
        
        // Obtener número de huéspedes
        int numeroHuespedes = 1; // Por defecto
        try {
            String numHuespedesStr = campoNumHuespedes.getValor().trim();
            if (!numHuespedesStr.isEmpty()) {
                numeroHuespedes = Integer.parseInt(numHuespedesStr);
                if (numeroHuespedes <= 0) {
                    numeroHuespedes = 1;
                }
            }
        } catch (NumberFormatException e) {
            // Si no es un número válido, usar 1
            numeroHuespedes = 1;
        }
        
        // Agregar servicios adicionales
        if (chkDesayuno.isSelected()) {
            total += 10.00 * numeroNoches * numeroHuespedes;
        }
        if (chkParking.isSelected()) {
            total += 5.00 * numeroNoches;
        }
        if (chkSpa.isSelected()) {
            total += 20.00 * numeroNoches;
        }
        
        // Actualizar label con formato
        lblTotalValor.setText(String.format("S/. %.2f", total));
    }
    
    private void cargarDatosEnTabla() {
        // Limpiar tabla
        tablaReservas.limpiar();
        
        // Cargar datos desde la gestión
        java.util.ArrayList<Reserva> reservas = gestionReservas.obtenerTodas();
        for (Reserva r : reservas) {
            tablaReservas.agregarFila(new String[]{
                r.getIdReserva(),
                r.getNombreHuesped(),
                r.getNumeroHabitacion(),
                r.obtenerCheckInFormateado(),
                r.obtenerCheckOutFormateado(),
                r.getEstado(),
                ""
            });
        }
    }
}
