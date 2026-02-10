package ventanas.dialogos;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import utilidades.Constantes;

/**
 * Diálogo para editar una reserva
 * Diseño exacto según aed.pen (versión simplificada)
 */
public class DialogoEditarReserva extends DialogoBase {
    
    public DialogoEditarReserva(String huesped, String habitacion, String checkIn, 
                               String checkOut, String nroHuespedes, String estado, String observaciones) {
        super("Editar Reserva", Color.rgb(245, 158, 11), 600, 0); // #F59E0B, más ancho para labels
        
        // Definir colores para usar en los inputs
        String colorBorde = Constantes.COLOR_BORDE_OSCURO.toString().substring(2, 8);
        String colorFondo = Constantes.COLOR_BLANCO.toString().substring(2, 8);
        
        content.setSpacing(10);
        // No sobrescribir padding - usar el de DialogoBase (20)
        content.setVisible(true);
        content.setManaged(true);
        
        // Fila 1: Huésped
        agregarCombo("Huésped:", huesped, 200);
        
        // Fila 2: Habitación
        agregarCombo("Habitación:", habitacion, 200);
        
        // Fila 3: Check-In
        agregarCampo("Check-In:", checkIn, 140);
        
        // Fila 4: Check-Out
        agregarCampo("Check-Out:", checkOut, 140);
        
        // Fila 5: Nro. Huéspedes
        agregarCampo("Nro. Huéspedes:", nroHuespedes, 80);
        
        // Fila 6: Estado (campo adicional, se mantiene)
        agregarCombo("Estado:", estado, 140);
        
        // Fila 7: Forma de Pago (igual que formulario principal)
        HBox filaTipoPago = new HBox(16);
        filaTipoPago.setAlignment(Pos.CENTER_LEFT);
        Label lblTipoPago = new Label("Forma de Pago:");
        lblTipoPago.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblTipoPago.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lblTipoPago.setStyle("-fx-text-fill: #000000;");
        lblTipoPago.setWrapText(false);
        lblTipoPago.setVisible(true);
        lblTipoPago.setManaged(true);
        ComboBox<String> cmbTipoPago = new ComboBox<>();
        cmbTipoPago.getItems().addAll("Tarjeta Crédito", "Efectivo", "Transferencia");
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
            // TODO: Implementar guardado
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
    
    private void agregarCampo(String etiqueta, String valor, int ancho) {
        HBox fila = new HBox(16);
        fila.setAlignment(Pos.CENTER_LEFT);
        Label lbl = new Label(etiqueta);
        lbl.setFont(javafx.scene.text.Font.font("Arial", 11));
        lbl.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lbl.setStyle("-fx-text-fill: #000000;");
        lbl.setWrapText(false);
        lbl.setVisible(true);
        lbl.setManaged(true);
        TextField txt = new TextField(valor);
        txt.setPrefWidth(ancho);
        txt.setPrefHeight(24);
        txt.setMinHeight(24);
        txt.setMaxHeight(24);
        String colorBorde = Constantes.COLOR_BORDE_OSCURO.toString().substring(2, 8);
        String colorFondo = Constantes.COLOR_BLANCO.toString().substring(2, 8);
        txt.setStyle("-fx-border-color: #" + colorBorde + "; -fx-background-color: #" + colorFondo + "; -fx-padding: 4 8 4 8; -fx-effect: null;");
        fila.getChildren().addAll(lbl, txt);
        content.getChildren().add(fila);
    }
    
    private void agregarCombo(String etiqueta, String valor, int ancho) {
        HBox fila = new HBox(16);
        fila.setAlignment(Pos.CENTER_LEFT);
        Label lbl = new Label(etiqueta);
        lbl.setFont(javafx.scene.text.Font.font("Arial", 11));
        lbl.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lbl.setStyle("-fx-text-fill: #000000;");
        lbl.setWrapText(false);
        lbl.setVisible(true);
        lbl.setManaged(true);
        ComboBox<String> combo = new ComboBox<>();
        combo.setValue(valor);
        combo.setPrefWidth(ancho);
        combo.setPrefHeight(24);
        combo.setMinHeight(24);
        combo.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(combo, "Seleccionar");
        fila.getChildren().addAll(lbl, combo);
        content.getChildren().add(fila);
    }
}

