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
public class piezaComprada {
    String tipo;
    int cantidad;
    double costo;

    public piezaComprada(String tipo, int cantidad, double costo) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.costo = costo;
    }

    public piezaComprada() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    
    
}
