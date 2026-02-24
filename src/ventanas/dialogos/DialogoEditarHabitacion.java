package ventanas.dialogos;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import utilidades.Constantes;

/**
 * Diálogo para editar una habitación.
 * Validaciones: número y piso solo dígitos, precio solo numérico; validación al guardar.
 */
public class DialogoEditarHabitacion extends DialogoBase {

    private TextField txtNumero;
    private TextField txtPiso;
    private TextField txtPrecio;
    private ComboBox<String> comboTipo;
    private ComboBox<String> comboCapacidad;
    private ComboBox<String> comboEstado;

    public DialogoEditarHabitacion(String numero, String tipo, String piso, 
                                   String capacidad, String precio, String estado, String amenidades) {
        super("Editar Habitación", Color.rgb(245, 158, 11), 520, 0); // #F59E0B, un poco más ancho
        
        content.setSpacing(10);
        content.setVisible(true);
        content.setManaged(true);

        // Fila 1: Número (solo dígitos)
        txtNumero = crearCampoConEstilo(numero != null ? numero : "", 80);
        aplicarFormatoSoloNumeros(txtNumero, 10);
        content.getChildren().add(crearFilaCampo("Número *:", txtNumero));

        // Fila 2: Tipo (ComboBox)
        HBox fila2 = new HBox(16);
        fila2.setAlignment(Pos.CENTER_LEFT);
        Label lblTipo = new Label("Tipo *:");
        lblTipo.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblTipo.setTextFill(Color.rgb(0, 0, 0));
        lblTipo.setStyle("-fx-text-fill: #000000;");
        lblTipo.setWrapText(false);
        comboTipo = new ComboBox<>();
        java.util.List<String> nombresTipos = java.util.Arrays.asList(
            utilidades.GestorDatos.getInstancia().getGestionTiposHabitacion().obtenerNombresTipos());
        comboTipo.getItems().setAll(nombresTipos);
        if (tipo != null && !tipo.isEmpty() && nombresTipos.contains(tipo)) {
            comboTipo.setValue(tipo);
        } else if (!nombresTipos.isEmpty()) {
            comboTipo.setValue(nombresTipos.get(0));
        }
        comboTipo.setPrefWidth(180);
        comboTipo.setPrefHeight(24);
        comboTipo.setMinHeight(24);
        comboTipo.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(comboTipo, "Seleccionar tipo");
        fila2.getChildren().addAll(lblTipo, comboTipo);
        content.getChildren().add(fila2);

        // Fila 3: Piso (solo dígitos)
        txtPiso = crearCampoConEstilo(piso != null ? piso : "", 80);
        aplicarFormatoSoloNumeros(txtPiso, 3);
        content.getChildren().add(crearFilaCampo("Piso *:", txtPiso));

        // Fila 4: Capacidad
        HBox filaCapacidad = new HBox(16);
        filaCapacidad.setAlignment(Pos.CENTER_LEFT);
        Label lblCapacidad = new Label("Capacidad *:");
        lblCapacidad.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblCapacidad.setTextFill(Color.rgb(0, 0, 0));
        lblCapacidad.setStyle("-fx-text-fill: #000000;");
        comboCapacidad = new ComboBox<>();
        comboCapacidad.getItems().addAll("1", "2", "3", "4", "5", "6");
        if (capacidad != null && !capacidad.isEmpty()) {
            String capNum = capacidad.replaceAll("\\D+", "");
            comboCapacidad.setValue(capNum.isEmpty() ? "1" : capNum);
        } else {
            comboCapacidad.setValue("1");
        }
        comboCapacidad.setPrefWidth(60);
        comboCapacidad.setPrefHeight(24);
        comboCapacidad.setMinHeight(24);
        comboCapacidad.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(comboCapacidad, "Seleccionar");
        filaCapacidad.getChildren().addAll(lblCapacidad, comboCapacidad);
        content.getChildren().add(filaCapacidad);

        // Fila 5: Precio/Noche (solo numérico)
        String precioLimpio = precio != null ? precio.replace("S/.", "").replace(",", ".").trim() : "";
        txtPrecio = crearCampoConEstilo(precioLimpio, 120);
        aplicarFormatoSoloNumerosDecimales(txtPrecio);
        content.getChildren().add(crearFilaCampo("Precio/Noche *:", txtPrecio));

        // Fila 6: Estado
        HBox fila6 = new HBox(16);
        fila6.setAlignment(Pos.CENTER_LEFT);
        Label lblEstado = new Label("Estado *:");
        lblEstado.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblEstado.setTextFill(Color.rgb(0, 0, 0));
        lblEstado.setStyle("-fx-text-fill: #000000;");
        comboEstado = new ComboBox<>();
        comboEstado.getItems().addAll("Disponible", "Ocupada", "Mantenimiento");
        comboEstado.setValue(estado != null ? estado : "Disponible");
        comboEstado.setPrefWidth(140);
        comboEstado.setPrefHeight(24);
        comboEstado.setMinHeight(24);
        comboEstado.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(comboEstado, "Seleccionar estado");
        fila6.getChildren().addAll(lblEstado, comboEstado);
        content.getChildren().add(fila6);
        
        // Fila 7: Amenidades (checkboxes)
        HBox fila7 = new HBox(16);
        fila7.setAlignment(Pos.CENTER_LEFT);
        Label lblAmenidades = new Label("Amenidades:");
        lblAmenidades.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblAmenidades.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lblAmenidades.setStyle("-fx-text-fill: #000000;");
        lblAmenidades.setWrapText(false);
        lblAmenidades.setVisible(true);
        lblAmenidades.setManaged(true);
        
        VBox panelCheckboxes = new VBox(6);
        panelCheckboxes.setAlignment(Pos.TOP_LEFT);
        CheckBox chkWifi = new CheckBox("WiFi");
        CheckBox chkTV = new CheckBox("TV Cable");
        CheckBox chkAC = new CheckBox("A/C");
        CheckBox chkMinibar = new CheckBox("Minibar");
        CheckBox chkJacuzzi = new CheckBox("Jacuzzi");
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
        
        // Parsear el string de amenidades para seleccionar los checkboxes correspondientes
        if (amenidades != null && !amenidades.isEmpty()) {
            String[] amenidadesArray = amenidades.split(",");
            for (String amenidad : amenidadesArray) {
                String amenidadTrim = amenidad.trim();
                if ("WiFi".equalsIgnoreCase(amenidadTrim)) {
                    chkWifi.setSelected(true);
                } else if ("TV Cable".equalsIgnoreCase(amenidadTrim)) {
                    chkTV.setSelected(true);
                } else if ("A/C".equalsIgnoreCase(amenidadTrim) || "AC".equalsIgnoreCase(amenidadTrim)) {
                    chkAC.setSelected(true);
                } else if ("Minibar".equalsIgnoreCase(amenidadTrim)) {
                    chkMinibar.setSelected(true);
                } else if ("Jacuzzi".equalsIgnoreCase(amenidadTrim)) {
                    chkJacuzzi.setSelected(true);
                }
            }
        }
        
        panelCheckboxes.getChildren().addAll(chkWifi, chkTV, chkAC, chkMinibar, chkJacuzzi);
        fila7.getChildren().addAll(lblAmenidades, panelCheckboxes);
        content.getChildren().add(fila7);
        
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
            String numeroVal = txtNumero.getText() != null ? txtNumero.getText().trim() : "";
            String tipoVal = comboTipo.getValue() != null ? comboTipo.getValue().trim() : "";
            String pisoVal = txtPiso.getText() != null ? txtPiso.getText().trim() : "";
            String precioStr = txtPrecio.getText() != null ? txtPrecio.getText().trim() : "";
            if (numeroVal.isEmpty() || tipoVal.isEmpty() || pisoVal.isEmpty() || precioStr.isEmpty()) {
                DialogoMensaje d = new DialogoMensaje("Error de Validación",
                    "Complete los campos obligatorios: Número, Tipo, Piso y Precio/Noche.", DialogoMensaje.TipoMensaje.ERROR);
                d.mostrar();
                return;
            }
            double precioVal;
            try {
                precioVal = Double.parseDouble(precioStr.replace(",", "."));
                if (precioVal <= 0) {
                    DialogoMensaje d = new DialogoMensaje("Error de Validación",
                        "El precio por noche debe ser mayor a 0.", DialogoMensaje.TipoMensaje.ERROR);
                    d.mostrar();
                    return;
                }
            } catch (NumberFormatException e) {
                DialogoMensaje d = new DialogoMensaje("Error de Validación",
                    "El precio debe ser un número válido.", DialogoMensaje.TipoMensaje.ERROR);
                d.mostrar();
                return;
            }
            // TODO: Implementar guardado (actualizar en gestor por número)
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
        TextField txt = new TextField(valor);
        txt.setPrefWidth(ancho);
        txt.setPrefHeight(24);
        txt.setMinHeight(24);
        txt.setMaxHeight(24);
        String colorBorde = Constantes.COLOR_BORDE_OSCURO.toString().substring(2, 8);
        String colorFondo = Constantes.COLOR_BLANCO.toString().substring(2, 8);
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

    private static void aplicarFormatoSoloNumeros(TextField campo, int maxDigitos) {
        campo.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) return change;
            if (!newText.matches("[0-9]*") || newText.length() > maxDigitos) return null;
            return change;
        }));
    }

    private static void aplicarFormatoSoloNumerosDecimales(TextField campo) {
        campo.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) return change;
            if (!newText.matches("[0-9]*\\.?[0-9]*")) return null;
            return change;
        }));
    }
}

