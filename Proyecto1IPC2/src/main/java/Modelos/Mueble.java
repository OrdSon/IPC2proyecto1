/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author OrdSon
 */
public class Mueble {
    private String modelo;
    private String nombre;
    private double precio;
    private double costo;

    public Mueble(String modelo, String nombre, double precio, double costo) {
        this.modelo = modelo;
        this.nombre = nombre;
        this.precio = precio;
        this.costo = costo;
    }

    public Mueble(String nombre, double precio, double costo) {
        this.nombre = nombre;
        this.precio = precio;
        this.costo = costo;
    }

    public Mueble() {
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    
    
}
