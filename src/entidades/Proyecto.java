package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Proyecto {

    private Integer numeroProyecto;
    private Map<String, Tarea> tareaMap = new HashMap<>();
    private Cliente cliente;
    private String domicilio;
    private String fechaInicio;
    private String fechaFin;
    private String fechaEstimadaFin;

    private String estado;

    public Proyecto(String[] titulos, String[] descripciones,
                    double[] dias, String domicilio, Cliente cliente,
                    String inicio, String fin) {
        if (titulos.length != descripciones.length || titulos.length != dias.length) {
            throw new IllegalArgumentException("All arrays must have the same length.");
        }
        this.numeroProyecto = IdGenerator.nextId();
        this.domicilio = domicilio;
        this.cliente = cliente;
        this.fechaInicio = inicio;
        this.fechaEstimadaFin = fin;
        this.fechaFin = fin;

        FechaUtil.validarFechas(inicio, fin);

        java.util.Set<String> titulosSet = new java.util.HashSet<>();

        for (int i = 0; i < titulos.length; i++) {
            if (!titulosSet.add(titulos[i])) {
                throw new IllegalArgumentException("Duplicate title: " + titulos[i]);
            }
            Tarea tarea = new Tarea(titulos[i], descripciones[i], dias[i]);
            tareaMap.put(titulos[i], tarea);
        }

        this.estado = Estado.pendiente;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public Integer getNumeroProyecto() {
        return numeroProyecto;
    }

    public String getEstado() {
        return estado;
    }

    public void finalizarProyecto(String fechaFin) throws IllegalArgumentException {
        FechaUtil.validarFecha(fechaFin);
        FechaUtil.validarFechas(this.fechaInicio, fechaFin);
        for (Tarea tarea : tareaMap.values()) {
            tarea.finalizarTarea();
        }
        this.fechaFin = fechaFin;
        this.estado = Estado.finalizado;

    }

    public Object[] tareasProyectoNoAsignadas() {
        List<Tarea> noAsignadas = new ArrayList<>();
        for (Tarea tarea : tareaMap.values()) {
            if (tarea.empleadoAsignadoLegajo == null) {
                noAsignadas.add(tarea);
            }
        }
        return noAsignadas.toArray();
    }

    public void asignarEmpleados(String tituloTarea, Integer legajoEmpleado) {
        Tarea tarea = obtenerTareaPorTitulo(tituloTarea);
        tarea.asignarEmpleado(legajoEmpleado);
        verificarTodasLasTareasAsignadas();
    }

    private void verificarTodasLasTareasAsignadas() {
        boolean todasAsignadas = true;
        for (Tarea t : tareaMap.values()) {
            if (t.empleadoAsignadoLegajo == null) {
                todasAsignadas = false;
                break;
            }
        }
        if (todasAsignadas) {
            this.estado = Estado.activo;
        }
    }
    public void registrarRetrasoEnTarea(String titulo, double cantidadDias) {
        Tarea tarea = obtenerTareaPorTitulo(titulo);
        tarea.registrarRetraso(cantidadDias);
        this.fechaFin = FechaUtil.sumarDiasAFecha(this.fechaFin, cantidadDias);
    }

    private Tarea obtenerTareaPorTitulo(String titulo) {
        Tarea tarea = tareaMap.get(titulo);
        if (tarea == null) {
            throw new IllegalArgumentException("Tarea no encontrada: " + titulo);
        }
        return tarea;
    }

    public void finalizarTarea(String titulo) {
        Tarea tarea = tareaMap.get(titulo);
        tarea.finalizarTarea();
    }

    public List<Integer> obtenerEmpleadosAsignados() {
        List<Integer> empleados = new ArrayList<>();
        for (Tarea tarea : tareaMap.values()) {
            if (tarea.empleadoAsignadoLegajo != null) {
                empleados.add(tarea.empleadoAsignadoLegajo);
            }
        }
        return empleados;
    }
}
