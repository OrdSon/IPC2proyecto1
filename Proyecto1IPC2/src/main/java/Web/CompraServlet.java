/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.CompraDAO;
import Modelos.Compra;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author OrdSon
 */
public class CompraServlet extends HttpServlet {

    String listar = "vistas/compra/listaCompras.jsp";
    String añadir = "vistas/compra/añadirCompra.jsp";

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
            String txtFecha = request.getParameter("txtFecha");
            String txtTotal = request.getParameter("txtTotal");
            String txtPuntoVentaCodigo = request.getParameter("txtPuntoVentaCodigo");

            LocalDate fecha = LocalDate.parse(txtFecha);
            double total = Double.parseDouble(txtTotal);
            int puntoVentaCodigo = Integer.parseInt(txtPuntoVentaCodigo);
            Compra compra = new Compra(fecha, total, puntoVentaCodigo);
            CompraDAO compraDAO = new CompraDAO();
            compraDAO.añadir(compra);
            acceso = listar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

}
