package componentes;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.fontawesome5.FontAwesomeRegular;

/**
 * Componente para mostrar iconos usando Ikonli (FontAwesome)
 */
public class IconoSimple extends StackPane {
    
    private FontIcon icono;
    
    public IconoSimple(String tipo, int tamano, Color color) {
        setAlignment(javafx.geometry.Pos.CENTER);
        
        icono = crearIconoPorTipo(tipo);
        icono.setIconSize(tamano);
        icono.setIconColor(color);
        
        getChildren().add(icono);
    }
    
    private FontIcon crearIconoPorTipo(String tipo) {
        switch(tipo) {
            case "navegacion":
                return new FontIcon(FontAwesomeSolid.BARS);
            case "usuarios":
                return new FontIcon(FontAwesomeSolid.USERS);
            case "cama":
                return new FontIcon(FontAwesomeSolid.BED);
            case "puerta":
                return new FontIcon(FontAwesomeSolid.DOOR_OPEN);
            case "calendario":
                return new FontIcon(FontAwesomeSolid.CALENDAR_CHECK);
            case "grafico":
                return new FontIcon(FontAwesomeSolid.CHART_BAR);
            case "ver":
                return new FontIcon(FontAwesomeRegular.EYE);
            case "editar":
                return new FontIcon(FontAwesomeSolid.PENCIL_ALT);
            case "eliminar":
                return new FontIcon(FontAwesomeSolid.TRASH_ALT);
            case "printer":
                return new FontIcon(FontAwesomeSolid.PRINT);
            case "trending-up":
                return new FontIcon(FontAwesomeSolid.CHART_LINE);
            case "circle-dot":
                return new FontIcon(FontAwesomeSolid.CIRCLE);
            case "file-text":
                return new FontIcon(FontAwesomeSolid.FILE);
            case "circle-check":
                return new FontIcon(FontAwesomeSolid.CHECK_CIRCLE);
            case "circle-x":
                return new FontIcon(FontAwesomeSolid.TIMES_CIRCLE);
            case "info":
                return new FontIcon(FontAwesomeSolid.INFO_CIRCLE);
            case "alert-triangle":
                return new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE);
            case "trash-2":
                return new FontIcon(FontAwesomeSolid.TRASH);
            case "x-circle":
                return new FontIcon(FontAwesomeSolid.TIMES_CIRCLE);
            case "download":
                return new FontIcon(FontAwesomeSolid.DOWNLOAD);
            case "x":
                return new FontIcon(FontAwesomeSolid.TIMES);
            case "user":
            case "avatar":
            case "profile":
                return new FontIcon(FontAwesomeSolid.USER_CIRCLE);
            default:
                return new FontIcon(FontAwesomeSolid.QUESTION);
        }
    }
    
    public void setColor(Color color) {
        if (icono != null) {
            icono.setIconColor(color);
        }
    }
    
    // Método para obtener el FontIcon interno (para cambiar opacidad, etc.)
    public FontIcon getFontIcon() {
        return icono;
    }
    
    // Métodos estáticos para crear iconos específicos
    public static IconoSimple crearIconoNavegacion(int tamano, Color color) {
        return new IconoSimple("navegacion", tamano, color);
    }
    
    public static IconoSimple crearIconoUsuarios(int tamano, Color color) {
        return new IconoSimple("usuarios", tamano, color);
    }
    
    public static IconoSimple crearIconoCama(int tamano, Color color) {
        return new IconoSimple("cama", tamano, color);
    }
    
    public static IconoSimple crearIconoPuerta(int tamano, Color color) {
        return new IconoSimple("puerta", tamano, color);
    }
    
    public static IconoSimple crearIconoCalendario(int tamano, Color color) {
        return new IconoSimple("calendario", tamano, color);
    }
    
    public static IconoSimple crearIconoGrafico(int tamano, Color color) {
        return new IconoSimple("grafico", tamano, color);
    }
    
    public static IconoSimple crearIconoVer(int tamano, Color color) {
        return new IconoSimple("ver", tamano, color);
    }
    
    public static IconoSimple crearIconoEditar(int tamano, Color color) {
        return new IconoSimple("editar", tamano, color);
    }
    
    public static IconoSimple crearIconoEliminar(int tamano, Color color) {
        return new IconoSimple("eliminar", tamano, color);
    }
    
    public static IconoSimple crearIconoTrendingUp(int tamano, Color color) {
        return new IconoSimple("trending-up", tamano, color);
    }
    
    public static IconoSimple crearIconoCircleDot(int tamano, Color color) {
        return new IconoSimple("circle-dot", tamano, color);
    }
    
    public static IconoSimple crearIconoFileText(int tamano, Color color) {
        return new IconoSimple("file-text", tamano, color);
    }
    
    public static IconoSimple crearIconoPrinter(int tamano, Color color) {
        return new IconoSimple("printer", tamano, color);
    }
    
    public static IconoSimple crearIconoCircleCheck(int tamano, Color color) {
        return new IconoSimple("circle-check", tamano, color);
    }
    
    public static IconoSimple crearIconoCircleX(int tamano, Color color) {
        return new IconoSimple("circle-x", tamano, color);
    }
    
    public static IconoSimple crearIconoInfo(int tamano, Color color) {
        return new IconoSimple("info", tamano, color);
    }
    
    public static IconoSimple crearIconoAlertTriangle(int tamano, Color color) {
        return new IconoSimple("alert-triangle", tamano, color);
    }
    
    public static IconoSimple crearIconoTrash2(int tamano, Color color) {
        return new IconoSimple("trash-2", tamano, color);
    }
    
    public static IconoSimple crearIconoXCircle(int tamano, Color color) {
        return new IconoSimple("x-circle", tamano, color);
    }
    
    public static IconoSimple crearIconoDownload(int tamano, Color color) {
        return new IconoSimple("download", tamano, color);
    }
    
    public static IconoSimple crearIconoX(int tamano, Color color) {
        return new IconoSimple("x", tamano, color);
    }
    
    public static IconoSimple crearIconoUsuario(int tamano, Color color) {
        return new IconoSimple("user", tamano, color);
    }
}
