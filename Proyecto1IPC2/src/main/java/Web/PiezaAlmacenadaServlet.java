/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.PiezaAlmacenadaDAO;
import Modelos.PiezaAlmacenada;
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
public class PiezaAlmacenadaServlet extends HttpServlet {

    String listar = "vistas/piezaAlmacenada/listarPiezas.jsp";
    String añadir = "vistas/piezaAlmacenada/añadirPiezas.jsp";
    String editar = "vistas/piezaAlmacenada/editarPiezas.jsp";

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
            
            String txtCosto = request.getParameter("txtCosto");
            String txtPiezaCodigo = request.getParameter("txtPiezaCodigo");
            String txtCompraCodigo = request.getParameter("txtCompraCodigo");
            String txtMuebleEnsambladoCodigo = request.getParameter("txtMuebleEnsambladoCodigo");
            String tipo = request.getParameter("txtTipo");
            
            double costo = Double.parseDouble(txtCosto);
            int piezaCodigo = Integer.parseInt(txtPiezaCodigo);
            int compraCodigo = Integer.parseInt(txtCompraCodigo);
            int muebleEnsambladoCodigo = Integer.parseInt(txtMuebleEnsambladoCodigo);
            
            
            PiezaAlmacenada piezaAlmacenada = new PiezaAlmacenada(costo, piezaCodigo, tipo, compraCodigo, muebleEnsambladoCodigo);
            PiezaAlmacenadaDAO piezaAlmacenadaDAO = new PiezaAlmacenadaDAO();
            piezaAlmacenadaDAO.añadir(piezaAlmacenada);
            acceso = listar;
            /*EDICION PASO 2
              Se obtiene la sesion para setear un atributo con nombre "codigoPiezaAlmacenada"
              para poder identificar al piezaAlmacenada en la transaccion,
              luego se redirige a editarPiezaAlmacenada.jsp
             */
        } else if (accion.equalsIgnoreCase("eliminar")) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            PiezaAlmacenadaDAO piezaAlmacenadaDAO = new PiezaAlmacenadaDAO();
            piezaAlmacenadaDAO.eliminar(codigo);
            acceso = listar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }
}
