package entidades;

public abstract class Empleado {
    //private static int contadorLegajo = 0; // Contador global
    //int legajo=0;
    String nombre;

    public Empleado(String nombre) {
    //    this.legajo= ++contadorLegajo;
        this.nombre = nombre;
    }


    @Override
    public String toString() {
        return nombre;
    }
}
