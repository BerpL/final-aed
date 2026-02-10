package componentes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;

/**
 * Componente reutilizable para tablas de datos
 */
public class TablaDatos extends VBox {
    
    private TableView<ObservableList<String>> tabla;
    private ObservableList<ObservableList<String>> datos;
    @SuppressWarnings("unused")
    private String[] columnas; // Se usa en el código, pero Eclipse no lo detecta correctamente
    
    // Callbacks para acciones de botones
    private java.util.function.Consumer<Integer> onVer;
    private java.util.function.Consumer<Integer> onEditar;
    private java.util.function.Consumer<Integer> onEliminar;
    
    public TablaDatos(String[] columnas) {
        this.columnas = columnas;
        // Borde exterior de la tabla: #A0A0A0 (como en el diseño)
        setStyle("-fx-border-color: #A0A0A0; -fx-border-width: 1;");
        // El VBox padre debe llamar a VBox.setVgrow(this, Priority.ALWAYS) para que ocupe todo el espacio
        
        datos = FXCollections.observableArrayList();
        tabla = new TableView<>(datos);
        // Hacer que la tabla ocupe todo el ancho y alto disponible
        // CONSTRAINED_RESIZE_POLICY está deprecado desde JavaFX 20, pero aún funciona correctamente
        @SuppressWarnings("deprecation")
        var resizePolicy = TableView.CONSTRAINED_RESIZE_POLICY;
        tabla.setColumnResizePolicy(resizePolicy);
        VBox.setVgrow(tabla, javafx.scene.layout.Priority.ALWAYS);
        
        // Crear columnas
        for (int i = 0; i < columnas.length; i++) {
            final int colIndex = i;
            TableColumn<ObservableList<String>, String> columna;
            
            if (columnas[i].equals("Acciones")) {
                // Columna especial para acciones - ocupa el espacio restante
                columna = new TableColumn<>(columnas[i]);
                // No establecer ancho fijo, ocupará el espacio restante
                columna.setCellFactory(_ -> new TableCell<ObservableList<String>, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || getIndex() < 0 || getIndex() >= datos.size()) {
                            setGraphic(null);
                        } else {
                            HBox panel = new HBox(8);
                            panel.setAlignment(javafx.geometry.Pos.CENTER);
                            panel.setPadding(new Insets(0)); // Sin padding en el panel, el padding está en la celda
                            
                            BotonAccion btnVer = BotonAccion.crearBotonVer();
                            BotonAccion btnEditar = BotonAccion.crearBotonEditar();
                            BotonAccion btnEliminar = BotonAccion.crearBotonEliminar();
                            
                            final int filaIndex = getIndex();
                            
                            btnVer.setOnMouseClicked(_ -> {
                                if (onVer != null) {
                                    onVer.accept(filaIndex);
                                }
                            });
                            
                            btnEditar.setOnMouseClicked(_ -> {
                                if (onEditar != null) {
                                    onEditar.accept(filaIndex);
                                }
                            });
                            
                            btnEliminar.setOnMouseClicked(_ -> {
                                if (onEliminar != null) {
                                    onEliminar.accept(filaIndex);
                                }
                            });
                            
                            panel.getChildren().addAll(btnVer, btnEditar, btnEliminar);
                            setGraphic(panel);
                            setAlignment(Pos.CENTER); // Centrar verticalmente el contenido
                            
                            // Alternar colores de fondo (exactamente como en el diseño)
                            // La columna de acciones es siempre la última, así que no tiene borde derecho
                            if (getIndex() % 2 == 0) {
                                setStyle("-fx-background-color: #FFFFFF; " +
                                         "-fx-border-color: #D0D0D0; " +
                                         "-fx-border-width: 0 0 1 0; " +
                                         "-fx-padding: 6 8 6 8;"); // Padding vertical aumentado para centrado
                            } else {
                                setStyle("-fx-background-color: #F8F8F8; " +
                                         "-fx-border-color: #D0D0D0; " +
                                         "-fx-border-width: 0 0 1 0; " +
                                         "-fx-padding: 6 8 6 8;"); // Padding vertical aumentado para centrado
                            }
                        }
                    }
                });
            } else if (columnas[i].equals("Ver")) {
                // Columna especial para reportes - solo botones Ver e Imprimir
                columna = new TableColumn<>(columnas[i]);
                columna.setCellFactory(_ -> new TableCell<ObservableList<String>, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || getIndex() < 0 || getIndex() >= datos.size()) {
                            setGraphic(null);
                            setText(null);
                            setStyle("");
                        } else {
                            HBox panel = new HBox(8);
                            panel.setAlignment(Pos.CENTER);
                            panel.setPadding(new Insets(0)); // Sin padding en el panel, el padding está en la celda

                            // Botón Ver (ojo azul #0078D4)
                            StackPane btnVer = new StackPane();
                            btnVer.setPrefSize(20, 20);
                            btnVer.setMinSize(20, 20);
                            btnVer.setMaxSize(20, 20);
                            btnVer.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
                            IconoSimple iconoVer = IconoSimple.crearIconoVer(16, Color.web("#0078D4")); // Tamaño aumentado a 16px
                            btnVer.getChildren().add(iconoVer);
                            btnVer.setOnMouseEntered(_ -> {
                                if (iconoVer.getFontIcon() != null) {
                                    iconoVer.getFontIcon().setOpacity(0.7);
                                }
                            });
                            btnVer.setOnMouseExited(_ -> {
                                if (iconoVer.getFontIcon() != null) {
                                    iconoVer.getFontIcon().setOpacity(1.0);
                                }
                            });

                            // Botón Imprimir (impresora gris #666666)
                            StackPane btnImprimir = new StackPane();
                            btnImprimir.setPrefSize(20, 20);
                            btnImprimir.setMinSize(20, 20);
                            btnImprimir.setMaxSize(20, 20);
                            btnImprimir.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
                            IconoSimple iconoImprimir = IconoSimple.crearIconoPrinter(16, Color.web("#666666")); // Tamaño aumentado a 16px
                            btnImprimir.getChildren().add(iconoImprimir);
                            btnImprimir.setOnMouseEntered(_ -> {
                                if (iconoImprimir.getFontIcon() != null) {
                                    iconoImprimir.getFontIcon().setOpacity(0.7);
                                }
                            });
                            btnImprimir.setOnMouseExited(_ -> {
                                if (iconoImprimir.getFontIcon() != null) {
                                    iconoImprimir.getFontIcon().setOpacity(1.0);
                                }
                            });

                            panel.getChildren().addAll(btnVer, btnImprimir);
                            setGraphic(panel);
                            setText(null);
                            setAlignment(Pos.CENTER); // Centrar verticalmente el contenido

                            // Alternar colores de fondo
                            if (getIndex() % 2 == 0) {
                                setStyle("-fx-background-color: #FFFFFF; " +
                                         "-fx-border-color: #D0D0D0; " +
                                         "-fx-border-width: 0 0 1 0; " +
                                         "-fx-padding: 6 8 6 8;"); // Padding vertical aumentado para centrado
                            } else {
                                setStyle("-fx-background-color: #F8F8F8; " +
                                         "-fx-border-color: #D0D0D0; " +
                                         "-fx-border-width: 0 0 1 0; " +
                                         "-fx-padding: 6 8 6 8;"); // Padding vertical aumentado para centrado
                            }
                        }
                    }
                });
            } else {
                columna = new TableColumn<>(columnas[i]);
                
                // Establecer anchos específicos según el diseño
                // Para la tabla de Huéspedes
                if (columnas[i].equals("ID")) {
                    columna.setPrefWidth(60);
                    columna.setMinWidth(60);
                    columna.setMaxWidth(60);
                } else if (columnas[i].equals("Nombre Completo")) {
                    columna.setPrefWidth(180);
                    columna.setMinWidth(150);
                } else if (columnas[i].equals("Documento")) {
                    columna.setPrefWidth(100);
                    columna.setMinWidth(80);
                } else if (columnas[i].equals("Teléfono")) {
                    columna.setPrefWidth(100);
                    columna.setMinWidth(80);
                } else if (columnas[i].equals("VIP")) {
                    columna.setPrefWidth(60);
                    columna.setMinWidth(60);
                    columna.setMaxWidth(60);
                } else if (columnas[i].equals("Número")) {
                    columna.setPrefWidth(80);
                    columna.setMinWidth(60);
                } else if (columnas[i].equals("Tipo")) {
                    columna.setPrefWidth(150);
                    columna.setMinWidth(120);
                } else if (columnas[i].equals("Piso")) {
                    columna.setPrefWidth(60);
                    columna.setMinWidth(50);
                } else if (columnas[i].equals("Estado")) {
                    columna.setPrefWidth(100);
                    columna.setMinWidth(80);
                } else if (columnas[i].equals("Precio")) {
                    columna.setPrefWidth(100);
                    columna.setMinWidth(80);
                } else if (columnas[i].equals("Código")) {
                    columna.setPrefWidth(120);
                    columna.setMinWidth(100);
                } else if (columnas[i].equals("Nombre")) {
                    columna.setPrefWidth(180);
                    columna.setMinWidth(150);
                } else if (columnas[i].equals("Capacidad")) {
                    columna.setPrefWidth(80);
                    columna.setMinWidth(60);
                } else if (columnas[i].equals("Camas")) {
                    columna.setPrefWidth(60);
                    columna.setMinWidth(50);
                } else if (columnas[i].equals("Área")) {
                    columna.setPrefWidth(60);
                    columna.setMinWidth(50);
                } else if (columnas[i].equals("Huésped")) {
                    columna.setPrefWidth(150);
                    columna.setMinWidth(120);
                } else if (columnas[i].equals("Habitación")) {
                    columna.setPrefWidth(120);
                    columna.setMinWidth(100);
                } else if (columnas[i].equals("Check-In") || columnas[i].equals("Check-Out")) {
                    columna.setPrefWidth(100);
                    columna.setMinWidth(90);
                } else {
                    // Para otras columnas, usar ancho por defecto pero permitir expansión
                    columna.setPrefWidth(120);
                    columna.setMinWidth(80);
                }
                
                columna.setCellValueFactory(param -> {
                    ObservableList<String> row = param.getValue();
                    if (row != null && colIndex < row.size()) {
                        return javafx.beans.binding.Bindings.createStringBinding(() -> row.get(colIndex));
                    }
                    return javafx.beans.binding.Bindings.createStringBinding(() -> "");
                });
                
                columna.setCellFactory(_ -> new TableCell<ObservableList<String>, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(item);
                            setFont(javafx.scene.text.Font.font("Arial", 11));
                            setPadding(new Insets(6, 8, 6, 8)); // Padding vertical aumentado para centrado
                            
                            // Estilo para columna VIP (exactamente como en el diseño: #107C10 para "Sí")
                            if (columnas[colIndex].equals("VIP")) {
                                if ("Si".equals(item) || "Sí".equals(item)) {
                                    setTextFill(Color.web("#107C10")); // Verde exacto del diseño
                                    setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 11));
                                    setAlignment(Pos.CENTER);
                                } else {
                                    setTextFill(Color.web("#666666"));
                                    setAlignment(Pos.CENTER);
                                }
                            } else if (columnas[colIndex].equals("Estado")) {
                                // Para columna Estado, centrar verticalmente
                                setTextFill(Color.web("#333333"));
                                setAlignment(Pos.CENTER_LEFT);
                            } else {
                                setTextFill(Color.web("#333333"));
                                setAlignment(Pos.CENTER_LEFT);
                            }
                            
                            // Alternar colores de fondo (exactamente como en el diseño)
                            // Determinar si es la última columna
                            boolean isLastColumn = (colIndex == columnas.length - 1);
                            String borderWidth = isLastColumn ? "0 0 1 0" : "0 1 1 0";
                            
                            if (getIndex() % 2 == 0) {
                                setStyle("-fx-background-color: #FFFFFF; " +
                                         "-fx-border-color: #D0D0D0; " +
                                         "-fx-border-width: " + borderWidth + "; " +
                                         "-fx-padding: 4 8 4 8;");
                            } else {
                                setStyle("-fx-background-color: #F8F8F8; " +
                                         "-fx-border-color: #D0D0D0; " +
                                         "-fx-border-width: " + borderWidth + "; " +
                                         "-fx-padding: 4 8 4 8;");
                            }
                        }
                    }
                });
            }
            
            tabla.getColumns().add(columna);
        }
        
        // Estilo del header y tabla
               tabla.setStyle("-fx-font-size: 11px; -fx-font-family: 'Arial'; " +
                             "-fx-border-color: transparent;"); // Sin borde en la tabla, el borde está en el VBox contenedor
               tabla.getStyleClass().add("custom-table");
               tabla.setFixedCellSize(32); // Altura fija de 32px para cada fila (aumentada para mejor centrado vertical)
        
        // Hacer que la tabla ocupe todo el espacio disponible
        VBox.setVgrow(tabla, javafx.scene.layout.Priority.ALWAYS);
        
        // Aplicar estilos del header después de que la tabla se muestre
        javafx.application.Platform.runLater(() -> {
            try {
                javafx.scene.Node headerBackground = tabla.lookup(".column-header-background");
                if (headerBackground != null) {
                    headerBackground.setStyle("-fx-background-color: #E0E0E0; " +
                                             "-fx-pref-height: 28px; " +
                                             "-fx-min-height: 28px; " +
                                             "-fx-max-height: 28px;");
                }
                
                // Aplicar estilo a cada columna del header
                java.util.List<javafx.scene.Node> headers = new java.util.ArrayList<>();
                tabla.lookupAll(".column-header").forEach(headers::add);
                
                for (int i = 0; i < headers.size(); i++) {
                    javafx.scene.Node node = headers.get(i);
                    boolean isLast = (i == headers.size() - 1);
                    String borderWidth = isLast ? "0 0 1 0" : "0 1 1 0";
                    
                    node.setStyle(
                        "-fx-background-color: #E0E0E0; " +
                        "-fx-border-color: #A0A0A0; " +
                        "-fx-border-width: " + borderWidth + "; " +
                        "-fx-padding: 4 8 4 8; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 12px; " +
                        "-fx-text-fill: #333333; " +
                        "-fx-alignment: center-left;"
                    );
                }
            } catch (Exception e) {
                // Si falla, continuar sin el estilo del header
            }
        });
        
        getChildren().add(tabla);
    }
    
    public void agregarFila(String[] datosFila) {
        ObservableList<String> fila = FXCollections.observableArrayList(datosFila);
        datos.add(fila);
    }
    
    public void limpiar() {
        datos.clear();
    }
    
    public int getFilaSeleccionada() {
        return tabla.getSelectionModel().getSelectedIndex();
    }
    
    public String getValor(int fila, int columna) {
        if (fila >= 0 && fila < datos.size() && columna >= 0 && columna < datos.get(fila).size()) {
            return datos.get(fila).get(columna);
        }
        return null;
    }
    
    public void eliminarFila(int fila) {
        if (fila >= 0 && fila < datos.size()) {
            datos.remove(fila);
        }
    }
    
    public TableView<ObservableList<String>> getTabla() {
        return tabla;
    }
    
    // Métodos para configurar callbacks
    public void setOnVer(java.util.function.Consumer<Integer> callback) {
        this.onVer = callback;
    }
    
    public void setOnEditar(java.util.function.Consumer<Integer> callback) {
        this.onEditar = callback;
    }
    
    public void setOnEliminar(java.util.function.Consumer<Integer> callback) {
        this.onEliminar = callback;
    }
}
