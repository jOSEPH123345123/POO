package universidad.modelo;

public class Edificio {
    private final int indice;
    private final String nombre;
    private final String descripcion;

    public Edificio(int indice, String nombre, String descripcion) {
        this.indice = indice;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIndice() { return indice; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return indice + ": " + nombre + " - " + descripcion;
    }
}
