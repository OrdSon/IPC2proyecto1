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
public class Movimiento {
    private int codigo;
    private double monto;
    private double resultado;
    private int ventaCodigo;
    private int compraCodigo;
    private int cajaCodigo;

    public Movimiento(int codigo, double monto, double resultado, int ventaCodigo, int compraCodigo, int cajaCodigo) {
        this.codigo = codigo;
        this.monto = monto;
        this.resultado = resultado;
        this.ventaCodigo = ventaCodigo;
        this.compraCodigo = compraCodigo;
        this.cajaCodigo = cajaCodigo;
    }

    public Movimiento(double monto, double resultado, int ventaCodigo, int compraCodigo, int cajaCodigo) {
        this.monto = monto;
        this.resultado = resultado;
        this.ventaCodigo = ventaCodigo;
        this.compraCodigo = compraCodigo;
        this.cajaCodigo = cajaCodigo;
    }

    public Movimiento() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public int getVentaCodigo() {
        return ventaCodigo;
    }

    public void setVentaCodigo(int ventaCodigo) {
        this.ventaCodigo = ventaCodigo;
    }

    public int getCompraCodigo() {
        return compraCodigo;
    }

    public void setCompraCodigo(int compraCodigo) {
        this.compraCodigo = compraCodigo;
    }

    public int getCajaCodigo() {
        return cajaCodigo;
    }

    public void setCajaCodigo(int cajaCodigo) {
        this.cajaCodigo = cajaCodigo;
    }
    
    
}
