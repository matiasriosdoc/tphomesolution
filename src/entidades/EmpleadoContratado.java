package entidades;

public class EmpleadoContratado extends Empleado {
    double valorHora;

    public EmpleadoContratado(String nombre, Double valorHora) {
        super(nombre);
        this.valorHora = valorHora;
    }
}
