package ventanas.dialogos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import componentes.IconoSimple;
import componentes.TablaDatos;
import utilidades.Constantes;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

// Imports de Apache PDFBox
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 * Diálogo para mostrar la vista previa de un reporte
 */
public class DialogoVistaPreviaReporte extends DialogoBase {
    
    private String tipoReporte;
    private List<String[]> datosReporte;
    private TablaDatos tablaVistaPrevia;
    
    public DialogoVistaPreviaReporte(String tipoReporte, List<String[]> datosReporte) {
        super("Vista Previa de Reporte", Constantes.COLOR_PRIMARIO, 800, 0);
        
        this.tipoReporte = tipoReporte;
        this.datosReporte = datosReporte;
        
        content.setSpacing(16);
        content.setPadding(new Insets(20));
        content.setVisible(true);
        content.setManaged(true);
        
        // Título del reporte
        Label lblTituloReporte = new Label(tipoReporte);
        lblTituloReporte.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 14));
        lblTituloReporte.setTextFill(Constantes.COLOR_TEXTO_PRINCIPAL);
        
        // Crear tabla de vista previa
        if (datosReporte != null && !datosReporte.isEmpty()) {
            String[] columnas = datosReporte.get(0); // Primera fila son las columnas
            tablaVistaPrevia = new TablaDatos(columnas);
            
            // Agregar datos (saltando la primera fila que son las columnas)
            for (int i = 1; i < datosReporte.size(); i++) {
                tablaVistaPrevia.agregarFila(datosReporte.get(i));
            }
        } else {
            // Tabla vacía si no hay datos
            tablaVistaPrevia = new TablaDatos(new String[]{"No hay datos disponibles"});
        }
        
        content.getChildren().addAll(lblTituloReporte, tablaVistaPrevia);
        
        // Botones: Descargar y Cerrar
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(12);
        
        // Botón Descargar
        HBox btnDescargar = new HBox(8);
        btnDescargar.setAlignment(Pos.CENTER);
        btnDescargar.setPadding(new Insets(6, 24, 6, 24));
        btnDescargar.setStyle("-fx-background-color: #" + Constantes.COLOR_PRIMARIO.toString().substring(2, 8) + 
                            "; -fx-border-color: #" + Constantes.COLOR_PRIMARIO_OSCURO.toString().substring(2, 8) + 
                            "; -fx-border-width: 1; " +
                            "-fx-border-radius: 2; " +
                            "-fx-background-radius: 2; " +
                            "-fx-cursor: hand;");
        
        IconoSimple iconoDescargar = IconoSimple.crearIconoDownload(14, Color.WHITE);
        Label lblDescargar = new Label("Descargar");
        lblDescargar.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12));
        lblDescargar.setTextFill(Color.WHITE);
        btnDescargar.getChildren().addAll(iconoDescargar, lblDescargar);
        
        btnDescargar.setOnMouseClicked(_ -> descargarPDF());
        
        // Botón Cerrar
        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setFont(javafx.scene.text.Font.font("Arial", 12));
        btnCerrar.setStyle("-fx-background-color: #" + Constantes.COLOR_SECUNDARIO.toString().substring(2, 8) + 
                         "; -fx-text-fill: #333333; " +
                         "-fx-border-color: #" + Constantes.COLOR_BORDE_MEDIO.toString().substring(2, 8) + 
                         "; -fx-border-width: 1; " +
                         "-fx-border-radius: 2; " +
                         "-fx-background-radius: 2; " +
                         "-fx-padding: 6 24 6 24; " +
                         "-fx-cursor: hand;");
        btnCerrar.setOnAction(_ -> cerrar());
        
        buttons.getChildren().addAll(btnDescargar, btnCerrar);
    }
    
    private void descargarPDF() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar Reporte PDF");
            fileChooser.setInitialFileName(tipoReporte.replace(" ", "_") + ".pdf");
            fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
            );
            
            File archivo = fileChooser.showSaveDialog(stage);
            if (archivo != null) {
                // Intentar generar PDF real usando Apache PDFBox
                boolean pdfGenerado = generarPDFReal(archivo);
                
                if (pdfGenerado) {
                    // Mostrar mensaje de éxito
                    DialogoMensaje dialogoExito = new DialogoMensaje(
                        "Éxito",
                        "El reporte PDF se ha descargado correctamente.",
                        DialogoMensaje.TipoMensaje.EXITO
                    );
                    dialogoExito.mostrar();
                } else {
                    // Si PDFBox no está disponible, mostrar error
                    DialogoMensaje dialogoError = new DialogoMensaje(
                        "Error",
                        "Apache PDFBox no está disponible. Por favor, descarga pdfbox-2.0.x.jar y fontbox-2.0.x.jar desde https://pdfbox.apache.org/download.html y agrégalos al classpath del proyecto.",
                        DialogoMensaje.TipoMensaje.ERROR
                    );
                    dialogoError.mostrar();
                }
            }
        } catch (Exception e) {
            // Mostrar mensaje de error
            DialogoMensaje dialogoError = new DialogoMensaje(
                "Error",
                "No se pudo descargar el reporte: " + e.getMessage(),
                DialogoMensaje.TipoMensaje.ERROR
            );
            dialogoError.mostrar();
        }
    }
    
    private boolean generarPDFReal(File archivo) {
        try (PDDocument document = new PDDocument()) {
            // Crear una nueva página
            PDPage page = new PDPage();
            document.addPage(page);
            
            // Crear content stream
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Obtener fuentes
                PDFont fontBold = PDType1Font.HELVETICA_BOLD;
                PDFont fontNormal = PDType1Font.HELVETICA;
                
                float margin = 50f;
                float yStart = 750f;
                float yPosition = yStart;
                float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
                
                // Escribir título del reporte
                contentStream.beginText();
                contentStream.setFont(fontBold, 18f);
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("REPORTE: " + tipoReporte);
                contentStream.endText();
                
                yPosition -= 30f;
                
                // Escribir fecha de generación
                contentStream.beginText();
                contentStream.setFont(fontNormal, 10f);
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText("Fecha de Generación: " + java.time.LocalDateTime.now().format(
                    java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                ));
                contentStream.endText();
                
                yPosition -= 30f;
                
                // Dibujar línea separadora
                contentStream.moveTo(margin, yPosition);
                contentStream.lineTo(margin + tableWidth, yPosition);
                contentStream.stroke();
                
                yPosition -= 20f;
                
                // Crear tabla con datos
                if (datosReporte != null && !datosReporte.isEmpty()) {
                    String[] columnas = datosReporte.get(0);
                    int numColumnas = columnas.length;
                    
                    // Calcular ancho de cada columna
                    float[] anchosColumnas = new float[numColumnas];
                    float anchoTotal = tableWidth - (numColumnas - 1) * 1; // Restar espacio de bordes
                    float anchoBase = anchoTotal / numColumnas;
                    
                    // Ajustar anchos según contenido (mínimo 80, máximo 150)
                    for (int i = 0; i < numColumnas; i++) {
                        anchosColumnas[i] = Math.max(80f, Math.min(150f, anchoBase));
                    }
                    
                    float alturaFila = 20f;
                    float alturaEncabezado = 25f;
                    
                    // Dibujar encabezado de la tabla
                    float xPosition = margin;
                    yPosition -= alturaEncabezado;
                    
                    // Fondo del encabezado (gris claro)
                    contentStream.setNonStrokingColor(0.9f, 0.9f, 0.9f);
                    contentStream.addRect(xPosition, yPosition, tableWidth, alturaEncabezado);
                    contentStream.fill();
                    
                    // Bordes del encabezado
                    contentStream.setStrokingColor(0.5f, 0.5f, 0.5f);
                    contentStream.setLineWidth(1f);
                    
                    // Línea superior
                    contentStream.moveTo(xPosition, yPosition + alturaEncabezado);
                    contentStream.lineTo(xPosition + tableWidth, yPosition + alturaEncabezado);
                    contentStream.stroke();
                    
                    // Línea inferior
                    contentStream.moveTo(xPosition, yPosition);
                    contentStream.lineTo(xPosition + tableWidth, yPosition);
                    contentStream.stroke();
                    
                    // Dibujar columnas del encabezado
                    float xActual = xPosition;
                    for (int i = 0; i < numColumnas; i++) {
                        // Línea vertical entre columnas
                        if (i > 0) {
                            contentStream.moveTo(xActual, yPosition);
                            contentStream.lineTo(xActual, yPosition + alturaEncabezado);
                            contentStream.stroke();
                        }
                        
                        // Texto del encabezado
                        contentStream.beginText();
                        contentStream.setFont(fontBold, 11f);
                        contentStream.setNonStrokingColor(0f, 0f, 0f);
                        
                        // Centrar texto en la columna
                        String texto = columnas[i];
                        // getStringWidth devuelve el ancho en unidades de 1/1000 del tamaño de fuente
                        float textoWidth = fontBold.getStringWidth(texto) / 1000f * 11f;
                        float xTexto = xActual + Math.max(5f, (anchosColumnas[i] - textoWidth) / 2);
                        float yTexto = yPosition + (alturaEncabezado - 11f) / 2 + 2f;
                        
                        contentStream.newLineAtOffset(xTexto, yTexto);
                        contentStream.showText(texto);
                        contentStream.endText();
                        
                        xActual += anchosColumnas[i] + 1; // +1 para el borde
                    }
                    
                    // Dibujar filas de datos
                    yPosition -= alturaFila;
                    
                    for (int filaIndex = 1; filaIndex < datosReporte.size(); filaIndex++) {
                        String[] fila = datosReporte.get(filaIndex);
                        
                        // Alternar color de fondo (blanco/gris muy claro)
                        if (filaIndex % 2 == 0) {
                            contentStream.setNonStrokingColor(0.95f, 0.95f, 0.95f);
                            contentStream.addRect(margin, yPosition, tableWidth, alturaFila);
                            contentStream.fill();
                        }
                        
                        // Bordes de la fila
                        contentStream.setStrokingColor(0.7f, 0.7f, 0.7f);
                        contentStream.setLineWidth(0.5f);
                        
                        // Línea inferior de la fila
                        contentStream.moveTo(margin, yPosition);
                        contentStream.lineTo(margin + tableWidth, yPosition);
                        contentStream.stroke();
                        
                        // Dibujar columnas de la fila
                        xActual = margin;
                        for (int colIndex = 0; colIndex < numColumnas; colIndex++) {
                            // Línea vertical entre columnas
                            if (colIndex > 0) {
                                contentStream.moveTo(xActual, yPosition);
                                contentStream.lineTo(xActual, yPosition + alturaFila);
                                contentStream.stroke();
                            }
                            
                            // Texto de la celda
                            String valor = (colIndex < fila.length && fila[colIndex] != null) ? fila[colIndex] : "";
                            
                            // Truncar texto si es muy largo
                            if (valor.length() > 25) {
                                valor = valor.substring(0, 22) + "...";
                            }
                            
                            contentStream.beginText();
                            contentStream.setFont(fontNormal, 10f);
                            contentStream.setNonStrokingColor(0f, 0f, 0f);
                            
                            // Alinear texto a la izquierda con padding
                            float xTexto = xActual + 5f;
                            float yTexto = yPosition + (alturaFila - 10f) / 2;
                            
                            contentStream.newLineAtOffset(xTexto, yTexto);
                            contentStream.showText(valor);
                            contentStream.endText();
                            
                            xActual += anchosColumnas[colIndex] + 1; // +1 para el borde
                        }
                        
                        yPosition -= alturaFila;
                    }
                    
                    // Línea final de la tabla
                    contentStream.setStrokingColor(0.5f, 0.5f, 0.5f);
                    contentStream.setLineWidth(1f);
                    contentStream.moveTo(margin, yPosition);
                    contentStream.lineTo(margin + tableWidth, yPosition);
                    contentStream.stroke();
                }
            }
            
            // Guardar documento
            document.save(archivo);
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private void generarReporteTexto(File archivo) throws IOException {
        // Generar archivo de texto formateado con el reporte
        try (FileWriter writer = new FileWriter(archivo, java.nio.charset.StandardCharsets.UTF_8)) {
            // Encabezado del reporte
            writer.write("═══════════════════════════════════════════════════════════\n");
            writer.write("                    REPORTE DE HOTEL\n");
            writer.write("═══════════════════════════════════════════════════════════\n\n");
            writer.write("Tipo de Reporte: " + tipoReporte + "\n");
            writer.write("Fecha de Generación: " + java.time.LocalDateTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            ) + "\n");
            writer.write("\n");
            writer.write("═══════════════════════════════════════════════════════════\n\n");
            
            if (datosReporte != null && !datosReporte.isEmpty()) {
                // Escribir encabezados
                String[] columnas = datosReporte.get(0);
                
                // Calcular ancho de cada columna
                int[] anchos = new int[columnas.length];
                for (int i = 0; i < columnas.length; i++) {
                    anchos[i] = Math.max(columnas[i].length(), 15);
                    // Verificar en todas las filas
                    for (int j = 1; j < datosReporte.size(); j++) {
                        if (i < datosReporte.get(j).length) {
                            anchos[i] = Math.max(anchos[i], datosReporte.get(j)[i].length());
                        }
                    }
                    anchos[i] = Math.min(anchos[i] + 2, 30); // Máximo 30 caracteres
                }
                
                // Escribir encabezados
                for (int i = 0; i < columnas.length; i++) {
                    writer.write(String.format("%-" + anchos[i] + "s", columnas[i]));
                    if (i < columnas.length - 1) {
                        writer.write(" | ");
                    }
                }
                writer.write("\n");
                
                // Línea separadora
                for (int i = 0; i < columnas.length; i++) {
                    for (int j = 0; j < anchos[i]; j++) {
                        writer.write("-");
                    }
                    if (i < columnas.length - 1) {
                        writer.write("-+-");
                    }
                }
                writer.write("\n");
                
                // Escribir datos
                for (int i = 1; i < datosReporte.size(); i++) {
                    String[] fila = datosReporte.get(i);
                    for (int j = 0; j < fila.length && j < columnas.length; j++) {
                        String valor = fila[j] != null ? fila[j] : "";
                        if (valor.length() > anchos[j]) {
                            valor = valor.substring(0, anchos[j] - 3) + "...";
                        }
                        writer.write(String.format("%-" + anchos[j] + "s", valor));
                        if (j < fila.length - 1 && j < columnas.length - 1) {
                            writer.write(" | ");
                        }
                    }
                    writer.write("\n");
                }
            }
            
            writer.write("\n═══════════════════════════════════════════════════════════\n");
            writer.write("                    FIN DEL REPORTE\n");
            writer.write("═══════════════════════════════════════════════════════════\n");
        }
    }
}

