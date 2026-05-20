package universidad.modelo;

import universidad.estructuras.ListaEnlazada;

public class Estudiante extends Persona {
    private int semestre;
    private final double[][] notas;  // [semestre][materia]
    private final String[][] codigosMaterias;
    private final int[] materiasPorSemestre;
    private final ListaEnlazada<String> historialMaterias;

    private static final int MAX_SEMESTRES = 10;
    private static final int MAX_MATERIAS_POR_SEMESTRE = 20;

    public Estudiante(String nombre, String id, String email, int semestre) {
        super(nombre, id, email);
        this.semestre = semestre;
        this.notas = new double[MAX_SEMESTRES][MAX_MATERIAS_POR_SEMESTRE];
        this.codigosMaterias = new String[MAX_SEMESTRES][MAX_MATERIAS_POR_SEMESTRE];
        this.materiasPorSemestre = new int[MAX_SEMESTRES];
        this.historialMaterias = new ListaEnlazada<>();

        // Inicializar
        for (int i = 0; i < MAX_SEMESTRES; i++) {
            materiasPorSemestre[i] = 0;
            for (int j = 0; j < MAX_MATERIAS_POR_SEMESTRE; j++) {
                notas[i][j] = -1;
                codigosMaterias[i][j] = null;
            }
        }
    }

    public boolean registrarNota(int semestreNum, String codigoMateria, double nota) {
        int idx = semestreNum - 1;
        if (idx < 0 || idx >= MAX_SEMESTRES) return false;
        if (nota < 0 || nota > 5) return false;
        if (materiasPorSemestre[idx] >= MAX_MATERIAS_POR_SEMESTRE) return false;

        int pos = materiasPorSemestre[idx];
        notas[idx][pos] = nota;
        codigosMaterias[idx][pos] = codigoMateria;
        materiasPorSemestre[idx]++;
        
        if (!historialMaterias.contiene(codigoMateria)) {
            historialMaterias.agregar(codigoMateria);
        }
        return true;
    }

    public double calcularPromedioSemestre(int semestreNum) {
        int idx = semestreNum - 1;
        if (idx < 0 || idx >= MAX_SEMESTRES || materiasPorSemestre[idx] == 0) return 0;
        
        double suma = 0;
        for (int i = 0; i < materiasPorSemestre[idx]; i++) {
            suma += notas[idx][i];
        }
        return suma / materiasPorSemestre[idx];
    }

    public double calcularPromedioAcumulado() {
        double sumaTotal = 0;
        int totalMaterias = 0;
        
        for (int i = 0; i < MAX_SEMESTRES; i++) {
            for (int j = 0; j < materiasPorSemestre[i]; j++) {
                sumaTotal += notas[i][j];
                totalMaterias++;
            }
        }
        return totalMaterias == 0 ? 0 : sumaTotal / totalMaterias;
    }

    public String generarReporteAcademico() {
        StringBuilder sb = new StringBuilder();
        sb.append("""
        \n========== REPORTE ACADEMICO ==========
        """);
        sb.append("Estudiante: ").append(getNombre()).append(" (ID: ").append(getId()).append(")\n");
        sb.append("Email: ").append(getEmail()).append("\n");
        sb.append("Semestre actual: ").append(semestre).append("\n");
        sb.append("----------------------------------------\n");

        for (int i = 0; i < MAX_SEMESTRES; i++) {
            if (materiasPorSemestre[i] > 0) {
                sb.append("Semestre ").append(i + 1).append(":\n");
                for (int j = 0; j < materiasPorSemestre[i]; j++) {
                    String estado = (notas[i][j] >= 3) ? "APROBADA" : "REPROBADA";
                    sb.append("  ").append(codigosMaterias[i][j])
                      .append(": ").append(String.format("%.2f", notas[i][j]))
                      .append(" - ").append(estado).append("\n");
                }
                sb.append("  Promedio semestre: ").append(String.format("%.2f", calcularPromedioSemestre(i + 1))).append("\n");
            }
        }
        
        sb.append("----------------------------------------\n");
        sb.append("PROMEDIO ACUMULADO: ").append(String.format("%.2f", calcularPromedioAcumulado())).append("\n");
        
        int aprobadas = 0, reprobadas = 0;
        for (int i = 0; i < MAX_SEMESTRES; i++) {
            for (int j = 0; j < materiasPorSemestre[i]; j++) {
                if (notas[i][j] >= 3) aprobadas++;
                else if (notas[i][j] >= 0) reprobadas++;
            }
        }
        sb.append("Materias aprobadas: ").append(aprobadas).append("\n");
        sb.append("Materias reprobadas: ").append(reprobadas).append("\n");
        
        return sb.toString();
    }

    public String generarReporteReprobadas() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== MATERIAS REPROBADAS ===\n");
        boolean hayReprobadas = false;
        
        for (int i = 0; i < MAX_SEMESTRES; i++) {
            for (int j = 0; j < materiasPorSemestre[i]; j++) {
                if (notas[i][j] >= 0 && notas[i][j] < 3) {
                    sb.append("Semestre ").append(i + 1)
                      .append(" | ").append(codigosMaterias[i][j])
                      .append(" | Nota: ").append(notas[i][j]).append("\n");
                    hayReprobadas = true;
                }
            }
        }
        
        if (!hayReprobadas) {
            sb.append("No hay materias reprobadas.\n");
        }
        return sb.toString();
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(generarReporteAcademico());
    }

    // Getters y Setters
    public int getSemestre() { return semestre; }
    public void setSemestre(int semestre) { this.semestre = semestre; }
    public ListaEnlazada<String> getHistorialMaterias() { return historialMaterias; }
}
