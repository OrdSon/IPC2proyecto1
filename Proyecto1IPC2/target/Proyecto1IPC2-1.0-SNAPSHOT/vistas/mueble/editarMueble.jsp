<%-- 
    Document   : editarMueble
    Created on : 15/08/2021, 07:00:45 PM
    Author     : ordson
--%>

<%@page import="Modelos.Mueble"%>
<%@page import="DAO.MuebleDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Editar mueble</h1>
        <%
            /*EDICION PASO 3
              Se crea una instancia de MuebleDAO y se hacen 3 cosas:
              Se selecciona al mueble con el codigo guardado en la sesion
              Se muestran los datos de dicho mueble en los campos de texto
              Se envian los campos de texto con sus nuevos contenidos al metodo editar de MuebleDAO para actualizarlos
            */
            MuebleDAO muebleDAO = new MuebleDAO();
            System.out.println((String)request.getSession().getAttribute("codigoMueble")+"  editar.jsp");
            String codigo = (String)request.getSession().getAttribute("codigoMueble");
            Mueble mueble = muebleDAO.listarCodigo(codigo);
        %>
        <div>
            <form>
                
                <input type ="hidden" name = "txtModelo" value = "<%=mueble.getModelo()%>"><br>
                Nombre <br>
                <input type ="text" name = "txtNombre" value = "<%=mueble.getNombre()%>"><br>
                Precio <br>
                <input type ="text" name = "txtPrecio" value = "<%=mueble.getPrecio()%>"><br>
                Costo <br>
                <input type ="text" name = "txtCosto" value = "<%=mueble.getCosto()%>"><br>
                
                <input type = "submit" name = "accion" value = "actualizar"><br>
                <a href="MuebleServlet?accion=listar">Volver</a>
            </form>
        </div>
    </body>
</html>
