package ventanas.dialogos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilidades.Constantes;

/**
 * Clase base para todos los diálogos del sistema
 */
public class DialogoBase {
    
    protected Stage stage;
    protected VBox root;
    protected HBox titleBar;
    protected VBox content;
    protected HBox buttons;
    protected String titulo;
    protected Color colorTitulo;
    
    public DialogoBase(String titulo, Color colorTitulo, double ancho, double alto) {
        this.titulo = titulo;
        this.colorTitulo = colorTitulo;
        
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        
        root = new VBox();
        root.setPrefWidth(ancho);
        root.setSpacing(0); // Sin espacio entre elementos hijos
        // No establecer altura fija - dejar que se calcule automáticamente según el contenido
        root.setStyle("-fx-background-color: #" + Constantes.COLOR_SECUNDARIO.toString().substring(2, 8) + 
                     "; -fx-border-color: #" + Constantes.COLOR_BORDE_OSCURO.toString().substring(2, 8) + 
                     "; -fx-border-width: 1;");
        
        // Efecto de sombra exacto según diseño: blur: 8, offset: 4,4, color: #00000040
        root.setEffect(new javafx.scene.effect.DropShadow(8, 4, 4, Color.color(0, 0, 0, 0.25)));
        
        crearTitleBar();
        content = new VBox();
        content.setPadding(new Insets(20));
        content.setStyle("-fx-background-color: #" + Constantes.COLOR_BLANCO.toString().substring(2, 8) + ";"); // Fondo blanco para el contenido
        // Permitir que el content crezca según su contenido sin límites
        content.setMinHeight(javafx.scene.layout.Region.USE_PREF_SIZE);
        content.setPrefHeight(javafx.scene.layout.Region.USE_COMPUTED_SIZE);
        content.setMaxHeight(Double.MAX_VALUE); // Permitir que crezca sin límites
        
        buttons = new HBox(8);
        buttons.setPadding(new Insets(12, 20, 12, 20));
        buttons.setAlignment(Pos.CENTER);
        buttons.setStyle("-fx-border-color: #" + Constantes.COLOR_BORDE_CLARO.toString().substring(2, 8) + 
                        "; -fx-border-width: 1 0 0 0;");
        // Permitir que los botones ocupen su espacio necesario
        // Asegurar altura mínima suficiente para que los botones no se corten
        buttons.setMinHeight(44); // Altura mínima: padding (12*2=24) + botón (~32) = ~56, pero usamos 44 como mínimo seguro
        buttons.setPrefHeight(javafx.scene.layout.Region.USE_COMPUTED_SIZE);
        buttons.setMaxHeight(javafx.scene.layout.Region.USE_PREF_SIZE); // Los botones no necesitan crecer, solo ocupar su espacio
        
        root.getChildren().addAll(titleBar, content, buttons);
        // Asegurar que el root pueda crecer según su contenido sin límites
        root.setMinHeight(javafx.scene.layout.Region.USE_PREF_SIZE);
        root.setMaxHeight(Double.MAX_VALUE); // Permitir que crezca sin límites
    }
    
    private void crearTitleBar() {
        titleBar = new HBox();
        titleBar.setAlignment(Pos.CENTER_LEFT);
        titleBar.setPrefHeight(28);
        titleBar.setMinHeight(28);
        titleBar.setMaxHeight(28);
        titleBar.setPadding(new Insets(0, 8, 0, 8));
        titleBar.setStyle("-fx-background-color: #" + colorTitulo.toString().substring(2, 8) + 
                         "; -fx-border-width: 0;");
        
        Label lblTitulo = new Label(titulo);
        lblTitulo.setFont(javafx.scene.text.Font.font("Arial", 11)); // fontSize: 11, fontWeight: normal
        lblTitulo.setTextFill(Color.WHITE);
        
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);
        
        StackPane btnCerrar = new StackPane();
        btnCerrar.setPrefSize(28, 28);
        btnCerrar.setMinSize(28, 28);
        btnCerrar.setMaxSize(28, 28);
        btnCerrar.setStyle("-fx-background-color: #E81123; -fx-cursor: hand;");
        btnCerrar.setAlignment(Pos.CENTER);
        
        // Usar icono X de FontAwesome (14x14 según diseño)
        componentes.IconoSimple iconoCerrar = componentes.IconoSimple.crearIconoX(14, Color.WHITE);
        btnCerrar.getChildren().add(iconoCerrar);
        
        btnCerrar.setOnMouseClicked(_ -> stage.close());
        
        titleBar.getChildren().addAll(lblTitulo, spacer, btnCerrar);
    }
    
    protected Button crearBoton(String texto, Color colorFondo, Color colorBorde, Color colorTexto) {
        Button btn = new Button(texto);
        btn.setFont(Constantes.FUENTE_NORMAL);
        btn.setPadding(new Insets(6, 20, 6, 20));
        btn.setStyle("-fx-background-color: #" + colorFondo.toString().substring(2, 8) + 
                    "; -fx-text-fill: #" + colorTexto.toString().substring(2, 8) + 
                    "; -fx-border-color: #" + colorBorde.toString().substring(2, 8) + 
                    "; -fx-border-width: 1; -fx-border-radius: 2; -fx-background-radius: 2;");
        btn.setCursor(javafx.scene.Cursor.HAND);
        return btn;
    }
    
    protected Button crearBotonPrimario(String texto) {
        return crearBoton(texto, Constantes.COLOR_PRIMARIO, Constantes.COLOR_PRIMARIO_OSCURO, Color.WHITE);
    }
    
    protected Button crearBotonSecundario(String texto) {
        return crearBoton(texto, Constantes.COLOR_SECUNDARIO, Constantes.COLOR_BORDE_MEDIO, Constantes.COLOR_TEXTO_PRINCIPAL);
    }
    
    protected Button crearBotonPeligro(String texto) {
        Color rojo = Color.rgb(220, 38, 38); // #DC2626
        Color rojoOscuro = Color.rgb(185, 28, 28); // #B91C1C
        return crearBoton(texto, rojo, rojoOscuro, Color.WHITE);
    }
    
    public void mostrar() {
        // Crear la escena primero
        javafx.scene.Scene scene = new javafx.scene.Scene(root);
        // Cargar CSS para que los checkboxes y otros componentes tengan el estilo correcto
        try {
            java.net.URL cssUrl = getClass().getResource("/main/resources/styles/estilos.css");
            if (cssUrl != null) {
                String cssPath = cssUrl.toExternalForm();
                scene.getStylesheets().add(cssPath);
            } else {
                System.err.println("Error al cargar CSS en diálogo: recurso /main/resources/styles/estilos.css no encontrado");
            }
            
            // Cargar tema AED para ComboBox
            java.net.URL themeUrl = getClass().getResource("/main/resources/styles/aed-theme.css");
            if (themeUrl != null) {
                scene.getStylesheets().add(themeUrl.toExternalForm());
            }
        } catch (Exception e) {
            System.err.println("Error al cargar CSS en diálogo: " + e.getMessage());
        }
        stage.setScene(scene);
        
        // Asegurar que el root calcule su altura correctamente antes de mostrar
        // Aplicar CSS y layout para calcular tamaños después de crear la escena
        root.applyCss();
        root.layout();
        
        // Forzar el cálculo del tamaño preferido de cada componente
        content.applyCss();
        content.layout();
        buttons.applyCss();
        buttons.layout();
        
        // Calcular altura total: titleBar (28) + content + buttons
        // Usar prefHeight(-1) que calcula el tamaño preferido basado en el contenido
        double alturaContent = content.prefHeight(-1);
        double alturaButtons = buttons.prefHeight(-1);
        
        // Si alguna altura es 0 o negativa, usar el tamaño calculado después del layout
        if (alturaContent <= 0) {
            alturaContent = content.getHeight();
        }
        if (alturaButtons <= 0) {
            alturaButtons = buttons.getHeight();
        }
        
        // Si alguna altura es 0 o negativa, usar el tamaño calculado después del layout
        if (alturaContent <= 0) {
            alturaContent = content.getHeight();
        }
        if (alturaButtons <= 0) {
            alturaButtons = buttons.getHeight();
        }
        
        // Si aún no tenemos altura válida, usar altura mínima estimada
        if (alturaContent <= 0) {
            alturaContent = 100; // Altura mínima estimada para content
        }
        if (alturaButtons <= 0) {
            alturaButtons = 44; // Altura mínima para buttons
        }
        
        // Calcular altura total
        double alturaTotal = 28 + alturaContent + alturaButtons;
        
        // Establecer la altura calculada
        root.setPrefHeight(alturaTotal);
        
        // Forzar un segundo layout con la altura establecida
        root.layout();
        
        stage.showAndWait();
    }
    
    public void cerrar() {
        stage.close();
    }
    
    public Stage getStage() {
        return stage;
    }
}

