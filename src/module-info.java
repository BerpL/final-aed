module Reserva_de_hoteles {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires org.kordamp.ikonli.core;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.ikonli.fontawesome5;
	
	opens main to javafx.graphics, javafx.fxml;
	opens ventanas to javafx.fxml;
	opens componentes to javafx.fxml;
	opens utilidades to javafx.fxml;
}

