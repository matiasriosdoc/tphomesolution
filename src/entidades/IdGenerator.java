package entidades;

public class IdGenerator {
    private static int counter = 0;

    public static synchronized int nextId() {
        return ++counter;
    }
}