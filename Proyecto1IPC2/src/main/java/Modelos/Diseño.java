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
public class Diseño {
    int codigo;
    String modelo;
    int pieza;
    int cantidad;

    public Diseño(int codigo, String modelo, int pieza, int cantidad) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.pieza = pieza;
        this.cantidad = cantidad;
    }

    public Diseño(String modelo, int pieza, int cantidad) {
        this.modelo = modelo;
        this.pieza = pieza;
        this.cantidad = cantidad;
    }

    public Diseño() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getPieza() {
        return pieza;
    }

    public void setPieza(int pieza) {
        this.pieza = pieza;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
    
      
}
