package universidad.estructuras;

public class Cola<T> {
    private class Nodo {
        T dato;
        Nodo siguiente;
        Nodo(T dato) { this.dato = dato; this.siguiente = null; }
    }

    private Nodo frente;
    private Nodo final_;
    private int tamano;

    public Cola() {
        frente = null;
        final_ = null;
        tamano = 0;
    }

    public void encolar(T dato) {
        Nodo nuevo = new Nodo(dato);
        if (estaVacia()) {
            frente = nuevo;
            final_ = nuevo;
        } else {
            final_.siguiente = nuevo;
            final_ = nuevo;
        }
        tamano++;
    }

    public T desencolar() {
        if (estaVacia()) return null;
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) final_ = null;
        tamano--;
        return dato;
    }

    public T verFrente() {
        return estaVacia() ? null : frente.dato;
    }

    public boolean estaVacia() { return frente == null; }
    public int getTamano() { return tamano; }

    public boolean contiene(T dato) {
        Nodo actual = frente;
        while (actual != null) {
            if (actual.dato.equals(dato)) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    public boolean eliminar(T dato) {
        if (estaVacia()) return false;
        if (frente.dato.equals(dato)) {
            desencolar();
            return true;
        }
        Nodo actual = frente;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato.equals(dato)) {
                if (actual.siguiente == final_) final_ = actual;
                actual.siguiente = actual.siguiente.siguiente;
                tamano--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public String toString() {
        if (estaVacia()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Nodo actual = frente;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.siguiente != null) sb.append(", ");
            actual = actual.siguiente;
        }
        sb.append("]");
        return sb.toString();
    }
}
