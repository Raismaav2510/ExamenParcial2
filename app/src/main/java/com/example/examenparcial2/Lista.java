package com.example.examenparcial2;

import java.io.Serializable;

public class Lista implements Serializable {
    private Nodo inicio, fin;

    public Lista() {
        inicio = null;
        fin = null;
    }
    public boolean estaVacia() {
        if(inicio == null) {
            return true;
        } else {
            return false;
        }
    }
    public void insertarFinal(String codigo, String nombre, String descripcion) {
        Nodo actual;
        if(estaVacia()) {
            actual = new Nodo(codigo, nombre, descripcion, null);
            inicio = actual;
            fin = actual;
        } else {
            actual = new Nodo(codigo, nombre, descripcion, null);
            fin.setSiguiente(actual);
            fin = actual;
        }
    }
    public String mostrar() {
        String mensaje = "";
        if(estaVacia()) {
            return "No hay nodos en la lista enlazada";
        } else {
            Nodo temporal = inicio;
            while(temporal != null) {
                mensaje = mensaje + temporal.getCodigo() + "\n" + temporal.getNombre() + "\n" + temporal.getDescripcion() + "\n\n";
                temporal = temporal.getSiguiente();
            }
            return mensaje;
        }
    }
    public String buscar(String codigo) {
        String mensaje = "";
        boolean bandera = false;
        if(estaVacia()) {
            return "No hay nodos en la lista enlazada";
        } else {
            Nodo temporal = inicio;
            while(temporal != null) {
                if(codigo.equals(temporal.getCodigo())) {
                    mensaje = mensaje + temporal.getCodigo() + "\n" + temporal.getNombre() + "\n" + temporal.getDescripcion() + "\n\n";
                    bandera = true;
                }
                temporal = temporal.getSiguiente();
            }
            if(!bandera) {
                mensaje = "No hay valores almacenados con el código leído";
            }
            return mensaje;
        }
    }
}
