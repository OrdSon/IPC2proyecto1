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
public class VentaRealizada {
    private int codigoVenta;
    private double total;
    private LocalDate fecha;
    private int clienteCodigo;
    private String NIT;
    private String nombreCliente;
    private String empleadoNombre;
    private int puntoVenta;

    public VentaRealizada(int codigoVenta, double total, LocalDate fecha, int clienteCodigo, String NIT, String nombreCliente, String empleadoNombre, int puntoVenta) {
        this.codigoVenta = codigoVenta;
        this.total = total;
        this.fecha = fecha;
        this.clienteCodigo = clienteCodigo;
        this.NIT = NIT;
        this.nombreCliente = nombreCliente;
        this.empleadoNombre = empleadoNombre;
        this.puntoVenta = puntoVenta;
    }

    public int getPuntoVenta() {
        return puntoVenta;
    }

    public void setPuntoVenta(int puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getClienteCodigo() {
        return clienteCodigo;
    }

    public void setClienteCodigo(int clienteCodigo) {
        this.clienteCodigo = clienteCodigo;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEmpleadoNombre() {
        return empleadoNombre;
    }

    public void setEmpleadoNombre(String empleadoNombre) {
        this.empleadoNombre = empleadoNombre;
    }

   
    
}
