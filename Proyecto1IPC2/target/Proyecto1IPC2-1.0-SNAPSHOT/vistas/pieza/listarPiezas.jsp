<%-- 
    Document   : listaClientes
    Created on : 14/08/2021, 06:50:02 PM
    Author     : ordson
--%>

<%@page import="java.sql.Connection"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.PiezaDAO"%>
<%@page import="Modelos.Pieza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Piezaria piezas</title>
    </head>
    <body>

        <h1 class="text-center">Lista de piezas</h1>
        <div style="height: 50px"></div>

        <!--<a href="PiezaServlet?accion=nuevo ">Añadir pieza</a> -->
        <div class="d-flex justify-content-center">

            <div class="card col-sm-4">
                <div class="card-body">
                    <form>
                        <div>
                            <label>Nombre:</label> 
                            <input type ="text" name = "txtNombre" class="form-control"><br>
                        </div>
                        <input type = "submit" name = "accion" value = "añadir"><br>
                    </form>
                </div>
            </div>
            <div style="width: 25px"></div>
            <div class="col-sm-6">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th >Codigo</th>
                            <th>Nombre</th>

                        </tr>
                    </thead>
                    <%
                        PiezaDAO piezaDAO = new PiezaDAO();
                        ArrayList<Pieza> piezas = piezaDAO.listar();
                        Iterator<Pieza> iterator = piezas.iterator();
                        Pieza pieza = null;
                        while (iterator.hasNext()) {
                            pieza = iterator.next();

                    %>
                    <tbody>
                        <tr>
                            <td><%=pieza.getCodigo()%></td>
                            <td><%=pieza.getNombre()%></td>
                            <td>
                                <!-- 
                                EDICION PASO 1
                                Se hace una referencia al objeto PiezaServlet con dos atributos
                                atributo accion = editar, para que me redireccione a editarPieza.jsp
                                y atributo codigo, que obtiene el codigo del pieza para trabajar con el mas adelante-->

                                <a href="PiezaServlet?accion=editar&codigo=<%=pieza.getCodigo()%>" class="btn btn-warning">Editar</a>
                                <!--
                                ELIMINACION PASO 1:
                                Se hace una referencia a PiezaServlet con dos atributos
                                accion = eliminar para ejecutar las acciones
                                y codigo = codigo de la pieza para trabajar con el mas adelante
                                -->
                                <a href="PiezaServlet?accion=eliminar&codigo=<%=pieza.getCodigo()%>" class="btn btn-danger">Eliminar</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
