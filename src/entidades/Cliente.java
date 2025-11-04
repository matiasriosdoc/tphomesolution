package entidades;

public class Cliente {
    String nombre;
    String telefono;
    String email;
    public Cliente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public Cliente(String nombre, String email, String telefono) {
        this(nombre,email);
        this.telefono=  telefono;
    }
}
