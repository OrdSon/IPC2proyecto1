/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import DAO.EmpleadoDAO;
import Modelos.Empleado;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ordson
 */
public class EmpleadoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String listar = "vistas/empleado/listaEmpleados.jsp";
    String añadir = "vistas/empleado/añadirEmpleado.jsp";
    String editar = "vistas/empleado/editarEmpleado.jsp";

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
            
            Empleado empleado = getEmpleado(request);
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            empleadoDAO.añadir(empleado);
            acceso = listar;

        } else if (accion.equalsIgnoreCase("editar")) {
            request.getSession().setAttribute("codigoEmpleado", request.getParameter("codigo"));
            acceso = editar;

        } else if (accion.equalsIgnoreCase("actualizar")) {
            int codigo = Integer.parseInt((String) request.getSession().getAttribute("codigoEmpleado"));
            Empleado empleado = getEmpleado(request);
            empleado.setCodigo(codigo);
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            empleadoDAO.editar(empleado);
            acceso = listar;

        } else if (accion.equalsIgnoreCase("eliminar")) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            empleadoDAO.eliminar(codigo);
            acceso = listar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    private Empleado getEmpleado(HttpServletRequest request) {
        String nombre = request.getParameter("txtNombre");
        String txtarea = request.getParameter("txtarea");
        String contraseña = request.getParameter("txtContraseña");
        String dpi = request.getParameter("txtDpi");
        String telefono = request.getParameter("txtTelefono");
        String direccion = request.getParameter("txtDireccion");
        String birth = request.getParameter("txtBirth");
        String salario = request.getParameter("txtSalario");
        String debut = request.getParameter("txtDebut");
        System.out.println(birth +"  FECHA DE NACIMIENTO");
        System.out.println(debut + " FECHA DE CONTRATACION");

        int area = verificarArea(txtarea);
        LocalDate fecha_nacimiento = LocalDate.parse(birth);
        LocalDate fecha_contratacion = LocalDate.parse(debut);
        Empleado empleado = new Empleado(nombre, area, contraseña, dpi, telefono, direccion, fecha_nacimiento, salario, fecha_contratacion);
        return empleado;
    }

    private int verificarArea(String txtArea) {
        switch (txtArea) {
            case "Fabrica":
                return 1;

            case "Ventas":
                return 2;

            case "Financiero":
                return 3;
            default:
                return -1;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */




















    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
