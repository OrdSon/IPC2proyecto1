<%-- 
    Document   : editarCliente
    Created on : 15/08/2021, 07:00:45 PM
    Author     : ordson
--%>

<%@page import="Modelos.Cliente"%>
<%@page import="DAO.ClienteDAO"%>
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
            ClienteDAO clienteDAO = new ClienteDAO();
            System.out.println((String)request.getSession().getAttribute("codigoCliente")+"  editar.jsp");
            int codigo = Integer.parseInt((String)request.getSession().getAttribute("codigoCliente"));
            Cliente cliente = clienteDAO.listarCodigo(codigo);
        %>
        <div>
            <form>
                NIT: <br>
                <input type ="hidden" name = "txtCodigo" value = "<%=cliente.getCodigo()%>"><br>
                <input type ="text" name = "txtNit" value = "<%=cliente.getNit()%>"><br>
                Nombre: <br>
                <input type ="text" name = "txtNombre" value = "<%=cliente.getNombre()%>"><br>
                Telefono: <br>
                <input type ="text" name = "txtTelefono" value = "<%=cliente.getTelefono()%>"><br>
                Direccion: <br>
                <input type = "text" name = "txtDireccion" value = "<%=cliente.getDireccion()%>"><br>
                <input type = "submit" name = "accion" value = "actualizar"><br>
                <a href="ClienteServlet?accion=listar">Volver</a>
            </form>
        </div>
    </body>
</html>
