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
public class MuebleEnsamblado {
    private int codigo;
    private int empleadoCodigo;
    private int puntoVentaCodigo;
    private String muebleModelo;

    public MuebleEnsamblado(int codigo, int empleadoCodigo, int puntoVentaCodigo, String muebleModelo) {
        this.codigo = codigo;
        this.empleadoCodigo = empleadoCodigo;
        this.puntoVentaCodigo = puntoVentaCodigo;
        this.muebleModelo = muebleModelo;
    }

    public MuebleEnsamblado(int empleadoCodigo, int puntoVentaCodigo, String muebleModelo) {
        this.empleadoCodigo = empleadoCodigo;
        this.puntoVentaCodigo = puntoVentaCodigo;
        this.muebleModelo = muebleModelo;
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
    
    
}
