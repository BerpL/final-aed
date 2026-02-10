package ventanas.dialogos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import componentes.IconoSimple;
import utilidades.Constantes;

/**
 * Diálogo para mostrar mensajes de éxito o error
 */
public class DialogoMensaje extends DialogoBase {
    
    public DialogoMensaje(String titulo, String mensaje, TipoMensaje tipo) {
        super(titulo, 
              tipo == TipoMensaje.EXITO ? Constantes.COLOR_PRIMARIO : 
              tipo == TipoMensaje.ERROR ? Color.rgb(216, 59, 1) : // #D83B01
              Constantes.COLOR_PRIMARIO,
              tipo == TipoMensaje.EXITO ? 320 : 350,
              0); // Altura calculada automáticamente
        
        content.setAlignment(Pos.CENTER);
        content.setSpacing(16);
        content.setPadding(new Insets(20));
        
        // Icono según el tipo - tamaños exactos según diseño
        IconoSimple icono;
        if (tipo == TipoMensaje.EXITO) {
            icono = IconoSimple.crearIconoCircleCheck(40, Color.rgb(16, 124, 16)); // #107C10
        } else if (tipo == TipoMensaje.ERROR) {
            icono = IconoSimple.crearIconoCircleX(40, Color.rgb(216, 59, 1)); // #D83B01
        } else {
            icono = IconoSimple.crearIconoInfo(40, Color.rgb(133, 100, 4)); // #856404
        }
        
        // Mensaje - fontSize 12, lineHeight 1.5, width según tipo
        Label lblMensaje = new Label(mensaje);
        lblMensaje.setFont(javafx.scene.text.Font.font("Arial", 12));
        lblMensaje.setStyle("-fx-text-fill: #333333; " +
                           "-fx-font-family: Arial; " +
                           "-fx-font-size: 12px; " +
                           "-fx-font-weight: normal;");
        lblMensaje.setAlignment(Pos.CENTER);
        lblMensaje.setWrapText(true);
        lblMensaje.setPrefWidth(tipo == TipoMensaje.EXITO ? 200 : 240);
        lblMensaje.setLineSpacing(1.5);
        
        HBox contenido = new HBox(16);
        contenido.setAlignment(Pos.CENTER);
        contenido.getChildren().addAll(icono, lblMensaje);
        
        content.getChildren().add(contenido);
        
        // Botón - fontSize 12, padding 6,24
        buttons.setAlignment(Pos.CENTER);
        Button btnAceptar = new Button("Aceptar");
        btnAceptar.setFont(javafx.scene.text.Font.font("Arial", 12));
        btnAceptar.setStyle("-fx-background-color: #" + Constantes.COLOR_PRIMARIO.toString().substring(2, 8) + 
                           "; -fx-text-fill: #FFFFFF; " +
                           "-fx-border-color: #" + Constantes.COLOR_PRIMARIO_OSCURO.toString().substring(2, 8) + 
                           "; -fx-border-width: 1; " +
                           "-fx-border-radius: 2; " +
                           "-fx-background-radius: 2; " +
                           "-fx-padding: 6 24 6 24; " +
                           "-fx-cursor: hand;");
        btnAceptar.setOnAction(_ -> cerrar());
        buttons.getChildren().add(btnAceptar);
        
        // Calcular altura
        root.setPrefHeight(javafx.scene.layout.Region.USE_COMPUTED_SIZE);
    }
    
    public enum TipoMensaje {
        EXITO, ERROR, INFO
    }
}

