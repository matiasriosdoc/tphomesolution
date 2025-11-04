package entidades;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * YYYY-MM-dd
 */
public class FechaUtil {
    public static void validarFechas(String inicio, String fin) {
        validarFecha(inicio);
        validarFecha(fin);
        LocalDate fechaInicio = LocalDate.parse(inicio);
        LocalDate fechaFin = LocalDate.parse(fin);
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio deberia ser menor a la fecha de fin.");
        }
    }

    public static void validarFecha(String fecha) {
        try {
            LocalDate.parse(fecha);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fecha formato invalido. Usar yyyy-MM-dd.", e);
        }
    }

    public static String sumarDiasAFecha(String fechaFin, double cantidadDias) {
        LocalDate fecha = LocalDate.parse(fechaFin);
        LocalDate nuevaFecha = fecha.plusDays((long) cantidadDias);
        return nuevaFecha.toString();
    }
}