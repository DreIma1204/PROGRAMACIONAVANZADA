package Modelo;

public class MFilaCatalogo {
    private String profesor;
    private String asignatura;
    private String grupo;
    private String alumno;

    public MFilaCatalogo(String profesor, String asignatura, String grupo, String alumno) {
        this.profesor = profesor;
        this.asignatura = asignatura;
        this.grupo = grupo;
        this.alumno = alumno;
    }

    public String obtenerProfesor() { return profesor; }
    public String obtenerAsignatura() { return asignatura; }
    public String obtenerGrupo() { return grupo; }
    public String obtenerAlumno() { return alumno; }
}