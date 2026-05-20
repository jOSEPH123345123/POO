package universidad;

import java.util.Scanner;
import universidad.excepciones.*;
import universidad.modelo.Estudiante;
import universidad.util.SistemaUniversitario;

public class Main {
    private static final SistemaUniversitario sistema = new SistemaUniversitario();
    private static Scanner scanner;

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     PLANIFICACIÓN ACADÉMICA - SISTEMA UNIVERSITARIO       ║");
        System.out.println("║              ESTRUCTURAS DE DATOS - PROYECTO FINAL        ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        // Datos demo
        sistema.registrarEstudiante("Ana María Gómez", "2024001", "ana@universidad.edu", 3);
        sistema.registrarEstudiante("Juan Pérez", "2024002", "juan@universidad.edu", 1);
        sistema.registrarEstudiante("María López", "2024003", "maria@universidad.edu", 2);

        try (Scanner sc = new Scanner(System.in)) {
            scanner = sc;
            int opcion;
            do {
                mostrarMenu();
                opcion = leerOpcion();
                procesarOpcion(opcion);
            } while (opcion != 25);

            System.out.println("\n¡Gracias por usar el Sistema Universitario!");
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("                     MENÚ PRINCIPAL");
        System.out.println("═".repeat(60));
        System.out.println("=== GESTIÓN DE ESTUDIANTES ===");
        System.out.println("  1. Registrar estudiante");
        System.out.println("  2. Buscar estudiante por ID");
        System.out.println("  3. Listar todos los estudiantes");
        System.out.println("  4. Eliminar estudiante");
        System.out.println("\n=== GESTIÓN DE MATERIAS ===");
        System.out.println("  5. Crear materia");
        System.out.println("  6. Agregar pre-requisito");
        System.out.println("  7. Mostrar pre-requisitos");
        System.out.println("  8. Inscribir estudiante");
        System.out.println("  9. Cancelar inscripción");
        System.out.println(" 10. Mostrar cola de espera");
        System.out.println(" 11. Listar materias");
        System.out.println("\n=== GESTIÓN DE HORARIOS ===");
        System.out.println(" 12. Reservar horario en aula");
        System.out.println(" 13. Liberar horario");
        System.out.println(" 14. Consultar disponibilidad");
        System.out.println(" 15. Agregar aula");
        System.out.println(" 16. Listar aulas");
        System.out.println("\n=== GESTIÓN DE NOTAS ===");
        System.out.println(" 17. Registrar nota");
        System.out.println(" 18. Ver reporte académico");
        System.out.println(" 19. Ver materias reprobadas");
        System.out.println("\n=== RUTAS ENTRE EDIFICIOS ===");
        System.out.println(" 20. Agregar edificio al campus");
        System.out.println(" 21. Agregar conexión entre edificios");
        System.out.println(" 22. Mostrar edificios");
        System.out.println(" 23. Calcular ruta más corta");
        System.out.println("\n=== SISTEMA DESHACER/REHACER ===");
        System.out.println(" 24. Deshacer última operación");
        System.out.println(" 25. Rehacer última operación");
        System.out.println("\n=== PROCESAMIENTO BATCH ===");
        System.out.println(" 26. Cargar solicitudes CSV");
        System.out.println(" 27. Procesar cola batch");
        System.out.println("\n=== SALIR ===");
        System.out.println(" 28. Salir");
        System.out.println("═".repeat(60));
        System.out.print("Seleccione una opción: ");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void procesarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1 -> registrarEstudiante();
                case 2 -> buscarEstudiante();
                case 3 -> sistema.listarEstudiantes();
                case 4 -> eliminarEstudiante();
                case 5 -> crearMateria();
                case 6 -> agregarPreRequisito();
                case 7 -> mostrarPreRequisitos();
                case 8 -> inscribirEstudiante();
                case 9 -> cancelarInscripcion();
                case 10 -> mostrarColaEspera();
                case 11 -> sistema.listarMaterias();
                case 12 -> reservarHorario();
                case 13 -> liberarHorario();
                case 14 -> consultarDisponibilidad();
                case 15 -> agregarAula();
                case 16 -> sistema.listarAulas();
                case 17 -> registrarNota();
                case 18 -> verReporteAcademico();
                case 19 -> verMateriasReprobadas();
                case 20 -> agregarEdificio();
                case 21 -> agregarConexion();
                case 22 -> sistema.mostrarEdificiosCampus();
                case 23 -> calcularRuta();
                case 24 -> sistema.deshacerOperacion();
                case 25 -> sistema.rehacerOperacion();
                case 26 -> cargarCSV();
                case 27 -> sistema.procesarColaBatch();
                case 28 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } catch (EstudianteNoEncontradoException | PreRequisitoNoAprobadoException | HorarioConflictivoException | ArchivoInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ==================== MÉTODOS AUXILIARES ====================
    private static void registrarEstudiante() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Semestre actual: ");
        int semestre = Integer.parseInt(scanner.nextLine());
        sistema.registrarEstudiante(nombre, id, email, semestre);
    }

    private static void buscarEstudiante() throws EstudianteNoEncontradoException {
        System.out.print("ID del estudiante: ");
        String id = scanner.nextLine();
        Estudiante e = sistema.buscarEstudiantePorId(id);
        e.mostrarInformacion();
    }

    private static void eliminarEstudiante() throws EstudianteNoEncontradoException {
        System.out.print("ID del estudiante a eliminar: ");
        String id = scanner.nextLine();
        sistema.eliminarEstudiante(id);
    }

    private static void crearMateria() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Cupos máximos: ");
        int cupos = Integer.parseInt(scanner.nextLine());
        System.out.print("Créditos: ");
        int creditos = Integer.parseInt(scanner.nextLine());
        sistema.crearMateria(codigo, nombre, cupos, creditos);
    }

    private static void agregarPreRequisito() {
        System.out.print("Código materia: ");
        String codMat = scanner.nextLine();
        System.out.print("Código pre-requisito: ");
        String preReq = scanner.nextLine();
        sistema.agregarPreRequisito(codMat, preReq);
    }

    private static void mostrarPreRequisitos() {
        System.out.print("Código materia: ");
        String codMat = scanner.nextLine();
        sistema.mostrarPreRequisitos(codMat);
    }

    private static void inscribirEstudiante() throws EstudianteNoEncontradoException, PreRequisitoNoAprobadoException {
        System.out.print("ID estudiante: ");
        String id = scanner.nextLine();
        System.out.print("Código materia: ");
        String codMat = scanner.nextLine();
        sistema.inscribirEstudiante(id, codMat);
    }

    private static void cancelarInscripcion() throws EstudianteNoEncontradoException {
        System.out.print("ID estudiante: ");
        String id = scanner.nextLine();
        System.out.print("Código materia: ");
        String codMat = scanner.nextLine();
        sistema.cancelarInscripcion(id, codMat);
    }

    private static void mostrarColaEspera() {
        System.out.print("Código materia: ");
        String codMat = scanner.nextLine();
        sistema.mostrarColaEspera(codMat);
    }

    private static void reservarHorario() throws HorarioConflictivoException {
        System.out.print("Nombre aula: ");
        String aula = scanner.nextLine();
        System.out.print("Día (0=Domingo,1=Lunes,...,6=Sábado): ");
        int dia = Integer.parseInt(scanner.nextLine());
        System.out.print("Hora (0-23): ");
        int hora = Integer.parseInt(scanner.nextLine());
        System.out.print("Duración (horas): ");
        int duracion = Integer.parseInt(scanner.nextLine());
        sistema.reservarHorario(aula, dia, hora, duracion);
    }

    private static void liberarHorario() {
        System.out.print("Nombre aula: ");
        String aula = scanner.nextLine();
        System.out.print("Día (0-6): ");
        int dia = Integer.parseInt(scanner.nextLine());
        System.out.print("Hora (0-23): ");
        int hora = Integer.parseInt(scanner.nextLine());
        System.out.print("Duración (horas): ");
        int duracion = Integer.parseInt(scanner.nextLine());
        sistema.liberarHorario(aula, dia, hora, duracion);
    }

    private static void consultarDisponibilidad() {
        System.out.print("Nombre aula: ");
        String aula = scanner.nextLine();
        System.out.print("Día (0-6): ");
        int dia = Integer.parseInt(scanner.nextLine());
        sistema.consultarDisponibilidadAula(aula, dia);
    }

    private static void agregarAula() {
        System.out.print("Nombre aula: ");
        String nombre = scanner.nextLine();
        System.out.print("Capacidad: ");
        int capacidad = Integer.parseInt(scanner.nextLine());
        sistema.agregarAula(nombre, capacidad);
    }

    private static void registrarNota() throws EstudianteNoEncontradoException {
        System.out.print("ID estudiante: ");
        String id = scanner.nextLine();
        System.out.print("Código materia: ");
        String codMat = scanner.nextLine();
        Integer semestre = leerEntero("Semestre (1-10): ");
        if (semestre == null) return;
        Double nota = leerDouble("Nota (0-5): ");
        if (nota == null) return;
        sistema.registrarNota(id, codMat, semestre, nota);
    }

    private static void verReporteAcademico() throws EstudianteNoEncontradoException {
        System.out.print("ID estudiante: ");
        String id = scanner.nextLine();
        sistema.verReporteAcademico(id);
    }

    private static void verMateriasReprobadas() throws EstudianteNoEncontradoException {
        System.out.print("ID estudiante: ");
        String id = scanner.nextLine();
        sistema.verReporteReprobadas(id);
    }

    private static void agregarEdificio() {
        System.out.print("Nombre edificio: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String desc = scanner.nextLine();
        sistema.agregarEdificioCampus(nombre, desc);
    }

    private static void agregarConexion() {
        Integer origen = leerEntero("Índice origen: ");
        if (origen == null) return;
        Integer destino = leerEntero("Índice destino: ");
        if (destino == null) return;
        Integer distancia = leerEntero("Distancia (metros): ");
        if (distancia == null) return;
        sistema.agregarConexionCampus(origen, destino, distancia);
    }

    private static void calcularRuta() {
        Integer origen = leerEntero("Índice origen: ");
        if (origen == null) return;
        Integer destino = leerEntero("Índice destino: ");
        if (destino == null) return;
        sistema.mostrarRutaCampus(origen, destino);
    }

    private static void cargarCSV() throws ArchivoInvalidoException {
        System.out.print("Ruta del archivo CSV: ");
        sistema.cargarSolicitudesCSV(scanner.nextLine().trim());
    }

    // ---------- Helpers de parseo con validación ----------
    private static Integer leerEntero(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida: se esperaba un número entero.");
            return null;
        }
    }

    private static Double leerDouble(String prompt) {
        System.out.print(prompt);
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida: se esperaba un número (decimal).");
            return null;
        }
    }
}
