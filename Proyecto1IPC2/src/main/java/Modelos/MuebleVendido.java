/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.time.LocalDate;

/**
 *
 * @author OrdSon
 */
public class MuebleVendido {
    int empleadoCodigo;
    String empleadoDPI;
    String empleadoNombre;
    String modelo;
    String nombre;
    double precio;
    double costo;
    int productoCodigo;
    int ventaCodigo;
    double ganancia;
    LocalDate fecha;

    public MuebleVendido(int empleadoCodigo, String empleadoDPI, String empleadoNombre, String modelo, String nombre, double precio, double costo, int productoCodigo, int ventaCodigo, LocalDate fecha) {
        this.empleadoCodigo = empleadoCodigo;
        this.empleadoDPI = empleadoDPI;
        this.empleadoNombre = empleadoNombre;
        this.modelo = modelo;
        this.nombre = nombre;
        this.precio = precio;
        this.costo = costo;
        this.productoCodigo = productoCodigo;
        this.ventaCodigo = ventaCodigo;
        this.fecha = fecha;
        this.ganancia = (precio - costo);
    }

    public int getEmpleadoCodigo() {
        return empleadoCodigo;
    }

    public String getEmpleadoDPI() {
        return empleadoDPI;
    }

    public String getEmpleadoNombre() {
        return empleadoNombre;
    }

    public String getModelo() {
        return modelo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public double getCosto() {
        return costo;
    }

    public int getProductoCodigo() {
        return productoCodigo;
    }

    public int getVentaCodigo() {
        return ventaCodigo;
    }

    public double getGanancia() {
        return ganancia;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    
    
}
