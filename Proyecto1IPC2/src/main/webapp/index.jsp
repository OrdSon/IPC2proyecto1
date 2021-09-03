<%-- 
    Document   : index
    Created on : 14/08/2021, 06:48:02 PM
    Author     : ordson
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="DAO.PuntoVentaDAO"%>
<%@page import="Modelos.PuntoVenta"%>
<%@page import="java.sql.Connection"%>
<%@page import="Utilidades.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <div class = "container mt-4 col-lg-4 text-center">
            <div class="card col-sm-10">
                <div class="card-body ">
                    <form class="form-sign" action="loginServlet" method="GET">
                        <div class="form-group">
                            <h2>Login</h2>
                        </div>
                        <div class="form-group">
                            <label>Empleado:</label>
                            <input type="text" name ="txtEmpleado" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Contraseña</label>
                            <input type="password" name ="txtContraseña" class="form-control">
                        </div>
                        
                        <input type="submit" name="accion" value="ingresar" class="btn btn-primary btn-block">
                    </form>
                </div>
            </div>
        </div>
        <%Conexion conexion = new Conexion();%>
        <a href="ClienteServlet?accion=listar">Mostrar clientes</a><br>
        <a href="PuntoVentaServlet?accion=listar">Mostrar puntos de venta</a><br>
        <a href="EmpleadoServlet?accion=listar">Mostrar empleados</a>
        <a href="EnsambladorServlet?comando=listar" >Mostrar ensambladores</a>
    </body>
</html>
