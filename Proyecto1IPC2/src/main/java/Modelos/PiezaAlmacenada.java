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
public class PiezaAlmacenada {
    private int codigo;
    private double costo;
    private int piezaCodigo;
    private String piezaTipo;
    private int compraCodigo;
    private int muebleEnsambladoCodigo;

    public PiezaAlmacenada(int codigo, double costo, int piezaCodigo, String piezaTipo, int compraCodigo, int muebleEnsambladoCodigo) {
        this.codigo = codigo;
        this.costo = costo;
        this.piezaCodigo = piezaCodigo;
        this.piezaTipo = piezaTipo;
        this.compraCodigo = compraCodigo;
        this.muebleEnsambladoCodigo = muebleEnsambladoCodigo;
    }

    public PiezaAlmacenada(double costo, int piezaCodigo, String piezaTipo, int compraCodigo, int muebleEnsambladoCodigo) {
        this.costo = costo;
        this.piezaCodigo = piezaCodigo;
        this.piezaTipo = piezaTipo;
        this.compraCodigo = compraCodigo;
        this.muebleEnsambladoCodigo = muebleEnsambladoCodigo;
    }

    public PiezaAlmacenada(double costo, int piezaCodigo, String piezaTipo, int compraCodigo) {
        this.costo = costo;
        this.piezaCodigo = piezaCodigo;
        this.piezaTipo = piezaTipo;
        this.compraCodigo = compraCodigo;
    }

    public PiezaAlmacenada(int codigo, double costo, int piezaCodigo, int muebleEnsambladoCodigo) {
        this.codigo = codigo;
        this.costo = costo;
        this.piezaCodigo = piezaCodigo;
        this.muebleEnsambladoCodigo = muebleEnsambladoCodigo;
    }
    
    
    

    public PiezaAlmacenada() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getPiezaCodigo() {
        return piezaCodigo;
    }

    public void setPiezaCodigo(int piezaCodigo) {
        this.piezaCodigo = piezaCodigo;
    }

    public String getPiezaTipo() {
        return piezaTipo;
    }

    public void setPiezaTipo(String piezaTipo) {
        this.piezaTipo = piezaTipo;
    }

    public int getCompraCodigo() {
        return compraCodigo;
    }

    public void setCompraCodigo(int compraCodigo) {
        this.compraCodigo = compraCodigo;
    }

    public int getMuebleEnsambladoCodigo() {
        return muebleEnsambladoCodigo;
    }

    public void setMuebleEnsambladoCodigo(int muebleEnsambladoCodigo) {
        this.muebleEnsambladoCodigo = muebleEnsambladoCodigo;
    }


    
}
