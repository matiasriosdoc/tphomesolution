package entidades;

import java.util.HashMap;
import java.util.Map;

public class Proyecto {
    Map<String, Tarea> tareaMap = new HashMap<>();

    Cliente cliente;
    String domicilio;
    String fechaInicio;
    String fechaFin;


    public Proyecto(String[] titulos, String[] descripciones,
                    double[] dias, String domicilio, Cliente cliente,
                    String inicio, String fin) {
        if (titulos.length != descripciones.length || titulos.length != dias.length) {
            throw new IllegalArgumentException("All arrays must have the same length.");
        }

        this.domicilio = domicilio;
        this.cliente = cliente;
        this.fechaInicio = inicio;
        this.fechaFin = fin;

        java.util.Set<String> titulosSet = new java.util.HashSet<>();
        for (int i = 0; i < titulos.length; i++) {
            if (!titulosSet.add(titulos[i])) {
                throw new IllegalArgumentException("Duplicate title: " + titulos[i]);
            }
            Tarea tarea = new Tarea(titulos[i], descripciones[i], dias[i]);
            tareaMap.put(titulos[i], tarea);
        }
    }

}
