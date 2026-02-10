package ventanas.dialogos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import componentes.IconoSimple;
import utilidades.Constantes;

/**
 * Diálogo de confirmación para eliminar elementos
 * Soporta dos variantes:
 * - Título azul (reservas): layout horizontal con icono y mensaje
 * - Título rojo (eliminaciones): layout vertical con icono, mensaje, nombre y advertencia
 */
public class DialogoConfirmacion extends DialogoBase {
    
    private boolean confirmado = false;
    
    public DialogoConfirmacion(String titulo, String mensaje, String nombreItem, String tipoIcono) {
        this(titulo, mensaje, nombreItem, tipoIcono, false);
    }
    
    public DialogoConfirmacion(String titulo, String mensaje, String nombreItem, String tipoIcono, boolean esEliminacion) {
        // Si es eliminación, título rojo; si no, título azul
        // Llamada a super() debe ser la primera línea
        super(titulo, 
              esEliminacion ? Color.rgb(220, 38, 38) : Constantes.COLOR_PRIMARIO, // #DC2626 o #0078D4
              esEliminacion ? 380 : 350, 
              0);
        
        // Configurar content según diseño
        content.setAlignment(Pos.CENTER);
        content.setSpacing(16);
        content.setPadding(new Insets(esEliminacion ? 24 : 20));
        
        // Icono de advertencia - según el tipo
        IconoSimple icono;
        if ("alert-triangle".equals(tipoIcono)) {
            if (esEliminacion) {
                icono = IconoSimple.crearIconoAlertTriangle(48, Color.rgb(220, 38, 38)); // #DC2626
            } else {
                icono = IconoSimple.crearIconoAlertTriangle(40, Color.rgb(133, 100, 4)); // #856404 para info
            }
        } else if ("trash-2".equals(tipoIcono)) {
            icono = IconoSimple.crearIconoTrash2(48, Color.rgb(220, 38, 38)); // #DC2626
        } else {
            icono = IconoSimple.crearIconoXCircle(48, Color.rgb(220, 38, 38)); // #DC2626
        }
        
        // Mensaje principal - fontSize 12, lineHeight 1.5
        Label lblMensaje = new Label(mensaje);
        lblMensaje.setFont(javafx.scene.text.Font.font("Arial", 12));
        lblMensaje.setStyle("-fx-text-fill: #000000; " +
                           "-fx-font-family: Arial; " +
                           "-fx-font-size: 12px; " +
                           "-fx-font-weight: normal;");
        lblMensaje.setAlignment(Pos.CENTER);
        lblMensaje.setWrapText(true);
        if (!esEliminacion) {
            lblMensaje.setPrefWidth(240);
        }
        lblMensaje.setLineSpacing(1.5);
        
        if (esEliminacion) {
            // Layout vertical para eliminaciones
            VBox contenido = new VBox(16);
            contenido.setAlignment(Pos.CENTER);
            contenido.getChildren().add(icono);
            contenido.getChildren().add(lblMensaje);
            
            // Nombre del item - fontSize 11, bold, color #666666
            if (nombreItem != null && !nombreItem.isEmpty()) {
                Label lblNombre = new Label(nombreItem);
                lblNombre.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 11));
                lblNombre.setStyle("-fx-text-fill: #666666; " +
                                 "-fx-font-family: Arial; " +
                                 "-fx-font-size: 11px; " +
                                 "-fx-font-weight: bold;");
                contenido.getChildren().add(lblNombre);
            }
            
            // Advertencia - fontSize 10, color #DC2626
            Label lblAdvertencia = new Label("Esta acción no se puede deshacer.");
            lblAdvertencia.setFont(javafx.scene.text.Font.font("Arial", 10));
            lblAdvertencia.setStyle("-fx-text-fill: #DC2626; " +
                                   "-fx-font-family: Arial; " +
                                   "-fx-font-size: 10px; " +
                                   "-fx-font-weight: normal;");
            contenido.getChildren().add(lblAdvertencia);
            
            content.getChildren().add(contenido);
            
            // Botones - centrados, fontSize 11, padding 6,20
            buttons.setAlignment(Pos.CENTER);
            buttons.setSpacing(16);
            
            Button btnEliminar = new Button("Eliminar");
            btnEliminar.setFont(javafx.scene.text.Font.font("Arial", 11));
            btnEliminar.setStyle("-fx-background-color: #DC2626; " +
                                "-fx-text-fill: #FFFFFF; " +
                                "-fx-border-color: #B91C1C; " +
                                "-fx-border-width: 1; " +
                                "-fx-border-radius: 2; " +
                                "-fx-background-radius: 2; " +
                                "-fx-padding: 6 20 6 20; " +
                                "-fx-cursor: hand;");
            btnEliminar.setOnAction(_ -> {
                confirmado = true;
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
            
            buttons.getChildren().addAll(btnEliminar, btnCancelar);
        } else {
            // Layout horizontal para confirmaciones de reserva
            HBox contenido = new HBox(16);
            contenido.setAlignment(Pos.CENTER);
            contenido.getChildren().addAll(icono, lblMensaje);
            
            content.getChildren().add(contenido);
            
            // Botones - alineados a la derecha (justifyContent: end), fontSize 12
            buttons.setAlignment(Pos.CENTER_RIGHT);
            buttons.setSpacing(8);
            
            Button btnEliminar = new Button("Eliminar");
            btnEliminar.setFont(javafx.scene.text.Font.font("Arial", 12));
            btnEliminar.setStyle("-fx-background-color: #D83B01; " +
                                "-fx-text-fill: #FFFFFF; " +
                                "-fx-border-color: #A52A00; " +
                                "-fx-border-width: 1; " +
                                "-fx-border-radius: 2; " +
                                "-fx-background-radius: 2; " +
                                "-fx-padding: 6 20 6 20; " +
                                "-fx-cursor: hand;");
            btnEliminar.setOnAction(_ -> {
                confirmado = true;
                cerrar();
            });
            
            Button btnCancelar = new Button("Cancelar");
            btnCancelar.setFont(javafx.scene.text.Font.font("Arial", 12));
            btnCancelar.setStyle("-fx-background-color: #F0F0F0; " +
                               "-fx-text-fill: #333333; " +
                               "-fx-border-color: #A0A0A0; " +
                               "-fx-border-width: 1; " +
                               "-fx-border-radius: 2; " +
                               "-fx-background-radius: 2; " +
                               "-fx-padding: 6 20 6 20; " +
                               "-fx-cursor: hand;");
            btnCancelar.setOnAction(_ -> cerrar());
            
            buttons.getChildren().addAll(btnEliminar, btnCancelar);
        }
        
        // Calcular altura
        root.setPrefHeight(javafx.scene.layout.Region.USE_COMPUTED_SIZE);
    }
    
    public boolean isConfirmado() {
        return confirmado;
    }
}

