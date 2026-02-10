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
 * Diálogo para editar un tipo de habitación
 * Debe estar sincronizado con el formulario principal de TiposHabitacionWindow
 */
public class DialogoEditarTipoHabitacion extends DialogoBase {
    
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
                                      String precio, String descripcion) {
        // Ancho mayor para que labels como "Nombre del Tipo" no se corten
        super("Editar Tipo de Habitación", Color.rgb(245, 158, 11), 650, 0); // #F59E0B
        
        content.setSpacing(10);
        // No sobrescribir padding - usar el de DialogoBase (20)
        content.setVisible(true);
        content.setManaged(true);
        
        String colorBorde = Constantes.COLOR_BORDE_OSCURO.toString().substring(2, 8);
        String colorFondo = Constantes.COLOR_BLANCO.toString().substring(2, 8);
        
        // Campo: Código
        HBox filaCodigo = new HBox(16);
        filaCodigo.setAlignment(Pos.CENTER_LEFT);
        Label lblCodigo = new Label("Código:");
        lblCodigo.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblCodigo.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lblCodigo.setStyle("-fx-text-fill: #000000;");
        lblCodigo.setWrapText(false);
        lblCodigo.setVisible(true);
        lblCodigo.setManaged(true);
        txtCodigo = new TextField(codigo);
        txtCodigo.setPrefWidth(80);
        txtCodigo.setPrefHeight(24);
        txtCodigo.setMinHeight(24);
        txtCodigo.setMaxHeight(24);
        txtCodigo.setStyle("-fx-border-color: #" + colorBorde + "; -fx-background-color: #" + colorFondo + "; -fx-padding: 4 8 4 8; -fx-effect: null;");
        filaCodigo.getChildren().addAll(lblCodigo, txtCodigo);
        content.getChildren().add(filaCodigo);
        
        // Campo: Nombre del Tipo
        HBox filaNombre = new HBox(16);
        filaNombre.setAlignment(Pos.CENTER_LEFT);
        Label lblNombre = new Label("Nombre del Tipo:");
        lblNombre.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblNombre.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lblNombre.setStyle("-fx-text-fill: #000000;");
        lblNombre.setWrapText(false);
        lblNombre.setVisible(true);
        lblNombre.setManaged(true);
        txtNombre = new TextField(nombre);
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
        Label lblPrecio = new Label("Precio Base:");
        lblPrecio.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblPrecio.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lblPrecio.setStyle("-fx-text-fill: #000000;");
        lblPrecio.setWrapText(false);
        lblPrecio.setVisible(true);
        lblPrecio.setManaged(true);
        txtPrecio = new TextField(precio);
        txtPrecio.setPrefWidth(120);
        txtPrecio.setPrefHeight(24);
        txtPrecio.setMinHeight(24);
        txtPrecio.setMaxHeight(24);
        txtPrecio.setStyle("-fx-border-color: #" + colorBorde + "; -fx-background-color: #" + colorFondo + "; -fx-padding: 4 8 4 8; -fx-effect: null;");
        filaPrecio.getChildren().addAll(lblPrecio, txtPrecio);
        content.getChildren().add(filaPrecio);
        
        // Campo: Capacidad
        HBox filaCapacidad = new HBox(16);
        filaCapacidad.setAlignment(Pos.CENTER_LEFT);
        
        // Capacidad
        Label lblCapacidad = new Label("Capacidad:");
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
        Label lblCamas = new Label("Tipo de Camas:");
        lblCamas.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblCamas.setTextFill(Color.rgb(0, 0, 0));
        lblCamas.setStyle("-fx-text-fill: #000000;");
        lblCamas.setWrapText(false);
        lblCamas.setVisible(true);
        lblCamas.setManaged(true);
        cmbCamas = new ComboBox<>();
        cmbCamas.getItems().addAll("Individual", "Doble", "Queen", "King");
        cmbCamas.setValue("Doble");
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
        Label lblArea = new Label("Área (m²):");
        lblArea.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblArea.setTextFill(Color.rgb(0, 0, 0));
        lblArea.setStyle("-fx-text-fill: #000000;");
        lblArea.setWrapText(false);
        lblArea.setVisible(true);
        lblArea.setManaged(true);
        txtArea = new TextField("40");
        txtArea.setPrefWidth(80);
        txtArea.setPrefHeight(24);
        txtArea.setMinHeight(24);
        txtArea.setMaxHeight(24);
        txtArea.setStyle("-fx-border-color: #" + colorBorde + "; -fx-background-color: #" + colorFondo + "; -fx-padding: 4 8 4 8; -fx-effect: null;");
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
}

