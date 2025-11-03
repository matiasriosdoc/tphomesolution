package entidades;

public class Tarea {
    String titulo;
    String descripcion;
    double dia;
    double diasDeRetraso=0;
    Integer empleadoAsignadoLegajo=null;

    public Tarea(String titulo, String descripcione, double dia) {
        this.titulo = titulo;
        this.descripcion = descripcione;
        if(dia<0){
            throw new IllegalArgumentException("El día no puede ser negativo: " + dia);
        }
        this.dia = dia;
    }

    public void asignarEmpleado(Integer legajo){
        if(empleadoAsignadoLegajo!=null){
            throw new IllegalStateException("La tarea ya tiene un empleado asignado: " + empleadoAsignadoLegajo);
        }
        this.empleadoAsignadoLegajo=legajo;
    }
    public void finalizarTarea(){
        this.empleadoAsignadoLegajo=null;
    }

    public void registrarRetraso(double cantidadDias){
        if(cantidadDias<0){
            throw new IllegalArgumentException("La cantidad de días no puede ser negativa: " + cantidadDias);
        }
        this.diasDeRetraso += cantidadDias;
    }
}
