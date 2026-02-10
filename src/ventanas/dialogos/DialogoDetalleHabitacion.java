package ventanas.dialogos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import utilidades.Constantes;

/**
 * Diálogo para ver detalles de una habitación
 */
public class DialogoDetalleHabitacion extends DialogoBase {
    
    public DialogoDetalleHabitacion(String numero, String tipo, String piso, String capacidad, 
                                    String precio, String estado, String amenidades) {
        super("Detalle de la Habitación", Constantes.COLOR_PRIMARIO, 420, 0);
        
        content.setSpacing(12);
        content.setPadding(new Insets(20));
        content.setVisible(true);
        content.setManaged(true);
        
        agregarFila("Número:", numero, true);
        agregarFila("Tipo:", tipo, true);
        agregarFila("Piso:", piso, true);
        agregarFila("Capacidad:", capacidad + " personas", true);
        agregarFila("Precio/Noche:", precio, true);
        
        // Estado con badge - fontSize 11 para label, badge con fontSize 10
        HBox filaEstado = new HBox(8);
        filaEstado.setAlignment(Pos.CENTER_LEFT);
        Label lblEstado = new Label("Estado:");
        lblEstado.setFont(javafx.scene.text.Font.font("Arial", 11));
        lblEstado.setTextFill(Color.rgb(102, 102, 102)); // #666666
        lblEstado.setWrapText(false); // Nunca truncar
        // Aplicar estilo inline explícito para asegurar visibilidad
        lblEstado.setStyle("-fx-text-fill: #666666; " +
                          "-fx-font-family: Arial; " +
                          "-fx-font-size: 11px; " +
                          "-fx-font-weight: normal;");
        lblEstado.setVisible(true);
        lblEstado.setManaged(true);
        
        Label badgeEstado = new Label(estado);
        badgeEstado.setFont(javafx.scene.text.Font.font("Arial", 10));
        badgeEstado.setStyle("-fx-text-fill: #FFFFFF; " +
                            "-fx-font-family: Arial; " +
                            "-fx-font-size: 10px; " +
                            "-fx-font-weight: normal;");
        badgeEstado.setPadding(new Insets(2, 8, 2, 8));
        Color colorEstado = "Disponible".equals(estado) ? Color.rgb(34, 197, 94) : // #22C55E
                           "Ocupada".equals(estado) ? Color.rgb(220, 38, 38) : 
                           Color.rgb(245, 158, 11);
        badgeEstado.setStyle("-fx-background-color: #" + colorEstado.toString().substring(2, 8) + 
                            "; -fx-text-fill: #FFFFFF; " +
                            "-fx-font-family: Arial; " +
                            "-fx-font-size: 10px; " +
                            "-fx-background-radius: 2; " +
                            "-fx-padding: 2 8 2 8;");
        
        filaEstado.getChildren().addAll(lblEstado, badgeEstado);
        content.getChildren().add(filaEstado);
        
        agregarFila("Amenidades:", amenidades, false);
        
        // Botón cerrar - fontSize 11, padding 6,24
        buttons.setAlignment(Pos.CENTER);
        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setFont(javafx.scene.text.Font.font("Arial", 11));
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
        
        // Label: fontSize 11, fontWeight normal, color #666666
        Label lbl = new Label(etiqueta);
        lbl.setFont(javafx.scene.text.Font.font("Arial", 11));
        lbl.setTextFill(Color.rgb(102, 102, 102)); // #666666
        lbl.setWrapText(false); // Nunca truncar
        // Aplicar estilo inline explícito para asegurar visibilidad
        lbl.setStyle("-fx-text-fill: #666666; " +
                    "-fx-font-family: Arial; " +
                    "-fx-font-size: 11px; " +
                    "-fx-font-weight: normal;");
        lbl.setVisible(true);
        lbl.setManaged(true);
        
        // Valor: fontSize 11, fontWeight bold o normal, color #000000
        Label val = new Label(valor);
        val.setWrapText(false); // Nunca truncar
        if (valorEnBold) {
            val.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 11));
            val.setStyle("-fx-text-fill: #000000; " +
                        "-fx-font-family: Arial; " +
                        "-fx-font-size: 11px; " +
                        "-fx-font-weight: bold;");
        } else {
            val.setFont(javafx.scene.text.Font.font("Arial", 11));
            val.setStyle("-fx-text-fill: #000000; " +
                        "-fx-font-family: Arial; " +
                        "-fx-font-size: 11px; " +
                        "-fx-font-weight: normal;");
        }
        val.setTextFill(Color.rgb(0, 0, 0)); // #000000
        val.setVisible(true);
        val.setManaged(true);
        
        fila.getChildren().addAll(lbl, val);
        content.getChildren().add(fila);
    }
}

