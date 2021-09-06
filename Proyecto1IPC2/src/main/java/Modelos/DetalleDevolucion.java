/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author OrdSon
 */
public class DetalleDevolucion implements Serializable{
     private  String nit;
    private String nombre;
    private int producto;
    private String modelo;
    private String mueble;
    private double total;
    private LocalDate fecha;

    public DetalleDevolucion(String nit, String nombre, int producto, String modelo, String mueble, double total, LocalDate fecha) {
        this.nit = nit;
        this.nombre = nombre;
        this.producto = producto;
        this.modelo = modelo;
        this.mueble = mueble;
        this.total = total;
        this.fecha = fecha;
    }

    public String getNit() {
        return nit;
    }

    public String getNombre() {
        return nombre;
    }

    public int getProducto() {
        return producto;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMueble() {
        return mueble;
    }

    public double getTotal() {
        return total;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
