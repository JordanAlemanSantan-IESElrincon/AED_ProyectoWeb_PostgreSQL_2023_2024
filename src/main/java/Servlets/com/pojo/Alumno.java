package Servlets.com.pojo;

public class Alumno {
    private int id;
    private String nombre;
    private String apellidos;
    private String curso;
    private int nota;

    public Alumno(int id, String nombre, String apellidos, String curso, int nota) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.curso = curso;
        this.nota = nota;
    }

    public Alumno(String nombre, String apellidos, String curso, int nota) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.curso = curso;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCurso() {
        return curso;
    }

    public int getNota() {
        return nota;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Id: " + getId() 
        + "\nNombre: " + getNombre()
        + "\nApellidos: " + getApellidos()
        + "\nCurso: " + getCurso()
        + "\nNota: " + getNota() + "\n";
    }
}
