package ventanas.dialogos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import utilidades.Constantes;

/**
 * Diálogo para ver detalles de un huésped
 */
public class DialogoDetalleHuesped extends DialogoBase {
    
    public DialogoDetalleHuesped(String id, String nombre, String documento, String telefono, 
                                  String email, String nacionalidad, boolean vip) {
        super("Detalle del Huésped", Constantes.COLOR_PRIMARIO, 420, 0);
        
        content.setSpacing(12);
        content.setPadding(new Insets(20));
        content.setVisible(true);
        content.setManaged(true);
        
        agregarFila("ID:", id, true); // ID en bold
        agregarFila("Nombre:", nombre, false);
        agregarFila("Documento:", documento, false);
        agregarFila("Teléfono:", telefono, false);
        agregarFila("Email:", email, false);
        agregarFila("Nacionalidad:", nacionalidad, false);
        
        // Cliente VIP - especial
        HBox filaVIP = new HBox(8); // gap: 8px
        filaVIP.setAlignment(Pos.CENTER_LEFT);
        Label lblVIP = new Label("Cliente VIP:");
        lblVIP.setFont(javafx.scene.text.Font.font("Arial", 12));
        lblVIP.setTextFill(Color.rgb(102, 102, 102)); // #666666
        // Aplicar estilo inline explícito para asegurar visibilidad
        lblVIP.setStyle("-fx-text-fill: #666666; " +
                       "-fx-font-family: Arial; " +
                       "-fx-font-size: 12px; " +
                       "-fx-font-weight: normal;");
        lblVIP.setVisible(true);
        lblVIP.setManaged(true);
        
        Label lblValorVIP = new Label(vip ? "Sí" : "No");
        lblValorVIP.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        if (vip) {
            lblValorVIP.setStyle("-fx-text-fill: #107C10; " +
                                "-fx-font-family: Arial; " +
                                "-fx-font-size: 12px; " +
                                "-fx-font-weight: bold;");
        } else {
            lblValorVIP.setStyle("-fx-text-fill: #333333; " +
                                "-fx-font-family: Arial; " +
                                "-fx-font-size: 12px; " +
                                "-fx-font-weight: bold;");
        }
        lblValorVIP.setVisible(true);
        lblValorVIP.setManaged(true);
        
        filaVIP.getChildren().addAll(lblVIP, lblValorVIP);
        content.getChildren().add(filaVIP);
        
        // Botón cerrar - fontSize 12, padding 6,24
        buttons.setAlignment(Pos.CENTER);
        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setFont(javafx.scene.text.Font.font("Arial", 12));
        btnCerrar.setStyle("-fx-background-color: #" + Constantes.COLOR_PRIMARIO.toString().substring(2, 8) + 
                          "; -fx-text-fill: #FFFFFF; " +
                          "-fx-border-color: #" + Constantes.COLOR_PRIMARIO_OSCURO.toString().substring(2, 8) + 
                          "; -fx-border-width: 1; " +
                          "-fx-border-radius: 2; " +
                          "-fx-background-radius: 2; " +
                          "-fx-padding: 6 24 6 24; " +
                          "-fx-cursor: hand;");
        btnCerrar.setOnAction(_ -> cerrar());
        buttons.getChildren().add(btnCerrar);
    }
    
    @Override
    public void mostrar() {
        // Usar el mismo método que DialogoBase para mantener consistencia
        super.mostrar();
    }
    
    private void agregarFila(String etiqueta, String valor, boolean valorEnBold) {
        HBox fila = new HBox(8); // gap: 8px
        fila.setAlignment(Pos.CENTER_LEFT);
        
        // Label: fontSize 12, fontWeight normal, color #666666
        Label lbl = new Label(etiqueta);
        lbl.setFont(javafx.scene.text.Font.font("Arial", 12));
        lbl.setTextFill(Color.rgb(102, 102, 102)); // #666666
        lbl.setWrapText(false); // Nunca truncar
        // Aplicar estilo inline explícito para asegurar visibilidad
        lbl.setStyle("-fx-text-fill: #666666; " +
                    "-fx-font-family: Arial; " +
                    "-fx-font-size: 12px; " +
                    "-fx-font-weight: normal;");
        lbl.setVisible(true);
        lbl.setManaged(true);
        
        // Valor: fontSize 12, fontWeight normal o bold según corresponda, color #333333
        Label val = new Label(valor);
        val.setWrapText(false); // Nunca truncar
        if (valorEnBold) {
            val.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
            val.setStyle("-fx-text-fill: #333333; " +
                        "-fx-font-family: Arial; " +
                        "-fx-font-size: 12px; " +
                        "-fx-font-weight: bold;");
        } else {
            val.setFont(javafx.scene.text.Font.font("Arial", 12));
            val.setStyle("-fx-text-fill: #333333; " +
                        "-fx-font-family: Arial; " +
                        "-fx-font-size: 12px; " +
                        "-fx-font-weight: normal;");
        }
        val.setVisible(true);
        val.setManaged(true);
        
        fila.getChildren().addAll(lbl, val);
        content.getChildren().add(fila);
    }
}

