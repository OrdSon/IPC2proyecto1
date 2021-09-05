/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import Modelos.DetalleVenta;
import Modelos.VentaRealizada;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author OrdSon
 */
public class ReporteVentas extends Reporte{

    public ReporteVentas() {
    }

    public String getReporteVentas(HttpServletRequest request) {
        ArrayList<VentaRealizada> ventas = (ArrayList<VentaRealizada>) request.getSession().getAttribute("ventasReporte");
        ArrayList<ArrayList<DetalleVenta>> detalles = (ArrayList<ArrayList<DetalleVenta>>) request.getSession().getAttribute("detallesReporte");
        String encabezado = (String) request.getSession().getAttribute("encabezadoVentas");
        String columnasVenta = "Codigo de venta;Total;Nit del cliente;Nombre del cliente;Fecha;Empleado \n";
        String columnasDetalle = "Codigo de producto;Modelo;Nombre;Precio \n";

        String reporte = "";
        reporte += encabezado + "\n";

        for (int i = 0; i < ventas.size(); i++) {
            reporte += columnasVenta;
            VentaRealizada ventaTemp = ventas.get(i);
            String filaVenta = ventaTemp.getCodigoVenta() + ";" + ventaTemp.getTotal() + ";" + ventaTemp.getNIT() + ";" + ventaTemp.getNombreCliente() + ";" + ventaTemp.getFecha()+";"+ventaTemp.getEmpleadoNombre();
            reporte += filaVenta + "\n";
            for (int j = 0; j < detalles.get(i).size(); j++) {
                if (j == 0) {
                    reporte += columnasDetalle;
                }
                DetalleVenta detalleTemp = detalles.get(i).get(j);
                String filaDetalle = detalleTemp.getCodigoProducto() + ";" + detalleTemp.getModelo() + ";" + detalleTemp.getNombreProducto() + ";" + detalleTemp.getPrecio();
                reporte += filaDetalle + "\n";
            }
        }
        return reporte;
    }

}
