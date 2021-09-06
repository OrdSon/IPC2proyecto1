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
public class LoteVenta {
    private int codigo;
    private int muebleEnsambladoCodigo;
    private int venta_codigo;

    public LoteVenta(int codigo, int muebleEnsambladoCodigo, int venta_codigo) {
        this.codigo = codigo;
        this.muebleEnsambladoCodigo = muebleEnsambladoCodigo;
        this.venta_codigo = venta_codigo;
    }

    public LoteVenta(int muebleEnsambladoCodigo, int venta_codigo) {
        this.muebleEnsambladoCodigo = muebleEnsambladoCodigo;
        this.venta_codigo = venta_codigo;
    }

    public LoteVenta() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getMuebleEnsambladoCodigo() {
        return muebleEnsambladoCodigo;
    }

    public void setMuebleEnsambladoCodigo(int muebleEnsambladoCodigo) {
        this.muebleEnsambladoCodigo = muebleEnsambladoCodigo;
    }

    public int getVenta_codigo() {
        return venta_codigo;
    }

    public void setVenta_codigo(int venta_codigo) {
        this.venta_codigo = venta_codigo;
    }
    
    
}
