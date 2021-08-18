/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Date;

/**
 *
 * @author OrdSon
 */
public class Devolucion {
    private int codigo;
    private java.sql.Date fecha;
    private double total;
    private int muebleDevuelto;
    private int ventaCodigo;

    public Devolucion(int codigo, Date fecha, double total, int muebleDevuelto, int ventaCodigo) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.total = total;
        this.muebleDevuelto = muebleDevuelto;
        this.ventaCodigo = ventaCodigo;
    }

    public Devolucion(Date fecha, double total, int muebleDevuelto, int ventaCodigo) {
        this.fecha = fecha;
        this.total = total;
        this.muebleDevuelto = muebleDevuelto;
        this.ventaCodigo = ventaCodigo;
    }
    
    public Devolucion() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getMuebleDevuelto() {
        return muebleDevuelto;
    }

    public void setMuebleDevuelto(int muebleDevuelto) {
        this.muebleDevuelto = muebleDevuelto;
    }

    public int getVentaCodigo() {
        return ventaCodigo;
    }

    public void setVentaCodigo(int ventaCodigo) {
        this.ventaCodigo = ventaCodigo;
    }
    
    
}
