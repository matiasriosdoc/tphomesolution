package entidades;

public class Tarea {
    String titulo;
    String descripcion;
    double duracionDias;
    double diasDeRetraso=0;
    Integer empleadoAsignadoLegajo=null;
    boolean esFinalizado= false;

    public Tarea(String titulo, String descripcione, double duracionDias) {
        this.titulo = titulo;
        this.descripcion = descripcione;
        if(duracionDias <0){
            throw new IllegalArgumentException("El día no puede ser negativo: " + duracionDias);
        }
        this.duracionDias = duracionDias;
        this.esFinalizado= false;
    }

    public void asignarEmpleado(Integer legajo){
        if(empleadoAsignadoLegajo!=null){
            throw new IllegalStateException("La tarea ya tiene un empleado asignado: " + empleadoAsignadoLegajo);
        }
        this.empleadoAsignadoLegajo=legajo;
    }
    public void finalizarTarea(){
        if(empleadoAsignadoLegajo==null)
            throw new IllegalArgumentException("No se permite finalizar proyectos con tareas no asignadas");
        this.empleadoAsignadoLegajo=null;
        this.esFinalizado= true;

    }

    public void registrarRetraso(double cantidadDias){
        if(cantidadDias<0){
            throw new IllegalArgumentException("La cantidad de días no puede ser negativa: " + cantidadDias);
        }
        this.diasDeRetraso += cantidadDias;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tarea: ").append(titulo).append("\n");
        sb.append("Descripción: ").append(descripcion).append("\n");
        sb.append("Duración (días): ").append(duracionDias).append("\n");
        sb.append("Días de retraso: ").append(diasDeRetraso).append("\n");
        sb.append("Empleado asignado: ")
                .append(empleadoAsignadoLegajo != null ? empleadoAsignadoLegajo : "No asignado").append("\n");
        sb.append("Finalizada: ").append(esFinalizado ? "Sí" : "No");
        return sb.toString();
    }
}
