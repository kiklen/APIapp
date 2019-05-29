package com.example.apiapp.Models;

public class Respuesta {

    int id,id_pregunta,valor;
    String respuesta;

    public Respuesta(){}

    public Respuesta(int id, int id_pregunta, int valor, String respuesta) {
        this.id = id;
        this.id_pregunta = id_pregunta;
        this.valor = valor;
        this.respuesta = respuesta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_pregunta() {
        return id_pregunta;
    }

    public void setId_pregunta(int id_pregunta) {
        this.id_pregunta = id_pregunta;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}

