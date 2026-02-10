package ventanas.dialogos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import utilidades.Constantes;

/**
 * Diálogo para ver detalles de un tipo de habitación
 * Diseño exacto según aed.pen
 */
public class DialogoDetalleTipoHabitacion extends DialogoBase {
    
    public DialogoDetalleTipoHabitacion(String codigo, String nombre, String capacidad, 
                                        String precioBase, String descripcion) {
        super("Detalle Tipo de Habitación", Constantes.COLOR_PRIMARIO, 400, 0); // Alto se calculará automáticamente
        
        // Configurar content exactamente según diseño
        content.setSpacing(12); // gap: 12px entre filas
        content.setPadding(new Insets(20)); // padding: 20px (asegurar que esté configurado)
        content.setVisible(true);
        content.setManaged(true);
        
        // Fila 1: Código
        agregarFila("Código:", codigo, true);
        
        // Fila 2: Nombre
        agregarFila("Nombre:", nombre, true);
        
        // Fila 3: Capacidad Max
        agregarFila("Capacidad Max:", capacidad, true);
        
        // Fila 4: Precio Base
        agregarFila("Precio Base:", precioBase, true);
        
        // Fila 5: Descripción (sin negrita en el valor)
        agregarFila("Descripción:", descripcion, false);
        
        // Configurar botones - usar padding de DialogoBase, solo configurar alignment
        buttons.setAlignment(Pos.CENTER);
        buttons.setStyle("-fx-border-color: #" + Constantes.COLOR_BORDE_CLARO.toString().substring(2, 8) + 
                        "; -fx-border-width: 1 0 0 0;");
        
        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setFont(javafx.scene.text.Font.font("Arial", 11)); // fontSize: 11, fontWeight: normal
        btnCerrar.setTextFill(Color.WHITE);
        btnCerrar.setPadding(new Insets(6, 24, 6, 24)); // padding: 6,24
        btnCerrar.setStyle("-fx-background-color: #" + Constantes.COLOR_PRIMARIO.toString().substring(2, 8) + 
                          "; -fx-text-fill: #FFFFFF; " +
                          "-fx-border-color: #" + Constantes.COLOR_PRIMARIO_OSCURO.toString().substring(2, 8) + 
                          "; -fx-border-width: 1; " +
                          "-fx-border-radius: 2; " +
                          "-fx-background-radius: 2; " +
                          "-fx-cursor: hand;");
        btnCerrar.setOnAction(_ -> cerrar());
        
        buttons.getChildren().add(btnCerrar);
    }
    
    @Override
    public void mostrar() {
        // Crear la escena primero
        javafx.scene.Scene scene = new javafx.scene.Scene(root);
        // Cargar CSS para que los checkboxes y otros componentes tengan el estilo correcto
        try {
            java.net.URL cssUrl = getClass().getResource("/main/resources/styles/estilos.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            } else {
                System.err.println("CSS de diálogo DetalleTipoHabitacion no encontrado: /main/resources/styles/estilos.css");
            }
        } catch (Exception e) {
            // CSS opcional, continuar sin él
        }
        stage.setScene(scene);
        
        // Asegurar que el root calcule su altura correctamente antes de mostrar
        root.applyCss();
        root.layout();
        
        // Forzar el cálculo del tamaño preferido de cada componente
        content.applyCss();
        content.layout();
        buttons.applyCss();
        buttons.layout();
        
        // Usar autosize para calcular la altura correctamente
        root.autosize();
        
        // Obtener la altura calculada por autosize
        double alturaCalculada = root.getHeight();
        
        // Si autosize no calculó correctamente, calcular manualmente
        if (alturaCalculada <= 0) {
            double alturaContent = content.prefHeight(-1);
            if (alturaContent <= 0) {
                alturaContent = content.getHeight();
            }
            if (alturaContent <= 0) {
                alturaContent = 100;
            }
            
            double alturaButtons = buttons.prefHeight(-1);
            if (alturaButtons <= 0) {
                alturaButtons = buttons.getHeight();
            }
            if (alturaButtons <= 0) {
                alturaButtons = 44; // Altura mínima para buttons (igual que otros diálogos)
            }
            
            alturaCalculada = 28 + alturaContent + alturaButtons;
        }
        
        // Establecer la altura calculada
        root.setPrefHeight(alturaCalculada);
        
        // Forzar un layout final
        root.layout();
        
        stage.showAndWait();
    }
    
    private void agregarFila(String etiqueta, String valor, boolean valorEnNegrita) {
        HBox fila = new HBox(8); // gap: 8px
        fila.setAlignment(Pos.CENTER_LEFT);
        
        // Label: #666666, Arial, 11px, normal
        Label lbl = new Label(etiqueta);
        lbl.setFont(javafx.scene.text.Font.font("Arial", 11));
        lbl.setTextFill(Color.rgb(102, 102, 102)); // #666666
        // Aplicar estilo inline explícito para asegurar visibilidad
        lbl.setStyle("-fx-text-fill: #666666; " +
                    "-fx-font-family: Arial; " +
                    "-fx-font-size: 11px; " +
                    "-fx-font-weight: normal;");
        lbl.setVisible(true);
        lbl.setManaged(true);
        
        // Valor: #000000, Arial, 11px, bold (si aplica)
        Label val = new Label(valor);
        if (valorEnNegrita) {
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
        
        // Para descripción, limitar ancho si es muy largo
        if ("Descripción:".equals(etiqueta)) {
            val.setWrapText(true);
            val.setPrefWidth(230); // width: 230px según diseño
            val.setMaxWidth(230);
        }
        
        fila.getChildren().addAll(lbl, val);
        content.getChildren().add(fila);
    }
}

