package componentes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import utilidades.Constantes;
import utilidades.UtilidadesGUI;

/**
 * Componente reutilizable para la barra de estado inferior
 */
public class BarraEstado extends HBox {
    
    private Label lblEstado;
    private Label lblUsuario;
    private Label lblFecha;
    
    public BarraEstado() {
        setAlignment(Pos.CENTER_LEFT);
        setPrefHeight(Constantes.ALTO_STATUS_BAR);
        setStyle("-fx-background-color: #" + Constantes.COLOR_STATUS_BAR.toString().substring(2, 8) + 
                 "; -fx-border-color: #" + Constantes.COLOR_BORDE_MEDIO.toString().substring(2, 8) + 
                 "; -fx-border-width: 1 0 0 0;");
        setPadding(new Insets(4, 12, 4, 12));
        setSpacing(16);
        
        // Panel izquierdo con estado
        HBox panelIzquierdo = new HBox(8);
        panelIzquierdo.setAlignment(Pos.CENTER_LEFT);
        
        // Indicador de estado (círculo verde)
        Rectangle indicador = new Rectangle(8, 8);
        indicador.setFill(Constantes.COLOR_EXITO);
        indicador.setArcWidth(4);
        indicador.setArcHeight(4);
        
        lblEstado = new Label("Conectado");
        lblEstado.setFont(Constantes.FUENTE_PEQUENA);
        lblEstado.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        
        panelIzquierdo.getChildren().addAll(indicador, lblEstado);
        
        // Panel derecho con usuario y fecha
        HBox panelDerecho = new HBox(16);
        panelDerecho.setAlignment(Pos.CENTER_RIGHT);
        
        lblUsuario = new Label("Usuario: Admin");
        lblUsuario.setFont(Constantes.FUENTE_PEQUENA);
        lblUsuario.setTextFill(Constantes.COLOR_TEXTO_SECUNDARIO);
        
        lblFecha = new Label(UtilidadesGUI.obtenerFechaHoraActual());
        lblFecha.setFont(Constantes.FUENTE_PEQUENA);
        lblFecha.setTextFill(Constantes.COLOR_TEXTO_SECUNDARIO);
        
        panelDerecho.getChildren().addAll(lblUsuario, lblFecha);
        
        // Espaciador flexible
        Region espaciador = new Region();
        HBox.setHgrow(espaciador, javafx.scene.layout.Priority.ALWAYS);
        
        getChildren().addAll(panelIzquierdo, espaciador, panelDerecho);
    }
    
    public void setEstado(String estado) {
        lblEstado.setText(estado);
    }
    
    public void setUsuario(String usuario) {
        lblUsuario.setText("Usuario: " + usuario);
    }
    
    public void actualizarFecha() {
        lblFecha.setText(UtilidadesGUI.obtenerFechaHoraActual());
    }
}
