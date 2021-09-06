/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import Modelos.DetalleDevolucion;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author OrdSon
 */
public class ReporteDevoluciones extends Reporte{
    
    public String getReporteDevoluciones(HttpServletRequest request){

            ArrayList<DetalleDevolucion> detalles = (ArrayList<DetalleDevolucion>)request.getSession().getAttribute("listaDevoluciones");
            String columnas = "Nit del cliente;Nombre;ID producto;Modelo;Dinero devuelto;Fecha";
            String reporte = "";
            reporte += columnas+"\n";
            for (int i = 0; i < detalles.size(); i++) {
                DetalleDevolucion temporal = detalles.get(i);
                String fila = temporal.getNit()+";"+temporal.getNombre()+";"+temporal.getProducto()+";"+temporal.getModelo()+";"+temporal.getTotal()+";"+temporal.getFecha()+"\n";
                reporte+=fila;
            }
            return reporte;
  
    }
}
