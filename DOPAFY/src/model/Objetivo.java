package model;
import java.sql.Timestamp;

public class Objetivo {
    private String descripcion;
    private int userId;
    private int progreso;
    private Timestamp fechaInicio;
    private int cantidadPuntos;
    private DificultadObjetivo dificultad;
    private int goalId;

    // Constructor vacío
    public Objetivo() {
    }

    // Constructor con todos los campos excepto goalId (puede ser autoincremental)
    public Objetivo(String descripcion, int userId, int progreso, Timestamp fechaInicio, int cantidadPuntos, DificultadObjetivo dificultad) {
        this.descripcion = descripcion;
        this.userId = userId;
        setProgreso(progreso); // Validación de progreso
        this.fechaInicio = fechaInicio;
        this.cantidadPuntos = cantidadPuntos;
        this.dificultad = dificultad;
    }

  

	// Getters y setters
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        if (progreso >= 0 && progreso <= 100) {
            this.progreso = progreso;
        } else {
            throw new IllegalArgumentException("El progreso debe estar entre 0 y 100.");
        }
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getCantidadPuntos() {
        return cantidadPuntos;
    }

    public void setCantidadPuntos(int cantidadPuntos) {
        this.cantidadPuntos = cantidadPuntos;
    }

    public DificultadObjetivo getDificultad() {
        return dificultad;
    }

    public void setDificultad(DificultadObjetivo dificultad) {
        this.dificultad = dificultad;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    // toString para imprimir el estado del objeto
    @Override
    public String toString() {
        return "Objetivo{" +
                "descripcion='" + descripcion + '\'' +
                ", userId=" + userId +
                ", progreso=" + progreso +
                ", fechaInicio=" + fechaInicio +
                ", cantidadPuntos=" + cantidadPuntos +
                ", dificultad=" + dificultad +
                ", goalId=" + goalId +
                '}';
    }
}
