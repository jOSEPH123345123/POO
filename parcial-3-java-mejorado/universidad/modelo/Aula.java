package universidad.modelo;

public class Aula {
    private final String nombre;
    private final int capacidad;
    private final boolean[][] horario;  // [7 días][24 horas]
    
    private static final String[] DIAS = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};

    public Aula(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.horario = new boolean[7][24];
        // Inicializar todo en false (libre)
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 24; j++) {
                horario[i][j] = false;
            }
        }
    }

    public boolean consultarDisponibilidad(int dia, int hora) {
        if (dia < 0 || dia > 6 || hora < 0 || hora > 23) return false;
        return !horario[dia][hora];
    }

    public boolean reservar(int dia, int hora, int duracion) {
        if (dia < 0 || dia > 6 || hora < 0 || hora + duracion > 24) return false;
        
        // Verificar disponibilidad
        for (int h = hora; h < hora + duracion; h++) {
            if (horario[dia][h]) return false;
        }
        
        // Reservar
        for (int h = hora; h < hora + duracion; h++) {
            horario[dia][h] = true;
        }
        return true;
    }

    public boolean liberar(int dia, int hora, int duracion) {
        if (dia < 0 || dia > 6 || hora < 0 || hora + duracion > 24) return false;
        
        for (int h = hora; h < hora + duracion; h++) {
            horario[dia][h] = false;
        }
        return true;
    }

    public void mostrarDisponibilidadDia(int dia) {
        if (dia < 0 || dia > 6) {
            System.out.println("Día inválido");
            return;
        }
        
        System.out.println("\n--- Disponibilidad de " + nombre + " para " + DIAS[dia] + " ---");
        for (int h = 0; h < 24; h++) {
            String estado = horario[dia][h] ? "OCUPADO" : "LIBRE";
            System.out.printf("%02d:00 - %s\n", h, estado);
        }
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getCapacidad() { return capacidad; }
    public static String getNombreDia(int dia) {
        return (dia >= 0 && dia <= 6) ? DIAS[dia] : "Día inválido";
    }

    @Override
    public String toString() {
        return "Aula: " + nombre + " | Capacidad: " + capacidad;
    }
}
