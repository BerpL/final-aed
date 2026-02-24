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
import clases.GestionTiposHabitacion;
import clases.TipoHabitacion;

/**
 * Diálogo para editar un tipo de habitación.
 * Recibe área, tipoCamas y amenidades; guarda cambios en GestionTiposHabitacion.
 */
public class DialogoEditarTipoHabitacion extends DialogoBase {

    private final String codigoOriginal;
    private TextField txtCodigo;
    private TextField txtNombre;
    private ComboBox<String> cmbCapacidad;
    private ComboBox<String> cmbCamas;
    private TextField txtArea;
    private TextField txtPrecio;
    private CheckBox chkWifi;
    private CheckBox chkTV;
    private CheckBox chkAC;
    private CheckBox chkMinibar;
    private CheckBox chkJacuzzi;

    public DialogoEditarTipoHabitacion(String codigo, String nombre, String capacidad,
                                      String precio, String descripcion, String area, String tipoCamas, String amenidades) {
        super("Editar Tipo de Habitación", Color.rgb(245, 158, 11), 650, 0);
        codigoOriginal = codigo != null ? codigo : "";

        content.setSpacing(10);
        // No sobrescribir padding - usar el de DialogoBase (20)
        content.setVisible(true);
        content.setManaged(true);
        
        String colorBorde = Constantes.COLOR_BORDE_OSCURO.toString().substring(2, 8);
        String colorFondo = Constantes.COLOR_BLANCO.toString().substring(2, 8);
        
        // Campo: Código (texto libre)
        HBox filaCodigo = new HBox(16);
        filaCodigo.setAlignment(Pos.CENTER_LEFT);
        Label lblCodigo = new Label("Código *:");
        lblCodigo.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblCodigo.setTextFill(Color.rgb(0, 0, 0));
        lblCodigo.setStyle("-fx-text-fill: #000000;");
        lblCodigo.setWrapText(false);
        txtCodigo = new TextField(codigo != null ? codigo.toUpperCase() : "");
        txtCodigo.setTextFormatter(new TextFormatter<>(change -> {
            change.setText(change.getText().toUpperCase());
            return change;
        }));
        txtCodigo.setPrefWidth(140);
        txtCodigo.setPrefHeight(24);
        txtCodigo.setMinHeight(24);
        txtCodigo.setMaxHeight(24);
        txtCodigo.setStyle("-fx-border-color: #" + colorBorde + "; -fx-background-color: #" + colorFondo + "; -fx-padding: 4 8 4 8; -fx-effect: null;");
        filaCodigo.getChildren().addAll(lblCodigo, txtCodigo);
        content.getChildren().add(filaCodigo);

        // Campo: Nombre del Tipo (texto libre)
        HBox filaNombre = new HBox(16);
        filaNombre.setAlignment(Pos.CENTER_LEFT);
        Label lblNombre = new Label("Nombre del Tipo *:");
        lblNombre.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblNombre.setTextFill(Color.rgb(0, 0, 0));
        lblNombre.setStyle("-fx-text-fill: #000000;");
        lblNombre.setWrapText(false);
        txtNombre = new TextField(nombre != null ? nombre : "");
        txtNombre.setPrefWidth(200);
        txtNombre.setPrefHeight(24);
        txtNombre.setMinHeight(24);
        txtNombre.setMaxHeight(24);
        txtNombre.setStyle("-fx-border-color: #" + colorBorde + "; -fx-background-color: #" + colorFondo + "; -fx-padding: 4 8 4 8; -fx-effect: null;");
        filaNombre.getChildren().addAll(lblNombre, txtNombre);
        content.getChildren().add(filaNombre);
        
        // Campo: Precio Base
        HBox filaPrecio = new HBox(16);
        filaPrecio.setAlignment(Pos.CENTER_LEFT);
        Label lblPrecio = new Label("Precio Base *:");
        lblPrecio.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblPrecio.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lblPrecio.setStyle("-fx-text-fill: #000000;");
        lblPrecio.setWrapText(false);
        lblPrecio.setVisible(true);
        lblPrecio.setManaged(true);
        String precioLimpio = precio != null ? precio.replace("S/.", "").replace(",", ".").trim() : "";
        txtPrecio = new TextField(precioLimpio);
        txtPrecio.setPrefWidth(120);
        txtPrecio.setPrefHeight(24);
        txtPrecio.setMinHeight(24);
        txtPrecio.setMaxHeight(24);
        txtPrecio.setStyle("-fx-border-color: #" + colorBorde + "; -fx-background-color: #" + colorFondo + "; -fx-padding: 4 8 4 8; -fx-effect: null;");
        aplicarFormatoSoloNumeros(txtPrecio);
        filaPrecio.getChildren().addAll(lblPrecio, txtPrecio);
        content.getChildren().add(filaPrecio);
        
        // Campo: Capacidad
        HBox filaCapacidad = new HBox(16);
        filaCapacidad.setAlignment(Pos.CENTER_LEFT);
        
        // Capacidad
        Label lblCapacidad = new Label("Capacidad *:");
        lblCapacidad.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblCapacidad.setTextFill(Color.rgb(0, 0, 0));
        lblCapacidad.setStyle("-fx-text-fill: #000000;");
        lblCapacidad.setWrapText(false);
        lblCapacidad.setVisible(true);
        lblCapacidad.setManaged(true);
        cmbCapacidad = new ComboBox<>();
        cmbCapacidad.getItems().addAll("1", "2", "3", "4", "5", "6");
        // Intentar usar la capacidad recibida, si es numérica
        if (capacidad != null && !capacidad.isEmpty()) {
            String capSoloNumero = capacidad.replaceAll("\\D+", "");
            if (!capSoloNumero.isEmpty() && cmbCapacidad.getItems().contains(capSoloNumero)) {
                cmbCapacidad.setValue(capSoloNumero);
            }
        }
        if (cmbCapacidad.getValue() == null) {
            cmbCapacidad.setValue("1");
        }
        cmbCapacidad.setPrefWidth(80);
        cmbCapacidad.setPrefHeight(24);
        cmbCapacidad.setMinHeight(24);
        cmbCapacidad.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(cmbCapacidad, "Seleccionar");
        filaCapacidad.getChildren().addAll(lblCapacidad, cmbCapacidad);
        content.getChildren().add(filaCapacidad);
        
        // Campo: Tipo de Camas
        HBox filaCamas = new HBox(16);
        filaCamas.setAlignment(Pos.CENTER_LEFT);
        Label lblCamas = new Label("Tipo de Camas *:");
        lblCamas.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblCamas.setTextFill(Color.rgb(0, 0, 0));
        lblCamas.setStyle("-fx-text-fill: #000000;");
        lblCamas.setWrapText(false);
        lblCamas.setVisible(true);
        lblCamas.setManaged(true);
        cmbCamas = new ComboBox<>();
        cmbCamas.getItems().addAll("Individual", "Doble", "Queen", "King");
        if (tipoCamas != null && !tipoCamas.isEmpty() && cmbCamas.getItems().contains(tipoCamas)) {
            cmbCamas.setValue(tipoCamas);
        } else {
            cmbCamas.setValue("Doble");
        }
        cmbCamas.setPrefWidth(150);
        cmbCamas.setPrefHeight(24);
        cmbCamas.setMinHeight(24);
        cmbCamas.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(cmbCamas, "Seleccionar tipo");
        filaCamas.getChildren().addAll(lblCamas, cmbCamas);
        content.getChildren().add(filaCamas);
        
        // Campo: Área
        HBox filaArea = new HBox(16);
        filaArea.setAlignment(Pos.CENTER_LEFT);
        Label lblArea = new Label("Área (m²) *:");
        lblArea.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblArea.setTextFill(Color.rgb(0, 0, 0));
        lblArea.setStyle("-fx-text-fill: #000000;");
        lblArea.setWrapText(false);
        lblArea.setVisible(true);
        lblArea.setManaged(true);
        String areaStr = (area != null && !area.isEmpty()) ? area.replace(",", ".").trim() : "";
        txtArea = new TextField(areaStr);
        txtArea.setPrefWidth(80);
        txtArea.setPrefHeight(24);
        txtArea.setMinHeight(24);
        txtArea.setMaxHeight(24);
        txtArea.setStyle("-fx-border-color: #" + colorBorde + "; -fx-background-color: #" + colorFondo + "; -fx-padding: 4 8 4 8; -fx-effect: null;");
        aplicarFormatoSoloNumeros(txtArea);
        filaArea.getChildren().addAll(lblArea, txtArea);
        content.getChildren().add(filaArea);
        
        // Campo: Amenidades Incluidas (checkboxes uno debajo del otro)
        HBox filaAmenidades = new HBox(16);
        filaAmenidades.setAlignment(Pos.TOP_LEFT);
        
        Label lblAmenidades = new Label("Amenidades Incluidas:");
        lblAmenidades.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblAmenidades.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lblAmenidades.setStyle("-fx-text-fill: #000000;");
        lblAmenidades.setWrapText(false);
        lblAmenidades.setVisible(true);
        lblAmenidades.setManaged(true);
        
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
        if (amenidades != null && !amenidades.isEmpty()) {
            String[] parts = amenidades.split(",");
            for (String p : parts) {
                String a = p.trim();
                if ("WiFi".equalsIgnoreCase(a)) chkWifi.setSelected(true);
                else if ("TV Cable".equalsIgnoreCase(a)) chkTV.setSelected(true);
                else if ("A/C".equalsIgnoreCase(a) || "AC".equalsIgnoreCase(a)) chkAC.setSelected(true);
                else if ("Minibar".equalsIgnoreCase(a)) chkMinibar.setSelected(true);
                else if ("Jacuzzi".equalsIgnoreCase(a)) chkJacuzzi.setSelected(true);
            }
        }
        // Panel vertical para las amenidades
        javafx.scene.layout.VBox panelAmenidades = new javafx.scene.layout.VBox(4);
        panelAmenidades.setAlignment(Pos.TOP_LEFT);
        panelAmenidades.getChildren().addAll(chkWifi, chkTV, chkAC, chkMinibar, chkJacuzzi);
        
        filaAmenidades.getChildren().addAll(lblAmenidades, panelAmenidades);
        content.getChildren().add(filaAmenidades);
        
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
            String codigoSel = txtCodigo.getText() != null ? txtCodigo.getText().trim() : "";
            String nombreVal = txtNombre.getText() != null ? txtNombre.getText().trim() : "";
            String precioStr = txtPrecio.getText() != null ? txtPrecio.getText().trim() : "";
            String areaTexto = txtArea.getText() != null ? txtArea.getText().trim() : "";
            if (codigoSel.isEmpty() || nombreVal.isEmpty() || precioStr.isEmpty() || areaTexto.isEmpty()) {
                DialogoMensaje d = new DialogoMensaje("Error de Validación",
                    "Complete todos los campos obligatorios.", DialogoMensaje.TipoMensaje.ERROR);
                d.mostrar();
                return;
            }
            double precioVal;
            double areaVal;
            try {
                precioVal = Double.parseDouble(precioStr.replace(",", "."));
                areaVal = Double.parseDouble(areaTexto.replace(",", "."));
                if (precioVal <= 0) {
                    DialogoMensaje d = new DialogoMensaje("Error de Validación",
                        "El precio base debe ser mayor a 0.", DialogoMensaje.TipoMensaje.ERROR);
                    d.mostrar();
                    return;
                }
                if (areaVal < 0) {
                    DialogoMensaje d = new DialogoMensaje("Error de Validación",
                        "El área no puede ser negativa.", DialogoMensaje.TipoMensaje.ERROR);
                    d.mostrar();
                    return;
                }
            } catch (NumberFormatException e) {
                DialogoMensaje d = new DialogoMensaje("Error de Validación",
                    "El precio base y el área deben ser números válidos.", DialogoMensaje.TipoMensaje.ERROR);
                d.mostrar();
                return;
            }
            String codigoForm = txtCodigo.getText() != null ? txtCodigo.getText().trim().toUpperCase() : "";
            String nombreForm = txtNombre.getText() != null ? txtNombre.getText().trim() : "";
            String capacidadForm = cmbCapacidad.getValue() != null ? cmbCapacidad.getValue() : "1";
            String tipoCamasForm = cmbCamas.getValue() != null ? cmbCamas.getValue() : "Doble";
            GestionTiposHabitacion gestor = utilidades.GestorDatos.getInstancia().getGestionTiposHabitacion();
            TipoHabitacion tipoExistente = gestor.buscarPorCodigo(codigoOriginal);
            if (tipoExistente == null) {
                DialogoMensaje d = new DialogoMensaje("Error", "No se encontró el tipo de habitación a editar.", DialogoMensaje.TipoMensaje.ERROR);
                d.mostrar();
                return;
            }
            TipoHabitacion actualizado = new TipoHabitacion();
            actualizado.setCodigo(codigoForm);
            actualizado.setNombre(nombreForm);
            actualizado.setPrecioBase(precioVal);
            actualizado.setCapacidad(Integer.parseInt(capacidadForm));
            actualizado.setTipoCamas(tipoCamasForm);
            actualizado.setArea(areaVal);
            actualizado.setWifi(chkWifi.isSelected());
            actualizado.setTv(chkTV.isSelected());
            actualizado.setAc(chkAC.isSelected());
            actualizado.setMinibar(chkMinibar.isSelected());
            actualizado.setJacuzzi(chkJacuzzi.isSelected());
            actualizado.setDescripcion(tipoExistente.getDescripcion() != null ? tipoExistente.getDescripcion() : "");
            boolean ok;
            if (codigoForm.equals(codigoOriginal)) {
                ok = gestor.actualizar(actualizado);
            } else {
                if (gestor.buscarPorCodigo(codigoForm) != null) {
                    DialogoMensaje d = new DialogoMensaje("Error de Validación",
                        "Ya existe otro tipo con el código: " + codigoForm, DialogoMensaje.TipoMensaje.ERROR);
                    d.mostrar();
                    return;
                }
                gestor.eliminar(codigoOriginal);
                ok = gestor.agregar(actualizado);
            }
            if (ok) {
                DialogoMensaje d = new DialogoMensaje("Operación Exitosa", "El tipo de habitación se actualizó correctamente.", DialogoMensaje.TipoMensaje.EXITO);
                d.mostrar();
                cerrar();
            } else {
                DialogoMensaje d = new DialogoMensaje("Error", "No se pudo actualizar el tipo de habitación.", DialogoMensaje.TipoMensaje.ERROR);
                d.mostrar();
            }
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
    
    private static void aplicarFormatoSoloNumeros(TextField campo) {
        campo.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) return change;
            if (!newText.matches("[0-9]*\\.?[0-9]*")) return null;
            return change;
        }));
    }
}

