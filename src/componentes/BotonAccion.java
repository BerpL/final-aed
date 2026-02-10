package componentes;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import utilidades.Constantes;

/**
 * Botón de acción con icono para usar en tablas
 */
public class BotonAccion extends StackPane {
    
    private IconoSimple icono;
    
    public BotonAccion(String tipo, Color color) {
        setAlignment(Pos.CENTER);
        setPrefSize(20, 20);
        setMinSize(20, 20);
        setMaxSize(20, 20);
        
        // Sin fondo ni borde, solo el icono (como en el diseño)
        setStyle("-fx-background-color: transparent; " +
                 "-fx-border-color: transparent; " +
                 "-fx-cursor: hand;");
        
        // Crear icono según el tipo (16px para mejor visibilidad, con el color del botón)
        switch(tipo) {
            case "ver":
                icono = IconoSimple.crearIconoVer(16, color);
                break;
            case "editar":
                icono = IconoSimple.crearIconoEditar(16, color);
                break;
            case "eliminar":
                icono = IconoSimple.crearIconoEliminar(16, color);
                break;
            default:
                icono = IconoSimple.crearIconoNavegacion(16, color);
        }
        
        getChildren().add(icono);
        
        // Agregar efecto hover (cambiar opacidad del icono interno)
        setOnMouseEntered(_ -> {
            if (icono.getFontIcon() != null) {
                icono.getFontIcon().setOpacity(0.7);
            }
        });
        
        setOnMouseExited(_ -> {
            if (icono.getFontIcon() != null) {
                icono.getFontIcon().setOpacity(1.0);
            }
        });
    }
    
    public static BotonAccion crearBotonVer() {
        BotonAccion btn = new BotonAccion("ver", Constantes.COLOR_PRIMARIO);
        // El icono ya tiene el color correcto (#0078D4)
        return btn;
    }
    
    public static BotonAccion crearBotonEditar() {
        BotonAccion btn = new BotonAccion("editar", Color.rgb(245, 158, 11)); // #F59E0B
        return btn;
    }
    
    public static BotonAccion crearBotonEliminar() {
        BotonAccion btn = new BotonAccion("eliminar", Color.rgb(220, 38, 38)); // #DC2626
        return btn;
    }
}
