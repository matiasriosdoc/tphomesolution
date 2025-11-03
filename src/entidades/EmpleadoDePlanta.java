package entidades;

public class EmpleadoDePlanta extends Empleado {
    String categoria;
    Double valorDia;

    public EmpleadoDePlanta(String nombre, double valorDia, String categoria) {
        super(nombre);
        if (valorDia < 0) {
            throw new IllegalArgumentException("El valor por dia no puede ser negativo");
        }
        Categoria.validarCategoria(categoria);
        this.categoria = categoria;
        this.valorDia= valorDia;
    }
}
