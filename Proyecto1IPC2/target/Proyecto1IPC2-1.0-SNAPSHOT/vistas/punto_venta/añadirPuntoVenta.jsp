<%-- 
    Document   : añadirCliente
    Created on : 15/08/2021, 03:47:00 PM
    Author     : ordson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Muebleria sucursales</title>
    </head>
    <body>
        <h1>Añadir punto de venta</h1>
        <form>
            Nombre: <br>
            <input type ="text" name = "txtNombre"><br>
            Telefono: <br>
            <input type ="text" name = "txtTelefono"><br>
            Direccion: <br>
            <input type = "text" name = "txtDireccion"><br>
            <input type = "submit" name = "accion" value = "añadir"><br>
            <a href="PuntoVentaServlet?accion=listar">Volver</a>
        </form>
    </body>
</html>
