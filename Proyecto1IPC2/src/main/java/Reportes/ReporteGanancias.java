/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import Modelos.MuebleVendido;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author OrdSon
 */
public class ReporteGanancias extends Reporte {

    public ReporteGanancias() {
    }

    public String getReporteGanancias(HttpServletRequest request) {
        ArrayList<MuebleVendido> muebles = (ArrayList<MuebleVendido>) request.getSession().getAttribute("reporteGanancias");
        String encabezado = (String) request.getSession().getAttribute("encabezadoGanancias")+"\n";
        String columnas = "DPI del empleado;Nombre del empleado;Codigo de producto;Producto;Precio;Costo;Ganancia;Fecha \n";
        double contador = 0;
        String reporte = "";
        reporte += encabezado;
        reporte += columnas;
        for (int i = 0; i < muebles.size(); i++) {
            MuebleVendido temporal = muebles.get(i);
            contador += temporal.getGanancia();
            String fila = temporal.getEmpleadoDPI()+";"+temporal.getEmpleadoNombre()+";"+temporal.getProductoCodigo()+";"+temporal.getNombre()+";"+temporal.getPrecio()+";"+temporal.getCosto()+";"+temporal.getGanancia();
            if (temporal.getFecha() != null) {
                fila += ";"+temporal.getFecha();
            }
            reporte+=fila+"\n";
        }
        reporte += "Ganancia total: "+contador;
        return  reporte;
    }

}
