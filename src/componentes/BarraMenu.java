package componentes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import utilidades.Constantes;

/**
 * Componente reutilizable para la barra de menú horizontal
 */
public class BarraMenu extends HBox {
    
    public BarraMenu() {
        setAlignment(Pos.CENTER_LEFT);
        setPrefHeight(Constantes.ALTO_MENU_BAR);
        setStyle("-fx-background-color: #" + Constantes.COLOR_MENU_BAR.toString().substring(2, 8) + 
                 "; -fx-border-color: #" + Constantes.COLOR_BORDE_CLARO.toString().substring(2, 8) + 
                 "; -fx-border-width: 0 0 1 0;");
        setPadding(new Insets(0, 4, 0, 4));
        setSpacing(0);
        
        String[] itemsMenu = {"Mantenimiento", "Reservas", "Consultas", "Reportes", "Ayuda"};
        
        for (String item : itemsMenu) {
            Label menuItem = new Label(item);
            menuItem.setFont(Constantes.FUENTE_MENU);
            menuItem.setTextFill(Constantes.COLOR_NEGRO);
            menuItem.setPadding(new Insets(4, 12, 4, 12));
            getChildren().add(menuItem);
        }
    }
}
