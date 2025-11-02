package entidades;

public class EmpleadoDePlanta extends Empleado {
    String categoria;
    Double valorDia;

    public EmpleadoDePlanta(String nombre, double valorDia, String categoria) {
        super(nombre);
        this.categoria = categoria;
        this.valorDia= valorDia;
    }
}
