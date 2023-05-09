package com.example.examenparcial2;

import java.io.Serializable;

public class Nodo implements Serializable {
    private Nodo siguiente;
    private String codigo, nombre, descripcion;

    public Nodo(String c, String n, String d, Nodo s) {
        this.siguiente = s;
        this.codigo = c;
        this.nombre = n;
        this.descripcion = d;
    }
    public Nodo getSiguiente() { return siguiente; }
    public void setSiguiente(Nodo siguiente) { this.siguiente = siguiente; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
}
