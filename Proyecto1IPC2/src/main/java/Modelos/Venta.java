
package Modelos;

import java.sql.Date;

/**
 *
 * @author OrdSon
 */
public class Venta {
    private int codigo;
    private double total;
    private Date fecha;
    private int puntoVentaCodigo;
    private int empleadoCodigo;
    private int clienteCodigo;

    public Venta(int codigo, double total, Date fecha, int puntoVentaCodigo, int empleadoCodigo, int clienteCodigo) {
        this.codigo = codigo;
        this.total = total;
        this.fecha = fecha;
        this.puntoVentaCodigo = puntoVentaCodigo;
        this.empleadoCodigo = empleadoCodigo;
        this.clienteCodigo = clienteCodigo;
    }

    public Venta(double total, Date fecha, int puntoVentaCodigo, int empleadoCodigo, int clienteCodigo) {
        this.total = total;
        this.fecha = fecha;
        this.puntoVentaCodigo = puntoVentaCodigo;
        this.empleadoCodigo = empleadoCodigo;
        this.clienteCodigo = clienteCodigo;
    }

    public Venta() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPuntoVentaCodigo() {
        return puntoVentaCodigo;
    }

    public void setPuntoVentaCodigo(int puntoVentaCodigo) {
        this.puntoVentaCodigo = puntoVentaCodigo;
    }

    public int getEmpleadoCodigo() {
        return empleadoCodigo;
    }

    public void setEmpleadoCodigo(int empleadoCodigo) {
        this.empleadoCodigo = empleadoCodigo;
    }

    public int getClienteCodigo() {
        return clienteCodigo;
    }

    public void setClienteCodigo(int clienteCodigo) {
        this.clienteCodigo = clienteCodigo;
    }
    
    
}
