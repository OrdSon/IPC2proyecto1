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
public class Compra {
    private int codigo;
    private java.sql.Date fecha;
    private double total;
    private int puntoVentaCodigo;

    public Compra(int codigo, Date fecha, double total, int puntoVentaCodigo) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.total = total;
        this.puntoVentaCodigo = puntoVentaCodigo;
    }

    public Compra(Date fecha, double total, int puntoVentaCodigo) {
        this.fecha = fecha;
        this.total = total;
        this.puntoVentaCodigo = puntoVentaCodigo;
    }

    public Compra() {
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

    public int getPuntoVentaCodigo() {
        return puntoVentaCodigo;
    }

    public void setPuntoVentaCodigo(int puntoVentaCodigo) {
        this.puntoVentaCodigo = puntoVentaCodigo;
    }
    
    
}
