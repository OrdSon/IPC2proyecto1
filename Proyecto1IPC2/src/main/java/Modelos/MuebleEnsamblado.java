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
public class MuebleEnsamblado {
    private int codigo;
    private int empleadoCodigo;
    private int puntoVentaCodigo;
    private String muebleModelo;
    private String muebleNombre;
    private int cantidad;
    private double precio;
    private double costo;
    private LocalDate fecha;
    
    
    public MuebleEnsamblado(int codigo, int empleadoCodigo, int puntoVentaCodigo, String muebleModelo) {
        this.codigo = codigo;
        this.empleadoCodigo = empleadoCodigo;
        this.puntoVentaCodigo = puntoVentaCodigo;
        this.muebleModelo = muebleModelo;
    }
    public MuebleEnsamblado(int codigo, int empleadoCodigo, int puntoVentaCodigo, String muebleModelo,int cantidad, double precio, double costo) {
        this.codigo = codigo;
        this.empleadoCodigo = empleadoCodigo;
        this.puntoVentaCodigo = puntoVentaCodigo;
        this.muebleModelo = muebleModelo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.costo = costo;
    }
    public MuebleEnsamblado(int codigo, int empleadoCodigo, int puntoVentaCodigo, String muebleModelo,int cantidad, double precio, double costo, LocalDate fecha, String muebleNombre) {
        this.codigo = codigo;
        this.empleadoCodigo = empleadoCodigo;
        this.puntoVentaCodigo = puntoVentaCodigo;
        this.muebleModelo = muebleModelo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.costo = costo;
        this.fecha = fecha;
        this.muebleNombre = muebleNombre;
    }

    public MuebleEnsamblado(int empleadoCodigo, int puntoVentaCodigo, String muebleModelo) {
        this.empleadoCodigo = empleadoCodigo;
        this.puntoVentaCodigo = puntoVentaCodigo;
        this.muebleModelo = muebleModelo;
    }
    
    public MuebleEnsamblado(int empleadoCodigo, int puntoVentaCodigo, String muebleModelo, LocalDate fecha) {
        this.empleadoCodigo = empleadoCodigo;
        this.puntoVentaCodigo = puntoVentaCodigo;
        this.muebleModelo = muebleModelo;
        this.fecha = fecha;
    }

    public MuebleEnsamblado(int codigo, String muebleModelo, String muebleNombre, double precio, double costo) {
        this.codigo = codigo;
        this.muebleModelo = muebleModelo;
        this.muebleNombre = muebleNombre;
        this.precio = precio;
        this.costo = costo;
    }
    

    public MuebleEnsamblado() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getEmpleadoCodigo() {
        return empleadoCodigo;
    }

    public void setEmpleadoCodigo(int empleadoCodigo) {
        this.empleadoCodigo = empleadoCodigo;
    }

    public int getPuntoVentaCodigo() {
        return puntoVentaCodigo;
    }

    public void setPuntoVentaCodigo(int puntoVentaCodigo) {
        this.puntoVentaCodigo = puntoVentaCodigo;
    }

    public String getMuebleModelo() {
        return muebleModelo;
    }

    public void setMuebleModelo(String muebleModelo) {
        this.muebleModelo = muebleModelo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

    public String getMuebleNombre() {
        return muebleNombre;
    }

    public void setMuebleNombre(String muebleNombre) {
        this.muebleNombre = muebleNombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    
    
}
