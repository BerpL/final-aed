package componentes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import utilidades.Constantes;

/**
 * Componente reutilizable para el panel de navegación lateral
 */
public class PanelNavegacion extends VBox {
    
    private HBox[] itemsNavegacion;
    private NavegacionListener listener;
    private int itemSeleccionado = -1;
    
    public interface NavegacionListener {
        void onItemSeleccionado(int indice);
    }
    
    public PanelNavegacion() {
        setSpacing(2);
        setPrefWidth(Constantes.ANCHO_PANEL_LATERAL);
        setMinWidth(Constantes.ANCHO_PANEL_LATERAL);
        setMaxWidth(Constantes.ANCHO_PANEL_LATERAL);
        // El BorderPane automáticamente hace que el panel izquierdo ocupe toda la altura
        setStyle("-fx-background-color: #" + Constantes.COLOR_PANEL_LATERAL.toString().substring(2, 8) + 
                 "; -fx-border-color: #" + Constantes.COLOR_BORDE_MEDIO.toString().substring(2, 8) + 
                 "; -fx-border-width: 0 1 0 0;");
        setPadding(new Insets(8, 0, 8, 0));
        
        // Items de navegación (incluyendo "Navegación" como primer item)
        String[] items = {"Navegación", "Huéspedes", "Habitaciones", "Tipos Habitación", "Reservas", "Reportes"};
        String[] iconos = {"navegacion", "usuarios", "cama", "puerta", "calendario", "grafico"};
        itemsNavegacion = new HBox[items.length];
        
        for (int i = 0; i < items.length; i++) {
            final int indice = i;
            HBox item = crearItemNavegacion(items[i], iconos[i], indice);
            itemsNavegacion[i] = item;
            getChildren().add(item);
        }
        
        // Espaciador para empujar los items hacia arriba y ocupar toda la altura
        javafx.scene.layout.Region spacer = new javafx.scene.layout.Region();
        VBox.setVgrow(spacer, javafx.scene.layout.Priority.ALWAYS);
        getChildren().add(spacer);
    }
    
    private HBox crearItemNavegacion(String texto, String tipoIcono, final int indice) {
        HBox item = new HBox(8);
        item.setAlignment(Pos.CENTER_LEFT);
        item.setPadding(new Insets(8, 12, 8, 12));
        item.setPrefHeight(36);
        item.setMaxHeight(36);
        item.setStyle("-fx-background-color: #" + Constantes.COLOR_PANEL_LATERAL.toString().substring(2, 8) + ";");
        item.setOnMouseEntered(_ -> {
            if (itemSeleccionado != indice) {
                item.setStyle("-fx-background-color: #" + 
                    String.format("%02x%02x%02x", 
                        Math.max(0, (int)(Constantes.COLOR_PANEL_LATERAL.getRed() * 255) - 10),
                        Math.max(0, (int)(Constantes.COLOR_PANEL_LATERAL.getGreen() * 255) - 10),
                        Math.max(0, (int)(Constantes.COLOR_PANEL_LATERAL.getBlue() * 255) - 10)
                    ) + ";");
            }
        });
        item.setOnMouseExited(_ -> {
            if (itemSeleccionado != indice) {
                item.setStyle("-fx-background-color: #" + Constantes.COLOR_PANEL_LATERAL.toString().substring(2, 8) + ";");
            }
        });
        item.setOnMouseClicked(_ -> {
            if (listener != null) {
                listener.onItemSeleccionado(indice);
            }
        });
        
        // Crear icono según el tipo
        IconoSimple icono = crearIconoPorTipo(tipoIcono, 16, Constantes.COLOR_TEXTO_PRINCIPAL);
        item.getChildren().add(icono);
        
        Label lblTexto = new Label(texto);
        lblTexto.setFont(Constantes.FUENTE_NORMAL);
        lblTexto.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        item.getChildren().add(lblTexto);
        
        return item;
    }
    
    private IconoSimple crearIconoPorTipo(String tipo, int tamano, Color color) {
        switch(tipo) {
            case "navegacion":
                return IconoSimple.crearIconoNavegacion(tamano, color);
            case "usuarios":
                return IconoSimple.crearIconoUsuarios(tamano, color);
            case "cama":
                return IconoSimple.crearIconoCama(tamano, color);
            case "puerta":
                return IconoSimple.crearIconoPuerta(tamano, color);
            case "calendario":
                return IconoSimple.crearIconoCalendario(tamano, color);
            case "grafico":
                return IconoSimple.crearIconoGrafico(tamano, color);
            default:
                return IconoSimple.crearIconoNavegacion(tamano, color);
        }
    }
    
    public void setNavegacionListener(NavegacionListener listener) {
        this.listener = listener;
    }
    
    public void seleccionarItem(int indice) {
        itemSeleccionado = indice;
        
        // Deseleccionar todos
        for (int i = 0; i < itemsNavegacion.length; i++) {
            HBox item = itemsNavegacion[i];
            if (i == indice) {
                item.setStyle("-fx-background-color: #" + Constantes.COLOR_PRIMARIO.toString().substring(2, 8) + ";");
                for (javafx.scene.Node node : item.getChildren()) {
                    if (node instanceof Label) {
                        ((Label) node).setTextFill(Constantes.COLOR_BLANCO);
                    } else if (node instanceof IconoSimple) {
                        ((IconoSimple) node).setColor(Constantes.COLOR_BLANCO);
                    }
                }
            } else {
                item.setStyle("-fx-background-color: #" + Constantes.COLOR_PANEL_LATERAL.toString().substring(2, 8) + ";");
                for (javafx.scene.Node node : item.getChildren()) {
                    if (node instanceof Label) {
                        ((Label) node).setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
                    } else if (node instanceof IconoSimple) {
                        ((IconoSimple) node).setColor(Constantes.COLOR_TEXTO_PRINCIPAL);
                    }
                }
            }
        }
    }
}
