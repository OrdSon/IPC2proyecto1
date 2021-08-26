/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.DiseñoDAO;
import Modelos.Diseño;
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
public class DiseñoServlet extends HttpServlet {

    String listar = "vistas/diseño/listaDiseños.jsp";
    String añadir = "vistas/diseño/añadirDiseño.jsp";
    String editar = "vistas/diseño/editarDiseño.jsp";

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
            String modelo = request.getParameter("txtModelo");
            String txtPieza = request.getParameter("txtPieza");
            int pieza = Integer.parseInt(txtPieza);
            Diseño diseño = new Diseño( modelo, 0);
            DiseñoDAO diseñoDAO = new DiseñoDAO();
            diseñoDAO.añadir(diseño);
            acceso = listar;
            /*EDICION PASO 2
              Se obtiene la sesion para setear un atributo con nombre "codigoDiseño"
              para poder identificar al diseño en la transaccion,
              luego se redirige a editarDiseño.jsp
             */
        } else if (accion.equalsIgnoreCase("eliminar")) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            DiseñoDAO diseñoDAO = new DiseñoDAO();
            diseñoDAO.eliminar(codigo);
            acceso = listar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

}
