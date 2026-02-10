package utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.util.StringConverter;

/**
 * Convertidor de fechas para DatePicker con formato dd/MM/yyyy
 * Maneja conversión bidireccional entre LocalDate y String
 */
public class DateConverter extends StringConverter<LocalDate> {
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    @Override
    public String toString(LocalDate date) {
        if (date == null) {
            return "";
        }
        return DATE_FORMATTER.format(date);
    }
    
    @Override
    public LocalDate fromString(String string) {
        if (string == null || string.trim().isEmpty()) {
            return null;
        }
        
        try {
            return LocalDate.parse(string.trim(), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            // Si el formato no es válido, retornar null
            // El DatePicker manejará el error visualmente
            return null;
        }
    }
}

