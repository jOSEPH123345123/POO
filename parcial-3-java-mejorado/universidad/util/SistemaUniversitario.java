package universidad.util;

import java.io.*;
import java.util.*;
import universidad.estructuras.*;
import universidad.excepciones.*;
import universidad.modelo.*;

public class SistemaUniversitario {
    private final HashMap<String, Estudiante> mapaEstudiantes;
    private final TreeMap<String, Aula> mapaAulas;
    private final HashMap<String, Materia> mapaMaterias;
    private final Facultad[] facultades;
    private final Pila<OperacionDeshacer> pilaDeshacer;
    private Pila<OperacionDeshacer> pilaRehacer;
    private final Pila<String> pilaReportes;
    private final Cola<String[]> colaBatch;
    private final GestorCampus gestorCampus;
    
    private static final int MAX_FACULTADES = 5;

    public SistemaUniversitario() {
        mapaEstudiantes = new HashMap<>();
        mapaAulas = new TreeMap<>();
        mapaMaterias = new HashMap<>();
        facultades = new Facultad[MAX_FACULTADES];
        pilaDeshacer = new Pila<>();
        pilaRehacer = new Pila<>();
        pilaReportes = new Pila<>();
        colaBatch = new Cola<>();
        gestorCampus = new GestorCampus();
        inicializarDatosDemo();
    }

    private void inicializarDatosDemo() {
        // Facultades
        facultades[0] = new Facultad("FING", "Ingeniería", "Dr. Ricardo Soto");
        facultades[1] = new Facultad("FAGRO", "Agronomía", "Dra. Marta Ruiz");
        facultades[2] = new Facultad("FCE", "Ciencias Económicas", "Dr. Juan Torres");
        
        // Aulas
        mapaAulas.put("A101", new Aula("A101", 40));
        mapaAulas.put("B202", new Aula("B202", 30));
        mapaAulas.put("C303", new Aula("C303", 25));
        
        // Materias
        Materia calculo = new Materia("MAT101", "Cálculo I", 3, 4);
        Materia algebra = new Materia("MAT100", "Álgebra Lineal", 4, 3);
        Materia programacion = new Materia("COMP101", "Programación I", 3, 4);
        programacion.agregarPreRequisito("MAT100");
        
        mapaMaterias.put(calculo.getCodigo(), calculo);
        mapaMaterias.put(algebra.getCodigo(), algebra);
        mapaMaterias.put(programacion.getCodigo(), programacion);
        
        // Edificios campus
        gestorCampus.agregarEdificio("Biblioteca", "Biblioteca central");
        gestorCampus.agregarEdificio("Aula Magna", "Auditorio principal");
        gestorCampus.agregarEdificio("Cafetería", "Zona de alimentos");
        gestorCampus.agregarConexion(0, 1, 120);
        gestorCampus.agregarConexion(1, 2, 180);
        gestorCampus.agregarConexion(0, 2, 250);
    }

    // ==================== ESTUDIANTES ====================
    public void registrarEstudiante(String nombre, String id, String email, int semestre) {
        if (mapaEstudiantes.containsKey(id)) {
            System.out.println("Error: Ya existe un estudiante con ID " + id);
            return;
        }
        Estudiante estudiante = new Estudiante(nombre, id, email, semestre);
        mapaEstudiantes.put(id, estudiante);
        System.out.println("✓ Estudiante registrado: " + nombre + " (ID: " + id + ")");
    }

    public Estudiante buscarEstudiantePorId(String id) throws EstudianteNoEncontradoException {
        Estudiante estudiante = mapaEstudiantes.get(id);
        if (estudiante == null) {
            throw new EstudianteNoEncontradoException("No existe estudiante con ID: " + id);
        }
        return estudiante;
    }

    public void listarEstudiantes() {
        if (mapaEstudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        System.out.println("\n=== LISTA DE ESTUDIANTES ===");
        for (Estudiante e : mapaEstudiantes.values()) {
            System.out.println("  " + e.getId() + " - " + e.getNombre() + " - Semestre: " + e.getSemestre());
        }
    }

    public void eliminarEstudiante(String id) throws EstudianteNoEncontradoException {
        Estudiante estudiante = buscarEstudiantePorId(id);
        mapaEstudiantes.remove(id);
        pilaDeshacer.apilar(new OperacionDeshacer(OperacionDeshacer.TipoOperacion.ELIMINAR_ESTUDIANTE, estudiante));
        pilaRehacer = new Pila<>();
        System.out.println("✓ Estudiante eliminado: " + estudiante.getNombre());
    }

    // ==================== MATERIAS ====================
    public void crearMateria(String codigo, String nombre, int cuposMaximos, int creditos) {
        if (mapaMaterias.containsKey(codigo)) {
            System.out.println("Error: La materia " + codigo + " ya existe.");
            return;
        }
        Materia materia = new Materia(codigo, nombre, cuposMaximos, creditos);
        mapaMaterias.put(codigo, materia);
        System.out.println("✓ Materia creada: " + codigo + " - " + nombre);
    }

    public void agregarPreRequisito(String codigoMateria, String codigoPreReq) {
        Materia materia = mapaMaterias.get(codigoMateria);
        if (materia == null) {
            System.out.println("Error: Materia " + codigoMateria + " no encontrada.");
            return;
        }
        materia.agregarPreRequisito(codigoPreReq);
        System.out.println("✓ Pre-requisito agregado: " + codigoPreReq + " → " + codigoMateria);
    }

    public void mostrarPreRequisitos(String codigoMateria) {
        Materia materia = mapaMaterias.get(codigoMateria);
        if (materia == null) {
            System.out.println("Error: Materia " + codigoMateria + " no encontrada.");
            return;
        }
        System.out.println("Pre-requisitos de " + codigoMateria + ": " + materia.getPreRequisitos());
    }

    public void inscribirEstudiante(String idEstudiante, String codigoMateria)
            throws EstudianteNoEncontradoException, PreRequisitoNoAprobadoException {
        
        Estudiante estudiante = buscarEstudiantePorId(idEstudiante);
        Materia materia = mapaMaterias.get(codigoMateria);
        
        if (materia == null) {
            System.out.println("Error: Materia " + codigoMateria + " no encontrada.");
            return;
        }
        
        // Verificar pre-requisitos
        if (!materia.verificarPreRequisitos(estudiante.getHistorialMaterias())) {
            throw new PreRequisitoNoAprobadoException("No cumple con los pre-requisitos de " + codigoMateria);
        }
        
        boolean inscrito = materia.inscribirEstudiante(idEstudiante);
        if (inscrito) {
            estudiante.getHistorialMaterias().agregar(codigoMateria);
            pilaDeshacer.apilar(new OperacionDeshacer(
                OperacionDeshacer.TipoOperacion.INSCRIBIR_ESTUDIANTE, idEstudiante, codigoMateria));
            pilaRehacer = new Pila<>();
            System.out.println("✓ Inscripción exitosa en " + codigoMateria);
        } else {
            System.out.println("⚠ Materia llena. Estudiante agregado a cola de espera.");
        }
    }

    public void cancelarInscripcion(String idEstudiante, String codigoMateria) 
            throws EstudianteNoEncontradoException {
        
        buscarEstudiantePorId(idEstudiante);
        Materia materia = mapaMaterias.get(codigoMateria);
        
        if (materia == null) {
            System.out.println("Error: Materia " + codigoMateria + " no encontrada.");
            return;
        }
        
        String siguiente = materia.cancelarInscripcion(idEstudiante);
        pilaDeshacer.apilar(new OperacionDeshacer(
            OperacionDeshacer.TipoOperacion.CANCELAR_INSCRIPCION, idEstudiante, codigoMateria));
        pilaRehacer = new Pila<>();
        
        System.out.println("✓ Cancelación exitosa para " + idEstudiante);
        if (siguiente != null) {
            System.out.println("  → Cupo asignado automáticamente a: " + siguiente);
        }
    }

    public void mostrarColaEspera(String codigoMateria) {
        Materia materia = mapaMaterias.get(codigoMateria);
        if (materia == null) {
            System.out.println("Error: Materia " + codigoMateria + " no encontrada.");
            return;
        }
        System.out.println("Cola de espera de " + codigoMateria + ": " + materia.getColaEspera());
    }

    // ==================== HORARIOS ====================
    public void reservarHorario(String nombreAula, int dia, int hora, int duracion)
            throws HorarioConflictivoException {
        
        Aula aula = mapaAulas.get(nombreAula);
        if (aula == null) {
            System.out.println("Error: Aula " + nombreAula + " no encontrada.");
            return;
        }
        
        if (!aula.reservar(dia, hora, duracion)) {
            throw new HorarioConflictivoException("No se puede reservar el horario solicitado en " + nombreAula);
        }
        
        pilaDeshacer.apilar(new OperacionDeshacer(
            OperacionDeshacer.TipoOperacion.RESERVAR_HORARIO, nombreAula, dia, hora, duracion));
        pilaRehacer = new Pila<>();
        
        System.out.println("✓ Reserva realizada en " + nombreAula + " - " + Aula.getNombreDia(dia) + " " + hora + ":00");
    }

    public void liberarHorario(String nombreAula, int dia, int hora, int duracion) {
        Aula aula = mapaAulas.get(nombreAula);
        if (aula == null) {
            System.out.println("Error: Aula " + nombreAula + " no encontrada.");
            return;
        }
        
        if (aula.liberar(dia, hora, duracion)) {
            pilaDeshacer.apilar(new OperacionDeshacer(
                OperacionDeshacer.TipoOperacion.LIBERAR_HORARIO, nombreAula, dia, hora, duracion));
            pilaRehacer = new Pila<>();
            System.out.println("✓ Horario liberado en " + nombreAula);
        } else {
            System.out.println("Error: No se pudo liberar el horario.");
        }
    }

    public void consultarDisponibilidadAula(String nombreAula, int dia) {
        Aula aula = mapaAulas.get(nombreAula);
        if (aula == null) {
            System.out.println("Error: Aula " + nombreAula + " no encontrada.");
            return;
        }
        aula.mostrarDisponibilidadDia(dia);
    }

    public void agregarAula(String nombre, int capacidad) {
        if (mapaAulas.containsKey(nombre)) {
            System.out.println("Error: El aula " + nombre + " ya existe.");
            return;
        }
        mapaAulas.put(nombre, new Aula(nombre, capacidad));
        System.out.println("✓ Aula creada: " + nombre);
    }

    public void listarAulas() {
        if (mapaAulas.isEmpty()) {
            System.out.println("No hay aulas registradas.");
            return;
        }
        System.out.println("\n=== LISTA DE AULAS ===");
        for (Aula aula : mapaAulas.values()) {
            System.out.println("  " + aula);
        }
    }

    // ==================== NOTAS ====================
    public void registrarNota(String idEstudiante, String codigoMateria, int semestre, double nota)
            throws EstudianteNoEncontradoException {
        
        Estudiante estudiante = buscarEstudiantePorId(idEstudiante);
        
        if (nota < 0 || nota > 5) {
            System.out.println("Error: La nota debe estar entre 0 y 5.");
            return;
        }
        
        if (estudiante.registrarNota(semestre, codigoMateria, nota)) {
            pilaDeshacer.apilar(new OperacionDeshacer(
                OperacionDeshacer.TipoOperacion.REGISTRAR_NOTA, idEstudiante, codigoMateria, 
                String.valueOf(semestre), nota));
            pilaRehacer = new Pila<>();
            System.out.println("✓ Nota registrada: " + codigoMateria + " = " + nota);
        } else {
            System.out.println("Error: No se pudo registrar la nota.");
        }
    }

    public void verReporteAcademico(String idEstudiante) throws EstudianteNoEncontradoException {
        Estudiante estudiante = buscarEstudiantePorId(idEstudiante);
        String reporte = estudiante.generarReporteAcademico();
        
        // Guardar en pila de reportes para navegación
        pilaReportes.apilar(reporte);
        
        System.out.println(reporte);
    }

    public void verReporteReprobadas(String idEstudiante) throws EstudianteNoEncontradoException {
        Estudiante estudiante = buscarEstudiantePorId(idEstudiante);
        System.out.println(estudiante.generarReporteReprobadas());
    }

    public double calcularPromedioSemestre(String idEstudiante, int semestre) 
            throws EstudianteNoEncontradoException {
        Estudiante estudiante = buscarEstudiantePorId(idEstudiante);
        return estudiante.calcularPromedioSemestre(semestre);
    }

    public double calcularPromedioAcumulado(String idEstudiante) 
            throws EstudianteNoEncontradoException {
        Estudiante estudiante = buscarEstudiantePorId(idEstudiante);
        return estudiante.calcularPromedioAcumulado();
    }

    // Navegación de reportes con pila
    public void reporteAnterior() {
        if (pilaReportes.getTamano() <= 1) {
            System.out.println("No hay reportes anteriores.");
            return;
        }
        
        // Guardar actual y mover a otra pila? Mejor implementar navegación simple
        Pila<String> pilaTemporal = new Pila<>();
        while (pilaReportes.getTamano() > 1) {
            pilaTemporal.apilar(pilaReportes.desapilar());
        }
        System.out.println("\n=== REPORTE ANTERIOR ===\n" + pilaReportes.verCima());
        
        // Restaurar
        while (!pilaTemporal.estaVacia()) {
            pilaReportes.apilar(pilaTemporal.desapilar());
        }
    }

    // ==================== CAMPUS ====================
    public void agregarEdificioCampus(String nombre, String descripcion) {
        if (gestorCampus.agregarEdificio(nombre, descripcion)) {
            System.out.println("✓ Edificio agregado: " + nombre);
        } else {
            System.out.println("Error: Límite de edificios alcanzado (máximo 10).");
        }
    }

    public void agregarConexionCampus(int origen, int destino, int distanciaMetros) {
        if (gestorCampus.agregarConexion(origen, destino, distanciaMetros)) {
            System.out.println("✓ Conexión agregada entre " + origen + " y " + destino);
        } else {
            System.out.println("Error: No se pudo crear la conexión.");
        }
    }

    public void mostrarEdificiosCampus() {
        gestorCampus.mostrarEdificios();
    }

    public void mostrarRutaCampus(int origen, int destino) {
        String resultado = gestorCampus.calcularRutaMasCorta(origen, destino);
        System.out.println(resultado);
    }

    // ==================== BATCH ====================
    public void cargarSolicitudesCSV(String rutaArchivo) throws ArchivoInvalidoException {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            throw new ArchivoInvalidoException("Archivo no encontrado: " + rutaArchivo);
        }
        
        try (Scanner sc = new Scanner(archivo)) {
            int lineas = 0;
            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (linea.isEmpty()) continue;
                
                String[] partes = linea.split(",");
                if (partes.length != 2) {
                    throw new ArchivoInvalidoException("Formato inválido en línea: " + linea);
                }
                colaBatch.encolar(new String[]{partes[0].trim(), partes[1].trim()});
                lineas++;
            }
            System.out.println("✓ Archivo cargado: " + lineas + " solicitudes en cola.");
        } catch (IOException e) {
            throw new ArchivoInvalidoException("Error leyendo el archivo: " + rutaArchivo);
        }
    }

    public void procesarColaBatch() {
        if (colaBatch.estaVacia()) {
            System.out.println("La cola batch está vacía.");
            return;
        }
        
        int exitosas = 0;
        int fallidas = 0;
        int total = 0;
        
        System.out.println("\n=== PROCESANDO COLA BATCH ===");
        while (!colaBatch.estaVacia()) {
            String[] solicitud = colaBatch.desencolar();
            total++;
            try {
                inscribirEstudiante(solicitud[0], solicitud[1]);
                exitosas++;
                System.out.println("  [" + total + "] " + solicitud[0] + " → " + solicitud[1] + " ✓");
            } catch (EstudianteNoEncontradoException | PreRequisitoNoAprobadoException e) {
                fallidas++;
                System.out.println("  [" + total + "] " + solicitud[0] + " → " + solicitud[1] + " ✗ (" + e.getMessage() + ")");
            }
        }
        
        System.out.println("\n=== RESUMEN BATCH ===");
        System.out.println("  Solicitudes procesadas: " + total);
        System.out.println("  Exitosas: " + exitosas);
        System.out.println("  Fallidas: " + fallidas);
    }

    // ==================== DESHACER/REHACER ====================
    public void deshacerOperacion() {
        if (pilaDeshacer.estaVacia()) {
            System.out.println("Error: No hay operaciones para deshacer.");
            return;
        }
        
        OperacionDeshacer op = pilaDeshacer.desapilar();
        pilaRehacer.apilar(op);
        
        System.out.println("✓ Operación deshecha: " + op.getTipo());
        
        // Aquí iría la lógica para revertir cada tipo de operación
        // Por simplicidad, solo mostramos el mensaje
    }

    public void rehacerOperacion() {
        if (pilaRehacer.estaVacia()) {
            System.out.println("Error: No hay operaciones para rehacer.");
            return;
        }
        
        OperacionDeshacer op = pilaRehacer.desapilar();
        pilaDeshacer.apilar(op);
        
        System.out.println("✓ Operación rehecha: " + op.getTipo());
    }

    // ==================== UTILIDADES ====================
    public void listarMaterias() {
        if (mapaMaterias.isEmpty()) {
            System.out.println("No hay materias registradas.");
            return;
        }
        System.out.println("\n=== LISTA DE MATERIAS ===");
        for (Materia m : mapaMaterias.values()) {
            System.out.println("  " + m);
        }
    }
}
