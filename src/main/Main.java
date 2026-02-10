package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilidades.Constantes;
import utilidades.GestorVentanas;

/**
 * Clase principal de la aplicación Sistema de Reserva de Hoteles
 * 
 * @author Sistema de Reservas
 * @version 2.0
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Sistema de Reserva de Hoteles");
			primaryStage.setWidth(Constantes.ANCHO_VENTANA);
			primaryStage.setHeight(Constantes.ALTO_VENTANA);
			primaryStage.setMinWidth(1024);
			primaryStage.setMinHeight(600);
			
			GestorVentanas gestorVentanas = new GestorVentanas();
			Scene scene = new Scene(gestorVentanas.getContenedor(), Constantes.ANCHO_VENTANA, Constantes.ALTO_VENTANA);
			
			// Cargar CSS si existe
			try {
				java.net.URL cssUrl = getClass().getResource("/main/resources/styles/estilos.css");
				if (cssUrl != null) {
					String cssPath = cssUrl.toExternalForm();
					scene.getStylesheets().add(cssPath);
					System.out.println("CSS cargado: " + cssPath);
				} else {
					System.err.println("Error al cargar CSS: recurso /main/resources/styles/estilos.css no encontrado en el classpath");
				}
				
				// Cargar tema AED para ComboBox
				java.net.URL themeUrl = getClass().getResource("/main/resources/styles/aed-theme.css");
				if (themeUrl != null) {
					String themePath = themeUrl.toExternalForm();
					scene.getStylesheets().add(themePath);
					System.out.println("Tema AED cargado: " + themePath);
				} else {
					System.err.println("Advertencia: tema AED no encontrado en /main/resources/styles/aed-theme.css");
				}
			} catch (Exception e) {
				System.err.println("Error al cargar CSS: " + e.getMessage());
				e.printStackTrace();
			}
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			gestorVentanas.mostrarPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
