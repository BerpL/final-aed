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
				scene.getStylesheets().add(getClass().getResource("/styles/estilos.css").toExternalForm());
			} catch (Exception e) {
				// CSS opcional, continuar sin él
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
