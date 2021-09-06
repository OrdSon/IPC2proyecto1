/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.DiseñoDAO;
import DAO.MuebleDAO;
import DAO.PiezaDAO;
import Modelos.Diseño;
import Modelos.Mueble;
import Modelos.Pieza;
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
public class DesignServlet extends HttpServlet {


String listar = "vistas/diseño/listarDiseños.jsp";
    DiseñoDAO diseñoDAO = new DiseñoDAO();
    MuebleDAO muebleDAO = new MuebleDAO();
    PiezaDAO piezaDAO = new PiezaDAO();
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
        } else if (accion.equalsIgnoreCase("añadir")) {
            String modeloMueble = request.getParameter("txtModelo");
            //String nombreMueble = request.getParameter("txtNombreMueble");
            String txtCodigoPieza = request.getParameter("txtCodigoPieza");
            //String txtNombrePieza = request.getParameter("txtNombrePieza");
            String txtCantidad = request.getParameter("txtCantidad");
            int pieza = Integer.parseInt(txtCodigoPieza);
            int cantidad = Integer.parseInt(txtCantidad);

            Diseño diseño = new Diseño(modeloMueble, pieza, cantidad);

            diseñoDAO.añadir(diseño);
            acceso = listar;

        } else if (accion.equalsIgnoreCase("eliminar")) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            diseñoDAO.eliminar(codigo);
            acceso = listar;
        }else if (accion.equalsIgnoreCase("Buscar modelo")) {
            try {
                String modelo = request.getParameter("txtModelo");
                Mueble mueble = muebleDAO.listarCodigo(modelo);
                request.getSession().setAttribute("muebleDiseñoActivo", mueble);
                request.getSession().setAttribute("modeloDiseño", mueble.getModelo());
            } catch (NullPointerException e) {
            }
            acceso = listar;
        } else if (accion.equalsIgnoreCase("Buscar por nombre")) {
            try {
                String modelo = request.getParameter("txtNombreMueble");
                Mueble mueble = muebleDAO.listarNombre(modelo);
                request.getSession().setAttribute("muebleDiseñoActivo", mueble);
                request.getSession().setAttribute("modeloDiseño", mueble.getModelo());
            } catch (NullPointerException e) {
            }
            acceso = listar;
        }else if (accion.equalsIgnoreCase("Buscar por tipo")) {
            try {
                String nombre = request.getParameter("txtNombrePieza");
                Pieza pieza = piezaDAO.listarNombre(nombre);
                request.getSession().setAttribute("piezaDiseñoActiva", pieza);
                
            } catch (NullPointerException e) {
            }
            acceso = listar;
        } else if (accion.equalsIgnoreCase("Buscar pieza")) {
            try {
                String txtCodigo = request.getParameter("txtCodigoPieza");
                int codigo = Integer.parseInt(txtCodigo);
                Pieza pieza = piezaDAO.listarCodigo(codigo);
                request.getSession().setAttribute("piezaDiseñoActiva", pieza);
            } catch (NullPointerException e) {
            }
            acceso = listar;
        }
        
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }
}
