package componentes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import utilidades.Constantes;

/**
 * Componente reutilizable para campos de formulario con etiqueta
 */
public class CampoFormulario extends HBox {
    
    private Label lblEtiqueta;
    private TextField txtCampo;
    
    public CampoFormulario(String etiqueta, int ancho) {
        setSpacing(12);
        setAlignment(Pos.CENTER_LEFT);
        setPrefHeight(30);
        
        lblEtiqueta = new Label(etiqueta + ":");
        lblEtiqueta.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblEtiqueta.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        
        txtCampo = new TextField();
        txtCampo.setPrefWidth(ancho);
        txtCampo.setPrefHeight(24);
        txtCampo.setMinHeight(24);
        txtCampo.setMaxHeight(24);
        txtCampo.setStyle("-fx-border-color: #" + Constantes.COLOR_BORDE_OSCURO.toString().substring(2, 8) + 
                         "; -fx-background-color: #" + Constantes.COLOR_BLANCO.toString().substring(2, 8) + 
                         "; -fx-padding: 4 8 4 8; " +
                         "-fx-effect: null;"); // Sin sombras
        txtCampo.setFont(Constantes.FUENTE_NORMAL);
        
        getChildren().addAll(lblEtiqueta, txtCampo);
    }
    
    public String getValor() {
        return txtCampo.getText();
    }
    
    public void setValor(String valor) {
        txtCampo.setText(valor);
    }
    
    public void limpiar() {
        txtCampo.setText("");
    }
    
    public TextField getCampo() {
        return txtCampo;
    }
}
