/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author OrdSon
 */
public class Reporte {
    public void exportarReporte(HttpServletResponse response, String reporte, String nombre) {
        response.setContentType("text/csv;charset=UTF-8");
        response.setHeader("Content-Disposition", "attatchment; filename="+nombre+".csv");
        try (PrintWriter writer = response.getWriter();) {
            writer.println(reporte);
        } catch (IOException e) {
        }
    }
}
