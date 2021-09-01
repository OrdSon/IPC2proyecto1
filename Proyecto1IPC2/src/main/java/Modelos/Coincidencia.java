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
public class Coincidencia {
    int pieza;
    String tipo;
    int disponibles;
    double costo;
    String modelo_mueble;
    int necesarias;

    public Coincidencia(int pieza, String tipo, int disponibles, double costo, String modelo_mueble, int necesarias) {
        this.pieza = pieza;
        this.tipo = tipo;
        this.disponibles = disponibles;
        this.costo = costo;
        this.modelo_mueble = modelo_mueble;
        this.necesarias = necesarias;
    }

    public Coincidencia(String tipo, int disponibles, double costo, String modelo_mueble, int necesarias) {
        this.tipo = tipo;
        this.disponibles = disponibles;
        this.costo = costo;
        this.modelo_mueble = modelo_mueble;
        this.necesarias = necesarias;
    }

    public Coincidencia(int pieza, String tipo, int disponibles, double costo, int necesarias) {
        this.pieza = pieza;
        this.tipo = tipo;
        this.disponibles = disponibles;
        this.costo = costo;
        this.necesarias = necesarias;
    }

    public int getPieza() {
        return pieza;
    }

    public void setPieza(int pieza) {
        this.pieza = pieza;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getModelo_mueble() {
        return modelo_mueble;
    }

    public void setModelo_mueble(String modelo_mueble) {
        this.modelo_mueble = modelo_mueble;
    }

    public int getNecesarias() {
        return necesarias;
    }

    public void setNecesarias(int necesarias) {
        this.necesarias = necesarias;
    }
    
    
}
