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
public class DetalleVenta {
    int codigoVenta;
    double total;
    LocalDate fecha;
    int puntoVenta;
    String modelo;
    String nombreProducto;
    double precio;
    String nit;
    String nombreCliente;
    int codigoProducto;

    public DetalleVenta(int codigoVenta, double total, LocalDate fecha, int puntoVenta, String modelo, String nombreProducto, double precio, String nit, String nombreCliente, int codigoProducto) {
        this.codigoVenta = codigoVenta;
        this.total = total;
        this.fecha = fecha;
        this.puntoVenta = puntoVenta;
        this.modelo = modelo;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.nit = nit;
        this.nombreCliente = nombreCliente;
        this.codigoProducto = codigoProducto;
    }
    
    

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public double getTotal() {
        return total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getPuntoVenta() {
        return puntoVenta;
    }

    public String getModelo() {
        return modelo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public String getNit() {
        return nit;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }
    
    
}
