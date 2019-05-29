package com.example.apiapp.Models;

public class Materia {

    int id,semestre;
    String nombre;


    public Materia(){}
    public Materia(int id, int semestre, String nombre) {
        this.id = id;
        this.semestre = semestre;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
