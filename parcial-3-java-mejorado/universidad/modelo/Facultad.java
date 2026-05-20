package universidad.modelo;

public class Facultad {
    private final String codigo;
    private final String nombre;
    private final String decano;

    public Facultad(String codigo, String nombre, String decano) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.decano = decano;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDecano() { return decano; }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " | Decano: " + decano;
    }
}
