package entidades;

import java.util.*;

public class HomeSolution implements IHomeSolution {
    private List<Tupla<Integer, String>> empleados = new ArrayList<>();
    private List<Tupla<Integer, String>> proyectos = new ArrayList<>();

    private final Map<Integer, Proyecto> proyectoMap = new HashMap<>();
    private final Map<Integer, Empleado> empleadoMap = new HashMap<>();

    @Override
    public void registrarEmpleado(String nombre, double valor) throws IllegalArgumentException {
        Empleado empleado = new EmpleadoContratado(nombre, valor);
        registrarEmpleadoComun(empleado);
    }

    @Override
    public void registrarEmpleado(String nombre, double valor, String categoria) throws IllegalArgumentException {
        Empleado empleado = new EmpleadoDePlanta(nombre, valor, categoria);
        registrarEmpleadoComun(empleado);
    }

    private void registrarEmpleadoComun(Empleado empleado) {
        Tupla<Integer, String> tuplaEmpleado = new Tupla<>(empleado.getLegajo(), empleado.getNombre());
        empleados.add(tuplaEmpleado);
        empleadoMap.put(empleado.getLegajo(), empleado);
    }

    @Override
    public void registrarProyecto(String[] titulos, String[] descripcion, double[] dias, String domicilio, String[] cliente, String inicio, String fin) throws IllegalArgumentException {
        Cliente clienteObj = new Cliente(cliente[0], cliente[1]);
        Proyecto proyecto = new Proyecto(titulos, descripcion, dias, domicilio, clienteObj, inicio, fin);

        proyectos.add(new Tupla<>(proyecto.getNumeroProyecto(), proyecto.getDomicilio()));
        proyectoMap.put(proyecto.getNumeroProyecto(), proyecto);
    }

    @Override
    public void asignarResponsableEnTarea(Integer numero, String titulo) throws Exception {
        Proyecto proyecto = proyectoMap.get(numero);
        Object[] empleadosNoAsignados = this.empleadosNoAsignados();
        Empleado primerEmpleadoNoAsignado = (Empleado) (empleadosNoAsignados.length > 0 ? empleadosNoAsignados[0] : null);
        proyecto.asignarEmpleados(titulo, primerEmpleadoNoAsignado.getLegajo());
        primerEmpleadoNoAsignado.setDisponible(false);

    }

    @Override
    public void asignarResponsableMenosRetraso(Integer numero, String titulo) throws Exception {
        Proyecto proyecto = proyectoMap.get(numero);
        Object[] empleadosNoAsignados = this.empleadosNoAsignados();
        if (empleadosNoAsignados.length == 0) {
            throw new Exception("No hay empleados disponibles");
        }
        Empleado empleadoConMenosRetrasos = null;
        int minRetrasos = Integer.MAX_VALUE;

        for (Object obj : empleadosNoAsignados) {
            Empleado empleado = (Empleado) obj;
            if (empleado.getCantidadRetrasos() < minRetrasos) {
                minRetrasos = empleado.getCantidadRetrasos();
                empleadoConMenosRetrasos = empleado;
            }
        }
        if (empleadoConMenosRetrasos != null) {
            proyecto.asignarEmpleados(titulo, empleadoConMenosRetrasos.getLegajo());
            empleadoConMenosRetrasos.setDisponible(false);
        }
    }

    @Override
    public void registrarRetrasoEnTarea(Integer numero, String titulo, double cantidadDias) throws IllegalArgumentException {
        Proyecto proyecto = proyectoMap.get(numero);
        proyecto.registrarRetrasoEnTarea(titulo, cantidadDias);
    }

    @Override
    public void agregarTareaEnProyecto(Integer numero, String titulo, String descripcion, double dias) throws IllegalArgumentException {

    }

    @Override
    public void finalizarTarea(Integer numero, String titulo) throws Exception {
        Proyecto proyecto = proyectoMap.get(numero);
        proyecto.finalizarTarea(titulo);

    }

    @Override
    public void finalizarProyecto(Integer numero, String fin) throws IllegalArgumentException {
        proyectoMap.get(numero).finalizarProyecto(fin);
    }

    @Override
    public void reasignarEmpleadoEnProyecto(Integer numero, Integer legajo, String titulo) throws Exception {

    }

    @Override
    public void reasignarEmpleadoConMenosRetraso(Integer numero, String titulo) throws Exception {

    }

    @Override
    public double costoProyecto(Integer numero) {
        return 0;
    }

    @Override
    public List<Tupla<Integer, String>> proyectosFinalizados() {
        return this.proyectosPorEstado(Estado.finalizado);
    }

    @Override
    public List<Tupla<Integer, String>> proyectosPendientes() {
        return this.proyectosPorEstado(Estado.pendiente);
    }

    @Override
    public List<Tupla<Integer, String>> proyectosActivos() {
        return this.proyectosPorEstado(Estado.activo);
    }

    private List<Tupla<Integer, String>> proyectosPorEstado(String estado) {
        List<Tupla<Integer, String>> proyectosPorEstado = new ArrayList<>();

        for (Tupla<Integer, String> tuplaProyecto : this.proyectos) {
            Proyecto proyecto = proyectoMap.get(tuplaProyecto.getValor1());
            if (proyecto.getEstado().equals(estado)) {
                proyectosPorEstado.add(tuplaProyecto);
            }
        }
        return proyectosPorEstado;
    }

    @Override
    public Object[] empleadosNoAsignados() {
        List<Empleado> empleadosNoAsignados = new ArrayList<>();

        for (Tupla<Integer, String> tuplaEmpleado : this.empleados) {
            Empleado empleado = empleadoMap.get(tuplaEmpleado.getValor1());
            if (empleado.isDisponible()) {
                empleadosNoAsignados.add(empleado);
            }
        }
        return empleadosNoAsignados.toArray();
    }

    @Override
    public boolean estaFinalizado(Integer numero) {
        return proyectoMap.get(numero).getEstado().equals(Estado.finalizado);
    }

    @Override
    public int consultarCantidadRetrasosEmpleado(Integer legajo) {
        return empleadoMap.get(legajo).getCantidadRetrasos();
    }

    @Override
    public List<Tupla<Integer, String>> empleadosAsignadosAProyecto(Integer numero) {
        Proyecto proyecto = proyectoMap.get(numero);
        // proyecto.
        return Collections.emptyList();
    }

    @Override
    public Object[] tareasProyectoNoAsignadas(Integer numero) {
        Proyecto proyecto = proyectoMap.get(numero);
        return proyecto.tareasProyectoNoAsignadas();

    }

    @Override
    public Object[] tareasDeUnProyecto(Integer numero) {
        return new Object[0];
    }

    @Override
    public String consultarDomicilioProyecto(Integer numero) {
        Proyecto proyecto = proyectoMap.get(numero);
        return proyecto.getDomicilio();
    }

    @Override
    public boolean tieneRestrasos(Integer legajo) {
        return empleadoMap.get(legajo).getCantidadRetrasos() > 0;
    }

    @Override
    public List<Tupla<Integer, String>> empleados() {
        return empleados;
    }

    @Override
    public String consultarProyecto(Integer numero) {
        return proyectoMap.get(numero).toString();
    }
}
