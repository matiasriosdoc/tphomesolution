package entidades;

public class EmpleadoContratado extends Empleado {
    double valorHora;

    public EmpleadoContratado(String nombre, Double valorHora) {
        super(nombre);
        if (valorHora < 0) {
            throw new IllegalArgumentException("El valor hora no puede ser negativo");
        }
        this.valorHora = valorHora;
    }
}
