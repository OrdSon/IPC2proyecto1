/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.LoteVentaDAO;
import Modelos.LoteVenta;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author OrdSon
 */
@WebServlet(name = "LoteVentaServlet", urlPatterns = {"/LoteVentaServlet"})
public class LoteVentaServlet extends HttpServlet {

    String listar = "vistas/loteVenta/listaLoteVentas.jsp";
    String añadir = "vistas/loteVenta/añadirLoteVenta.jsp";
    String editar = "vistas/loteVenta/editarLoteVenta.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("listar")) {
            acceso = listar;
        } else if (accion.equalsIgnoreCase("nuevo")) {
            acceso = añadir;
        } else if (accion.equalsIgnoreCase("añadir")) {
            String txtMuebleEnsamblado = request.getParameter("txtMuebleEnsamblado");
            String txtVenta = request.getParameter("txtVenta");
            
            int muebleEnsamblado = Integer.parseInt(txtMuebleEnsamblado);
            int venta = Integer.parseInt(txtVenta);

            LoteVenta loteVenta = new LoteVenta(muebleEnsamblado, venta);
            LoteVentaDAO loteVentaDAO = new LoteVentaDAO();
            loteVentaDAO.añadir(loteVenta);
            acceso = listar;

        }  else if (accion.equalsIgnoreCase("eliminar")) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            LoteVentaDAO loteVentaDAO = new LoteVentaDAO();
            loteVentaDAO.eliminar(codigo); 
            acceso = listar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

}
