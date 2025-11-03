package entidades;

public class Categoria {
    public static final String inicial="INICIAL";
    public static final String tecnico="TÉCNICO";
    public static final String experto="EXPERTO";


    public static void validarCategoria(String estado) {
        if (!estado.equals(inicial) && !estado.equals(tecnico) && !estado.equals(experto)) {
            throw new IllegalArgumentException("Categoria inválido: " + estado);
        }
    }
}
