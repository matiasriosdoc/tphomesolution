package entidades;

public abstract class Empleado {

    private Integer legajo;
    private String nombre;
    private boolean disponible=true;

    public int getCantidadRetrasos() {
        return cantidadRetrasos;
    }

    private int cantidadRetrasos=0;

    public Empleado(String nombre) {
        this.legajo = IdGenerator.nextId();
        this.nombre = nombre;
    }
    public boolean isDisponible() {
        return disponible;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    public Integer getLegajo() {
        return legajo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
