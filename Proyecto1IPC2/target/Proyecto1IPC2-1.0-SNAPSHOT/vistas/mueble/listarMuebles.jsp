<%-- 
    Document   : listaMuebles
    Created on : 14/08/2021, 06:50:02 PM
    Author     : ordson
--%>

<%@page import="java.sql.Connection"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.MuebleDAO"%>
<%@page import="Modelos.Mueble"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Muebleria muebles</title>
    </head>
    <body>

        <h1 class="text-center">Lista de muebles</h1>
        <div style="height: 50px"></div>

        <!--<a href="MuebleServlet?accion=nuevo ">Añadir mueble</a> -->
        <div class="d-flex justify-content-center">

            <div class="card col-sm-4">
                <div class="card-body">
                    <form>
                        <div>
                            <label>Modelo: (8 caracteres)</label> 
                            <input type ="text" name = "txtModelo" class="form-control"><br>                        
                        </div>
                        <div>
                            <label>Nombre:</label> 
                            <input type ="text" name = "txtNombre" class="form-control"><br>
                        </div>
                        <div>
                            <label>Precio:</label> 
                            <input type ="text" name = "txtPrecio" class="form-control"><br>  
                        </div>
                        <div>
                            <label>Costo:</label> 
                            <input type = "text" name = "txtCosto" class="form-control"><br>  
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
                            <th >Modelo</th>
                            <th>Nombre</th>
                            <th>Precio</th>
                            <th>Costo</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <%
                        MuebleDAO muebleDAO = new MuebleDAO();
                        ArrayList<Mueble> muebles = muebleDAO.listar();
                        Iterator<Mueble> iterator = muebles.iterator();
                        Mueble mueble = null;
                        while (iterator.hasNext()) {
                            mueble = iterator.next();

                    %>
                    <tbody>
                        <tr>
                            <td><%=mueble.getModelo()%></td>
                            <td><%=mueble.getNombre()%></td>
                            <td><%=mueble.getPrecio()%></td>
                            <td><%=mueble.getCosto()%></td>
                            <td>
                                <!-- 
                                EDICION PASO 1
                                Se hace una referencia al objeto MuebleServlet con dos atributos
                                atributo accion = editar, para que me redireccione a editarMueble.jsp
                                y atributo codigo, que obtiene el codigo del mueble para trabajar con el mas adelante-->

                                <a href="MuebleServlet?accion=editar&codigo=<%=mueble.getModelo()%>" class="btn btn-warning">Editar</a>
                                <!--
                                ELIMINACION PASO 1:
                                Se hace una referencia a MuebleServlet con dos atributos
                                accion = eliminar para ejecutar las acciones
                                y codigo = codigo del mueble para trabajar con el mas adelante
                                -->
                                <a href="MuebleServlet?accion=eliminar&codigo=<%=mueble.getModelo()%>" class="btn btn-danger">Eliminar</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
