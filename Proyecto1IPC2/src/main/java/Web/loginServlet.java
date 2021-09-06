/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.EmpleadoDAO;
import Modelos.Empleado;
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
public class loginServlet extends HttpServlet {

    private final String FABRICA = "vistas/principales/fabrica.jsp";
    private final String VENTAS = "vistas/principales/ventas.jsp";
    private final String ADMINISTRACION = "vistas/principales/administracion.jsp";
    private final String FAIL = "img/0nDG5VW.png";
    private final String INDEX = "index.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String destino = "";
        if (accion.equalsIgnoreCase("ingresar")) {
            String empleadoDPI = request.getParameter("txtEmpleado");
            String contraseña = request.getParameter("txtContraseña");

            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            int resultado = empleadoDAO.verificarUsuario(empleadoDPI, contraseña);
            destino = getDestino(resultado);
            if (!destino.equals(INDEX)) {
                Empleado empleado = empleadoDAO.listarDPI(empleadoDPI);
                request.getSession().setAttribute("empleadoActivo", empleado);
            }

        } else if (accion.equalsIgnoreCase("salir")) {
            destino = INDEX;
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destino);
        requestDispatcher.forward(request, response);

    }

    private String getDestino(int resultado) {

        String destino;
        switch (resultado) {
            case -1:
                destino = INDEX;
                break;
            case 1:
                destino = FABRICA;
                break;
            case 2:
                destino = VENTAS;
                break;
            case 3:
                destino = ADMINISTRACION;
                break;
            case 61:
                destino = FAIL;
                break;
            default:
                destino = INDEX;
                break;
        }
        return destino;
    }

}
