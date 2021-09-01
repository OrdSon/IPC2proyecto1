/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.MuebleDAO;
import DAO.MuebleEnsambladoDAO;
import Modelos.Empleado;
import Modelos.Mueble;
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
    MuebleDAO muebleDAO = new MuebleDAO();
    MuebleEnsambladoDAO muebleEnsambladoDAO = new MuebleEnsambladoDAO();
    String añadir = "vistas/muebleEnsamblado/añadirMuebleEnsamblado.jsp";
    String listar = "vistas/muebleEnsamblado/EnsamblarMueble.jsp";

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
            Empleado activo = obtenerEmpleado(request);
            String muebleModelo = request.getParameter("txtModelo");
            MuebleEnsamblado muebleEnsamblado = new MuebleEnsamblado(activo.getCodigo(), 2, muebleModelo);
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
        }else if (accion.equalsIgnoreCase("Buscar modelo")) {
            try {
                String modelo = request.getParameter("txtModelo");
                Mueble mueble = muebleDAO.listarCodigo(modelo);
                request.getSession().setAttribute("modeloEnsambleActivo", mueble);
                request.getSession().setAttribute("modeloEnsamble", mueble.getModelo());
            } catch (NullPointerException e) {
            }
            acceso = listar;
        } else if (accion.equalsIgnoreCase("Buscar por nombre")) {
            try {
                String modelo = request.getParameter("txtNombreMueble");
                Mueble mueble = muebleDAO.listarNombre(modelo);
                request.getSession().setAttribute("modeloEnsambleActivo", mueble);
                request.getSession().setAttribute("modeloEnsamble", mueble.getModelo());
            } catch (NullPointerException e) {
            }
            acceso = listar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }
    
    private Empleado obtenerEmpleado(HttpServletRequest request){
        try {
            Empleado activo = (Empleado)request.getSession().getAttribute("empleadoActivo");
            return  activo;
        } catch (Exception e) {
        }
        return  null;
    }
    
}
