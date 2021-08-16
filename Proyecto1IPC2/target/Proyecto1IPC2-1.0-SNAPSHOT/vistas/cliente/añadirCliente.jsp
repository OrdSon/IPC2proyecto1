<%-- 
    Document   : añadirCliente
    Created on : 15/08/2021, 03:47:00 PM
    Author     : ordso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Muebleria clientes</title>
    </head>
    <body>
        <h1>Añadir cliente</h1>
        <form>
            NIT: <br>
            <input type ="text" name = "txtNit"><br>
            Nombre: <br>
            <input type ="text" name = "txtNombre"><br>
            Telefono: <br>
            <input type ="text" name = "txtTelefono"><br>
            Direccion: <br>
            <input type = "text" name = "txtDireccion"><br>
            <input type = "submit" name = "accion" value = "añadir"><br>
        </form>
    </body>
</html>
