/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.MuebleEnsambladoDAO;
import Modelos.MuebleEnsamblado;
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
public class MuebleEnsambladoServlet extends HttpServlet {

    String listar = "vistas/muebleEnsamblado/listaMuebleEnsamblados.jsp";
    String añadir = "vistas/muebleEnsamblado/añadirMuebleEnsamblado.jsp";
    String editar = "vistas/muebleEnsamblado/editarMuebleEnsamblado.jsp";

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
            int empleadoCodigo = Integer.parseInt(request.getParameter("txtEmpleadoCodigo"));
            int puntoVentaCodigo  = Integer.parseInt(request.getParameter("txtPuntoVentaCodigo"));
            String modelo = request.getParameter("txtModelo");

            MuebleEnsamblado muebleEnsamblado = new MuebleEnsamblado(empleadoCodigo,puntoVentaCodigo, modelo);
            MuebleEnsambladoDAO muebleEnsambladoDAO = new MuebleEnsambladoDAO();
            muebleEnsambladoDAO.añadir(muebleEnsamblado);
            acceso = listar;
            /*EDICION PASO 2
              Se obtiene la sesion para setear un atributo con nombre "codigoMuebleEnsamblado"
              para poder identificar al muebleEnsamblado en la transaccion,
              luego se redirige a editarMuebleEnsamblado.jsp
             */
        } else if (accion.equalsIgnoreCase("eliminar")) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            MuebleEnsambladoDAO muebleEnsambladoDAO = new MuebleEnsambladoDAO();
            muebleEnsambladoDAO.eliminar(codigo);
            acceso = listar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }
}
