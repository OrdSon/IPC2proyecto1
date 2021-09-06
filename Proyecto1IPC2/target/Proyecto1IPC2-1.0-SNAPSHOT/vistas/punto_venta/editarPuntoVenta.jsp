<%-- 
    Document   : editarCliente
    Created on : 15/08/2021, 07:00:45 PM
    Author     : ordson
--%>


<%@page import="Modelos.PuntoVenta"%>
<%@page import="DAO.PuntoVentaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Editar cliente</h1>
        <%
            /*EDICION PASO 3
              Se crea una instancia de ClienteDAO y se hacen 3 cosas:
              Se selecciona al cliente con el codigo guardado en la sesion
              Se muestran los datos de dicho cliente en los campos de texto
              Se envian los campos de texto con sus nuevos contenidos al metodo editar de ClienteDAO para actualizarlos
            */
            PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
            int codigo = Integer.parseInt((String)request.getSession().getAttribute("codigoPunto"));
            PuntoVenta puntoVenta = puntoVentaDAO.listarCodigo(codigo);
        %>
        <div>
            <form>
                
                <input type ="hidden" name = "txtCodigo" value = "<%=puntoVenta.getCodigo()%>"><br>
                Nombre: <br>
                <input type ="text" name = "txtNombre" value = "<%=puntoVenta.getNombre()%>"><br>
                Telefono: <br>
                <input type ="text" name = "txtTelefono" value = "<%=puntoVenta.getTelefono()%>"><br>
                Direccion: <br>
                <input type = "text" name = "txtDireccion" value = "<%=puntoVenta.getDireccion()%>"><br>
                <input type = "submit" name = "accion" value = "actualizar"><br>
                <a href="PuntoVentaServlet?accion=listar">Volver</a>
                
            </form>
        </div>
    </body>
</html>
