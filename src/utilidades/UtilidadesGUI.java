package utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Clase de utilidades para componentes GUI
 */
public class UtilidadesGUI {
    
    /**
     * Crea un separador horizontal
     */
    public static Region crearSeparador() {
        Region separador = new Region();
        separador.setPrefHeight(1);
        separador.setMaxHeight(1);
        separador.setStyle("-fx-background-color: #" + Constantes.COLOR_SEPARADOR.toString().substring(2, 8) + ";");
        return separador;
    }
    
    /**
     * Crea un label con estilo consistente
     */
    public static Label crearLabel(String texto, Font fuente, Color color) {
        Label label = new Label(texto);
        label.setFont(fuente);
        label.setTextFill(color);
        return label;
    }
    
    /**
     * Obtiene la fecha y hora actual formateada
     */
    public static String obtenerFechaHoraActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
    
    private UtilidadesGUI() {
        // Clase de utilidades, no se debe instanciar
    }
}
