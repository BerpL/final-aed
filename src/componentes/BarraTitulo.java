package componentes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import utilidades.Constantes;

/**
 * Componente reutilizable para la barra de título de las ventanas
 */
public class BarraTitulo extends BorderPane {
    
    private Label lblTitulo;
    
    public BarraTitulo(String titulo) {
        setPrefHeight(Constantes.ALTO_BARRA_TITULO);
        setStyle("-fx-background-color: #" + Constantes.COLOR_PRIMARIO.toString().substring(2, 8) + ";");
        
        // Panel izquierdo con título
        HBox panelIzquierdo = new HBox(8);
        panelIzquierdo.setAlignment(Pos.CENTER_LEFT);
        panelIzquierdo.setPadding(new Insets(0, 8, 0, 8));
        
        lblTitulo = new Label(titulo);
        lblTitulo.setFont(Constantes.FUENTE_TITULO_VENTANA);
        lblTitulo.setTextFill(Constantes.COLOR_TEXTO_BLANCO);
        panelIzquierdo.getChildren().add(lblTitulo);
        
        // Panel derecho con controles de ventana (simplificado)
        HBox panelDerecho = new HBox();
        panelDerecho.setAlignment(Pos.CENTER_RIGHT);
        panelDerecho.setPrefWidth(138);
        panelDerecho.setPrefHeight(Constantes.ALTO_BARRA_TITULO);
        
        setLeft(panelIzquierdo);
        setRight(panelDerecho);
    }
    
    public void setTitulo(String titulo) {
        lblTitulo.setText(titulo);
    }
}
