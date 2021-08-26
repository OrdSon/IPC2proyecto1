<%-- 
    Document   : editarPieza
    Created on : 15/08/2021, 07:00:45 PM
    Author     : ordson
--%>

<%@page import="Modelos.Pieza"%>
<%@page import="DAO.PiezaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Editar pieza</h1>
        <%
            /*EDICION PASO 3
              Se crea una instancia de PiezaDAO y se hacen 3 cosas:
              Se selecciona al pieza con el codigo guardado en la sesion
              Se muestran los datos de dicho pieza en los campos de texto
              Se envian los campos de texto con sus nuevos contenidos al metodo editar de PiezaDAO para actualizarlos
             */
            PiezaDAO piezaDAO = new PiezaDAO();
            System.out.println((String) request.getSession().getAttribute("codigoPieza") + "  editar.jsp");
            int codigo = Integer.parseInt((String) request.getSession().getAttribute("codigoPieza"));
            Pieza pieza = piezaDAO.listarCodigo(codigo);
        %>
       
            <div class="card col-sm-6">
                <div class="card-body">
                    <form>
                        <div>
                            <label>Nombre:</label> 
                            <input type ="text" name = "txtNombre" class="form-control" value="<%=pieza.getNombre()%>"><br>                        
                        </div>
                        <input type = "submit" name = "accion" value = "actualizar"><br>
                    </form>
                </div>
            </div>
        
        <%--<form>
            NIT: <br>
            <input type ="hidden" name = "txtCodigo" value = "<%=pieza.getCodigo()%>"><br>
            <input type ="text" name = "txtNit" value = "<%=pieza.getNit()%>"><br>
            Nombre: <br>
            <input type ="text" name = "txtNombre" value = "<%=pieza.getNombre()%>"><br>
            Telefono: <br>
            <input type ="text" name = "txtTelefono" value = "<%=pieza.getTelefono()%>"><br>
            Direccion: <br>
            <input type = "text" name = "txtDireccion" value = "<%=pieza.getDireccion()%>"><br>
            <input type = "submit" name = "accion" value = "actualizar"><br>
            <a href="PiezaServlet?accion=listar">Volver</a>
        </form> --%>

    </body>
</html>
