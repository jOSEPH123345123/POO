package universidad.modelo;

import universidad.estructuras.Cola;
import universidad.estructuras.ListaEnlazada;

public class Materia {
    private final String codigo;
    private final String nombre;
    private final int cuposMaximos;
    private int cuposOcupados;
    private final int creditos;
    private final ListaEnlazada<String> preRequisitos;
    private final ListaEnlazada<String> estudiantesInscritos;
    private final Cola<String> colaEspera;

    public Materia(String codigo, String nombre, int cuposMaximos, int creditos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cuposMaximos = cuposMaximos;
        this.creditos = creditos;
        this.cuposOcupados = 0;
        this.preRequisitos = new ListaEnlazada<>();
        this.estudiantesInscritos = new ListaEnlazada<>();
        this.colaEspera = new Cola<>();
    }

    public void agregarPreRequisito(String codigoPreReq) {
        if (!preRequisitos.contiene(codigoPreReq)) {
            preRequisitos.agregar(codigoPreReq);
        }
    }

    public boolean tienePreRequisito(String codigoPreReq) {
        return preRequisitos.contiene(codigoPreReq);
    }

    public boolean verificarPreRequisitos(ListaEnlazada<String> historial) {
        for (int i = 0; i < preRequisitos.getTamano(); i++) {
            String prereq = preRequisitos.obtener(i);
            if (!historial.contiene(prereq)) {
                return false;
            }
        }
        return true;
    }

    public boolean inscribirEstudiante(String idEstudiante) {
        if (estudiantesInscritos.contiene(idEstudiante)) {
            return false;
        }
        
        if (cuposOcupados < cuposMaximos) {
            estudiantesInscritos.agregar(idEstudiante);
            cuposOcupados++;
            return true;
        } else {
            if (!colaEspera.contiene(idEstudiante)) {
                colaEspera.encolar(idEstudiante);
            }
            return false;
        }
    }

    public String cancelarInscripcion(String idEstudiante) {
        if (estudiantesInscritos.contiene(idEstudiante)) {
            estudiantesInscritos.eliminar(idEstudiante);
            cuposOcupados--;
            
            // Asignar cupo al primero de la cola
            if (!colaEspera.estaVacia()) {
                String siguiente = colaEspera.desencolar();
                estudiantesInscritos.agregar(siguiente);
                cuposOcupados++;
                return siguiente;
            }
            return null;
        }
        
        // Si está en cola de espera, eliminarlo
        colaEspera.eliminar(idEstudiante);
        return null;
    }

    public boolean estaLlena() {
        return cuposOcupados >= cuposMaximos;
    }

    public int getCuposDisponibles() {
        return cuposMaximos - cuposOcupados;
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getCreditos() { return creditos; }
    public ListaEnlazada<String> getPreRequisitos() { return preRequisitos; }
    public Cola<String> getColaEspera() { return colaEspera; }
    public ListaEnlazada<String> getEstudiantesInscritos() { return estudiantesInscritos; }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " | Cupos: " + cuposOcupados + "/" + cuposMaximos + " | Créditos: " + creditos;
    }
}
