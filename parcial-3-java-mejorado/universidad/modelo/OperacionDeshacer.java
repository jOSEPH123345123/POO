package universidad.modelo;

public class OperacionDeshacer {
    public enum TipoOperacion {
        INSCRIBIR_ESTUDIANTE,
        CANCELAR_INSCRIPCION,
        ELIMINAR_ESTUDIANTE,
        RESERVAR_HORARIO,
        LIBERAR_HORARIO,
        REGISTRAR_NOTA
    }

    private final TipoOperacion tipo;
    private String idEstudiante;
    private String codigoMateria;
    private Estudiante estudianteEliminado;
    private String nombreAula;
    private int dia;
    private int hora;
    private int duracion;
    @SuppressWarnings("unused")
    private String semestreNota;
    private double nota;

    // Constructores
    public OperacionDeshacer(TipoOperacion tipo, String idEstudiante, String codigoMateria) {
        this.tipo = tipo;
        this.idEstudiante = idEstudiante;
        this.codigoMateria = codigoMateria;
    }

    public OperacionDeshacer(TipoOperacion tipo, Estudiante estudianteEliminado) {
        this.tipo = tipo;
        this.estudianteEliminado = estudianteEliminado;
    }

    public OperacionDeshacer(TipoOperacion tipo, String nombreAula, int dia, int hora, int duracion) {
        this.tipo = tipo;
        this.nombreAula = nombreAula;
        this.dia = dia;
        this.hora = hora;
        this.duracion = duracion;
    }

    public OperacionDeshacer(TipoOperacion tipo, String idEstudiante, String codigoMateria, String semestreNota, double nota) {
        this.tipo = tipo;
        this.idEstudiante = idEstudiante;
        this.codigoMateria = codigoMateria;
        this.semestreNota = semestreNota;
        this.nota = nota;
    }

    // Getters
    public TipoOperacion getTipo() { return tipo; }
    public String getIdEstudiante() { return idEstudiante; }
    public String getCodigoMateria() { return codigoMateria; }
    public Estudiante getEstudianteEliminado() { return estudianteEliminado; }
    public String getNombreAula() { return nombreAula; }
    public int getDia() { return dia; }
    public int getHora() { return hora; }
    public int getDuracion() { return duracion; }
    public double getNota() { return nota; }
}
