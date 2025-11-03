package entidades;

public class Estado {
    public static final String activo="ACTIVO";
    public static final String pendiente="PENDIENTE";
    public static final String finalizado="FINALIZADO";


    public static void validarEstado(String estado) {
        if (!estado.equals(activo) && !estado.equals(pendiente) && !estado.equals(finalizado)) {
            throw new IllegalArgumentException("Estado inválido: " + estado);
        }
    }
}
