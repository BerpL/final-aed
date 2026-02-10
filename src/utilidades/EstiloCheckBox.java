package utilidades;

import javafx.scene.control.CheckBox;

/**
 * Utilidad para aplicar estilos personalizados a CheckBox según diseño aed.pen
 * Usa la clase CSS "aed-check" definida en aed-theme.css
 */
public class EstiloCheckBox {
    
    /**
     * Aplica el estilo personalizado a un CheckBox según diseño aed.pen
     * Agrega la clase CSS "aed-check" que tiene mayor especificidad que los estilos globales
     * @param checkBox El CheckBox a estilizar
     */
    public static void aplicarEstilo(CheckBox checkBox) {
        // Agregar clase CSS para aplicar estilos modernos
        // La clase "aed-check" tiene mayor especificidad que los estilos globales
        checkBox.getStyleClass().add("aed-check");
    }
}

