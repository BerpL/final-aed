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
 * Diálogo para editar un huésped
 * Diseño exacto según aed.pen
 */
public class DialogoEditarHuesped extends DialogoBase {
    
    private TextField txtDni;
    private TextField txtNombres;
    private TextField txtApellidos;
    private TextField txtTelefono;
    private TextField txtEmail;
    private TextField txtDireccion;
    private TextField txtFechaNac;
    private ComboBox<String> comboTipoDoc;
    private ComboBox<String> comboGenero;
    private ComboBox<String> comboNacionalidad;
    private CheckBox checkVip;
    private CheckBox checkNewsletter;
    
    public DialogoEditarHuesped(String dni, String nombres, String apellidos, 
                               String telefono, String email, String nacionalidad, boolean vip) {
        super("Editar Huésped", Color.rgb(245, 158, 11), 520, 0); // #F59E0B, un poco más ancho para labels
        
        content.setSpacing(10);
        // No sobrescribir padding - usar el de DialogoBase (20)
        content.setVisible(true);
        content.setManaged(true);
        
        // Campo: DNI (igual que formulario principal)
        HBox filaDni = new HBox(16);
        filaDni.setAlignment(Pos.CENTER_LEFT);
        Label lblDni = new Label("DNI:");
        lblDni.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblDni.setTextFill(Color.rgb(51, 51, 51)); // #333333
        lblDni.setStyle("-fx-text-fill: #333333; -fx-font-weight: 500;");
        lblDni.setWrapText(false);
        lblDni.setVisible(true);
        lblDni.setManaged(true);
        txtDni = new TextField(dni);
        txtDni.setPrefWidth(100);
        txtDni.setPrefHeight(24);
        txtDni.setMinHeight(24);
        txtDni.setMaxHeight(24);
        String colorBorde = Constantes.COLOR_BORDE_OSCURO.toString().substring(2, 8);
        String colorFondo = Constantes.COLOR_BLANCO.toString().substring(2, 8);
        txtDni.setStyle("-fx-border-color: #" + colorBorde + "; -fx-background-color: #" + colorFondo + "; -fx-padding: 4 8 4 8; -fx-effect: null;");
        filaDni.getChildren().addAll(lblDni, txtDni);
        content.getChildren().add(filaDni);
        
        // Campo: Nombres (igual que formulario principal)
        HBox filaNombres = new HBox(16);
        filaNombres.setAlignment(Pos.CENTER_LEFT);
        Label lblNombres = new Label("Nombres:");
        lblNombres.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblNombres.setTextFill(Color.rgb(51, 51, 51)); // #333333
        lblNombres.setStyle("-fx-text-fill: #333333; -fx-font-weight: 500;");
        lblNombres.setWrapText(false);
        lblNombres.setVisible(true);
        lblNombres.setManaged(true);
        txtNombres = new TextField(nombres);
        txtNombres.setPrefWidth(200);
        txtNombres.setPrefHeight(24);
        txtNombres.setMinHeight(24);
        txtNombres.setMaxHeight(24);
        txtNombres.setStyle("-fx-border-color: #" + colorBorde + "; -fx-background-color: #" + colorFondo + "; -fx-padding: 4 8 4 8; -fx-effect: null;");
        filaNombres.getChildren().addAll(lblNombres, txtNombres);
        content.getChildren().add(filaNombres);
        
        // Campo: Apellidos (igual que formulario principal)
        HBox filaApellidos = new HBox(16);
        filaApellidos.setAlignment(Pos.CENTER_LEFT);
        Label lblApellidos = new Label("Apellidos:");
        lblApellidos.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblApellidos.setTextFill(Color.rgb(51, 51, 51)); // #333333
        lblApellidos.setStyle("-fx-text-fill: #333333; -fx-font-weight: 500;");
        lblApellidos.setWrapText(false);
        lblApellidos.setVisible(true);
        lblApellidos.setManaged(true);
        txtApellidos = new TextField(apellidos);
        txtApellidos.setPrefWidth(240);
        txtApellidos.setPrefHeight(24);
        txtApellidos.setMinHeight(24);
        txtApellidos.setMaxHeight(24);
        txtApellidos.setStyle("-fx-border-color: #" + colorBorde + "; -fx-background-color: #" + colorFondo + "; -fx-padding: 4 8 4 8; -fx-effect: null;");
        filaApellidos.getChildren().addAll(lblApellidos, txtApellidos);
        content.getChildren().add(filaApellidos);
        
        // Campo: Teléfono (igual que formulario principal)
        HBox filaTelefono = new HBox(16);
        filaTelefono.setAlignment(Pos.CENTER_LEFT);
        Label lblTelefono = new Label("Teléfono:");
        lblTelefono.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblTelefono.setTextFill(Color.rgb(51, 51, 51)); // #333333
        lblTelefono.setStyle("-fx-text-fill: #333333; -fx-font-weight: 500;");
        lblTelefono.setWrapText(false);
        lblTelefono.setVisible(true);
        lblTelefono.setManaged(true);
        txtTelefono = new TextField(telefono);
        txtTelefono.setPrefWidth(100);
        txtTelefono.setPrefHeight(24);
        txtTelefono.setMinHeight(24);
        txtTelefono.setMaxHeight(24);
        txtTelefono.setStyle("-fx-border-color: #" + colorBorde + "; -fx-background-color: #" + colorFondo + "; -fx-padding: 4 8 4 8; -fx-effect: null;");
        filaTelefono.getChildren().addAll(lblTelefono, txtTelefono);
        content.getChildren().add(filaTelefono);
        
        // Campo: Email (igual que formulario principal)
        HBox filaEmail = new HBox(16);
        filaEmail.setAlignment(Pos.CENTER_LEFT);
        Label lblEmail = new Label("Email:");
        lblEmail.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblEmail.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lblEmail.setStyle("-fx-text-fill: #000000;");
        lblEmail.setWrapText(false);
        lblEmail.setVisible(true);
        lblEmail.setManaged(true);
        txtEmail = new TextField(email);
        txtEmail.setPrefWidth(260);
        txtEmail.setPrefHeight(24);
        txtEmail.setMinHeight(24);
        txtEmail.setMaxHeight(24);
        txtEmail.setStyle("-fx-border-color: #" + colorBorde + "; -fx-background-color: #" + colorFondo + "; -fx-padding: 4 8 4 8; -fx-effect: null;");
        filaEmail.getChildren().addAll(lblEmail, txtEmail);
        content.getChildren().add(filaEmail);
        
        // Campo: Dirección
        HBox filaDireccion = new HBox(16);
        filaDireccion.setAlignment(Pos.CENTER_LEFT);
        Label lblDireccion = new Label("Dirección:");
        lblDireccion.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblDireccion.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lblDireccion.setStyle("-fx-text-fill: #000000;");
        lblDireccion.setWrapText(false);
        lblDireccion.setVisible(true);
        lblDireccion.setManaged(true);
        txtDireccion = new TextField("");
        txtDireccion.setPrefWidth(260);
        txtDireccion.setPrefHeight(24);
        txtDireccion.setMinHeight(24);
        txtDireccion.setMaxHeight(24);
        txtDireccion.setStyle("-fx-border-color: #" + colorBorde + "; -fx-background-color: #" + colorFondo + "; -fx-padding: 4 8 4 8; -fx-effect: null;");
        filaDireccion.getChildren().addAll(lblDireccion, txtDireccion);
        content.getChildren().add(filaDireccion);
        
        // Campo: Fecha Nac
        HBox filaFechaNac = new HBox(16);
        filaFechaNac.setAlignment(Pos.CENTER_LEFT);
        Label lblFechaNac = new Label("Fecha Nac:");
        lblFechaNac.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblFechaNac.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lblFechaNac.setStyle("-fx-text-fill: #000000;");
        lblFechaNac.setWrapText(false);
        lblFechaNac.setVisible(true);
        lblFechaNac.setManaged(true);
        txtFechaNac = new TextField("");
        txtFechaNac.setPrefWidth(140);
        txtFechaNac.setPrefHeight(24);
        txtFechaNac.setMinHeight(24);
        txtFechaNac.setMaxHeight(24);
        txtFechaNac.setStyle("-fx-border-color: #" + colorBorde + "; -fx-background-color: #" + colorFondo + "; -fx-padding: 4 8 4 8; -fx-effect: null;");
        filaFechaNac.getChildren().addAll(lblFechaNac, txtFechaNac);
        content.getChildren().add(filaFechaNac);
        
        // Campo: Tipo Doc
        HBox filaTipoDoc = new HBox(16);
        filaTipoDoc.setAlignment(Pos.CENTER_LEFT);
        Label lblTipoDoc = new Label("Tipo Doc:");
        lblTipoDoc.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblTipoDoc.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lblTipoDoc.setStyle("-fx-text-fill: #000000;");
        lblTipoDoc.setWrapText(false);
        lblTipoDoc.setVisible(true);
        lblTipoDoc.setManaged(true);
        comboTipoDoc = new ComboBox<>();
        comboTipoDoc.getItems().addAll("DNI", "Pasaporte", "Carné Extranjería");
        comboTipoDoc.setPrefWidth(160);
        comboTipoDoc.setPrefHeight(24);
        comboTipoDoc.setMinHeight(24);
        comboTipoDoc.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(comboTipoDoc, "Seleccionar tipo");
        filaTipoDoc.getChildren().addAll(lblTipoDoc, comboTipoDoc);
        content.getChildren().add(filaTipoDoc);
        
        // Campo: Género
        HBox filaGenero = new HBox(16);
        filaGenero.setAlignment(Pos.CENTER_LEFT);
        Label lblGenero = new Label("Género:");
        lblGenero.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblGenero.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lblGenero.setStyle("-fx-text-fill: #000000;");
        lblGenero.setWrapText(false);
        lblGenero.setVisible(true);
        lblGenero.setManaged(true);
        comboGenero = new ComboBox<>();
        comboGenero.getItems().addAll("Masculino", "Femenino");
        comboGenero.setPrefWidth(160);
        comboGenero.setPrefHeight(24);
        comboGenero.setMinHeight(24);
        comboGenero.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(comboGenero, "Seleccionar género");
        filaGenero.getChildren().addAll(lblGenero, comboGenero);
        content.getChildren().add(filaGenero);
        
        // Campo: Nacionalidad
        HBox filaNacionalidad = new HBox(16);
        filaNacionalidad.setAlignment(Pos.CENTER_LEFT);
        Label lblNacionalidad = new Label("Nacionalidad:");
        lblNacionalidad.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblNacionalidad.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lblNacionalidad.setStyle("-fx-text-fill: #000000;");
        lblNacionalidad.setWrapText(false);
        lblNacionalidad.setVisible(true);
        lblNacionalidad.setManaged(true);
        comboNacionalidad = new ComboBox<>();
        comboNacionalidad.getItems().addAll("Peruana", "Argentina", "Chilena", "Colombiana", "Ecuatoriana", "Española", "Estadounidense", "Mexicana");
        comboNacionalidad.setValue(nacionalidad);
        comboNacionalidad.setPrefWidth(160);
        comboNacionalidad.setPrefHeight(24);
        comboNacionalidad.setMinHeight(24);
        comboNacionalidad.setMaxHeight(24);
        utilidades.EstiloComboBox.aplicarEstilo(comboNacionalidad, "Seleccionar nacionalidad");
        filaNacionalidad.getChildren().addAll(lblNacionalidad, comboNacionalidad);
        content.getChildren().add(filaNacionalidad);
        
        // Campo: Cliente VIP
        HBox filaVip = new HBox(16);
        filaVip.setAlignment(Pos.CENTER_LEFT);
        Label lblVip = new Label("Cliente VIP:");
        lblVip.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblVip.setTextFill(Color.rgb(0, 0, 0)); // #000000
        lblVip.setStyle("-fx-text-fill: #000000;");
        lblVip.setWrapText(false);
        lblVip.setVisible(true);
        lblVip.setManaged(true);
        checkVip = new CheckBox();
        checkVip.setSelected(vip);
        utilidades.EstiloCheckBox.aplicarEstilo(checkVip);
        filaVip.getChildren().addAll(lblVip, checkVip);
        content.getChildren().add(filaVip);
        
        // Campo: Recibir promociones (Newsletter)
        HBox filaNewsletter = new HBox(16);
        filaNewsletter.setAlignment(Pos.CENTER_LEFT);
        checkNewsletter = new CheckBox("Recibir promociones por email");
        checkNewsletter.setFont(Constantes.FUENTE_NORMAL);
        checkNewsletter.setSelected(false);
        utilidades.EstiloCheckBox.aplicarEstilo(checkNewsletter);
        filaNewsletter.getChildren().add(checkNewsletter);
        content.getChildren().add(filaNewsletter);
        
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

