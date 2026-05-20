package universidad.util;

import universidad.modelo.Edificio;

public class GestorCampus {
    private static final int MAX_EDIFICIOS = 10;
    private static final int INF = Integer.MAX_VALUE / 2;

    private final int[][] matrizDistancias;
    private final Edificio[] edificios;
    private int cantidadEdificios;

    public GestorCampus() {
        matrizDistancias = new int[MAX_EDIFICIOS][MAX_EDIFICIOS];
        edificios = new Edificio[MAX_EDIFICIOS];
        cantidadEdificios = 0;
        
        for (int i = 0; i < MAX_EDIFICIOS; i++) {
            for (int j = 0; j < MAX_EDIFICIOS; j++) {
                matrizDistancias[i][j] = (i == j) ? 0 : INF;
            }
        }
    }

    public boolean agregarEdificio(String nombre, String descripcion) {
        if (cantidadEdificios >= MAX_EDIFICIOS) return false;
        edificios[cantidadEdificios] = new Edificio(cantidadEdificios, nombre, descripcion);
        cantidadEdificios++;
        return true;
    }

    public boolean agregarConexion(int origen, int destino, int distanciaMetros) {
        if (origen < 0 || origen >= cantidadEdificios || destino < 0 || destino >= cantidadEdificios) {
            return false;
        }
        matrizDistancias[origen][destino] = distanciaMetros;
        matrizDistancias[destino][origen] = distanciaMetros;
        return true;
    }

    public String calcularRutaMasCorta(int origen, int destino) {
        if (origen < 0 || origen >= cantidadEdificios || destino < 0 || destino >= cantidadEdificios) {
            return "Origen o destino inválido";
        }
        if (origen == destino) {
            return "Origen y destino son el mismo edificio.";
        }

        int[] dist = new int[cantidadEdificios];
        boolean[] visitado = new boolean[cantidadEdificios];
        int[] previo = new int[cantidadEdificios];

        for (int i = 0; i < cantidadEdificios; i++) {
            dist[i] = INF;
            previo[i] = -1;
        }
        dist[origen] = 0;

        for (int i = 0; i < cantidadEdificios; i++) {
            int u = -1;
            int min = INF;
            for (int j = 0; j < cantidadEdificios; j++) {
                if (!visitado[j] && dist[j] < min) {
                    min = dist[j];
                    u = j;
                }
            }
            if (u == -1) break;
            visitado[u] = true;

            for (int v = 0; v < cantidadEdificios; v++) {
                if (!visitado[v] && matrizDistancias[u][v] < INF) {
                    int alt = dist[u] + matrizDistancias[u][v];
                    if (alt < dist[v]) {
                        dist[v] = alt;
                        previo[v] = u;
                    }
                }
            }
        }

        if (dist[destino] == INF) {
            return "No existe ruta entre los edificios seleccionados.";
        }

        // Construir ruta
        StringBuilder ruta = new StringBuilder();
        int actual = destino;
        while (actual != -1) {
            ruta.insert(0, edificios[actual].getNombre());
            if (previo[actual] != -1) {
                ruta.insert(0, " -> ");
            }
            actual = previo[actual];
        }
        
        return "Ruta más corta: " + ruta + " | Distancia total: " + dist[destino] + " metros";
    }

    public void mostrarEdificios() {
        if (cantidadEdificios == 0) {
            System.out.println("No hay edificios registrados.");
            return;
        }
        System.out.println("\n=== EDIFICIOS DEL CAMPUS ===");
        for (int i = 0; i < cantidadEdificios; i++) {
            System.out.println(edificios[i]);
        }
    }

    public int getCantidadEdificios() { return cantidadEdificios; }
    public Edificio getEdificio(int indice) { return edificios[indice]; }
}
