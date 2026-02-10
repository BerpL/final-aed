package utilidades;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Clase que contiene todas las constantes de colores, fuentes y estilos
 * utilizados en la aplicación, basadas en el diseño aed.pen
 */
public class Constantes {
    
    // Colores principales
    public static final Color COLOR_PRIMARIO = Color.rgb(0, 120, 212); // #0078D4
    public static final Color COLOR_PRIMARIO_OSCURO = Color.rgb(0, 90, 158); // #005A9E
    public static final Color COLOR_SECUNDARIO = Color.rgb(240, 240, 240); // #F0F0F0
    public static final Color COLOR_FONDO = Color.rgb(240, 240, 240); // #F0F0F0
    public static final Color COLOR_BLANCO = Color.rgb(255, 255, 255); // #FFFFFF
    public static final Color COLOR_NEGRO = Color.rgb(0, 0, 0); // #000000
    
    // Colores de estado
    public static final Color COLOR_EXITO = Color.rgb(0, 176, 80); // #00B050
    public static final Color COLOR_ERROR = Color.rgb(232, 17, 35); // #E81123
    public static final Color COLOR_ADVERTENCIA = Color.rgb(255, 243, 205); // #FFF3CD
    
    // Colores de texto
    public static final Color COLOR_TEXTO_PRINCIPAL = Color.rgb(51, 51, 51); // #333333
    public static final Color COLOR_TEXTO_SECUNDARIO = Color.rgb(102, 102, 102); // #666666
    public static final Color COLOR_TEXTO_BLANCO = Color.rgb(255, 255, 255); // #FFFFFF
    
    // Colores de paneles
    public static final Color COLOR_PANEL_LATERAL = Color.rgb(232, 232, 232); // #E8E8E8
    public static final Color COLOR_PANEL_FORMULARIO = Color.rgb(248, 248, 248); // #F8F8F8
    public static final Color COLOR_MENU_BAR = Color.rgb(240, 240, 240); // #F0F0F0
    public static final Color COLOR_STATUS_BAR = Color.rgb(232, 232, 232); // #E8E8E8
    
    // Colores de bordes
    public static final Color COLOR_BORDE_CLARO = Color.rgb(208, 208, 208); // #D0D0D0
    public static final Color COLOR_BORDE_MEDIO = Color.rgb(192, 192, 192); // #C0C0C0
    public static final Color COLOR_BORDE_OSCURO = Color.rgb(160, 160, 160); // #A0A0A0
    public static final Color COLOR_BORDE_TABLA = Color.rgb(160, 160, 160); // #A0A0A0
    public static final Color COLOR_SEPARADOR = Color.rgb(224, 224, 224); // #E0E0E0
    
    // Colores de tablas
    public static final Color COLOR_TABLA_HEADER = Color.rgb(224, 224, 224); // #E0E0E0
    public static final Color COLOR_TABLA_FILA_PAR = Color.rgb(255, 255, 255); // #FFFFFF
    public static final Color COLOR_TABLA_FILA_IMPAR = Color.rgb(248, 248, 248); // #F8F8F8
    
    // Fuentes
    public static final Font FUENTE_TITULO = Font.font("Arial", FontWeight.BOLD, 18);
    public static final Font FUENTE_SUBTITULO = Font.font("Arial", FontWeight.BOLD, 14);
    public static final Font FUENTE_NORMAL = Font.font("Arial", 12);
    public static final Font FUENTE_PEQUENA = Font.font("Arial", 11);
    public static final Font FUENTE_MENU = Font.font("Arial", 12);
    public static final Font FUENTE_TITULO_VENTANA = Font.font("Arial", 12);
    
    // Tamaños
    public static final int ANCHO_PANEL_LATERAL = 180;
    public static final int ALTO_BARRA_TITULO = 32;
    public static final int ALTO_MENU_BAR = 24;
    public static final int ALTO_STATUS_BAR = 24;
    public static final int ANCHO_VENTANA = 1366;
    public static final int ALTO_VENTANA = 768;
    
    // Padding y espaciado
    public static final int PADDING_PEQUENO = 4;
    public static final int PADDING_MEDIO = 8;
    public static final int PADDING_GRANDE = 16;
    public static final int GAP_PEQUENO = 8;
    public static final int GAP_MEDIO = 12;
    public static final int GAP_GRANDE = 24;
    
    // Radio de borde
    public static final int RADIO_BORDE = 2;
    
    private Constantes() {
        // Clase de constantes, no se debe instanciar
    }
}

