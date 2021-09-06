/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import Modelos.DetalleVenta;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author OrdSon
 */
public class ReporteMuebles extends Reporte{

    public ReporteMuebles() {
    }

    public String getReporteMuebles(HttpServletRequest request) {
        ArrayList<DetalleVenta> detallesVenta = (ArrayList<DetalleVenta>) request.getSession().getAttribute("reporteMuebles");
        String encabezado =(String) request.getSession().getAttribute("encabezadoVentas")+"\n";
        String columnas = "Codigo de venta;Fecha;Codigo de producto;Modelo;Nombre;Precio;Nit del cliente;Nombre del cliente \n";
        String reporte ="";
        reporte+=encabezado;
        reporte+=columnas;
        for (int i = 0; i < detallesVenta.size(); i++) {
            DetalleVenta temporal = detallesVenta.get(i);
            String fila = temporal.getCodigoVenta()+";"+temporal.getFecha()+";"+temporal.getCodigoProducto()+";"+temporal.getModelo()+";"+temporal.getNombreProducto()+";"+temporal.getPrecio()+";"+temporal.getNit()+";"+temporal.getNombreCliente()+"\n";
            reporte+=fila;
        }
        return reporte;
    }   

}
