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
public class Devolucion {
    private int codigo;
    private LocalDate fecha;
    private double total;
    private int muebleDevuelto;
    private int ventaCodigo;

    public Devolucion(int codigo, LocalDate fecha, double total, int muebleDevuelto, int ventaCodigo) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.total = total;
        this.muebleDevuelto = muebleDevuelto;
        this.ventaCodigo = ventaCodigo;
    }

    public Devolucion(LocalDate fecha, double total, int muebleDevuelto, int ventaCodigo) {
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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
