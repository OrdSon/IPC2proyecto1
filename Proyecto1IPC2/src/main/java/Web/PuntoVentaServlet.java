package Web;



import DAO.PuntoVentaDAO;
import Modelos.PuntoVenta;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ordson
 */
public class PuntoVentaServlet extends HttpServlet {

    String listar = "vistas/punto_venta/listaPuntosVenta.jsp";
    String añadir = "vistas/punto_venta/añadirPuntoVenta.jsp";
    String editar = "vistas/punto_venta/editarPuntoVenta.jsp";
    
    PuntoVenta puntoVenta = new PuntoVenta();
    PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();

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
            String nombre = request.getParameter("txtNombre");
            String telefono = request.getParameter("txtTelefono");
            String direccion = request.getParameter("txtDireccion");
            puntoVenta = new PuntoVenta(nombre, telefono, direccion);
            puntoVentaDAO = new PuntoVentaDAO();
            puntoVentaDAO.añadir(puntoVenta);
            acceso = listar;
            /*EDICION PASO 2
              Se obtiene la sesion para setear un atributo con nombre "codigoPunto"
              para poder identificar al cliente en la transaccion,
              luego se redirige a editarPuntoVenta.jsp
            */
        }else if(accion.equalsIgnoreCase("editar")){
            request.getSession().setAttribute("codigoPunto", request.getParameter("codigo"));
            acceso = editar;
            /*EDITAR PASO 4 
             Se reciben los campos de texto y se crea un objeto PuntoVenta con esa informacion
             luego se envia este objeto como parametro al metodo puntoVenta.editar
             para ejecutar la transaccion
            */
        }else if (accion.equalsIgnoreCase("actualizar")) {
            System.out.println((String)request.getParameter("txtCodigo")+"  clienteServlet");
            int codigo = Integer.parseInt((String)request.getSession().getAttribute("codigoPunto"));
            String nombre = request.getParameter("txtNombre");
            String telefono = request.getParameter("txtTelefono");
            String direccion = request.getParameter("txtDireccion");
            puntoVenta = new PuntoVenta(codigo, nombre, telefono, direccion);
            puntoVentaDAO.editar(puntoVenta);
            acceso = listar;
            /*ELIMINAR PASO 2:
              Se toma el atributo codigo de listarPuntosVenta.jsp
              y se pasa como parametro al metodo puntoVentaDAO.eliminar();
              y se pasa a eliminar directamente a la base de datos
              esta eliminacion debe ser validada mas tarde por un javaScript
            */
        }else if (accion.equalsIgnoreCase("eliminar")){
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            puntoVentaDAO.eliminar(codigo);
            acceso = listar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
