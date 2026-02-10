package ventanas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import componentes.BarraEstado;
import componentes.BarraMenu;
import componentes.BarraTitulo;
import componentes.PanelNavegacion;
import utilidades.Constantes;
import utilidades.GestorVentanas;

/**
 * Ventana principal del sistema de reserva de hoteles
 */
public class MainWindow {
    
    private BorderPane root;
    private BarraTitulo barraTitulo;
    private BarraMenu barraMenu;
    private PanelNavegacion panelNavegacion;
    private BarraEstado barraEstado;
    private VBox panelContenido;
    private GestorVentanas gestorVentanas;
    
    public MainWindow() {
        root = new BorderPane();
        root.setStyle("-fx-background-color: #" + Constantes.COLOR_FONDO.toString().substring(2, 8) + ";");
        
        // Barra de título
        barraTitulo = new BarraTitulo("Sistema de Reserva de Hoteles");
        root.setTop(barraTitulo);
        
        // Barra de menú
        barraMenu = new BarraMenu();
        
        // Panel principal con navegación y contenido
        BorderPane panelPrincipal = new BorderPane();
        panelPrincipal.setStyle("-fx-background-color: #" + Constantes.COLOR_FONDO.toString().substring(2, 8) + ";");
        
        // Panel de navegación lateral
        panelNavegacion = new PanelNavegacion();
        panelNavegacion.seleccionarItem(0); // Seleccionar "Navegación" en la pantalla principal
        panelPrincipal.setLeft(panelNavegacion);
        
        // Panel de contenido central
        panelContenido = crearPanelContenido();
        panelPrincipal.setCenter(panelContenido);
        
        // Contenedor para menú y contenido principal
        VBox contenedorPrincipal = new VBox();
        contenedorPrincipal.getChildren().addAll(barraMenu, panelPrincipal);
        VBox.setVgrow(panelPrincipal, javafx.scene.layout.Priority.ALWAYS);
        root.setCenter(contenedorPrincipal);
        
        // Barra de estado
        barraEstado = new BarraEstado();
        root.setBottom(barraEstado);
    }
    
    private VBox crearPanelContenido() {
        VBox panel = new VBox(24);
        panel.setPadding(new Insets(24));
        panel.setStyle("-fx-background-color: #" + Constantes.COLOR_BLANCO.toString().substring(2, 8) + ";");
        
        // Sección de bienvenida
        VBox seccionBienvenida = new VBox(8);
        Label lblTituloBienvenida = new Label("Bienvenido al Sistema de Reserva de Hoteles");
        lblTituloBienvenida.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 24));
        lblTituloBienvenida.setTextFill(Constantes.COLOR_PRIMARIO);
        
        Label lblSubtitulo = new Label("Sistema de gestión de reservas hoteleras y hospedajes");
        lblSubtitulo.setFont(Constantes.FUENTE_SUBTITULO);
        lblSubtitulo.setTextFill(Constantes.COLOR_TEXTO_SECUNDARIO);
        
        seccionBienvenida.getChildren().addAll(lblTituloBienvenida, lblSubtitulo);
        
        // Separador
        Region separador = new Region();
        separador.setPrefHeight(1);
        separador.setMaxHeight(1);
        separador.setStyle("-fx-background-color: #" + Constantes.COLOR_SEPARADOR.toString().substring(2, 8) + ";");
        
        // Sección de descripción
        VBox seccionDescripcion = new VBox(12);
        Label lblTituloDesc = new Label("Descripción del Sistema");
        lblTituloDesc.setFont(Constantes.FUENTE_SUBTITULO);
        lblTituloDesc.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        
        Label lblDescripcion = new Label("Este sistema permite gestionar huéspedes, habitaciones, tipos de habitación y reservas de manera eficiente. Utilice el menú lateral para navegar entre las diferentes secciones del sistema.");
        lblDescripcion.setFont(Constantes.FUENTE_NORMAL);
        lblDescripcion.setTextFill(Constantes.COLOR_TEXTO_SECUNDARIO);
        lblDescripcion.setWrapText(true);
        lblDescripcion.setPrefWidth(600);
        
        seccionDescripcion.getChildren().addAll(lblTituloDesc, lblDescripcion);
        
        // Sección de accesos rápidos
        VBox seccionAccesos = new VBox(12);
        Label lblTituloAccesos = new Label("Accesos Rápidos");
        lblTituloAccesos.setFont(Constantes.FUENTE_SUBTITULO);
        lblTituloAccesos.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        
        HBox panelBotones = new HBox(12);
        panelBotones.setAlignment(Pos.CENTER_LEFT);
        
        Button btnRegistrarHuesped = crearBotonAcceso("Registrar Huésped", false);
        btnRegistrarHuesped.setOnAction(_ -> {
            if (gestorVentanas != null) {
                gestorVentanas.mostrarHuespedes();
            }
        });
        
        Button btnRegistrarHabitacion = crearBotonAcceso("Registrar Habitación", false);
        btnRegistrarHabitacion.setOnAction(_ -> {
            if (gestorVentanas != null) {
                gestorVentanas.mostrarHabitaciones();
            }
        });
        
        Button btnNuevaReserva = crearBotonAcceso("Nueva Reserva", true);
        btnNuevaReserva.setOnAction(_ -> {
            if (gestorVentanas != null) {
                gestorVentanas.mostrarReservas();
            }
        });
        
        Button btnConsultarHabitaciones = crearBotonAcceso("Consultar Habitaciones", false);
        btnConsultarHabitaciones.setOnAction(_ -> {
            if (gestorVentanas != null) {
                gestorVentanas.mostrarHabitaciones();
            }
        });
        
        panelBotones.getChildren().addAll(btnRegistrarHuesped, btnRegistrarHabitacion, btnNuevaReserva, btnConsultarHabitaciones);
        seccionAccesos.getChildren().addAll(lblTituloAccesos, panelBotones);
        
        panel.getChildren().addAll(seccionBienvenida, separador, seccionDescripcion, seccionAccesos);
        
        return panel;
    }
    
    private Button crearBotonAcceso(String texto, boolean destacado) {
        Button boton = new Button(texto);
        boton.setFont(Constantes.FUENTE_NORMAL);
        boton.setPadding(new Insets(8, 16, 8, 16));
        
        if (destacado) {
            boton.setStyle("-fx-background-color: #" + Constantes.COLOR_PRIMARIO.toString().substring(2, 8) + 
                          "; -fx-text-fill: #" + Constantes.COLOR_TEXTO_BLANCO.toString().substring(2, 8) + 
                          "; -fx-border-color: #" + Constantes.COLOR_PRIMARIO_OSCURO.toString().substring(2, 8) + 
                          "; -fx-border-width: 1; -fx-border-radius: 2; -fx-background-radius: 2;");
        } else {
            boton.setStyle("-fx-background-color: #" + Constantes.COLOR_SECUNDARIO.toString().substring(2, 8) + 
                          "; -fx-text-fill: #" + Constantes.COLOR_TEXTO_PRINCIPAL.toString().substring(2, 8) + 
                          "; -fx-border-color: #" + Constantes.COLOR_BORDE_OSCURO.toString().substring(2, 8) + 
                          "; -fx-border-width: 1; -fx-border-radius: 2; -fx-background-radius: 2;");
        }
        
        boton.setOnMouseEntered(_ -> {
            if (destacado) {
                boton.setStyle("-fx-background-color: #" + Constantes.COLOR_PRIMARIO_OSCURO.toString().substring(2, 8) + 
                              "; -fx-text-fill: #" + Constantes.COLOR_TEXTO_BLANCO.toString().substring(2, 8) + 
                              "; -fx-border-color: #" + Constantes.COLOR_PRIMARIO_OSCURO.toString().substring(2, 8) + 
                              "; -fx-border-width: 1; -fx-border-radius: 2; -fx-background-radius: 2;");
            } else {
                boton.setStyle("-fx-background-color: #" + Constantes.COLOR_BORDE_CLARO.toString().substring(2, 8) + 
                              "; -fx-text-fill: #" + Constantes.COLOR_TEXTO_PRINCIPAL.toString().substring(2, 8) + 
                              "; -fx-border-color: #" + Constantes.COLOR_BORDE_OSCURO.toString().substring(2, 8) + 
                              "; -fx-border-width: 1; -fx-border-radius: 2; -fx-background-radius: 2;");
            }
        });
        
        boton.setOnMouseExited(_ -> {
            if (destacado) {
                boton.setStyle("-fx-background-color: #" + Constantes.COLOR_PRIMARIO.toString().substring(2, 8) + 
                              "; -fx-text-fill: #" + Constantes.COLOR_TEXTO_BLANCO.toString().substring(2, 8) + 
                              "; -fx-border-color: #" + Constantes.COLOR_PRIMARIO_OSCURO.toString().substring(2, 8) + 
                              "; -fx-border-width: 1; -fx-border-radius: 2; -fx-background-radius: 2;");
            } else {
                boton.setStyle("-fx-background-color: #" + Constantes.COLOR_SECUNDARIO.toString().substring(2, 8) + 
                              "; -fx-text-fill: #" + Constantes.COLOR_TEXTO_PRINCIPAL.toString().substring(2, 8) + 
                              "; -fx-border-color: #" + Constantes.COLOR_BORDE_OSCURO.toString().substring(2, 8) + 
                              "; -fx-border-width: 1; -fx-border-radius: 2; -fx-background-radius: 2;");
            }
        });
        
        return boton;
    }
    
    public BorderPane getRoot() {
        return root;
    }
    
    public PanelNavegacion getPanelNavegacion() {
        return panelNavegacion;
    }
    
    public void setGestorVentanas(GestorVentanas gestorVentanas) {
        this.gestorVentanas = gestorVentanas;
    }
}
