package Modelo;
import java.util.Collections;
import java.util.List;

public class Examen {
    private List<Pregunta> listaPreguntas;
    private int indiceActual;
    private int aciertos;

    public Examen(List<Pregunta> preguntas) {
        this.listaPreguntas = preguntas;
        this.indiceActual = 0;
        this.aciertos = 0;
    }

    public void barajar() {
        Collections.shuffle(listaPreguntas);
    }

    public Pregunta obtenerActual() {
        return (indiceActual < listaPreguntas.size()) ? listaPreguntas.get(indiceActual) : null;
    }

    public boolean verificarRespuesta(String seleccion) {
        if (obtenerActual().getRespuestaCorrecta().equals(seleccion)) {
            aciertos++;
            return true;
        }
        return false;
    }

    public boolean siguiente() {
        indiceActual++;
        return indiceActual < listaPreguntas.size();
    }

    public int getAciertos() { return aciertos; }
    public int getTotal() { return listaPreguntas.size(); }
    public int getNumeroPregunta() { return indiceActual + 1; }
}