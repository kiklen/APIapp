package com.example.apiapp.Models;

public class Pregunta {

    int id, id_evaluacion, puntuacion;
    String pregunta;

    public Pregunta(){}

    public Pregunta(int id, int id_evaluacion, int puntuacion, String pregunta) {
        this.id = id;
        this.id_evaluacion = id_evaluacion;
        this.puntuacion = puntuacion;
        this.pregunta = pregunta;
    }

    public Pregunta(int id, int puntuacion, String pregunta) {
        this.id = id;
        this.puntuacion = puntuacion;
        this.pregunta = pregunta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_evaluacion() {
        return id_evaluacion;
    }

    public void setId_evaluacion(int id_evaluacion) {
        this.id_evaluacion = id_evaluacion;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}
