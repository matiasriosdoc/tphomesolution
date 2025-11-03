package entidades;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FechaUtil {
    public static void validarFechas(String inicio, String fin) {

        try {
            LocalDate fechaInicio = LocalDate.parse(inicio);
            LocalDate fechaFin = LocalDate.parse(fin);
            if (fechaInicio.isAfter(fechaFin)) {
                throw new IllegalArgumentException("La fecha de inicio deberia ser menor a la fecha de fin.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fecha formato invalido. Usar yyyy-MM-dd.", e);
        }
    }
    public static void validarFecha(String fecha) {
        try {
            LocalDate.parse(fecha);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Fecha formato invalido. Usar yyyy-MM-dd.", e);
        }
    }

    public static String sumarDiasAFecha(String fechaEstimadaFin, double cantidadDias) {
        LocalDate fecha = LocalDate.parse(fechaEstimadaFin);
        LocalDate nuevaFecha = fecha.plusDays((long) cantidadDias);
        return nuevaFecha.toString();
    }
}