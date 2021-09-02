package Modelos;

/**
 *
 * @author OrdSon
 */
public class Caja {
    private int codigo;
    private double capital;
    private int puntoVentaCodigo;

    public Caja(int codigo, double capital, int puntoVentaCodigo) {
        this.codigo = codigo;
        this.capital = capital;
        this.puntoVentaCodigo = puntoVentaCodigo;
    }

    public Caja(double capital, int puntoVentaCodigo) {
        this.capital = capital;
        this.puntoVentaCodigo = puntoVentaCodigo;
    }

    public Caja(int codigo, double capital) {
        this.codigo = codigo;
        this.capital = capital;
    }
    
    

    public Caja() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public int getPuntoVentaCodigo() {
        return puntoVentaCodigo;
    }

    public void setPuntoVentaCodigo(int puntoVentaCodigo) {
        this.puntoVentaCodigo = puntoVentaCodigo;
    }
    
    
}
