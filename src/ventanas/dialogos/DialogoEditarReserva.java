package ventanas.dialogos;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import utilidades.Constantes;

/**
 * Diálogo para editar una reserva. Validaciones y campos obligatorios al guardar.
 */
public class DialogoEditarReserva extends DialogoBase {

    private ComboBox<String> cmbHuesped;
    private ComboBox<String> cmbHabitacion;
    private TextField txtCheckIn;
    private TextField txtCheckOut;
    private TextField txtNroHuespedes;
    private ComboBox<String> cmbEstado;
    private ComboBox<String> cmbTipoPago;

    public DialogoEditarReserva(String huesped, String habitacion, String checkIn,
                               String checkOut, String nroHuespedes, String estado, String observaciones) {
        super("Editar Reserva", Color.rgb(245, 158, 11), 600, 0);

        String colorBorde = Constantes.COLOR_BORDE_OSCURO.toString().substring(2, 8);
        String colorFondo = Constantes.COLOR_BLANCO.toString().substring(2, 8);

        content.setSpacing(10);
        content.setVisible(true);
        content.setManaged(true);

        // Fila 1: Huésped *
        cmbHuesped = new ComboBox<>();
        if (huesped != null && !huesped.isEmpty()) {
            cmbHuesped.getItems().add(huesped);
            cmbHuesped.setValue(huesped);
        }
        cmbHuesped.setPrefWidth(200);
        cmbHuesped.setPrefHeight(24);
        cmbHuesped.setMinHeight(24);
        cmbHuesped.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(cmbHuesped, "Seleccionar huésped");
        content.getChildren().add(crearFilaCombo("Huésped *:", cmbHuesped));

        // Fila 2: Habitación *
        cmbHabitacion = new ComboBox<>();
        if (habitacion != null && !habitacion.isEmpty()) {
            cmbHabitacion.getItems().add(habitacion);
            cmbHabitacion.setValue(habitacion);
        }
        cmbHabitacion.setPrefWidth(200);
        cmbHabitacion.setPrefHeight(24);
        cmbHabitacion.setMinHeight(24);
        cmbHabitacion.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(cmbHabitacion, "Seleccionar habitación");
        content.getChildren().add(crearFilaCombo("Habitación *:", cmbHabitacion));

        // Fila 3: Check-In * (fecha dd/MM/yyyy)
        txtCheckIn = crearCampoConEstilo(checkIn != null ? checkIn : "", 140);
        content.getChildren().add(crearFilaCampo("Check-In *:", txtCheckIn));

        // Fila 4: Check-Out *
        txtCheckOut = crearCampoConEstilo(checkOut != null ? checkOut : "", 140);
        content.getChildren().add(crearFilaCampo("Check-Out *:", txtCheckOut));

        // Fila 5: Nro. Huéspedes *
        txtNroHuespedes = crearCampoConEstilo(nroHuespedes != null ? nroHuespedes : "", 80);
        txtNroHuespedes.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) return change;
            if (!newText.matches("[0-9]*") || newText.length() > 2) return null;
            return change;
        }));
        content.getChildren().add(crearFilaCampo("Nro. Huéspedes *:", txtNroHuespedes));

        // Fila 6: Estado *
        cmbEstado = new ComboBox<>();
        cmbEstado.getItems().addAll("Pendiente", "Confirmada", "Completada", "Cancelada");
        cmbEstado.setValue(estado != null && !estado.isEmpty() ? estado : "Pendiente");
        cmbEstado.setPrefWidth(140);
        cmbEstado.setPrefHeight(24);
        cmbEstado.setMinHeight(24);
        cmbEstado.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(cmbEstado, "Seleccionar estado");
        content.getChildren().add(crearFilaCombo("Estado *:", cmbEstado));

        // Fila 7: Forma de Pago *
        HBox filaTipoPago = new HBox(16);
        filaTipoPago.setAlignment(Pos.CENTER_LEFT);
        Label lblTipoPago = new Label("Forma de Pago *:");
        lblTipoPago.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblTipoPago.setTextFill(Color.rgb(0, 0, 0));
        lblTipoPago.setStyle("-fx-text-fill: #000000;");
        lblTipoPago.setWrapText(false);
        cmbTipoPago = new ComboBox<>();
        cmbTipoPago.getItems().addAll("Tarjeta Crédito", "Efectivo", "Transferencia");
        cmbTipoPago.setValue("Efectivo");
        cmbTipoPago.setPrefWidth(200);
        cmbTipoPago.setPrefHeight(24);
        cmbTipoPago.setMinHeight(24);
        cmbTipoPago.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(cmbTipoPago, "Seleccionar forma de pago");
        filaTipoPago.getChildren().addAll(lblTipoPago, cmbTipoPago);
        content.getChildren().add(filaTipoPago);
        
        // Fila 8: Total (solo lectura, igual que formulario principal)
        HBox filaTotal = new HBox(16);
        filaTotal.setAlignment(Pos.CENTER_LEFT);
        Label lblTotal = new Label("Total:");
        lblTotal.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblTotal.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lblTotal.setStyle("-fx-text-fill: #000000;");
        lblTotal.setWrapText(false);
        lblTotal.setVisible(true);
        lblTotal.setManaged(true);
        TextField txtTotal = new TextField("S/. 0.00");
        txtTotal.setEditable(false);
        txtTotal.setPrefWidth(150);
        txtTotal.setPrefHeight(24);
        txtTotal.setMinHeight(24);
        txtTotal.setMaxHeight(24);
        txtTotal.setStyle("-fx-border-color: #" + colorBorde + "; -fx-background-color: #" + colorFondo + "; -fx-padding: 4 8 4 8; -fx-effect: null;");
        filaTotal.getChildren().addAll(lblTotal, txtTotal);
        content.getChildren().add(filaTotal);
        
        // Fila 9: Servicios Adicionales (igual que formulario principal)
        HBox filaServicios = new HBox(16);
        filaServicios.setAlignment(Pos.CENTER_LEFT);
        Label lblServicios = new Label("Servicios Adicionales:");
        lblServicios.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblServicios.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lblServicios.setStyle("-fx-text-fill: #000000;");
        lblServicios.setWrapText(false);
        lblServicios.setVisible(true);
        lblServicios.setManaged(true);
        CheckBox chkDesayuno = new CheckBox("Desayuno (S/.10.00)");
        CheckBox chkParking = new CheckBox("Parking (S/.5.00)");
        CheckBox chkSpa = new CheckBox("Spa (S/.20.00)");
        utilidades.EstiloCheckBox.aplicarEstilo(chkDesayuno);
        utilidades.EstiloCheckBox.aplicarEstilo(chkParking);
        utilidades.EstiloCheckBox.aplicarEstilo(chkSpa);
        filaServicios.getChildren().addAll(lblServicios, chkDesayuno, chkParking, chkSpa);
        content.getChildren().add(filaServicios);
        
        // Botones - usar padding de DialogoBase, solo configurar alignment y spacing
        buttons.setAlignment(Pos.CENTER_RIGHT);
        buttons.setSpacing(10);
        
        Button btnGuardar = new Button("Guardar");
        btnGuardar.setFont(javafx.scene.text.Font.font("Arial", 11));
        btnGuardar.setStyle("-fx-background-color: #" + Constantes.COLOR_PRIMARIO.toString().substring(2, 8) + 
                           "; -fx-text-fill: #FFFFFF; " +
                           "-fx-border-color: #" + Constantes.COLOR_PRIMARIO_OSCURO.toString().substring(2, 8) + 
                           "; -fx-border-width: 1; " +
                           "-fx-border-radius: 2; " +
                           "-fx-background-radius: 2; " +
                           "-fx-padding: 6 20 6 20; " +
                           "-fx-cursor: hand;");
        btnGuardar.setOnAction(_ -> {
            String valHuesped = cmbHuesped.getValue() != null ? cmbHuesped.getValue().trim() : "";
            String valHabitacion = cmbHabitacion.getValue() != null ? cmbHabitacion.getValue().trim() : "";
            String valCheckIn = txtCheckIn.getText() != null ? txtCheckIn.getText().trim() : "";
            String valCheckOut = txtCheckOut.getText() != null ? txtCheckOut.getText().trim() : "";
            String valNro = txtNroHuespedes.getText() != null ? txtNroHuespedes.getText().trim() : "";
            String valEstado = cmbEstado.getValue() != null ? cmbEstado.getValue().trim() : "";
            String valPago = cmbTipoPago.getValue() != null ? cmbTipoPago.getValue().trim() : "";
            if (valHuesped.isEmpty() || valHabitacion.isEmpty() || valCheckIn.isEmpty() || valCheckOut.isEmpty()
                || valNro.isEmpty() || valEstado.isEmpty() || valPago.isEmpty()) {
                DialogoMensaje d = new DialogoMensaje("Error de Validación",
                    "Complete todos los campos obligatorios: Huésped, Habitación, Check-In, Check-Out, Nro. Huéspedes, Estado y Forma de Pago.",
                    DialogoMensaje.TipoMensaje.ERROR);
                d.mostrar();
                return;
            }
            int nro;
            try {
                nro = Integer.parseInt(valNro);
                if (nro < 1) {
                    DialogoMensaje d = new DialogoMensaje("Error de Validación",
                        "El número de huéspedes debe ser al menos 1.", DialogoMensaje.TipoMensaje.ERROR);
                    d.mostrar();
                    return;
                }
            } catch (NumberFormatException e) {
                DialogoMensaje d = new DialogoMensaje("Error de Validación",
                    "El número de huéspedes debe ser un número válido.", DialogoMensaje.TipoMensaje.ERROR);
                d.mostrar();
                return;
            }
            // TODO: actualizar reserva en gestor
            cerrar();
        });
        
        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setFont(javafx.scene.text.Font.font("Arial", 11));
        btnCancelar.setStyle("-fx-background-color: #F0F0F0; " +
                           "-fx-text-fill: #000000; " +
                           "-fx-border-color: #A0A0A0; " +
                           "-fx-border-width: 1; " +
                           "-fx-border-radius: 2; " +
                           "-fx-background-radius: 2; " +
                           "-fx-padding: 6 20 6 20; " +
                           "-fx-cursor: hand;");
        btnCancelar.setOnAction(_ -> cerrar());
        
        buttons.getChildren().addAll(btnGuardar, btnCancelar);
        
        // Asegurar que el diálogo ajuste su altura dinámicamente según el contenido
        root.setPrefHeight(javafx.scene.layout.Region.USE_COMPUTED_SIZE);
    }
    
    private TextField crearCampoConEstilo(String valor, int ancho) {
        String colorBorde = Constantes.COLOR_BORDE_OSCURO.toString().substring(2, 8);
        String colorFondo = Constantes.COLOR_BLANCO.toString().substring(2, 8);
        TextField txt = new TextField(valor);
        txt.setPrefWidth(ancho);
        txt.setPrefHeight(24);
        txt.setMinHeight(24);
        txt.setMaxHeight(24);
        txt.setStyle("-fx-border-color: #" + colorBorde + "; -fx-background-color: #" + colorFondo + "; -fx-padding: 4 8 4 8; -fx-effect: null;");
        return txt;
    }

    private HBox crearFilaCampo(String etiqueta, TextField txt) {
        HBox fila = new HBox(16);
        fila.setAlignment(Pos.CENTER_LEFT);
        Label lbl = new Label(etiqueta);
        lbl.setFont(javafx.scene.text.Font.font("Arial", 11));
        lbl.setTextFill(Color.rgb(0, 0, 0));
        lbl.setStyle("-fx-text-fill: #000000;");
        lbl.setWrapText(false);
        fila.getChildren().addAll(lbl, txt);
        return fila;
    }

    private HBox crearFilaCombo(String etiqueta, ComboBox<String> combo) {
        HBox fila = new HBox(16);
        fila.setAlignment(Pos.CENTER_LEFT);
        Label lbl = new Label(etiqueta);
        lbl.setFont(javafx.scene.text.Font.font("Arial", 11));
        lbl.setTextFill(Color.rgb(0, 0, 0));
        lbl.setStyle("-fx-text-fill: #000000;");
        lbl.setWrapText(false);
        fila.getChildren().addAll(lbl, combo);
        return fila;
    }
}

