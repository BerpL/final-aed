package utilidades;

import javafx.scene.control.ComboBox;

/**
 * Utilidad para aplicar el estilo moderno AED a ComboBox
 * Aplica la clase CSS "aed-combo" para un look corporativo moderno
 */
public class EstiloComboBox {
    
    /**
     * Aplica el estilo moderno AED a un ComboBox
     * @param comboBox El ComboBox a estilizar
     */
    public static void aplicarEstilo(ComboBox<?> comboBox) {
        // Agregar clase CSS para aplicar estilos modernos
        comboBox.getStyleClass().add("aed-combo");
    }
    
    /**
     * Aplica el estilo moderno AED y establece un prompt text
     * @param comboBox El ComboBox a estilizar
     * @param promptText Texto por defecto a mostrar (ej: "Seleccionar huésped")
     */
    public static void aplicarEstilo(ComboBox<?> comboBox, String promptText) {
        aplicarEstilo(comboBox);
        comboBox.setPromptText(promptText);
    }
}



