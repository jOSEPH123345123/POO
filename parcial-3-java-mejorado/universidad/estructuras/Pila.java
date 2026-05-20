package universidad.estructuras;

public class Pila<T> {
    private class Nodo {
        T dato;
        Nodo siguiente;
        Nodo(T dato) { this.dato = dato; this.siguiente = null; }
    }

    private Nodo cima;
    private int tamano;

    public Pila() {
        cima = null;
        tamano = 0;
    }

    public void apilar(T dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = cima;
        cima = nuevo;
        tamano++;
    }

    public T desapilar() {
        if (estaVacia()) return null;
        T dato = cima.dato;
        cima = cima.siguiente;
        tamano--;
        return dato;
    }

    public T verCima() {
        return estaVacia() ? null : cima.dato;
    }

    public boolean estaVacia() { return cima == null; }
    public int getTamano() { return tamano; }
    
    public void vaciar() {
        cima = null;
        tamano = 0;
    }
}
