/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.MovimientoDAO;
import Modelos.Movimiento;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author OrdSon
 */
public class MovimientoServlet extends HttpServlet {

    String listar = "vistas/movimiento/listaMovimientos.jsp";
    String añadir = "vistas/movimiento/añadirMovimiento.jsp";

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
            String txtMonto = request.getParameter("txtMonto");
            String txtResultado = request.getParameter("txtResultado");
            String txtVenta= request.getParameter("txtVenta");
            String txtCompra = request.getParameter("txtCompra");
            String txtCaja= request.getParameter("txtCaja");
            
            double monto = Double.parseDouble(txtMonto);
            int resultado = Integer.parseInt(txtResultado);
            int venta = Integer.parseInt(txtVenta);
            int compra = Integer.parseInt(txtCompra);
            int caja = Integer.parseInt(txtCaja);
            
            Movimiento movimiento = new Movimiento(monto, resultado, venta, compra, caja);
            MovimientoDAO movimientoDAO = new MovimientoDAO();
            movimientoDAO.añadir(movimiento);
            acceso = listar;
           
        } 
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

}
